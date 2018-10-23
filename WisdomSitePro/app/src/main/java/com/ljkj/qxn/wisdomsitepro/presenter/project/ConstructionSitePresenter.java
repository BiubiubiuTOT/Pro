package com.ljkj.qxn.wisdomsitepro.presenter.project;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.project.ConstructionSiteContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.ConstructionSiteInfo;
import com.ljkj.qxn.wisdomsitepro.model.ProjectModel;

import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;

/**
 * 类描述：施工现场
 * 创建人：liufei
 * 创建时间：2018/3/10 11:34
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class ConstructionSitePresenter extends ConstructionSiteContract.Presenter {

    public ConstructionSitePresenter(ConstructionSiteContract.View view, ProjectModel model) {
        super(view, model);
    }

    @Override
    public void getConstructionSite(String proId) {
        model.getConstructionSite(proId, new JsonCallback<ResponseData<ConstructionSiteInfo>>(new TypeToken<ResponseData<ConstructionSiteInfo>>() {
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
            public void onSuccess(ResponseData<ConstructionSiteInfo> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showConstructionSite(data.getResult());
                    } else {
                        view.showError(data.getErrmsg());
                    }
                }
            }
        });
    }
}
