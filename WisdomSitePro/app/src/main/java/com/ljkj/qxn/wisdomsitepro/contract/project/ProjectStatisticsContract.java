package com.ljkj.qxn.wisdomsitepro.contract.project;

import com.ljkj.qxn.wisdomsitepro.data.entity.ProjectStatisticsInfo;
import com.ljkj.qxn.wisdomsitepro.model.ProjectModel;

import cdsp.android.presenter.BasePresenter;
import cdsp.android.ui.base.BaseView;

/**
 * 类描述：项目统计
 * 创建人：lxx
 * 创建时间：2018/6/28
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public interface ProjectStatisticsContract {

    interface View extends BaseView {
        void showProjectStatistics(ProjectStatisticsInfo data);
    }

    abstract class Presenter extends BasePresenter<ProjectStatisticsContract.View, ProjectModel> {
        public Presenter(ProjectStatisticsContract.View view, ProjectModel model) {
            super(view, model);
        }

        public abstract void getProjectStatistics(String projectId);
    }

}
