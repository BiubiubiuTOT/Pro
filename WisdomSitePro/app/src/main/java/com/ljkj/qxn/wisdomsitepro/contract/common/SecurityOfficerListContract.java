package com.ljkj.qxn.wisdomsitepro.contract.common;

import com.ljkj.qxn.wisdomsitepro.data.entity.SecurityOfficerInfo;
import com.ljkj.qxn.wisdomsitepro.model.CommonModel;

import java.util.List;

import cdsp.android.presenter.BasePresenter;
import cdsp.android.ui.base.BaseView;

/**
 * 类描述：获取项目专职安全员列表
 * 创建人：lxx
 * 创建时间：2018/9/25
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public interface SecurityOfficerListContract {

    interface View extends BaseView {
        void showSecurityOfficer(List<SecurityOfficerInfo> list);
    }

    abstract class Presenter extends BasePresenter<View, CommonModel> {
        public Presenter(View view, CommonModel model) {
            super(view, model);
        }

        public abstract void getSecurityOfficer(String projId);
    }

}
