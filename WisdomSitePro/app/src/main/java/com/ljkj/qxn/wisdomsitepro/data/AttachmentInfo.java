package com.ljkj.qxn.wisdomsitepro.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.ljkj.qxn.wisdomsitepro.data.api.HostConfig;

/**
 * 类描述：附件实体
 * 创建人：lxx
 * 创建时间：2018/6/25
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class AttachmentInfo implements Parcelable {

    @SerializedName("attachmenttitle")
    private String attachmentTitle; //附件标题

    private String extend; //扩展名

    @SerializedName("filesize")
    private long fileSize; //文件大小Byte

    @SerializedName("realpath")
    private String realPath; //文件路径

    public AttachmentInfo() {
    }

    public AttachmentInfo(Parcel in) {
        attachmentTitle = in.readString();
        extend = in.readString();
        fileSize = in.readLong();
        realPath = in.readString();
    }

    public static final Creator<AttachmentInfo> CREATOR = new Creator<AttachmentInfo>() {
        @Override
        public AttachmentInfo createFromParcel(Parcel in) {
            return new AttachmentInfo(in);
        }

        @Override
        public AttachmentInfo[] newArray(int size) {
            return new AttachmentInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(attachmentTitle);
        dest.writeString(extend);
        dest.writeLong(fileSize);
        dest.writeString(realPath);
    }

    public String getAttachmentTitle() {
        return attachmentTitle;
    }

    public void setAttachmentTitle(String attachmentTitle) {
        this.attachmentTitle = attachmentTitle;
    }

    public String getExtend() {
        return extend;
    }

    public void setExtend(String extend) {
        this.extend = extend;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getRealPath() {
        return realPath;
    }

    public void setRealPath(String realPath) {
        this.realPath = realPath;
    }


    /**
     * 返回附件下载路径
     *
     * @return 路径
     */
    public String getAttachmentDownloadPath() {
        return HostConfig.getHost().substring(0, HostConfig.getHost().length() - 2) + "/" + realPath;
    }

    @Override
    public String toString() {
        return "AttachmentInfo{" +
                "attachmentTitle='" + attachmentTitle + '\'' +
                ", extend='" + extend + '\'' +
                ", fileSize=" + fileSize +
                ", realPath='" + realPath + '\'' +
                '}';
    }
}
