package com.ljkj.qxn.wisdomsitepro.presenter.safe;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.safe.SafeSystemPdfContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.SafeSystemPdfInfo;
import com.ljkj.qxn.wisdomsitepro.model.SafeModel;

import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;

/**
 * 类描述：首页
 * 创建人：liufei
 * 创建时间：2018/2/23 11:41
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SafeSystemPdfPresenter extends SafeSystemPdfContract.Presenter {

    public SafeSystemPdfPresenter(SafeSystemPdfContract.View view, SafeModel model) {
        super(view, model);
    }

    @Override
    public void getSafeSystemPdf(String proId, int marFlag) {
        model.getSafeSystemPdf(proId, marFlag, new JsonCallback<ResponseData<SafeSystemPdfInfo>>(new TypeToken<ResponseData<SafeSystemPdfInfo>>() {
        }) {
            @Override
            public void onSuccess(ResponseData<SafeSystemPdfInfo> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showSafeSystemPdf(data.getResult());
                    } else {
                        view.showError(data.getErrmsg());
                    }
                }
            }

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
