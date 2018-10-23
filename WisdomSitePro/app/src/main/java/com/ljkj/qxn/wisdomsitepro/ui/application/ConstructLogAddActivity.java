package com.ljkj.qxn.wisdomsitepro.ui.application;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.foamtrace.photopicker.PhotoPickerActivity;
import com.foamtrace.photopicker.PhotoPreviewActivity;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.Utils.PhotoPickerHelper;
import com.ljkj.qxn.wisdomsitepro.Utils.PickerDialogHelper;
import com.ljkj.qxn.wisdomsitepro.Utils.widget.InputItemView;
import com.ljkj.qxn.wisdomsitepro.contract.application.ConstructAddLogContract;
import com.ljkj.qxn.wisdomsitepro.contract.common.ImageCompressorContract;
import com.ljkj.qxn.wisdomsitepro.contract.common.UploadContract;
import com.ljkj.qxn.wisdomsitepro.data.FileEntity;
import com.ljkj.qxn.wisdomsitepro.data.PermissionConstant;
import com.ljkj.qxn.wisdomsitepro.data.entity.ConstructAddLogInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.ConstructLogAddInfo;
import com.ljkj.qxn.wisdomsitepro.data.event.RefreshEvent;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.model.ApplicationModel;
import com.ljkj.qxn.wisdomsitepro.model.CommonModel;
import com.ljkj.qxn.wisdomsitepro.presenter.application.ConstructAddLogPresenter;
import com.ljkj.qxn.wisdomsitepro.presenter.common.ImageCompressorPresenter;
import com.ljkj.qxn.wisdomsitepro.presenter.common.UploadPresenter;
import com.ljkj.qxn.wisdomsitepro.ui.common.SelectImageCallback;
import com.ljkj.qxn.wisdomsitepro.ui.common.adapter.SelectImageAdapter;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseActivity;
import cdsp.android.util.DateUtils;

/**
 * 作者：JiaJia
 * 创建时间：2018/3/9 17:31.
 * 功能描述：
 */

public class ConstructLogAddActivity extends BaseActivity implements ConstructAddLogContract.View, ImageCompressorContract.View, UploadContract.View {
    private static final int REQUEST_EMERGENCY = 1;
    private static final int REQUEST_EMERGENCY_PREVIEW = 3;

    @BindView(R.id.tv_back)
    TextView tvBack;

    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.et_emergency)
    EditText etEmergency;

    @BindView(R.id.rl_images)
    RecyclerView rlImages;

    @BindView(R.id.et_production)
    EditText etProduction;

    @BindView(R.id.et_security)
    EditText etSecurity;

    @BindView(R.id.tv_submit)
    TextView tvSubmit;

    @BindView(R.id.ll_emergency)
    LinearLayout llEmergency;

    @BindView(R.id.item_date)
    InputItemView itemDate;

    @BindView(R.id.item_weather)
    InputItemView itemWeather;

    @BindView(R.id.item_wind_level)
    InputItemView itemWindLevel;

    @BindView(R.id.item_temperature)
    InputItemView temperatureItem;

    @BindView(R.id.item_emergency)
    InputItemView emergencyItem;

    private SelectImageAdapter emergencySelectImageAdapter;
    private ArrayList<String> emergencyImagePaths = new ArrayList<>();
    List<String> booleanStrList = new ArrayList<>();

    private ConstructAddLogPresenter presenter;
    private ImageCompressorPresenter imageCompressorPresenter;
    private UploadPresenter uploadPresenter;

    private ConstructLogAddInfo constructLogAddInfo;
    private List<FileEntity> fileEntities = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_construct_log_add);
        ButterKnife.bind(this);
    }

    @Override
    protected void initUI() {
        tvTitle.setText("新增施工日志");
        rlImages.setLayoutManager(new GridLayoutManager(this, 4));
        rlImages.setAdapter(emergencySelectImageAdapter = new SelectImageAdapter(this));
        emergencySelectImageAdapter.setSelectImageCallback(new SelectImageCallback() {
            @Override
            public void viewImage(int position) {
                PhotoPickerHelper.startPreview(ConstructLogAddActivity.this, REQUEST_EMERGENCY_PREVIEW, emergencyImagePaths, position);
            }

            @Override
            public void addImage() {
                AndPermission.with(ConstructLogAddActivity.this)
                        .runtime()
                        .permission(PermissionConstant.PERMISSIONS_OF_IMAGE)
                        .onGranted(new Action<List<String>>() {
                            @Override
                            public void onAction(List<String> permissions) {
                                //图片选择
                                PhotoPickerHelper.startPicker(ConstructLogAddActivity.this, REQUEST_EMERGENCY, emergencyImagePaths, 9, true, true);
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

        itemDate.setTag(Calendar.getInstance());
        itemDate.setContent(DateUtils.calendar2str(Calendar.getInstance(), DateUtils.PATTERN_DATE));
    }

    @Override
    protected void initData() {
        booleanStrList.add("是");
        booleanStrList.add("否");

        presenter = new ConstructAddLogPresenter(this, ApplicationModel.newInstance());
        addPresenter(presenter);

        imageCompressorPresenter = new ImageCompressorPresenter(this, CommonModel.newInstance());
        addPresenter(imageCompressorPresenter);
        uploadPresenter = new UploadPresenter(this, CommonModel.newInstance());
        addPresenter(uploadPresenter);

        presenter.getConstructLogAddInfo(UserManager.getInstance().getProjectId());
    }

    @OnClick({R.id.tv_back, R.id.item_date, R.id.item_wind_level, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.item_date:
                showTimeDialog(itemDate);
                break;
            case R.id.tv_submit:
                if (checkData()) {
                    fileEntities.clear();
                    imageCompressorPresenter.compressorImages(emergencyImagePaths);
                }
                break;
            default:
                break;
        }
    }

    private boolean checkData() {
        if (TextUtils.isEmpty(itemWeather.getContent())) {
            showError("请输入天气情况");
            return false;
        }

        if (TextUtils.isEmpty(itemWindLevel.getContent())) {
            showError("请输入风力等级");
            return false;
        }

        if (TextUtils.isEmpty(temperatureItem.getContent())) {
            showError("请输入当前温度");
            return false;
        }

        if (TextUtils.isEmpty(etProduction.getText().toString())) {
            showError("请填写生产记录情况");
            return false;
        }

        if (TextUtils.isEmpty(etSecurity.getText().toString())) {
            showError("请填写质量记录安全记录情况");
            return false;
        }

        if (emergencySelectImageAdapter.getItemCount() == 0) {
            showError("请选择图片");
            return false;
        }

        if (constructLogAddInfo == null) {
            showError("获取施工日志信息失败");
            presenter.getConstructLogAddInfo(UserManager.getInstance().getProjectId());
            return false;
        }

        return true;
    }

    private void submit() {
        String date = itemDate.getContent();
        String weather = itemWeather.getContent();
        String windLevel = itemWindLevel.getContent();
        String temperature = temperatureItem.getContent();
        String production = etProduction.getText().toString().trim();
        String qualitySafe = etSecurity.getText().toString().trim();
        String emergency = emergencyItem.getContent();

        presenter.saveConstructLog(constructLogAddInfo.unitName, date, emergency, temperature,
                constructLogAddInfo.unitName, UserManager.getInstance().getProjectId(), UserManager.getInstance().getProjectName(),
                "", production, qualitySafe, UserManager.getInstance().getUserName(), weather, windLevel, fileEntities);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_EMERGENCY:
                    emergencyImagePaths = data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT);
                    emergencySelectImageAdapter.loadData(emergencyImagePaths);
                    break;

                case REQUEST_EMERGENCY_PREVIEW:
                    emergencyImagePaths = data.getStringArrayListExtra(PhotoPreviewActivity.EXTRA_RESULT);
                    emergencySelectImageAdapter.loadData(emergencyImagePaths);
                    break;
                default:
                    break;
            }

        }
    }

    @Override
    public void showAddInfo(ConstructLogAddInfo info) {
        this.constructLogAddInfo = info;
    }

    @Override
    public void showAddResult(ConstructAddLogInfo info) {
        showError("新增日志成功");
        EventBus.getDefault().post(new RefreshEvent(""));
        finish();
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

    private void showOptionDialog(final List<String> items, final InputItemView inputItemView, OnOptionsSelectListener listener) {
        if (isFastDoubleClick()) {
            return;
        }
        int select = inputItemView.getTag() != null ? (int) inputItemView.getTag() : items.size() / 2;
        PickerDialogHelper.showPickerOption(this, items, select, true, listener);
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
            entity.type = "";
            entity.projId = UserManager.getInstance().getProjectId();
            entity.projCode = UserManager.getInstance().getProjectId();
            fileEntities.add(entity);
        }
        submit();
    }


}
