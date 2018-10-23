package com.ljkj.qxn.wisdomsitepro.data.entity;

/**
 * 类描述：施工日志保存成功返回实体类
 * 创建人：rjf
 * 创建时间：2018/3/14 15:51.
 * 修改人：
 * 修改时间
 * 修改备注
 */

public class ConstructAddLogInfo {

    /**
     * constructionUnit :
     * createBy :
     * createDate : null
     * createName :
     * date : {"date":1,"hours":0,"seconds":0,"month":0,"timezoneOffset":-480,"year":118,"minutes":0,"time":1514736000000,"day":1}
     * emergency : 测试突发情况
     * fileId :
     * id : 8a81e4b362236c130162237a550d0008
     * maxMinTemp :
     * proHead :
     * proId : 40288153601f8a9a01601f8c42d10009
     * proName :
     * proNo :
     * production : 测试生产记录情况
     * qualitySafety : 测试质量安全记录情况
     * recorder :
     * remark :
     * updateBy :
     * updateDate : null
     * updateName :
     * weather : 雪
     * wind : 6级
     */

    private String constructionUnit;
    private String createBy;
    private Object createDate;
    private String createName;
    private DateBean date;
    private String emergency;
    private String fileId;
    private String id;
    private String maxMinTemp;
    private String proHead;
    private String proId;
    private String proName;
    private String proNo;
    private String production;
    private String qualitySafety;
    private String recorder;
    private String remark;
    private String updateBy;
    private Object updateDate;
    private String updateName;
    private String weather;
    private String wind;

    public String getConstructionUnit() {
        return constructionUnit;
    }

    public void setConstructionUnit(String constructionUnit) {
        this.constructionUnit = constructionUnit;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Object getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Object createDate) {
        this.createDate = createDate;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public DateBean getDate() {
        return date;
    }

    public void setDate(DateBean date) {
        this.date = date;
    }

    public String getEmergency() {
        return emergency;
    }

    public void setEmergency(String emergency) {
        this.emergency = emergency;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaxMinTemp() {
        return maxMinTemp;
    }

    public void setMaxMinTemp(String maxMinTemp) {
        this.maxMinTemp = maxMinTemp;
    }

    public String getProHead() {
        return proHead;
    }

    public void setProHead(String proHead) {
        this.proHead = proHead;
    }

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getProNo() {
        return proNo;
    }

    public void setProNo(String proNo) {
        this.proNo = proNo;
    }

    public String getProduction() {
        return production;
    }

    public void setProduction(String production) {
        this.production = production;
    }

    public String getQualitySafety() {
        return qualitySafety;
    }

    public void setQualitySafety(String qualitySafety) {
        this.qualitySafety = qualitySafety;
    }

    public String getRecorder() {
        return recorder;
    }

    public void setRecorder(String recorder) {
        this.recorder = recorder;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Object getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Object updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public static class DateBean {
        /**
         * date : 1
         * hours : 0
         * seconds : 0
         * month : 0
         * timezoneOffset : -480
         * year : 118
         * minutes : 0
         * time : 1514736000000
         * day : 1
         */

        private int date;
        private int hours;
        private int seconds;
        private int month;
        private int timezoneOffset;
        private int year;
        private int minutes;
        private long time;
        private int day;

        public int getDate() {
            return date;
        }

        public void setDate(int date) {
            this.date = date;
        }

        public int getHours() {
            return hours;
        }

        public void setHours(int hours) {
            this.hours = hours;
        }

        public int getSeconds() {
            return seconds;
        }

        public void setSeconds(int seconds) {
            this.seconds = seconds;
        }

        public int getMonth() {
            return month;
        }

        public void setMonth(int month) {
            this.month = month;
        }

        public int getTimezoneOffset() {
            return timezoneOffset;
        }

        public void setTimezoneOffset(int timezoneOffset) {
            this.timezoneOffset = timezoneOffset;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public int getMinutes() {
            return minutes;
        }

        public void setMinutes(int minutes) {
            this.minutes = minutes;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }
    }
}
