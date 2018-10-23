package com.ljkj.qxn.wisdomsitepro.contract.project;

import com.ljkj.qxn.wisdomsitepro.data.entity.FivePartyInfo;
import com.ljkj.qxn.wisdomsitepro.model.ProjectModel;

import cdsp.android.presenter.BasePresenter;
import cdsp.android.ui.base.BaseView;

/**
 * Created by niujingtong on 2018/7/10.
 * 模块：
 */
public interface FivePartyContract {
    interface View extends BaseView {
        /**
         * 显示 项目编号，进度
         *
         * @param info
         */
        void showFivePartyInfo(FivePartyInfo info);

        void showSubInfo(FivePartyInfo info);
    }

    abstract class Presenter extends BasePresenter<View, ProjectModel> {

        public Presenter(View view, ProjectModel model) {
            super(view, model);
        }

        public abstract void getFivePartyInfo(String proId);

        public abstract void getSubInfo(String proId);
    }
}
