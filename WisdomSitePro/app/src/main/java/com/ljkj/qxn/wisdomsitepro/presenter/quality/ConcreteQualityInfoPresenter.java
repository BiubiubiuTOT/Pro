package com.ljkj.qxn.wisdomsitepro.presenter.quality;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.quality.ConcreteQualityInfoContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.ConcreteCompressiveInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.ConcreteEntranceAcceptanceInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.PageInfo;
import com.ljkj.qxn.wisdomsitepro.model.ConcreteModel;

import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;

/**
 * 混凝土质量信息
 * Created by lxx on 2018/3/10.
 */

public class ConcreteQualityInfoPresenter extends ConcreteQualityInfoContract.Presenter {


    public ConcreteQualityInfoPresenter(ConcreteQualityInfoContract.View view, ConcreteModel model) {
        super(view, model);
    }

    @Override
    public void getApproachList(String keyWord, int page, String proId, int rows) {
        TypeToken<ResponseData<PageInfo<ConcreteEntranceAcceptanceInfo>>> typeToken = new TypeToken<ResponseData<PageInfo<ConcreteEntranceAcceptanceInfo>>>() {
        };
        model.getApproachList(keyWord, page, proId, rows, new JsonCallback<ResponseData<PageInfo<ConcreteEntranceAcceptanceInfo>>>(typeToken) {
            @Override
            protected void onError(int errcode, String errmsg) {
                if (isAttach) {
                    view.showError(errmsg);
                }
            }

            @Override
            public void onSuccess(ResponseData<PageInfo<ConcreteEntranceAcceptanceInfo>> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showApproachList(data.getResult());
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
    public void getCompressiveList(String keyWord, int page, String proId, int rows) {
        TypeToken<ResponseData<PageInfo<ConcreteCompressiveInfo>>> typeToken = new TypeToken<ResponseData<PageInfo<ConcreteCompressiveInfo>>>() {
        };
        model.getCompressiveList(keyWord, page, proId, rows, new JsonCallback<ResponseData<PageInfo<ConcreteCompressiveInfo>>>(typeToken) {
            @Override
            protected void onError(int errcode, String errmsg) {
                if (isAttach) {
                    view.showError(errmsg);
                }
            }

            @Override
            public void onSuccess(ResponseData<PageInfo<ConcreteCompressiveInfo>> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showCompressiveList(data.getResult());
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
    public void getPermeabilityList(String keyWord, int page, String proId, int rows) {
        TypeToken<ResponseData<PageInfo<ConcreteCompressiveInfo>>> typeToken = new TypeToken<ResponseData<PageInfo<ConcreteCompressiveInfo>>>() {
        };
        model.getPermeabilityList(keyWord, page, proId, rows, new JsonCallback<ResponseData<PageInfo<ConcreteCompressiveInfo>>>(typeToken) {

            @Override
            protected void onError(int errcode, String errmsg) {
                if (isAttach) {
                    view.showError(errmsg);
                }
            }

            @Override
            public void onSuccess(ResponseData<PageInfo<ConcreteCompressiveInfo>> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showPermeabilityList(data.getResult());
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
