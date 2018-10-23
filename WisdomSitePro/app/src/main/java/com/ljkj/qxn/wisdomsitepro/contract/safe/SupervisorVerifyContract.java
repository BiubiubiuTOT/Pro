package com.ljkj.qxn.wisdomsitepro.contract.safe;

import com.ljkj.qxn.wisdomsitepro.model.SafeModel;

import cdsp.android.presenter.BasePresenter;
import cdsp.android.ui.base.BaseView;

public interface SupervisorVerifyContract {

    interface View extends BaseView {
        void showVerifySuccess();
    }

    abstract class Presenter extends BasePresenter<View, SafeModel> {

        public Presenter(View view, SafeModel model) {
            super(view, model);
        }

        public abstract void supervisorVerify(int checkType, int flag, String advice, String verifyName, String id);
    }
}
