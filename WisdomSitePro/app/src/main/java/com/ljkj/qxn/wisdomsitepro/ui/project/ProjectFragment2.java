package com.ljkj.qxn.wisdomsitepro.ui.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.Utils.H5Helper;
import com.ljkj.qxn.wisdomsitepro.contract.project.ProjectStatisticsContract;
import com.ljkj.qxn.wisdomsitepro.contract.project.ProjectWeatherContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.ProjectStatisticsInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.WeatherInfo;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.model.ProjectModel;
import com.ljkj.qxn.wisdomsitepro.presenter.project.ProjectStatisticsPresenter;
import com.ljkj.qxn.wisdomsitepro.presenter.project.ProjectWeatherPresenter;
import com.ljkj.qxn.wisdomsitepro.ui.contacts.ProjectOverviewActivity;
import com.ljkj.qxn.wisdomsitepro.ui.project.view.ProjectLabourView;
import com.ljkj.qxn.wisdomsitepro.ui.project.view.ProjectSpecialEquipmentView;
import com.ljkj.qxn.wisdomsitepro.ui.project.view.ProjectTeamPersonView;
import com.ljkj.qxn.wisdomsitepro.ui.project.view.ProjectWeatherView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;
import cdsp.android.ui.base.BaseFragment;

/**
 * 类描述：项目
 * 创建人：lxx
 * 创建时间：2018/6/19
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class ProjectFragment2 extends BaseFragment implements ProjectWeatherContract.View, ProjectStatisticsContract.View {
    public static final String TAG = ProjectFragment2.class.getName();

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    @BindView(R.id.tv_title)
    TextView titleText;

    @BindView(R.id.tv_back)
    TextView backText;

    @BindView(R.id.project_weather_view)
    ProjectWeatherView weatherView;

    @BindView(R.id.project_labour_view)
    ProjectLabourView projectLabourView;

    @BindView(R.id.project_team_person_view)
    ProjectTeamPersonView projectTeamPersonView;

    @BindView(R.id.project_special_equipment)
    ProjectSpecialEquipmentView projectSpecialEquipmentView;

    private ProjectWeatherPresenter weatherPresenter;
    private ProjectStatisticsPresenter statisticsPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_project2, container, false);
        return view;
    }

    @Override
    protected void initUI() {
        backText.setVisibility(View.GONE);
        titleText.setText("项目");
        refreshLayout.setEnableLoadMore(false);
    }

    @Override
    protected void initData() {
        weatherPresenter = new ProjectWeatherPresenter(this, ProjectModel.newInstance());
        statisticsPresenter = new ProjectStatisticsPresenter(this, ProjectModel.newInstance());
        addPresenter(weatherPresenter);
        addPresenter(statisticsPresenter);
        initListener();
        refreshLayout.autoRefresh();
    }

    private void initListener() {
        UserManager.getInstance().addOnProjectChangeListener(onProjectChangeListener);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                weatherPresenter.getWeather(UserManager.getInstance().getProjectId());
                statisticsPresenter.getProjectStatistics(UserManager.getInstance().getProjectId());
            }
        });
        weatherView.setOnBarometerListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                H5Helper.toBarometer(getContext(), "晴雨表", UserManager.getInstance().getProjectId());
            }
        });
        weatherView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ProjectOverviewActivity.class));
            }
        });
    }

    @Override
    public void hideProgress() {
        super.hideProgress();
        refreshLayout.finishRefresh();
    }

    private UserManager.OnProjectChangeListener onProjectChangeListener = new UserManager.OnProjectChangeListener() {
        @Override
        public void onProjectChange(String projectId, String name) {
            refreshLayout.autoRefresh();
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        UserManager.getInstance().removeOnProjectChangeListener(onProjectChangeListener);
    }

    @Override
    public void showWeather(WeatherInfo weatherInfo) {
        if (weatherInfo.heWeathers.size() > 0) {
            weatherView.setData(weatherInfo.heWeathers.get(0).now);
        }
    }

    @Override
    public void showProjectStatistics(ProjectStatisticsInfo data) {
        projectLabourView.setData(data.zcgrs, data.rcry, data.zcry, data.cql);
        weatherView.setProjectInfo(data.gq, data.sgts);
        projectTeamPersonView.setData(data.bzzcrs);

    }
}
