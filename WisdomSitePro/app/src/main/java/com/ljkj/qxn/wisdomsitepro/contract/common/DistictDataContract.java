package com.ljkj.qxn.wisdomsitepro.contract.common;

import com.ljkj.qxn.wisdomsitepro.data.entity.DistictInfo;
import com.ljkj.qxn.wisdomsitepro.model.CommonModel;

import java.util.List;

import cdsp.android.presenter.BasePresenter;
import cdsp.android.ui.base.BaseView;

/**
 * 类描述：数据字典查询
 * 创建人：mhl
 * 创建时间：2018/2/28 10:01
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public interface DistictDataContract {


    interface View extends BaseView {


        /**
         * 数据字典列表
         *
         * @param data
         */
        void showDisticts(List<DistictInfo> data);

    }

    abstract class Presenter extends BasePresenter<View, CommonModel> {

        public Presenter(View view, CommonModel model) {
            super(view, model);
        }


        /**
         * 数据字典列表查询
         *
         * @param type
         */
        public abstract void listDisticts(String type);
    }
}
