package com.ljkj.qxn.wisdomsitepro.contract.safe;

import com.ljkj.qxn.wisdomsitepro.data.entity.ManagerPeopleInfo;
import com.ljkj.qxn.wisdomsitepro.model.SafeModel;

import cdsp.android.presenter.BasePresenter;
import cdsp.android.ui.base.BaseView;

/**
 * 类描述：安全管理人员体系
 * 创建人：liufei
 * 创建时间：2018/3/10 11:33
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public interface SafeSystemPeopleContract {


    interface View extends BaseView {

        /**
         * 显示获取的数据
         *
         * @param data
         */
        void showSafeSystemPeople(ManagerPeopleInfo data);
    }

    abstract class Presenter extends BasePresenter<View, SafeModel> {

        public Presenter(View view, SafeModel model) {
            super(view, model);
        }

        /**
         * 管理人员名单
         *
         * @param proId        项目id
         */
        public abstract void getSafeSystemPeople(String proId);
    }


}
