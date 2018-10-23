package com.ljkj.qxn.wisdomsitepro.presenter.application;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.application.PersonArchiveContact;
import com.ljkj.qxn.wisdomsitepro.data.entity.AttendManagerTeamInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.TeamPersonInfo;
import com.ljkj.qxn.wisdomsitepro.model.ApplicationModel;

import java.util.List;

import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;

/**
 * 类描述：人员档案
 * 创建人：lxx
 * 创建时间：2018/7/2
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class PersonArchivePresenter extends PersonArchiveContact.Presenter {
    public PersonArchivePresenter(PersonArchiveContact.View view, ApplicationModel model) {
        super(view, model);
    }

    @Override
    public void getTeamPersonList(String proId) {
        model.getTeamPersonList(proId, new JsonCallback<ResponseData<List<TeamPersonInfo>>>(new TypeToken<ResponseData<List<TeamPersonInfo>>>() {
        }) {

            @Override
            public void onStart() {
                super.onStart();
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
            public void onSuccess(ResponseData<List<TeamPersonInfo>> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showTeamPersonList(data.getResult());
                    } else {
                        view.showError(data.getErrmsg());
                    }
                }
            }
        });

    }

    @Override
    public void getTeamAttendanceList(String proId) {
        model.getTeamAttendanceList(proId, new JsonCallback<ResponseData<List<AttendManagerTeamInfo>>>(new TypeToken<ResponseData<List<AttendManagerTeamInfo>>>() {
        }) {

            @Override
            public void onStart() {
                super.onStart();
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
            public void onSuccess(ResponseData<List<AttendManagerTeamInfo>> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showTeamAttendanceList(data.getResult());
                    } else {
                        view.showError(data.getErrmsg());
                    }
                }
            }
        });
    }

}
