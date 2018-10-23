package com.ljkj.qxn.wisdomsitepro.presenter;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.project.FarmerBrandContract;
import com.ljkj.qxn.wisdomsitepro.data.FarmerBrandInfo;
import com.ljkj.qxn.wisdomsitepro.model.ProjectModel;

import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;

public class FarmerBrandPresenter extends FarmerBrandContract.Presenter {
    public FarmerBrandPresenter(FarmerBrandContract.View view, ProjectModel model) {
        super(view, model);
    }

    @Override
    public void getFarmerInfo(String projId) {
        model.getFarmerInfo(projId, new JsonCallback<ResponseData<FarmerBrandInfo>>(new TypeToken<ResponseData<FarmerBrandInfo>>() {
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
            public void onSuccess(ResponseData<FarmerBrandInfo> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showFarmerInfo(data.getResult());
                    } else {
                        view.showError(data.getErrmsg());
                    }
                }
            }
        });
    }
}
