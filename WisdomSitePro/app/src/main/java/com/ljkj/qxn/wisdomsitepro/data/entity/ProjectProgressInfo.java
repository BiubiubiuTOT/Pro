package com.ljkj.qxn.wisdomsitepro.data.entity;

/**
 * 类描述：工程形象进度
 * 创建人：liufei
 * 创建时间：2018/3/10
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class ProjectProgressInfo {

    /**
     * id : 7732900671757942784
     * projId : 7730596820333821952
     * progressExplain : 当天的工程进度需要自己新增
     * progressDate : 2018-09-25 20:17:07
     */

    private String id;
    private String projId;
    private String progressExplain;
    private String progressDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjId() {
        return projId;
    }

    public void setProjId(String projId) {
        this.projId = projId;
    }

    public String getProgressExplain() {
        return progressExplain;
    }

    public void setProgressExplain(String progressExplain) {
        this.progressExplain = progressExplain;
    }

    public String getProgressDate() {
        return progressDate;
    }

    public void setProgressDate(String progressDate) {
        this.progressDate = progressDate;
    }
}
