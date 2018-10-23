package com.ljkj.qxn.wisdomsitepro.presenter.safe;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.safe.SafeTechnologyDetailContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.SafeTechnologyDetail;
import com.ljkj.qxn.wisdomsitepro.model.SafeModel;

import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;
/**
 * 类描述：安全技术交底详情
 * 创建人：lxx
 * 创建时间：2018/9/25
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SafeTechnologyDetailPresenter extends SafeTechnologyDetailContract.Presenter {
    public SafeTechnologyDetailPresenter(SafeTechnologyDetailContract.View view, SafeModel model) {
        super(view, model);
    }

    @Override
    public void getSafeTechnologyDetail(String id) {
        model.getSafeTechnologyDetail(id, new JsonCallback<ResponseData<SafeTechnologyDetail>>(new TypeToken<ResponseData<SafeTechnologyDetail>>() {
        }) {
            @Override
            public void onStart() {
                super.onStart();
                if (isAttach) {
                    view.showProgress("请求数据中...");
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
            public void onSuccess(ResponseData<SafeTechnologyDetail> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showSafeTechnologyDetail(data.getResult());
                    } else {
                        view.showError(data.getErrmsg());
                    }
                }
            }
        });
    }
}
