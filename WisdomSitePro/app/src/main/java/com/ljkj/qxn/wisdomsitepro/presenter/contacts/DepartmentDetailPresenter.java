package com.ljkj.qxn.wisdomsitepro.presenter.contacts;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.contacts.DepartmentDetailContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.contract.DepartmentListInfo;
import com.ljkj.qxn.wisdomsitepro.model.ContactsModel;

import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;

/**
 * 类描述：部门详情
 * 创建人：rjf
 * 创建时间：2018/7/2 17:09.
 * 修改人：
 * 修改时间
 * 修改备注
 */

public class DepartmentDetailPresenter extends DepartmentDetailContract.Presenter {

    public DepartmentDetailPresenter(DepartmentDetailContract.View view, ContactsModel model) {
        super(view, model);
    }

    @Override
    public void updateDepartment(String id, String projId, String deptName, String deptId, String parentId) {
        model.updateDepartment(id, projId, deptName, deptId, parentId, new JsonCallback<ResponseData>(new TypeToken<ResponseData>() {
        }) {
            @Override
            public void onSuccess(ResponseData data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.dealUpdateDepartmentResult();
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

            @Override
            public void onStart() {
                super.onStart();
                if (isAttach) {
                    view.showProgress("正在修改中...");
                }
            }

            @Override
            public void onFinish() {
                super.onFinish();
                if (isAttach) {
                    view.hideProgress();
                }
            }
        });
    }

    @Override
    public void deleteDepartment(String deptId, String projId) {
        model.deleteDepartment(deptId, projId, new JsonCallback<ResponseData>(new TypeToken<ResponseData>() {
        }) {
            @Override
            public void onSuccess(ResponseData data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.dealDeleteDepartmentResult();
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

            @Override
            public void onStart() {
                super.onStart();
                if (isAttach) {
                    view.showProgress("正在删除中...");
                }
            }

            @Override
            public void onFinish() {
                super.onFinish();
                if (isAttach) {
                    view.hideProgress();
                }
            }
        });
    }

    @Override
    public void getDepartmentDetail(final String deptId) {
        model.getDepartmentDetail(deptId, new JsonCallback<ResponseData<DepartmentListInfo>>(new TypeToken<ResponseData<DepartmentListInfo>>() {
        }) {
            @Override
            public void onSuccess(ResponseData<DepartmentListInfo> data) {
                if (data.isSuccess()) {
                    view.showDepartmentDetail(data.getResult());
                } else
                    view.showError(data.getErrmsg());
            }

            @Override
            protected void onError(int errcode, String errmsg) {
                view.showError(errmsg);
            }
        });
    }
}
