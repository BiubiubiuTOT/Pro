package com.ljkj.qxn.wisdomsitepro.presenter.project;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.project.ProjectInfoContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.PageInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.ProjectDoneProgress;
import com.ljkj.qxn.wisdomsitepro.data.entity.ProjectInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.ProjectProgressInfo;
import com.ljkj.qxn.wisdomsitepro.model.ProjectModel;

import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;

/**
 * Created by niujingtong on 2018/7/10.
 * 模块：
 */
public class ProjectInfoPresenter extends ProjectInfoContract.Presenter {
    public ProjectInfoPresenter(ProjectInfoContract.View view, ProjectModel model) {
        super(view, model);
    }

    @Override
    public void getProjectInfo(String proId) {
        model.getProjectInfo(proId, new JsonCallback<ResponseData<ProjectDoneProgress>>(new TypeToken<ResponseData<ProjectDoneProgress>>() {
        }) {
            @Override
            public void onSuccess(ResponseData<ProjectDoneProgress> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showProjectInfo(data.getResult());
                    } else {
                        view.showError(data.getErrmsg());
                    }
                }
            }


            @Override
            protected void onError(int errcode, String errmsg) {
                if (isAttach)
                    view.showError(errmsg);
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
