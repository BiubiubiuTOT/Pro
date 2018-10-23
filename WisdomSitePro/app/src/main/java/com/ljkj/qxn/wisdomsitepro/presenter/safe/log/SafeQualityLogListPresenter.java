package com.ljkj.qxn.wisdomsitepro.presenter.safe.log;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.safe.log.SafeQualityLogListContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.PageInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.SafeQualityLogInfo;
import com.ljkj.qxn.wisdomsitepro.model.SafeQualityLogModel;

import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;

/**
 * 类描述：安全/质量日志 列表
 * 创建人：lxx
 * 创建时间：2018/9/18
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SafeQualityLogListPresenter extends SafeQualityLogListContract.Presenter {
    public SafeQualityLogListPresenter(SafeQualityLogListContract.View view, SafeQualityLogModel model) {
        super(view, model);
    }

    @Override
    public void getSafeLogList(String proId, int page, int size, String recorder) {
        model.getSafeLogList(proId, page, size, recorder, new JsonCallback<ResponseData<PageInfo<SafeQualityLogInfo>>>(new TypeToken<ResponseData<PageInfo<SafeQualityLogInfo>>>() {
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
            public void onSuccess(ResponseData<PageInfo<SafeQualityLogInfo>> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showSafeLogList(data.getResult());
                    } else {
                        view.showError(data.getErrmsg());
                    }
                }
            }
        });
    }

    @Override
    public void getQualityLogList(String proId, int page, int size, String recorder) {
        model.getQualityLogList(proId, page, size, recorder, new JsonCallback<ResponseData<PageInfo<SafeQualityLogInfo>>>(new TypeToken<ResponseData<PageInfo<SafeQualityLogInfo>>>() {
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
            public void onSuccess(ResponseData<PageInfo<SafeQualityLogInfo>> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showQualityLogList(data.getResult());
                    } else {
                        view.showError(data.getErrmsg());
                    }
                }
            }
        });
    }
}
