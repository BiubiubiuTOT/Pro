package com.ljkj.qxn.wisdomsitepro.presenter.safe;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.common.InspectRectifyContract;
import com.ljkj.qxn.wisdomsitepro.contract.safe.SafeInspectRectifyContract;
import com.ljkj.qxn.wisdomsitepro.model.QualityModel;
import com.ljkj.qxn.wisdomsitepro.model.SafeModel;

import java.util.HashMap;

import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;

/**
 * 类描述：巡检整改
 * 创建人：rjf
 * 创建时间：2018/3/19 17:55.
 * 修改人：
 * 修改时间
 * 修改备注
 */

public class SafeInspectRectifyPresenter extends SafeInspectRectifyContract.Presenter {
    public SafeInspectRectifyPresenter(SafeInspectRectifyContract.View view, SafeModel model) {
        super(view, model);
    }

    @Override
    public void safeInspectRectify(HashMap params) {
        model.safeInspectRectify(params, new JsonCallback<ResponseData<String>>(new TypeToken<ResponseData<String>>() {
        }) {
            @Override
            public void onStart() {
                super.onStart();
                if (isAttach) {
                    view.showProgress("数据提交中");
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
            public void onSuccess(ResponseData<String> data) {

                if (isAttach) {
                    if (data.isSuccess()) {
                        view.dealResult(data.getResult());
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
