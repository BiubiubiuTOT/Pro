package com.ljkj.qxn.wisdomsitepro.contract.quality;

import com.ljkj.qxn.wisdomsitepro.data.entity.PageInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.QualityAcceptInfo;
import com.ljkj.qxn.wisdomsitepro.model.QualityModel;

import cdsp.android.presenter.BasePresenter;
import cdsp.android.ui.base.BaseView;

/**
 * 类描述: 质量验收
 * 创建人：rjf
 * 创建时间：2018/3/10 11:45.
 * 修改人：
 * 修改时间
 * 修改备注
 */

public interface QualityAcceptContract {
    interface View extends BaseView {

        void showQualityAcceptInfo(PageInfo<QualityAcceptInfo> pageInfo);
    }

    abstract class Presenter extends BasePresenter<View, QualityModel> {
        public Presenter(View view, QualityModel model) {
            super(view, model);
        }

        public abstract void getQualityAcceptList(int page, String proId, int rows);
    }
}
