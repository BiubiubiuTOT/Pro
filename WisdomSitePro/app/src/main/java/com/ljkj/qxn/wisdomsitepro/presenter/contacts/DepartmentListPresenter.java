package com.ljkj.qxn.wisdomsitepro.presenter.contacts;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.contacts.DepartmentListContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.LaborCompanyBean;
import com.ljkj.qxn.wisdomsitepro.data.entity.RoleBean;
import com.ljkj.qxn.wisdomsitepro.data.entity.contract.DepartmentListInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.contract.ProjectDeptInfo;
import com.ljkj.qxn.wisdomsitepro.model.ContactsModel;

import java.util.List;

import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;

/**
 * 类描述：联系人部门列表
 * 创建人：rjf
 * 创建时间：2018/7/2 14:08.
 * 修改人：
 * 修改时间
 * 修改备注
 */

public class DepartmentListPresenter extends DepartmentListContract.Presenter {

    public DepartmentListPresenter(DepartmentListContract.View view, ContactsModel model) {
        super(view, model);
    }

    @Override
    public void getProjectInfo(String proId, String userId) {
        model.getProjectMemberNum(proId, userId, new JsonCallback<ResponseData<ProjectDeptInfo>>(
                new TypeToken<ResponseData<ProjectDeptInfo>>() {
                }) {
            @Override
            public void onSuccess(ResponseData<ProjectDeptInfo> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showProjectInfo(data.getResult());
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
            public void onFinish() {
                super.onFinish();
                if (isAttach) {
                    view.hideProgress();
                }
            }
        });
    }

    @Override
    public void getProjectDepartmentList(String dist, Integer page, Integer rows) {
        model.getDepartmentList(dist, page, rows, new JsonCallback<ResponseData<List<DepartmentListInfo>>>(
                new TypeToken<ResponseData<List<DepartmentListInfo>>>() {
                }
        ) {
            @Override
            public void onSuccess(ResponseData<List<DepartmentListInfo>> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showProjectDepartmentList(data.getResult());
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
            public void onFinish() {
                super.onFinish();
                if (isAttach) {
                    view.hideProgress();
                }
            }
        });
    }

    @Override
    public void getBanZuDepartmentList(String dist, Integer page, Integer rows) {
        model.getBanzuList(dist, page, rows, new JsonCallback<ResponseData<List<DepartmentListInfo>>>(
                new TypeToken<ResponseData<List<DepartmentListInfo>>>() {
                }
        ) {
            @Override
            public void onSuccess(ResponseData<List<DepartmentListInfo>> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showBanZuDepartmentList(data.getResult());
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
            public void onFinish() {
                super.onFinish();
                if (isAttach) {
                    view.hideProgress();
                }
            }
        });
    }

    @Override
    public void getUserDepartmentList(Integer page, Integer rows, String userId, String proId) {
        model.getUserDepartmentList(page, rows, proId, userId, new JsonCallback<ResponseData<List<DepartmentListInfo>>>(
                new TypeToken<ResponseData<List<DepartmentListInfo>>>() {
                }
        ) {
            @Override
            public void onSuccess(ResponseData<List<DepartmentListInfo>> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showUserDepartmentList(data.getResult());
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
            public void onFinish() {
                super.onFinish();
                if (isAttach) {
                    view.hideProgress();
                }
            }
        });
    }

    @Override
    public void addDepartment(String proId, String deptName, String orgType, String parentId) {
        model.addDepartment(proId, deptName, orgType, parentId, new JsonCallback<ResponseData>(new TypeToken<ResponseData>() {
        }) {
            @Override
            public void onSuccess(ResponseData data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.dealAddDepartmentResult();
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
                    view.showProgress("正在创建中...");
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
    public void getLaborCompanyList(String proId) {
        model.getLaborCompanyList(proId, new JsonCallback<ResponseData<List<LaborCompanyBean>>>(
                new TypeToken<ResponseData<List<LaborCompanyBean>>>() {
                }
        ) {
            @Override
            public void onSuccess(ResponseData<List<LaborCompanyBean>> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showLaborCompanyList(data.getResult());
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
            public void onFinish() {
                super.onFinish();
                if (isAttach) {
                    view.hideProgress();
                }
            }
        });
    }

    @Override
    public void getRoleList(String projId) {
        model.getRoleList(projId, new JsonCallback<ResponseData<RoleBean>>(
                new TypeToken<ResponseData<RoleBean>>() {
                }
        ) {
            @Override
            public void onSuccess(ResponseData<RoleBean> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showRoleList(data.getResult());
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
            public void onFinish() {
                super.onFinish();
                if (isAttach) {
                    view.hideProgress();
                }
            }
        });
    }
}
