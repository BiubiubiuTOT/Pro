package com.ljkj.qxn.wisdomsitepro.presenter.quality;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.quality.ConcreteSiteAcceptanceContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.ConcreteEntranceAcceptanceInfo;
import com.ljkj.qxn.wisdomsitepro.model.ConcreteModel;

import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;

/**
 * 商品混凝土进场验收-详情
 * Created by lxx on 2018/3/11.
 */

public class ConcreteSiteAcceptancePresenter extends ConcreteSiteAcceptanceContract.Presenter {

    public ConcreteSiteAcceptancePresenter(ConcreteSiteAcceptanceContract.View view, ConcreteModel model) {
        super(view, model);
    }

    @Override
    public void getApproachDetail(final String id) {
        TypeToken<ResponseData<ConcreteEntranceAcceptanceInfo>> typeToken = new TypeToken<ResponseData<ConcreteEntranceAcceptanceInfo>>() {
        };
        model.getApproachDetail(id, new JsonCallback<ResponseData<ConcreteEntranceAcceptanceInfo>>(typeToken) {

            @Override
            public void onStart() {
                super.onStart();
                if (isAttach) {
                    view.showProgress("数据请求中...");
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
                        view.showSiteAcceptanceDetail(data.getResult());
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
