package com.ljkj.qxn.wisdomsitepro.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherInfo {

    @SerializedName("HeWeather6")
    public List<HeWeather> heWeathers;

    public static class HeWeather {
        /** 实况天气 */
        public Now now;
    }

    public static class Now {
        /** 云量 */
        public String cloud;

        /** 能见度，默认单位：公里 */
        public String vis;

        /** 大气压强 */
        public String pres;

        /** 降水量 */
        public String pcpn;

        /** 相对湿度 */
        public String hum;

        /** 风速，公里/小时 */
        public String wind_spd;

        /** 风力 */
        public String wind_sc;

        /** 风向 */
        public String wind_dir;

        /** 风向360角度 */
        public String wind_deg;

        /** 体感温度，默认单位：摄氏度 */
        public String fl;

        /** 温度，默认单位：摄氏度 */
        public String tmp;

        /** 实况天气状况 */
        public String cond_txt;

    }

}
