package com.ljkj.qxn.wisdomsitepro.presenter.application;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.application.TowerCraneEquipmentContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.NullEntity;
import com.ljkj.qxn.wisdomsitepro.data.entity.TowerCraneEquipmentDetail;
import com.ljkj.qxn.wisdomsitepro.data.entity.TowerCraneEquipmentInfo;
import com.ljkj.qxn.wisdomsitepro.model.EquipmentModel;

import java.util.List;

import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;

/**
 * 类描述：吊塔设备列表
 * 创建人：lxx
 * 创建时间：2018/8/1
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class TowerCraneEquipmentPresenter extends TowerCraneEquipmentContract.Presenter {
    public TowerCraneEquipmentPresenter(TowerCraneEquipmentContract.View view, EquipmentModel model) {
        super(view, model);
    }

    @Override
    public void getTowerCraneEquipmentList(String proId) {

        model.getTowerCraneEquipmentList(proId, new JsonCallback<ResponseData<List<TowerCraneEquipmentInfo>>>(new TypeToken<ResponseData<List<TowerCraneEquipmentInfo>>>() {
        }) {

            @Override
            public void onSuccess(ResponseData<List<TowerCraneEquipmentInfo>> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showTowerCraneEquipmentList(data.getResult());
                    } else {
                        view.showError(data.getErrmsg());
                    }
                }
            }

            @Override
            public void onStart() {
                super.onStart();
                if (isAttach) {
                    view.showProgress("正在加载中...");
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
    public void getTowerCraneEquipmentDetail(String deviceCode) {
        model.getTowerCraneEquipmentDetail(deviceCode, new JsonCallback<ResponseData<TowerCraneEquipmentDetail>>(new TypeToken<ResponseData<TowerCraneEquipmentDetail>>() {
        }) {

            @Override
            public void onSuccess(ResponseData<TowerCraneEquipmentDetail> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showTowerCraneDetail(data.getResult() == null ? new TowerCraneEquipmentDetail() : data.getResult());
                    } else {
                        view.showError(data.getErrmsg());
                    }
                }
            }

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
        });
    }

    @Override
    public void getTowerCraneEquipmentRecord(String deviceCode, int type) {
        model.getTowerCraneEquipmentRecord(deviceCode, type, new JsonCallback<ResponseData<NullEntity>>(new TypeToken<ResponseData<NullEntity>>() {
        }) {
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
                        view.showTowerCraneRecord();
                    } else {
                        view.showError(data.getErrmsg());
                    }
                }
            }

        });
    }


}
