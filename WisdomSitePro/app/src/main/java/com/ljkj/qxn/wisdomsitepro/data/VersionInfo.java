package com.ljkj.qxn.wisdomsitepro.data;

/**
 * 类描述：版本信息
 * 创建人：lxx
 * 创建时间：2018/4/10 13:31
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class VersionInfo {

    /**
     * 安装包下载路径
     */
    private String installPackageUrl;

    /**
     * 是否强制更新
     */
    private boolean isForceUpdate;

    /**
     * 最新版本号
     */
    private int lastVersionCode;

    /**
     * 更新详情
     */
    private String updateDetail;

    /**
     * 版本名称
     */
    private String versionName;

    public String getInstallPackageUrl() {
        return installPackageUrl;
    }

    public void setInstallPackageUrl(String installPackageUrl) {
        this.installPackageUrl = installPackageUrl;
    }

    public boolean isForceUpdate() {
        return isForceUpdate;
    }

    public void setForceUpdate(boolean forceUpdate) {
        isForceUpdate = forceUpdate;
    }

    public int getLastVersionCode() {
        return lastVersionCode;
    }

    public void setLastVersionCode(int lastVersionCode) {
        this.lastVersionCode = lastVersionCode;
    }

    public String getUpdateDetail() {
        return updateDetail;
    }

    public void setUpdateDetail(String updateDetail) {
        this.updateDetail = updateDetail;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    @Override
    public String toString() {
        return "VersionInfo{" +
                "installPackageUrl='" + installPackageUrl + '\'' +
                ", isForceUpdate=" + isForceUpdate +
                ", lastVersionCode='" + lastVersionCode + '\'' +
                ", updateDetail='" + updateDetail + '\'' +
                ", versionName='" + versionName + '\'' +
                '}';
    }
}
