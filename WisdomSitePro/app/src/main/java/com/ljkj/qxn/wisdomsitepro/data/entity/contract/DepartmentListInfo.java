package com.ljkj.qxn.wisdomsitepro.data.entity.contract;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.ljkj.qxn.wisdomsitepro.data.entity.DeptBean;

import java.util.List;

/**
 * 类描述：项目列表信息
 * 创建人：rjf
 * 创建时间：2018/7/2 13:49.
 * 修改人：
 * 修改时间
 * 修改备注
 */

public class DepartmentListInfo implements Parcelable {

    /**
     * id : 7728151746135482368
     * name : 项目部
     * status : 0
     * deptList : [{"pageNum":0,"pageSize":0,"id":"7728488215789723648","projId":"7720883873953882112","projCode":null,"parentId":"7728151746135482368","name":"办公室","orgType":194,"sort":1,"createUserId":"1536825819","createUserName":null,"createTime":"2018-09-13 15:56:32","updateUserId":"7720898944388063232","updateUserName":null,"updateTime":"2018-09-15 11:29:36","status":0},{"pageNum":0,"pageSize":0,"id":"7728763898105454592","projId":"7720883873953882112","projCode":null,"parentId":"7728151746135482368","name":"部门5","orgType":194,"sort":null,"createUserId":"7720898944388063232","createUserName":null,"createTime":"2018-09-14 10:12:00","updateUserId":"7720898944388063232","updateUserName":null,"updateTime":"2018-09-15 11:54:49","status":1},{"pageNum":0,"pageSize":0,"id":"7729226501504131072","projId":"7720883873953882112","projCode":null,"parentId":"7728151746135482368","name":"部门3","orgType":194,"sort":null,"createUserId":"7720898944388063232","createUserName":null,"createTime":"2018-09-15 16:50:13","updateUserId":null,"updateUserName":null,"updateTime":"2018-09-15 16:50:13","status":1}]
     */

    private String id;
    private String name;
    private int status;

    @SerializedName(value = "deptList", alternate = {"lmTeamList"})
    private List<DeptBean> deptList;

    protected DepartmentListInfo(Parcel in) {
        id = in.readString();
        name = in.readString();
        status = in.readInt();
    }

    public static final Creator<DepartmentListInfo> CREATOR = new Creator<DepartmentListInfo>() {
        @Override
        public DepartmentListInfo createFromParcel(Parcel in) {
            return new DepartmentListInfo(in);
        }

        @Override
        public DepartmentListInfo[] newArray(int size) {
            return new DepartmentListInfo[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<DeptBean> getDeptList() {
        return deptList;
    }

    public void setDeptList(List<DeptBean> deptList) {
        this.deptList = deptList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(name);
        parcel.writeInt(status);
    }

}
