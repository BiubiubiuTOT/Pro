package com.ljkj.qxn.wisdomsitepro.data.entity;

/**
 * 类描述：
 * 创建人：mhl
 * 创建时间：2018/2/26 15:00
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class AttendanceHistoryInfo {

    /**
     * id
     */
    private String id;

    /**
     * address
     */
    private String address;

    /**
     * 考勤日期
     */
    private String date;

    /**
     * 累计工时
     */
    private int hh;

    /**
     * 分钟
     */
    private int mm;

    /**
     * 姓名
     */
    private String name;

    /**
     * 上班时间
     */
    private String sbTime;

    /**
     * 工种
     */
    private String workType;

    /**
     * 下班时间
     */
    private String xbTime;

    private String goWorkImage;
    private String offWorkImage;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getHh() {
        return hh;
    }

    public void setHh(int hh) {
        this.hh = hh;
    }

    public int getMm() {
        return mm;
    }

    public void setMm(int mm) {
        this.mm = mm;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSbTime() {
        return sbTime;
    }

    public void setSbTime(String sbTime) {
        this.sbTime = sbTime;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public String getXbTime() {
        return xbTime;
    }

    public void setXbTime(String xbTime) {
        this.xbTime = xbTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGoWorkImage() {
        return goWorkImage;
    }

    public void setGoWorkImage(String goWorkImage) {
        this.goWorkImage = goWorkImage;
    }

    public String getOffWorkImage() {
        return offWorkImage;
    }

    public void setOffWorkImage(String offWorkImage) {
        this.offWorkImage = offWorkImage;
    }

    @Override
    public String toString() {
        return "AttendanceHistoryInfo{" +
                "id='" + id + '\'' +
                ", address='" + address + '\'' +
                ", date='" + date + '\'' +
                ", hh=" + hh +
                ", mm=" + mm +
                ", name='" + name + '\'' +
                ", sbTime='" + sbTime + '\'' +
                ", workType='" + workType + '\'' +
                ", xbTime='" + xbTime + '\'' +
                '}';
    }
}
