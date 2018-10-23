package com.ljkj.qxn.wisdomsitepro.data.message;

import android.os.Parcel;
import android.os.Parcelable;

import com.ljkj.qxn.wisdomsitepro.data.CheckableEntity;

import java.io.Serializable;

/**
 * 类描述：公告联系人实体
 * 创建人：lxx
 * 创建时间：2018/6/23
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class NoticeContactInfo implements CheckableEntity, Parcelable, Serializable {

    private boolean isChecked;

    private String realName; //人名

    private String userName; //用户名

    private String job; //职位

    private String departmentName; //部门

    public NoticeContactInfo() {
    }

    protected NoticeContactInfo(Parcel in) {
        isChecked = in.readByte() != 0;
        realName = in.readString();
        userName = in.readString();
        job = in.readString();
        departmentName = in.readString();
    }

    public static final Creator<NoticeContactInfo> CREATOR = new Creator<NoticeContactInfo>() {
        @Override
        public NoticeContactInfo createFromParcel(Parcel in) {
            return new NoticeContactInfo(in);
        }

        @Override
        public NoticeContactInfo[] newArray(int size) {
            return new NoticeContactInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (isChecked ? 1 : 0));
        dest.writeString(realName);
        dest.writeString(userName);
        dest.writeString(job);
        dest.writeString(departmentName);
    }

    @Override
    public boolean isChecked() {
        return isChecked;
    }

    @Override
    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getDepartmentName() {
        return departmentName == null ? "" : departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public String toString() {
        return "NoticeContactInfo{" +
                "isChecked=" + isChecked +
                ", realName='" + realName + '\'' +
                ", userName='" + userName + '\'' +
                ", job='" + job + '\'' +
                ", departmentName='" + departmentName + '\'' +
                '}';
    }
}
