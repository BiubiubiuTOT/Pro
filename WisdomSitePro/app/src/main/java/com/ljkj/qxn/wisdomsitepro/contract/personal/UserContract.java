package com.ljkj.qxn.wisdomsitepro.contract.personal;

import com.ljkj.qxn.wisdomsitepro.data.entity.AuthorityInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.LoginInfo;
import com.ljkj.qxn.wisdomsitepro.model.UserModel;

import java.util.List;

import cdsp.android.presenter.BasePresenter;
import cdsp.android.ui.base.BaseView;

/**
 * 类描述：
 * 创建人：mhl
 * 创建时间：2018/2/27 10:46
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public interface UserContract {


    interface View extends BaseView {

        /**
         * 显示登录结果
         *
         * @param data
         */
        void showLoginInfo(LoginInfo data);

        void showUpdatePwdInfo();

        void showAuthority(List<AuthorityInfo> list);
    }

    abstract class Presenter extends BasePresenter<View, UserModel> {

        public Presenter(View view, UserModel model) {
            super(view, model);
        }


        /**
         * 用户登录
         *
         * @param name
         * @param password
         */
        public abstract void login(String name, String password);


        public abstract void updatePwd(String userId, String projId, String newPassword, String password);

        public abstract void getAuthority(String roleId, String userId);
    }


}
