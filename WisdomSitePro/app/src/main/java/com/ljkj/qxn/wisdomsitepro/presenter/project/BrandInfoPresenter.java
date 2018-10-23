package com.ljkj.qxn.wisdomsitepro.presenter.project;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.project.BrandInfoContract;
import com.ljkj.qxn.wisdomsitepro.data.BrandInfo;
import com.ljkj.qxn.wisdomsitepro.model.ProjectModel;

import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;

public class BrandInfoPresenter extends BrandInfoContract.Presenter {
    public BrandInfoPresenter(BrandInfoContract.View view, ProjectModel model) {
        super(view, model);
    }

    @Override
    public void getBrandInfo(String projId, String publicType) {
        model.getBrandInfo(projId, publicType, new JsonCallback<ResponseData<BrandInfo>>(new TypeToken<ResponseData<BrandInfo>>() {
        }) {
            @Override
            public void onStart() {
                super.onStart();
                if (isAttach) {
                    view.showProgress("获取数据中...");
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
            public void onSuccess(ResponseData<BrandInfo> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showBrandInfo(data.getResult());
                    } else {
                        view.showError(data.getErrmsg());
                    }
                }
            }
        });
    }
}
