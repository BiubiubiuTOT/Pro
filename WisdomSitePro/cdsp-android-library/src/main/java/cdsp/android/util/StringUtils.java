package cdsp.android.util;

import android.text.TextUtils;
import android.widget.EditText;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cdsp.android.constant.Consts;

/**
 * ================================================
 * 作    者：loujingying@aliyun.com
 * 版    本：1.0.0
 * 创建日期：2017/8/4
 * 描    述：字符串工具类
 * 修订历史：
 * ================================================
 */

public abstract class StringUtils {

    /**
     *  判断字符串是否为空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0 || str.equalsIgnoreCase("null")) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断字符串是否相等
     *
     * @param a
     * @param b
     * @return
     */
    public static boolean equals(CharSequence a, CharSequence b) {
        return TextUtils.equals(a, b);
    }

    /**
     * 判断字符串是否为数字
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        for (int i = str.length(); --i >= 0; ) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 字符串url编码
     *
     * @param str
     * @return
     */
    public static String encodeString(String str) {
        try {
            return URLEncoder.encode(str, Consts.DEFAULT_ENCODING);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return str;
        }
    }

    /**
     * 字符串url解码
     *
     * @param str
     * @return
     */
    public static String decodeString(String str) {
        try {
            return URLDecoder.decode(str, Consts.DEFAULT_ENCODING);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return str;
        }
    }

    /**
     * 将字符串转为小写
     *
     * @param s
     * @return
     */
    public static String toLowerCase(String s) {
        return s.toLowerCase(Locale.getDefault());
    }

    /**
     * 将字符串转为大写
     *
     * @param s
     * @return
     */
    public static String toUpperCase(String s) {
        return s.toUpperCase(Locale.getDefault());
    }

    /**
     * 获取字符串文本
     *
     * @param editText
     * @return
     */
    public static String getText(EditText editText) {
        return editText.getText().toString().trim();
    }

    /**
     * 获取一段字符串中的数字
     *
     * @param originalStr
     * @return
     */
    public static final String getStringNum(String originalStr) {
        String numberStr = "";
        originalStr.trim();
        if (originalStr != null && !"".equals(originalStr)) {
            for (int i = 0; i < originalStr.length(); i++) {
                if (originalStr.charAt(i) >= 48 && originalStr.charAt(i) <= 57) {
                    numberStr += originalStr.charAt(i);
                }
            }
        }

        return numberStr;
    }

    /**
     * 对给定的字符串返回唯一的标记字符串<br>
     * 主要应用于网络url的标记，将url转换映射成唯一的标识字符串<br>
     * 写法参考Volley中的写法<br>
     *
     * @param str 需要转换的字符串
     * @return 返回唯一的标识
     */
    public static String toHash(String str) {
        String ret = null;
        if (str != null && str.length() > 0) {
            int len = str.length();
            String part1 = str.substring(0, len / 2).hashCode() + "";
            String part2 = str.substring(len / 2).hashCode() + "";
            ret = part1 + part2;
        }
        return ret;
    }


    /**
     * 截取显示字符串
     *
     * @param text
     * @return
     */
    public static String maskText(String text, int number) {
        if (TextUtils.isEmpty(text)) {
            return "";
        }
        if (text.length() > number) {
            text = text.substring(0, number) + "...";
        }
        return text;
    }

    /**
     * 获取手机号后4位数
     *
     * @param phone
     * @return
     */
    public static String getBehindFourNumber(String phone) {
        return phone.substring(phone.length() - 4, phone.length());
    }

    /**
     * 格式化钱字符串
     *
     * @param cost
     * @return
     */
    public static String formatMoney(float cost) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        return "￥" + decimalFormat.format(cost);
    }

    /**
     * 获取汉字的个数
     *
     * @param str
     * @return
     */
    public static int getChineseCount(CharSequence str) {
        String regEx = "[\\u4e00-\\u9fa5]";
        int count = 0;
        if (!TextUtils.isEmpty(str)) {
            Pattern p = Pattern.compile(regEx);// 计算有几个unicode字
            Matcher m = p.matcher(str);
            while (m.find()) {
                for (int i = 0; i <= m.groupCount(); i++) {
                    count = count + 1;
                }
            }
            count += str.length();
        }
        return count;
    }


}
