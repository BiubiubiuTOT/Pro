package com.ljkj.qxn.wisdomsitepro.presenter.personal;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.personal.ProjectListContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.ProjectInfo;
import com.ljkj.qxn.wisdomsitepro.model.UserModel;

import java.util.List;

import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;

public class ProjectListPresenter extends ProjectListContract.Presenter {

    public ProjectListPresenter(ProjectListContract.View view, UserModel model) {
        super(view, model);
    }

    @Override
    public void getProjectList() {
        model.getProjectList(new JsonCallback<ResponseData<List<ProjectInfo>>>(new TypeToken<ResponseData<List<ProjectInfo>>>() {
        }) {

            @Override
            public void onStart() {
                super.onStart();
                if (isAttach) {
                    view.showProgress("加载项目中...");
                }
            }

            @Override
            public void onSuccess(ResponseData<List<ProjectInfo>> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showProjectList(data.getResult());
                    } else {
                        view.showError(data.getErrmsg());
                    }
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
        });
    }

    @Override
    public void getProject(String projId) {
        model.getProject(projId, new JsonCallback<ResponseData<List<ProjectInfo>>>(new TypeToken<ResponseData<List<ProjectInfo>>>() {
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
            public void onSuccess(ResponseData<List<ProjectInfo>> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showProject(data.getResult());
                    } else {
                        view.showError(data.getErrmsg());
                    }
                }
            }
        });
    }
}
