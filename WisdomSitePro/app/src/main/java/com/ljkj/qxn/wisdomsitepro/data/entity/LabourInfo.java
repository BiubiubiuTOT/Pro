package com.ljkj.qxn.wisdomsitepro.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * 类描述：劳务人员实体
 * 创建人：mhl
 * 创建时间：2018/2/26 14:15
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class LabourInfo implements Parcelable {

    /**
     * teamsType : 水泥班
     * accessDate : 2018-06-01
     * laborCompany : 贵州建筑劳务工程公司
     * native : 贵州
     * idCard : 522724199310053815
     * sex : 女
     * name : 刘松
     * phoneNum : 13087826581
     * id : 123456
     */

    private String teamsType;
    private String accessDate;
    private String laborCompany;
    @SerializedName("native")
    private String nativeX;
    private String idCard;
    private String sex;
    private String name;
    private String phoneNum;
    private String id;
    private boolean selected = false;

    protected LabourInfo(Parcel in) {
        teamsType = in.readString();
        accessDate = in.readString();
        laborCompany = in.readString();
        nativeX = in.readString();
        idCard = in.readString();
        sex = in.readString();
        name = in.readString();
        phoneNum = in.readString();
        id = in.readString();
        selected = in.readByte() != 0;
    }

    public static final Creator<LabourInfo> CREATOR = new Creator<LabourInfo>() {
        @Override
        public LabourInfo createFromParcel(Parcel in) {
            return new LabourInfo(in);
        }

        @Override
        public LabourInfo[] newArray(int size) {
            return new LabourInfo[size];
        }
    };

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getTeamsType() {
        return teamsType;
    }

    public void setTeamsType(String teamsType) {
        this.teamsType = teamsType;
    }

    public String getAccessDate() {
        return accessDate;
    }

    public void setAccessDate(String accessDate) {
        this.accessDate = accessDate;
    }

    public String getLaborCompany() {
        return laborCompany;
    }

    public void setLaborCompany(String laborCompany) {
        this.laborCompany = laborCompany;
    }

    public String getNativeX() {
        return nativeX;
    }

    public void setNativeX(String nativeX) {
        this.nativeX = nativeX;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(teamsType);
        parcel.writeString(accessDate);
        parcel.writeString(laborCompany);
        parcel.writeString(nativeX);
        parcel.writeString(idCard);
        parcel.writeString(sex);
        parcel.writeString(name);
        parcel.writeString(phoneNum);
        parcel.writeString(id);
        parcel.writeByte((byte) (selected ? 1 : 0));
    }
}
