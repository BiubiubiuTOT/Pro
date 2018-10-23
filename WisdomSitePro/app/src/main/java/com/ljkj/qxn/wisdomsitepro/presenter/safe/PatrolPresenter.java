package com.ljkj.qxn.wisdomsitepro.presenter.safe;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.safe.PatrolContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.PageInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.PatrolInfo;
import com.ljkj.qxn.wisdomsitepro.model.SafeModel;

import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;

/**
 * 类描述：巡检
 * 创建人：liufei
 * 创建时间：2018/3/12 13:43
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class PatrolPresenter extends PatrolContract.Presenter {


    public PatrolPresenter(PatrolContract.View view, SafeModel model) {
        super(view, model);
    }

    @Override
    public void getPatrolList(String proId, String type, String whyType, String zgbz, String jcrqTime,String isDealWith, int rows, int page) {
        model.getPatrolList(proId, type, whyType, zgbz, jcrqTime,isDealWith, rows, page,
                new JsonCallback<ResponseData<PageInfo<PatrolInfo>>>(new TypeToken<ResponseData<PageInfo<PatrolInfo>>>() {
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
                    public void onSuccess(ResponseData<PageInfo<PatrolInfo>> data) {
                        if (isAttach) {
                            if (data.isSuccess()) {
                                view.showPatrolList(data.getResult());
                            } else {
                                view.showError(data.getErrmsg());
                            }
                        }
                    }
                });
    }
}
