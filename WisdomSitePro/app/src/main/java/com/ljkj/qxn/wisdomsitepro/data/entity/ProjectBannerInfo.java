package com.ljkj.qxn.wisdomsitepro.data.entity;

/**
 * 类描述：项目bunner
 * 创建人：liufei
 * 创建时间：2018/3/13
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class ProjectBannerInfo {
    /**
     * date : 2018-02-12 00:00:00
     * title : 测测测
     */

    private String title;
    private String date;
    private String fileId;

    public ProjectBannerInfo(String title, String date, String fileId) {
        this.title = title;
        this.date = date;
        this.fileId = fileId;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getFileId() {
        return fileId;
    }
}
