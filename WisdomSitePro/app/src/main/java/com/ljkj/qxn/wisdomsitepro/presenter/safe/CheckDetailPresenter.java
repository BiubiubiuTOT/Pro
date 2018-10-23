package com.ljkj.qxn.wisdomsitepro.presenter.safe;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.safe.CheckDetailContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.CheckDetail;
import com.ljkj.qxn.wisdomsitepro.model.SafeModel;

import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;

/**
 * 类描述：检查详情
 * 创建人：lxx
 * 创建时间：2018/9/7
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class CheckDetailPresenter extends CheckDetailContract.Presenter {

    public CheckDetailPresenter(CheckDetailContract.View view, SafeModel model) {
        super(view, model);
    }

    @Override
    public void getSafeCheckDetail(String id) {
        model.getSafeCheckDetail(id, new JsonCallback<ResponseData<CheckDetail>>(new TypeToken<ResponseData<CheckDetail>>() {
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
            public void onSuccess(ResponseData<CheckDetail> data) {
                if (isAttach) {
                    if (data.getErrcode() == 200) {
                        view.showSafeCheckDetail(data.getResult());
                    } else {
                        view.showError(data.getErrmsg());
                    }
                }
            }
        });
    }

    @Override
    public void getQualityCheckDetail(String id) {
        model.getQualityCheckDetail(id, new JsonCallback<ResponseData<CheckDetail>>(new TypeToken<ResponseData<CheckDetail>>() {
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
            public void onSuccess(ResponseData<CheckDetail> data) {
                if (isAttach) {
                    if (data.getErrcode() == 200) {
                        view.showQualityCheckDetail(data.getResult());
                    } else {
                        view.showError(data.getErrmsg());
                    }
                }
            }
        });
    }

    @Override
    public void getSupervisorCheckDetail(String id) {
        model.getSupervisorCheckDetail(id, new JsonCallback<ResponseData<CheckDetail>>(new TypeToken<ResponseData<CheckDetail>>() {
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
            public void onSuccess(ResponseData<CheckDetail> data) {
                if (isAttach) {
                    if (data.getErrcode() == 200) {
                        view.showSupervisorCheckDetail(data.getResult());
                    } else {
                        view.showError(data.getErrmsg());
                    }
                }
            }
        });
    }


}
