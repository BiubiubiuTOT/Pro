package com.ljkj.qxn.wisdomsitepro.contract.safe;

import com.ljkj.qxn.wisdomsitepro.data.entity.DeptTeamInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.PageInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.SafePatrolInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.TeamLeader;
import com.ljkj.qxn.wisdomsitepro.model.AuthorityModel;
import com.ljkj.qxn.wisdomsitepro.model.SafeModel;

import java.util.List;

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

public interface AuthorityContract {
    interface View extends BaseView {

        void showDeptTeamList(List<DeptTeamInfo> pageInfo);

        void showTeamLeader(TeamLeader leader);
    }

    abstract class Presenter extends BasePresenter<View, AuthorityModel> {
        public Presenter(View view, AuthorityModel model) {
            super(view, model);
        }

        public abstract void getDeptTeamList(String proId);

        public abstract void getTeamLeader(String deptId, String projId);
    }

}
