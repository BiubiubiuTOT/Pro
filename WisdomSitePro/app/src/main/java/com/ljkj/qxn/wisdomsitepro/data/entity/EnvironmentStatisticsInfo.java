package com.ljkj.qxn.wisdomsitepro.data.entity;

import java.util.ArrayList;
import java.util.List;

public class EnvironmentStatisticsInfo {
    public List<PmInfo> latest24Data = new ArrayList<>();

    public static class PmInfo {
        public String timeHour;
        public float pm25;
        public float pm10;
    }

}
