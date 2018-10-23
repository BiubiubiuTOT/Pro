package com.ljkj.qxn.wisdomsitepro.contract.common;

import com.ljkj.qxn.wisdomsitepro.data.VersionInfo;
import com.ljkj.qxn.wisdomsitepro.model.CommonModel;

import cdsp.android.presenter.BasePresenter;
import cdsp.android.ui.base.BaseView;

/**
 * 类描述：获取最新APP版本信息
 * 创建人：lxx
 * 创建时间：2018/4/10 13:43
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public interface AppUpdateContract {

    interface View extends BaseView {
        void showVersionInfo(VersionInfo versionInfo);
    }

    abstract class Presenter extends BasePresenter<View, CommonModel> {
        public Presenter(View view, CommonModel model) {
            super(view, model);
        }

        public abstract void checkUpdate();
    }


}
