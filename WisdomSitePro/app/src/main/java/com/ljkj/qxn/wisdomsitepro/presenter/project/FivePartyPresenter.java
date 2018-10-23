package com.ljkj.qxn.wisdomsitepro.presenter.project;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.project.FivePartyContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.FivePartyInfo;
import com.ljkj.qxn.wisdomsitepro.model.ProjectModel;

import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;

/**
 * Created by niujingtong on 2018/7/10.
 * 模块：
 */
public class FivePartyPresenter extends FivePartyContract.Presenter {
    public FivePartyPresenter(FivePartyContract.View view, ProjectModel model) {
        super(view, model);
    }

    @Override
    public void getFivePartyInfo(String proId) {
        model.getFivePartyInfo(proId, new JsonCallback<ResponseData<FivePartyInfo>>(new TypeToken<ResponseData<FivePartyInfo>>() {
        }) {
            @Override
            public void onSuccess(ResponseData<FivePartyInfo> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showFivePartyInfo(data.getResult());
                    } else {
                        view.showError(data.getErrmsg());
                    }
                }
            }


            @Override
            protected void onError(int errcode, String errmsg) {
                if (isAttach)
                    view.showError(errmsg);
            }

            @Override
            public void onFinish() {
                super.onFinish();
                if (isAttach) {
                    view.hideProgress();
                }
            }
        });
    }

    @Override
    public void getSubInfo(String proId) {
        model.getSubInfo(proId, new JsonCallback<ResponseData<FivePartyInfo>>(new TypeToken<ResponseData<FivePartyInfo>>() {
        }) {
            @Override
            public void onSuccess(ResponseData<FivePartyInfo> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showSubInfo(data.getResult());
                    } else {
                        view.showError(data.getErrmsg());
                    }
                }
            }


            @Override
            protected void onError(int errcode, String errmsg) {
                if (isAttach)
                    view.showError(errmsg);
            }

            @Override
            public void onFinish() {
                super.onFinish();
                if (isAttach) {
                    view.hideProgress();
                }
            }
        });
    }
}
