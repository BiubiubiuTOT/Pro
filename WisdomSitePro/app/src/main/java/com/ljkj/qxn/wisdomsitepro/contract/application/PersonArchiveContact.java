package com.ljkj.qxn.wisdomsitepro.contract.application;

import com.ljkj.qxn.wisdomsitepro.data.entity.AttendManagerTeamInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.TeamPersonInfo;
import com.ljkj.qxn.wisdomsitepro.model.ApplicationModel;

import java.util.List;

import cdsp.android.presenter.BasePresenter;
import cdsp.android.ui.base.BaseView;

/**
 * 类描述：人员档案
 * 创建人：lxx
 * 创建时间：2018/7/2
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public interface PersonArchiveContact {

    interface View extends BaseView {
        void showTeamPersonList(List<TeamPersonInfo> info);

        void showTeamAttendanceList(List<AttendManagerTeamInfo> info);

    }

    abstract class Presenter extends BasePresenter<PersonArchiveContact.View, ApplicationModel> {
        public Presenter(PersonArchiveContact.View view, ApplicationModel model) {
            super(view, model);
        }

        public abstract void getTeamPersonList(String proId);

        public abstract void getTeamAttendanceList(String proId);

    }

}
