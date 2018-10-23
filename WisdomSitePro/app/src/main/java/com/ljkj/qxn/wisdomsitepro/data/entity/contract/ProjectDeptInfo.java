package com.ljkj.qxn.wisdomsitepro.data.entity.contract;

/**
 * 类描述：项目成员总数信息
 * 创建人：rjf
 * 创建时间：2018/7/3 10:06.
 * 修改人：
 * 修改时间
 * 修改备注
 */

public class ProjectDeptInfo {


    /**
     * deptUserNum : 0
     * projUserNum : 1
     */

    private int deptUserNum;
    private int projUserNum;
    private String deptId;
    private String deptName;
    private String projDeptId;

    public String getProjDeptId() {
        return projDeptId;
    }

    public void setProjDeptId(String projDeptId) {
        this.projDeptId = projDeptId;
    }

    public int getDeptUserNum() {
        return deptUserNum;
    }

    public void setDeptUserNum(int deptUserNum) {
        this.deptUserNum = deptUserNum;
    }

    public int getProjUserNum() {
        return projUserNum;
    }

    public void setProjUserNum(int projUserNum) {
        this.projUserNum = projUserNum;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
}
