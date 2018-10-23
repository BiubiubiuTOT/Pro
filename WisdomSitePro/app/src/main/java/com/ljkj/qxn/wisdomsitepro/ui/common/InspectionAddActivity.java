package com.ljkj.qxn.wisdomsitepro.ui.common;

import android.content.Context;
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
import com.ljkj.qxn.wisdomsitepro.Utils.PickerDialogHelper;
import com.ljkj.qxn.wisdomsitepro.Utils.widget.InputItemView;
import com.ljkj.qxn.wisdomsitepro.contract.common.DistictDataContract;
import com.ljkj.qxn.wisdomsitepro.contract.common.ImageCompressorContract;
import com.ljkj.qxn.wisdomsitepro.contract.common.UploadFilesContract;
import com.ljkj.qxn.wisdomsitepro.contract.quality.QualityPatrolAddContract;
import com.ljkj.qxn.wisdomsitepro.data.Consts;
import com.ljkj.qxn.wisdomsitepro.data.PermissionConstant;
import com.ljkj.qxn.wisdomsitepro.data.entity.DistictInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.EnclosureInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.LabourInfo;
import com.ljkj.qxn.wisdomsitepro.data.event.InspectionEvent;
import com.ljkj.qxn.wisdomsitepro.Utils.PhotoPickerHelper;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.model.CommonModel;
import com.ljkj.qxn.wisdomsitepro.model.QualityModel;
import com.ljkj.qxn.wisdomsitepro.presenter.common.DistictDataPresenter;
import com.ljkj.qxn.wisdomsitepro.presenter.common.ImageCompressorPresenter;
import com.ljkj.qxn.wisdomsitepro.presenter.common.UploadFilesPresenter;
import com.ljkj.qxn.wisdomsitepro.presenter.quality.QualityPatrolAddPresenter;
import com.ljkj.qxn.wisdomsitepro.ui.common.adapter.SelectImageAdapter;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.http.ResponseData;
import cdsp.android.ui.base.BaseActivity;
import cdsp.android.util.DateUtils;

/**
 * 类描述：新增巡检
 * 创建人：liufei
 * 创建时间：2018/2/6 14:32
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class InspectionAddActivity extends BaseActivity implements SelectImageCallback, QualityPatrolAddContract.View, UploadFilesContract.View, ImageCompressorContract.View {
    private static final String KEY_TYPE = "type";

    @BindView(R.id.item_date)
    InputItemView dateItem;

    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.et_fwr)
    EditText etFwr;

    @BindView(R.id.et_ques)
    EditText etQues;

    @BindView(R.id.tv_group_leader)
    TextView tvGroupLader;

    @BindView(R.id.tv_reason)
    TextView tvReason;

    @BindView(R.id.ll_choose_reason)
    LinearLayout llChooseReason;

    @BindView(R.id.tv_lose)
    TextView tvLose;

    @BindView(R.id.ll_choose_lose)
    LinearLayout llChooseLose;

    @BindView(R.id.rl_images)
    RecyclerView rlImages;

    @BindView(R.id.tv_submit)
    TextView tvSubmit;

    @BindView(R.id.item_group)
    InputItemView groupItem; //整改班组

    private String type;

    private SelectImageAdapter selectImageAdapter;

    private ArrayList<String> filePaths = new ArrayList<>();

    private List<DistictInfo> lossTypeD = new ArrayList<DistictInfo>();
    private List<DistictInfo> whyTypeD = new ArrayList<DistictInfo>();
    private List<DistictInfo> zgbzD = new ArrayList<DistictInfo>();
    private List<LabourInfo> groupLeaders = new ArrayList<LabourInfo>();

    private int bzIndex;
    private int whyIndex;
    private int lossIndex;
    private int groupLeaderIndex;

    private String cgFormId;

    Calendar startCalendar, endCalendar;

    private QualityPatrolAddPresenter qualityPatrolAddPresenter;

    private DistictDataPresenter lossTypePresenter;

    private DistictDataPresenter whyTypePresenter;

    private UploadFilesPresenter filesPresenter;

    private ImageCompressorPresenter compressorPresenter;


    public static void startActivity(Context context, String type) {
        Intent intent = new Intent(context, InspectionAddActivity.class);
        intent.putExtra(KEY_TYPE, type);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quality_patrol_add);
    }

    @Override
    protected void initUI() {
        this.type = getIntent().getStringExtra(KEY_TYPE);
        tvTitle.setText(TextUtils.equals(BaseInspectionListActivity.QUALITY_TYPE, type) ? "新增质量巡检" : "新增安全巡检");
        tvReason.setText("无");
        tvReason.setTag("w");
        tvLose.setText("无");
        tvLose.setTag("w");
        rlImages.setLayoutManager(new GridLayoutManager(this, 3));
        rlImages.setAdapter(selectImageAdapter = new SelectImageAdapter(this));
        selectImageAdapter.setSelectImageCallback(this);
        etFwr.setText(UserManager.getInstance().getUserName());
    }

    @Override
    protected void initData() {
        dateItem.setTag(Calendar.getInstance());
        dateItem.setContent(DateUtils.calendar2str(Calendar.getInstance(), DateUtils.PATTERN_DATE));
        startCalendar = Calendar.getInstance();
        startCalendar.add(Calendar.YEAR, -10);
        endCalendar = Calendar.getInstance();

        qualityPatrolAddPresenter = new QualityPatrolAddPresenter(this, QualityModel.newInstance());
        filesPresenter = new UploadFilesPresenter(this, CommonModel.newInstance());
        compressorPresenter = new ImageCompressorPresenter(this, CommonModel.newInstance());
        addPresenter(qualityPatrolAddPresenter);
        addPresenter(filesPresenter);
        addPresenter(compressorPresenter);
        addPresenter(whyTypePresenter = buildDistictDataPresenter(1));
        addPresenter(lossTypePresenter = buildDistictDataPresenter(0));
    }

    /**
     * @param type 0,事故造成的人员伤亡或者直接经济损失分类
     *             1,事故发生的原因分类
     *             2,整改班组
     */
    private DistictDataPresenter buildDistictDataPresenter(final int type) {

        final DistictDataPresenter presenter = new DistictDataPresenter(new DistictDataContract.View() {
            @Override
            public void showDisticts(List<DistictInfo> data) {

                if (data != null && !data.isEmpty()) {
                    if (type == 0) {

                        lossTypeD = data;
                        showLossSelect();

                    } else if (type == 1) {

                        whyTypeD = data;
                        showReasonSelect();

                    } else if (type == 2) {

                        zgbzD = data;
                        showGroupSelect();

                    }
                }
            }

            @Override
            public void showProgress(String message) {

            }

            @Override
            public void hideProgress() {

            }

            @Override
            public void showError(String message) {
                InspectionAddActivity.this.showError(message);
            }
        }, CommonModel.newInstance());
        return presenter;
    }


    /**
     * 整改班组（单选）
     */

    private void showGroupSelect() {

        List<String> bzStrs = new ArrayList<String>();
        for (int i = 0; i < zgbzD.size(); i++) {
            bzStrs.add(zgbzD.get(i).getName());
        }
        PickerDialogHelper.showPickerOption(this, bzStrs, bzIndex, false, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                bzIndex = options1;

            }
        });
    }

    /**
     * 班组负责人（单选）
     */
    private void showGroupLeaderSelect() {

        List<String> strs = new ArrayList<String>();
        for (int i = 0; i < groupLeaders.size(); i++) {
            strs.add(groupLeaders.get(i).getName());
        }
        PickerDialogHelper.showPickerOption(this, strs, groupLeaderIndex, false, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                groupLeaderIndex = options1;
                tvGroupLader.setTag(groupLeaders.get(options1).getId());
                tvGroupLader.setText(groupLeaders.get(options1).getName());
            }
        });
    }

    /**
     * 原因（单选）
     */
    private void showReasonSelect() {

        List<String> strs = new ArrayList<String>();
        for (int i = 0; i < whyTypeD.size(); i++) {
            strs.add(whyTypeD.get(i).getName());
        }
        PickerDialogHelper.showPickerOption(this, strs, whyIndex, false, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                whyIndex = options1;
                tvReason.setTag(whyTypeD.get(options1).getValue());
                tvReason.setText(whyTypeD.get(options1).getName());
            }
        });
    }

    /**
     * 损失（单选）
     */
    private void showLossSelect() {

        List<String> strs = new ArrayList<String>();
        for (int i = 0; i < lossTypeD.size(); i++) {
            strs.add(lossTypeD.get(i).getName());
        }
        PickerDialogHelper.showPickerOption(this, strs, lossIndex, false, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                lossIndex = options1;
                tvLose.setTag(lossTypeD.get(options1).getValue());
                tvLose.setText(lossTypeD.get(options1).getName());
            }
        });
    }

    @Override
    public void viewImage(int position) {
        PhotoPickerHelper.startPreview(this, PhotoPreviewActivity.REQUEST_PREVIEW, filePaths, position);
    }

    @Override
    public void addImage() {
        AndPermission.with(InspectionAddActivity.this)
                .runtime()
                .permission(PermissionConstant.PERMISSIONS_OF_IMAGE)
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> permissions) {
                        //图片选择
                        PhotoPickerHelper.startPicker(InspectionAddActivity.this,0,filePaths);

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
            //图片选择
            if (requestCode == 0) {
                filePaths = data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT);
            }
            //图片预览
            else if (requestCode == PhotoPreviewActivity.REQUEST_PREVIEW) {
                filePaths = data.getStringArrayListExtra(PhotoPreviewActivity.EXTRA_RESULT);
            }
            selectImageAdapter.loadData(filePaths);
        }
    }

    @OnClick({R.id.tv_back, R.id.item_date, R.id.tv_group_leader, R.id.tv_reason, R.id.tv_lose, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.tv_back:
                finish();
                break;

            //日期选择
            case R.id.item_date:
                final Calendar current = (Calendar) dateItem.getTag();
                PickerDialogHelper.showTimePicker(this, current, startCalendar, endCalendar, false, new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        current.setTime(date);
                        dateItem.setTag(current);
                        dateItem.setContent(DateUtils.date2str(date, DateUtils.PATTERN_DATE));
                    }
                });
                break;

            //班组负责人
            case R.id.tv_group_leader:
                if (groupLeaders.isEmpty()) {
                    qualityPatrolAddPresenter.listManageList(UserManager.getInstance().getProjectId());
                } else {
                    showGroupLeaderSelect();
                }
                break;

            //按事故发生原因分类
            case R.id.tv_reason:
                if (whyTypeD.isEmpty()) {

                    whyTypePresenter.listDisticts("sgfsyyfl");

                } else {
                    showReasonSelect();
                }
                break;

            //按人员伤亡及经济损失分类
            case R.id.tv_lose:

                if (lossTypeD.isEmpty()) {
                    lossTypePresenter.listDisticts("sgswssfl");
                } else {
                    showLossSelect();
                }
                break;

            //数据提交
            case R.id.tv_submit:
                checkAndPostData();
                break;
        }
    }

    /**
     * 数据校验提交
     */
    private void checkAndPostData() {
        String fwr = etFwr.getText().toString().trim();
        String groupLeader = tvGroupLader.getText().toString().trim();
        String reason = tvReason.getText().toString().trim();
        String loss = tvLose.getText().toString();
        String question = etQues.getText().toString().trim();
        String group = groupItem.getContent();

        if (TextUtils.isEmpty(group)) {
            showError("请输入整改班组");
            return;
        }

        if (TextUtils.isEmpty(question)) {
            showError("请输入问题描述");
            return;
        }
        if (TextUtils.isEmpty(fwr)) {
            showError("请输入发文人");
            return;
        }
        if (TextUtils.isEmpty(groupLeader)) {
            showError("请选择班组负责人");
            return;
        }
        if (TextUtils.isEmpty(reason)) {
            showError("请选择原因分类");
            return;
        }
        if (TextUtils.isEmpty(loss)) {
            showError("请选择损失分类");
            return;
        }

        if (filePaths.isEmpty()) {
            showError("请选择图片");
            return;
        }

        qualityPatrolAddPresenter.addQualityPatrol((String) tvGroupLader.getTag(), "", fwr, (String) tvLose.getTag(), "1", dateItem.getContent(), type, (String) tvReason.getTag(), UserManager.getInstance().getProjectId(), group, question);
    }

    @Override
    public void showCompressFilePaths(List<String> data) {
        filesPresenter.uploadFiles("file", cgFormId, "pro_check", "", data);
    }

    @Override
    public void showEnclosureInfos(List<EnclosureInfo> data) {
        EventBus.getDefault().post(new InspectionEvent(Consts.EVENT_TYPE.INSPECTION_ADD));
        showError("数据新增提交成功");
        finish();
    }

    @Override
    public void showQualityPatrolAddResult(ResponseData<String> data) {

        cgFormId = data.getResult();

        //图片上传
        if (!filePaths.isEmpty()) {
            compressorPresenter.compressorImages(filePaths);
        }

        //无图片上传
        else {

            finish();
            showError("数据新增提交成功");
        }
    }

    @Override
    public void showManagerList(List<LabourInfo> data) {
        if (data != null && !data.isEmpty()) {
            groupLeaders = data;
            showGroupLeaderSelect();
        }
    }
}
