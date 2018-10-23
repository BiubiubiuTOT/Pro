package com.ljkj.qxn.wisdomsitepro.presenter.project;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.project.ProjectHomeInfoContract;
import com.ljkj.qxn.wisdomsitepro.data.SafeQualityStatisticsInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.EnvironmentStatisticsInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.LaborCountInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.ProjectHomeInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.RealTimePersonStatisticsInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.RealTimeTeamStatisticsInfo;
import com.ljkj.qxn.wisdomsitepro.model.ProjectModel;

import java.util.List;

import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;

/**
 * 类描述：项目首页信息
 * 创建人：lxx
 * 创建时间：2018/9/27
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class ProjectHomeInfoPresenter extends ProjectHomeInfoContract.Presenter {
    public ProjectHomeInfoPresenter(ProjectHomeInfoContract.View view, ProjectModel model) {
        super(view, model);
    }

    @Override
    public void getProjectHomeInfo(String proId) {
        model.getProjectHomeInfo(proId, new JsonCallback<ResponseData<ProjectHomeInfo>>(new TypeToken<ResponseData<ProjectHomeInfo>>() {
        }) {
            @Override
            protected void onError(int errcode, String errmsg) {
                if (isAttach) {
                    view.showError(errmsg);
                }
            }

            @Override
            public void onFinish() {
                super.onFinish();
                if (isAttach){
                    view.hideProgress();
                }
            }

            @Override
            public void onSuccess(ResponseData<ProjectHomeInfo> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showProjectHomeInfo(data.getResult());
                    } else {
                        view.showError(data.getErrmsg());
                    }
                }
            }
        });

    }

    @Override
    public void getLaborCount(String projId) {
        model.getLaborCount(projId, new JsonCallback<ResponseData<LaborCountInfo>>(new TypeToken<ResponseData<LaborCountInfo>>() {
        }) {
            @Override
            protected void onError(int errcode, String errmsg) {
                if (isAttach) {
                    view.showError(errmsg);
                }
            }

            @Override
            public void onFinish() {
                super.onFinish();
                if (isAttach){
                    view.hideProgress();
                }
            }

            @Override
            public void onSuccess(ResponseData<LaborCountInfo> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showLaborCountInfo(data.getResult());
                    } else {
                        view.showError(data.getErrmsg());
                    }
                }
            }
        });
    }

    @Override
    public void getRealTimePersonStatistics(String projId) {
        model.getRealTimePersonStatistics(projId, new JsonCallback<ResponseData<List<RealTimePersonStatisticsInfo>>>(new TypeToken<ResponseData<List<RealTimePersonStatisticsInfo>>>() {
        }) {
            @Override
            protected void onError(int errcode, String errmsg) {
                if (isAttach) {
                    view.showError(errmsg);
                }
            }

            @Override
            public void onFinish() {
                super.onFinish();
                if (isAttach){
                    view.hideProgress();
                }
            }

            @Override
            public void onSuccess(ResponseData<List<RealTimePersonStatisticsInfo>> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showRealTimePersonStatistics(data.getResult());
                    } else {
                        view.showError(data.getErrmsg());
                    }
                }
            }
        });

    }

    @Override
    public void getRealTimeTeamStatistics(String projId) {
        model.getRealTimeTeamStatistics(projId, new JsonCallback<ResponseData<List<RealTimeTeamStatisticsInfo>>>(new TypeToken<ResponseData<List<RealTimeTeamStatisticsInfo>>>() {
        }) {
            @Override
            protected void onError(int errcode, String errmsg) {
                if (isAttach) {
                    view.showError(errmsg);
                }
            }

            @Override
            public void onFinish() {
                super.onFinish();
                if (isAttach){
                    view.hideProgress();
                }
            }

            @Override
            public void onSuccess(ResponseData<List<RealTimeTeamStatisticsInfo>> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showRealTimeTeamStatistics(data.getResult());
                    } else {
                        view.showError(data.getErrmsg());
                    }
                }
            }
        });
    }

    @Override
    public void getSafeQualityStatistics(String projId) {
        model.getSafeQualityStatistics(projId, new JsonCallback<ResponseData<SafeQualityStatisticsInfo>>(new TypeToken<ResponseData<SafeQualityStatisticsInfo>>() {
        }) {
            @Override
            protected void onError(int errcode, String errmsg) {
                if (isAttach) {
                    view.showError(errmsg);
                }
            }

            @Override
            public void onFinish() {
                super.onFinish();
                if (isAttach){
                    view.hideProgress();
                }
            }

            @Override
            public void onSuccess(ResponseData<SafeQualityStatisticsInfo> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showSafeQualityStatistics(data.getResult());
                    } else {
                        view.showError(data.getErrmsg());
                    }
                }
            }
        });
    }

    @Override
    public void getEnvironmentStatistics(String projId) {
        model.getEnvironmentStatistics(projId, new JsonCallback<ResponseData<EnvironmentStatisticsInfo>>(new TypeToken<ResponseData<EnvironmentStatisticsInfo>>() {
        }) {
            @Override
            protected void onError(int errcode, String errmsg) {
                if (isAttach) {
                    view.showError(errmsg);
                }
            }

            @Override
            public void onFinish() {
                super.onFinish();
                if (isAttach){
                    view.hideProgress();
                }
            }

            @Override
            public void onSuccess(ResponseData<EnvironmentStatisticsInfo> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showEnvironmentStatistics(data.getResult());
                    } else {
                        view.showError(data.getErrmsg());
                    }
                }
            }
        });
    }

}
