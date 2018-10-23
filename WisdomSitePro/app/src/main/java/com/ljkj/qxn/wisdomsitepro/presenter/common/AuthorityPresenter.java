package com.ljkj.qxn.wisdomsitepro.presenter.common;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.safe.AuthorityContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.DeptTeamInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.TeamLeader;
import com.ljkj.qxn.wisdomsitepro.model.AuthorityModel;

import java.util.List;

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

public class AuthorityPresenter extends AuthorityContract.Presenter {


    public AuthorityPresenter(AuthorityContract.View view, AuthorityModel model) {
        super(view, model);
    }


    @Override
    public void getDeptTeamList(String proId) {
        model.getDeptTeamList(proId,
                new JsonCallback<ResponseData<List<DeptTeamInfo>>>(new TypeToken<ResponseData<List<DeptTeamInfo>>>() {
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
                    public void onStart() {
                        super.onStart();
                        if (isAttach){
                            view.showProgress("获取数据中...");
                        }
                    }

                    @Override
                    public void onSuccess(ResponseData<List<DeptTeamInfo>> data) {
                        if (isAttach) {
                            if (data.isSuccess()) {
                                view.showDeptTeamList(data.getResult());
                            } else {
                                view.showError(data.getErrmsg());
                            }
                        }
                    }
                });
    }

    @Override
    public void getTeamLeader(String deptId, String projId) {
        model.getTeamLeader(deptId, projId,
                new JsonCallback<ResponseData<TeamLeader>>(new TypeToken<ResponseData<TeamLeader>>() {
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
                    public void onSuccess(ResponseData<TeamLeader> data) {
                        if (isAttach) {
                            if (data.isSuccess()) {
                                view.showTeamLeader(data.getResult());
                            } else {
                                view.showError(data.getErrmsg());
                            }
                        }
                    }
                });
    }
}
