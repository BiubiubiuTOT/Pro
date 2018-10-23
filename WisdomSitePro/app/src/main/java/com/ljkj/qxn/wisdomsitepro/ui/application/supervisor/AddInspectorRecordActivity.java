package com.ljkj.qxn.wisdomsitepro.ui.application.supervisor;

import android.app.Activity;
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
import com.ljkj.qxn.wisdomsitepro.Utils.widget.InputItemView;
import com.ljkj.qxn.wisdomsitepro.contract.common.ImageCompressorContract;
import com.ljkj.qxn.wisdomsitepro.contract.common.UploadContract;
import com.ljkj.qxn.wisdomsitepro.contract.supervisor.InspectorRecordContract;
import com.ljkj.qxn.wisdomsitepro.data.FileEntity;
import com.ljkj.qxn.wisdomsitepro.data.PermissionConstant;
import com.ljkj.qxn.wisdomsitepro.data.entity.InspectorRecorderManageInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.PageInfo;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.model.CommonModel;
import com.ljkj.qxn.wisdomsitepro.model.SupervisorModel;
import com.ljkj.qxn.wisdomsitepro.presenter.common.ImageCompressorPresenter;
import com.ljkj.qxn.wisdomsitepro.presenter.common.UploadPresenter;
import com.ljkj.qxn.wisdomsitepro.presenter.supervisor.InspectorRecordPresenter;
import com.ljkj.qxn.wisdomsitepro.ui.common.SelectImageCallback;
import com.ljkj.qxn.wisdomsitepro.ui.common.adapter.SelectImageAdapter;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;

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

/**
 * 类描述：新增巡视记录
 * 创建人：lxx
 * 创建时间：2018/9/1
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class AddInspectorRecordActivity extends BaseActivity implements InspectorRecordContract.View, UploadContract.View,
        ImageCompressorContract.View {
    private static final int PREVIEW_CODE = 0x7;
    private static final int SELECT_CODE = 0x8;

    @BindView(R.id.tv_title)
    protected TextView titleText;

    @BindView(R.id.item_record_name)
    protected InputItemView recordNameItem; //记录名称

    @BindView(R.id.item_inspector_time)
    protected InputItemView inspectorTimeItem; //巡视时间

    @BindView(R.id.item_unit)
    protected InputItemView unitItem; // 监理单位

    @BindView(R.id.et_inspector_range)
    protected EditText inspectorRangeEdit; //巡视范围、部位、工序

    @BindView(R.id.et_construct_project)
    protected EditText constructProjectEdit; //施工项目、人员到位、工艺合规性

    @BindView(R.id.et_record_data)
    protected EditText recordDataEdit; //主要巡视记录数据

    @BindView(R.id.rl_image)
    protected RecyclerView imageRV;

    @BindView(R.id.tv_submit)
    protected TextView submitText;

    private Calendar startCalendar, endCalendar;
    private SelectImageAdapter imageAdapter;
    private ArrayList<String> imagePaths = new ArrayList<>();
    private InspectorRecordPresenter inspectorRecordPresenter;
    private UploadPresenter uploadPresenter;
    private ImageCompressorPresenter imageCompressorPresenter;
    public String id;
    private InspectorRecorderManageInfo info = new InspectorRecorderManageInfo();
    private String range;
    private String name;
    private String project;
    private String data;
    private String unit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_inspector_record);
    }

    @Override
    protected void initUI() {
        titleText.setText("新增巡视记录");
        imageRV.setNestedScrollingEnabled(false);
        imageRV.setLayoutManager(new GridLayoutManager(this, 4));
        imageRV.setAdapter(imageAdapter = new SelectImageAdapter(this));

        startCalendar = Calendar.getInstance();
        startCalendar.add(Calendar.YEAR, -10);
        inspectorTimeItem.setTag(Calendar.getInstance());
        endCalendar = Calendar.getInstance();
        inspectorTimeItem.setContent(DateUtils.calendar2str(Calendar.getInstance(), DateUtils.PATTERN_DATE));
    }

    @Override
    protected void initData() {
        imageAdapter.setSelectImageCallback(new SelectImageCallback() {
            @Override
            public void viewImage(int position) {
                PhotoPickerHelper.startPreview(AddInspectorRecordActivity.this, PREVIEW_CODE, imagePaths, position, true);
            }

            @Override
            public void addImage() {
                AndPermission.with(AddInspectorRecordActivity.this)
                        .runtime()
                        .permission(PermissionConstant.PERMISSIONS_OF_IMAGE)
                        .onGranted(new Action<List<String>>() {
                            @Override
                            public void onAction(List<String> permissions) {
                                PhotoPickerHelper.startPicker(AddInspectorRecordActivity.this, SELECT_CODE, imagePaths, 9, true, true);
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

        inspectorRecordPresenter = new InspectorRecordPresenter(this, SupervisorModel.newInstance());
        addPresenter(inspectorRecordPresenter);
        uploadPresenter = new UploadPresenter(this, CommonModel.newInstance());
        addPresenter(uploadPresenter);
        imageCompressorPresenter = new ImageCompressorPresenter(this, CommonModel.newInstance());
        addPresenter(imageCompressorPresenter);

        id = getIntent().getStringExtra("id");
        if (id != null)
            inspectorRecordPresenter.getInspectorRecordDetail(id);
    }

    @OnClick({R.id.tv_back, R.id.tv_submit, R.id.item_inspector_time})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.item_inspector_time:
                showTimeDialog(inspectorTimeItem);
                break;
            case R.id.tv_submit:
                if (checkData()) {
                    if (!imagePaths.isEmpty()) {
                        uploadFile();
                    } else {
                        submit();
                    }
                }
                break;
            default:
                break;
        }
    }

    private void uploadFile() {
        imageCompressorPresenter.compressorImages(imagePaths);
    }

    private void submit() {
        info.setName(name);
        info.setPatrolScope(range);
        info.setProjWorkerTech(project);
        info.setRecordData(data);
        info.setPatrolTime(inspectorTimeItem.getContent());
        info.setSupervisionUnit(unit);
        info.setProjId(UserManager.getInstance().getProjectId());
        info.setCreateUserName(UserManager.getInstance().getUserName());

        inspectorRecordPresenter.addInspectorRecord(info);
    }

    private boolean checkData() {
        name = recordNameItem.getContent();
        if (TextUtils.isEmpty(name)) {
            showError("请输入" + recordNameItem.getTitle());
            return false;
        }

        range = inspectorRangeEdit.getText().toString();
        if (TextUtils.isEmpty(range)) {
            showError("请输入巡视范围、部位、工序");
            return false;
        }

        project = constructProjectEdit.getText().toString();
        if (TextUtils.isEmpty(project)) {
            showError("请输入施工项目、人员到位、工艺合规性");
            return false;
        }

        data = recordDataEdit.getText().toString();
        if (TextUtils.isEmpty(data)) {
            showError("请输入主要巡视记录数据");
            return false;
        }

        unit = unitItem.getContent();
        if (TextUtils.isEmpty(unit)) {
            showError("请输入监理单位");
            return false;
        }

        return true;
    }

    private void showTimeDialog(final InputItemView inputItemView) {
        if (isFastDoubleClick()) {
            return;
        }
        final Calendar calendar = (Calendar) inputItemView.getTag();
        PickerDialogHelper.showTimePicker(this, calendar, startCalendar, endCalendar, true, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                calendar.setTime(date);
                inputItemView.setTag(calendar);
                inputItemView.setContent(DateUtils.date2str(date, DateUtils.PATTERN_DATE));
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case SELECT_CODE:
                    imagePaths = data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT);
                    imageAdapter.loadData(imagePaths);
                    break;

                case PREVIEW_CODE:
                    imagePaths = data.getStringArrayListExtra(PhotoPreviewActivity.EXTRA_RESULT);
                    imageAdapter.loadData(imagePaths);
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void showInspectorRecordList(PageInfo<InspectorRecorderManageInfo> datas) {

    }

    @Override
    public void dealDeleteRecordResult() {

    }

    @Override
    public void showInspectorRecordDetail(InspectorRecorderManageInfo info) {

    }

    @Override
    public void dealAddRecordResult() {
        showError("添加记录成功");
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void showUploadInfo(List<FileEntity> entityList) {
        JSONArray jsonArray = new JSONArray();
        if (entityList != null) {
            try {
                for (FileEntity fileEntity : entityList) {
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
    public void showCompressFilePaths(List<String> data) {
        List<File> files = new ArrayList<>();
        for (String path : data) {
            files.add(new File(path));
        }
        uploadPresenter.upload(UserManager.getInstance().getProjectId(), files);
    }
}
