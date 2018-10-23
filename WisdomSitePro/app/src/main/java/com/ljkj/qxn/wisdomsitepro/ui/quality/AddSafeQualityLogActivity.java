package com.ljkj.qxn.wisdomsitepro.ui.quality;

import android.content.Context;
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
import com.ljkj.qxn.wisdomsitepro.contract.safe.log.SafeQualityLogAddContract;
import com.ljkj.qxn.wisdomsitepro.data.FileEntity;
import com.ljkj.qxn.wisdomsitepro.data.FileType;
import com.ljkj.qxn.wisdomsitepro.data.PermissionConstant;
import com.ljkj.qxn.wisdomsitepro.data.event.RefreshEvent;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.model.CommonModel;
import com.ljkj.qxn.wisdomsitepro.model.SafeQualityLogModel;
import com.ljkj.qxn.wisdomsitepro.presenter.common.ImageCompressorPresenter;
import com.ljkj.qxn.wisdomsitepro.presenter.common.UploadPresenter;
import com.ljkj.qxn.wisdomsitepro.presenter.safe.log.SafeQualityLogAddPresenter;
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
import butterknife.OnClick;
import cdsp.android.ui.base.BaseActivity;
import cdsp.android.util.DateUtils;
import cdsp.android.util.ToastUtils;

/**
 * 类描述：新增安全/质量日志
 * 创建人：lxx
 * 创建时间：2018/7/6
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class AddSafeQualityLogActivity extends BaseActivity implements
        ImageCompressorContract.View, SafeQualityLogAddContract.View, UploadContract.View {
    private static final String KEY_ADD_LOG_TYPE = "key_add_log_type";
    private static final int REQUEST_IMAGE_PICK_CODE = 100;
    private static final int REQUEST_IMAGE_PREVIEW_CODE = 101;

    @BindView(R.id.tv_title)
    TextView titleText;

    @BindView(R.id.item_date)
    InputItemView dateItem;

    @BindView(R.id.item_weather)
    InputItemView weatherItem;

    @BindView(R.id.item_construction_site)
    InputItemView constructionSiteItem;

    @BindView(R.id.item_construction_dynamic)
    EditText constructionDynamicItem;

    @BindView(R.id.tv_status_title)
    TextView statusTitleText;

    @BindView(R.id.et_status)
    EditText statusEdit;

    @BindView(R.id.tv_handle_situation_title)
    TextView handleSituationTitleText;

    @BindView(R.id.et_handle_situation)
    EditText handleSituationEdit;

    @BindView(R.id.tv_images_title)
    TextView imagesTitleText;

    @BindView(R.id.rl_images)
    RecyclerView imagesRV;

    private Calendar startCalendar, endCalendar;
    private SelectImageAdapter imageAdapter;
    private ArrayList<String> imagePaths = new ArrayList<>();
    private List<FileEntity> fileEntities = new ArrayList<>();

    private List<String> weatherList = new ArrayList<>();
    private int addLogType;
    private ImageCompressorPresenter compressorPresenter;
    private SafeQualityLogAddPresenter safeQualityLogAddPresenter;
    private UploadPresenter uploadPresenter;

    public static void startActivity(Context context, int type) {
        Intent intent = new Intent(context, AddSafeQualityLogActivity.class);
        intent.putExtra(KEY_ADD_LOG_TYPE, type);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_safe_quality_log);
    }

    @Override
    protected void initUI() {
        addLogType = getIntent().getIntExtra(KEY_ADD_LOG_TYPE, SafeQualityLogActivity.SAFE_LOG);
        titleText.setText(addLogType == SafeQualityLogActivity.SAFE_LOG ? "新增安全日志" : "新增质量日志");
        statusTitleText.setText(addLogType == SafeQualityLogActivity.SAFE_LOG ? "安全状况" : "质量状况");
        handleSituationTitleText.setText(addLogType == SafeQualityLogActivity.SAFE_LOG ? "安全问题处理情况" : "质量问题处理情况");
        imagesTitleText.setText(addLogType == SafeQualityLogActivity.SAFE_LOG ? "施工安全图片" : "施工质量图片");
        imagesRV.setNestedScrollingEnabled(false);
        imagesRV.setLayoutManager(new GridLayoutManager(this, 3));
        imagesRV.setAdapter(imageAdapter = new SelectImageAdapter(this));

        startCalendar = Calendar.getInstance();
        startCalendar.add(Calendar.YEAR, -10);
        dateItem.setTag(Calendar.getInstance());
        endCalendar = Calendar.getInstance();
        dateItem.setContent(DateUtils.calendar2str(Calendar.getInstance(), DateUtils.PATTERN_DATE));
    }

    @Override
    protected void initData() {
        weatherList.add("晴天");
        weatherList.add("阴天");
        weatherList.add("小雨");
        weatherList.add("大雨");

        safeQualityLogAddPresenter = new SafeQualityLogAddPresenter(this, SafeQualityLogModel.newInstance());
        compressorPresenter = new ImageCompressorPresenter(this, CommonModel.newInstance());
        uploadPresenter = new UploadPresenter(this, CommonModel.newInstance());
        addPresenter(uploadPresenter);
        addPresenter(compressorPresenter);
        addPresenter(safeQualityLogAddPresenter);

        weatherItem.setContent(weatherList.get(0));
        imageAdapter.setSelectImageCallback(new SelectImageCallback() {
            @Override
            public void viewImage(int position) {
                PhotoPickerHelper.startPreview(AddSafeQualityLogActivity.this, REQUEST_IMAGE_PREVIEW_CODE, imagePaths, position);
            }

            @Override
            public void addImage() {
                AndPermission.with(AddSafeQualityLogActivity.this)
                        .runtime()
                        .permission(PermissionConstant.PERMISSIONS_OF_IMAGE)
                        .onGranted(new Action<List<String>>() {
                            @Override
                            public void onAction(List<String> permissions) {
                                PhotoPickerHelper.startPicker(AddSafeQualityLogActivity.this, REQUEST_IMAGE_PICK_CODE, imagePaths);
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
                default:
                    break;
            }

        }
    }

    @OnClick({R.id.tv_back, R.id.tv_submit, R.id.item_date, R.id.item_weather})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.item_date:
                showTimeDialog(dateItem);
                break;
            case R.id.item_weather:
                showOptionDialog(weatherList, weatherItem);
                break;
            case R.id.tv_submit:
                if (checkData()) {
                    fileEntities.clear();
                    if (imagePaths.size() > 0) {
                        compressorPresenter.compressorImages(imagePaths);
                    } else {
                        doSubmit();
                    }
                }
                break;
            default:
                break;
        }
    }

    private boolean checkData() {
        if (TextUtils.isEmpty(constructionSiteItem.getContent())) {
            showError("请输入施工部位");
            return false;
        }
        if (TextUtils.isEmpty(constructionDynamicItem.getText().toString())) {
            showError("请输入施工工序动态");
            return false;
        }
        if (TextUtils.isEmpty(statusEdit.getText().toString())) {
            showError(statusEdit.getHint().toString());
            return false;
        }
        if (TextUtils.isEmpty(handleSituationEdit.getText().toString())) {
            showError(handleSituationEdit.getHint().toString());
            return false;
        }
        return true;
    }

    private void doSubmit() {
        String date = dateItem.getContent();
        String weather = weatherItem.getContent();
        String constructionSite = constructionSiteItem.getContent();
        String constructionDynamic = constructionDynamicItem.getText().toString();
        String status = statusEdit.getText().toString();
        String handleSituation = handleSituationEdit.getText().toString();
        List<File> files = new ArrayList<>();
        for (String path : imagePaths) {
            files.add(new File(path));
        }

        if (addLogType == SafeQualityLogActivity.SAFE_LOG) {
            safeQualityLogAddPresenter.addSafeLog(constructionDynamic, constructionSite, date, UserManager.getInstance().getUserId(),
                    UserManager.getInstance().getUserName(), UserManager.getInstance().getProjectCode(), UserManager.getInstance().getProjectId(),
                    handleSituation, status, weatherList.indexOf(weather) + 1 + "", fileEntities);
        } else {
            safeQualityLogAddPresenter.addQualityLog(constructionDynamic, constructionSite, date, UserManager.getInstance().getProjectId(),
                    handleSituation, status, weatherList.indexOf(weather) + 1 + "", fileEntities, UserManager.getInstance().getUserName());
        }
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

    private void showOptionDialog(final List<String> items, final InputItemView inputItemView) {
        if (isFastDoubleClick()) {
            return;
        }
        int select = inputItemView.getTag() != null ? (int) inputItemView.getTag() : 0;
        PickerDialogHelper.showPickerOption(this, items, select, true, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                inputItemView.setTag(options1);
                inputItemView.setContent(items.get(options1));
            }
        });
    }

    @Override
    public void showCompressFilePaths(List<String> data) {
        List<File> list = new ArrayList<>();
        for (String path : data) {
            list.add(new File(path));
        }
        uploadPresenter.upload(UserManager.getInstance().getProjectId(), list);
    }

    @Override
    public void showAddSafe() {
        ToastUtils.showShort("提交成功");
        EventBus.getDefault().post(new RefreshEvent(""));
        finish();
    }

    @Override
    public void showAddQuality() {
        ToastUtils.showShort("提交成功");
        EventBus.getDefault().post(new RefreshEvent(""));
        finish();
    }

    @Override
    public void showUploadInfo(List<FileEntity> entities) {
        for (FileEntity entity : entities) {
            entity.type = FileType.SafeDefend.TYPE_SAFE_DEFEND;
            entity.projId = UserManager.getInstance().getProjectId();
            entity.projCode = UserManager.getInstance().getProjectCode();
            fileEntities.add(entity);
        }
        doSubmit();
    }


}
