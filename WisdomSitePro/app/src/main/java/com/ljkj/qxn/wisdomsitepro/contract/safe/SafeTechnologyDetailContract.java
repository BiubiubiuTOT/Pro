package com.ljkj.qxn.wisdomsitepro.contract.safe;

import com.ljkj.qxn.wisdomsitepro.data.entity.SafeTechnologyDetail;
import com.ljkj.qxn.wisdomsitepro.model.SafeModel;

import cdsp.android.presenter.BasePresenter;
import cdsp.android.ui.base.BaseView;

/**
 * 类描述：安全技术交底详情
 * 创建人：lxx
 * 创建时间：2018/9/25
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public interface SafeTechnologyDetailContract {

    interface View extends BaseView {
        void showSafeTechnologyDetail(SafeTechnologyDetail detail);
    }

    abstract class Presenter extends BasePresenter<View, SafeModel> {
        public Presenter(View view, SafeModel model) {
            super(view, model);
        }

        public abstract void getSafeTechnologyDetail(String id);
    }

}
