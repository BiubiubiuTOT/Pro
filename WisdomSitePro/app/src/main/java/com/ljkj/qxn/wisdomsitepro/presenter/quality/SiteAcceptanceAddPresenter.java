package com.ljkj.qxn.wisdomsitepro.presenter.quality;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.quality.SiteAcceptanceAddContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.ConcreteEntranceAcceptanceInfo;
import com.ljkj.qxn.wisdomsitepro.model.ConcreteModel;

import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;

/**
 * 商品混凝土进场验收-新增
 * Created by lxx on 2018/3/13.
 */

public class SiteAcceptanceAddPresenter extends SiteAcceptanceAddContract.Presenter {
    public SiteAcceptanceAddPresenter(SiteAcceptanceAddContract.View view, ConcreteModel model) {
        super(view, model);
    }

    @Override
    public void addSiteAcceptance(ConcreteEntranceAcceptanceInfo detail) {
        model.saveApproach(detail, new JsonCallback<ResponseData<ConcreteEntranceAcceptanceInfo>>(new TypeToken<ResponseData<ConcreteEntranceAcceptanceInfo>>() {
        }) {
            @Override
            public void onStart() {
                super.onStart();
                if (isAttach) {
                    view.showProgress("提交数据中...");
                }
            }

            @Override
            protected void onError(int errcode, String errmsg) {
                if (isAttach) {
                    view.showError(errmsg);
                }
            }

            @Override
            public void onSuccess(ResponseData<ConcreteEntranceAcceptanceInfo> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.addSiteAcceptanceSuccess(data.getResult());
                    } else {
                        view.showError(data.getErrmsg());
                    }
                }
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
