package com.ljkj.qxn.wisdomsitepro.presenter.safe;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.safe.SafeEduListContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.PageInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.SafeEduInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.SafeTechnologyInfo;
import com.ljkj.qxn.wisdomsitepro.model.SafeModel;

import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;

/**
 * 类描述：安全教育
 * 创建人：liufei
 * 创建时间：2018/3/12 13:43
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class SafeEduPresenter extends SafeEduListContract.Presenter {

    public SafeEduPresenter(SafeEduListContract.View view, SafeModel model) {
        super(view, model);
    }

    @Override
    public void getSafeEduList(String proId, String isSb, String date, int rows, int page) {
        model.getSafeEduList(proId, isSb, date, rows, page, new JsonCallback<ResponseData<PageInfo<SafeEduInfo>>>(new TypeToken<ResponseData<PageInfo<SafeEduInfo>>>() {
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
            public void onSuccess(ResponseData<PageInfo<SafeEduInfo>> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showSafeEduList(data.getResult());
                    } else {
                        view.showError(data.getErrmsg());
                    }
                }
            }
        });
    }

    @Override
    public void getSafeEdeTechnologyList(String proId, String isReport, String date, int pageSize, int pageNum) {
        model.getSafeEdeTechnologyList(proId, isReport, date, pageSize, pageNum, new JsonCallback<ResponseData<PageInfo<SafeTechnologyInfo>>>(new TypeToken<ResponseData<PageInfo<SafeTechnologyInfo>>>() {
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
            public void onSuccess(ResponseData<PageInfo<SafeTechnologyInfo>> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showSafeTechnologyList(data.getResult());
                    } else {
                        view.showError(data.getErrmsg());
                    }
                }
            }
        });
    }
}
