package com.ljkj.qxn.wisdomsitepro.contract.safe;

import com.ljkj.qxn.wisdomsitepro.model.SafeModel;

import cdsp.android.presenter.BasePresenter;
import cdsp.android.ui.base.BaseView;

public interface CheckRevocationContract {

    interface View extends BaseView {
        void showSafeRevocation();

        void showQualityRevocation();
    }

    abstract class Presenter extends BasePresenter<View, SafeModel> {
        public Presenter(View view, SafeModel model) {
            super(view, model);
        }

        public abstract void qualityCheckRevocation(String id);

        public abstract void safeCheckRevocation(String id);
    }


}
