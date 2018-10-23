package com.ljkj.qxn.wisdomsitepro.ui.quality;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.Utils.PickerDialogHelper;
import com.ljkj.qxn.wisdomsitepro.Utils.widget.InputItemView;
import com.ljkj.qxn.wisdomsitepro.WApplication;
import com.ljkj.qxn.wisdomsitepro.contract.common.UploadContract;
import com.ljkj.qxn.wisdomsitepro.contract.quality.SiteAcceptanceAddContract;
import com.ljkj.qxn.wisdomsitepro.data.FileEntity;
import com.ljkj.qxn.wisdomsitepro.data.entity.ConcreteEntranceAcceptanceInfo;
import com.ljkj.qxn.wisdomsitepro.data.event.InspectionEvent;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.model.CommonModel;
import com.ljkj.qxn.wisdomsitepro.model.ConcreteModel;
import com.ljkj.qxn.wisdomsitepro.presenter.common.UploadPresenter;
import com.ljkj.qxn.wisdomsitepro.presenter.quality.SiteAcceptanceAddPresenter;
import com.ljkj.qxn.wisdomsitepro.ui.quality.adapter.ShowAttachmentAdapter;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.logging.Logger;
import cdsp.android.ui.base.BaseActivity;
import cdsp.android.util.DateUtils;
import cdsp.android.util.FileUtils;
import cdsp.android.util.ToastUtils;

/**
 * 商品混凝土进场验收-新增
 * Created by lxx on 2018/3/13.
 */
public class SiteAcceptanceAddActivity extends BaseActivity implements SiteAcceptanceAddContract.View, UploadContract.View {

    private static final int REQUEST_CODE_FILE = 100;

    @BindView(R.id.tv_title)
    TextView titleText;

    @BindView(R.id.item_pouring_part)
    InputItemView pouringPartItem;

    @BindView(R.id.item_concrete_supplier)
    InputItemView concreteSupplierItem;

    @BindView(R.id.item_site_builders)
    InputItemView siteBuildersItem;

    @BindView(R.id.item_site_supervision_personnel)
    InputItemView siteSupervisionPersonnelItem;

    @BindView(R.id.item_labor_operator)
    InputItemView laborOperatorItem;

    @BindView(R.id.item_concrete_type)
    InputItemView concreteTypeItem;

    @BindView(R.id.item_strength_grade)
    InputItemView strengthGradeItem;

    @BindView(R.id.item_pouring_time)
    InputItemView pouringTimeItem;

    @BindView(R.id.item_finish_time)
    InputItemView finishTimeItem;

    @BindView(R.id.item_number)
    InputItemView numberItem;

    @BindView(R.id.item_weather)
    InputItemView weatherItem;

    @BindView(R.id.ll_content)
    LinearLayout contentLayout;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.scrollView)
    ScrollView scrollView;

    private SiteAcceptanceAddPresenter acceptanceAddPresenter;
    private UploadPresenter uploadFilesPresenter;
    private ShowAttachmentAdapter attachmentAdapter;
    private String currentFilePath;
    private boolean isUploadSuccess = false;
    private List<String> filePaths = new ArrayList<>();
    private List<FileEntity> entities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site_acceptance_add);
    }

    @Override
    protected void initUI() {
        titleText.setText("新增混凝土进场验收");
        pouringTimeItem.setTag(Calendar.getInstance());
        finishTimeItem.setTag(Calendar.getInstance());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(attachmentAdapter = new ShowAttachmentAdapter(this));
    }

    @Override
    protected void initData() {
        acceptanceAddPresenter = new SiteAcceptanceAddPresenter(this, ConcreteModel.newInstance());
        uploadFilesPresenter = new UploadPresenter(this, CommonModel.newInstance());
        addPresenter(acceptanceAddPresenter);
        addPresenter(uploadFilesPresenter);
    }

    @OnClick({R.id.tv_back, R.id.btn_submit, R.id.tv_upload, R.id.item_concrete_type, R.id.item_strength_grade, R.id.item_pouring_time, R.id.item_finish_time})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_submit:
                if (!checkData()) {//校验
                    return;
                }
                if (!isSelectFile()) {//附件
                    return;
                }

                uploadFile();
                break;
            case R.id.tv_upload:
                chooseAttachment();
                break;
            case R.id.item_concrete_type:
                String[] items = getResources().getStringArray(R.array.concreteType);
                showOptionDialog(Arrays.asList(items), concreteTypeItem);
                break;
            case R.id.item_strength_grade:
                String[] items1 = getResources().getStringArray(R.array.strengthGrade);
                showOptionDialog(Arrays.asList(items1), strengthGradeItem);
                break;
            case R.id.item_pouring_time:
                showTimeDialog(pouringTimeItem);
                break;
            case R.id.item_finish_time:
                showTimeDialog(finishTimeItem);
                break;
            case R.id.tv_back:
                onBackPressed();
                break;
            default:
                break;
        }
    }

    private void chooseAttachment() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent, REQUEST_CODE_FILE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_FILE) {
            if (data.getData() != null) {
                Uri uri = data.getData();
                currentFilePath = FileUtils.getFileAbsolutePath(this, uri);
                filePaths.add(currentFilePath);
                Logger.d("选择的文件路径=" + uri.getPath());
                String name = currentFilePath.substring(currentFilePath.lastIndexOf("/") + 1);

                attachmentAdapter.insertLast(new FileEntity(name, currentFilePath));
                WApplication.getMainHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        scrollView.fullScroll(ScrollView.FOCUS_DOWN);
                    }
                });
            } else {
                showError("选取附件失败");
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void uploadFile() {
        List<File> files = new ArrayList<>();
        for (String path : filePaths) {
            files.add(new File(path));
        }
        uploadFilesPresenter.upload(UserManager.getInstance().getProjectId(), files);
    }

    private boolean isSelectFile() {

        if (filePaths.isEmpty()) {
            showError("请选择验收附件");
            return false;
        }
        return true;
    }

    private void submit() {
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

        ConcreteEntranceAcceptanceInfo detail = new ConcreteEntranceAcceptanceInfo();
        detail.setPouringPart(pouringPartItem.getContent());
        detail.setConcreteSupplier(concreteSupplierItem.getContent());
        detail.setSceneConstructionPersonnel(siteBuildersItem.getContent());
        detail.setSupervisorUnitPersonnel(siteSupervisionPersonnelItem.getContent());
        detail.setSceneOperationPersonnel(laborOperatorItem.getContent());
        detail.setConcreteType(concreteTypeItem.getContent());
        detail.setStrengthGrade(strengthGradeItem.getContent());
        detail.setPouringStartDate(pouringTimeItem.getContent());
        detail.setPouringEndDate(finishTimeItem.getContent());
        detail.setCoordinationCode(numberItem.getContent());
        detail.setWeatherCondition(weatherItem.getContent());
        detail.setProjId(UserManager.getInstance().getProjectId());
        detail.setFile(jsonArray);

        acceptanceAddPresenter.addSiteAcceptance(detail);
    }

    private boolean checkData() {
        for (int count = contentLayout.getChildCount(), i = 0; i < count; i++) {
            View childView = contentLayout.getChildAt(i);
            if (childView instanceof InputItemView) {
                InputItemView itemView = (InputItemView) childView;
                if (TextUtils.isEmpty(itemView.getContent())) {
                    showError("请输入" + itemView.getTitle());
                    return false;
                }
            }
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

    private void showOptionDialog(final List<String> items, final InputItemView inputItemView) {
        if (isFastDoubleClick()) {
            return;
        }
        int select = inputItemView.getTag() != null ? (int) inputItemView.getTag() : items.size() / 2;
        PickerDialogHelper.showPickerOption(this, items, select, true, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                inputItemView.setTag(options1);
                inputItemView.setContent(items.get(options1));
            }
        });
    }

    @Override
    public void addSiteAcceptanceSuccess(ConcreteEntranceAcceptanceInfo detail) {
        ToastUtils.showShort("提交成功");
        EventBus.getDefault().post(new InspectionEvent());
        finish();
    }


    @Override
    public void showUploadInfo(List<FileEntity> entities) {
        this.entities = entities;

        submit();
    }
}
