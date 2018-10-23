package com.ljkj.qxn.wisdomsitepro.presenter.quality;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.quality.QualityPatrolContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.PageInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.QualityPatrolDetailInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.QualityPatrolInfo;
import com.ljkj.qxn.wisdomsitepro.model.QualityModel;

import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;

/**
 * 类描述：巡检
 * 创建人：liufei
 * 创建时间：2018/3/12 13:43
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class QualityPatrolPresenter extends QualityPatrolContract.Presenter {


    public QualityPatrolPresenter(QualityPatrolContract.View view, QualityModel model) {
        super(view, model);
    }


    @Override
    public void getQualityPatrolList(String proId, String accidentReason, String changeGroup, String startDate, String endDate, int rows, int page) {
        model.getQualityPatrolListV2(proId, accidentReason, changeGroup, startDate, endDate, rows, page,
                new JsonCallback<ResponseData<PageInfo<QualityPatrolInfo>>>(new TypeToken<ResponseData<PageInfo<QualityPatrolInfo>>>() {
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
                    public void onSuccess(ResponseData<PageInfo<QualityPatrolInfo>> data) {
                        if (isAttach) {
                            if (data.isSuccess()) {
                                view.showQualityPatrolList(data.getResult());
                            } else {
                                view.showError(data.getErrmsg());
                            }
                        }
                    }
                });
    }

    @Override
    public void getQualityPatrolMessageList(String proId, int rows, int page) {
        model.getQualityPatrolMessageListV2(proId, rows, page,
                new JsonCallback<ResponseData<PageInfo<QualityPatrolInfo>>>(new TypeToken<ResponseData<PageInfo<QualityPatrolInfo>>>() {
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
                    public void onSuccess(ResponseData<PageInfo<QualityPatrolInfo>> data) {
                        if (isAttach) {
                            if (data.isSuccess()) {
                                view.showQualityPatrolList(data.getResult());
                            } else {
                                view.showError(data.getErrmsg());
                            }
                        }
                    }
                });
    }

    @Override
    public void getQualityPatrolDetail(String id) {
        model.getQualityPatrolDetail(id,
                new JsonCallback<ResponseData<QualityPatrolDetailInfo>>(new TypeToken<ResponseData<QualityPatrolDetailInfo>>() {
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
                    public void onSuccess(ResponseData<QualityPatrolDetailInfo> data) {
                        if (isAttach) {
                            if (data.isSuccess()) {
                                view.showQualityPatrolDetail(data.getResult());
                            } else {
                                view.showError(data.getErrmsg());
                            }
                        }
                    }
                });
    }
}
