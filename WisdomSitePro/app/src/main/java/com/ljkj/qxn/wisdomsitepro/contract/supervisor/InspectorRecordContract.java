package com.ljkj.qxn.wisdomsitepro.contract.supervisor;

import com.ljkj.qxn.wisdomsitepro.data.entity.InspectorRecorderManageInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.PageInfo;
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
public interface InspectorRecordContract {

    interface View extends BaseView {
        void showInspectorRecordList(PageInfo<InspectorRecorderManageInfo> datas);

        void dealDeleteRecordResult();

        void showInspectorRecordDetail(InspectorRecorderManageInfo info);

        void dealAddRecordResult();
    }

    abstract class Presenter extends BasePresenter<View, SupervisorModel> {
        public Presenter(View view, SupervisorModel model) {
            super(view, model);
        }

        public abstract void getInspectorRecordList(String proId, String recordName, String beginTime, int pageNum, int pageSize);

        public abstract void getInspectorRecordDetail(String id);

        public abstract void deleteRecord(String id, String name, String userId);

        public abstract void addInspectorRecord(InspectorRecorderManageInfo info);
    }

}
