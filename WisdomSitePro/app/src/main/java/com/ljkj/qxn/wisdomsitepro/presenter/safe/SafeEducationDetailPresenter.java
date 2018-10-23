package com.ljkj.qxn.wisdomsitepro.presenter.safe;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.safe.SafeEducationDetailContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.SafeEducationDetailInfo;
import com.ljkj.qxn.wisdomsitepro.model.SafeModel;

import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;

/**
 * 类描述：安全教育详情
 * 创建人：mhl
 * 创建时间：2018/3/14 11:58
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class SafeEducationDetailPresenter extends SafeEducationDetailContract.Presenter {

    public SafeEducationDetailPresenter(SafeEducationDetailContract.View view, SafeModel model) {
        super(view, model);
    }

    @Override
    public void viewSafeEduDetail(String id) {
        model.safeEducationDetail(id, new JsonCallback<ResponseData<SafeEducationDetailInfo>>(new TypeToken<ResponseData<SafeEducationDetailInfo>>() {
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
            public void onSuccess(ResponseData<SafeEducationDetailInfo> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showSafeEduDetail(data.getResult());
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
