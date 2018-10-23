package com.ljkj.qxn.wisdomsitepro.ui.quality;

import android.content.Context;
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
import com.ljkj.qxn.wisdomsitepro.contract.quality.CompressionAndImpermeabilityAddContract;
import com.ljkj.qxn.wisdomsitepro.data.FileEntity;
import com.ljkj.qxn.wisdomsitepro.data.entity.ConcreteCompressiveInfo;
import com.ljkj.qxn.wisdomsitepro.data.event.InspectionEvent;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.model.CommonModel;
import com.ljkj.qxn.wisdomsitepro.model.ConcreteModel;
import com.ljkj.qxn.wisdomsitepro.presenter.common.UploadPresenter;
import com.ljkj.qxn.wisdomsitepro.presenter.quality.CompressionAndImpermeabilityAddPresenter;
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
 * 混凝土抗压强度检验/抗渗-新增
 * Created by lxx on 2018/3/13.
 */

public class CompressionAndImpermeabilityAddActivity extends BaseActivity
        implements CompressionAndImpermeabilityAddContract.View, UploadContract.View {

    private static final String EXTRA_KEY_TYPE = "extra_key_type";
    public static final int TYPE_COMPRESSION = 1;
    public static final int TYPE_IMPERMEABILITY = 2;
    private static final int REQUEST_CODE_FILE = 101;

    @BindView(R.id.tv_title)
    TextView titleText;

    @BindView(R.id.item_check_number)
    InputItemView checkNumberItem;

    @BindView(R.id.item_entrustment_unit)
    InputItemView entrustmentUnitItem;

    @BindView(R.id.item_witness_unit)
    InputItemView witnessUnitItem;

    @BindView(R.id.item_witness_person)
    InputItemView witnessPersonItem;

    @BindView(R.id.item_use_part)
    InputItemView usePartItem;

    @BindView(R.id.item_sample_name)
    InputItemView sampleNameItem;

    @BindView(R.id.item_strength_grade)
    InputItemView strengthGradeItem;

    @BindView(R.id.item_Impermeability_grade)
    InputItemView impermeabilityGradeItem;

    @BindView(R.id.item_maintenance_mode)
    InputItemView maintenanceModeItem;

    @BindView(R.id.item_age)
    InputItemView ageItem;

    @BindView(R.id.item_forming_date)
    InputItemView formingDateItem;

    @BindView(R.id.item_check_date)
    InputItemView checkDateItem;


    @BindView(R.id.item_check_result)
    InputItemView checkResultItem;

    @BindView(R.id.item_check_unit)
    InputItemView checkUnitItem;

    @BindView(R.id.item_appearance_quality)
    InputItemView appearanceQuality;

    @BindView(R.id.ll_content)
    LinearLayout contentLayout;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.scrollView)
    ScrollView scrollView;

    private CompressionAndImpermeabilityAddPresenter compressionAndImpermeabilityAddPresenter;
    private UploadPresenter filesPresenter;
    private int type;
    private ShowAttachmentAdapter attachmentAdapter;
    private String currentFilePath;
    private boolean isUploadSuccess = false;
    private List<String> filePaths = new ArrayList<>();
    private List<FileEntity> entities;

    public static void startActivity(Context context, int type) {
        Intent intent = new Intent(context, CompressionAndImpermeabilityAddActivity.class);
        intent.putExtra(EXTRA_KEY_TYPE, type);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compressino_test_add);
    }

    @Override
    protected void initUI() {
        type = getIntent().getIntExtra(EXTRA_KEY_TYPE, TYPE_COMPRESSION);
        titleText.setText(type == TYPE_COMPRESSION ? "新增抗压强度检验" : "新增抗渗检验");
        impermeabilityGradeItem.setVisibility(type == TYPE_COMPRESSION ? View.GONE : View.VISIBLE);
        formingDateItem.setTag(Calendar.getInstance());
        checkDateItem.setTag(Calendar.getInstance());
        ageItem.setTag(Calendar.getInstance());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(attachmentAdapter = new ShowAttachmentAdapter(this));
    }

    @Override
    protected void initData() {
        compressionAndImpermeabilityAddPresenter = new CompressionAndImpermeabilityAddPresenter(this, ConcreteModel.newInstance());
        filesPresenter = new UploadPresenter(this, CommonModel.newInstance());
        addPresenter(compressionAndImpermeabilityAddPresenter);
        addPresenter(filesPresenter);
    }

    @OnClick({R.id.tv_back, R.id.btn_submit, R.id.tv_upload, R.id.item_strength_grade, R.id.item_maintenance_mode,
            R.id.item_forming_date, R.id.item_check_date, R.id.item_appearance_quality,
            R.id.item_Impermeability_grade})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.item_strength_grade:
                String[] items = getResources().getStringArray(R.array.strengthGrade);
                showOptionDialog(Arrays.asList(items), strengthGradeItem);
                break;
            case R.id.item_Impermeability_grade:
                String[] array = getResources().getStringArray(R.array.impermeabilityGrade);
                showOptionDialog(Arrays.asList(array), impermeabilityGradeItem);
                break;
            case R.id.item_maintenance_mode:
                String[] items1 = getResources().getStringArray(R.array.maintenanceMode);
                showOptionDialog(Arrays.asList(items1), maintenanceModeItem);
                break;
            case R.id.item_forming_date:
                showTimeDialog(formingDateItem);
                break;
            case R.id.item_check_date:
                showTimeDialog(checkDateItem);
                break;
            case R.id.item_appearance_quality:
                String[] items2 = getResources().getStringArray(R.array.appearanceQuality);
                showOptionDialog(Arrays.asList(items2), appearanceQuality);
                break;
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
            case R.id.tv_back:
                finish();
                break;
            default:
                break;
        }

    }

    private void uploadFile() {
        List<File> files = new ArrayList<>();
        for (String path : filePaths) {
            files.add(new File(path));
        }
        filesPresenter.upload(UserManager.getInstance().getProjectId(), files);
    }

    private boolean isSelectFile() {

        if (filePaths.isEmpty()) {
            showError("请选择验收附件");
            return false;
        }
        return true;
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

    protected void submit() {
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

        ConcreteCompressiveInfo detail = new ConcreteCompressiveInfo();
        detail.setCheckCode(checkNumberItem.getContent());
        detail.setEntrustUnit(entrustmentUnitItem.getContent());
        detail.setWitnessUnit(witnessUnitItem.getContent());
        detail.setWitness(witnessPersonItem.getContent());
        detail.setUsePart(usePartItem.getContent());
        detail.setSampleName(sampleNameItem.getContent());
        detail.setDesignStrengthGrade(strengthGradeItem.getContent());
        detail.setDesignImperviousGrade(impermeabilityGradeItem.getContent());
        detail.setMaintenanceMode(maintenanceModeItem.getContent());
        detail.setNearTerm(ageItem.getContent());
        detail.setFormingDate(formingDateItem.getContent());
        detail.setCheckDate(checkDateItem.getContent());
        detail.setCheckResult(checkResultItem.getContent());
        detail.setCheckUnit(checkUnitItem.getContent());
        detail.setAppearanceQuality(appearanceQuality.getContent());
        detail.setProjId(UserManager.getInstance().getProjectId());
        detail.setFile(jsonArray);

        if (type == TYPE_COMPRESSION) {
            compressionAndImpermeabilityAddPresenter.addCompression(detail);
        } else if (type == TYPE_IMPERMEABILITY) {
            compressionAndImpermeabilityAddPresenter.addImpermeability(detail);
        }
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

    private boolean checkData() {
        for (int count = contentLayout.getChildCount(), i = 0; i < count; i++) {
            View childView = contentLayout.getChildAt(i);
            if (childView instanceof InputItemView && childView.getVisibility() != View.GONE) {
                InputItemView itemView = (InputItemView) childView;
                if (TextUtils.isEmpty(itemView.getContent())) {
                    showError("请输入" + itemView.getTitle());
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void addSuccess(ConcreteCompressiveInfo detail) {
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
