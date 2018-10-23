package com.ljkj.qxn.wisdomsitepro.contract.common;

import com.ljkj.qxn.wisdomsitepro.model.QualityModel;

import cdsp.android.presenter.BasePresenter;
import cdsp.android.ui.base.BaseView;

/**
 * 类描述：立即审批
 * 创建人：rjf
 * 创建时间：2018/3/19 15:35.
 * 修改人：
 * 修改时间
 * 修改备注
 */

public interface InspectionApprovalContract {

    interface View extends BaseView {


        /**
         * 执行提交审批后的结果
         */
        void showResult();

    }

    abstract class Presenter extends BasePresenter<View, QualityModel> {

        public Presenter(View view, QualityModel model) {
            super(view, model);
        }


        /**
         * @param id
         */
        public abstract void viewInspectionDetail(String id);
    }
}
