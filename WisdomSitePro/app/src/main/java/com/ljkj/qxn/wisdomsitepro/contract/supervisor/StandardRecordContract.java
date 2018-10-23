package com.ljkj.qxn.wisdomsitepro.contract.supervisor;

import com.ljkj.qxn.wisdomsitepro.data.entity.PageInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.SupervisorRecordManageInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.SupervisorStandardInfo;
import com.ljkj.qxn.wisdomsitepro.model.SupervisorModel;

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
public interface StandardRecordContract {

    interface View extends BaseView {
        void showStandardRecordList(PageInfo<SupervisorStandardInfo> datas);

        void dealDeleteRecordResult();

        void showStandardRecordDetail(SupervisorStandardInfo info);

        void dealAddRecordResult();
    }

    abstract class Presenter extends BasePresenter<StandardRecordContract.View, SupervisorModel> {
        public Presenter(StandardRecordContract.View view, SupervisorModel model) {
            super(view, model);
        }

        public abstract void getStandardRecordList(String proId, String recordName, int pageNum, int pageSize);

        public abstract void getStandardRecordDetail(String id);

        public abstract void deleteRecord(String id, String name, String userId);

        public abstract void addStandardRecord(SupervisorRecordManageInfo info);
    }

}
