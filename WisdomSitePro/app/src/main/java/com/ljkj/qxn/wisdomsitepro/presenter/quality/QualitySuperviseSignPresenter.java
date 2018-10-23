package com.ljkj.qxn.wisdomsitepro.presenter.quality;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.quality.QualitySuperviseSignContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.QualitySuperviseSignInfo;
import com.ljkj.qxn.wisdomsitepro.model.QualityModel;

import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;

/**
 * 类描述：质量监督申报
 * 创建人：mhl
 * 创建时间：2018/3/16 16:59
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class QualitySuperviseSignPresenter extends QualitySuperviseSignContract.Presenter {

    public QualitySuperviseSignPresenter(QualitySuperviseSignContract.View view, QualityModel model) {
        super(view, model);
    }

    @Override
    public void viewQualitySuperviseSignInfo(String proId) {

        model.quaRegister(proId, new JsonCallback<ResponseData<QualitySuperviseSignInfo>>(new TypeToken<ResponseData<QualitySuperviseSignInfo>>() {
        }) {

            @Override
            public void onStart() {
                super.onStart();
                if (isAttach) {
                    view.showProgress("数据获取中");
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
            public void onSuccess(ResponseData<QualitySuperviseSignInfo> data) {

                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showQualitySuperviseSignInfo(data.getResult());
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
