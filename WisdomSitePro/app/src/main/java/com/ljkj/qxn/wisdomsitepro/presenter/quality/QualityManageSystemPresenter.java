package com.ljkj.qxn.wisdomsitepro.presenter.quality;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.quality.QualityManageSystemContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.QualityManageSystemInfo;
import com.ljkj.qxn.wisdomsitepro.model.QualityModel;

import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;

/**
 * 类描述：质量管理体系
 * 创建人：mhl
 * 创建时间：2018/2/28 11:01
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class QualityManageSystemPresenter extends QualityManageSystemContract.Presenter {


    public QualityManageSystemPresenter(QualityManageSystemContract.View view, QualityModel model) {
        super(view, model);
    }

    @Override
    public void viewQualityManageSystemInfo(String proId) {
        model.viewSetupindex(proId, new JsonCallback<ResponseData<QualityManageSystemInfo>>(new TypeToken<ResponseData<QualityManageSystemInfo>>() {
        }) {


            @Override
            public void onStart() {
                super.onStart();
                if (isAttach) {
                    view.showProgress("数据请求中...");
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
            public void onSuccess(ResponseData<QualityManageSystemInfo> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showQualityManageSystemInfo(data.getResult());
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
