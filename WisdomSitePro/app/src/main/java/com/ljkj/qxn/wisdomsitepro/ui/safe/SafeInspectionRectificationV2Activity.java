package com.ljkj.qxn.wisdomsitepro.ui.safe;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;

import com.foamtrace.photopicker.PhotoPickerActivity;
import com.foamtrace.photopicker.PhotoPreviewActivity;
import com.ljkj.qxn.wisdomsitepro.Utils.PhotoPickerHelper;
import com.ljkj.qxn.wisdomsitepro.contract.common.ImageCompressorContract;
import com.ljkj.qxn.wisdomsitepro.contract.common.UploadContract;
import com.ljkj.qxn.wisdomsitepro.contract.safe.SafeInspectRectifyContract;
import com.ljkj.qxn.wisdomsitepro.data.Consts;
import com.ljkj.qxn.wisdomsitepro.data.FileEntity;
import com.ljkj.qxn.wisdomsitepro.data.PermissionConstant;
import com.ljkj.qxn.wisdomsitepro.data.entity.SafePatrolDetailInfo;
import com.ljkj.qxn.wisdomsitepro.data.event.InspectionEvent;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.model.CommonModel;
import com.ljkj.qxn.wisdomsitepro.model.SafeModel;
import com.ljkj.qxn.wisdomsitepro.presenter.common.ImageCompressorPresenter;
import com.ljkj.qxn.wisdomsitepro.presenter.common.UploadPresenter;
import com.ljkj.qxn.wisdomsitepro.presenter.safe.SafeInspectRectifyPresenter;
import com.ljkj.qxn.wisdomsitepro.ui.common.SelectImageCallback;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 类描述：巡检-整改操作
 * 创建人：lxx
 * 创建时间：2018/4/9 15:58
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SafeInspectionRectificationV2Activity extends SafeInspectionDetailV2Activity implements SelectImageCallback, SafeInspectRectifyContract.View, UploadContract.View, ImageCompressorContract.View {

    private ArrayList<String> filePaths = new ArrayList<>();
    private SafeInspectRectifyPresenter presenter;
    private UploadPresenter uploadPresenter;
    private ImageCompressorPresenter compressorPresenter;
    private String content;

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
        presenter = new SafeInspectRectifyPresenter(this, SafeModel.newInstance());
        uploadPresenter = new UploadPresenter(this, CommonModel.newInstance());
        compressorPresenter = new ImageCompressorPresenter(this, CommonModel.newInstance());
        addPresenter(presenter);
        addPresenter(uploadPresenter);
        addPresenter(compressorPresenter);
    }

    @Override
    public void showSafePatrolDetail(SafePatrolDetailInfo data) {
        super.showSafePatrolDetail(data);
        List<SafePatrolDetailInfo.ReformBean> reform = data.getReform();
        SafePatrolDetailInfo.CheckBean check = data.getCheck();
        rectifyText.setText("整改回复" + (reform.size() + 1));
        checkDate2Item.setContent(check.getCheckDate());
        groupLeader2Item.setContent(safePatrolDetailInfo.getCheck().getTeamManager());
        postMan2Item.setContent(safePatrolDetailInfo.getCheck().getCreateUsername());
    }

    @Override
    protected void doNext() {
        content = rectifyContentEdit.getText().toString();
        if (TextUtils.isEmpty(content)) {
            showError("请填写整改回复");
            return;
        }
        super.doNext();
        if (!filePaths.isEmpty()) {
            compressorPresenter.compressorImages(filePaths);
        } else {
            rectification(null);
        }
    }

    /**
     * 立即/重新整改
     *
     * @param entities
     */
    private void rectification(List<FileEntity> entities) {
        JSONArray jsonArray = new JSONArray();
        if (entities != null) {
            try {
                for (FileEntity fileEntity : entities) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("fileExt", fileEntity.fileExt);
                    jsonObject.put("fileId", fileEntity.fileId);
                    jsonObject.put("fileName", fileEntity.fileName);
                    jsonObject.put("fileSize", fileEntity.fileSize);
                    jsonObject.put("type", fileEntity.type);
                    jsonObject.put("projCode", fileEntity.projCode);
                    jsonObject.put("projId", fileEntity.projId);
                    jsonArray.put(jsonObject);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        SafePatrolDetailInfo.CheckBean check = safePatrolDetailInfo.getCheck();
        HashMap<String, Object> params = new HashMap<>();
        params.put("content", content);
        params.put("createUserName", UserManager.getInstance().getUserName());
        params.put("createUserId", UserManager.getInstance().getUserId());
        params.put("file", jsonArray);
        params.put("securityPatrolId", id);

        presenter.safeInspectRectify(params);
    }

    @Override
    protected boolean checkData() {
        if (safePatrolDetailInfo == null) {
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
                        PhotoPickerHelper.startPicker(SafeInspectionRectificationV2Activity.this, 0, filePaths);
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
        EventBus.getDefault().post(new InspectionEvent(Consts.EVENT_TYPE.INSPECTION_PASS));
        showError("提交成功");
        finish();
    }

    @Override
    public void showCompressFilePaths(List<String> data) {
        uploadFile(data);
    }

    private void uploadFile(List<String> paths) {
        List<File> files = new ArrayList<>();
        for (String path : paths) {
            files.add(new File(path));
        }
        uploadPresenter.upload(UserManager.getInstance().getProjectId(), files);
    }


    @Override
    public void showUploadInfo(List<FileEntity> entities) {
        for (FileEntity entity : entities) {
            entity.setProjId(UserManager.getInstance().getProjectId());
        }

        rectification(entities);
    }
}
