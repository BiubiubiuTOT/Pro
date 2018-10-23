package com.ljkj.qxn.wisdomsitepro.contract.project;

import com.ljkj.qxn.wisdomsitepro.data.entity.ProjectProgressAddInfo;
import com.ljkj.qxn.wisdomsitepro.model.ProjectModel;

import cdsp.android.presenter.BasePresenter;
import cdsp.android.ui.base.BaseView;

/**
 * 工程形象进度-新增
 * 创建人：lxx
 * 创建时间：2018/3/21 11:33
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public interface ProjectProgressAddContract {

    interface View extends BaseView {
        void delaAddProgressResult( );
    }

    abstract class Presenter extends BasePresenter<View, ProjectModel> {

        public Presenter(View view, ProjectModel model) {
            super(view, model);
        }

        public abstract void addProjectProgress(ProjectProgressAddInfo info);
    }

}
