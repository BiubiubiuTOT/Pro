package com.ljkj.qxn.wisdomsitepro.presenter.project;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.project.ProjectProgressContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.ProjectProgressInfo;
import com.ljkj.qxn.wisdomsitepro.model.ProjectModel;

import java.util.List;

import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;

/**
 * 类描述：工程形象进度
 * 创建人：liufei
 * 创建时间：2018/3/10 11:34
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class ProjectProgressPresenter extends ProjectProgressContract.Presenter {

    public ProjectProgressPresenter(ProjectProgressContract.View view, ProjectModel model) {
        super(view, model);
    }

    @Override
    public void getProjectProgress(String proId, String time) {
        model.getProjectProgress(proId, time, new JsonCallback<ResponseData<List<ProjectProgressInfo>>>(new TypeToken<ResponseData<List<ProjectProgressInfo>>>() {
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
            public void onSuccess(ResponseData<List<ProjectProgressInfo>> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showProjectProgressList(data.getResult());
                    } else {
                        view.showError(data.getErrmsg());
                    }
                }
            }
        });
    }

    @Override
    public void getRecentProjectProgress(String proId) {
        model.getRecentProjectProgress(proId, new JsonCallback<ResponseData<ProjectProgressInfo>>
                (new TypeToken<ResponseData<ProjectProgressInfo>>() {
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
            public void onSuccess(ResponseData<ProjectProgressInfo> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showRecentProjectProgressInfo(data.getResult());
                    } else {
                        view.showError(data.getErrmsg());
                    }
                }
            }
        });
    }
}
