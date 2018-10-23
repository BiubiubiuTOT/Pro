package com.ljkj.qxn.wisdomsitepro.presenter.safe;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.safe.SafetySupervisionDeclarationContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.SafetySupervisionDeclarationInfo;
import com.ljkj.qxn.wisdomsitepro.model.SafeModel;

import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;

/**
 * 类描述：安全监督申报
 * 创建人：mhl
 * 创建时间：2018/3/13 14:17
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class SafetySupervisionDeclarationPresenter extends SafetySupervisionDeclarationContract.Presenter {

    public SafetySupervisionDeclarationPresenter(SafetySupervisionDeclarationContract.View view, SafeModel model) {
        super(view, model);
    }

    @Override
    public void viewSupervisionDeclaration(String proId) {

        model.safetySupervisionDeclaration(proId, new JsonCallback<ResponseData<SafetySupervisionDeclarationInfo>>(new TypeToken<ResponseData<SafetySupervisionDeclarationInfo>>() {
        }) {

            @Override
            public void onStart() {
                super.onStart();
                if (isAttach) {
                    view.showProgress("数据请求中....");
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
            public void onSuccess(ResponseData<SafetySupervisionDeclarationInfo> data) {

                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showSupervisionDeclaration(data.getResult());
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
