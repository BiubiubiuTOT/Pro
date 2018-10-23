package com.ljkj.qxn.wisdomsitepro.presenter.personal;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.personal.UserInfoContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.UserInfo;
import com.ljkj.qxn.wisdomsitepro.model.UserModel;

import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;

/**
 * 类描述：用户信息
 * 创建人：liufei
 * 创建时间：2018/3/12 14:39
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class UserInfoPresenter extends UserInfoContract.Presenter {

    public UserInfoPresenter(UserInfoContract.View view, UserModel model) {
        super(view, model);
    }

    @Override
    public void getUserInfo() {
        model.getUserInfo(new JsonCallback<ResponseData<UserInfo>>(new TypeToken<ResponseData<UserInfo>>() {
        }) {
            @Override
            public void onFinish() {
                super.onFinish();
                if (isAttach) {
                    view.hideProgress();
                }
            }

            @Override
            public void onSuccess(ResponseData<UserInfo> data) {

                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showLoginInfo(data.getResult());
                    } else {
                        view.showError(data.getErrmsg());
                    }
                }
            }

            @Override
            protected void onError(int errcode, String errmsg) {
                if (isAttach) {
                    view.showError(errmsg);
                }
            }
        });
    }
}
