package com.ljkj.qxn.wisdomsitepro.ui.quality;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;

import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.foamtrace.photopicker.PhotoPickerActivity;
import com.foamtrace.photopicker.PhotoPreviewActivity;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.Utils.PickerDialogHelper;
import com.ljkj.qxn.wisdomsitepro.Utils.widget.InputItemView;
import com.ljkj.qxn.wisdomsitepro.contract.common.ImageCompressorContract;
import com.ljkj.qxn.wisdomsitepro.contract.common.UploadFilesContract;
import com.ljkj.qxn.wisdomsitepro.contract.quality.CheckImmediateRectificationContract;
import com.ljkj.qxn.wisdomsitepro.data.FileEntity;
import com.ljkj.qxn.wisdomsitepro.data.PermissionConstant;
import com.ljkj.qxn.wisdomsitepro.data.entity.EnclosureInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.QualityCheckResultDetail;
import com.ljkj.qxn.wisdomsitepro.data.event.RefreshEvent;
import com.ljkj.qxn.wisdomsitepro.Utils.PhotoPickerHelper;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.model.CommonModel;
import com.ljkj.qxn.wisdomsitepro.model.QualityModel;
import com.ljkj.qxn.wisdomsitepro.presenter.common.ImageCompressorPresenter;
import com.ljkj.qxn.wisdomsitepro.presenter.common.UploadFilesPresenter;
import com.ljkj.qxn.wisdomsitepro.presenter.quality.CheckImmediateRectPresenter;
import com.ljkj.qxn.wisdomsitepro.ui.common.BaseCheckRectificationActivity;
import com.ljkj.qxn.wisdomsitepro.ui.common.SelectImageCallback;
import com.ljkj.qxn.wisdomsitepro.ui.common.adapter.SelectImageAdapter;
import com.ljkj.qxn.wisdomsitepro.ui.quality.adapter.ShowAttachmentAdapter;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;
import cdsp.android.logging.Logger;
import cdsp.android.util.ToastUtils;

/**
 * 立即整改
 * 创建人：lxx
 * 创建时间：2018/3/15 15:00
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class QuaImmediateRectificationActivity extends BaseCheckRectificationActivity implements SelectImageCallback, UploadFilesContract.View, CheckImmediateRectificationContract.View, ImageCompressorContract.View {
    private static final int REQUEST_CODE_IMAGE = 100;

    /**
     * 整改报告扫描件
     */
    private static final int REQUEST_CODE_IMAGE_REPORT = 300;

    /**
     * 定制预案附件
     */
    private static final int REQUEST_CODE_IMAGE_PLAN = 400;

    /**
     * 制定措施附件
     */
    private static final int REQUEST_CODE_IMAGE_MEASURE = 500;
    /**
     * 资金附件
     */
    private static final int REQUEST_CODE_IMAGE_MONEY = 600;

    private SelectImageAdapter selectImageAdapter;

    private String id;
    private String projectName;
    private String projectAddress;
    private String checkNo;

    private ShowAttachmentAdapter reportAdapter;
    private ShowAttachmentAdapter moneyAdapter;
    private ShowAttachmentAdapter planAdapter;
    private ShowAttachmentAdapter measureAdapter;

    private UploadFilesPresenter uploadFilesPresenter;
    private CheckImmediateRectPresenter checkImmediateRectPresenter;
    private ImageCompressorPresenter compressorPresenter;

    private int uploadCount;

    private ArrayList<String> imagePaths = new ArrayList<>();
    private ArrayList<String> imageReportPaths = new ArrayList<>();
    private ArrayList<String> imagePlanPaths = new ArrayList<>();
    private ArrayList<String> imageMeasurePaths = new ArrayList<>();
    private ArrayList<String> imageMoneyPaths = new ArrayList<>();
    private String baseId;


    public static void startActivity(Context context, String id, String projectName, String projectAddress, String checkNo) {
        Intent intent = new Intent(context, QuaImmediateRectificationActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("projectName", projectName);
        intent.putExtra("projectAddress", projectAddress);
        intent.putExtra("checkNo", checkNo);
        context.startActivity(intent);
    }

    @Override
    protected void initUI() {
        super.initUI();
        id = getIntent().getStringExtra("id");
        projectName = getIntent().getStringExtra("projectName");
        projectAddress = getIntent().getStringExtra("projectAddress");
        checkNo = getIntent().getStringExtra("checkNo");

        titleText.setText("立即整改");
        projectNameText.setText(projectName);
        projectAddressText.setText(projectAddress);

        imageRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        imageRecyclerView.setAdapter(selectImageAdapter = new SelectImageAdapter(this));
        selectImageAdapter.setSelectImageCallback(this);

        planItem.setContent("否");
        measureItem.setContent("否");
    }

    @Override
    protected void initData() {
        super.initData();
        uploadFilesPresenter = new UploadFilesPresenter(this, CommonModel.newInstance());
        checkImmediateRectPresenter = new CheckImmediateRectPresenter(this, QualityModel.newInstance());
        compressorPresenter = new ImageCompressorPresenter(this, CommonModel.newInstance());
        addPresenter(compressorPresenter);
        addPresenter(uploadFilesPresenter);
        addPresenter(checkImmediateRectPresenter);

        reportRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        moneyRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        planRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        measureRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        reportRecyclerView.setAdapter(reportAdapter = new ShowAttachmentAdapter(this));
        moneyRecyclerView.setAdapter(moneyAdapter = new ShowAttachmentAdapter(this));
        planRecyclerView.setAdapter(planAdapter = new ShowAttachmentAdapter(this));
        measureRecyclerView.setAdapter(measureAdapter = new ShowAttachmentAdapter(this));

    }

    @Override
    public void viewImage(int position) {
        PhotoPickerHelper.startPreview(this, PhotoPreviewActivity.REQUEST_PREVIEW, imagePaths, position);
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
                        PhotoPickerHelper.startPicker(QuaImmediateRectificationActivity.this, REQUEST_CODE_IMAGE, imagePaths);
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

    @OnClick({R.id.tv_back, R.id.tv_submit, R.id.tv_upload_plan, R.id.tv_upload_measure, R.id.tv_upload_report, R.id.item_plan, R.id.item_measure, R.id.tv_upload_money})
    @Override
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.tv_submit:
                submit2();
                break;
            case R.id.tv_upload_report:
                PhotoPickerHelper.startPicker(QuaImmediateRectificationActivity.this, REQUEST_CODE_IMAGE_REPORT, imageReportPaths);

                break;
            case R.id.item_plan:
                showWhetherDialog(planItem, planLayout);
                break;
            case R.id.item_measure:
                showWhetherDialog(measureItem, measureLayout);

                break;
            case R.id.tv_upload_money:
                PhotoPickerHelper.startPicker(QuaImmediateRectificationActivity.this, REQUEST_CODE_IMAGE_MONEY, imageMoneyPaths);

                break;
            case R.id.tv_upload_plan:
                PhotoPickerHelper.startPicker(QuaImmediateRectificationActivity.this, REQUEST_CODE_IMAGE_PLAN, imagePlanPaths);

                break;
            case R.id.tv_upload_measure:
                PhotoPickerHelper.startPicker(QuaImmediateRectificationActivity.this, REQUEST_CODE_IMAGE_MEASURE, imageMeasurePaths);

                break;
            default:
                break;
        }
    }

    private boolean checkData() {
        if (TextUtils.isEmpty(situationEdit.getText().toString().trim())) {
            ToastUtils.showShort("请输入整改情况");
            return false;
        }

        if (imagePaths.size() == 0) {
            ToastUtils.showShort("请添加整改图片");
            return false;
        }

        if (TextUtils.isEmpty(adviceEdit.getText().toString().trim())) {
            ToastUtils.showShort("请输入监理单位意见");
            return false;
        }

        if (reportAdapter.getItemCount() == 0) {
            ToastUtils.showShort("请上传整改报告扫描件");
            return false;
        }

        if (planItem.getContent().equals("是") && planAdapter.getItemCount() == 0) {
            ToastUtils.showShort("请上传定制预案附件");
            return false;
        }

        if (measureItem.getContent().equals("是") && measureAdapter.getItemCount() == 0) {
            ToastUtils.showShort("请上传定制措施附件");
            return false;
        }

        uploadCount = 2;
        if (planItem.getContent().equals("是")) {
            ++uploadCount;
        } else if (measureItem.getContent().equals("是")) {
            ++uploadCount;
        } else if (imageMoneyPaths.size() > 0) {
            ++uploadCount;
        }

        return true;
    }

    private void showWhetherDialog(final InputItemView inputItemView, final LinearLayout layout) {
        if (isFastDoubleClick()) {
            return;
        }
        final List<String> items = new ArrayList<>();
        items.add("是");
        items.add("否");
        PickerDialogHelper.showPickerOption(this, items, 0, true, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                inputItemView.setContent(items.get(options1));
                layout.setVisibility(options1 == 0 ? View.VISIBLE : View.GONE);
            }
        });
    }

    @Override
    public void showEnclosureInfos(List<EnclosureInfo> data) {
        --uploadCount;
        if (uploadCount == 0) {
            super.hideProgress();
            Logger.d("----------------立即整改：所有文件上传成功----------------------");
            showError("提交成功");
            EventBus.getDefault().post(new RefreshEvent(""));
            jump();
        }
    }


    @Override
    public void rectifySuccess(String id) {
        this.baseId = id;
        jump();
    }

    protected void jump() {
        Intent intent = new Intent(this, QualityCheckActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE_IMAGE) { //图片选择
                imagePaths = data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT);
                selectImageAdapter.loadData(imagePaths);
            } else if (requestCode == PhotoPreviewActivity.REQUEST_PREVIEW) { //图片预览
                imagePaths = data.getStringArrayListExtra(PhotoPreviewActivity.EXTRA_RESULT);
                selectImageAdapter.loadData(imagePaths);
            } else if (requestCode == REQUEST_CODE_IMAGE_REPORT) {
                imageReportPaths = data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT);
                chooseFileSuccess(reportAdapter, imageReportPaths);

            } else if (requestCode == REQUEST_CODE_IMAGE_PLAN) {
                imagePlanPaths = data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT);
                chooseFileSuccess(planAdapter, imagePlanPaths);

            } else if (requestCode == REQUEST_CODE_IMAGE_MEASURE) {
                imageMeasurePaths = data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT);
                chooseFileSuccess(measureAdapter, imageMeasurePaths);

            } else if (requestCode == REQUEST_CODE_IMAGE_MONEY) {
                imageMoneyPaths = data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT);
                chooseFileSuccess(moneyAdapter, imageMoneyPaths);
            }

        }
    }

    private void chooseFileSuccess(ShowAttachmentAdapter attachmentAdapter, List<String> paths) {
        List<FileEntity> list = new ArrayList<>();
        for (String path : paths) {
            File temp = new File(path);
            FileEntity entity = new FileEntity();
            entity.setFilePath(path);
            entity.setFileName(temp.getName());
            list.add(entity);
        }
        attachmentAdapter.loadData(list);
    }

    @Override
    public void showProgress(String message) {
        super.showProgress("数据提交中...");
    }

    @Override
    public void showError(String message) {
        super.showError(message);
        super.hideProgress();
    }

    @Override
    public void hideProgress() {
//        super.hideProgress();
    }

    @Override
    public void showCompressFilePaths(List<String> filePaths) {
        imagePaths = (ArrayList<String>) filePaths;
        doSubmit();
    }

    private void doSubmit() {
        QualityCheckResultDetail.Base base = new QualityCheckResultDetail.Base();
        base.setZgqk(situationEdit.getText().toString().trim());
        base.setProAddress(projectAddress);
        base.setProName(projectName);
        base.setId(id);
        base.setJldwyj(adviceEdit.getText().toString());
        base.setSfzdya(planItem.getContent());
        base.setSfzdcs(measureItem.getContent());
        base.setZgzjse(moneyItem.getContent());
        base.setZgtzsbh(checkNo);

        List<File> imageList = new ArrayList<>();
        List<File> reportList = new ArrayList<>();
        List<File> planList = new ArrayList<>();
        List<File> measureList = new ArrayList<>();
        List<File> moneyList = new ArrayList<>();
        for (String path : imagePaths) {
            imageList.add(new File(path));
        }
        for (String path : imageReportPaths) {
            reportList.add(new File(path));
        }

        if (planItem.getContent().equals("是")) {
            for (String path : imagePlanPaths) {
                planList.add(new File(path));
            }
        }
        if (measureItem.getContent().equals("是")) {
            for (String path : imageMeasurePaths) {
                measureList.add(new File(path));
            }
        }
        for (String path : imageMoneyPaths) {
            moneyList.add(new File(path));
        }
        checkImmediateRectPresenter.dealWithAndFileSave(base, UserManager.getInstance().getProjectId(), imageList, reportList, planList, measureList, moneyList);
    }

    private void submit2() {
        if (!checkData()) {
            return;
        }
        compressorPresenter.compressorImages(imagePaths);
    }


}
