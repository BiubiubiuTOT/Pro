package com.ljkj.qxn.wisdomsitepro.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * 类描述：楼层信息
 * 创建人：lxx
 * 创建时间：2018/9/5
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class FloorInfo implements Parcelable {
    @SerializedName("buildName")
    public String floorName;

    @SerializedName("id")
    public String floorId;



    public FloorInfo() {
    }


    protected FloorInfo(Parcel in) {
        floorName = in.readString();
        floorId = in.readString();
    }

    public static final Creator<FloorInfo> CREATOR = new Creator<FloorInfo>() {
        @Override
        public FloorInfo createFromParcel(Parcel in) {
            return new FloorInfo(in);
        }

        @Override
        public FloorInfo[] newArray(int size) {
            return new FloorInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(floorName);
        dest.writeString(floorId);
    }
}
