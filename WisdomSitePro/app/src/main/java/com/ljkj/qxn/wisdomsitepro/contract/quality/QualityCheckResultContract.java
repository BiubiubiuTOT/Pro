package com.ljkj.qxn.wisdomsitepro.contract.quality;

import com.ljkj.qxn.wisdomsitepro.data.entity.QualityCheckResultDetail;
import com.ljkj.qxn.wisdomsitepro.model.QualityModel;

import cdsp.android.presenter.BasePresenter;
import cdsp.android.ui.base.BaseView;

/**
 * 类描述：检查结果详情
 * 创建人：lxx
 * 创建时间：2018/3/15 11:32
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public interface QualityCheckResultContract {

    interface View extends BaseView {
        void showCheckResultDetail(QualityCheckResultDetail detail);
    }

    abstract class Presenter extends BasePresenter<View, QualityModel> {

        public Presenter(View view, QualityModel model) {
            super(view, model);
        }

        public abstract void getCheckResultDetail(String id, String proId);
    }

}
