package com.ljkj.qxn.wisdomsitepro.ui.safe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
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
import com.ljkj.qxn.wisdomsitepro.contract.common.SecurityOfficerListContract;
import com.ljkj.qxn.wisdomsitepro.contract.common.UploadContract;
import com.ljkj.qxn.wisdomsitepro.contract.safe.SafeEducationAddContract;
import com.ljkj.qxn.wisdomsitepro.data.FileEntity;
import com.ljkj.qxn.wisdomsitepro.data.PermissionConstant;
import com.ljkj.qxn.wisdomsitepro.data.entity.SecurityEduTechPerson;
import com.ljkj.qxn.wisdomsitepro.data.entity.SecurityOfficerInfo;
import com.ljkj.qxn.wisdomsitepro.data.event.SafeEducationRefreshEvent;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.model.CommonModel;
import com.ljkj.qxn.wisdomsitepro.model.SafeModel;
import com.ljkj.qxn.wisdomsitepro.presenter.common.ImageCompressorPresenter;
import com.ljkj.qxn.wisdomsitepro.presenter.common.SecurityOfficerPresenter;
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
 * 类描述：新增安全技术交底
 * 创建人：lxx
 * 创建时间：2018/7/18
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SafeTechnologyDiscloseAddActivity extends BaseActivity implements ImageCompressorContract.View, UploadContract.View, SafeEducationAddContract.View, SecurityOfficerListContract.View {
    private static final int REQUEST_IMAGE_PICK_CODE = 200;
    private static final int REQUEST_IMAGE_PREVIEW_CODE = 201;
    private static final int REQUEST_CODE_TRAINEE = 101;

    @BindView(R.id.tv_title)
    TextView titleText;

    @BindView(R.id.item_disclose_name)
    InputItemView discloseNameItem;

    @BindView(R.id.item_disclose_date)
    InputItemView discloseDateItem;

    @BindView(R.id.item_disclose_part)
    InputItemView disclosePartItem;

    @BindView(R.id.item_disclose_person)
    InputItemView disclosePersonItem;

    @BindView(R.id.item_safe_person)
    InputItemView safePersonItem;

    @BindView(R.id.item_team_head)
    InputItemView teamHeadItem;

    @BindView(R.id.item_team)
    InputItemView teamItem;

    @BindView(R.id.item_whether_report)
    InputItemView whetherReportItem;

    @BindView(R.id.et_content)
    EditText contentEdit;

    @BindView(R.id.rl_images)
    RecyclerView imagesRV;

    private SelectImageAdapter imageAdapter;
    private ArrayList<String> imagePaths = new ArrayList<>();
    private List<FileEntity> fileEntities = new ArrayList<>();
    private List<SecurityEduTechPerson> selectLabors = new ArrayList<>();
    private List<SecurityOfficerInfo> securityOfficerInfoList;

    private ImageCompressorPresenter compressorPresenter;
    private SafeEducationAddPresenter safeEducationAddPresenter;
    private SecurityOfficerPresenter securityOfficerPresenter;
    private UploadPresenter uploadPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safe_technology_disclose_add);
    }

    @Override
    protected void initUI() {
        titleText.setText("新增安全技术交底");
        discloseDateItem.setTag(Calendar.getInstance());
        discloseDateItem.setContent(DateUtils.calendar2str(Calendar.getInstance(), DateUtils.PATTERN_DATE));
        whetherReportItem.setContent("否");
        teamHeadItem.setEnable(false);

        imagesRV.setNestedScrollingEnabled(false);
        imagesRV.setLayoutManager(new GridLayoutManager(this, 4));
        imagesRV.setAdapter(imageAdapter = new SelectImageAdapter(this));
    }

    @Override
    protected void initData() {
        compressorPresenter = new ImageCompressorPresenter(this, CommonModel.newInstance());
        uploadPresenter = new UploadPresenter(this, CommonModel.newInstance());
        safeEducationAddPresenter = new SafeEducationAddPresenter(this, SafeModel.newInstance());
        securityOfficerPresenter = new SecurityOfficerPresenter(this, CommonModel.newInstance());
        addPresenter(securityOfficerPresenter);
        addPresenter(uploadPresenter);
        addPresenter(compressorPresenter);
        addPresenter(safeEducationAddPresenter);
        imageAdapter.setSelectImageCallback(new SelectImageCallback() {
            @Override
            public void viewImage(int position) {
                PhotoPickerHelper.startPreview(SafeTechnologyDiscloseAddActivity.this, REQUEST_IMAGE_PREVIEW_CODE, imagePaths, position);
            }

            @Override
            public void addImage() {
                AndPermission.with(SafeTechnologyDiscloseAddActivity.this)
                        .runtime()
                        .permission(PermissionConstant.PERMISSIONS_OF_IMAGE)
                        .onGranted(new Action<List<String>>() {
                            @Override
                            public void onAction(List<String> permissions) {
                                PhotoPickerHelper.startPicker(SafeTechnologyDiscloseAddActivity.this, REQUEST_IMAGE_PICK_CODE, imagePaths);
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

    @OnClick({R.id.tv_back, R.id.item_disclose_date, R.id.item_whether_report, R.id.tv_submit, R.id.item_team, R.id.item_safe_person})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.item_disclose_date:
                showTimeDialog(discloseDateItem);
                break;
            case R.id.item_whether_report:
                PickerDialogHelper.showPickerOption(SafeTechnologyDiscloseAddActivity.this, Arrays.asList("是", "否"), 0, false, new OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                        whetherReportItem.setTag(options1 == 0 ? "Y" : "N");
                        whetherReportItem.setContent(options1 == 0 ? "是" : "否");
                    }
                });
                break;
            case R.id.item_team:
                SelectTeamActivity.startActivity(this, REQUEST_CODE_TRAINEE);

                break;
            case R.id.item_safe_person:
                if (securityOfficerInfoList != null) {
                    showOfficerDialog(securityOfficerInfoList);
                } else {
                    securityOfficerPresenter.getSecurityOfficer(UserManager.getInstance().getProjectId());
                }
                break;
            case R.id.tv_submit:
                if (checkData()) {
                    compressorPresenter.compressorImages(imagePaths);
                }
                break;
            default:
                break;
        }
    }

    private boolean checkData() {
        if (TextUtils.isEmpty(discloseNameItem.getContent())) {
            showError("请填写交底名称");
            return false;
        }
        if (TextUtils.isEmpty(disclosePartItem.getContent())) {
            showError("请填写交底部位");
            return false;
        }
        if (TextUtils.isEmpty(disclosePersonItem.getContent())) {
            showError("请填写交底人");
            return false;
        }
        if (TextUtils.isEmpty(safePersonItem.getContent())) {
            showError("请填写专职安全员");
            return false;
        }
        if (TextUtils.isEmpty(teamItem.getContent())) {
            showError("请选择交底班组");
            return false;
        }
        if (TextUtils.isEmpty(contentEdit.getText().toString())) {
            showError("请填写交底内容");
            return false;
        }
        if (imagePaths.size() == 0) {
            showError("请选择图片");
            return false;
        }
        return true;
    }

    private void showTimeDialog(final InputItemView inputItemView) {
        if (isFastDoubleClick()) {
            return;
        }
        final Calendar calendar = (Calendar) inputItemView.getTag();
        PickerDialogHelper.showTimePicker(this, calendar, true, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                calendar.setTime(date);
                inputItemView.setTag(calendar);
                inputItemView.setContent(DateUtils.date2str(date, DateUtils.PATTERN_DATE));
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_IMAGE_PICK_CODE:
                    imagePaths = data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT);
                    imageAdapter.loadData(imagePaths);
                    break;

                case REQUEST_IMAGE_PREVIEW_CODE:
                    imagePaths = data.getStringArrayListExtra(PhotoPreviewActivity.EXTRA_RESULT);
                    imageAdapter.loadData(imagePaths);
                    break;
                case REQUEST_CODE_TRAINEE:
                    selectLabors = data.getParcelableArrayListExtra("data");
                    if (selectLabors.size() > 0) {
                        StringBuilder stringBuilder = new StringBuilder();
                        StringBuilder stringBuilder1 = new StringBuilder();
                        for (SecurityEduTechPerson person : selectLabors) {
                            stringBuilder.append(person.teamName).append(",");
                            stringBuilder1.append(person.teamPerson).append(",");
                        }
                        teamItem.setContent(stringBuilder.substring(0, stringBuilder.length() - 1));
                        teamHeadItem.setContent(stringBuilder1.substring(0, stringBuilder1.length() - 1));
                    } else {
                        teamItem.setContent("");
                        teamHeadItem.setContent("");
                    }
                    break;
                default:
                    break;
            }
        }
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
            entity.projId = UserManager.getInstance().getProjectId();
            entity.projCode = UserManager.getInstance().getProjectCode();
            fileEntities.add(entity);
        }

        safeEducationAddPresenter.addSafeTechnology(discloseNameItem.getContent(), discloseDateItem.getContent(), disclosePartItem.getContent(),
                disclosePersonItem.getContent(), safePersonItem.getContent(), teamHeadItem.getContent(), selectLabors, whetherReportItem.getContent().equals("是") ? 1 : 0, contentEdit.getText().toString(),
                fileEntities, UserManager.getInstance().getProjectId(), UserManager.getInstance().getProjectCode(), UserManager.getInstance().getUserId(),
                UserManager.getInstance().getUserName());
    }

    @Override
    public void showAddSafeTechnology() {
        ToastUtils.showShort("提交成功");
        EventBus.getDefault().post(new SafeEducationRefreshEvent(SafeEducationRefreshEvent.EDUCATION));
        finish();
    }

    @Override
    public void showSecurityOfficer(List<SecurityOfficerInfo> info) {
        this.securityOfficerInfoList = info;
        showOfficerDialog(info);
    }

    private void showOfficerDialog(final List<SecurityOfficerInfo> list) {
        List<String> strings = new ArrayList<>();
        for (SecurityOfficerInfo securityOfficerInfo : list) {
            strings.add(securityOfficerInfo.name);
        }
        PickerDialogHelper.showPickerOption(this, strings, 0, true, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                SecurityOfficerInfo info = list.get(options1);
                safePersonItem.setContent(info.name);
            }
        });
    }

    @Override
    public void showAddSafeEduResult() { //ignore
    }

}
