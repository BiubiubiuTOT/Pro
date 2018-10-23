package com.ljkj.qxn.wisdomsitepro.contract.safe;

import com.ljkj.qxn.wisdomsitepro.data.entity.PageInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.SafeHiddenAccountInfo;
import com.ljkj.qxn.wisdomsitepro.model.SafeModel;

import cdsp.android.presenter.BasePresenter;
import cdsp.android.ui.base.BaseView;

/**
 * 类描述：安全/质量隐患台账
 * 创建人：lxx
 * 创建时间：2018/7/11
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public interface SafeHiddenAccountContract {

    interface View extends BaseView {
        void showSafeHiddenAccount(PageInfo<SafeHiddenAccountInfo> datas);

        void showQualityHiddenAccount(PageInfo<SafeHiddenAccountInfo> datas);
    }

    abstract class Presenter extends BasePresenter<SafeHiddenAccountContract.View, SafeModel> {
        public Presenter(View view, SafeModel model) {
            super(view, model);
        }

        public abstract void getSafeHiddenAccountList(String proId, int type, int page, int rows);

        public abstract void getQualityHiddenAccountList(String proId, int type, int page, int rows);

    }

}
