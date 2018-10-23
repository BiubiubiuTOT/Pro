package com.ljkj.qxn.wisdomsitepro.presenter.supervisor;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.supervisor.SideStationRecordContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.PageInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.SideStationRecordUnit;
import com.ljkj.qxn.wisdomsitepro.data.entity.SiteStationRecorderManageDetailInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.SiteStationRecorderManageInfo;
import com.ljkj.qxn.wisdomsitepro.model.SupervisorModel;

import java.util.List;

import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;

/**
 * 类描述：旁站记录管理
 * 创建人：lxx
 * 创建时间：2018/9/3
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SideStationRecordPresenter extends SideStationRecordContract.Presenter {

    public SideStationRecordPresenter(SideStationRecordContract.View view, SupervisorModel model) {
        super(view, model);
    }

    @Override
    public void getSideStationRecordList(String proId, String recordName, String beginTime, int pageNum, int pageSize) {
        model.getSideStationRecordList(proId, recordName, beginTime, pageNum, pageSize,
                new JsonCallback<ResponseData<PageInfo<SiteStationRecorderManageInfo>>>(new TypeToken<ResponseData<PageInfo<SiteStationRecorderManageInfo>>>() {
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
                    public void onSuccess(ResponseData<PageInfo<SiteStationRecorderManageInfo>> data) {
                        if (isAttach) {
                            if (data.isSuccess()) {
                                view.showSideStationRecordList(data.getResult());
                            } else {
                                view.showError(data.getErrmsg());
                            }
                        }
                    }

                });
    }

    @Override
    public void getSideStationRecordDetail(String id) {
        model.getSideRecordDetail(id,
                new JsonCallback<ResponseData<SiteStationRecorderManageDetailInfo>>(new TypeToken<ResponseData<SiteStationRecorderManageDetailInfo>>() {
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
                    public void onSuccess(ResponseData<SiteStationRecorderManageDetailInfo> data) {
                        if (isAttach) {
                            if (data.isSuccess()) {
                                view.showSideStationRecordDetail(data.getResult());
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
        model.deleteSideRecord(id, name, userId,
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
    public void addSideStationRecord(SiteStationRecorderManageDetailInfo info) {
        model.addSideRecord(info,
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

    @Override
    public void getUnits(String projId) {
        model.getSideRecordUnits(projId,
                new JsonCallback<ResponseData<List<SideStationRecordUnit>>>(new TypeToken<ResponseData<List<SideStationRecordUnit>>>() {
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
                    public void onSuccess(ResponseData<List<SideStationRecordUnit>> data) {
                        if (isAttach) {
                            if (data.isSuccess()) {
                                view.showUnits(data.getResult());
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

}
