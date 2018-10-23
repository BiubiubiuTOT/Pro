package com.ljkj.qxn.wisdomsitepro.presenter.safe;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.safe.SafePatrolContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.PageInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.SafePatrolDetailInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.SafePatrolInfo;
import com.ljkj.qxn.wisdomsitepro.model.SafeModel;

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

public class SafePatrolPresenter extends SafePatrolContract.Presenter {


    public SafePatrolPresenter(SafePatrolContract.View view, SafeModel model) {
        super(view, model);
    }


    @Override
    public void getSafePatrolList(String proId, String accidentReason, String changeGroup, String startDate, String endDate, String isDealWith, int rows, int page) {
        model.getSafePatrolListV2(proId, accidentReason, changeGroup, startDate, endDate, isDealWith, rows, page,
                new JsonCallback<ResponseData<PageInfo<SafePatrolInfo>>>(new TypeToken<ResponseData<PageInfo<SafePatrolInfo>>>() {
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
                    public void onSuccess(ResponseData<PageInfo<SafePatrolInfo>> data) {
                        if (isAttach) {
                            if (data.isSuccess()) {
                                view.showSafePatrolList(data.getResult());
                            } else {
                                view.showError(data.getErrmsg());
                            }
                        }
                    }
                });
    }

    @Override
    public void getSafePatrolMessageList(String proId, int rows, int page) {
        model.getSafePatrolMessageListV2(proId, rows, page,
                new JsonCallback<ResponseData<PageInfo<SafePatrolInfo>>>(new TypeToken<ResponseData<PageInfo<SafePatrolInfo>>>() {
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
                    public void onSuccess(ResponseData<PageInfo<SafePatrolInfo>> data) {
                        if (isAttach) {
                            if (data.isSuccess()) {
                                view.showSafePatrolList(data.getResult());
                            } else {
                                view.showError(data.getErrmsg());
                            }
                        }
                    }
                });
    }

    @Override
    public void getSafePatrolDetail(String id) {
        model.getSafePatrolDetail(id,
                new JsonCallback<ResponseData<SafePatrolDetailInfo>>(new TypeToken<ResponseData<SafePatrolDetailInfo>>() {
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
                    public void onSuccess(ResponseData<SafePatrolDetailInfo> data) {
                        if (isAttach) {
                            if (data.isSuccess()) {
                                view.showSafePatrolDetail(data.getResult());
                            } else {
                                view.showError(data.getErrmsg());
                            }
                        }
                    }
                });
    }
}
