package com.ljkj.qxn.wisdomsitepro.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class DeptBean implements Parcelable {

    /**
     * pageNum : 0
     * pageSize : 0
     * id : 7728488215789723648
     * projId : 7720883873953882112
     * projCode : null
     * parentId : 7728151746135482368
     * name : 办公室
     * orgType : 194
     * sort : 1
     * createUserId : 1536825819
     * createUserName : null
     * createTime : 2018-09-13 15:56:32
     * updateUserId : 7720898944388063232
     * updateUserName : null
     * updateTime : 2018-09-15 11:29:36
     * status : 0
     */

    private int pageNum;
    private int pageSize;
    private String id;
    private String projId;
    private String projCode;
    private String parentId;
    private String name;
    private int orgType;
    private int sort;
    private String createUserId;
    private String createUserName;
    private String createTime;
    private String updateUserId;
    private String updateUserName;
    private String updateTime;
    private int status;
    private int num;

    public DeptBean(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public DeptBean(String id, String name, int num) {
        this.id = id;
        this.name = name;
        this.num = num;
    }

    protected DeptBean(Parcel in) {
        pageNum = in.readInt();
        pageSize = in.readInt();
        id = in.readString();
        projId = in.readString();
        projCode = in.readString();
        parentId = in.readString();
        name = in.readString();
        orgType = in.readInt();
        sort = in.readInt();
        createUserId = in.readString();
        createUserName = in.readString();
        createTime = in.readString();
        updateUserId = in.readString();
        updateUserName = in.readString();
        updateTime = in.readString();
        status = in.readInt();
        num = in.readInt();
    }

    public static final Creator<DeptBean> CREATOR = new Creator<DeptBean>() {
        @Override
        public DeptBean createFromParcel(Parcel in) {
            return new DeptBean(in);
        }

        @Override
        public DeptBean[] newArray(int size) {
            return new DeptBean[size];
        }
    };

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

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

    public String getProjCode() {
        return projCode;
    }

    public void setProjCode(String projCode) {
        this.projCode = projCode;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrgType() {
        return orgType;
    }

    public void setOrgType(int orgType) {
        this.orgType = orgType;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(pageNum);
        parcel.writeInt(pageSize);
        parcel.writeString(id);
        parcel.writeString(projId);
        parcel.writeString(projCode);
        parcel.writeString(parentId);
        parcel.writeString(name);
        parcel.writeInt(orgType);
        parcel.writeInt(sort);
        parcel.writeString(createUserId);
        parcel.writeString(createUserName);
        parcel.writeString(createTime);
        parcel.writeString(updateUserId);
        parcel.writeString(updateUserName);
        parcel.writeString(updateTime);
        parcel.writeInt(status);
        parcel.writeInt(num);
    }
}
