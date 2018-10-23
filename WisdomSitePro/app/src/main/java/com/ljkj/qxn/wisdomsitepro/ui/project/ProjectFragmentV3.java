package com.ljkj.qxn.wisdomsitepro.ui.project;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.Utils.H5Helper;
import com.ljkj.qxn.wisdomsitepro.Utils.widget.HorizontalProgressBar;
import com.ljkj.qxn.wisdomsitepro.contract.project.ProjectHomeInfoContract;
import com.ljkj.qxn.wisdomsitepro.data.SafeQualityStatisticsInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.EnvironmentStatisticsInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.LaborCountInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.ProjectHomeInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.RealTimePersonStatisticsInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.RealTimeTeamStatisticsInfo;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.model.ProjectModel;
import com.ljkj.qxn.wisdomsitepro.presenter.project.ProjectHomeInfoPresenter;
import com.ljkj.qxn.wisdomsitepro.ui.application.VideoMonitorActivity;
import com.ljkj.qxn.wisdomsitepro.ui.contacts.NineBrandOneChartActivity;
import com.ljkj.qxn.wisdomsitepro.ui.project.adapter.ProjectInfoAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseFragment;

/**
 * 类描述：项目
 * 创建人：lxx
 * 创建时间：2018/8/21
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class ProjectFragmentV3 extends BaseFragment implements ProjectHomeInfoContract.View {
    public static final String TAG = ProjectFragmentV3.class.getName();
    private static final String[] FRAGMENT_TITLE = {"人数", "班组", "安全/质量", "环境"};

    @BindView(R.id.tv_title)
    TextView titleText;

    @BindView(R.id.progress_bar)
    HorizontalProgressBar horizontalProgressBar;

    @BindView(R.id.tv_start_date)
    TextView startDateText; //开工日期

    @BindView(R.id.tv_remain_days)
    TextView remainDaysText; //剩余天数

    @BindView(R.id.tv_finish_date)
    TextView finishDateText; //竣工日期

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    @BindView(R.id.viewpager)
    ViewPager viewpager;

    @BindView(R.id.web_view_container)
    ViewGroup webViewContainer;

    private List<Fragment> fragments = new ArrayList<>();
    private ProjectInfoAdapter projectInfoAdapter;
    private ProjectHomeInfoPresenter projectHomeInfoPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_project3, container, false);
        return view;
    }

    @Override
    protected void initUI() {
        titleText.setText(UserManager.getInstance().getProjectName());
        refreshLayout.setEnableLoadMore(false);

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        recyclerView.setAdapter(projectInfoAdapter = new ProjectInfoAdapter(null));
    }

    @Override
    protected void initData() {
        fragments.add(PresencePersonsChartFragment.newInstance());
        fragments.add(PresenceGroupChartFragment.newInstance());
        fragments.add(SafetyQualityChartFragment.newInstance());
        fragments.add(EnvironmentDataChartFragment.newInstance());

        projectHomeInfoPresenter = new ProjectHomeInfoPresenter(this, ProjectModel.newInstance());
        addPresenter(projectHomeInfoPresenter);

        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getChildFragmentManager());
        viewpager.setOffscreenPageLimit(3);
        viewpager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewpager);
        viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                projectHomeInfoPresenter.getProjectHomeInfo(UserManager.getInstance().getProjectId());
                projectHomeInfoPresenter.getLaborCount(UserManager.getInstance().getProjectId());
                projectHomeInfoPresenter.getRealTimePersonStatistics(UserManager.getInstance().getProjectId());
                projectHomeInfoPresenter.getRealTimeTeamStatistics(UserManager.getInstance().getProjectId());
                projectHomeInfoPresenter.getSafeQualityStatistics(UserManager.getInstance().getProjectId());
                projectHomeInfoPresenter.getEnvironmentStatistics(UserManager.getInstance().getProjectId());
            }
        });
        refreshLayout.autoRefresh();
    }

    private void setRemainDays(String days) {
        SpannableString spannableString = new SpannableString(days);
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#B5F877")), 2, days.length() - 1, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        int size = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 18, getResources().getDisplayMetrics());
        spannableString.setSpan(new AbsoluteSizeSpan(size), 2, days.length() - 1, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        remainDaysText.setText(spannableString);
    }

    @OnClick({R.id.tv_nine_brand, R.id.tv_project_progress, R.id.tv_video_monitor, R.id.tv_barometer})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_nine_brand: //九牌一图
                startActivity(new Intent(getActivity(), NineBrandOneChartActivity.class));
                break;
            case R.id.tv_project_progress: //工程形象进度展示
                startActivity(new Intent(getActivity(), ProjectProgressActivity.class));
                break;
            case R.id.tv_video_monitor: //实时视频监控
                VideoMonitorActivity.startActivity(getActivity());
                break;
            case R.id.tv_barometer: //晴雨表
                H5Helper.toBarometer(getContext(), "晴雨表", "0000000064d279b80164d9aad41121e4");
                break;
            default:
                break;
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void showProjectHomeInfo(ProjectHomeInfo projectHomeInfo) {
        int percent = 0;
        try {
            percent = Integer.parseInt(projectHomeInfo.percent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        horizontalProgressBar.setProgress(percent);
        startDateText.setText("开工：" + projectHomeInfo.startDate);
        setRemainDays("剩余 " + projectHomeInfo.remainDay + " 天");
        finishDateText.setText("竣工：" + projectHomeInfo.endDate);
    }

    @Override
    public void showLaborCountInfo(LaborCountInfo info) {
        List<ProjectInfoAdapter.Data> list = new ArrayList<>();
        list.add(new ProjectInfoAdapter.Data("项目实时在场人数", info.actualCount + " 人", Color.parseColor("#F7834F")));
        list.add(new ProjectInfoAdapter.Data("项目历史累计人数", info.historyCount + " 人", Color.parseColor("#58A5FE")));
        list.add(new ProjectInfoAdapter.Data("劳务人员今日出勤率", info.todayRate + " %", Color.parseColor("#A0AFBF")));
        list.add(new ProjectInfoAdapter.Data("月平均出勤人数", info.laborEverDayNum + " 人", Color.parseColor("#94C45E")));
        list.add(new ProjectInfoAdapter.Data("今日进场劳务人数", info.inCount + " 人", Color.parseColor("#E3B72F")));
        list.add(new ProjectInfoAdapter.Data("今日出场劳务人数", info.outCount + " 人", Color.parseColor("#F7834F")));
        projectInfoAdapter.setNewData(list);
    }

    @Override
    public void showRealTimePersonStatistics(List<RealTimePersonStatisticsInfo> list) {
        if (list == null || list.size() == 0) {
            return;
        }

        PresencePersonsChartFragment fragment = (PresencePersonsChartFragment) fragments.get(0);
        fragment.showData(list);
    }

    @Override
    public void showRealTimeTeamStatistics(List<RealTimeTeamStatisticsInfo> list) {
        if (list == null || list.size() == 0) {
            return;
        }

        for (RealTimeTeamStatisticsInfo info : list) {
            if (info.count > 0) {
                PresenceGroupChartFragment fragment = (PresenceGroupChartFragment) fragments.get(1);
                fragment.showData(list);
                break;
            }
        }
        
    }

    @Override
    public void showSafeQualityStatistics(SafeQualityStatisticsInfo info) {
        if (info == null) {
            return;
        }
        SafetyQualityChartFragment fragment = (SafetyQualityChartFragment) fragments.get(2);
        fragment.showData(info);
    }

    @Override
    public void showEnvironmentStatistics(EnvironmentStatisticsInfo info) {
        if (info == null) {
            return;
        }
        EnvironmentDataChartFragment fragment = (EnvironmentDataChartFragment) fragments.get(3);
        fragment.showData(info, false);
    }

    @Override
    public void hideProgress() {
        super.hideProgress();
        refreshLayout.finishRefresh();
    }

    private class MyFragmentPagerAdapter extends FragmentPagerAdapter {
        MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return FRAGMENT_TITLE.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return FRAGMENT_TITLE[position];
        }
    }

    private void test() {
        //TODO
//        final FragmentManager fragmentManager = getChildFragmentManager();
//        final WebViewFragment fragment = WebViewFragment.newInstance("http://111.85.152.35:18071/CO-CTMS/m/laborController.do?bimApplication");
//        fragmentManager.beginTransaction()
//                .replace(R.id.web_view_container, fragment)
//                .commit();
//        fragment.setCallback(new WebViewFragment.Callback() {
//            @Override
//            public void loadError(int code, String msg) {
//                if (code == -2) {
//                    webViewContainer.setVisibility(View.GONE);
//                }
//            }
//
//            @Override
//            public void loadSuccess() {
//                webViewContainer.setVisibility(View.VISIBLE);
//            }
//        });
        webViewContainer.setVisibility(View.GONE);
        refreshLayout.finishRefresh(1000);
    }

}
