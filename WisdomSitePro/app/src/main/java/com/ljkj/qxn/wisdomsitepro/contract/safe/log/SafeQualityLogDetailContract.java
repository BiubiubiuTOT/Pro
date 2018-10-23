package com.ljkj.qxn.wisdomsitepro.contract.safe.log;

import com.ljkj.qxn.wisdomsitepro.data.entity.SafeQualityLogDetail;
import com.ljkj.qxn.wisdomsitepro.model.SafeQualityLogModel;

import cdsp.android.presenter.BasePresenter;
import cdsp.android.ui.base.BaseView;

/**
 * 类描述：安全/质量日志 详情
 * 创建人：lxx
 * 创建时间：2018/9/18
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public interface SafeQualityLogDetailContract {

    interface View extends BaseView {
        void showSafeLogDetail(SafeQualityLogDetail detail);

        void showQualityDetail(SafeQualityLogDetail detail);
    }

    abstract class Presenter extends BasePresenter<View, SafeQualityLogModel> {
        public Presenter(View view, SafeQualityLogModel model) {
            super(view, model);
        }

        public abstract void getSafeLogDetail(String id);

        public abstract void getQualityLogDetail(String id);
    }


}
