package com.ljkj.qxn.wisdomsitepro.presenter.application;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.application.ConstructListLogContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.ConstructLogInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.PageInfo;
import com.ljkj.qxn.wisdomsitepro.model.ApplicationModel;

import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;

/**
 * 作者：JiaJia
 * 创建时间：2018/3/9 15:28.
 * 功能描述：
 */

public class ConstructListLogPresenter extends ConstructListLogContract.Presenter {
    public ConstructListLogPresenter(ConstructListLogContract.View view, ApplicationModel model) {
        super(view, model);
    }

    @Override
    public void constructLogList(String projId, int page, int size, final String date, String recorder) {
        model.constructLogList(projId, page, size, date, recorder, new JsonCallback<ResponseData<PageInfo<ConstructLogInfo>>>(new TypeToken<ResponseData<PageInfo<ConstructLogInfo>>>() {
        }) {
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

            @Override
            public void onSuccess(ResponseData<PageInfo<ConstructLogInfo>> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showPageLogInfo(data.getResult());
                    } else {
                        view.showError(data.getErrmsg());
                    }
                }
            }

        });
    }


}
