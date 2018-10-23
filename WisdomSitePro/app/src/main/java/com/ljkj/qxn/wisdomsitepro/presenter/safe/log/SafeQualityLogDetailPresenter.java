package com.ljkj.qxn.wisdomsitepro.presenter.safe.log;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.safe.log.SafeQualityLogDetailContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.SafeQualityLogDetail;
import com.ljkj.qxn.wisdomsitepro.model.SafeQualityLogModel;

import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;

/**
 * 类描述：安全/质量日志 详情
 * 创建人：lxx
 * 创建时间：2018/9/18
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SafeQualityLogDetailPresenter extends SafeQualityLogDetailContract.Presenter {
    public SafeQualityLogDetailPresenter(SafeQualityLogDetailContract.View view, SafeQualityLogModel model) {
        super(view, model);
    }

    @Override
    public void getSafeLogDetail(String id ) {
        model.getSafeLogDetail(id, new JsonCallback<ResponseData<SafeQualityLogDetail>>(new TypeToken<ResponseData<SafeQualityLogDetail>>() {
        }) {
            @Override
            public void onStart() {
                super.onStart();
                if (isAttach) {
                    view.showProgress("获取数据中...");
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

            @Override
            public void onSuccess(ResponseData<SafeQualityLogDetail> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showSafeLogDetail(data.getResult());
                    } else {
                        view.showError(data.getErrmsg());
                    }
                }
            }
        });
    }

    @Override
    public void getQualityLogDetail(String id) {
        model.getQualityLogDetail(id, new JsonCallback<ResponseData<SafeQualityLogDetail>>(new TypeToken<ResponseData<SafeQualityLogDetail>>() {
        }) {
            @Override
            public void onStart() {
                super.onStart();
                if (isAttach) {
                    view.showProgress("获取数据中...");
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

            @Override
            public void onSuccess(ResponseData<SafeQualityLogDetail> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showSafeLogDetail(data.getResult());
                    } else {
                        view.showError(data.getErrmsg());
                    }
                }
            }
        });
    }
}
