package com.ljkj.qxn.wisdomsitepro.presenter.safe;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.safe.FloorContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.FloorInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.NullEntity;
import com.ljkj.qxn.wisdomsitepro.model.SafeProtectionModel;

import java.util.List;

import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;

public class FloorPresenter extends FloorContract.Presenter {

    public FloorPresenter(FloorContract.View view, SafeProtectionModel model) {
        super(view, model);
    }

    @Override
    public void getFloorList(String buildId, String projCode, String projId) {
        model.getFloorList(buildId, projCode, projId, new JsonCallback<ResponseData<List<FloorInfo>>>(new TypeToken<ResponseData<List<FloorInfo>>>() {
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
            public void onSuccess(ResponseData<List<FloorInfo>> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showFloorList(data.getResult());
                    } else {
                        view.showError(data.getErrmsg());
                    }
                }
            }
        });
    }

    @Override
    public void firstAddFloors(String buildId, int num, String projCode, String projId) {
        model.firstAddFloors(buildId, num, projCode, projId, new JsonCallback<ResponseData<NullEntity>>(new TypeToken<ResponseData<NullEntity>>() {
        }) {
            @Override
            public void onStart() {
                super.onStart();
                if (isAttach) {
                    view.showProgress("新增楼层中...");
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
                        view.showFirstAddFloors();
                    } else {
                        view.showError(data.getErrmsg());
                    }
                }
            }
        });
    }

    @Override
    public void deleteFloor(String floorId) {
        model.deleteFloor(floorId, new JsonCallback<ResponseData<NullEntity>>(new TypeToken<ResponseData<NullEntity>>() {
        }) {
            @Override
            public void onStart() {
                super.onStart();
                if (isAttach) {
                    view.showProgress("删除楼层中...");
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
                        view.showDeleteFloor();
                    } else {
                        view.showError(data.getErrmsg());
                    }
                }
            }

        });
    }

    @Override
    public void addFloor(String buildId, String projCode, String projId) {
        model.addFloor(buildId, projCode, projId, new JsonCallback<ResponseData<NullEntity>>(new TypeToken<ResponseData<NullEntity>>() {
        }) {
            @Override
            public void onStart() {
                super.onStart();
                if (isAttach) {
                    view.showProgress("新增楼层中...");
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
                        view.showAddFloor();
                    } else {
                        view.showError(data.getErrmsg());
                    }
                }
            }

        });
    }


}
