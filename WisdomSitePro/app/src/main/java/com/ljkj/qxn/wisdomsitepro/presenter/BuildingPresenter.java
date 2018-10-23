package com.ljkj.qxn.wisdomsitepro.presenter;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.safe.BuildingContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.BuildingInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.NullEntity;
import com.ljkj.qxn.wisdomsitepro.model.SafeProtectionModel;

import java.util.List;

import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;

public class BuildingPresenter extends BuildingContract.Presenter {
    public BuildingPresenter(BuildingContract.View view, SafeProtectionModel model) {
        super(view, model);
    }

    @Override
    public void getBuildList(String proId) {
        model.getBuildList(proId, new JsonCallback<ResponseData<List<BuildingInfo>>>(new TypeToken<ResponseData<List<BuildingInfo>>>() {
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
            public void onSuccess(ResponseData<List<BuildingInfo>> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showBuildings(data.getResult());
                    } else {
                        view.showError(data.getErrmsg());
                    }
                }
            }

        });
    }

    @Override
    public void deleteBuild(String id) {
        model.deleteBuild(id, new JsonCallback<ResponseData<NullEntity>>(new TypeToken<ResponseData<NullEntity>>() {
        }) {
            @Override
            public void onStart() {
                super.onStart();
                if (isAttach) {
                    view.showProgress("删除中...");
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
            public void onSuccess(ResponseData<NullEntity> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showDeleteBuilding();
                    } else {
                        view.showError(data.getErrmsg());
                    }
                }
            }
        });
    }

    @Override
    public void updateBuild(String id, String buildName, String proId) {
        model.updateBuild(id, buildName, proId, new JsonCallback<ResponseData<NullEntity>>(new TypeToken<ResponseData<NullEntity>>() {
        }) {
            @Override
            public void onStart() {
                super.onStart();
                if (isAttach) {
                    view.showProgress("修改中...");
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
            public void onSuccess(ResponseData<NullEntity> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showUpdateBuilding();
                    } else {
                        view.showError(data.getErrmsg());
                    }
                }
            }
        });
    }

    @Override
    public void addBuild(String buildName, String proId, String proCode, String proName) {
        model.addBuild(buildName, proId, proCode, proName, new JsonCallback<ResponseData<NullEntity>>(new TypeToken<ResponseData<NullEntity>>() {
        }) {
            @Override
            public void onStart() {
                super.onStart();
                if (isAttach) {
                    view.showProgress("新增中...");
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
            public void onSuccess(ResponseData<NullEntity> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showAddBuild();
                    } else {
                        view.showError(data.getErrmsg());
                    }
                }
            }
        });
    }

    @Override
    public void firstAddBuild(String buildName, int num, String proCode, String proId, String proName) {
        model.firstAddBuild(buildName, num, proCode, proId, proName, new JsonCallback<ResponseData<NullEntity>>(new TypeToken<ResponseData<NullEntity>>() {
        }) {
            @Override
            public void onStart() {
                super.onStart();
                if (isAttach) {
                    view.showProgress("新增中...");
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
            public void onSuccess(ResponseData<NullEntity> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showFirstAddBuild();
                    } else {
                        view.showError(data.getErrmsg());
                    }
                }
            }
        });
    }


}
