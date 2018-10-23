package com.ljkj.qxn.wisdomsitepro.contract.quality;

import com.ljkj.qxn.wisdomsitepro.data.entity.PageInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.QualityCheckInfo;
import com.ljkj.qxn.wisdomsitepro.model.QualityModel;

import cdsp.android.presenter.BasePresenter;
import cdsp.android.ui.base.BaseView;

/**
 * 质量检查
 * Created by lxx on 2018/3/9.
 */

public interface QualityCheckContract {
    interface View extends BaseView {

        void showQualityCheckInfo(PageInfo<QualityCheckInfo> pageInfo);
    }

    abstract class Presenter extends BasePresenter<View, QualityModel> {
        public Presenter(View view, QualityModel model) {
            super(view, model);
        }

        public abstract void getQualityCheckList(int page, String proId, int rows, String type, String yhdj, String zglx,String jcrqTime);
    }

}
