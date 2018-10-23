package com.ljkj.qxn.wisdomsitepro.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class SecurityEduTechPerson implements Parcelable {
    public String laborCom; //劳务公司名
    public String teamName; //班组名


    public String teamPerson; //班组负责人
    public String teamLeaderId; //班组长id
    public String laborId;
    public String teamId;

    public SecurityEduTechPerson() {
    }

    protected SecurityEduTechPerson(Parcel in) {
        laborCom = in.readString();
        teamName = in.readString();
        teamPerson = in.readString();
        teamLeaderId = in.readString();
        laborId = in.readString();
        teamId = in.readString();
    }

    public static final Creator<SecurityEduTechPerson> CREATOR = new Creator<SecurityEduTechPerson>() {
        @Override
        public SecurityEduTechPerson createFromParcel(Parcel in) {
            return new SecurityEduTechPerson(in);
        }

        @Override
        public SecurityEduTechPerson[] newArray(int size) {
            return new SecurityEduTechPerson[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(laborCom);
        dest.writeString(teamName);
        dest.writeString(teamPerson);
        dest.writeString(teamLeaderId);
        dest.writeString(laborId);
        dest.writeString(teamId);
    }
}
