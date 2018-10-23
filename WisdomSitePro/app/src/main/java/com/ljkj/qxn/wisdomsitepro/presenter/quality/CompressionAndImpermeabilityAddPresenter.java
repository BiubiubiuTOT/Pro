package com.ljkj.qxn.wisdomsitepro.presenter.quality;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.quality.CompressionAndImpermeabilityAddContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.ConcreteCompressiveInfo;
import com.ljkj.qxn.wisdomsitepro.model.ConcreteModel;

import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;

/**
 * 混凝土抗压强度检验/抗渗-新增
 * Created by lxx on 2018/3/14.
 */

public class CompressionAndImpermeabilityAddPresenter extends CompressionAndImpermeabilityAddContract.Presenter {

    public CompressionAndImpermeabilityAddPresenter(CompressionAndImpermeabilityAddContract.View view, ConcreteModel model) {
        super(view, model);
    }

    @Override
    public void addCompression(ConcreteCompressiveInfo detail) {
        model.saveCompressive(detail, new JsonCallback<ResponseData<ConcreteCompressiveInfo>>(new TypeToken<ResponseData<ConcreteCompressiveInfo>>() {
        }) {
            @Override
            public void onStart() {
                super.onStart();
                if (isAttach) {
                    view.showProgress("数据提交中...");
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
                        view.addSuccess(data.getResult());
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

    @Override
    public void addImpermeability(ConcreteCompressiveInfo detail) {
        model.savePermeability(detail, new JsonCallback<ResponseData<ConcreteCompressiveInfo>>(new TypeToken<ResponseData<ConcreteCompressiveInfo>>() {
        }) {

            @Override
            public void onStart() {
                super.onStart();
                if (isAttach) {
                    view.showProgress("数据提交中...");
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
                        view.addSuccess(data.getResult());
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
