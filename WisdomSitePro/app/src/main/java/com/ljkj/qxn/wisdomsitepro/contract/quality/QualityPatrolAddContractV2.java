package com.ljkj.qxn.wisdomsitepro.contract.quality;

import com.ljkj.qxn.wisdomsitepro.data.entity.LabourInfo;
import com.ljkj.qxn.wisdomsitepro.model.QualityModel;

import java.util.HashMap;
import java.util.List;

import cdsp.android.http.ResponseData;
import cdsp.android.presenter.BasePresenter;
import cdsp.android.ui.base.BaseView;

/**
 * 类描述：新增巡检
 * 创建人：mhl
 * 创建时间：2018/3/17 9:27
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public interface QualityPatrolAddContractV2 {

    interface View extends BaseView {


        /**
         * 下发结果显示
         *
         * @param data
         */
        void showQualityPatrolAddResult(String data);

    }

    abstract class Presenter extends BasePresenter<View, QualityModel> {

        public Presenter(View view, QualityModel model) {
            super(view, model);
        }


        public abstract void qualityPatrolAdd(HashMap params);
    }
}
