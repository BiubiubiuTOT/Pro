package com.ljkj.qxn.wisdomsitepro.contract.project;

import com.ljkj.qxn.wisdomsitepro.data.SafeQualityStatisticsInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.EnvironmentStatisticsInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.LaborCountInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.ProjectHomeInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.RealTimePersonStatisticsInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.RealTimeTeamStatisticsInfo;
import com.ljkj.qxn.wisdomsitepro.model.ProjectModel;

import java.util.List;

import cdsp.android.presenter.BasePresenter;
import cdsp.android.ui.base.BaseView;

/**
 * 类描述：项目首页信息
 * 创建人：lxx
 * 创建时间：2018/9/27
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public interface ProjectHomeInfoContract {
    interface View extends BaseView {
        void showProjectHomeInfo(ProjectHomeInfo projectHomeInfo);

        void showLaborCountInfo(LaborCountInfo info);

        void showRealTimePersonStatistics(List<RealTimePersonStatisticsInfo> list);

        void showRealTimeTeamStatistics(List<RealTimeTeamStatisticsInfo> list);

        void showSafeQualityStatistics(SafeQualityStatisticsInfo info);

        void showEnvironmentStatistics(EnvironmentStatisticsInfo info);
    }

    abstract class Presenter extends BasePresenter<View, ProjectModel> {
        public Presenter(View view, ProjectModel model) {
            super(view, model);
        }

        public abstract void getProjectHomeInfo(String proId);

        public abstract void getLaborCount(String projId);

        public abstract void getRealTimePersonStatistics(String projId);

        public abstract void getRealTimeTeamStatistics(String projId);

        public abstract void getSafeQualityStatistics(String projId);

        public abstract void getEnvironmentStatistics(String projId);
    }

}
