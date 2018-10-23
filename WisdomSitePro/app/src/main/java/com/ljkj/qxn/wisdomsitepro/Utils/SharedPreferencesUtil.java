package com.ljkj.qxn.wisdomsitepro.Utils;


import android.content.Context;
import android.content.SharedPreferences;

/**
 * 类描述：SharedPreferences 工具类
 * 创建人：lxx
 * 创建时间：2018/5/30 16:45
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SharedPreferencesUtil {
    private static SharedPreferences mSharedPreferences;
    private static final String SP_NAME = "config";

    /**
     * 存储String
     *
     * @param context context
     * @param key     key
     * @param value   value
     */
    public static void putString(Context context, String key, String value) {
        if (mSharedPreferences == null) {
            mSharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        }
        mSharedPreferences.edit().putString(key, value).apply();
    }


    public static void putBoolean(Context context, String key, boolean booleanValue) {
        if (mSharedPreferences == null) {
            mSharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        }
        mSharedPreferences.edit().putBoolean(key, booleanValue).apply();
    }

    public static boolean getBoolean(Context context, String key, boolean defValue) {
        if (mSharedPreferences == null) {
            mSharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        }
        return mSharedPreferences.getBoolean(key, defValue);
    }

    /**
     * 获取String
     *
     * @param context  context
     * @param key      key
     * @param defValue 默认值
     * @return 返回存储的值
     */
    public static String getString(Context context, String key, String defValue) {
        if (mSharedPreferences == null) {
            mSharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        }
        return mSharedPreferences.getString(key, defValue);
    }

    /**
     * 是否存在对应的key
     *
     * @param context context
     * @param key     key
     * @return boolean
     */
    public static boolean contains(Context context, String key) {
        if (mSharedPreferences == null) {
            mSharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        }
        return mSharedPreferences.contains(key);
    }

    /**
     * 移除对应的key
     *
     * @param context context
     * @param key     key
     */
    public static void remove(Context context, String key) {
        if (mSharedPreferences == null) {
            mSharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        }
        mSharedPreferences.edit().remove(key).apply();
    }

    /**
     * 清除SharePreference
     *
     * @param context context
     */
    public static void clear(Context context) {
        if (mSharedPreferences == null) {
            mSharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        }
        mSharedPreferences.edit().clear().apply();
    }

}