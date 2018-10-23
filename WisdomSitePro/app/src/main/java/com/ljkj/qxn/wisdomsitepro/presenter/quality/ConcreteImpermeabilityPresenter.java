package com.ljkj.qxn.wisdomsitepro.presenter.quality;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.quality.ConcreteImpermeabilityContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.ConcreteCompressiveInfo;
import com.ljkj.qxn.wisdomsitepro.model.ConcreteModel;

import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;

/**
 * 混凝土抗渗检验-详情
 * Created by lxx on 2018/3/11.
 */

public class ConcreteImpermeabilityPresenter extends ConcreteImpermeabilityContract.Presenter {

    public ConcreteImpermeabilityPresenter(ConcreteImpermeabilityContract.View view, ConcreteModel model) {
        super(view, model);
    }

    @Override
    public void getPermeabilityDetail(String id) {
        TypeToken<ResponseData<ConcreteCompressiveInfo>> typeToken = new TypeToken<ResponseData<ConcreteCompressiveInfo>>() {
        };
        model.getPermeabilityDetail(id, new JsonCallback<ResponseData<ConcreteCompressiveInfo>>(typeToken) {

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
            public void onSuccess(ResponseData<ConcreteCompressiveInfo> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showPermeabilityDetail(data.getResult());
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
