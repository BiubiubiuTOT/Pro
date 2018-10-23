package com.ljkj.qxn.wisdomsitepro.contract.safe;

import com.ljkj.qxn.wisdomsitepro.data.entity.CheckInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.PageInfo;
import com.ljkj.qxn.wisdomsitepro.model.SafeModel;

import cdsp.android.presenter.BasePresenter;
import cdsp.android.ui.base.BaseView;

/**
 * 类描述：监理检查列表
 * 创建人：lxx
 * 创建时间：2018/9/6
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public interface SupervisorCheckListContract {

    interface View extends BaseView {
        void showSupervisorCheckList(PageInfo<CheckInfo> datas);
    }

    abstract class Presenter extends BasePresenter<SupervisorCheckListContract.View, SafeModel> {
        public Presenter(SupervisorCheckListContract.View view, SafeModel model) {
            super(view, model);
        }

        public abstract void getSupervisorCheckList(String proId, int checkType, int page, int size, String dangerLevel, String rectifyType, String checkDate);
    }

}
