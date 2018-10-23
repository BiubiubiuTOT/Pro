package com.ljkj.qxn.wisdomsitepro.presenter.application;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.application.ConstructLogDetailContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.ConstructLogDetailInfo;
import com.ljkj.qxn.wisdomsitepro.model.ApplicationModel;

import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;

/**
 * 类描述：施工日志详情
 * 创建人：rjf
 * 创建时间：2018/3/15 09:07.
 * 修改人：
 * 修改时间
 * 修改备注
 */

public class ConstructLogDetailPresenter extends ConstructLogDetailContract.Presenter {

    public ConstructLogDetailPresenter(ConstructLogDetailContract.View view, ApplicationModel model) {
        super(view, model);
    }

    @Override
    public void getLogDetail(String id) {
        model.getLogDetail(id, new JsonCallback<ResponseData<ConstructLogDetailInfo>>(new TypeToken<ResponseData<ConstructLogDetailInfo>>() {
        }) {

            @Override
            public void onStart() {
                super.onStart();
                if (isAttach){
                    view.showProgress("正在加载中...");
                }
            }

            @Override
            public void onSuccess(ResponseData<ConstructLogDetailInfo> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showLogDetail(data.getResult());
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

            @Override
            protected void onError(int errcode, String errmsg) {
                if (isAttach) {
                    view.showError(errmsg);
                }
            }
        });

    }
}
