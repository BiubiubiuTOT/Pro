package com.ljkj.qxn.wisdomsitepro.contract.quality;

import com.ljkj.qxn.wisdomsitepro.data.entity.PageInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.QualityPatrolDetailInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.QualityPatrolInfo;
import com.ljkj.qxn.wisdomsitepro.model.QualityModel;

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

public interface QualityPatrolContract {
    interface View extends BaseView {

        void showQualityPatrolList(PageInfo<QualityPatrolInfo> pageInfo);

        void showQualityPatrolDetail(QualityPatrolDetailInfo info);
    }

    abstract class Presenter extends BasePresenter<View, QualityModel> {
        public Presenter(View view, QualityModel model) {
            super(view, model);
        }

        public abstract void getQualityPatrolList(String proId, String whyType,
                                                  String reformTeam, String startDate,
                                                  String endDate, int rows, int page);

        public abstract void getQualityPatrolMessageList(String proId, int rows, int page);

        public abstract void getQualityPatrolDetail(String id);
    }

}
