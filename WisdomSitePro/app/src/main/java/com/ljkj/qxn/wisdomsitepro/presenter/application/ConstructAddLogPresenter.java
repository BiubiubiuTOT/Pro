package com.ljkj.qxn.wisdomsitepro.presenter.application;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.application.ConstructAddLogContract;
import com.ljkj.qxn.wisdomsitepro.data.FileEntity;
import com.ljkj.qxn.wisdomsitepro.data.entity.ConstructAddLogInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.ConstructLogAddInfo;
import com.ljkj.qxn.wisdomsitepro.model.ApplicationModel;

import java.util.List;

import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;

/**
 * 类描述：新增施工日志
 * 创建人：rjf
 * 创建时间：2018/3/14 15:06.
 * 修改人：
 * 修改时间
 * 修改备注
 */

public class ConstructAddLogPresenter extends ConstructAddLogContract.Presenter {

    public ConstructAddLogPresenter(ConstructAddLogContract.View view, ApplicationModel model) {
        super(view, model);
    }

    @Override
    public void getConstructLogAddInfo(String proId) {
        model.getConstructLogAddInfo(proId, new JsonCallback<ResponseData<ConstructLogAddInfo>>(new TypeToken<ResponseData<ConstructLogAddInfo>>() {
        }) {

            @Override
            public void onSuccess(ResponseData<ConstructLogAddInfo> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showAddInfo(data.getResult());
                    } else {
                        view.showError(data.getErrmsg());
                    }
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
    public void saveConstructLog(String constructionUnit, String date, String emergency,
                                 String maxMinTemp, String proHead, String proId,
                                 String proName, String proNo, String production, String qualitySafety,
                                 String recorder, String weather, String wind, List<FileEntity> file) {
        model.saveConstructLog(constructionUnit, date, emergency, maxMinTemp, proHead, proId,
                proName, proNo, production, qualitySafety, recorder, weather, wind, file,
                new JsonCallback<ResponseData<ConstructAddLogInfo>>(new TypeToken<ResponseData<ConstructAddLogInfo>>() {
                }) {

                    @Override
                    public void onStart() {
                        super.onStart();
                        if (isAttach) {
                            view.showProgress("数据提交中...");
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
                    public void onSuccess(ResponseData<ConstructAddLogInfo> data) {
                        if (isAttach) {
                            if (data.isSuccess()) {
                                view.showAddResult(data.getResult());
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
