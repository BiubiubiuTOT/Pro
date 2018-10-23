package com.ljkj.qxn.wisdomsitepro.presenter.safe;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.safe.SafeEducationAddContract;
import com.ljkj.qxn.wisdomsitepro.data.FileEntity;
import com.ljkj.qxn.wisdomsitepro.data.entity.NullEntity;
import com.ljkj.qxn.wisdomsitepro.data.entity.SecurityEduTechPerson;
import com.ljkj.qxn.wisdomsitepro.model.SafeModel;

import java.util.List;

import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;

/**
 * 类描述：安全教育新增
 * 创建人：mhl
 * 创建时间：2018/3/15 15:40
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class SafeEducationAddPresenter extends SafeEducationAddContract.Presenter {

    public SafeEducationAddPresenter(SafeEducationAddContract.View view, SafeModel model) {
        super(view, model);
    }

    @Override
    public void addSafeEdu(String presentationName, String presentationDate, String presentationInfo, List<FileEntity> file,
                           String trainer, List<SecurityEduTechPerson> persons, int isReport, String projId, String projCode,
                           String createUserId, String createUserName) {
        model.addSafeEdu(presentationName, presentationDate, presentationInfo, file, trainer, persons, isReport, projId,
                projCode, createUserId, createUserName, new JsonCallback<ResponseData<NullEntity>>(new TypeToken<ResponseData<NullEntity>>() {
                }) {

                    @Override
                    public void onStart() {
                        super.onStart();
                        if (isAttach) {
                            view.showProgress("提交中...");
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
                    public void onSuccess(ResponseData<NullEntity> data) {
                        if (isAttach) {
                            if (data.isSuccess()) {
                                view.showAddSafeEduResult();
                            } else {
                                view.showError(data.getErrmsg());
                            }
                        }
                    }
                });
    }

    @Override
    public void addSafeTechnology(String presentationName, String presentationDate, String presentationPosition,
                                  String presentationPerson, String safetyOfficer, String teamLeader, List<SecurityEduTechPerson> team,
                                  int isReport, String presentationInfo, List<FileEntity> file, String projId,
                                  String projCode, String createUserId, String createUserName) {
        model.addSafeTechnology(presentationName, presentationDate, presentationPosition, presentationPerson, safetyOfficer, teamLeader
                , team, isReport, presentationInfo, file, projId, projCode, createUserId, createUserName, new JsonCallback<ResponseData<NullEntity>>(new TypeToken<ResponseData<NullEntity>>() {
                }) {
                    @Override
                    public void onStart() {
                        super.onStart();
                        if (isAttach) {
                            view.showProgress("提交中...");
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
                    public void onSuccess(ResponseData<NullEntity> data) {
                        if (isAttach) {
                            if (data.isSuccess()) {
                                view.showAddSafeTechnology();
                            } else {
                                view.showError(data.getErrmsg());
                            }
                        }
                    }

                });
    }

}
