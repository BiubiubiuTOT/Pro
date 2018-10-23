package com.ljkj.qxn.wisdomsitepro.presenter.supervisor;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.supervisor.SupervisorRecordContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.PageInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.SupervisorRecordManageInfo;
import com.ljkj.qxn.wisdomsitepro.model.SupervisorModel;

import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;

/**
 * 类描述：巡视记录管理
 * 创建人：lxx
 * 创建时间：2018/9/3
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SupervisorRecordPresenter extends SupervisorRecordContract.Presenter {

    public SupervisorRecordPresenter(SupervisorRecordContract.View view, SupervisorModel model) {
        super(view, model);
    }

    @Override
    public void getSupervisorRecordList(String proId, String recordName, String beginTime, int pageNum, int pageSize) {
        model.getSupervisorRecordList(proId, recordName, beginTime, pageNum, pageSize,
                new JsonCallback<ResponseData<PageInfo<SupervisorRecordManageInfo>>>(new TypeToken<ResponseData<PageInfo<SupervisorRecordManageInfo>>>() {
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
                    public void onSuccess(ResponseData<PageInfo<SupervisorRecordManageInfo>> data) {
                        if (isAttach) {
                            if (data.isSuccess()) {
                                view.showSupervisorRecordList(data.getResult());
                            } else {
                                view.showError(data.getErrmsg());
                            }
                        }
                    }

                });
    }

    @Override
    public void getSupervisorRecordDetail(String id) {
        model.getSupervisorRecordDetail(id,
                new JsonCallback<ResponseData<SupervisorRecordManageInfo>>(new TypeToken<ResponseData<SupervisorRecordManageInfo>>() {
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
                    public void onSuccess(ResponseData<SupervisorRecordManageInfo> data) {
                        if (isAttach) {
                            if (data.isSuccess()) {
                                view.showSupervisorRecordDetail(data.getResult());
                            } else {
                                view.showError(data.getErrmsg());
                            }
                        }
                    }

                    @Override
                    public void onStart() {
                        super.onStart();
                        if (isAttach)
                            view.showProgress("加载中...");
                    }
                });
    }

    @Override
    public void deleteRecord(String id, String name, String userId) {
        model.deleteSupervisorRecord(id, name, userId,
                new JsonCallback<ResponseData<String>>(new TypeToken<ResponseData<String>>() {
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
                    public void onSuccess(ResponseData<String> data) {
                        if (isAttach) {
                            if (data.isSuccess()) {
                                view.dealDeleteRecordResult();
                            } else {
                                view.showError(data.getErrmsg());
                            }
                        }
                    }

                    @Override
                    public void onStart() {
                        super.onStart();
                        if (isAttach) {
                            view.showProgress("删除记录中...");
                        }
                    }

                });
    }

    @Override
    public void addSupervisorRecord(SupervisorRecordManageInfo info) {
        model.addSupervisorRecord(info,
                new JsonCallback<ResponseData<PageInfo<String>>>(new TypeToken<ResponseData<PageInfo<String>>>() {
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
                    public void onSuccess(ResponseData<PageInfo<String>> data) {
                        if (isAttach) {
                            if (data.isSuccess()) {
                                view.dealAddRecordResult();
                            } else {
                                view.showError(data.getErrmsg());
                            }
                        }
                    }

                    @Override
                    public void onStart() {
                        super.onStart();
                        if (isAttach) {
                            view.showProgress("添加记录中...");
                        }
                    }
                });
    }

}
