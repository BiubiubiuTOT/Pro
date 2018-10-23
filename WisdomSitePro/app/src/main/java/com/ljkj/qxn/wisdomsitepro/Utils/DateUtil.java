package com.ljkj.qxn.wisdomsitepro.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 类描述：转换时间格式
 * 创建人：liufei
 * 创建时间：2018/3/12
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class DateUtil {
    public static SimpleDateFormat dateFormat;

    public static String format(String millisStr) {
        dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        try {
            long millis = Long.parseLong(millisStr);
            return dateFormat.format(millis);
        } catch (Exception e) {
            e.printStackTrace();
            return millisStr;
        }
    }

    public static String formatYM(String millisStr) {
        dateFormat = new SimpleDateFormat("yyyy-MM", Locale.getDefault());
        try {
            long millis = Long.parseLong(millisStr);
            return dateFormat.format(millis);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String formatToHHmm(String millisStr) {
        dateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        try {
            long millis = Long.parseLong(millisStr);
            return dateFormat.format(millis);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }


    public static boolean canDelete(String createTime) {
        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        try {
            Date date = dateFormat.parse(createTime);
            return System.currentTimeMillis() < (date.getTime() + 24 * 60 * 60 * 1000);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 通过时间秒毫秒数判断两个时间的间隔
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDays(String date1, String date2) {
        dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        int days = 0;
        try {
            Date par1 = dateFormat.parse(date1);
            Date par2 = dateFormat.parse(date2);
            days = (int) ((par2.getTime() - par1.getTime()) / (1000 * 3600 * 24));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return days;
    }
}
