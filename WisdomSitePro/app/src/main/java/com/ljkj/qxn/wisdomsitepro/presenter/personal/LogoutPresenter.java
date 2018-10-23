package com.ljkj.qxn.wisdomsitepro.presenter.personal;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.personal.LogoutContract;
import com.ljkj.qxn.wisdomsitepro.model.UserModel;

import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;

/**
 * 注销登录
 * 创建人：lxx
 * 创建时间：2018/3/21 16:52
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class LogoutPresenter extends LogoutContract.Presenter {
    public LogoutPresenter(LogoutContract.View view, UserModel model) {
        super(view, model);
    }

    @Override
    public void logout(String token) {
        model.logout(token, new JsonCallback<ResponseData<Void>>(new TypeToken<ResponseData<Void>>() {
        }) {

            @Override
            public void onStart() {
                if (isAttach) {
                    view.showProgress("注销登录中...");
                }
            }

            @Override
            protected void onError(int errcode, String errmsg) {
                if (isAttach) {
                    view.showError(errmsg);
                }
            }

            @Override
            public void onSuccess(ResponseData<Void> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.logoutSuccess();
                    } else {
                        view.showError(data.getErrmsg());
                    }
                }
            }

            @Override
            public void onFinish() {
                if (isAttach) {
                    view.hideProgress();
                }
            }
        });
    }
}
