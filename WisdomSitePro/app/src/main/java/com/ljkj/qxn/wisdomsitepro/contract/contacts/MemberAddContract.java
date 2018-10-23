package com.ljkj.qxn.wisdomsitepro.contract.contacts;

import com.ljkj.qxn.wisdomsitepro.data.entity.MessageInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.PageInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.contract.DutyListInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.contract.MemberAddInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.contract.QueryMemberInfo;
import com.ljkj.qxn.wisdomsitepro.model.ContactsModel;

import java.util.HashMap;

import cdsp.android.presenter.BasePresenter;
import cdsp.android.ui.base.BaseView;

/**
 * 类描述：添加部门成员
 * 创建人：rjf
 * 创建时间：2018/7/3 11:14.
 * 修改人：
 * 修改时间
 * 修改备注
 */

public interface MemberAddContract {
    interface View extends BaseView {
        /**
         * 处理添加成员结果
         */
        void dealAddMemberResult();

        /**
         * 显示职务列表
         *
         * @param data 职务列表
         */
        void showDutyList(PageInfo<DutyListInfo> data);

        void dealDeleteResult();

        void showSearchMembers(PageInfo<QueryMemberInfo> data);
    }

    abstract class Presenter extends BasePresenter<MemberAddContract.View, ContactsModel> {
        public Presenter(MemberAddContract.View view, ContactsModel model) {
            super(view, model);
        }

        /**
         * 添加成员
         *
         * @param info 成员信息
         */
        public abstract void addMember(HashMap info);

        /**
         * 获取职务列表
         *
         * @param proId 项目ID
         */
        public abstract void getDutyList(String proId);

        /**
         * 删除成员
         *
         * @param id 成员id
         */
        public abstract void deleteMember(String id);

        /**
         *
         */
        public abstract void searachMember(String id, String proId);
    }
}
