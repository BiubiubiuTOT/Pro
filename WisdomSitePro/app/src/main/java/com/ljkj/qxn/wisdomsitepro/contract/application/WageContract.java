package com.ljkj.qxn.wisdomsitepro.contract.application;

import com.ljkj.qxn.wisdomsitepro.data.entity.PageInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.SfgzStatisticsInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.WageInfo;
import com.ljkj.qxn.wisdomsitepro.model.ApplicationModel;

import cdsp.android.presenter.BasePresenter;
import cdsp.android.ui.base.BaseView;

/**
 * 类描述：
 * 创建人：mhl
 * 创建时间：2018/2/27 9:53
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public interface WageContract {


    interface View extends BaseView {

        /**
         * 分页显示工资数据
         *
         * @param data
         */
        void showPageWageInfo(PageInfo<WageInfo> data);


        /**
         * 月总工资统计
         *
         * @param data
         */
        void showSfgzStatisticsInfo(SfgzStatisticsInfo data);
    }

    abstract class Presenter extends BasePresenter<View, ApplicationModel> {

        public Presenter(View view, ApplicationModel model) {
            super(view, model);
        }


        /**
         * 分页获取工资列表数据
         *
         * @param month
         * @param searchKey
         * @param page
         * @param proId
         * @param rows
         * @param workType
         * @param teams
         */
        public abstract void listPageWageInfo(String month, String searchKey, int page, String proId, int rows, String workType, String teams);

        /**
         * 月工资总额统计
         *
         * @param month
         * @param proId
         */
        public abstract void proSfgzStatistics(String month, String proId);
    }
}
