package com.ljkj.qxn.wisdomsitepro.presenter.safe;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.safe.SupervisorCheckListContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.CheckInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.PageInfo;
import com.ljkj.qxn.wisdomsitepro.model.SafeModel;

import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;

/**
 * 类描述：监理检查列表
 * 创建人：lxx
 * 创建时间：2018/9/6
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SupervisorCheckListPresenter extends SupervisorCheckListContract.Presenter {
    public SupervisorCheckListPresenter(SupervisorCheckListContract.View view, SafeModel model) {
        super(view, model);
    }

    @Override
    public void getSupervisorCheckList(String proId, int checkType, int page, int size, final String dangerLevel, String rectifyType, String checkDate) {
        model.getSupervisorCheckList(proId, checkType, page, size, dangerLevel, rectifyType, checkDate,
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
                                view.showSupervisorCheckList(data.getResult());
                            } else {
                                view.showError(data.getErrmsg());
                            }
                        }
                    }

                });
    }
}
