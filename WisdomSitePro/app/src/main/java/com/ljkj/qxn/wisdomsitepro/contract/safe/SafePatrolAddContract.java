package com.ljkj.qxn.wisdomsitepro.contract.safe;

import com.ljkj.qxn.wisdomsitepro.data.entity.LabourInfo;
import com.ljkj.qxn.wisdomsitepro.model.QualityModel;
import com.ljkj.qxn.wisdomsitepro.model.SafeModel;

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

public interface SafePatrolAddContract {

    interface View extends BaseView {


        /**
         * 新增安全巡检
         *
         * @param data
         */
        void showSafePatrolAddResult(String data);

    }

    abstract class Presenter extends BasePresenter<View, SafeModel> {

        public Presenter(View view, SafeModel model) {
            super(view, model);
        }

        public abstract void safePatrolAdd(HashMap params);
    }
}
