package com.ljkj.qxn.wisdomsitepro.presenter.quality;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.quality.QualityPatrolAddContractV2;
import com.ljkj.qxn.wisdomsitepro.model.QualityModel;

import java.util.HashMap;

import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;

/**
 * 类描述：下发责任整改通知书
 * 创建人：mhl
 * 创建时间：2018/3/17 9:40
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class QualityPatrolAddPresenterV2 extends QualityPatrolAddContractV2.Presenter {

    public QualityPatrolAddPresenterV2(QualityPatrolAddContractV2.View view, QualityModel model) {
        super(view, model);
    }


    @Override
    public void qualityPatrolAdd(HashMap params) {
        model.qualityPatrolAdd(params, new JsonCallback<ResponseData<String>>(new TypeToken<ResponseData<String>>() {
        }) {

            @Override
            public void onStart() {
                super.onStart();
                if (isAttach) {
                    view.showProgress("提交中...");
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
            public void onSuccess(ResponseData<String> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showQualityPatrolAddResult(data.getResult());
                    } else {
                        view.showError(data.getErrmsg());
                    }
                }
            }

            @Override
            protected void onError(int errcode, String errmsg) {

                if (isAttach) {
                    view.showError(errmsg);
                }
            }
        });
    }
}
