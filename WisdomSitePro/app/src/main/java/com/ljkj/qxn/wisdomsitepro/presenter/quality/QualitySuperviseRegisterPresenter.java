package com.ljkj.qxn.wisdomsitepro.presenter.quality;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.quality.QualitySuperviseRegisterContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.QualitySuperviseRegisterInfo;
import com.ljkj.qxn.wisdomsitepro.model.QualityModel;

import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;

/**
 * 质量监督注册登记
 * 创建人：lxx
 * 创建时间：2018/3/19 10:43
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class QualitySuperviseRegisterPresenter extends QualitySuperviseRegisterContract.Presenter {

    public QualitySuperviseRegisterPresenter(QualitySuperviseRegisterContract.View view, QualityModel model) {
        super(view, model);
    }

    @Override
    public void qualitySupReg(String proId) {
        model.quaSupervisorRegister(proId, new JsonCallback<ResponseData<QualitySuperviseRegisterInfo>>(new TypeToken<ResponseData<QualitySuperviseRegisterInfo>>() {
        }) {
            @Override
            public void onStart() {
                super.onStart();
                if (isAttach) {
                    view.showProgress("数据获取中");
                }
            }

            @Override
            protected void onError(int errcode, String errmsg) {
                if (isAttach) {
                    view.showError(errmsg);
                }
            }

            @Override
            public void onSuccess(ResponseData<QualitySuperviseRegisterInfo> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showQualitySuperviseRegisterInfo(data.getResult());
                    } else {
                        view.showError(data.getErrmsg());
                    }
                }
            }

            @Override
            public void onFinish() {
                if (isAttach) {
                    view.hideProgress();
                }
            }
        });
    }

}
