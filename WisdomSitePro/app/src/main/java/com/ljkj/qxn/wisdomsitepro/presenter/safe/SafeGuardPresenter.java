package com.ljkj.qxn.wisdomsitepro.presenter.safe;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.safe.SafeGuardContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.SafeGuardInfo;
import com.ljkj.qxn.wisdomsitepro.model.SafeModel;

import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;

/**
 * 类描述：安全防护
 * 创建人：rjf
 * 创建时间：2018/3/13 15:27.
 * 修改人：
 * 修改时间
 * 修改备注
 */

public class SafeGuardPresenter extends SafeGuardContract.Presenter {
    public SafeGuardPresenter(SafeGuardContract.View view, SafeModel model) {
        super(view, model);
    }

    @Override
    public void getSafeGuardInfo(String proId) {
        model.getSafeGuardInfo(proId, new JsonCallback<ResponseData<SafeGuardInfo>>(new TypeToken<ResponseData<SafeGuardInfo>>(){}) {

            @Override
            public void onStart() {
                super.onStart();
                if (isAttach){
                    view.showProgress("数据正在加载中...");
                }
            }

            @Override
            public void onSuccess(ResponseData<SafeGuardInfo> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showSafeGuardInfo(data.getResult());
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
