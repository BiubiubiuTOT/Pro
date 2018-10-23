package com.ljkj.qxn.wisdomsitepro.contract.contacts;

import com.ljkj.qxn.wisdomsitepro.data.entity.LaborCompanyBean;
import com.ljkj.qxn.wisdomsitepro.data.entity.RoleBean;
import com.ljkj.qxn.wisdomsitepro.data.entity.contract.DepartmentListInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.contract.ProjectDeptInfo;
import com.ljkj.qxn.wisdomsitepro.model.ContactsModel;

import java.util.List;

import cdsp.android.presenter.BasePresenter;
import cdsp.android.ui.base.BaseView;
import io.rong.imageloader.utils.L;

/**
 * 类描述：联系人 添加部门和成员 部门列表
 * 创建人：rjf
 * 创建时间：2018/7/2 13:47.
 * 修改人：
 * 修改时间
 * 修改备注
 */

public interface DepartmentListContract {
    interface View extends BaseView {

        /**
         * 显示成员总数
         *
         * @param memberNumber
         */
        void showProjectInfo(ProjectDeptInfo memberNumber);

        /**
         * 展示项目部列表信息
         *
         * @param listInfo 部门列表信息
         */
        void showProjectDepartmentList(List<DepartmentListInfo> listInfo);

        /**
         * 展示班组列表信息
         *
         * @param listInfo
         */

        void showBanZuDepartmentList(List<DepartmentListInfo> listInfo);

        /**
         * 展示用户部门信息
         *
         * @param listInfo
         */
        void showUserDepartmentList(List<DepartmentListInfo> listInfo);

        /**
         * 展示劳务公司列表
         *
         * @param list
         */
        void showLaborCompanyList(List<LaborCompanyBean> list);

        void showRoleList(RoleBean list);

        /**
         * 处理添加部门结果
         */
        void dealAddDepartmentResult();
    }

    abstract class Presenter extends BasePresenter<DepartmentListContract.View, ContactsModel> {
        public Presenter(DepartmentListContract.View view, ContactsModel model) {
            super(view, model);
        }

        /**
         * 获取项目成员总数
         *
         * @param proId
         */
        public abstract void getProjectInfo(String proId, String userId);

        /**
         * 获取项目部列表
         *
         * @param page
         * @param rows
         * @param proId
         */
        public abstract void getProjectDepartmentList(String proId, Integer page, Integer rows);

        /**
         * 获取班组部门列表
         *
         * @param page
         * @param rows
         * @param proId
         */
        public abstract void getBanZuDepartmentList(String proId, Integer page, Integer rows);

        /**
         * 获取用户所在部门
         *
         * @param page
         * @param rows
         * @param userId
         * @param proId
         */
        public abstract void getUserDepartmentList(Integer page, Integer rows, String userId, String proId);

        /**
         * 添加部门
         *
         * @param deptName 部门名称
         * @param orgType  部门类型
         * @param projId   项目ID
         * @param parentId 劳务id
         */
        public abstract void addDepartment(String projId, String deptName, String orgType, String parentId);


        /**
         * 劳务公司列表
         *
         * @param proId
         */
        public abstract void getLaborCompanyList(String proId);


        public abstract void getRoleList(String projId);
    }
}
