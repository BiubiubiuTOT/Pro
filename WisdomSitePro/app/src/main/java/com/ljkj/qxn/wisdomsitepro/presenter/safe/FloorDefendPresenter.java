package com.ljkj.qxn.wisdomsitepro.presenter.safe;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.safe.FloorDefendContract;
import com.ljkj.qxn.wisdomsitepro.data.FileEntity;
import com.ljkj.qxn.wisdomsitepro.data.entity.FloorDetail;
import com.ljkj.qxn.wisdomsitepro.data.entity.NullEntity;
import com.ljkj.qxn.wisdomsitepro.model.SafeProtectionModel;

import java.util.List;

import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;

public class FloorDefendPresenter extends FloorDefendContract.Presenter {
    public FloorDefendPresenter(FloorDefendContract.View view, SafeProtectionModel model) {
        super(view, model);
    }

    @Override
    public void getFloorDetail(String floorId) {
        model.getFloorDetail(floorId, new JsonCallback<ResponseData<FloorDetail>>(new TypeToken<ResponseData<FloorDetail>>() {
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
            public void onSuccess(ResponseData<FloorDetail> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showFloorDetail(data.getResult());
                    } else {
                        view.showError(data.getErrmsg());
                    }
                }
            }
        });
    }

    @Override
    public void addFloorDetail(String floorId,String projCode, String projId, String content, List<FileEntity> file) {
        model.addFloorDetail(floorId,projCode, projId, content, file, new JsonCallback<ResponseData<NullEntity>>(new TypeToken<ResponseData<NullEntity>>() {
        }) {
            @Override
            public void onStart() {
                super.onStart();
                if (isAttach) {
                    view.showProgress("提交中...");
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
                        view.showAddFloorDetail();
                    } else {
                        view.showError(data.getErrmsg());
                    }
                }
            }

        });
    }
}
