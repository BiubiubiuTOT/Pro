package com.ljkj.qxn.wisdomsitepro.contract.application;

import com.ljkj.qxn.wisdomsitepro.data.entity.AttendanceHistoryInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.AttendanceInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.PageInfo;
import com.ljkj.qxn.wisdomsitepro.model.ApplicationModel;

import cdsp.android.presenter.BasePresenter;
import cdsp.android.ui.base.BaseView;

/**
 * 类描述：
 * 创建人：mhl
 * 创建时间：2018/2/27 10:10
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public interface AttendanceContract {


    interface View extends BaseView {

        /**
         * 分页显示考勤数据
         *
         * @param data
         */
        void showPageAttendanceInfo(PageInfo<AttendanceInfo> data);


        /**
         * 分页显示考勤历史数据
         *
         * @param data
         */
        void showAttendanceHitoryInfo(PageInfo<AttendanceHistoryInfo> data);
    }

    abstract class Presenter extends BasePresenter<View, ApplicationModel> {

        public Presenter(View view, ApplicationModel model) {
            super(view, model);
        }


        /**
         * 分页获取考勤数据
         *
         * @param searchKey
         * @param page
         * @param proId
         * @param rows
         */
        public abstract void listPageAttendanceInfo( String searchKey, int page, String proId, int rows,String id);


        /**
         * 分页获取考勤历史
         *
         * @param laborPersonId
         * @param month
         * @param page
         * @param proId
         * @param rows
         */
        public abstract void listPageAttendanceHitoryInfo(String laborPersonId, String month, int page, String proId, int rows);
    }
}
