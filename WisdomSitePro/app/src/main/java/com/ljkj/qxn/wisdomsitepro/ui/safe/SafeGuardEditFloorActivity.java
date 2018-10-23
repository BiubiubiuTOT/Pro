package com.ljkj.qxn.wisdomsitepro.ui.safe;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.foamtrace.photopicker.PhotoPickerActivity;
import com.foamtrace.photopicker.PhotoPreviewActivity;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.contract.common.ImageCompressorContract;
import com.ljkj.qxn.wisdomsitepro.contract.common.UploadFilesContract;
import com.ljkj.qxn.wisdomsitepro.contract.safe.SafeGuardAddContract;
import com.ljkj.qxn.wisdomsitepro.data.PermissionConstant;
import com.ljkj.qxn.wisdomsitepro.data.entity.EnclosureInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.SafeGuardEditFloorInfo;
import com.ljkj.qxn.wisdomsitepro.Utils.PhotoPickerHelper;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.model.CommonModel;
import com.ljkj.qxn.wisdomsitepro.model.SafeModel;
import com.ljkj.qxn.wisdomsitepro.presenter.common.ImageCompressorPresenter;
import com.ljkj.qxn.wisdomsitepro.presenter.common.UploadFilesPresenter;
import com.ljkj.qxn.wisdomsitepro.presenter.safe.SafeGuardAddPresenter;
import com.ljkj.qxn.wisdomsitepro.ui.common.SelectImageCallback;
import com.ljkj.qxn.wisdomsitepro.ui.common.adapter.SelectImageAdapter;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseActivity;

/**
 * 类描述：安全防护楼层详情编辑
 * 创建人：rjf
 * 创建时间：2018/3/13 14:41.
 * 修改人：
 * 修改时间
 * 修改备注
 */

public class SafeGuardEditFloorActivity extends BaseActivity implements SelectImageCallback, SafeGuardAddContract.View, ImageCompressorContract.View, UploadFilesContract.View {

    private static final String KEY_GUARD_INSTRUCTION = "guardInstruction";
    private static final String KEY_FLOOR_ID = "floorId";

    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_safe_guard_instruction)
    EditText etSafeGuardInstruction;
    @BindView(R.id.rl_floor_images)
    RecyclerView rlFloorImages;
    @BindView(R.id.bt_submit_add_floor)
    Button btSubmitAddFloor;

    private String guardInstruction;
    private String floorId;

    private SelectImageAdapter floorImageAdapter;
    private ArrayList<String> floorImagePaths = new ArrayList<>();

    SafeGuardAddPresenter presenter;
    ImageCompressorPresenter imageCompressorPresenter;
    UploadFilesPresenter uploadFilesPresenter;

    private String uploadFileId;

    public static void startActivity(Context context, String guardInstruction, String floorId) {
        Intent intent = new Intent(context, SafeGuardEditFloorActivity.class);
        intent.putExtra(KEY_GUARD_INSTRUCTION, guardInstruction);
        intent.putExtra(KEY_FLOOR_ID, floorId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safe_guard_edti_floor);
        ButterKnife.bind(this);

    }

    @Override
    protected void initUI() {
        tvTitle.setText("编辑楼层详情");

        this.guardInstruction = getIntent().getStringExtra(KEY_GUARD_INSTRUCTION);
        this.floorId = getIntent().getStringExtra(KEY_FLOOR_ID);

        etSafeGuardInstruction.setText(guardInstruction);

        rlFloorImages.setLayoutManager(new GridLayoutManager(this, 3));
        rlFloorImages.setAdapter(floorImageAdapter = new SelectImageAdapter(this, 3));
        floorImageAdapter.setSelectImageCallback(this);
    }

    @Override
    protected void initData() {
        presenter = new SafeGuardAddPresenter(this, SafeModel.newInstance());
        addPresenter(presenter);

        imageCompressorPresenter = new ImageCompressorPresenter(this, CommonModel.newInstance());
        addPresenter(imageCompressorPresenter);

        uploadFilesPresenter = new UploadFilesPresenter(this, CommonModel.newInstance());
        addPresenter(uploadFilesPresenter);

    }

    @OnClick({R.id.tv_back, R.id.bt_submit_add_floor})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.bt_submit_add_floor:
                String guardInstruction = etSafeGuardInstruction.getText().toString().trim();
                if (TextUtils.isEmpty(guardInstruction)) {
                    showError("请填写安全防护说明信息");
                    return;
                }

                if (floorImagePaths.isEmpty()) {
                    showError("请上传安全防护图片");
                    return;
                }

                presenter.editFloor(guardInstruction, floorId, UserManager.getInstance().getProjectId());
                break;
        }
    }

    @Override
    public void viewImage(int position) {
        PhotoPickerHelper.startPreview(this, PhotoPreviewActivity.REQUEST_PREVIEW, floorImagePaths, position);
    }

    @Override
    public void addImage() {
        AndPermission.with(this)
                .runtime()
                .permission(PermissionConstant.PERMISSIONS_OF_IMAGE)
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> permissions) {
                        PhotoPickerHelper.startPicker(SafeGuardEditFloorActivity.this, 0, floorImagePaths);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            //图片选择
            if (requestCode == 0) {
                floorImagePaths = data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT);
            }
            //图片预览
            else if (requestCode == PhotoPreviewActivity.REQUEST_PREVIEW) {
                floorImagePaths = data.getStringArrayListExtra(PhotoPreviewActivity.EXTRA_RESULT);
            }
            floorImageAdapter.loadData(floorImagePaths);
        }
    }

    @Override
    public void showAddResult() {

    }

    @Override
    public void showAddResult(SafeGuardEditFloorInfo info) {
        uploadFileId = info.getId();
        imageCompressorPresenter.compressorImages(floorImagePaths);
    }

    @Override
    public void showCompressFilePaths(List<String> data) {
        uploadFilesPresenter.uploadFiles("photo", uploadFileId, "pro_protect_content", "", data);
    }

    @Override
    public void showEnclosureInfos(List<EnclosureInfo> data) {
        showError("信息修改成功");
        finish();
    }
}
