package com.ljkj.qxn.wisdomsitepro.presenter.safe;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.safe.CheckListContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.CheckInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.PageInfo;
import com.ljkj.qxn.wisdomsitepro.model.SafeModel;

import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;

/**
 * 类描述：检查列表Presenter
 * 创建人：lxx
 * 创建时间：2018/9/6
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class CheckListPresenter extends CheckListContract.Presenter {
    public CheckListPresenter(CheckListContract.View view, SafeModel model) {
        super(view, model);
    }

    @Override
    public void getSafeCheckList(String proId, int page, int size, final String dangerLevel, String rectifyType, String checkDate, boolean isMessage) {
        model.getSafeCheckList(proId, page, size, dangerLevel, rectifyType, checkDate, isMessage,
                new JsonCallback<ResponseData<PageInfo<CheckInfo>>>(new TypeToken<ResponseData<PageInfo<CheckInfo>>>() {
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
                    public void onSuccess(ResponseData<PageInfo<CheckInfo>> data) {
                        if (isAttach) {
                            if (data.getErrcode() == 200) {
                                view.showSafeCheckList(data.getResult());
                            } else {
                                view.showError(data.getErrmsg());
                            }
                        }
                    }

                });
    }

    @Override
    public void getQualityCheckList(String proId, int page, int size, String dangerLevel, String rectifyType, String checkDate, boolean isMessage) {
        model.getQualityCheckList(proId, page, size, dangerLevel, rectifyType, checkDate, isMessage,
                new JsonCallback<ResponseData<PageInfo<CheckInfo>>>(new TypeToken<ResponseData<PageInfo<CheckInfo>>>() {
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
                    public void onSuccess(ResponseData<PageInfo<CheckInfo>> data) {
                        if (isAttach) {
                            if (data.getErrcode() == 200) {
                                view.showQualityCheckList(data.getResult());
                            } else {
                                view.showError(data.getErrmsg());
                            }
                        }
                    }
                });
    }

}
