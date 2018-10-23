package com.ljkj.qxn.wisdomsitepro.Utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 类描述：
 * 创建人：liufei
 * 创建时间：2018/2/2
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class CalendarUtil {
    private static CalendarUtil instance;
    private List<Date> days = new ArrayList<>();
    private Calendar calendar = Calendar.getInstance();

    public static CalendarUtil newInstance() {
        if (instance == null) {
            instance = new CalendarUtil();
        }
        return instance;
    }

    //根据日期获取日历数据
    public List<Date> getCalendarData(Date date) {
        days = new ArrayList<>();
        calendar = Calendar.getInstance();
        int year = Integer.parseInt(formatTime(date, "yyyy"));
        //设置日历的日期，从1号开始
        calendar.set(year, date.getMonth(), 1);
        //星期一在前，计算1号是第几位
        int iDay = calendar.get(Calendar.DAY_OF_WEEK) - Calendar.MONDAY;
        if (iDay < 0)
            iDay = 6;
        calendar.add(Calendar.DAY_OF_WEEK, -iDay);
        //当前日历显示的总天数
        int count = getDaysOfMonth(calendar) + iDay < 36 ? 35 : 42;
        for (int i = 1; i <= count; i++) {
            days.add(calendar.getTime());
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        return days;
    }

    //获取当前月有多少天
    public int getDaysOfMonth(Calendar calendar) {
        Calendar lastDate = Calendar.getInstance();
        lastDate.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, 1);
        lastDate.add(Calendar.DATE, -1);
        return Integer.parseInt(formatTime(lastDate.getTime(), "dd"));
    }

    public String formatTime(Date date, String format) {
        SimpleDateFormat mFormat = new SimpleDateFormat(format);
        return mFormat.format(date);
    }
}
