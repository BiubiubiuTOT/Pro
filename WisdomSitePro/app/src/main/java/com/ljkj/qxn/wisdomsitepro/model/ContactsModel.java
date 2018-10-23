package com.ljkj.qxn.wisdomsitepro.model;

import com.ljkj.qxn.wisdomsitepro.Utils.OkGoHelper;
import com.ljkj.qxn.wisdomsitepro.data.api.HostConfig;

import java.util.HashMap;

import cdsp.android.http.JsonCallback;
import cdsp.android.model.BaseModel;

/**
 * 类描述：联系人
 * 创建人：rjf
 * 创建时间：2018/7/2 13:53.
 * 修改人：
 * 修改时间
 * 修改备注
 */

public class ContactsModel extends BaseModel {

    //获取部门列表
    private static final String DEPARTMENT_LIST_URL = "/authority/dept/queryDeptListByProjId";
    //获取班组列表
    private static final String BANZU_LIST_URL = "/authority/dept/queryTeamListByProjId";

    //添加部门
    private static final String DEPARTMENT_ADD_URL = "/authority/dept/add";

    //修改部门
    private static final String DEPARTMENT_UPDATE_URL = "/authority/dept/updateById";

    //删除部门
    private static final String DEPARTMENT_DELETE_URL = "/authority/dept/deleteById";

    //项目成员总数
    private static final String PROJECT_MEMBER_NUMBER_URL = "/authority/user/queryUserDeptInfo";

    //添加成员
    private static final String MEMBER_ADD_URL = "/authority/user/add";

    //获取职务列表
    private static final String DUTY_LIST_URL = "/authority/contactsController.do?findProRoleList";

    //删除成员
    private static final String MEMBER_DELETE_URL = "/authority/contactsController.do?deleteUser";

    //查询成员
    private static final String MEMBER_SEARCH_URL = "/authority/user/dataGrid";

    //部门详情
    private static final String DEPARMENT_DETAIL_URL = "/authority/contactsController.do?findDeptById";

    //劳务公司
    private static final String LABOR_COMPANY_URL = "/authority/dept/queryLaborCompanyListByProjId";

    //职务
    private static final String ROLE_URL = "/authority/role/dataGrid";

    private static ContactsModel model;

    private ContactsModel() {

    }

    public static ContactsModel newInstance() {
        if (model == null) {
            model = new ContactsModel();
        }
        return model;
    }

    /**
     * 获取部门列表
     *
     * @param page  分页页数
     * @param rows  分页每页条数
     * @param proId 项目ID
     */
    public void getDepartmentList(String proId, Integer page, Integer rows, JsonCallback callback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("projId", proId);
        params.put("pageNum", page);
        params.put("pageSize", rows);
        OkGoHelper.getInstance().postJson(HostConfig.getHost2() + DEPARTMENT_LIST_URL, params, DEPARTMENT_LIST_URL, callback);
    }

    /**
     * 获取班组列表
     *
     * @param page  分页页数
     * @param rows  分页每页条数
     * @param proId 项目ID
     */
    public void getBanzuList(String proId, Integer page, Integer rows, JsonCallback callback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("pageNum", page);
        params.put("pageSize", rows);
        params.put("projId", proId);
        OkGoHelper.getInstance().postJson(HostConfig.getHost2() + BANZU_LIST_URL, params, BANZU_LIST_URL, callback);
    }

    /**
     * 获取用户所在部门
     *
     * @param page   分页页数
     * @param rows   分页每页条数
     * @param proId  项目ID
     * @param userId 用户id
     */
    public void getUserDepartmentList(Integer page, Integer rows, String proId, String userId, JsonCallback callback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("page", page);
        params.put("rows", rows);
        params.put("proId", proId);
        params.put("mobilePhone", userId);
        OkGoHelper.getInstance().get(HostConfig.getHost2() + DEPARTMENT_LIST_URL, params, DEPARTMENT_LIST_URL, callback);
    }

    /**
     * 添加部门
     *
     * @param name     部门名称
     * @param orgType  部门类型
     * @param proId    项目ID
     * @param parentId 劳务id
     */
    public void addDepartment(String proId, String name, String orgType, String parentId, JsonCallback callback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("projId", proId);
        params.put("orgType", orgType);
        params.put("name", name);
        params.put("parentId", parentId);
        OkGoHelper.getInstance().postJson(HostConfig.getHost2() + DEPARTMENT_ADD_URL, params, DEPARTMENT_ADD_URL, callback);
    }

    /**
     * 修改部门
     *
     * @param id       部门ID
     * @param deptName 部门名称
     */
    public void updateDepartment(String id, String projId, String deptName, String orgType, String parentId, JsonCallback callback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("projId", projId);
        params.put("name", deptName);
        params.put("orgType", orgType);
        params.put("parentId", parentId);
        OkGoHelper.getInstance().postJson(HostConfig.getHost2() + DEPARTMENT_UPDATE_URL, params, DEPARTMENT_UPDATE_URL, callback);
    }

    /**
     * 删除部门
     *
     * @param deptId 部门ID
     */
    public void deleteDepartment(String deptId, String projId, JsonCallback callback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", deptId);
        params.put("projId", projId);
        OkGoHelper.getInstance().postJson(HostConfig.getHost2() + DEPARTMENT_DELETE_URL, params, DEPARTMENT_DELETE_URL, callback);
    }

    /**
     * 获取项目成员总数
     *
     * @param proId
     */
    public void getProjectMemberNum(String proId, String userId, JsonCallback callback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("projId", proId);
        params.put("id", userId);
        OkGoHelper.getInstance().postJson(HostConfig.getHost2() + PROJECT_MEMBER_NUMBER_URL, params, PROJECT_MEMBER_NUMBER_URL, callback);
    }


    /**
     * 添加成员
     */
    public void addMember(HashMap params, JsonCallback callback) {
        OkGoHelper.getInstance().postJson(HostConfig.getHost2() + MEMBER_ADD_URL, params, MEMBER_ADD_URL, callback);
    }

    /**
     * 获取职务列表
     *
     * @param proId 项目ID
     */
    public void getDutyList(String proId, JsonCallback callback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("proId", proId);
        OkGoHelper.getInstance().get(HostConfig.getHost2() + DUTY_LIST_URL, params, DUTY_LIST_URL, callback);
    }

    /**
     * 删除成员
     *
     * @param id       成员id
     * @param callback
     */
    public void deleteMember(String id, JsonCallback callback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", id);
        OkGoHelper.getInstance().post(HostConfig.getHost2() + MEMBER_DELETE_URL, params, MEMBER_DELETE_URL, callback);
    }

    /**
     * 查询成员
     *
     * @param id       选择的部门或者班组的id
     * @param proId    项目ID
     * @param callback
     */
    public void searchMember(String id, String proId, JsonCallback callback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("deptId", id);
        params.put("projId", proId);
        params.put("pageNum", 1);
        params.put("pageSize", 999);
        OkGoHelper.getInstance().postJson(HostConfig.getHost2() + MEMBER_SEARCH_URL, params, MEMBER_SEARCH_URL, callback);
    }

    /**
     * 部门详情
     *
     * @param deptId   部门ID
     * @param callback
     */
    public void getDepartmentDetail(String deptId, JsonCallback callback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("deptId", deptId);
        OkGoHelper.getInstance().post(HostConfig.getHost2() + DEPARMENT_DETAIL_URL, params, DEPARMENT_DETAIL_URL, callback);
    }

    /**
     * 劳务公司
     *
     * @param projId   部门ID
     * @param callback
     */
    public void getLaborCompanyList(String projId, JsonCallback callback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("projId", projId);
        OkGoHelper.getInstance().postJson(HostConfig.getHost2() + LABOR_COMPANY_URL, params, LABOR_COMPANY_URL, callback);
    }


    /**
     * 职务列表
     *
     * @param projId   部门ID
     * @param callback
     */
    public void getRoleList(String projId, JsonCallback callback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("projId", projId);
        OkGoHelper.getInstance().postJson(HostConfig.getHost2() + ROLE_URL, params, ROLE_URL, callback);
    }
}
