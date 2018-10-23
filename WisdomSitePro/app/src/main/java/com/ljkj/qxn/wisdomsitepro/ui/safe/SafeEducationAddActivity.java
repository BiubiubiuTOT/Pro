package com.ljkj.qxn.wisdomsitepro.ui.safe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.foamtrace.photopicker.PhotoPickerActivity;
import com.foamtrace.photopicker.PhotoPreviewActivity;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.Utils.PhotoPickerHelper;
import com.ljkj.qxn.wisdomsitepro.Utils.PickerDialogHelper;
import com.ljkj.qxn.wisdomsitepro.Utils.widget.InputItemView;
import com.ljkj.qxn.wisdomsitepro.contract.common.ImageCompressorContract;
import com.ljkj.qxn.wisdomsitepro.contract.common.UploadContract;
import com.ljkj.qxn.wisdomsitepro.contract.safe.SafeEducationAddContract;
import com.ljkj.qxn.wisdomsitepro.data.FileEntity;
import com.ljkj.qxn.wisdomsitepro.data.PermissionConstant;
import com.ljkj.qxn.wisdomsitepro.data.entity.SecurityEduTechPerson;
import com.ljkj.qxn.wisdomsitepro.data.event.SafeEducationRefreshEvent;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.model.CommonModel;
import com.ljkj.qxn.wisdomsitepro.model.SafeModel;
import com.ljkj.qxn.wisdomsitepro.presenter.common.ImageCompressorPresenter;
import com.ljkj.qxn.wisdomsitepro.presenter.common.UploadPresenter;
import com.ljkj.qxn.wisdomsitepro.presenter.safe.SafeEducationAddPresenter;
import com.ljkj.qxn.wisdomsitepro.ui.common.SelectImageCallback;
import com.ljkj.qxn.wisdomsitepro.ui.common.adapter.SelectImageAdapter;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseActivity;
import cdsp.android.util.DateUtils;
import cdsp.android.util.ToastUtils;

/**
 * 类描述：新增安全教育
 * 创建人：mhl
 * 创建时间：2018/3/14 15:01
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SafeEducationAddActivity extends BaseActivity implements SafeEducationAddContract.View, ImageCompressorContract.View, SelectImageCallback, UploadContract.View {
    private static final int REQUEST_CODE_TRAINEE = 101;

    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.rl_images)
    RecyclerView rlImages;

    @BindView(R.id.item_name)
    InputItemView nameText;

    @BindView(R.id.item_date)
    InputItemView dateItem;

    @BindView(R.id.et_context)
    EditText etContext;

    @BindView(R.id.item_trainer)
    InputItemView trainerItem;

    @BindView(R.id.item_trainee)
    InputItemView traineeItem;

    @BindView(R.id.item_whether_report)
    InputItemView reportItem;

    @BindView(R.id.tv_submit)
    TextView tvSubmit;

    private SelectImageAdapter selectImageAdapter;

    private ArrayList<String> imagePaths = new ArrayList<>();
    private List<FileEntity> fileEntities = new ArrayList<>();

    private int isSbIndex;
    private Date date;
    Calendar startCa = Calendar.getInstance();
    Calendar endCa = Calendar.getInstance();

    private SafeEducationAddPresenter safeEducationAddPresenter;
    private ImageCompressorPresenter compressorPresenter;
    private UploadPresenter uploadPresenter;

    private List<SecurityEduTechPerson> selectLabors = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_safe_education);
    }

    @Override
    protected void initUI() {
        tvTitle.setText("新增安全教育交底");
        rlImages.setLayoutManager(new GridLayoutManager(this, 4));
        rlImages.setAdapter(selectImageAdapter = new SelectImageAdapter(this));
        selectImageAdapter.setSelectImageCallback(this);
        rlImages.setNestedScrollingEnabled(false);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
    }

    @Override
    protected void initData() {
        dateItem.setContent(DateUtils.calendar2str(Calendar.getInstance(), DateUtils.PATTERN_DATE));
        startCa.setTime(new Date());
        endCa.add(Calendar.YEAR, 10);

        safeEducationAddPresenter = new SafeEducationAddPresenter(this, SafeModel.newInstance());
        compressorPresenter = new ImageCompressorPresenter(this, CommonModel.newInstance());
        uploadPresenter = new UploadPresenter(this, CommonModel.newInstance());
        addPresenter(uploadPresenter);
        addPresenter(safeEducationAddPresenter);
        addPresenter(compressorPresenter);
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
                        PhotoPickerHelper.startPicker(SafeEducationAddActivity.this, 0, imagePaths);
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
            if (requestCode == 0) {            //图片选择
                imagePaths = data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT);
                selectImageAdapter.loadData(imagePaths);
            } else if (requestCode == PhotoPreviewActivity.REQUEST_PREVIEW) {
                imagePaths = data.getStringArrayListExtra(PhotoPreviewActivity.EXTRA_RESULT);
                selectImageAdapter.loadData(imagePaths);
            } else if (requestCode == REQUEST_CODE_TRAINEE) { //受训人
                selectLabors = data.getParcelableArrayListExtra("data");
                if (selectLabors.size() > 0) {
                    StringBuilder stringBuilder = new StringBuilder();
                    for (SecurityEduTechPerson person : selectLabors) {
                        stringBuilder.append(person.teamName).append("，");
                    }
                    traineeItem.setContent(stringBuilder.substring(0, stringBuilder.length() - 1));
                } else {
                    traineeItem.setContent("");
                }
            }

        }
    }

    @Override
    public void hideProgress() {
        super.hideProgress();
    }

    @OnClick({R.id.tv_back, R.id.item_whether_report, R.id.tv_submit, R.id.item_date, R.id.item_trainee})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.item_trainee: //受训人
                SelectTeamActivity.startActivity(this, REQUEST_CODE_TRAINEE);
                break;
            case R.id.item_whether_report:
                PickerDialogHelper.showPickerOption(SafeEducationAddActivity.this, Arrays.asList("是", "否"), isSbIndex, false, new OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                        isSbIndex = options1;
                        reportItem.setTag(options1 == 0 ? "1" : "0");
                        reportItem.setContent(options1 == 0 ? "是" : "否");
                    }
                });
                break;
            case R.id.item_date:
                if (date == null) {
                    date = new Date();
                }
                Calendar curCa = Calendar.getInstance();
                curCa.setTime(date);
                startCa.add(Calendar.YEAR, -10);

                PickerDialogHelper.showTimePicker(this, curCa, startCa, endCa, false, new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        SafeEducationAddActivity.this.date = date;
                        dateItem.setContent(DateUtils.date2str(date, DateUtils.PATTERN_DATE));
                    }
                });
                break;
            case R.id.tv_submit:
                checkAndPostData();
                break;
            default:
                break;
        }
    }

    private void checkAndPostData() {
        String contextName = nameText.getContent();
        String context = etContext.getText().toString().trim();
        String eduPerson = trainerItem.getContent();
        String isReport = reportItem.getContent();

        if (TextUtils.isEmpty(contextName)) {
            showError("请填写交底名称");
            return;
        }
        if (TextUtils.isEmpty(context)) {
            showError("请输入交底内容");
            return;
        }
        if (imagePaths.size() == 0) {
            showError("请添加图片");
            return;
        }
        if (TextUtils.isEmpty(eduPerson)) {
            showError("请输入培训人");
            return;
        }
        if (selectLabors.isEmpty()) {
            showError("请选择受训人");
            return;
        }
        if (TextUtils.isEmpty(isReport)) {
            showError("请选择是否上报监督机构");
            return;
        }
        compressorPresenter.compressorImages(imagePaths);
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
        fileEntities.clear();
        for (FileEntity entity : entities) {
            entity.projId = UserManager.getInstance().getProjectId();
            entity.projCode = UserManager.getInstance().getProjectCode();
            fileEntities.add(entity);
        }
        safeEducationAddPresenter.addSafeEdu(nameText.getContent(), dateItem.getContent(), etContext.getText().toString(),
                fileEntities, trainerItem.getContent(), selectLabors, reportItem.getContent().equals("是") ? 1 : 0, UserManager.getInstance().getProjectId(), UserManager.getInstance().getProjectCode(),
                UserManager.getInstance().getUserId(), UserManager.getInstance().getUserName());
    }

    @Override
    public void showAddSafeEduResult() {
        ToastUtils.showShort("提交成功");
        EventBus.getDefault().post(new SafeEducationRefreshEvent(SafeEducationRefreshEvent.EDUCATION));
        finish();
    }

    @Override
    public void showAddSafeTechnology() { //ignore
    }
}
