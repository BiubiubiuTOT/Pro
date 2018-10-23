package com.ljkj.qxn.wisdomsitepro.ui.safe;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.foamtrace.photopicker.PhotoPickerActivity;
import com.foamtrace.photopicker.PhotoPreviewActivity;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.Utils.PhotoPickerHelper;
import com.ljkj.qxn.wisdomsitepro.Utils.PickerDialogHelper;
import com.ljkj.qxn.wisdomsitepro.Utils.widget.InputItemView;
import com.ljkj.qxn.wisdomsitepro.Utils.widget.ItemView;
import com.ljkj.qxn.wisdomsitepro.contract.common.DistictDataContract;
import com.ljkj.qxn.wisdomsitepro.contract.common.ImageCompressorContract;
import com.ljkj.qxn.wisdomsitepro.contract.common.UploadContract;
import com.ljkj.qxn.wisdomsitepro.contract.safe.AuthorityContract;
import com.ljkj.qxn.wisdomsitepro.contract.safe.SafePatrolAddContract;
import com.ljkj.qxn.wisdomsitepro.data.Consts;
import com.ljkj.qxn.wisdomsitepro.data.FileEntity;
import com.ljkj.qxn.wisdomsitepro.data.PermissionConstant;
import com.ljkj.qxn.wisdomsitepro.data.entity.DeptTeamInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.DistictInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.LabourInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.TeamLeader;
import com.ljkj.qxn.wisdomsitepro.data.event.InspectionEvent;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.model.AuthorityModel;
import com.ljkj.qxn.wisdomsitepro.model.CommonModel;
import com.ljkj.qxn.wisdomsitepro.model.SafeModel;
import com.ljkj.qxn.wisdomsitepro.presenter.common.AuthorityPresenter;
import com.ljkj.qxn.wisdomsitepro.presenter.common.DistictDataPresenter;
import com.ljkj.qxn.wisdomsitepro.presenter.common.ImageCompressorPresenter;
import com.ljkj.qxn.wisdomsitepro.presenter.common.UploadPresenter;
import com.ljkj.qxn.wisdomsitepro.presenter.safe.SafePatrolAddPresenter;
import com.ljkj.qxn.wisdomsitepro.ui.common.SelectImageCallback;
import com.ljkj.qxn.wisdomsitepro.ui.common.adapter.SelectImageAdapter;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseActivity;
import cdsp.android.util.DateUtils;

public class SafeInspectionAddV2Activity extends BaseActivity implements
        SelectImageCallback, SafePatrolAddContract.View,
        UploadContract.View, ImageCompressorContract.View
        , DistictDataContract.View, AuthorityContract.View {

    @BindView(R.id.item_date)
    ItemView dateItem;

    @BindView(R.id.item_date_end)
    ItemView itemDateEnd;

    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.item_fwr)
    ItemView itemFwr;

    @BindView(R.id.et_ques)
    EditText etQues;

    @BindView(R.id.item_group_leader)
    ItemView itemGroupLader;

    @BindView(R.id.item_reason)
    ItemView itemReason;

    @BindView(R.id.item_lose)
    ItemView itemLose;

    @BindView(R.id.rl_images)
    RecyclerView rlImages;

    @BindView(R.id.tv_submit)
    TextView tvSubmit;

    @BindView(R.id.item_group)
    ItemView itemGroup; //整改班组

    @BindView(R.id.item_position)
    InputItemView itemPosition;

    @BindView(R.id.item_build_floor)
    ItemView itemBuildFloor;

    private SelectImageAdapter selectImageAdapter;

    private ArrayList<String> filePaths = new ArrayList<>();

    private List<DistictInfo> whyTypeD = new ArrayList<DistictInfo>();
    private List<DistictInfo> zgbzD = new ArrayList<DistictInfo>();
    private List<LabourInfo> groupLeaders = new ArrayList<LabourInfo>();

    private int bzIndex;
    private int whyIndex;
    private int lossIndex;

    private String cgFormId;

    Calendar startCalendar, endCalendar, endDateCalendar;

    private SafePatrolAddPresenter safePatrolAddPresenter;
    private AuthorityPresenter authorityPresenter;


    private DistictDataPresenter whyTypePresenter;

    private UploadPresenter uploadPresenter;

    private ImageCompressorPresenter compressorPresenter;
    private List<String> strsLoss;
    private String[] arrLoss = {"一般事故", "较大事故", "重大事故", "特别重大事故"};
    private List<String> options1Items = new ArrayList<>();
    private List<List<String>> options2Items = new ArrayList<>();
    private List<String> floorItems1 = new ArrayList<>();
    private List<List<String>> floorItems2 = new ArrayList<>();
    private Map<String, String> options = new HashMap<>();
    private OptionsPickerView<String> pvOptions;
    private OptionsPickerView<String> floorOptions;
    private String group;
    private String question;
    private String loss;
    private String reason;
    private String teamManager;
    private String teamId;
    private String teamManagerId;
    private String createUser;
    private String position;
    private String floor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safe_inspection_add_v2);
    }

    @Override
    protected void initUI() {
        tvTitle.setText("新增安全巡检");
        rlImages.setLayoutManager(new GridLayoutManager(this, 3));
        rlImages.setAdapter(selectImageAdapter = new SelectImageAdapter(this));
        selectImageAdapter.setSelectImageCallback(this);
        itemFwr.setContent(UserManager.getInstance().getUserName());

        strsLoss = Arrays.asList(arrLoss);

        buildFloor();
        initFloorWindow();
    }

    private void buildFloor() {
        for (int i = 1; i < 51; i++) {
            floorItems1.add(i + "栋");
            List<String> floors = new ArrayList<>();
            for (int j = 1; j < 51; j++) {
                floors.add(j + "层");
            }
            floorItems2.add(floors);
        }
    }

    @Override
    protected void initData() {
        dateItem.setTag(Calendar.getInstance());
        dateItem.setContent(DateUtils.calendar2str(Calendar.getInstance(), DateUtils.PATTERN_DATE));
        startCalendar = Calendar.getInstance();
        startCalendar.add(Calendar.YEAR, -10);
        endCalendar = Calendar.getInstance();
        endDateCalendar = Calendar.getInstance();
        endDateCalendar.set(2020, 11, 28);
        dateItem.setContent(DateUtils.date2str(new Date(), DateUtils.PATTERN_DATE));
        itemDateEnd.setContent(DateUtils.date2str(new Date(), DateUtils.PATTERN_DATE));

        safePatrolAddPresenter = new SafePatrolAddPresenter(this, SafeModel.newInstance());
        uploadPresenter = new UploadPresenter(this, CommonModel.newInstance());
        compressorPresenter = new ImageCompressorPresenter(this, CommonModel.newInstance());
        addPresenter(safePatrolAddPresenter);
        addPresenter(uploadPresenter);
        addPresenter(compressorPresenter);
        addPresenter(whyTypePresenter = new DistictDataPresenter(this, CommonModel.newInstance()));
        authorityPresenter = new AuthorityPresenter(this, AuthorityModel.newInstance());
        addPresenter(authorityPresenter);
        authorityPresenter.getDeptTeamList(UserManager.getInstance().getProjectId());
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
        pvOptions.show();
    }

    /**
     * 原因（单选）
     */
    private void showReasonSelect() {

        List<String> strs = new ArrayList<String>();
        for (int i = 0; i < whyTypeD.size(); i++) {
            strs.add(whyTypeD.get(i).getName());
        }
        if (strs.isEmpty()) {
            showError("数据获取失败，请稍后重试");
            return;
        }
        PickerDialogHelper.showPickerOption(this, strs, whyIndex, false, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                whyIndex = options1;
                itemReason.setTag(whyTypeD.get(options1).getValue());
                itemReason.setContent(whyTypeD.get(options1).getName());
            }
        });
    }

    /**
     * 损失（单选）
     */
    private void showLossSelect() {
        PickerDialogHelper.showPickerOption(this, strsLoss, lossIndex, false, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                lossIndex = options1 + 1;
                itemLose.setContent(strsLoss.get(options1));
            }
        });
    }

    @Override
    public void viewImage(int position) {
        PhotoPickerHelper.startPreview(this, PhotoPreviewActivity.REQUEST_PREVIEW, filePaths, position);
    }

    @Override
    public void addImage() {
        AndPermission.with(SafeInspectionAddV2Activity.this)
                .runtime()
                .permission(PermissionConstant.PERMISSIONS_OF_IMAGE)
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> permissions) {
                        //图片选择
                        PhotoPickerHelper.startPicker(SafeInspectionAddV2Activity.this, 0, filePaths);

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

    @OnClick({R.id.tv_back, R.id.item_date, R.id.item_group,
            R.id.item_reason, R.id.item_lose, R.id.tv_submit,
            R.id.item_build_floor, R.id.item_date_end})
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
            case R.id.item_date_end:
                PickerDialogHelper.showTimePicker(this, Calendar.getInstance(), startCalendar, endDateCalendar, false, new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        itemDateEnd.setContent(DateUtils.date2str(date, DateUtils.PATTERN_DATE));
                    }
                });
                break;
            //按事故发生原因分类
            case R.id.item_reason:
                if (whyTypeD.isEmpty()) {

                    whyTypePresenter.listDisticts("securityPatrolAccidentCause");

                } else {
                    showReasonSelect();
                }
                break;
            //整改班组
            case R.id.item_group:
                showGroupLeaderSelect();
                break;

            //按人员伤亡及经济损失分类
            case R.id.item_lose:

                showLossSelect();
                break;

            //数据提交
            case R.id.tv_submit:
                checkAndPostData();
                break;
            case R.id.item_build_floor:
                floorOptions.show();
                break;
        }
    }

    /**
     * 数据校验提交
     */
    private void checkAndPostData() {
        createUser = itemFwr.getContent().trim();
        teamManager = itemGroupLader.getContent();
        reason = itemReason.getContent().trim();
        loss = itemLose.getContent().trim();
        question = etQues.getText().toString().trim();
        group = itemGroup.getContent().trim();
        position = itemPosition.getContent().trim();

        if (TextUtils.isEmpty(group)) {
            showError("请输入整改班组");
            return;
        }

        if (TextUtils.isEmpty(question)) {
            showError("请输入问题描述");
            return;
        }
        if (TextUtils.isEmpty(createUser)) {
            showError("请输入发文人");
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
        if (TextUtils.isEmpty(floor)) {
            showError("请选择楼栋楼层");
            return;
        }
        if (TextUtils.isEmpty(position)) {
            showError("请输入检查部位");
            return;
        }

        //图片上传
        if (!filePaths.isEmpty()) {
            compressorPresenter.compressorImages(filePaths);
        }
        //无图片上传
        else {
            post(null);
        }


    }

    @Override
    public void showCompressFilePaths(List<String> data) {
        uploadFile(data);
    }

    private void uploadFile(List<String> paths) {
        List<File> files = new ArrayList<>();
        for (String path : paths) {
            files.add(new File(path));
        }
        uploadPresenter.upload(UserManager.getInstance().getProjectId(), files);
    }


    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, SafeInspectionAddV2Activity.class));
    }

    @Override
    public void showDisticts(List<DistictInfo> data) {
        whyTypeD = data;
        showReasonSelect();
    }

    @Override
    public void showDeptTeamList(List<DeptTeamInfo> list) {
        List<String> list1;
        for (DeptTeamInfo deptTeamInfo : list) {
            String name = deptTeamInfo.getName();
            options.put(name, deptTeamInfo.getId());
            options1Items.add(name);
            list1 = new ArrayList<>();
            List<DeptTeamInfo.LmTeamListBean> lmTeamList = deptTeamInfo.getLmTeamList();
            for (DeptTeamInfo.LmTeamListBean lmTeamListBean : lmTeamList) {
                String name1 = lmTeamListBean.getName();
                options.put(name1, lmTeamListBean.getId());
                list1.add(name1);
            }
            options2Items.add(list1);
        }
        initGroupWindow();
    }

    @Override
    public void showTeamLeader(TeamLeader leader) {
        if (leader != null) {
            itemGroupLader.setContent(leader.getName());
            teamManagerId = leader.getId();
        }
    }

    private void initGroupWindow() {
        //条件选择器
        pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                List<String> list = options2Items.get(options1);
                if (!list.isEmpty()) {
                    String str = list.get(option2);
                    teamId = options.get(str);
                    itemGroup.setContent(str);

                    authorityPresenter.getTeamLeader(teamId, UserManager.getInstance().getProjectId());
                } else {
                    itemGroup.setContent("请选择");
                    teamId = "";
                }
            }
        }).setSubmitColor(ContextCompat.getColor(this, R.color.color_main))//确定按钮文字颜色
                .setCancelColor(ContextCompat.getColor(this, R.color.color_main))//取消按钮文字颜色
                .setTextColorCenter(ContextCompat.getColor(this, R.color.color_main))//选中颜色
                .isDialog(true)
                .setOutSideCancelable(false)
                .setTitleColor(ContextCompat.getColor(this, R.color.color_main))
                .build();
        pvOptions.setPicker(options1Items, options2Items);
    }

    private void initFloorWindow() {
        //条件选择器
        floorOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                floor = floorItems1.get(options1) + floorItems2.get(options1).get(option2);
                itemBuildFloor.setContent(floor);
            }
        }).setSubmitColor(ContextCompat.getColor(this, R.color.color_main))//确定按钮文字颜色
                .setCancelColor(ContextCompat.getColor(this, R.color.color_main))//取消按钮文字颜色
                .setTextColorCenter(ContextCompat.getColor(this, R.color.color_main))//选中颜色
                .isDialog(true)
                .setOutSideCancelable(false)
                .setTitleColor(ContextCompat.getColor(this, R.color.color_main))
                .build();
        floorOptions.setPicker(floorItems1, floorItems2);
    }

    @Override
    public void showSafePatrolAddResult(String data) {
        EventBus.getDefault().post(new InspectionEvent(Consts.EVENT_TYPE.INSPECTION_ADD));
        showError("提交成功");
        finish();
    }

    @Override
    public void showUploadInfo(List<FileEntity> entities) {
        for (FileEntity entity : entities) {
            entity.setProjId(UserManager.getInstance().getProjectId());
        }
        post(entities);
    }

    private void post(List<FileEntity> entities) {
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
        HashMap<String, Object> params = new HashMap<>();
        params.put("file", jsonArray);
        params.put("accidentCause", reason);//事故原因
        params.put("checkDate", dateItem.getContent());
        params.put("endDate", itemDateEnd.getContent());
        params.put("team", group);//整改班组名称
        params.put("teamId", teamId);
        params.put("teamManager", teamManager);//整改班组负责人
        params.put("teamManagerId", teamManagerId);
        params.put("riskContent", question);//隐患整改内容
        params.put("projId", UserManager.getInstance().getProjectId());
        params.put("personEconomicLoss", lossIndex);//人员伤亡或经济损失分类
        params.put("createUsername", createUser);//人员伤亡或经济损失分类
        params.put("createUserid", UserManager.getInstance().getUserId());//
        params.put("checkPosition", position);//检查位置
        params.put("buildFloor", floor);//楼栋楼层
        safePatrolAddPresenter.safePatrolAdd(params);
    }
}
