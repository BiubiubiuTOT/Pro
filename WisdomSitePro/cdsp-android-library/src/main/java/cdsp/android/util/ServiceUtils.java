package cdsp.android.util;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;

import java.util.List;

import cdsp.android.base.BaseApplication;

/**
 * ================================================
 * 作    者：loujingying@aliyun.com
 * 版    本：1.0.0
 * 创建日期：2017/8/4
 * 描    述：service相关工具类
 * 修订历史：
 * ================================================
 */

public final class ServiceUtils {

    private static final int MAX_NUM = 100;

    /**
     * 判断某个服务是否活着
     *
     * @param service
     * @return
     */
    public static boolean isServiceRunning(Class service) {

        Context context = BaseApplication.getApplication();
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);

        List<ActivityManager.RunningServiceInfo> runningServices = am.getRunningServices(MAX_NUM);
        String serviceName = service.getName();

        for (ActivityManager.RunningServiceInfo rs : runningServices) {
            String className = rs.service.getClassName();
            if (className.equals(serviceName)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 判断某个服务是否活着
     *
     * @param context
     * @param className
     * @return
     */
    public static boolean isServiceRunning(Context context, String className) {
        boolean isRunning = false;
        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> serviceInfos = activityManager
                .getRunningServices(Integer.MAX_VALUE);
        for (ActivityManager.RunningServiceInfo si : serviceInfos) {
            if (className.equals(si.service.getClassName())) {
                isRunning = true;
            }
        }
        return isRunning;
    }

    /**
     * 停止service运行
     *
     * @param context
     * @param className
     * @return
     */
    public static boolean stopRunningService(Context context, String className) {
        Intent intent_service = null;
        boolean ret = false;
        try {
            intent_service = new Intent(context, Class.forName(className));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (intent_service != null) {
            ret = context.stopService(intent_service);
        }
        return ret;
    }


}
