package com.ljkj.qxn.wisdomsitepro.contract.project;

import com.ljkj.qxn.wisdomsitepro.data.FarmerBrandInfo;
import com.ljkj.qxn.wisdomsitepro.model.ProjectModel;

import cdsp.android.presenter.BasePresenter;
import cdsp.android.ui.base.BaseView;

public interface FarmerBrandContract {

    interface View extends BaseView {
        void showFarmerInfo(FarmerBrandInfo farmerBrandInfo);
    }

    abstract class Presenter extends BasePresenter<View, ProjectModel> {

        public Presenter(View view, ProjectModel model) {
            super(view, model);
        }

        public abstract void getFarmerInfo(String projId);
    }

}
