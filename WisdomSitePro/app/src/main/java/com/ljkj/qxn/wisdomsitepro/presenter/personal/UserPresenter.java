package com.ljkj.qxn.wisdomsitepro.presenter.personal;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.personal.UserContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.AuthorityInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.LoginInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.NullEntity;
import com.ljkj.qxn.wisdomsitepro.model.UserModel;

import java.util.List;

import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;

/**
 * 类描述：用户业务
 * 创建人：mhl
 * 创建时间：2018/2/27 10:52
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class UserPresenter extends UserContract.Presenter {

    public UserPresenter(UserContract.View view, UserModel model) {
        super(view, model);
    }

    @Override
    public void login(String name, String password) {
        model.loginV2(name, password, new JsonCallback<ResponseData<LoginInfo>>(new TypeToken<ResponseData<LoginInfo>>() {
        }) {

            @Override
            public void onStart() {
                super.onStart();
                if (isAttach) {
                    view.showProgress("登录中...");
                }
            }

            @Override
            public void onFinish() {
                super.onFinish();
                if (isAttach) {
                    view.hideProgress();
                }
            }

            @Override
            public void onSuccess(ResponseData<LoginInfo> data) {

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

    @Override
    public void updatePwd(String userId, String projId, String newPassword, String password) {
        model.updatePwd(userId, projId, newPassword, password, new JsonCallback<ResponseData<NullEntity>>(new TypeToken<ResponseData<NullEntity>>() {
        }) {
            @Override
            public void onStart() {
                super.onStart();
                if (isAttach) {
                    view.showProgress("修改中...");
                }
            }

            @Override
            public void onFinish() {
                super.onFinish();
                if (isAttach) {
                    view.hideProgress();
                }
            }

            @Override
            protected void onError(int errcode, String errmsg) {
                if (isAttach) {
                    view.showError(errmsg);
                }
            }

            @Override
            public void onSuccess(ResponseData<NullEntity> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showUpdatePwdInfo();
                    } else {
                        view.showError(data.getErrmsg());
                    }
                }
            }

        });
    }

    @Override
    public void getAuthority(String roleId, String userId) {
        model.getAuthority(roleId, userId, new JsonCallback<ResponseData<List<AuthorityInfo>>>(new TypeToken<ResponseData<List<AuthorityInfo>>>() {
        }) {

            @Override
            public void onStart() {
                super.onStart();
                if (isAttach) {
                    view.showProgress("权限获取中...");
                }
            }

            @Override
            public void onFinish() {
                super.onFinish();
                if (isAttach) {
                    view.hideProgress();
                }
            }

            @Override
            protected void onError(int errcode, String errmsg) {
                if (isAttach) {
                    view.showError(errmsg);
                }
            }

            @Override
            public void onSuccess(ResponseData<List<AuthorityInfo>> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showAuthority(data.getResult());
                    } else {
                        view.showError(data.getErrmsg());
                    }
                }
            }

        });
    }

}
