package com.ljkj.qxn.wisdomsitepro.presenter.application;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.application.AttendanceContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.AttendanceHistoryInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.AttendanceInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.PageInfo;
import com.ljkj.qxn.wisdomsitepro.model.ApplicationModel;

import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;

/**
 * 类描述：考勤历史列表
 * 创建人：mhl
 * 创建时间：2018/2/27 10:15
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class AttendanceHistoryPresenter extends AttendanceContract.Presenter {

    public AttendanceHistoryPresenter(AttendanceContract.View view, ApplicationModel model) {
        super(view, model);
    }

    @Override
    public void listPageAttendanceInfo( String searchKey, int page, String proId, int rows,String id) {

        model.attendanceList(searchKey, page, proId, rows,id, new JsonCallback<ResponseData<PageInfo<AttendanceInfo>>>(new TypeToken<ResponseData<PageInfo<AttendanceInfo>>>() {
        }) {
            @Override
            public void onFinish() {
                super.onFinish();
                if (isAttach) {
                    view.hideProgress();
                }
            }

            @Override
            public void onSuccess(ResponseData<PageInfo<AttendanceInfo>> data) {

                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showPageAttendanceInfo(data.getResult());
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
    public void listPageAttendanceHitoryInfo(String laborPersonId, String month, int page, String proId, int rows) {

        model.attendanceHistory(laborPersonId, month, page, proId, rows, new JsonCallback<ResponseData<PageInfo<AttendanceHistoryInfo>>>(new TypeToken<ResponseData<PageInfo<AttendanceHistoryInfo>>>() {
        }) {

            @Override
            public void onFinish() {
                super.onFinish();
                if (isAttach) {
                    view.hideProgress();
                }
            }

            @Override
            public void onStart() {
                super.onStart();
                if (isAttach) {
                    view.showProgress("获取数据中...");
                }
            }

            @Override
            public void onSuccess(ResponseData<PageInfo<AttendanceHistoryInfo>> data) {


                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showAttendanceHitoryInfo(data.getResult());
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
