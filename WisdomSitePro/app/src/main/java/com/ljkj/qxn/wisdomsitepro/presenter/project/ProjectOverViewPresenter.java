package com.ljkj.qxn.wisdomsitepro.presenter.project;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.project.ProjectOverViewContract;
import com.ljkj.qxn.wisdomsitepro.data.ProjectOverViewInfo;
import com.ljkj.qxn.wisdomsitepro.model.ProjectModel;

import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;

public class ProjectOverViewPresenter extends ProjectOverViewContract.Presenter {
    public ProjectOverViewPresenter(ProjectOverViewContract.View view, ProjectModel model) {
        super(view, model);
    }

    @Override
    public void getProjectOverView(String projId) {
        model.getProjectOverView(projId, new JsonCallback<ResponseData<ProjectOverViewInfo>>(new TypeToken<ResponseData<ProjectOverViewInfo>>() {
        }) {
            @Override
            public void onStart() {
                super.onStart();
                if (isAttach) {
                    view.showProgress("获取数据中...");
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
            protected void onError(int errcode, String errmsg) {
                if (isAttach) {
                    view.showError(errmsg);
                }
            }

            @Override
            public void onSuccess(ResponseData<ProjectOverViewInfo> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showProjectOverview(data.getResult());
                    } else {
                        view.showError(data.getErrmsg());
                    }

                }
            }

        });
    }
}
