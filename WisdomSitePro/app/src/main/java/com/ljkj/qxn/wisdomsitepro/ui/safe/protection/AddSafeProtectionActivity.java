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
import com.foamtrace.photopicker.PhotoPreviewActivity;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.Utils.PhotoPickerHelper;
import com.ljkj.qxn.wisdomsitepro.contract.common.ImageCompressorContract;
import com.ljkj.qxn.wisdomsitepro.contract.common.UploadContract;
import com.ljkj.qxn.wisdomsitepro.contract.safe.FloorDefendContract;
import com.ljkj.qxn.wisdomsitepro.data.FileEntity;
import com.ljkj.qxn.wisdomsitepro.data.FileType;
import com.ljkj.qxn.wisdomsitepro.data.PermissionConstant;
import com.ljkj.qxn.wisdomsitepro.data.entity.FloorDetail;
import com.ljkj.qxn.wisdomsitepro.data.entity.FloorInfo;
import com.ljkj.qxn.wisdomsitepro.data.event.SafeDefendEvent;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.model.CommonModel;
import com.ljkj.qxn.wisdomsitepro.model.SafeProtectionModel;
import com.ljkj.qxn.wisdomsitepro.presenter.common.ImageCompressorPresenter;
import com.ljkj.qxn.wisdomsitepro.presenter.common.UploadPresenter;
import com.ljkj.qxn.wisdomsitepro.presenter.safe.FloorDefendPresenter;
import com.ljkj.qxn.wisdomsitepro.ui.common.SelectImageCallback;
import com.ljkj.qxn.wisdomsitepro.ui.common.adapter.SelectImageAdapter;
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
 * 类描述：新增安全防护
 * 创建人：lxx
 * 创建时间：2018/9/5
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class AddSafeProtectionActivity extends BaseActivity implements FloorDefendContract.View, ImageCompressorContract.View, UploadContract.View {
    private static final int PREVIEW_CODE = 100;
    private static final int SELECT_CODE = 101;

    @BindView(R.id.tv_title)
    TextView titleText;

    @BindView(R.id.et_content)
    EditText contentEdit;

    @BindView(R.id.tv_image)
    TextView imageText;

    @BindView(R.id.rl_image)
    protected RecyclerView imageRV;

    private SelectImageAdapter imageAdapter;
    private ArrayList<String> imagePaths = new ArrayList<>();
    private List<FileEntity> fileEntities = new ArrayList<>();

    private FloorDefendPresenter floorDefendPresenter;
    private ImageCompressorPresenter compressorPresenter;
    private UploadPresenter uploadPresenter;
    private FloorInfo floorInfo;

    public static void startActivity(Context context, FloorInfo floorInfo) {
        Intent intent = new Intent(context, AddSafeProtectionActivity.class);
        intent.putExtra("floorInfo", floorInfo);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_safe_protection);
    }

    @Override
    protected void initUI() {
        floorInfo = getIntent().getParcelableExtra("floorInfo");
        titleText.setText("新增安全防护-" + floorInfo.floorName);
        imageRV.setLayoutManager(new GridLayoutManager(this, 4));
        imageRV.setAdapter(imageAdapter = new SelectImageAdapter(this));
    }

    @Override
    protected void initData() {
        floorDefendPresenter = new FloorDefendPresenter(this, SafeProtectionModel.newInstance());
        compressorPresenter = new ImageCompressorPresenter(this, CommonModel.newInstance());
        uploadPresenter = new UploadPresenter(this, CommonModel.newInstance());
        addPresenter(floorDefendPresenter);
        addPresenter(compressorPresenter);
        addPresenter(uploadPresenter);

        imageAdapter.setSelectImageCallback(new SelectImageCallback() {
            @Override
            public void viewImage(int position) {
                PhotoPickerHelper.startPreview(AddSafeProtectionActivity.this, PREVIEW_CODE, imagePaths, position, true);
            }

            @Override
            public void addImage() {
                AndPermission.with(AddSafeProtectionActivity.this)
                        .runtime()
                        .permission(PermissionConstant.PERMISSIONS_OF_IMAGE)
                        .onGranted(new Action<List<String>>() {
                            @Override
                            public void onAction(List<String> permissions) {
                                PhotoPickerHelper.startPicker(AddSafeProtectionActivity.this, SELECT_CODE, imagePaths, 9, true, true);
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
        });
    }

    @OnClick({R.id.tv_back, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.tv_submit:
                if (checkData()) {
                    fileEntities.clear();
                    compressorPresenter.compressorImages(imagePaths);
                }
                break;
            default:
                break;
        }
    }

    private boolean checkData() {
        if (TextUtils.isEmpty(contentEdit.getText().toString())) {
            showError("请填写防护说明");
            return false;
        }
        if (imagePaths.size() == 0) {
            showError("请选择防护图片");
            return false;
        }
        return true;
    }

    private void doSubmit() {
        floorDefendPresenter.addFloorDetail(floorInfo.floorId, UserManager.getInstance().getProjectCode(), UserManager.getInstance().getProjectId(), contentEdit.getText().toString(), fileEntities);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case SELECT_CODE:
                    imagePaths = data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT);
                    imageAdapter.loadData(imagePaths);
                    imageText.setText("上传图片 " + imagePaths.size() + "/9");
                    break;

                case PREVIEW_CODE:
                    imagePaths = data.getStringArrayListExtra(PhotoPreviewActivity.EXTRA_RESULT);
                    imageAdapter.loadData(imagePaths);
                    imageText.setText("上传图片 " + imagePaths.size() + "/9");
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void showAddFloorDetail() {
        ToastUtils.showShort("提交成功");
        EventBus.getDefault().post(new SafeDefendEvent());
        finish();
    }

    @Override
    public void showCompressFilePaths(List<String> data) {
        List<File> files = new ArrayList<>();
        for (String path : data) {
            files.add(new File(path));
        }
        uploadPresenter.upload(UserManager.getInstance().getProjectId(), files);
    }

    @Override
    public void showUploadInfo(List<FileEntity> entities) {
        for (FileEntity entity : entities) {
            entity.type = FileType.SafeDefend.TYPE_SAFE_DEFEND;
            entity.projId = UserManager.getInstance().getProjectId();
            entity.projCode = UserManager.getInstance().getProjectCode();
            entity.relId = floorInfo.floorId;
            fileEntities.add(entity);
        }
        doSubmit();
    }

    @Override
    public void showFloorDetail(FloorDetail floorDetail) {//ignore
    }

}
