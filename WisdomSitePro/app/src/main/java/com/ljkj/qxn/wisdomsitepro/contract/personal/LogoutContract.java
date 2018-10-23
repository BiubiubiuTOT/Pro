package com.ljkj.qxn.wisdomsitepro.contract.personal;


import com.ljkj.qxn.wisdomsitepro.model.UserModel;

import cdsp.android.presenter.BasePresenter;
import cdsp.android.ui.base.BaseView;

/**
 * 注销登录
 * 创建人：lxx
 * 创建时间：2018/3/21 16:49
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public interface LogoutContract {
    interface View extends BaseView {
        void logoutSuccess();
    }

    abstract class Presenter extends BasePresenter<View, UserModel> {

        public Presenter(View view, UserModel model) {
            super(view, model);
        }

        public abstract void logout(String token);
    }

}
