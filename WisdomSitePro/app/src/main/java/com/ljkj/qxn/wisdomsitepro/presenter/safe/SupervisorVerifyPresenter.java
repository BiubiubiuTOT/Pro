package com.ljkj.qxn.wisdomsitepro.presenter.safe;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.safe.SupervisorVerifyContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.NullEntity;
import com.ljkj.qxn.wisdomsitepro.model.SafeModel;

import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;

public class SupervisorVerifyPresenter extends SupervisorVerifyContract.Presenter {

    public SupervisorVerifyPresenter(SupervisorVerifyContract.View view, SafeModel model) {
        super(view, model);
    }

    @Override
    public void supervisorVerify(int checkType, int flag, String advice, final String verifyName, String id) {
        model.supervisorVerify(checkType, flag, advice, verifyName, id, new JsonCallback<ResponseData<NullEntity>>(new TypeToken<ResponseData<NullEntity>>() {
        }) {
            @Override
            public void onStart() {
                super.onStart();
                if (isAttach) {
                    view.showProgress("正在提交中...");
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
                    if (data.getErrcode() == 200) {
                        view.showVerifySuccess();
                    } else {
                        view.showError(data.getErrmsg());
                    }
                }
            }
        });
    }

}
