package com.ljkj.qxn.wisdomsitepro.presenter.safe;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.safe.SafeHiddenAccountContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.PageInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.SafeHiddenAccountInfo;
import com.ljkj.qxn.wisdomsitepro.model.SafeModel;

import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;

/**
 * 类描述：安全隐患台账
 * 创建人：lxx
 * 创建时间：2018/7/11
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SafeHiddenAccountPresenter extends SafeHiddenAccountContract.Presenter {

    public SafeHiddenAccountPresenter(SafeHiddenAccountContract.View view, SafeModel model) {
        super(view, model);
    }

    @Override
    public void getSafeHiddenAccountList(String proId, int type, int page, int rows) {
        model.getSafeHiddenAccountList(proId, type, page, rows, new JsonCallback<ResponseData<PageInfo<SafeHiddenAccountInfo>>>(new TypeToken<ResponseData<PageInfo<SafeHiddenAccountInfo>>>() {
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
            public void onSuccess(ResponseData<PageInfo<SafeHiddenAccountInfo>> data) {
                if (isAttach) {
                    view.showSafeHiddenAccount(data.getResult());
                }
            }
        });

    }

    @Override
    public void getQualityHiddenAccountList(String proId, int type, int page, int rows) {
        model.getQualityHiddenAccountList(proId, type, page, rows, new JsonCallback<ResponseData<PageInfo<SafeHiddenAccountInfo>>>(new TypeToken<ResponseData<PageInfo<SafeHiddenAccountInfo>>>() {
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
            public void onSuccess(ResponseData<PageInfo<SafeHiddenAccountInfo>> data) {
                if (isAttach) {
                    view.showQualityHiddenAccount(data.getResult());
                }
            }
        });
    }

}
