package com.ljkj.qxn.wisdomsitepro.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 类描述：考勤实体
 * 创建人：mhl
 * 创建时间：2018/2/26 14:29
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class AttendanceInfo implements Parcelable {

    /**
     * id : null
     * name : null
     * idCard : null
     * address : 贵州省贵阳市观山湖区大数据基地
     * onWrok : null
     * offWork : null
     * workHour : null
     * workMinute : null
     */

    private String id;
    private String name;
    private String idCard;
    private String address;
    private String onWrok;
    private String offWork;
    private String workHour;
    private String workMinute;

    protected AttendanceInfo(Parcel in) {
        id = in.readString();
        name = in.readString();
        idCard = in.readString();
        address = in.readString();
        onWrok = in.readString();
        offWork = in.readString();
        workHour = in.readString();
        workMinute = in.readString();
    }

    public static final Creator<AttendanceInfo> CREATOR = new Creator<AttendanceInfo>() {
        @Override
        public AttendanceInfo createFromParcel(Parcel in) {
            return new AttendanceInfo(in);
        }

        @Override
        public AttendanceInfo[] newArray(int size) {
            return new AttendanceInfo[size];
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

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOnWrok() {
        return onWrok;
    }

    public void setOnWrok(String onWrok) {
        this.onWrok = onWrok;
    }

    public String getOffWork() {
        return offWork;
    }

    public void setOffWork(String offWork) {
        this.offWork = offWork;
    }

    public String getWorkHour() {
        return workHour;
    }

    public void setWorkHour(String workHour) {
        this.workHour = workHour;
    }

    public String getWorkMinute() {
        return workMinute;
    }

    public void setWorkMinute(String workMinute) {
        this.workMinute = workMinute;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(name);
        parcel.writeString(idCard);
        parcel.writeString(address);
        parcel.writeString(onWrok);
        parcel.writeString(offWork);
        parcel.writeString(workHour);
        parcel.writeString(workMinute);
    }
}


