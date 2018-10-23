package com.ljkj.qxn.wisdomsitepro.data;

import java.util.ArrayList;
import java.util.List;

public class SafeQualityStatisticsInfo {
    public List<Data> secPatrol = new ArrayList<>();
    public List<Data> quaPatrol = new ArrayList<>();
    public List<Data> secCheck = new ArrayList<>();
    public List<Data> quaCheck = new ArrayList<>();

    public static class Data {
        public int data;
        public int year;
        public int month;
        public int day;
    }

}
