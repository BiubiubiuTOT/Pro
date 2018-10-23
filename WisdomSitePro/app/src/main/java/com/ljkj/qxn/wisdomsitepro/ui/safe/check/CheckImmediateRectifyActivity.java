package com.ljkj.qxn.wisdomsitepro.ui.safe.check;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.foamtrace.photopicker.PhotoPickerActivity;
import com.foamtrace.photopicker.PhotoPreviewActivity;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.Utils.PhotoPickerHelper;
import com.ljkj.qxn.wisdomsitepro.Utils.PickerDialogHelper;
import com.ljkj.qxn.wisdomsitepro.Utils.widget.InputItemView;
import com.ljkj.qxn.wisdomsitepro.contract.common.ImageCompressorContract;
import com.ljkj.qxn.wisdomsitepro.contract.common.UploadContract;
import com.ljkj.qxn.wisdomsitepro.contract.safe.CheckRectifyContract;
import com.ljkj.qxn.wisdomsitepro.data.FileEntity;
import com.ljkj.qxn.wisdomsitepro.data.FileType;
import com.ljkj.qxn.wisdomsitepro.data.PermissionConstant;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.model.CommonModel;
import com.ljkj.qxn.wisdomsitepro.model.SafeModel;
import com.ljkj.qxn.wisdomsitepro.presenter.common.ImageCompressorPresenter;
import com.ljkj.qxn.wisdomsitepro.presenter.common.UploadPresenter;
import com.ljkj.qxn.wisdomsitepro.presenter.safe.CheckRectifyPresenter;
import com.ljkj.qxn.wisdomsitepro.ui.common.SelectImageCallback;
import com.ljkj.qxn.wisdomsitepro.ui.common.adapter.SelectImageAdapter;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseActivity;
import cdsp.android.util.ToastUtils;

/**
 * 类描述：检查-立即整改(重新整改)
 * 创建人：lxx
 * 创建时间：2018/9/7
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class CheckImmediateRectifyActivity extends BaseActivity implements CheckRectifyContract.View, UploadContract.View, ImageCompressorContract.View {
    private static final int PREVIEW_CODE_RECTIFY = 100;
    private static final int SELECT_CODE_RECTIFY = 101;

    private static final int PREVIEW_CODE_REPORT = 200;
    private static final int SELECT_CODE_REPORT = 201;

    private static final int PREVIEW_CODE_PLAN = 300;
    private static final int SELECT_CODE_PLAN = 301;

    private static final int PREVIEW_CODE_MEASURE = 400;
    private static final int SELECT_CODE_MEASURE = 401;

    private static final int PREVIEW_CODE_MONEY = 500;
    private static final int SELECT_CODE_MONEY = 501;

    private static final int IMAGE_RECTIFY = 1;
    private static final int IMAGE_REPORT = 2;
    private static final int IMAGE_PLAN = 3;
    private static final int IMAGE_MEASURE = 4;
    private static final int IMAGE_MONEY = 5;

    public static final int RECTIFY = 1; //立即整改
    public static final int RE_RECTIFY = 2; //重新整改

    @BindView(R.id.tv_title)
    TextView titleText;

    @BindView(R.id.et_rectify)
    EditText rectifyEdit;

    @BindView(R.id.rl_rectify)
    RecyclerView rectifyRV;

    @BindView(R.id.rl_report)
    RecyclerView reportRV;

    @BindView(R.id.rl_plan)
    RecyclerView planRV;

    @BindView(R.id.rl_measure)
    RecyclerView measureRV;

    @BindView(R.id.rl_money)
    RecyclerView moneyRV;

    @BindView(R.id.item_plan)
    InputItemView planItem;

    @BindView(R.id.item_measure)
    InputItemView measureItem;

    @BindView(R.id.item_money)
    InputItemView moneyItem;

    @BindView(R.id.ll_plan)
    LinearLayout planLayout;

    @BindView(R.id.ll_measure)
    LinearLayout measureLayout;

    private SelectImageAdapter rectifyImageAdapter;
    private SelectImageAdapter reportImageAdapter;
    private SelectImageAdapter planImageAdapter;
    private SelectImageAdapter measureImageAdapter;
    private SelectImageAdapter moneyImageAdapter;
    private ArrayList<String> rectifyImagePaths = new ArrayList<>();
    private ArrayList<String> reportImagePaths = new ArrayList<>();
    private ArrayList<String> planImagePaths = new ArrayList<>();
    private ArrayList<String> measureImagePaths = new ArrayList<>();
    private ArrayList<String> moneyImagePaths = new ArrayList<>();

    private UploadPresenter uploadPresenter;
    private CheckRectifyPresenter checkRectifyPresenter;
    private ImageCompressorPresenter compressorPresenter;

    private int imageType;
    private SparseArray<String> typeString = new SparseArray<>();
    private SparseArray<List<String>> compressMap = new SparseArray<>();
    private List<FileEntity> fileEntities = new ArrayList<>();
    private String id;
    private int checkType;
    private int rectifyType;

    public static void startActivity(Context context, String id, int checkType, int rectifyType) {
        Intent intent = new Intent(context, CheckImmediateRectifyActivity.class);
        intent.putExtra("checkType", checkType);
        intent.putExtra("id", id);
        intent.putExtra("rectifyType", rectifyType);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_immediate_rectify);
    }

    @Override
    protected void initUI() {
        checkType = getIntent().getIntExtra("checkType", CheckListActivity.TYPE_SAFE_CHECK);
        id = getIntent().getStringExtra("id");
        rectifyType = getIntent().getIntExtra("rectifyType", RECTIFY);
        titleText.setText(rectifyType == RECTIFY ? "立即整改" : "重新整改");
        planItem.setContent("否");
        measureItem.setContent("否");
        planItem.getTitleText().setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        measureItem.getTitleText().setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        moneyItem.getTitleText().setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);

        rectifyRV.setNestedScrollingEnabled(false);
        rectifyRV.setLayoutManager(new GridLayoutManager(this, 4));
        rectifyRV.setAdapter(rectifyImageAdapter = new SelectImageAdapter(this));

        reportRV.setNestedScrollingEnabled(false);
        reportRV.setLayoutManager(new GridLayoutManager(this, 4));
        reportRV.setAdapter(reportImageAdapter = new SelectImageAdapter(this));

        planRV.setNestedScrollingEnabled(false);
        planRV.setLayoutManager(new GridLayoutManager(this, 4));
        planRV.setAdapter(planImageAdapter = new SelectImageAdapter(this));

        measureRV.setNestedScrollingEnabled(false);
        measureRV.setLayoutManager(new GridLayoutManager(this, 4));
        measureRV.setAdapter(measureImageAdapter = new SelectImageAdapter(this));

        moneyRV.setNestedScrollingEnabled(false);
        moneyRV.setLayoutManager(new GridLayoutManager(this, 4));
        moneyRV.setAdapter(moneyImageAdapter = new SelectImageAdapter(this));
    }

    @Override
    protected void initData() {
        typeString.put(IMAGE_RECTIFY, FileType.SafeCheck.TYPE_SAFE_CHECK_RECTIFY);
        typeString.put(IMAGE_REPORT, FileType.SafeCheck.TYPE_SAFE_CHECK_REPORT);
        typeString.put(IMAGE_PLAN, FileType.SafeCheck.TYPE_SAFE_CHECK_PLAN);
        typeString.put(IMAGE_MEASURE, FileType.SafeCheck.TYPE_SAFE_CHECK_MEASURE);
        typeString.put(IMAGE_MONEY, FileType.SafeCheck.TYPE_SAFE_CHECK_MONEY);
        checkRectifyPresenter = new CheckRectifyPresenter(this, SafeModel.newInstance());
        uploadPresenter = new UploadPresenter(this, CommonModel.newInstance());
        compressorPresenter = new ImageCompressorPresenter(this, CommonModel.newInstance());
        addPresenter(checkRectifyPresenter);
        addPresenter(uploadPresenter);
        addPresenter(compressorPresenter);

        rectifyImageAdapter.setSelectImageCallback(new MySelectImageCallback(SELECT_CODE_RECTIFY, PREVIEW_CODE_RECTIFY, rectifyImagePaths));
        reportImageAdapter.setSelectImageCallback(new MySelectImageCallback(SELECT_CODE_REPORT, PREVIEW_CODE_REPORT, reportImagePaths));
        planImageAdapter.setSelectImageCallback(new MySelectImageCallback(SELECT_CODE_PLAN, PREVIEW_CODE_PLAN, planImagePaths));
        measureImageAdapter.setSelectImageCallback(new MySelectImageCallback(SELECT_CODE_MEASURE, PREVIEW_CODE_MEASURE, measureImagePaths));
        moneyImageAdapter.setSelectImageCallback(new MySelectImageCallback(SELECT_CODE_MONEY, PREVIEW_CODE_MONEY, moneyImagePaths));
    }

    @OnClick({R.id.tv_back, R.id.tv_submit, R.id.item_plan, R.id.item_measure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.tv_submit:
                //FIXME
                if (checkData()) {
                    //开始压缩图片
                    compressMap.clear();
                    fileEntities.clear();
                    imageType = IMAGE_RECTIFY;
                    compressorPresenter.compressorImages(rectifyImagePaths);
                }
                break;
            case R.id.item_plan:
                showWhetherDialog(planItem, planLayout);
                break;
            case R.id.item_measure:
                showWhetherDialog(measureItem, measureLayout);
            default:
                break;
        }
    }

    //正式开始提交整改
    private void doSubmit() {
        String rectifyInfo = rectifyEdit.getText().toString();
        String isPlan = planItem.getContent().equals("是") ? "1" : "0";
        String isMeasure = measureItem.getContent().equals("是") ? "1" : "0";
        String money = moneyItem.getContent();
        String reformer = UserManager.getInstance().getUserName();
        String proId = UserManager.getInstance().getProjectId();
        String proCode = UserManager.getInstance().getProjectCode();
        if (checkType == CheckListActivity.TYPE_SAFE_CHECK) {
            checkRectifyPresenter.safeCheckRectify(id, rectifyInfo, isPlan, isMeasure, money, reformer, proId, proCode, fileEntities);
        } else {
            checkRectifyPresenter.qualityCheckRectify(id, rectifyInfo, isPlan, isMeasure, money, reformer, proId, proCode, fileEntities);
        }
    }

    @Override
    public void showSafeRectifySuccess() {
        ToastUtils.showShort("安全检查整改成功！");
        super.hideProgress();
        Intent intent = new Intent(this, CheckListActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

    @Override
    public void showQualityRectifySuccess() {
        ToastUtils.showShort("质量检查整改成功！");
        super.hideProgress();
        Intent intent = new Intent(this, CheckListActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

    @Override
    public void showCompressFilePaths(List<String> data) {
        compressMap.put(imageType, data);
        if (compressMap.indexOfKey(IMAGE_REPORT) < 0) {
            imageType = IMAGE_REPORT;
            compressorPresenter.compressorImages(reportImagePaths);
        } else if (compressMap.indexOfKey(IMAGE_PLAN) < 0 && planItem.getContent().equals("是")) {
            imageType = IMAGE_PLAN;
            compressorPresenter.compressorImages(planImagePaths);
        } else if (compressMap.indexOfKey(IMAGE_MEASURE) < 0 && measureItem.getContent().equals("是")) {
            imageType = IMAGE_MEASURE;
            compressorPresenter.compressorImages(measureImagePaths);
        } else if (compressMap.indexOfKey(IMAGE_MONEY) < 0 && moneyImagePaths.size() > 0) {
            imageType = IMAGE_MONEY;
            compressorPresenter.compressorImages(moneyImagePaths);
        } else { //全部压缩完成，开始正式上传文件
            uploadFile(IMAGE_RECTIFY, compressMap.get(IMAGE_RECTIFY));
            compressMap.remove(IMAGE_RECTIFY);
        }
    }

    private void uploadFile(int type, List<String> paths) {
        imageType = type;
        List<File> files = new ArrayList<>();
        for (String path : paths) {
            files.add(new File(path));
        }
        uploadPresenter.upload(UserManager.getInstance().getProjectId(), files);
    }

    @Override
    public void showUploadInfo(List<FileEntity> entities) {
        for (FileEntity entity : entities) {
            entity.type = typeString.get(imageType);
            entity.projId = UserManager.getInstance().getProjectId();
            entity.projCode = UserManager.getInstance().getProjectCode();
            fileEntities.add(entity);
        }
        if (compressMap.size() == 0) { //全部上传完成
            doSubmit();
            return;
        }
        uploadFile(compressMap.keyAt(0), compressMap.valueAt(0));
        compressMap.removeAt(0);
    }

    private boolean checkData() {
        if (TextUtils.isEmpty(rectifyEdit.getText().toString().trim())) {
            ToastUtils.showShort("请输入整改情况");
            return false;
        }

        if (rectifyImagePaths.size() == 0) {
            ToastUtils.showShort("请添加整改情况图片");
            return false;
        }

        if (reportImagePaths.size() == 0) {
            ToastUtils.showShort("请上传整改报告书扫描件");
            return false;
        }

        if (planItem.getContent().equals("是") && planImagePaths.size() == 0) {
            ToastUtils.showShort("请上传定制预案附件");
            return false;
        }

        if (measureItem.getContent().equals("是") && measureImagePaths.size() == 0) {
            ToastUtils.showShort("请上传定制措施附件");
            return false;
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
    public void showProgress(String message) {
        super.showProgress("正在提交中...");
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case SELECT_CODE_RECTIFY:
                    rectifyImagePaths.clear();
                    rectifyImagePaths.addAll(data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT));
                    rectifyImageAdapter.loadData(rectifyImagePaths);
                    break;
                case PREVIEW_CODE_RECTIFY:
                    rectifyImagePaths.clear();
                    rectifyImagePaths.addAll(data.getStringArrayListExtra(PhotoPreviewActivity.EXTRA_RESULT));
                    rectifyImageAdapter.loadData(rectifyImagePaths);
                    break;

                case SELECT_CODE_REPORT:
                    reportImagePaths.clear();
                    reportImagePaths.addAll(data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT));
                    reportImageAdapter.loadData(reportImagePaths);
                    break;
                case PREVIEW_CODE_REPORT:
                    reportImagePaths.clear();
                    reportImagePaths.addAll(data.getStringArrayListExtra(PhotoPreviewActivity.EXTRA_RESULT));
                    reportImageAdapter.loadData(reportImagePaths);
                    break;

                case SELECT_CODE_PLAN:
                    planImagePaths.clear();
                    planImagePaths.addAll(data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT));
                    planImageAdapter.loadData(planImagePaths);
                    break;
                case PREVIEW_CODE_PLAN:
                    planImagePaths.clear();
                    planImagePaths.addAll(data.getStringArrayListExtra(PhotoPreviewActivity.EXTRA_RESULT));
                    planImageAdapter.loadData(planImagePaths);
                    break;

                case SELECT_CODE_MEASURE:
                    measureImagePaths.clear();
                    measureImagePaths.addAll(data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT));
                    measureImageAdapter.loadData(measureImagePaths);
                    break;
                case PREVIEW_CODE_MEASURE:
                    measureImagePaths.clear();
                    measureImagePaths.addAll(data.getStringArrayListExtra(PhotoPreviewActivity.EXTRA_RESULT));
                    measureImageAdapter.loadData(measureImagePaths);
                    break;

                case SELECT_CODE_MONEY:
                    moneyImagePaths.clear();
                    moneyImagePaths.addAll(data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT));
                    moneyImageAdapter.loadData(moneyImagePaths);
                    break;
                case PREVIEW_CODE_MONEY:
                    moneyImagePaths.clear();
                    moneyImagePaths.addAll(data.getStringArrayListExtra(PhotoPreviewActivity.EXTRA_RESULT));
                    moneyImageAdapter.loadData(moneyImagePaths);
                    break;
                default:
                    break;
            }
        }
    }

    private class MySelectImageCallback implements SelectImageCallback {
        private int selectCode, previewCode;
        private ArrayList<String> imagePaths;

        MySelectImageCallback(int selectCode, int previewCode, ArrayList<String> imagePaths) {
            this.selectCode = selectCode;
            this.previewCode = previewCode;
            this.imagePaths = imagePaths;
        }

        @Override
        public void viewImage(int position) {
            PhotoPickerHelper.startPreview(CheckImmediateRectifyActivity.this, previewCode, imagePaths, position, true);
        }

        @Override
        public void addImage() {
            AndPermission.with(CheckImmediateRectifyActivity.this)
                    .runtime()
                    .permission(PermissionConstant.PERMISSIONS_OF_IMAGE)
                    .onGranted(new Action<List<String>>() {
                        @Override
                        public void onAction(List<String> permissions) {
                            PhotoPickerHelper.startPicker(CheckImmediateRectifyActivity.this, selectCode, imagePaths, 9, true, true);
                        }
                    })
                    .onDenied(new Action<List<String>>() {
                        @Override
                        public void onAction(List<String> permissions) {
                            ToastUtils.showShort("用户已禁止访问图片权限");
                        }
                    })
                    .start();
        }
    }

}
