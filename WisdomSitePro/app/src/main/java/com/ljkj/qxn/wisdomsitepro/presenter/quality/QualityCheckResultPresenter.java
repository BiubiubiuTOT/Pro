package com.ljkj.qxn.wisdomsitepro.presenter.quality;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.quality.QualityCheckResultContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.QualityCheckResultDetail;
import com.ljkj.qxn.wisdomsitepro.model.QualityModel;

import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;

/**
 * 类描述：检查结果详情
 * 创建人：lxx
 * 创建时间：2018/3/15 11:45
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class QualityCheckResultPresenter extends QualityCheckResultContract.Presenter {

    public QualityCheckResultPresenter(QualityCheckResultContract.View view, QualityModel model) {
        super(view, model);
    }

    @Override
    public void getCheckResultDetail(String id, String proId) {
        model.getCheckResultDetail(id, proId, new JsonCallback<ResponseData<QualityCheckResultDetail>>(new TypeToken<ResponseData<QualityCheckResultDetail>>() {
        }) {

            @Override
            public void onStart() {
                super.onStart();
                if (isAttach) {
                    view.showProgress("获取数据中...");
                }
            }

            @Override
            protected void onError(int errcode, String errmsg) {
                if (isAttach) {
                    view.showError(errmsg);
                }
            }

            @Override
            public void onSuccess(ResponseData<QualityCheckResultDetail> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showCheckResultDetail(data.getResult());
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
