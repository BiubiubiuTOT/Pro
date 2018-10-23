package com.ljkj.qxn.wisdomsitepro.Utils;

import com.ljkj.qxn.wisdomsitepro.manager.UserManager;

import java.net.URLEncoder;

import cdsp.android.base.BaseApplication;
import cdsp.android.constant.Consts;
import cdsp.android.http.RequestParams;
import cdsp.android.util.DeviceUtils;

/**
 * 类描述：
 * 创建人：mhl
 * 创建时间：2018/3/15 14:05
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class UrlUtil {

    public static String encodeParams(RequestParams params) {

        String url = "";
        try {
            url = "&params=" + URLEncoder.encode(params.toString(), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return url;
    }

    /**
     * 添加协议参数
     */
    public static String buildProtocolParams(String url) {

        StringBuilder sbParams = new StringBuilder();
        if (!url.contains("?")) {
            sbParams.append("?" + Consts.REQUEST_PARAM_APP_ID + "=" + BaseApplication.getApplication().getAppId());
        } else {
            sbParams.append("&" + Consts.REQUEST_PARAM_APP_ID + "=" + BaseApplication.getApplication().getAppId());
        }
        sbParams.append("&" + Consts.REQUEST_PARAM_DEVICE_ID + "=" + DeviceUtils.getDeviceId(BaseApplication.getAppContext()));
        sbParams.append("&" + Consts.REQUEST_PARAM_USER_TOKEN + "=" + UserManager.getInstance().getUserToken());
        return sbParams.toString();
    }
}
