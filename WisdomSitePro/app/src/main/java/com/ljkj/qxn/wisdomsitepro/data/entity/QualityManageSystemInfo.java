package com.ljkj.qxn.wisdomsitepro.data.entity;

/**
 * 类描述：质量管理体系
 * 创建人：mhl
 * 创建时间：2018/2/28 10:32
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class QualityManageSystemInfo {

    /**
     * 业务id
     */
    private String id;
    /**
     * 质量方针和质量目标
     */
    private String target;



    private String zlscFile;
    private String cxxFile;
    private String zljlFile;
    private String createName;
    private String createBy;
    private long createDate;
    private String updateName;
    private String updateBy;
    private long updateDate;
    private String proId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getZlscFile() {
        return zlscFile;
    }

    public void setZlscFile(String zlscFile) {
        this.zlscFile = zlscFile;
    }

    public String getCxxFile() {
        return cxxFile;
    }

    public void setCxxFile(String cxxFile) {
        this.cxxFile = cxxFile;
    }

    public String getZljlFile() {
        return zljlFile;
    }

    public void setZljlFile(String zljlFile) {
        this.zljlFile = zljlFile;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
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

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
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

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }
}
