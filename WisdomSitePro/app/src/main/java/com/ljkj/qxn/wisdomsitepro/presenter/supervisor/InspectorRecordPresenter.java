package com.ljkj.qxn.wisdomsitepro.presenter.supervisor;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.supervisor.InspectorRecordContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.InspectorRecorderManageInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.PageInfo;
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
public class InspectorRecordPresenter extends InspectorRecordContract.Presenter {

    public InspectorRecordPresenter(InspectorRecordContract.View view, SupervisorModel model) {
        super(view, model);
    }

    @Override
    public void getInspectorRecordList(String proId, String recordName, String beginTime, int pageNum, int pageSize) {
        model.getInspectorRecordList(proId, recordName, beginTime, pageNum, pageSize,
                new JsonCallback<ResponseData<PageInfo<InspectorRecorderManageInfo>>>(new TypeToken<ResponseData<PageInfo<InspectorRecorderManageInfo>>>() {
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
                    public void onSuccess(ResponseData<PageInfo<InspectorRecorderManageInfo>> data) {
                        if (isAttach) {
                            if (data.isSuccess()) {
                                view.showInspectorRecordList(data.getResult());
                            } else {
                                view.showError(data.getErrmsg());
                            }
                        }
                    }

                });
    }

    @Override
    public void getInspectorRecordDetail(String id) {
        model.getInspectorRecordDetail(id,
                new JsonCallback<ResponseData<InspectorRecorderManageInfo>>(new TypeToken<ResponseData<InspectorRecorderManageInfo>>() {
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
                    public void onSuccess(ResponseData<InspectorRecorderManageInfo> data) {
                        if (isAttach) {
                            if (data.isSuccess()) {
                                view.showInspectorRecordDetail(data.getResult());
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
        model.deleteInspectorRecord(id, name, userId,
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
    public void addInspectorRecord(InspectorRecorderManageInfo info) {
        model.addInspectorRecord(info,
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
