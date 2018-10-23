package com.ljkj.qxn.wisdomsitepro.contract.safe;

import com.ljkj.qxn.wisdomsitepro.data.entity.PageInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.SafePatrolDetailInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.SafePatrolInfo;
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

public interface SafePatrolContract {
    interface View extends BaseView {

        void showSafePatrolList(PageInfo<SafePatrolInfo> pageInfo);

        void showSafePatrolDetail(SafePatrolDetailInfo info);
    }

    abstract class Presenter extends BasePresenter<View, SafeModel> {
        public Presenter(View view, SafeModel model) {
            super(view, model);
        }

        public abstract void getSafePatrolList(String proId, String accidentReason,
                                               String changeGroup, String startDate,
                                               String endDate, String isDealWith, int rows, int page);

        public abstract void getSafePatrolMessageList(String proId, int rows, int page);

        public abstract void getSafePatrolDetail(String id);
    }

}
