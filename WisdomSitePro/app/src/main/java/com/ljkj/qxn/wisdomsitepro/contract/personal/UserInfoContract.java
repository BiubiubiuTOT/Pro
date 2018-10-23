package com.ljkj.qxn.wisdomsitepro.contract.personal;

import com.ljkj.qxn.wisdomsitepro.data.entity.UserInfo;
import com.ljkj.qxn.wisdomsitepro.model.UserModel;

import cdsp.android.presenter.BasePresenter;
import cdsp.android.ui.base.BaseView;

/**
 * 类描述：用户信息
 * 创建人：liufei
 * 创建时间：2018/3/12 14:37
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public interface UserInfoContract {


    interface View extends BaseView {

        /**
         * 显示
         *
         */
        void showLoginInfo(UserInfo data);
    }

    abstract class Presenter extends BasePresenter<View, UserModel> {

        public Presenter(View view, UserModel model) {
            super(view, model);
        }


        /**
         * 获取
         */
        public abstract void getUserInfo();
    }


}
