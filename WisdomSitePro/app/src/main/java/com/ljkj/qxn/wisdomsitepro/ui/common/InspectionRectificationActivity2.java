package com.ljkj.qxn.wisdomsitepro.ui.common;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;

import com.foamtrace.photopicker.PhotoPickerActivity;
import com.foamtrace.photopicker.PhotoPreviewActivity;
import com.ljkj.qxn.wisdomsitepro.contract.common.ImageCompressorContract;
import com.ljkj.qxn.wisdomsitepro.contract.common.InspectRectifyContract;
import com.ljkj.qxn.wisdomsitepro.contract.common.UploadFilesContract;
import com.ljkj.qxn.wisdomsitepro.data.PermissionConstant;
import com.ljkj.qxn.wisdomsitepro.data.entity.EnclosureInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.InspectionInfo;
import com.ljkj.qxn.wisdomsitepro.Utils.PhotoPickerHelper;
import com.ljkj.qxn.wisdomsitepro.model.CommonModel;
import com.ljkj.qxn.wisdomsitepro.model.QualityModel;
import com.ljkj.qxn.wisdomsitepro.presenter.common.ImageCompressorPresenter;
import com.ljkj.qxn.wisdomsitepro.presenter.common.InspectRectifyPresenter;
import com.ljkj.qxn.wisdomsitepro.presenter.common.UploadFilesPresenter;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;

import java.util.ArrayList;
import java.util.List;

/**
 * 类描述：巡检-整改操作
 * 创建人：lxx
 * 创建时间：2018/4/9 15:58
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class InspectionRectificationActivity2 extends BaseInspectionDetailsActivity2 implements SelectImageCallback, InspectRectifyContract.View, UploadFilesContract.View, ImageCompressorContract.View {

    private ArrayList<String> filePaths = new ArrayList<>();
    private InspectRectifyPresenter presenter;
    private UploadFilesPresenter filesPresenter;
    private ImageCompressorPresenter compressorPresenter;
    private String cgFormId;

    @Override
    protected void initUI() {
        super.initUI();
        nextText.setText("提交");
        examineLayout.setVisibility(View.GONE);
        rectifyLayout.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initData() {
        super.initData();
        sceneImageAdapter.setSelectImageCallback(this);
        presenter = new InspectRectifyPresenter(this, QualityModel.newInstance());
        filesPresenter = new UploadFilesPresenter(this, CommonModel.newInstance());
        compressorPresenter = new ImageCompressorPresenter(this, CommonModel.newInstance());
        addPresenter(presenter);
        addPresenter(filesPresenter);
        addPresenter(compressorPresenter);
    }

    @Override
    public void showInspectionDetail(InspectionInfo data) {
        super.showInspectionDetail(data);
        rectifyText.setText("整改回复" + (data.reforms.size() + 1));
        checkDate2Item.setContent(data.check.upTime);
        groupLeader2Item.setContent(data.check.bzfzrShow);
        postMan2Item.setContent(data.check.fwr);
    }

    @Override
    protected void doNext() {
        super.doNext();
        if (TextUtils.isEmpty(cgFormId)) {
            String statusHistory = titleText.getText().toString().contains("重新整改") ? "4" : "2";
            presenter.approvalSave(id, inspectionInfo.check.fwr, rectifyContentEdit.getText().toString(), statusHistory, inspectionInfo.check.bzfzr);
        }
    }

    @Override
    protected boolean checkData() {
        if (inspectionInfo == null) {
            showError("巡检详情获取失败");
            return false;
        }
        if (TextUtils.isEmpty(rectifyContentEdit.getText().toString())) {
            showError("请输入整改内容");
            return false;
        }
        if (filePaths.isEmpty()) {
            showError("请选择图片");
            return false;
        }
        return true;
    }

    @Override
    public void viewImage(int position) {
        PhotoPickerHelper.startPreview(this, PhotoPreviewActivity.REQUEST_PREVIEW, filePaths, position);
    }

    @Override
    public void addImage() {
        AndPermission.with(this)
                .runtime()
                .permission(PermissionConstant.PERMISSIONS_OF_IMAGE)
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> permissions) {
                        //图片选择
                        PhotoPickerHelper.startPicker(InspectionRectificationActivity2.this, 0, filePaths);
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
                filePaths = data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT);
            }
            //图片预览
            else if (requestCode == PhotoPreviewActivity.REQUEST_PREVIEW) {
                filePaths = data.getStringArrayListExtra(PhotoPreviewActivity.EXTRA_RESULT);
            }
            sceneImageAdapter.loadData(filePaths);
        }
    }

    @Override
    public void dealResult(String data) {
        cgFormId = data;
        //图片上传
        if (!filePaths.isEmpty()) {
            compressorPresenter.compressorImages(filePaths);
        } else {
            showError("提交成功");
            finish();
        }
    }

    @Override
    public void showCompressFilePaths(List<String> data) {
        filesPresenter.uploadFiles("reform_file", cgFormId, "pro_check_reform", "", data);
    }

    @Override
    public void showEnclosureInfos(List<EnclosureInfo> data) {
        showError("提交成功");
        finish();
    }
}
