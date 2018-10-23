package com.ljkj.qxn.wisdomsitepro.contract.supervisor;

import com.ljkj.qxn.wisdomsitepro.data.entity.PageInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.SideStationRecordUnit;
import com.ljkj.qxn.wisdomsitepro.data.entity.SiteStationRecorderManageDetailInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.SiteStationRecorderManageInfo;
import com.ljkj.qxn.wisdomsitepro.model.SupervisorModel;

import java.util.List;

import cdsp.android.presenter.BasePresenter;
import cdsp.android.ui.base.BaseView;

/**
 * 类描述：旁站记录管理
 * 创建人：lxx
 * 创建时间：2018/9/3
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public interface SideStationRecordContract {

    interface View extends BaseView {
        void showSideStationRecordList(PageInfo<SiteStationRecorderManageInfo> datas);

        void dealDeleteRecordResult();

        void showSideStationRecordDetail(SiteStationRecorderManageDetailInfo info);

        void dealAddRecordResult();

        void showUnits(List<SideStationRecordUnit> info);
    }

    abstract class Presenter extends BasePresenter<View, SupervisorModel> {
        public Presenter(View view, SupervisorModel model) {
            super(view, model);
        }

        public abstract void getSideStationRecordList(String proId, String recordName, String beginTime, int pageNum, int pageSize);

        public abstract void getSideStationRecordDetail(String id);

        public abstract void deleteRecord(String id, String name, String userId);

        public abstract void addSideStationRecord(SiteStationRecorderManageDetailInfo info);

        public abstract void getUnits(String projId);
    }

}
