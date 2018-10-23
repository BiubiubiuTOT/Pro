package com.ljkj.qxn.wisdomsitepro.contract.application;

import com.ljkj.qxn.wisdomsitepro.data.entity.ConstructLogInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.PageInfo;
import com.ljkj.qxn.wisdomsitepro.model.ApplicationModel;

import cdsp.android.presenter.BasePresenter;
import cdsp.android.ui.base.BaseView;

/**
 * 作者：JiaJia
 * 创建时间：2018/3/9 14:48.
 * 功能描述：
 */

public interface ConstructListLogContract {

    public interface View extends BaseView {

        /**
         * 分页显示工资数据
         *
         * @param data
         */
        void showPageLogInfo(PageInfo<ConstructLogInfo> data);
    }

    public abstract class Presenter extends BasePresenter<View, ApplicationModel> {

        public Presenter(View view, ApplicationModel model) {
            super(view, model);
        }


        public abstract void constructLogList(String projId, int page, int size, String date, String recorder);
    }
}
