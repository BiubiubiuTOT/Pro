package com.ljkj.qxn.wisdomsitepro.data.entity.contract;

/**
 * 类描述：职务列表信息
 * 创建人：rjf
 * 创建时间：2018/7/3 11:36.
 * 修改人：
 * 修改时间
 * 修改备注
 */

public class DutyListInfo {

    /**
     * ID : 职务ID
     * create_by : 创建人id
     * create_date : 创建日期
     * create_name : 创建人
     * org_type : 部门/班组，1：部门，2：班组
     * pro_id : 所属项目ID
     * rolecode : 角色编码
     * rolename : 	角色名称
     * update_by : 修改人ID
     * update_date : 修改人日期
     * update_name : 修改人
     */

    private String id;
    private String createBy;
    private long createDate;
    private String createName;
    private String orgType;
    private String proId;
    private String rolecode;
    private String rolename;
    private String updateBy;
    private long updateDate;
    private String updateName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    public String getRolecode() {
        return rolecode;
    }

    public void setRolecode(String rolecode) {
        this.rolecode = rolecode;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public long getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(long updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }
}
