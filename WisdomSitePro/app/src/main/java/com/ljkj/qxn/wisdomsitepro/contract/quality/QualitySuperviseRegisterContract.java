package com.ljkj.qxn.wisdomsitepro.contract.quality;

import com.ljkj.qxn.wisdomsitepro.data.entity.QualitySuperviseRegisterInfo;
import com.ljkj.qxn.wisdomsitepro.model.QualityModel;

import cdsp.android.presenter.BasePresenter;
import cdsp.android.ui.base.BaseView;

/**
 * 质量监督注册登记
 * 创建人：lxx
 * 创建时间：2018/3/19 10:43
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public interface QualitySuperviseRegisterContract {

    interface View extends BaseView {
        void showQualitySuperviseRegisterInfo(QualitySuperviseRegisterInfo info);
    }

    abstract class Presenter extends BasePresenter<View, QualityModel> {

        public Presenter(View view, QualityModel model) {
            super(view, model);
        }

        public abstract void qualitySupReg(String proId);
    }


}
