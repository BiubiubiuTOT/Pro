package com.ljkj.qxn.wisdomsitepro.presenter.common;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.common.AppUpdateContract;
import com.ljkj.qxn.wisdomsitepro.data.VersionInfo;
import com.ljkj.qxn.wisdomsitepro.model.CommonModel;

import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;

/**
 * 类描述：获取最新APP版本信息
 * 创建人：lxx
 * 创建时间：2018/4/10 13:43
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class AppUpdatePresenter extends AppUpdateContract.Presenter {
    public AppUpdatePresenter(AppUpdateContract.View view, CommonModel model) {
        super(view, model);
    }

    @Override
    public void checkUpdate() {
        model.updateVersion(new JsonCallback<ResponseData<VersionInfo>>(new TypeToken<ResponseData<VersionInfo>>() {
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
            public void onSuccess(ResponseData<VersionInfo> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showVersionInfo(data.getResult());
                    } else {
                        view.showError(data.getErrmsg());
                    }
                }
            }


        });
    }
}
