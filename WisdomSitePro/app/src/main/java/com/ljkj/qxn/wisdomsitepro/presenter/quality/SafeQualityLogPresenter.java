package com.ljkj.qxn.wisdomsitepro.presenter.quality;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.quality.SafeQualityLogContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.PageInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.SafeQualityLogDetail;
import com.ljkj.qxn.wisdomsitepro.data.entity.SafeQualityLogInfo;
import com.ljkj.qxn.wisdomsitepro.model.QualityModel;

import java.io.File;
import java.util.List;

import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;

/**
 * 类描述：安全/质量日志
 * 创建人：lxx
 * 创建时间：2018/7/9
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SafeQualityLogPresenter extends SafeQualityLogContract.Presenter {
    public SafeQualityLogPresenter(SafeQualityLogContract.View view, QualityModel model) {
        super(view, model);
    }

    @Override
    public void getLogList(String projectId, int type, int page, int rows) {
        model.getSafeQualityLogList(projectId, type, page, rows, new JsonCallback<ResponseData<PageInfo<SafeQualityLogInfo>>>(new TypeToken<ResponseData<PageInfo<SafeQualityLogInfo>>>() {
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
            public void onSuccess(ResponseData<PageInfo<SafeQualityLogInfo>> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showLogList(data.getResult());
                    } else {
                        view.showError(data.getErrmsg());
                    }
                }
            }

        });
    }

    @Override
    public void getLogDetail(String id) {
        model.getSafeQualityLogDetail(id, new JsonCallback<ResponseData<SafeQualityLogDetail>>(new TypeToken<ResponseData<SafeQualityLogDetail>>() {
        }) {

            @Override
            public void onStart() {
                super.onStart();
                if (isAttach) {
                    view.showProgress("正在加载");
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
            public void onSuccess(ResponseData<SafeQualityLogDetail> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showLogDetail(data.getResult());
                    } else {
                        view.showError(data.getErrmsg());
                    }
                }
            }
        });
    }

    @Override
    public void addLog(String proId, int type, final String date, String weather, String constructionSite,
                       String constructionDynamic, String status, String handleSituation, List<File> imageList) {
        model.addSafeQualityLog(proId, type, date, weather, constructionSite, constructionDynamic, status, handleSituation, imageList,
                new JsonCallback<ResponseData<String>>(new TypeToken<ResponseData<String>>() {
                }) {

                    @Override
                    public void onStart() {
                        super.onStart();
                        if (isAttach) {
                            view.showProgress("正在提交");
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
                    public void onSuccess(ResponseData<String> data) {
                        if (isAttach) {
                            if (data.isSuccess()) {
                                view.showAddLogSuccess();
                            } else {
                                view.showError(data.getErrmsg());
                            }
                        }
                    }
                });
    }

}
