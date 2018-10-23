package com.ljkj.qxn.wisdomsitepro.contract.contacts;

import com.ljkj.qxn.wisdomsitepro.data.entity.contract.DepartmentListInfo;
import com.ljkj.qxn.wisdomsitepro.model.ContactsModel;

import cdsp.android.presenter.BasePresenter;
import cdsp.android.ui.base.BaseView;

/**
 * 类描述：联系人 添加部门成员 点击部门进入部门详情
 * 创建人：rjf
 * 创建时间：2018/7/2 16:57.
 * 修改人：
 * 修改时间
 * 修改备注
 */

public interface DepartmentDetailContract {
    interface View extends BaseView {
        /**
         * 处理编辑部门结果
         */
        void dealUpdateDepartmentResult();

        /**
         * 处理删除部门结果
         */
        void dealDeleteDepartmentResult();

        /**
         * 部门详情
         *
         * @param data
         */
        void showDepartmentDetail(DepartmentListInfo data);
    }

    abstract class Presenter extends BasePresenter<DepartmentDetailContract.View, ContactsModel> {
        public Presenter(DepartmentDetailContract.View view, ContactsModel model) {
            super(view, model);
        }

        /**
         * 修改部门
         *
         * @param id  部门ID
         * @param deptName 部门名称
         */
        public abstract void updateDepartment(String id, String projId, String deptName, String orgType, String parentId);

        /**
         * 删除部门
         *
         * @param deptId 部门ID
         */
        public abstract void deleteDepartment(String deptId, String projId);

        public abstract void getDepartmentDetail(String deptId);
    }
}
