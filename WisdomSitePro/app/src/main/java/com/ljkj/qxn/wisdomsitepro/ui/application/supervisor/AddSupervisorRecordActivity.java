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
import com.ljkj.qxn.wisdomsitepro.contract.supervisor.SupervisorRecordContract;
import com.ljkj.qxn.wisdomsitepro.data.FileEntity;
import com.ljkj.qxn.wisdomsitepro.data.PermissionConstant;
import com.ljkj.qxn.wisdomsitepro.data.entity.PageInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.SupervisorRecordManageInfo;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.model.CommonModel;
import com.ljkj.qxn.wisdomsitepro.model.SupervisorModel;
import com.ljkj.qxn.wisdomsitepro.presenter.common.ImageCompressorPresenter;
import com.ljkj.qxn.wisdomsitepro.presenter.common.UploadPresenter;
import com.ljkj.qxn.wisdomsitepro.presenter.supervisor.SupervisorRecordPresenter;
import com.ljkj.qxn.wisdomsitepro.ui.common.SelectImageCallback;
import com.ljkj.qxn.wisdomsitepro.ui.common.adapter.SelectImageAdapter;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;

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
import cdsp.android.ui.base.BaseActivity;
import cdsp.android.util.DateUtils;

/**
 * 类描述：新增监理记录
 * 创建人：lxx
 * 创建时间：2018/9/1
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class AddSupervisorRecordActivity extends BaseActivity implements
        SupervisorRecordContract.View, ImageCompressorContract.View, UploadContract.View {
    private static final int PREVIEW_CODE = 0x9;
    private static final int SELECT_CODE = 0xa;

    @BindView(R.id.tv_title)
    protected TextView titleText;

    @BindView(R.id.item_record_name)
    protected InputItemView recordNameItem; //记录名称

    @BindView(R.id.item_date)
    protected InputItemView dateItem; //巡视时间

    @BindView(R.id.item_unit)
    protected InputItemView unitItem; //监理单位

    @BindView(R.id.item_supervisor_type)
    protected InputItemView supervisorTypeItem; //监理类型

    @BindView(R.id.et_info)
    protected EditText contentEdit; //监理记录内容

    @BindView(R.id.rl_image)
    protected RecyclerView imageRV;

    @BindView(R.id.tv_submit)
    protected TextView submitText;

    private Calendar startCalendar, endCalendar;
    private SelectImageAdapter imageAdapter;
    private ArrayList<String> imagePaths = new ArrayList<>();
    private SupervisorRecordPresenter supervisorRecordPresenter;
    private ImageCompressorPresenter imageCompressorPresenter;
    private UploadPresenter uploadPresenter;
    public String id;
    private SupervisorRecordManageInfo info = new SupervisorRecordManageInfo();
    private String content;
    private String type;
    private String name;
    private String unit;
    private int typeIndex;

    public final List<String> types = Arrays.asList("质量", "安全", "进度", "检查", "会议", "其他");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_supervisor_record);
    }

    @Override
    protected void initUI() {
        titleText.setText("新增监理记录");
        imageRV.setNestedScrollingEnabled(false);
        imageRV.setLayoutManager(new GridLayoutManager(this, 4));
        imageRV.setAdapter(imageAdapter = new SelectImageAdapter(this));

        startCalendar = Calendar.getInstance();
        startCalendar.add(Calendar.YEAR, -10);
        dateItem.setTag(Calendar.getInstance());
        endCalendar = Calendar.getInstance();
        dateItem.setContent(DateUtils.calendar2str(Calendar.getInstance(), DateUtils.PATTERN_DATE_2));
    }

    @Override
    protected void initData() {
        imageAdapter.setSelectImageCallback(new SelectImageCallback() {
            @Override
            public void viewImage(int position) {
                PhotoPickerHelper.startPreview(AddSupervisorRecordActivity.this, PREVIEW_CODE, imagePaths, position, true);
            }

            @Override
            public void addImage() {
                AndPermission.with(AddSupervisorRecordActivity.this)
                        .runtime()
                        .permission(PermissionConstant.PERMISSIONS_OF_IMAGE)
                        .onGranted(new Action<List<String>>() {
                            @Override
                            public void onAction(List<String> permissions) {
                                PhotoPickerHelper.startPicker(AddSupervisorRecordActivity.this, SELECT_CODE, imagePaths, 9, true, true);
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

        imageCompressorPresenter = new ImageCompressorPresenter(this, CommonModel.newInstance());
        uploadPresenter = new UploadPresenter(this, CommonModel.newInstance());
        supervisorRecordPresenter = new SupervisorRecordPresenter(this, SupervisorModel.newInstance());

        addPresenter(uploadPresenter);
        addPresenter(imageCompressorPresenter);
        addPresenter(supervisorRecordPresenter);

        id = getIntent().getStringExtra("id");
        if (id != null)
            supervisorRecordPresenter.getSupervisorRecordDetail(id);
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

    @OnClick({R.id.tv_back, R.id.tv_submit, R.id.item_date, R.id.item_supervisor_type})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.item_date:
                showTimeDialog(dateItem);
                break;
            case R.id.item_supervisor_type:
                PickerDialogHelper.showPickerOption(this, types, 0, true, new OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                        typeIndex = options1 + 1;
                        supervisorTypeItem.setContent(types.get(options1));
                    }
                });
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
        info.setRecordInfo(content);
        info.setRecordTime(dateItem.getContent());
        info.setSupervisionUnit(unit);
        info.setProjId(UserManager.getInstance().getProjectId());
        info.setSupervisionType(typeIndex);
        info.setCreateUserName(UserManager.getInstance().getUserName());

        supervisorRecordPresenter.addSupervisorRecord(info);
    }

    private boolean checkData() {
        name = recordNameItem.getContent();
        if (TextUtils.isEmpty(name)) {
            showError("请输入" + recordNameItem.getTitle());
            return false;
        }

        type = supervisorTypeItem.getContent();
        if (TextUtils.isEmpty(type)) {
            showError("请选择" + supervisorTypeItem.getTitle());
            return false;
        }

        content = contentEdit.getText().toString();
        if (TextUtils.isEmpty(content)) {
            showError("请输入监理记录内容");
            return false;
        }

        unit = unitItem.getContent();
        if (TextUtils.isEmpty(unit)) {
            showError("请输入" + unitItem.getTitle());
            return false;
        }

        return true;
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
    public void showCompressFilePaths(List<String> data) {
        List<File> files = new ArrayList<>();
        for (String path : data) {
            files.add(new File(path));
        }
        uploadPresenter.upload(UserManager.getInstance().getProjectId(), files);
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
    public void showSupervisorRecordList(PageInfo<SupervisorRecordManageInfo> datas) {

    }

    @Override
    public void dealDeleteRecordResult() {

    }

    @Override
    public void showSupervisorRecordDetail(SupervisorRecordManageInfo info) {

    }

    @Override
    public void dealAddRecordResult() {
        showError("添加记录成功");
        setResult(RESULT_OK);
        finish();
    }
}
