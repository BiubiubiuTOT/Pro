package com.ljkj.qxn.wisdomsitepro.ui.application.supervisor;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.Utils.PickerDialogHelper;
import com.ljkj.qxn.wisdomsitepro.Utils.widget.InputItemView;
import com.ljkj.qxn.wisdomsitepro.contract.common.ImageTagCompressorContract;
import com.ljkj.qxn.wisdomsitepro.contract.common.QueryFileContract;
import com.ljkj.qxn.wisdomsitepro.contract.supervisor.SideStationRecordContract;
import com.ljkj.qxn.wisdomsitepro.data.FileEntity;
import com.ljkj.qxn.wisdomsitepro.data.entity.PageInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.SideStationRecordUnit;
import com.ljkj.qxn.wisdomsitepro.data.entity.SiteStationRecorderManageDetailInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.SiteStationRecorderManageInfo;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.model.CommonModel;
import com.ljkj.qxn.wisdomsitepro.model.SupervisorModel;
import com.ljkj.qxn.wisdomsitepro.presenter.common.ImageTagCompressorPresenter;
import com.ljkj.qxn.wisdomsitepro.presenter.common.QueryFilePresenter;
import com.ljkj.qxn.wisdomsitepro.presenter.supervisor.SideStationRecordPresenter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;
import cdsp.android.ui.base.BaseActivity;
import cdsp.android.util.DateUtils;

/**
 * 类描述：新增旁站记录
 * 创建人：lxx
 * 创建时间：2018/8/28
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class AddSideStationRecordActivity extends BaseActivity implements
        SideStationRecordContract.View, ImageTagCompressorContract.View, QueryFileContract.View {
    private static final String[] TITLES = {"人", "机", "料", "法/环"};

    @BindView(R.id.tv_title)
    TextView titleText;

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    @BindView(R.id.viewpager)
    ViewPager viewpager;

    @BindView(R.id.scroll_View)
    ScrollView scrollView;

    @BindView(R.id.item_record_name)
    InputItemView recordNameItem;

    @BindView(R.id.item_start_date)
    InputItemView startDateItem;

    @BindView(R.id.item_finish_date)
    InputItemView finishDateItem;

    @BindView(R.id.item_supervisor_unit)
    InputItemView supervisorUnitItem;

    @BindView(R.id.item_construction_unit)
    InputItemView constructionUnitItem;

    @BindView(R.id.item_progress)
    InputItemView progressItem;

    @BindView(R.id.item_position)
    InputItemView positionItem;

    @BindView(R.id.tv_submit)
    TextView submitText;

    private List<Fragment> fragments = new ArrayList<>();

    private SideStationRecordPresenter presenter;
    private ImageTagCompressorPresenter imageTagCompressorPresenter;
    private QueryFilePresenter queryFilePresenter;
    private SiteStationRecorderManageDetailInfo info = new SiteStationRecorderManageDetailInfo();
    private Map<String, List<String>> fileList = new HashMap<>();
    private List<FileEntity> entityList = new ArrayList<>();
    private int count;
    private String id;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, AddSideStationRecordActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_side_station_record);
    }

    private void updateUIForDetail(SiteStationRecorderManageDetailInfo info) {
        recordNameItem.setEnable(false);
        startDateItem.setEnable(false);
        finishDateItem.setEnable(false);
        startDateItem.showArrow(false);
        finishDateItem.showArrow(false);
        positionItem.setEnable(false);
        progressItem.setEnable(false);
        submitText.setVisibility(View.GONE);

        recordNameItem.setContent(info.getName());
        startDateItem.setContent(info.getBeginTime());
        finishDateItem.setContent(info.getEndTime());
        supervisorUnitItem.setContent(info.getSupervisionUnit());
        constructionUnitItem.setContent(info.getConstructionUnit());
        String process = info.getProcess();
        String position = info.getPosition();

        progressItem.setContent(TextUtils.isEmpty(process) ? "无" : process);
        positionItem.setContent(TextUtils.isEmpty(position) ? "无" : position);

        for (Fragment fragment : fragments) {
            if (fragment instanceof DataInterface) {
                ((DataInterface) fragment).updateUIForDetail(info);
            }
        }
    }

    @Override
    protected void initUI() {
        viewpager.post(new Runnable() {
            @Override
            public void run() {
                ViewGroup.LayoutParams layoutParams = viewpager.getLayoutParams();
                layoutParams.height = scrollView.getHeight() - tabLayout.getHeight();
                viewpager.setLayoutParams(layoutParams);
            }
        });
        viewpager.setOffscreenPageLimit(3);
        tabLayout.setupWithViewPager(viewpager);

        startDateItem.setTag(Calendar.getInstance());
        finishDateItem.setTag(Calendar.getInstance());

        supervisorUnitItem.setEnable(false);
        constructionUnitItem.setEnable(false);
    }

    @Override
    protected void initData() {
        presenter = new SideStationRecordPresenter(this, SupervisorModel.newInstance());
        addPresenter(presenter);
        imageTagCompressorPresenter = new ImageTagCompressorPresenter(this, CommonModel.newInstance());
        addPresenter(imageTagCompressorPresenter);
        queryFilePresenter = new QueryFilePresenter(this, CommonModel.newInstance());
        addPresenter(queryFilePresenter);

        boolean canEdit;
        id = getIntent().getStringExtra("id");
        if (id != null) {
            presenter.getSideStationRecordDetail(id);
            titleText.setText("旁站记录详情");

            canEdit = false;
            queryFilePresenter.queryFile(id);
        } else {
            titleText.setText("新增旁站记录");
            startDateItem.setContent(DateUtils.calendar2str(Calendar.getInstance(), DateUtils.PATTERN_DATE_2));
            presenter.getUnits(UserManager.getInstance().getProjectId());
            canEdit = true;
        }

        fragments.add(AddSideStationRecordPersonFragment.newInstance(canEdit));
        fragments.add(AddSideStationRecordMachineFragment.newInstance(canEdit));
        fragments.add(AddSideStationRecordMaterialFragment.newInstance(canEdit));
        fragments.add(AddSideStationRecordMethodEnvironmentFragment.newInstance());

        viewpager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
    }


    @OnClick({R.id.tv_back, R.id.item_start_date, R.id.item_finish_date, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.item_start_date:
                showTimeDialog(startDateItem);
                break;
            case R.id.item_finish_date:
                showTimeDialog(finishDateItem);
                break;
            case R.id.tv_submit:
                if (checkData()) {
                    doSubmit();
                }
                break;
            default:
                break;
        }
    }

    private void doSubmit() {
        for (Fragment fragment : fragments) {
            if (fragment instanceof DataInterface) {
                ((DataInterface) fragment).putData(info, fileList);
            }
        }

        info.setName(recordNameItem.getContent());
        info.setBeginTime(startDateItem.getContent());
        info.setEndTime(finishDateItem.getContent());
        info.setSupervisionUnit(supervisorUnitItem.getContent());
        info.setConstructionUnit(constructionUnitItem.getContent());
        info.setProjId(UserManager.getInstance().getProjectId());
        info.setCreateUserName(UserManager.getInstance().getUserName());
        info.setProcess(progressItem.getContent());
        info.setPosition(positionItem.getContent());

        if (fileList.isEmpty()) {
            postData();
        } else uploadFile();
    }

    private void uploadFile() {
        count = fileList.size();
        Set<Map.Entry<String, List<String>>> entries = fileList.entrySet();
        for (Map.Entry<String, List<String>> entry : entries) {
            List<String> value = entry.getValue();
            if (value != null && !value.isEmpty())
                imageTagCompressorPresenter.compressorImages(entry.getKey(), value);
        }
    }

    private void postData() {
        presenter.addSideStationRecord(info);
    }

    private boolean checkData() {
        if (TextUtils.isEmpty(recordNameItem.getContent())) {
            showError("请输入" + recordNameItem.getTitle());
            return false;
        }

        Calendar start = (Calendar) startDateItem.getTag();
        Calendar end = (Calendar) finishDateItem.getTag();
        if (end.compareTo(start) < 0 || end.compareTo(start) == 0) {
            showError("开始时间不能大于结束时间");
            return false;
        }

        if (TextUtils.isEmpty(supervisorUnitItem.getContent())) {
            showError("请输入" + supervisorUnitItem.getTitle());
            return false;
        }

        if (TextUtils.isEmpty(constructionUnitItem.getContent())) {
            showError("请输入" + constructionUnitItem.getTitle());
            return false;
        }


        return true;
    }

    private void showTimeDialog(final InputItemView inputItemView) {
        if (id != null) return;//详情

        if (isFastDoubleClick()) {
            return;
        }
        final Calendar calendar = (Calendar) inputItemView.getTag();
        PickerDialogHelper.showDatePicker(this, calendar, true, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                calendar.setTime(date);
                inputItemView.setTag(calendar);
                inputItemView.setContent(DateUtils.date2str(date, DateUtils.PATTERN_DATE_2));
            }
        });
    }

    @Override
    public void showSideStationRecordList(PageInfo<SiteStationRecorderManageInfo> datas) {

    }

    @Override
    public void dealDeleteRecordResult() {

    }

    @Override
    public void showSideStationRecordDetail(SiteStationRecorderManageDetailInfo info) {
        updateUIForDetail(info);
    }

    @Override
    public void dealAddRecordResult() {
        showError("添加记录成功");
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void showUnits(List<SideStationRecordUnit> info) {
        if (info != null && info.size() > 0) {
            for (SideStationRecordUnit sideStationRecordUnit : info) {
                String unitName = sideStationRecordUnit.getUnitName();
                String type = sideStationRecordUnit.getType();
                if (type.equals("108")) {//监理单位
                    supervisorUnitItem.setContent(unitName);
                    continue;
                }
                if (type.equals("110")) {//施工单位
                    constructionUnitItem.setContent(unitName);
                }
            }
        }
    }

    @Override
    public void showCompressFilePaths(final String tag, List<String> data) {
        List<File> files = new ArrayList<>();
        for (String path : data) {
            files.add(new File(path));
        }
        CommonModel.newInstance().upload(UserManager.getInstance().getProjectId(), files, new JsonCallback<ResponseData<List<FileEntity>>>(new TypeToken<ResponseData<List<FileEntity>>>() {
        }) {
            @Override
            public void onStart() {
                super.onStart();
                showProgress("文件上传中...");
            }

            @Override
            public void onFinish() {
                super.onFinish();
                hideProgress();
            }

            @Override
            protected void onError(int errcode, String errmsg) {
                showError(errmsg);
            }

            @Override
            public void onSuccess(ResponseData<List<FileEntity>> data) {
                List<FileEntity> result = data.getResult();
                for (FileEntity entity : result) {
                    entity.setType(tag);
                }
                entityList.addAll(result);
                count--;

                if (count == 0)
                    addFileData();
            }

        });
    }

    private void addFileData() {
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
        postData();
    }

    @Override
    public void showFiles(List<FileEntity> fileEntities) {
        if (fileEntities != null) {
            for (Fragment fragment : fragments) {
                if (fragment instanceof DataInterface) {
                    ((DataInterface) fragment).showFiles(fileEntities);
                }
            }
        }
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return TITLES.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }
    }

}
