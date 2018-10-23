package com.ljkj.qxn.wisdomsitepro.presenter.project;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.project.ProjectStatisticsContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.ProjectStatisticsInfo;
import com.ljkj.qxn.wisdomsitepro.model.ProjectModel;

import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;

/**
 * 类描述：项目统计
 * 创建人：lxx
 * 创建时间：2018/6/28
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class ProjectStatisticsPresenter extends ProjectStatisticsContract.Presenter {
    public ProjectStatisticsPresenter(ProjectStatisticsContract.View view, ProjectModel model) {
        super(view, model);
    }

    @Override
    public void getProjectStatistics(String projectId) {
        model.getProjectStatistics(projectId, new JsonCallback<ResponseData<ProjectStatisticsInfo>>(new TypeToken<ResponseData<ProjectStatisticsInfo>>() {
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
            public void onSuccess(ResponseData<ProjectStatisticsInfo> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showProjectStatistics(data.getResult());
                    } else {
                        view.showError(data.getErrmsg());
                    }
                }
            }

        });
    }


}
