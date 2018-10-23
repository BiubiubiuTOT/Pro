package com.ljkj.qxn.wisdomsitepro.ui.safe.protection;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.foamtrace.photopicker.PhotoPickerActivity;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.Utils.PhotoPickerHelper;
import com.ljkj.qxn.wisdomsitepro.contract.common.DeleteFileRelationContract;
import com.ljkj.qxn.wisdomsitepro.contract.common.QueryFileContract;
import com.ljkj.qxn.wisdomsitepro.contract.common.UploadContract;
import com.ljkj.qxn.wisdomsitepro.contract.safe.FloorDefendContract;
import com.ljkj.qxn.wisdomsitepro.data.FileEntity;
import com.ljkj.qxn.wisdomsitepro.data.FileType;
import com.ljkj.qxn.wisdomsitepro.data.PermissionConstant;
import com.ljkj.qxn.wisdomsitepro.data.api.HostConfig;
import com.ljkj.qxn.wisdomsitepro.data.entity.FloorDetail;
import com.ljkj.qxn.wisdomsitepro.data.event.SafeDefendEvent;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.model.CommonModel;
import com.ljkj.qxn.wisdomsitepro.model.SafeProtectionModel;
import com.ljkj.qxn.wisdomsitepro.presenter.common.DeleteFileRelationPresenter;
import com.ljkj.qxn.wisdomsitepro.presenter.common.QueryFilePresenter;
import com.ljkj.qxn.wisdomsitepro.presenter.common.UploadPresenter;
import com.ljkj.qxn.wisdomsitepro.presenter.safe.FloorDefendPresenter;
import com.ljkj.qxn.wisdomsitepro.ui.common.adapter.ChooseImageAdapter;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseActivity;
import cdsp.android.util.ToastUtils;

/**
 * 类描述：编辑安全防护
 * 创建人：lxx
 * 创建时间：2018/9/17
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class EditSafeProtectionActivity extends BaseActivity implements QueryFileContract.View, UploadContract.View, FloorDefendContract.View, DeleteFileRelationContract.View {
    private static final int SELECT_CODE = 101;

    @BindView(R.id.tv_title)
    TextView titleText;

    @BindView(R.id.et_content)
    EditText contentText;

    @BindView(R.id.rl_image)
    RecyclerView imageRV;

    @BindView(R.id.tv_image)
    TextView imageText;

    private ChooseImageAdapter chooseImageAdapter;

    private FloorDetail floorDetail;
    private QueryFilePresenter queryFilePresenter;
    private UploadPresenter uploadPresenter;
    private FloorDefendPresenter floorDefendPresenter;
    private DeleteFileRelationPresenter deleteFileRelationPresenter;

    private List<String> fileIds = new ArrayList<>();
    private List<String> deleteFileIds = new ArrayList<>();
    private ArrayList<String> imagePaths = new ArrayList<>();
    private List<FileEntity> fileEntities = new ArrayList<>();

    public static void startActivity(Context context, FloorDetail floorDetail) {
        Intent intent = new Intent(context, EditSafeProtectionActivity.class);
        intent.putExtra("floorDetail", floorDetail);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_safe_protection);
    }

    @Override
    protected void initUI() {
        floorDetail = getIntent().getParcelableExtra("floorDetail");
        titleText.setText("编辑");
        contentText.setText(floorDetail.buildDefendDetail);

        imageText.setVisibility(View.GONE);
        imageRV.setVisibility(View.GONE);
    }

    @Override
    protected void initData() {
        queryFilePresenter = new QueryFilePresenter(this, CommonModel.newInstance());
        uploadPresenter = new UploadPresenter(this, CommonModel.newInstance());
        floorDefendPresenter = new FloorDefendPresenter(this, SafeProtectionModel.newInstance());
        deleteFileRelationPresenter = new DeleteFileRelationPresenter(this, CommonModel.newInstance());
        addPresenter(deleteFileRelationPresenter);
        addPresenter(floorDefendPresenter);
        addPresenter(uploadPresenter);
        addPresenter(queryFilePresenter);
        imageRV.setLayoutManager(new GridLayoutManager(this, 4));
        imageRV.setAdapter(chooseImageAdapter = new ChooseImageAdapter(null));

        chooseImageAdapter.setCallback(new ChooseImageAdapter.Callback() {
            @Override
            public void addImage() {
                AndPermission.with(EditSafeProtectionActivity.this)
                        .runtime()
                        .permission(PermissionConstant.PERMISSIONS_OF_IMAGE)
                        .onGranted(new Action<List<String>>() {
                            @Override
                            public void onAction(List<String> permissions) {
                                ArrayList<String> list = new ArrayList<>();
                                for (String path : imagePaths) {
                                    if (!path.startsWith("http")) {
                                        list.add(path);
                                    }
                                }
                                PhotoPickerHelper.startPicker(EditSafeProtectionActivity.this, SELECT_CODE, list, 9 - fileIds.size(), true, true);
                            }
                        })
                        .onDenied(new Action<List<String>>() {
                            @Override
                            public void onAction(List<String> permissions) {
                                showError("用户已禁止访问图片权限");
                            }
                        })
                        .start();
            }

            @Override
            public void deleteImage(int position) {
                for (String fileId : fileIds) {
                    if (chooseImageAdapter.getItem(position).contains(fileId)) {
                        fileIds.remove(fileId);
                        deleteFileIds.add(fileId);
                        break;
                    }
                }
                chooseImageAdapter.remove(position);
                imageText.setText("编辑图片 " + chooseImageAdapter.getData().size() + "/9");
                chooseImageAdapter.notifyDataSetChanged();
            }

            @Override
            public void previewImage(int position) {
                PhotoPickerHelper.startPreview(EditSafeProtectionActivity.this, chooseImageAdapter.getItem(position));
            }
        });

        queryFilePresenter.queryFile(floorDetail.id);
    }

    @OnClick({R.id.tv_back, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.tv_submit:
                if (checkData()) {
                    if (deleteFileIds.size() > 0) {
//                        deleteFilesPresenter.deleteFiles(deleteFileIds);
                        deleteFileRelationPresenter.deleteFilesRelation(deleteFileIds);
                    } else {
//                        showDeleteFiles();
                        showDeleteFilesRelation();
                    }
                }
                break;
            default:
                break;
        }
    }

    private boolean checkData() {
        if (TextUtils.isEmpty(contentText.getText().toString())) {
            showError("请填写防护说明");
            return false;
        }
        if (chooseImageAdapter.getData().size() == 0) {
            showError("请选择防护图片");
            return false;
        }
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case SELECT_CODE:
                    imagePaths.clear();
                    for (String id : fileIds) {
                        imagePaths.add(HostConfig.getFileDownUrl(id));
                    }
                    imagePaths.addAll(data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT));
                    chooseImageAdapter.setNewData(imagePaths);
                    imageText.setText("编辑图片 " + imagePaths.size() + "/9");
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void showFiles(List<FileEntity> fileEntities) {
        if (fileEntities.size() > 0) {
            imageText.setVisibility(View.VISIBLE);
            imageRV.setVisibility(View.VISIBLE);
        }
        for (FileEntity fileEntity : fileEntities) {
            fileIds.add(fileEntity.fileId);
            imagePaths.add(HostConfig.getFileDownUrl(fileEntity.fileId));
        }
        imageText.setText("编辑图片 " + imagePaths.size() + "/9");
        chooseImageAdapter.setNewData(imagePaths);
    }

    @Override
    public void showUploadInfo(List<FileEntity> entities) {
        if (entities != null) {
            for (FileEntity entity : entities) {
                entity.type = FileType.SafeDefend.TYPE_SAFE_DEFEND;
                entity.projId = UserManager.getInstance().getProjectId();
                entity.projCode = UserManager.getInstance().getProjectCode();
                entity.relId = floorDetail.id;
                fileEntities.add(entity);
            }
        }
        floorDefendPresenter.addFloorDetail(floorDetail.id, UserManager.getInstance().getProjectCode(), UserManager.getInstance().getProjectId(), contentText.getText().toString(), fileEntities);
    }

    @Override
    public void showAddFloorDetail() {
        ToastUtils.showShort("编辑成功");
        EventBus.getDefault().post(new SafeDefendEvent());
        finish();
    }

    @Override
    public void showDeleteFilesRelation() {
        List<File> list = new ArrayList<>();
        for (String path : chooseImageAdapter.getData()) {
            if (!path.startsWith("http")) {
                list.add(new File(path));
            }
        }
        if (!list.isEmpty()) {
            uploadPresenter.upload(UserManager.getInstance().getProjectId(), list);
        } else {
            showUploadInfo(null);
        }
    }

    @Override
    public void showFloorDetail(FloorDetail floorDetail) { //ignore

    }

}
