package com.ljkj.qxn.wisdomsitepro.Utils;

import android.content.Context;

import com.ljkj.qxn.wisdomsitepro.data.api.HostConfig;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.ui.common.WebViewActivity;
import com.ljkj.qxn.wisdomsitepro.ui.common.WebViewFragment;

import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.HashMap;

import cdsp.android.base.BaseApplication;
import cdsp.android.constant.Consts;
import cdsp.android.util.DeviceUtils;

/**
 * html5协助类
 * 创建人：lxx
 * 创建时间：2018/5/23 14:13
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class H5Helper {

    /** 项目 首页详情 */
    private static final String PRO_HOME_DETAIL = "/proInfo.do?editBase";

    /** 关于我们 */
    private static final String ABOUT_US = "/loginController.do?About_us";

    /** 质量/安全 统计 */
    private static final String QUA_SAFE_STATISTICAL = "/qualityTjController.do?statistical";

    /** 农民工维权 */
    private static final String GO_GSP = "/proInfo.do?goGsp";

    /** 建筑节能公示牌 */
    private static final String ENERGY_CONSERVATION = "/proInfo.do?getJzjnPublicityH5";

    /** 工程概况牌 */
    private static final String PROFILE = "/proInfo.do?profile";

    /** 劳务人员统计 */
    private static final String LABOR_PERSON_STATISTICS = "/laborController.do?laborCount";

    /** 环境监测 */
    private static final String ENVIRONMENT_MANAGER = "/environmentController.do?getLatest";

    /** 劳务人员详情 */
    private static final String LABOR_PERSON_DETAIL = "/laborController.do?laborDetail";

    /** pdf预览 */
    private static final String PDF_DETAIL = "/securityController.do?safetySys";

    /** 五方主体 */
    private static final String PRO_WFZT_DETAIL_URL = "/proInfo.do?listUnits";

    /** 晴雨表 */
    private static final String BAROMETER_URL = "/proInfo.do?yearCalendar";

    /**
     * 关于我们H5
     *
     * @param context context
     * @param title   标题
     */
    public static void toAboutUs(Context context, String title) {
        String url = generateUrl(HostConfig.getHost() + ABOUT_US, null);
        WebViewActivity.startActivity(context, title, url);
    }

    /**
     * 质量/安全 统计 H5
     *
     * @param context context
     * @param title   标题
     * @param proId   项目id
     * @param type    1:安全统计  2:质量统计
     */
    public static void toQualitySafeStatistics(Context context, String title, String proId, int type) {
        String url;
        if (type == 1) {
            url = getHost() + "/app/safe?projId=" + proId;
        } else {
            url = getHost() + "/app/quality?projId=" + proId;
        }
        WebViewActivity.startActivity(context, title, url);
    }

    /**
     * 农民工维权 H5
     *
     * @param context context
     * @param title   标题
     * @param proId   项目id
     * @param type    type
     */
    public static void toGSP(Context context, String title, String proId, int type) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("type", type);
        params.put("proId", proId);
        params.put("goNew", "true");
        String url = generateUrl(HostConfig.getHost() + GO_GSP, params);
        WebViewActivity.startActivity(context, title, url);
    }

    /**
     * 建筑节能公示牌 H5
     *
     * @param context context
     * @param title   标题
     * @param proId   项目id
     */
    public static void toEnergyConservation(Context context, String title, String proId) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("proId", proId);
        String url = generateUrl(HostConfig.getHost() + ENERGY_CONSERVATION, params);
        WebViewActivity.startActivity(context, title, url);
    }

    /**
     * 人员统计 H5
     *
     * @param context context
     * @param title   标题
     * @param proId   项目id
     */
    public static void toLaborPersonStatistics(Context context, String title, String proId) {
        String url = getHost() + "/app/person?projId=" + proId;
        WebViewActivity.startActivity(context, title, url);
    }


    /**
     * 工程概况牌 H5
     *
     * @param context context
     * @param title   标题
     * @param proId   项目id
     */
    public static void toProfile(Context context, String title, String proId) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("proId", proId);
        String url = generateUrl(HostConfig.getHost() + PROFILE, params);
        WebViewActivity.startActivity(context, title, url);
    }


    /**
     * 环境监测 H5
     *
     * @param context context
     * @param title   标题
     * @param proId   项目id
     */
    public static void toEnvironmentManager(Context context, String title, String proId) {
        String url = getHost() + "/app/environment?projId=" + proId;
        WebViewActivity.startActivity(context, title, url);
    }

    /**
     * 劳务人员详情 H5
     *
     * @param context context
     * @param title   标题
     * @param id      劳务人员id
     */
    public static void toLaborPersonDetail(Context context, String title, String id) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", id);
        String url = generateUrl(HostConfig.getHost() + LABOR_PERSON_DETAIL, params);
        WebViewActivity.startActivity(context, title, url);
    }

    /**
     * 五方责任主体
     *
     * @param context context
     * @param title   标题
     * @param proId   劳务人员id
     */
    public static void toWFZRT(Context context, String title, String proId) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("proId", proId);
        String url = generateUrl(HostConfig.getHost() + PRO_WFZT_DETAIL_URL, params);
        WebViewActivity.startActivity(context, title, url);
    }

    /**
     * 晴雨表
     *
     * @param context context
     * @param title   标题
     * @param proId   项目id
     */
    public static void toBarometer(Context context, String title, String proId) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("proId", proId);
        String url = generateUrl(HostConfig.getHost() + BAROMETER_URL, params);
        WebViewActivity.startActivity(context, title, url);
    }

    /**
     * pdf预览 H5
     *
     * @param path 文件路径
     * @return WebViewFragment2
     */
    public static WebViewFragment getPdfFragment(String path) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("path", path);
        String url = generateUrl(HostConfig.getHost() + PDF_DETAIL, params);
        return WebViewFragment.newInstance(url);
    }

    /**
     * 首页H5
     *
     * @param proId 项目Id
     * @return WebViewFragment
     */
    public static WebViewFragment getHomeFragment(String proId) {
        return WebViewFragment.newInstance(getHomeUrl(proId));
    }

    public static String getHomeUrl(String proId) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("proId", proId);
        return generateUrl(HostConfig.getHost() + PRO_HOME_DETAIL, params);
    }

    private static String generateUrl(String url, HashMap<String, Object> params) {
        StringBuilder h5Url = new StringBuilder();
        h5Url.append(url);
        h5Url.append("&" + Consts.REQUEST_PARAM_APP_ID + "=").append(BaseApplication.getApplication().getAppId());
        h5Url.append("&" + Consts.REQUEST_PARAM_DEVICE_ID + "=").append(DeviceUtils.getDeviceId(BaseApplication.getAppContext()));
        h5Url.append("&" + Consts.REQUEST_PARAM_USER_TOKEN + "=").append(UserManager.getInstance().getUserToken());
        if (params != null) {
            try {
                h5Url.append("&params=").append(URLEncoder.encode(new JSONObject(params).toString(), "UTF-8"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return h5Url.toString();
    }


    private static String getHost() {
        if (HostConfig.getHost2().equals(HostConfig.HOST_RELEASE_V2)) {
            return "http://120.78.162.216:8082/#";
        } else {
            return "http://10.2.100.240:8082/#";
        }
    }

}
