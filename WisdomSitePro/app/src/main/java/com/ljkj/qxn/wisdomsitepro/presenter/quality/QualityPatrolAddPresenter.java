package com.ljkj.qxn.wisdomsitepro.presenter.quality;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.quality.QualityPatrolAddContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.LabourInfo;
import com.ljkj.qxn.wisdomsitepro.model.QualityModel;

import java.util.List;

import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;

/**
 * 类描述：下发责任整改通知书
 * 创建人：mhl
 * 创建时间：2018/3/17 9:40
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class QualityPatrolAddPresenter extends QualityPatrolAddContract.Presenter {

    public QualityPatrolAddPresenter(QualityPatrolAddContract.View view, QualityModel model) {
        super(view, model);
    }

    @Override
    public void addQualityPatrol(String bzfzr, String code, String fwr, String lossType, String status, String upTime, String type, String whyType, String proId, String zgbz, String zgnr) {

        model.saveApproval(bzfzr, code, fwr, lossType, status, upTime, type, whyType, proId, zgbz, zgnr, new JsonCallback<ResponseData<String>>(new TypeToken<ResponseData<String>>() {
        }) {

            @Override
            public void onStart() {
                super.onStart();
                if (isAttach) {
                    view.showProgress("数据提交中");
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
            public void onSuccess(ResponseData<String> data) {

                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showQualityPatrolAddResult(data);
                    } else {
                        view.showError(data.getErrmsg());
                    }
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
    public void listManageList(String proId) {

        model.listManageList(proId, new JsonCallback<ResponseData<List<LabourInfo>>>(new TypeToken<ResponseData<List<LabourInfo>>>() {
        }) {

            @Override
            public void onSuccess(ResponseData<List<LabourInfo>> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showManagerList(data.getResult());
                    } else {
                        view.showError(data.getErrmsg());
                    }
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
}
