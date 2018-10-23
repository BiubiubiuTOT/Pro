package com.ljkj.qxn.wisdomsitepro.data.message;

import com.google.gson.annotations.SerializedName;

/**
 * 类描述：公告实体
 * 创建人：lxx
 * 创建时间：2018/6/22
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class NoticeInfo {
    public static final int NOTICE_UNREAD = 1;
    public static final int NOTICE_READ = 2;

    private int noRead; //未读数

    private int read; //已读数

    @SerializedName("content")
    private String noticeContent; // 公告内容

    @SerializedName("title")
    private String noticeTitle; //公告标题

    private String id; //公告id

    private int noticeStatus; //状态1:未读，2:已读

    @SerializedName("releaseDate")
    private String createTime; //创建时间

    @SerializedName("releaseUserName")
    private String fromUser; //发布人

    private String createUser; //创建人

    private String acceptUsersName; //接收人

    public int getNoRead() {
        return noRead;
    }

    public void setNoRead(int noRead) {
        this.noRead = noRead;
    }

    public int getRead() {
        return read;
    }

    public void setRead(int read) {
        this.read = read;
    }

    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getStatus() {
        return noticeStatus;
    }

    public void setStatus(int status) {
        this.noticeStatus = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }


    public String getAcceptUsersName() {
        return acceptUsersName;
    }

    public void setAcceptUsersName(String acceptUsersName) {
        this.acceptUsersName = acceptUsersName;
    }

    @Override
    public String toString() {
        return "NoticeInfo{" +
                "noRead=" + noRead +
                ", read=" + read +
                ", noticeContent='" + noticeContent + '\'' +
                ", noticeTitle='" + noticeTitle + '\'' +
                ", id='" + id + '\'' +
                ", status=" + noticeStatus +
                ", createTime=" + createTime +
                ", fromUser='" + fromUser + '\'' +
                ", createUser='" + createUser + '\'' +
                '}';
    }
}
