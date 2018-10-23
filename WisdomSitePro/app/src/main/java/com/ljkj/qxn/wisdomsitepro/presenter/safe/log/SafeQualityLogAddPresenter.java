package com.ljkj.qxn.wisdomsitepro.presenter.safe.log;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.safe.log.SafeQualityLogAddContract;
import com.ljkj.qxn.wisdomsitepro.data.FileEntity;
import com.ljkj.qxn.wisdomsitepro.data.entity.NullEntity;
import com.ljkj.qxn.wisdomsitepro.model.SafeQualityLogModel;

import java.util.List;

import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;

public class SafeQualityLogAddPresenter extends SafeQualityLogAddContract.Presenter {
    public SafeQualityLogAddPresenter(SafeQualityLogAddContract.View view, SafeQualityLogModel model) {
        super(view, model);
    }

    @Override
    public void addSafeLog(String conProcess, String conSite, String recordDate, String createUserId, String createUsername,
                           String projCode, String projId, String securityProblem, String securityStatus, String weather, List<FileEntity> file) {
        model.addSafeLog(conProcess, conSite, recordDate, createUserId, createUsername, projCode, projId, securityProblem,
                securityStatus, weather, file, new JsonCallback<ResponseData<NullEntity>>(new TypeToken<ResponseData<NullEntity>>() {
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
                                view.showAddSafe();
                            } else {
                                view.showError(data.getErrmsg());
                            }

                        }
                    }
                });
    }

    @Override
    public void addQualityLog(String constructionDynamic, String constructionPosition, String recordDate, String projId,
                              String qualityProblemResult, String qualitySituation, String weather, List<FileEntity> file, String createUserName) {
        model.addQualityLog(constructionDynamic, constructionPosition, recordDate, projId,
                qualityProblemResult, qualitySituation, weather, file, createUserName, new JsonCallback<ResponseData<NullEntity>>(new TypeToken<ResponseData<NullEntity>>() {
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
                                view.showAddQuality();
                            } else {
                                view.showAddQuality();
                            }
                        }
                    }

                });

    }
}
