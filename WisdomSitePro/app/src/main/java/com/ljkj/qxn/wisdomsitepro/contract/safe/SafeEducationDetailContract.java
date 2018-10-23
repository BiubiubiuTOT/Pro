package com.ljkj.qxn.wisdomsitepro.contract.safe;

import com.ljkj.qxn.wisdomsitepro.data.entity.SafeEducationDetailInfo;
import com.ljkj.qxn.wisdomsitepro.model.SafeModel;

import cdsp.android.presenter.BasePresenter;
import cdsp.android.ui.base.BaseView;

/**
 * 类描述：安全教育详情
 * 创建人：mhl
 * 创建时间：2018/3/14 11:47
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public interface SafeEducationDetailContract {

    interface View extends BaseView {

        void showSafeEduDetail(SafeEducationDetailInfo data);
    }

    abstract class Presenter extends BasePresenter<View, SafeModel> {
        public Presenter(View view, SafeModel model) {
            super(view, model);
        }

        public abstract void viewSafeEduDetail(String id);
    }
}
