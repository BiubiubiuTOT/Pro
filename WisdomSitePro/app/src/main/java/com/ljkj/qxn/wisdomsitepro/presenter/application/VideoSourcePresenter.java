package com.ljkj.qxn.wisdomsitepro.presenter.application;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.application.VideoSourceContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.VideoSourceInfo;
import com.ljkj.qxn.wisdomsitepro.model.ApplicationModel;

import java.util.ArrayList;
import java.util.List;

import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;

/**
 * 类描述：视频源信息
 * 创建人：lxx
 * 创建时间：2018/4/20
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class VideoSourcePresenter extends VideoSourceContract.Presenter {
    public VideoSourcePresenter(VideoSourceContract.View view, ApplicationModel model) {
        super(view, model);
    }

    @Override
    public void getVideoSource(String proId) {
        model.getVideoSource(proId, new JsonCallback<ResponseData<List<VideoSourceInfo>>>(new TypeToken<ResponseData<List<VideoSourceInfo>>>() {
        }) {

            @Override
            public void onStart() {
                super.onStart();
                if (isAttach) {
                    view.showProgress("请求数据中...");
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
            public void onSuccess(ResponseData<List<VideoSourceInfo>> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showVideoList(data.getResult());
                    } else {
                        view.showError(data.getErrmsg());
                    }
                }
            }

        });
    }

    @Override
    public void getVideoMonitor(String proId) {
        model.getVideoMonitorSource(proId, new JsonCallback<ResponseData<List<ArrayList<VideoSourceInfo>>>>(new TypeToken<ResponseData<List<ArrayList<VideoSourceInfo>>>>() {
        }) {
            @Override
            public void onStart() {
                super.onStart();
                if (isAttach) {
                    view.showProgress("请求数据中...");
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
            public void onSuccess(ResponseData<List<ArrayList<VideoSourceInfo>>> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showVideoMonitor(data.getResult());
                    } else {
                        view.showError(data.getErrmsg());
                    }
                }
            }

        });
    }
}
