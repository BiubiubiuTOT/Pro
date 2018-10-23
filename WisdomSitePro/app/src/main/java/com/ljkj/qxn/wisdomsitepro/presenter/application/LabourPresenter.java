package com.ljkj.qxn.wisdomsitepro.presenter.application;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.application.LabourContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.LabourDetail;
import com.ljkj.qxn.wisdomsitepro.data.entity.LabourInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.PageInfo;
import com.ljkj.qxn.wisdomsitepro.model.ApplicationModel;

import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;

/**
 * 类描述：劳务人员列表
 * 创建人：mhl
 * 创建时间：2018/2/27 9:37
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class LabourPresenter extends LabourContract.Presenter {

    public LabourPresenter(LabourContract.View view, ApplicationModel model) {
        super(view, model);
    }

    @Override
    public void listPageLabourInfo(String searchKey, int page, String proId, int rows, String id) {

        model.laborList(searchKey, page, proId, rows, id, new JsonCallback<ResponseData<PageInfo<LabourInfo>>>(new TypeToken<ResponseData<PageInfo<LabourInfo>>>() {
        }) {

            @Override
            public void onFinish() {
                super.onFinish();
                if (isAttach) {
                    view.hideProgress();
                }
            }

            @Override
            public void onSuccess(ResponseData<PageInfo<LabourInfo>> data) {

                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showPageLabourInfo(data.getResult());
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
    public void getLabourDetail(String id) {
        model.getLaborDetail(id, new JsonCallback<ResponseData<LabourDetail>>(new TypeToken<ResponseData<LabourDetail>>() {
        }) {

            @Override
            public void onFinish() {
                super.onFinish();
                if (isAttach) {
                    view.hideProgress();
                }
            }

            @Override
            public void onSuccess(ResponseData<LabourDetail> data) {

                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showLabourDetail(data.getResult());
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
