package com.ljkj.qxn.wisdomsitepro.contract.project;

import com.ljkj.qxn.wisdomsitepro.data.entity.PageInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.ProjectDoneProgress;
import com.ljkj.qxn.wisdomsitepro.data.entity.ProjectInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.ProjectProgressInfo;
import com.ljkj.qxn.wisdomsitepro.model.ProjectModel;

import cdsp.android.http.JsonCallback;
import cdsp.android.presenter.BasePresenter;
import cdsp.android.ui.base.BaseView;

/**
 * Created by niujingtong on 2018/7/10.
 * 模块：
 */
public interface ProjectInfoContract {
    interface View extends BaseView {
        /**
         * 显示 项目编号，进度
         *
         * @param info
         */
        void showProjectInfo(ProjectDoneProgress info);
    }

    abstract class Presenter extends BasePresenter<View, ProjectModel> {

        public Presenter(View view, ProjectModel model) {
            super(view, model);
        }

        public abstract void getProjectInfo(String proId);
    }
}
