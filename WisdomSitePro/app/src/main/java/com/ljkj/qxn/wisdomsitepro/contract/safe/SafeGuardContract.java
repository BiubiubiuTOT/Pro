package com.ljkj.qxn.wisdomsitepro.contract.safe;

import com.ljkj.qxn.wisdomsitepro.data.entity.SafeGuardInfo;
import com.ljkj.qxn.wisdomsitepro.model.SafeModel;

import cdsp.android.presenter.BasePresenter;
import cdsp.android.ui.base.BaseView;

/**
 * 类描述：安全防护
 * 创建人：rjf
 * 创建时间：2018/3/13 15:24.
 * 修改人：
 * 修改时间
 * 修改备注
 */

public interface SafeGuardContract {

    interface View extends BaseView {
        void showSafeGuardInfo(SafeGuardInfo data);
    }

    abstract class Presenter extends BasePresenter<View, SafeModel> {

        public Presenter(View view, SafeModel model) {
            super(view, model);
        }

        /**
         * 安全防护列表
         *
         * @param proId        项目id
         */
        public abstract void getSafeGuardInfo(String proId);
    }
}
