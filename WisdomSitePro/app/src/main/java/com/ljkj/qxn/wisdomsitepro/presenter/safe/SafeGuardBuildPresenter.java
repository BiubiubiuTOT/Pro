package com.ljkj.qxn.wisdomsitepro.presenter.safe;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.safe.SafeGuardBuildContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.SafeGuardBuildInfo;
import com.ljkj.qxn.wisdomsitepro.model.SafeModel;

import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;

/**
 * 类描述：安全防护楼栋详情
 * 创建人：rjf
 * 创建时间：2018/3/13 16:10.
 * 修改人：
 * 修改时间
 * 修改备注
 */

public class SafeGuardBuildPresenter extends SafeGuardBuildContract.Presenter {
    public SafeGuardBuildPresenter(SafeGuardBuildContract.View view, SafeModel model) {
        super(view, model);
    }

    @Override
    public void getSafeGuardBuild(String lou, String proId) {
        model.getSafeGuardBuild(lou, proId, new JsonCallback<ResponseData<SafeGuardBuildInfo>>(new TypeToken<ResponseData<SafeGuardBuildInfo>>() {
        }) {

            @Override
            public void onStart() {
                super.onStart();
                if (isAttach){
                    view.showProgress("数据加载中...");
                }
            }

            @Override
            public void onSuccess(ResponseData<SafeGuardBuildInfo> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showSafeGuardBuild(data.getResult());
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

    @Override
    public void deleteSafeGuardBuild(String cengId) {
        model.deleteSafeGuardBuild(cengId, new JsonCallback<ResponseData>(new TypeToken<ResponseData>(){}) {

            @Override
            public void onStart() {
                super.onStart();
                if (isAttach){
                    view.showProgress("正在删除");
                }
            }

            @Override
            public void onSuccess(ResponseData data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.deleteSafeGuardBuild();
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
