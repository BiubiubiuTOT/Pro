package com.ljkj.qxn.wisdomsitepro.contract.project;

import com.ljkj.qxn.wisdomsitepro.data.ProjectOverViewInfo;
import com.ljkj.qxn.wisdomsitepro.model.ProjectModel;

import cdsp.android.presenter.BasePresenter;
import cdsp.android.ui.base.BaseView;

public interface ProjectOverViewContract {

    interface View extends BaseView {
        void showProjectOverview(ProjectOverViewInfo info);
    }

    abstract class Presenter extends BasePresenter<View, ProjectModel> {

        public Presenter(View view, ProjectModel model) {
            super(view, model);
        }

        public abstract void getProjectOverView(String projId);
    }

}
