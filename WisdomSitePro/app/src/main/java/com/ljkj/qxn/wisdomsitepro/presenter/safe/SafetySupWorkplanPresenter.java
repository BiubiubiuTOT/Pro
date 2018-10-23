package com.ljkj.qxn.wisdomsitepro.presenter.safe;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.safe.SafetySupWorkplanContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.SafetySupWorkplanInfo;
import com.ljkj.qxn.wisdomsitepro.model.SafeModel;

import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;

/**
 * 类描述：
 * 创建人：mhl
 * 创建时间：2018/3/13 13:55
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class SafetySupWorkplanPresenter extends SafetySupWorkplanContract.Presenter {

    public SafetySupWorkplanPresenter(SafetySupWorkplanContract.View view, SafeModel model) {
        super(view, model);
    }

    @Override
    public void viewSafetySupWorkplan(String proId) {

        model.safetySupWorkplan(proId, new JsonCallback<ResponseData<SafetySupWorkplanInfo>>(new TypeToken<ResponseData<SafetySupWorkplanInfo>>() {
        }) {


            @Override
            public void onStart() {
                super.onStart();
                if (isAttach) {
                    view.showProgress("数据获取中...");
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
            public void onSuccess(ResponseData<SafetySupWorkplanInfo> data) {

                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showSafetySupWorkplan(data.getResult());
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
