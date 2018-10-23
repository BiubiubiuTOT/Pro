package com.ljkj.qxn.wisdomsitepro.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 类描述：楼栋信息
 * 创建人：lxx
 * 创建时间：2018/9/14
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class BuildingInfo implements Parcelable {

    /** 楼栋名称 */
    public String buildName;

    /** 楼层数 */
    public int floors;

    /** 楼层id */
    public String id;

    public BuildingInfo() {
    }

    protected BuildingInfo(Parcel in) {
        buildName = in.readString();
        floors = in.readInt();
        id = in.readString();
    }

    public static final Creator<BuildingInfo> CREATOR = new Creator<BuildingInfo>() {
        @Override
        public BuildingInfo createFromParcel(Parcel in) {
            return new BuildingInfo(in);
        }

        @Override
        public BuildingInfo[] newArray(int size) {
            return new BuildingInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(buildName);
        dest.writeInt(floors);
        dest.writeString(id);
    }
}
