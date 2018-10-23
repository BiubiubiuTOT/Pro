package com.ljkj.qxn.wisdomsitepro.contract.quality;

import com.ljkj.qxn.wisdomsitepro.contract.common.ListFileContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.QualityManageSystemInfo;
import com.ljkj.qxn.wisdomsitepro.model.CommonModel;
import com.ljkj.qxn.wisdomsitepro.model.QualityModel;

import cdsp.android.presenter.BasePresenter;
import cdsp.android.ui.base.BaseView;

/**
 * 类描述：
 * 创建人：mhl
 * 创建时间：2018/2/28 10:57
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public interface QualityManageSystemContract {

    interface View extends BaseView {


        /**
         * 质量管理体系
         *
         * @param data
         */
        void showQualityManageSystemInfo(QualityManageSystemInfo data);

    }

    abstract class Presenter extends BasePresenter<View,QualityModel> {

        public Presenter(View view, QualityModel model) {
            super(view, model);
        }


        /**
         * 查询质量管理体系
         *
         * @param proId 项目ID
         */
        public abstract void viewQualityManageSystemInfo(String proId);

    }


}
