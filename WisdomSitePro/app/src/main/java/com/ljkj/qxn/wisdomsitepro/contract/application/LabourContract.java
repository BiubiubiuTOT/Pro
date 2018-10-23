package com.ljkj.qxn.wisdomsitepro.contract.application;

import com.ljkj.qxn.wisdomsitepro.data.entity.LabourDetail;
import com.ljkj.qxn.wisdomsitepro.data.entity.LabourInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.PageInfo;
import com.ljkj.qxn.wisdomsitepro.model.ApplicationModel;

import cdsp.android.presenter.BasePresenter;
import cdsp.android.ui.base.BaseView;

/**
 * 类描述：
 * 创建人：mhl
 * 创建时间：2018/2/27 9:38
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public interface LabourContract {


    interface View extends BaseView {

        /**
         * 分页显示劳务人员数据
         *
         * @param data
         */
        void showPageLabourInfo(PageInfo<LabourInfo> data);

        void showLabourDetail(LabourDetail data);
    }

    abstract class Presenter extends BasePresenter<View, ApplicationModel> {

        public Presenter(View view, ApplicationModel model) {
            super(view, model);
        }


        /**
         * 分页获取劳务人员
         *
         * @param searchKey
         * @param page
         * @param proId
         */
        public abstract void listPageLabourInfo(String searchKey, int page, String proId, int row, String id);

        /**
         * 劳务人员详情
         *
         * @param id
         */
        public abstract void getLabourDetail(String id);
    }
}
