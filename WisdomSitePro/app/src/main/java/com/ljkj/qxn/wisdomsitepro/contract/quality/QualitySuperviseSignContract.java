package com.ljkj.qxn.wisdomsitepro.contract.quality;

import com.ljkj.qxn.wisdomsitepro.data.entity.PageInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.QualityCheckInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.QualitySuperviseSignInfo;
import com.ljkj.qxn.wisdomsitepro.model.QualityModel;

import cdsp.android.presenter.BasePresenter;
import cdsp.android.ui.base.BaseView;

/**
 * 类描述：质量监督申报
 * 创建人：mhl
 * 创建时间：2018/3/16 16:57
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public interface QualitySuperviseSignContract {

    interface View extends BaseView {

        void showQualitySuperviseSignInfo(QualitySuperviseSignInfo data);

    }

    abstract class Presenter extends BasePresenter<View, QualityModel> {
        public Presenter(View view, QualityModel model) {
            super(view, model);
        }

        public abstract void viewQualitySuperviseSignInfo(String proId);
    }
}
