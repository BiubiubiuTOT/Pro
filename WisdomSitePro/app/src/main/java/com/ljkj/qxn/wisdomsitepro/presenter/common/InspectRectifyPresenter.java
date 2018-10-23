package com.ljkj.qxn.wisdomsitepro.presenter.common;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.common.InspectRectifyContract;
import com.ljkj.qxn.wisdomsitepro.model.QualityModel;

import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;

/**
 * 类描述：巡检整改
 * 创建人：rjf
 * 创建时间：2018/3/19 17:55.
 * 修改人：
 * 修改时间
 * 修改备注
 */

public class InspectRectifyPresenter extends InspectRectifyContract.Presenter {
    public InspectRectifyPresenter(InspectRectifyContract.View view, QualityModel model) {
        super(view, model);
    }

    @Override
    public void saveRectifyDetail(String id, String zgDetail) {
        model.saveRectifyDetail(id, zgDetail, new JsonCallback<ResponseData<String>>(new TypeToken<ResponseData<String>>() {
        }) {
            @Override
            public void onStart() {
                super.onStart();
                if (isAttach) {
                    view.showProgress("数据提交中");
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
            public void onSuccess(ResponseData<String> data) {

                if (isAttach) {
                    if (data.isSuccess()) {
                        view.dealResult(data.getResult());
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

    @Override
    public void saveRectifyDetail(String id, String zgDetail, String zgFlag) {
        model.saveRectifyDetail(id, zgDetail, zgFlag, new JsonCallback<ResponseData<String>>(new TypeToken<ResponseData<String>>() {
        }) {
            @Override
            public void onStart() {
                super.onStart();
                if (isAttach) {
                    view.showProgress("数据提交中");
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
            public void onSuccess(ResponseData<String> data) {

                if (isAttach) {
                    if (data.isSuccess()) {
                        view.dealResult(data.getResult());
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

    @Override
    public void checkRectifyDetail(String id, String statusHistory, String noThroughReason, String bizId) {
        model.checkRectifyDetail(id, statusHistory, noThroughReason, bizId, new JsonCallback<ResponseData>(new TypeToken<ResponseData>() {
        }) {
            @Override
            public void onStart() {
                super.onStart();
                if (isAttach) {
                    view.showProgress("数据提交中");
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
            public void onSuccess(ResponseData data) {

                if (isAttach) {
                    if (data.isSuccess()) {
                        view.dealResult("");
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


    /**
     * 立即、重新整改
     *
     * @param bizId         当前巡检详情id
     * @param postMan       发文人
     * @param reformDetail  整改详情
     * @param statusHistory 记录状态，立即整改传2，重新整改传4
     * @param teamLeader    班组负责人
     */
    public void approvalSave(String bizId, String postMan, String reformDetail, String statusHistory, String teamLeader) {
        model.approvalSave(bizId, postMan, reformDetail, statusHistory, teamLeader, new JsonCallback<ResponseData<String>>(new TypeToken<ResponseData<String>>() {
        }) {

            @Override
            public void onStart() {
                super.onStart();
                if (isAttach) {
                    view.showProgress("数据提交中");
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
            public void onSuccess(ResponseData<String> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.dealResult(data.getResult());
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
