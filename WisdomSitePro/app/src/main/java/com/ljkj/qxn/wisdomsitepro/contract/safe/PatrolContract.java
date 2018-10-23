package com.ljkj.qxn.wisdomsitepro.contract.safe;

import com.ljkj.qxn.wisdomsitepro.data.entity.PageInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.PatrolInfo;
import com.ljkj.qxn.wisdomsitepro.model.SafeModel;

import cdsp.android.presenter.BasePresenter;
import cdsp.android.ui.base.BaseView;

/**
 * 类描述：巡检
 * 创建人：liufei
 * 创建时间：2018/3/12 13:41
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public interface PatrolContract {
    interface View extends BaseView {

        void showPatrolList(PageInfo<PatrolInfo> pageInfo);
    }

    abstract class Presenter extends BasePresenter<View, SafeModel> {
        public Presenter(View view, SafeModel model) {
            super(view, model);
        }

        public abstract void getPatrolList(String proId, String type, String whyType, String zgbz, String jcrqTime, String isDealWith,int rows, int page);
    }

}
