package com.ljkj.qxn.wisdomsitepro.presenter.safe;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.safe.CheckRevocationContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.NullEntity;
import com.ljkj.qxn.wisdomsitepro.model.SafeModel;

import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;

public class CheckRevocationPresenter extends CheckRevocationContract.Presenter {

    public CheckRevocationPresenter(CheckRevocationContract.View view, SafeModel model) {
        super(view, model);
    }

    @Override
    public void qualityCheckRevocation(String id) {
        model.qualityCheckRevocation(id, new JsonCallback<ResponseData<NullEntity>>(new TypeToken<ResponseData<NullEntity>>() {
        }) {
            @Override
            public void onFinish() {
                super.onFinish();
                if (isAttach) {
                    view.hideProgress();
                }
            }

            @Override
            public void onStart() {
                super.onStart();
                if (isAttach) {
                    view.showProgress("撤回中...");
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
                        view.showQualityRevocation();
                    } else {
                        view.showQualityRevocation();
                    }
                }
            }
        });
    }

    @Override
    public void safeCheckRevocation(String id) {
        model.safeCheckRevocation(id, new JsonCallback<ResponseData<NullEntity>>(new TypeToken<ResponseData<NullEntity>>() {
        }) {
            @Override
            public void onFinish() {
                super.onFinish();
                if (isAttach) {
                    view.hideProgress();
                }
            }

            @Override
            public void onStart() {
                super.onStart();
                if (isAttach) {
                    view.showProgress("撤回中...");
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
                        view.showQualityRevocation();
                    } else {
                        view.showQualityRevocation();
                    }
                }
            }
        });
    }
}
