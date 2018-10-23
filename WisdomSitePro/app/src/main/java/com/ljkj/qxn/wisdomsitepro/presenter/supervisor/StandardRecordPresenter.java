package com.ljkj.qxn.wisdomsitepro.presenter.supervisor;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.supervisor.StandardRecordContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.PageInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.SupervisorRecordManageInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.SupervisorStandardInfo;
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
public class StandardRecordPresenter extends StandardRecordContract.Presenter {

    public StandardRecordPresenter(StandardRecordContract.View view, SupervisorModel model) {
        super(view, model);
    }

    @Override
    public void getStandardRecordList(String proId, String recordName, int pageNum, int pageSize) {
        model.getStandardRecordList(proId, recordName, pageNum, pageSize,
                new JsonCallback<ResponseData<PageInfo<SupervisorStandardInfo>>>(new TypeToken<ResponseData<PageInfo<SupervisorStandardInfo>>>() {
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
                    public void onSuccess(ResponseData<PageInfo<SupervisorStandardInfo>> data) {
                        if (isAttach) {
                            if (data.isSuccess()) {
                                view.showStandardRecordList(data.getResult());
                            } else {
                                view.showError(data.getErrmsg());
                            }
                        }
                    }

                });
    }

    @Override
    public void getStandardRecordDetail(String id) {
        model.getStandardRecordDetail(id,
                new JsonCallback<ResponseData<SupervisorStandardInfo>>(new TypeToken<ResponseData<SupervisorStandardInfo>>() {
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
                    public void onSuccess(ResponseData<SupervisorStandardInfo> data) {
                        if (isAttach) {
                            if (data.isSuccess()) {
                                view.showStandardRecordDetail(data.getResult());
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
        model.deleteStandardRecord(id, name, userId,
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
    public void addStandardRecord(SupervisorRecordManageInfo info) {
        model.addStandardRecord(info,
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
