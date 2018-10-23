package com.ljkj.qxn.wisdomsitepro.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 类描述：楼层防护详情
 * 创建人：lxx
 * 创建时间：2018/9/5
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class FloorDetail implements Parcelable {

    public String id;

    /** 楼层安全防护说明 */
    public String buildDefendDetail;

    public FloorDetail() {
    }

    protected FloorDetail(Parcel in) {
        id = in.readString();
        buildDefendDetail = in.readString();
    }

    public static final Creator<FloorDetail> CREATOR = new Creator<FloorDetail>() {
        @Override
        public FloorDetail createFromParcel(Parcel in) {
            return new FloorDetail(in);
        }

        @Override
        public FloorDetail[] newArray(int size) {
            return new FloorDetail[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(buildDefendDetail);
    }
}
