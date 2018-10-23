package com.ljkj.qxn.wisdomsitepro.contract.safe;

import com.ljkj.qxn.wisdomsitepro.data.entity.CheckDetail;
import com.ljkj.qxn.wisdomsitepro.model.SafeModel;

import cdsp.android.presenter.BasePresenter;
import cdsp.android.ui.base.BaseView;

/**
 * 类描述：检查详情
 * 创建人：lxx
 * 创建时间：2018/9/7
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public interface CheckDetailContract {
    interface View extends BaseView {
        void showSafeCheckDetail(CheckDetail checkDetail);
        void showQualityCheckDetail(CheckDetail checkDetail);
        void showSupervisorCheckDetail(CheckDetail checkDetail);
    }

    abstract class Presenter extends BasePresenter<CheckDetailContract.View, SafeModel> {
        public Presenter(CheckDetailContract.View view, SafeModel model) {
            super(view, model);
        }

        public abstract void getSafeCheckDetail(String id);

        public abstract void getQualityCheckDetail(String id);

        public abstract void getSupervisorCheckDetail(String id);
    }

}
