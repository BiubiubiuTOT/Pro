package com.ljkj.qxn.wisdomsitepro.data.entity.contract;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by ligh on 2018/7/4.
 * 模块：
 */
public class QueryMemberInfo implements Parcelable {

    /**
     * id : 7729832929991876608
     * projId : 7720883873953882112
     * dept : 班组一
     * role : null
     * name : 张三
     * phone : 2232323
     * idcard : 341226197309171488
     * sex : 女
     * userType : 部门经理
     * title : 正高级工程师
     * major : 3
     * userCertificateList : [{"pageNum":0,"pageSize":0,"id":"7729916332726050816","projId":"7720883873953882112","userId":"7729832929991876608","name":"33","code":"3","createTime":"2018-09-17 14:31:22","status":1}]
     */

    private String id;
    private String projId;
    private String dept;
    private String role;
    private String name;
    private String phone;
    private String idcard;
    private String sex;
    private String userType;
    private String title;
    private String major;
    private String photoId;
    private List<UserCertificateListBean> userCertificateList;

    protected QueryMemberInfo(Parcel in) {
        id = in.readString();
        projId = in.readString();
        dept = in.readString();
        role = in.readString();
        name = in.readString();
        phone = in.readString();
        idcard = in.readString();
        sex = in.readString();
        userType = in.readString();
        title = in.readString();
        major = in.readString();
        photoId = in.readString();
    }

    public static final Creator<QueryMemberInfo> CREATOR = new Creator<QueryMemberInfo>() {
        @Override
        public QueryMemberInfo createFromParcel(Parcel in) {
            return new QueryMemberInfo(in);
        }

        @Override
        public QueryMemberInfo[] newArray(int size) {
            return new QueryMemberInfo[size];
        }
    };

    public String getPhotoId() {
        return photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
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

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public List<UserCertificateListBean> getUserCertificateList() {
        return userCertificateList;
    }

    public void setUserCertificateList(List<UserCertificateListBean> userCertificateList) {
        this.userCertificateList = userCertificateList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(projId);
        parcel.writeString(dept);
        parcel.writeString(role);
        parcel.writeString(name);
        parcel.writeString(phone);
        parcel.writeString(idcard);
        parcel.writeString(sex);
        parcel.writeString(userType);
        parcel.writeString(title);
        parcel.writeString(major);
        parcel.writeString(photoId);
    }

    public static class UserCertificateListBean {
        /**
         * pageNum : 0
         * pageSize : 0
         * id : 7729916332726050816
         * projId : 7720883873953882112
         * userId : 7729832929991876608
         * name : 33
         * code : 3
         * createTime : 2018-09-17 14:31:22
         * status : 1
         */

        private int pageNum;
        private int pageSize;
        private String id;
        private String projId;
        private String userId;
        private String name;
        private String code;
        private String createTime;
        private int status;

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

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
