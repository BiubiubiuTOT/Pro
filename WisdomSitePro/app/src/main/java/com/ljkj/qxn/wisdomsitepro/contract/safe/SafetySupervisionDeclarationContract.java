package com.ljkj.qxn.wisdomsitepro.contract.safe;

import com.ljkj.qxn.wisdomsitepro.data.entity.ManagerPeopleInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.SafetySupervisionDeclarationInfo;
import com.ljkj.qxn.wisdomsitepro.model.SafeModel;

import cdsp.android.presenter.BasePresenter;
import cdsp.android.ui.base.BaseView;

/**
 * 类描述：安全监督申报
 * 创建人：mhl
 * 创建时间：2018/3/13 14:16
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public interface SafetySupervisionDeclarationContract {


    interface View extends BaseView {
        void showSupervisionDeclaration(SafetySupervisionDeclarationInfo data);
    }

    abstract class Presenter extends BasePresenter<View, SafeModel> {

        public Presenter(View view, SafeModel model) {
            super(view, model);
        }

        public abstract void viewSupervisionDeclaration(String proId);
    }
}
