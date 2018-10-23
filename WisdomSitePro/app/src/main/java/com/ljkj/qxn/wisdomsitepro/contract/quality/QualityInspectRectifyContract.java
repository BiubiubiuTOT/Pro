package com.ljkj.qxn.wisdomsitepro.contract.quality;

import com.ljkj.qxn.wisdomsitepro.model.QualityModel;
import com.ljkj.qxn.wisdomsitepro.model.SafeModel;

import java.util.HashMap;

import cdsp.android.presenter.BasePresenter;
import cdsp.android.ui.base.BaseView;

/**
 * 类描述：巡检立即整改/重新整改
 * 创建人：rjf
 * 创建时间：2018/3/19 17:47.
 * 修改人：
 * 修改时间
 * 修改备注
 */

public interface QualityInspectRectifyContract {
    interface View extends BaseView {

        /**
         * @param data
         */
        void dealResult(String data);

    }

    abstract class Presenter extends BasePresenter<View, QualityModel> {

        public Presenter(View view, QualityModel model) {
            super(view, model);
        }

        public abstract void qualityInspectRectify(HashMap params);
    }

}
