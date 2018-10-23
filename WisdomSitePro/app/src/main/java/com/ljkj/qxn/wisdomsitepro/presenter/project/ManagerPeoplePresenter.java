package com.ljkj.qxn.wisdomsitepro.presenter.project;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.project.ManagerPeopleContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.ManagerPeopleInfo;
import com.ljkj.qxn.wisdomsitepro.model.ProjectModel;

import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;

/**
 * 类描述：管理人员名单
 * 创建人：liufei
 * 创建时间：2018/3/10 11:34
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class ManagerPeoplePresenter extends ManagerPeopleContract.Presenter {

    public ManagerPeoplePresenter(ManagerPeopleContract.View view, ProjectModel model) {
        super(view, model);
    }

    @Override
    public void getManagerPeople(String proId) {
        model.getManagerPeople(proId, new JsonCallback<ResponseData<ManagerPeopleInfo>>(new TypeToken<ResponseData<ManagerPeopleInfo>>() {
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
            public void onSuccess(ResponseData<ManagerPeopleInfo> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showManagerPeople(data.getResult());
                    } else {
                        view.showError(data.getErrmsg());
                    }
                }
            }
        });
    }
}
