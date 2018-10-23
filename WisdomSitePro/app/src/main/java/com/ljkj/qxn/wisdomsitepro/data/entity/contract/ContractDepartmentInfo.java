package com.ljkj.qxn.wisdomsitepro.data.entity.contract;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 类描述：添加部门成员信息类
 * 创建人：rjf
 * 创建时间：2018/6/25 11:29.
 * 修改人：
 * 修改时间
 * 修改备注
 */

public class ContractDepartmentInfo implements Parcelable {

    private String departmentName;

    private int memberNum;

    public String getDepartmentName() {
        return departmentName;
    }

    public ContractDepartmentInfo setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
        return this;
    }

    public int getMemberNum() {
        return memberNum;
    }

    public ContractDepartmentInfo setMemberNum(int memberNum) {
        this.memberNum = memberNum;
        return this;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.departmentName);
        dest.writeInt(this.memberNum);
    }

    public ContractDepartmentInfo() {
    }

    protected ContractDepartmentInfo(Parcel in) {
        this.departmentName = in.readString();
        this.memberNum = in.readInt();
    }

    public static final Parcelable.Creator<ContractDepartmentInfo> CREATOR = new Parcelable.Creator<ContractDepartmentInfo>() {
        @Override
        public ContractDepartmentInfo createFromParcel(Parcel source) {
            return new ContractDepartmentInfo(source);
        }

        @Override
        public ContractDepartmentInfo[] newArray(int size) {
            return new ContractDepartmentInfo[size];
        }
    };
}
