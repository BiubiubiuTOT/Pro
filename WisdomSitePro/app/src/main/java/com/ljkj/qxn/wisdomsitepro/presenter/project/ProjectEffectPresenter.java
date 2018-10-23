package com.ljkj.qxn.wisdomsitepro.presenter.project;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.project.ProjectEffectContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.ProjectEffectInfo;
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
public class ProjectEffectPresenter extends ProjectEffectContract.Presenter {

    public ProjectEffectPresenter(ProjectEffectContract.View view, ProjectModel model) {
        super(view, model);
    }

    @Override
    public void getProjectEffect(String proId) {
        model.getProjectEffect(proId, new JsonCallback<ResponseData<ProjectEffectInfo>>(new TypeToken<ResponseData<ProjectEffectInfo>>() {
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
            public void onSuccess(ResponseData<ProjectEffectInfo> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showProjectEffect(data.getResult());
                    } else {
                        view.showError(data.getErrmsg());
                    }
                }
            }
        });
    }
}
