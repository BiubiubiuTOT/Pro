package com.ljkj.qxn.wisdomsitepro.data.api;


import android.text.TextUtils;

import com.ljkj.qxn.wisdomsitepro.BuildConfig;

import java.util.ArrayList;
import java.util.List;

import cdsp.android.util.SpUtils;

public class HostConfig {
    public static final String KEY_DEBUG_ADDRESS = "key_debug_address";

    private static final String HOST_DEBUG1 = "http://111.85.152.35:18071/CO-CTMS/m"; //测试环境，可外网访问
    private static final String HOST_DEBUG3 = "http://10.2.100.252:18071/CO-CTMS/m"; //开发环境，公司内网访问

    public static final String HOST_DEBUG2 = "http://10.2.100.240:8081"; //系统服务

    private static final String HOST_RELEASE = "http://120.79.175.210:18071/CO-CTMS/m"; //生产环境地址
    private static final String HOST_RELEASE_DEMO = "http://111.85.152.35:17073/CO-CTMS/m"; //正式环境(演示)，可外网访问


    public static final String HOST_RELEASE_V2 = "http://120.78.162.216:8060/api"; // v2.0生产地址
    private static final String HOST_DEBUG_V2 = "http://10.2.100.240:8060/api"; // v2.0开发地址

    private static String debugAddress;

    @Deprecated
    public static String getHost() {
        return HostConfig.HOST_RELEASE;
    }

    public static String getHost2() {
        if (BuildConfig.DEBUG) {
            if (TextUtils.isEmpty(debugAddress)) {
                debugAddress = SpUtils.getString(KEY_DEBUG_ADDRESS, HOST_DEBUG_V2);
            }
            return debugAddress;
        } else {
            return HOST_RELEASE_V2;
        }
    }

    public static String getFileDownUrl(String fileId) {
        return getHost2() + "/file/down?fid=" + fileId;
    }

    public static void changeDebugAddress(String address) {
        debugAddress = address;
    }

    public static List<String> getDebugAddressList() {
        List<String> list = new ArrayList<>();
        list.add(HOST_DEBUG_V2);
        list.add(HOST_RELEASE_V2);
        return list;
    }

    public static String getPushUserAccount() {
        String host = getHost2();
        if (host.equals(HOST_RELEASE)) {
            return "release";
        }
        if (host.equals(HOST_RELEASE_DEMO)) {
            return "demo";
        }
        return "test";
    }

}
