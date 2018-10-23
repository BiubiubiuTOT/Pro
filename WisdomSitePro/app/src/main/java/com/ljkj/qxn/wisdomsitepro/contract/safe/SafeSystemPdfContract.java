package com.ljkj.qxn.wisdomsitepro.contract.safe;

import com.ljkj.qxn.wisdomsitepro.data.entity.SafeSystemPdfInfo;
import com.ljkj.qxn.wisdomsitepro.model.SafeModel;

import cdsp.android.presenter.BasePresenter;
import cdsp.android.ui.base.BaseView;

/**
 * 类描述：安全体系pdf
 * 创建人：liufei
 * 创建时间：2018/3/10 11:33
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public interface SafeSystemPdfContract {


    interface View extends BaseView {

        /**
         * 显示获取的数据
         *
         * @param data
         */
        void showSafeSystemPdf(SafeSystemPdfInfo data);
    }

    abstract class Presenter extends BasePresenter<View, SafeModel> {

        public Presenter(View view, SafeModel model) {
            super(view, model);
        }

        /**
         * 获取pdf地址
         */
        public abstract void getSafeSystemPdf(String proId, int marFlag);
    }


}
