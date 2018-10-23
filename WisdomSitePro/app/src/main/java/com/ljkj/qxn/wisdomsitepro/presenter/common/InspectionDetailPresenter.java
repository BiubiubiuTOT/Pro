package com.ljkj.qxn.wisdomsitepro.presenter.common;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.common.InspectionDetailContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.InspectionInfo;
import com.ljkj.qxn.wisdomsitepro.model.QualityModel;

import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;

/**
 * 类描述：巡检详情
 * 创建人：mhl
 * 创建时间：2018/3/19 9:51
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class InspectionDetailPresenter extends InspectionDetailContract.Presenter {

    public InspectionDetailPresenter(InspectionDetailContract.View view, QualityModel model) {
        super(view, model);
    }

    @Override
    public void viewInspectionDetail(String id) {

        model.viewInspectionDetail(id, new JsonCallback<ResponseData<InspectionInfo>>(new TypeToken<ResponseData<InspectionInfo>>() {
        }) {

            @Override
            public void onStart() {
                super.onStart();
                if (isAttach) {
                    view.showProgress("数据请求中");
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
            public void onSuccess(ResponseData<InspectionInfo> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showInspectionDetail(data.getResult());
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
