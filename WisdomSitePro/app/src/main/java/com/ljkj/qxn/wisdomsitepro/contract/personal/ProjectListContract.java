package com.ljkj.qxn.wisdomsitepro.contract.personal;

import com.ljkj.qxn.wisdomsitepro.data.entity.ProjectInfo;
import com.ljkj.qxn.wisdomsitepro.model.UserModel;

import java.util.List;

import cdsp.android.presenter.BasePresenter;
import cdsp.android.ui.base.BaseView;


public interface ProjectListContract {

    interface View extends BaseView {

        /**
         * 显示当前用户能选择的项目列表
         */
        void showProjectList(List<ProjectInfo> data);

        void showProject(List<ProjectInfo> list);
    }

    abstract class Presenter extends BasePresenter<View, UserModel> {

        public Presenter(View view, UserModel model) {
            super(view, model);
        }

        /**
         * 获取项目列表
         */
        public abstract void getProjectList();


        public abstract void getProject(String projId);
    }
}
