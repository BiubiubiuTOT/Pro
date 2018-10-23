package com.ljkj.qxn.wisdomsitepro.ui.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.foamtrace.photopicker.PhotoPickerActivity;
import com.foamtrace.photopicker.PhotoPreviewActivity;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.Utils.PhotoPickerHelper;
import com.ljkj.qxn.wisdomsitepro.Utils.PickerDialogHelper;
import com.ljkj.qxn.wisdomsitepro.Utils.widget.ItemView;
import com.ljkj.qxn.wisdomsitepro.contract.common.ImageCompressorContract;
import com.ljkj.qxn.wisdomsitepro.contract.common.UploadContract;
import com.ljkj.qxn.wisdomsitepro.contract.project.ProjectProgressAddContract;
import com.ljkj.qxn.wisdomsitepro.data.FileEntity;
import com.ljkj.qxn.wisdomsitepro.data.PermissionConstant;
import com.ljkj.qxn.wisdomsitepro.data.entity.ProjectProgressAddInfo;
import com.ljkj.qxn.wisdomsitepro.data.event.RefreshEvent;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.model.CommonModel;
import com.ljkj.qxn.wisdomsitepro.model.ProjectModel;
import com.ljkj.qxn.wisdomsitepro.presenter.common.ImageCompressorPresenter;
import com.ljkj.qxn.wisdomsitepro.presenter.common.UploadPresenter;
import com.ljkj.qxn.wisdomsitepro.presenter.project.ProjectProgressAddPresenter;
import com.ljkj.qxn.wisdomsitepro.ui.common.SelectImageCallback;
import com.ljkj.qxn.wisdomsitepro.ui.common.adapter.SelectImageAdapter;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseActivity;
import cdsp.android.util.DateUtils;
import cdsp.android.util.ToastUtils;


/**
 * 类描述：新增工程形象进度
 * 创建人：mhl
 * 创建时间：2018/2/1 15:44
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class AddProjectImageProgressActivity extends BaseActivity
        implements SelectImageCallback, UploadContract.View, ProjectProgressAddContract.View, ImageCompressorContract.View {

    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.rl_images)
    RecyclerView rlImages;

    @BindView(R.id.item_date)
    ItemView dateItem;

    @BindView(R.id.et_progress)
    EditText progressEdit;

    private UploadPresenter uploadFilesPresenter;
    private ProjectProgressAddPresenter progressAddPresenter;
    private ImageCompressorPresenter imageCompressorPresenter;

    //本地图片
    private ArrayList<String> filePaths = new ArrayList<>();
    private SelectImageAdapter selectImageAdapter;
    private ProjectProgressAddInfo info = new ProjectProgressAddInfo();
    private String content;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_project_image_progress);
    }

    @Override
    protected void initUI() {
        tvTitle.setText("新增工程形象进度");
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        rlImages.setLayoutManager(gridLayoutManager);
        rlImages.setAdapter(selectImageAdapter = new SelectImageAdapter(this));
        selectImageAdapter.setSelectImageCallback(this);
        dateItem.setTag(Calendar.getInstance());
        dateItem.setContent(DateUtils.date2str(Calendar.getInstance().getTime(), DateUtils.PATTERN_DATE_2));
    }

    @Override
    protected void initData() {
        uploadFilesPresenter = new UploadPresenter(this, CommonModel.newInstance());
        progressAddPresenter = new ProjectProgressAddPresenter(this, ProjectModel.newInstance());
        imageCompressorPresenter = new ImageCompressorPresenter(this, CommonModel.newInstance());
        addPresenter(imageCompressorPresenter);
        addPresenter(progressAddPresenter);
        addPresenter(uploadFilesPresenter);
    }

    @OnClick({R.id.tv_back, R.id.item_date, R.id.tv_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.item_date:
                showTimeDialog();
                break;
            case R.id.tv_next:
                if (checkData())
                    uploadFile();
                break;
            default:
                break;
        }
    }

    private void uploadFile() {
        imageCompressorPresenter.compressorImages(filePaths);
    }

    private boolean checkData() {
        if (filePaths.size() == 0) {
            ToastUtils.showShort("请选择进度图片");
            return false;
        }

        content = progressEdit.getText().toString();
        if (TextUtils.isEmpty(content)) {
            ToastUtils.showShort("请填写进度说明");
            return false;
        }
        return true;
    }

    private void submit() {
        info.setProgressExplain(content);
        info.setCreateUserName(UserManager.getInstance().getUserName());
        info.setCreateUserId(UserManager.getInstance().getUserId());
        info.setProjId(UserManager.getInstance().getProjectId());
        info.setProgressDate(dateItem.getContent());

        progressAddPresenter.addProjectProgress(info);
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
                        PhotoPickerHelper.startPicker(AddProjectImageProgressActivity.this, 0, filePaths);
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
            if (requestCode == 0) { //图片选择
                filePaths = data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT);
            } else if (requestCode == PhotoPreviewActivity.REQUEST_PREVIEW) { //图片预览
                filePaths = data.getStringArrayListExtra(PhotoPreviewActivity.EXTRA_RESULT);
            }
            selectImageAdapter.loadData(filePaths);
        }
    }

    private void showTimeDialog() {
        if (isFastDoubleClick()) {
            return;
        }
        final Calendar calendar = (Calendar) dateItem.getTag();
        PickerDialogHelper.showDatePicker(this, calendar, true, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                calendar.setTime(date);
                dateItem.setTag(calendar);
                dateItem.setContent(DateUtils.date2str(date, DateUtils.PATTERN_DATE_2));
            }
        });
    }


    @Override
    public void showProgress(String message) {
        super.showProgress("提交中...");
    }


    @Override
    public void showCompressFilePaths(List<String> paths) {
        List<File> files = new ArrayList<>();
        for (String path : paths) {
            files.add(new File(path));
        }
        uploadFilesPresenter.upload(UserManager.getInstance().getProjectId(), files);
    }

    @Override
    public void showUploadInfo(List<FileEntity> entities) {
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

        info.setFile(jsonArray);
        submit();
    }

    @Override
    public void delaAddProgressResult() {
        ToastUtils.showShort("提交成功");
        EventBus.getDefault().post(new RefreshEvent(""));
        finish();
    }
}
