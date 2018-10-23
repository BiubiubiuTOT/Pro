package com.ljkj.qxn.wisdomsitepro.presenter.project;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.project.ProjectProgressAddContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.ProjectProgressAddInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.ProjectProgressInfo;
import com.ljkj.qxn.wisdomsitepro.model.ProjectModel;

import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;

/**
 * 工程形象进度-新增
 * 创建人：lxx
 * 创建时间：2018/3/21 11:35
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class ProjectProgressAddPresenter extends ProjectProgressAddContract.Presenter {
    public ProjectProgressAddPresenter(ProjectProgressAddContract.View view, ProjectModel model) {
        super(view, model);
    }

    @Override
    public void addProjectProgress(ProjectProgressAddInfo info) {
        model.addProjectProgress(info, new JsonCallback<ResponseData<ProjectProgressInfo>>(new TypeToken<ResponseData<ProjectProgressInfo>>() {
        }) {


            @Override
            protected void onError(int errcode, String errmsg) {
                if (isAttach) {
                    view.showError(errmsg);
                }
            }

            @Override
            public void onStart() {
                super.onStart();
                if (isAttach)
                    view.showProgress("提交数据中...");
            }

            @Override
            public void onSuccess(ResponseData<ProjectProgressInfo> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.delaAddProgressResult();
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

        });
    }
}
