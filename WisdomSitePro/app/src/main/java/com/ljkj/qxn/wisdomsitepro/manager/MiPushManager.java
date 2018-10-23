package com.ljkj.qxn.wisdomsitepro.manager;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;

import com.ljkj.qxn.wisdomsitepro.MainActivity;
import com.ljkj.qxn.wisdomsitepro.SplashActivity;
import com.ljkj.qxn.wisdomsitepro.WApplication;
import com.ljkj.qxn.wisdomsitepro.data.api.HostConfig;
import com.ljkj.qxn.wisdomsitepro.ui.message.NoticeDetailActivity;
import com.ljkj.qxn.wisdomsitepro.ui.personal.AboutActivity;
import com.ljkj.qxn.wisdomsitepro.ui.quality.QuaGenerHiddenDangerOfImmeRectiActivity;
import com.ljkj.qxn.wisdomsitepro.ui.safe.SafeGenerHiddenDangerOfImmeRectiActivity;
import com.xiaomi.channel.commonutils.logger.LoggerInterface;
import com.xiaomi.mipush.sdk.Logger;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xiaomi.mipush.sdk.MiPushMessage;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import cdsp.android.util.ToastUtils;

/**
 * 类描述：小米推送 管理类
 * 创建人：lxx
 * 创建时间：2018/6/5 09:44
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class MiPushManager {
    private static final String TAG = "MiPush";
    private static final String APP_ID = "2882303761517785423";
    private static final String APP_KEY = "5491778574423";
    private static final String APP_SECRET = "noc93K6rB1jK5sWhIlAJOQ=="; //服务器端的身份标识

    private String regId;
    private boolean isUnregister = false; //是否调用了unregisterPush();

    private MiPushManager() {
    }

    public static MiPushManager getInstance() {
        return PushHolder.holder;
    }

    private static class PushHolder {
        private static MiPushManager holder = new MiPushManager();
    }

    //注册MiPush推送服务
    public void registerPush(Context context) {
        if (shouldInit(context)) {
            MiPushClient.registerPush(context, APP_ID, APP_KEY);
        }
        enableLogger(context);
        isUnregister = false;
    }

    //关闭MiPush推送服务，当用户希望不再使用MiPush推送服务的时候调用，
    // 调用成功之后，app将不会接收到任何MiPush服务推送的数据，直到下一次调用registerPush
    public void unregisterPush(Context context) {
        MiPushClient.unregisterPush(context);
        isUnregister = true;
    }

    //alias可以理解为regId的别名，开发者可以将alias设置为自己应用帐号系统的帐号，或者设备标识等。然后在使用Server
    //SDK发送消息的时候，即可直接指定发送给特定的alias，而不是regId，避免存储regId
    //注: 一个RegId可以被设置多个别名，如果设置的别名已经存在，会覆盖掉之前的别名。
    public void setAlias(Context context, String alias) {
        //设置别名
        MiPushClient.setAlias(context, alias, null);
    }

    //撤销别名
    public void unsetAlias(Context context, String alias) {
        MiPushClient.unsetAlias(context, alias, null);
    }

    //开发者可以在不同设备上设置同一个userAccount。然后使用Server
    //SDK给该userAccount发送消息；此时，所有设置了该userAccount的设备都可以收到消息
    public void setUserAccount(Context context, String userAccount) {
        //设置帐号
        MiPushClient.setUserAccount(context, userAccount, null);
    }

    //撤销帐号
    public void unsetUserAccount(Context context, String userAccount) {
        MiPushClient.unsetUserAccount(context, userAccount, null);
    }

    //主题用来做广播消息。不同手机上的同一个app可以订阅同一个主题。通过发送主题消息的API，
    // 即可同时向所有订阅该主题的客户端发送消息。比如，您有一个新闻类的app，
    // 可以自定义“财经”、“体育”、“科技“等主题；对于经常阅读财经新闻的用户，
    // 您可以帮用户订阅“财经”类主题，当有新的财经要闻发生时，直接通过主题推送该新闻给所有订阅该主题的用户。
    public void subscribe(Context context, String topic) {
        //为某个用户设置订阅topic
        MiPushClient.subscribe(context, topic, null);
    }

    //取消某个用户的订阅topic
    public void unsubscribe(Context context, String topic) {
        MiPushClient.unsubscribe(context, topic, null);
    }

    //暂停接收MiPush服务推送的消息
    public void pausePush(Context context) {
        MiPushClient.pausePush(context, null);
    }

    //恢复接收MiPush服务推送的消息，这时服务器会把暂停时期的推送消息重新推送过来
    public void resumePush(Context context) {
        MiPushClient.resumePush(context, null);
    }

    //获取客户端所有设置的别名
    public List<String> getAllAlias(final Context context) {
        return MiPushClient.getAllAlias(context);
    }

    //获取客户端所有订阅的主题
    public List<String> getAllTopic(final Context context) {
        return MiPushClient.getAllAlias(context);
    }

    //获取客户端所有设置的帐号
    public List<String> getAllUserAccount(final Context context) {
        return MiPushClient.getAllUserAccount(context);
    }

    //获取客户端的RegId
    public String getRegId(Context context) {
        return MiPushClient.getRegId(context);
    }

    //清除小米推送弹出的某一个notifyId通知
    public void clearNotification(Context context, int notifyId) {
        MiPushClient.clearNotification(context, notifyId);
    }

    //清除小米推送弹出的所有通知
    public void clearNotification(Context context) {
        MiPushClient.clearNotification(context);
    }

    /**
     * 打开Logcat调试日志,在自定义Application的onCreate中调用<br/>
     * 默认情况下，会将日志内容写入SDCard/Android/data/apppkgname/files/MiPushLog目录下的文件
     */
    private void enableLogger(Context context) {
        LoggerInterface newLogger = new LoggerInterface() {
            @Override
            public void setTag(String tag) {
                // ignore
            }

            @Override
            public void log(String content, Throwable t) {
                Log.d(TAG, content, t);
            }

            @Override
            public void log(String content) {
                Log.d(TAG, content);
            }
        };
        Logger.setLogger(context, newLogger);
    }

    /**
     * 如果app需要关闭写日志文件功能（不建议关闭），只需要调用Logger.disablePushFileLog(context)即可
     */
    public void disableLogger(Context context) {
        Logger.disablePushFileLog(context);
    }

    private boolean shouldInit(Context context) {
        ActivityManager am = ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE));
        List<ActivityManager.RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
        String mainProcessName = context.getPackageName();
        int myPid = Process.myPid();
        for (ActivityManager.RunningAppProcessInfo info : processInfos) {
            if (info.pid == myPid && mainProcessName.equals(info.processName)) {
                return true;
            }
        }
        return false;
    }

    public void onReceiveRegisterSuccess(String regId) {
        this.regId = regId;
        setAliasAndUserAccount();
    }

    public void setAliasAndUserAccount() {
        if (TextUtils.isEmpty(this.regId)) {
            return;
        }
        if (!UserManager.getInstance().needLogin()) {
            String userId = UserManager.getInstance().getUserId();
            setAlias(WApplication.getApplication(), userId);
        }
        setUserAccount(WApplication.getApplication(), HostConfig.getPushUserAccount());
        if (!TextUtils.isEmpty(UserManager.getInstance().getProjectId())) {
            setUserAccount(WApplication.getApplication(), UserManager.getInstance().getProjectId());
        }
    }

    public boolean isUnregister() {
        return isUnregister;
    }

    public void doForLogout(Context context) {
        unsetAlias(context, UserManager.getInstance().getUserId());
        clearNotification(context);
        unregisterPush(context);
    }

    public void onReceivePassThroughMessage(MiPushMessage miPushMessage) {
        boolean isNotified = miPushMessage.isNotified(); //isNotified表示消息是否通过通知栏传给app的。如果为true，表示消息在通知栏出过通知；如果为false，表示消息是直接传给app的，没有弹出过通知。
        String content = miPushMessage.getContent(); //消息的内容
    }

    public void onNotificationMessageArrived(MiPushMessage miPushMessage) {
        String content = miPushMessage.getContent();
    }

    public void onNotificationMessageClicked(MiPushMessage miPushMessage) {
        String content = miPushMessage.getContent();
        if (TextUtils.isEmpty(content)) {
            return;
        }
        if (MainActivity.isMainActivityOn()) {
            handle(WApplication.getApplication(), content, true);
        } else {
            Intent intent = new Intent(WApplication.getApplication(), SplashActivity.class);
//            Intent intent = WApplication.getApplication().getPackageManager().getLaunchIntentForPackage(WApplication.getApplication().getPackageName());
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
            intent.putExtra(SplashActivity.KEY_PUSH_CONTENT, content);
            WApplication.getApplication().startActivity(intent);
        }
    }

    /** 公告 */
    private static final int PUSH_TYPE_NOTICE = 1;
    /** 检查 */
    private static final int PUSH_TYPE_CHECK = 2;

    public void handle(Context context, String pushContent, boolean fromPush) {
        try {
            JSONObject jsonObject = new JSONObject(pushContent);
            int pushType = jsonObject.getInt("type");
            JSONObject resultObject = jsonObject.getJSONObject("result");
            switch (pushType) {
                case PUSH_TYPE_NOTICE:
                    handleForNotice(context, resultObject, fromPush);
                    break;
                case PUSH_TYPE_CHECK:
                    handleForCheck(context, jsonObject);
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //处理公告的推送逻辑
    private void handleForNotice(Context context, JSONObject resultObject, boolean fromPush) throws JSONException {
        String id = resultObject.getString("id");
        if (!TextUtils.isEmpty(id)) {
            NoticeDetailActivity.startActivity(context, id, fromPush);
        }
    }

    //处理检查的推送逻辑
    private void handleForCheck(Context context, JSONObject resultObject) throws JSONException {
        //TODO
        String id = resultObject.getString("id");
        int checkType = 1;
        if (!TextUtils.isEmpty(id)) {
            if (checkType == 1) {
                //立即整改-一般隐患
                SafeGenerHiddenDangerOfImmeRectiActivity.startActivity(context, QuaGenerHiddenDangerOfImmeRectiActivity.TYPE_NORMAL, id);
                //立即整改-重大隐患
                SafeGenerHiddenDangerOfImmeRectiActivity.startActivity(context, QuaGenerHiddenDangerOfImmeRectiActivity.TYPE_MAJOR, id);
            } else {
                QuaGenerHiddenDangerOfImmeRectiActivity.startActivity(context, QuaGenerHiddenDangerOfImmeRectiActivity.TYPE_NORMAL, id);
                QuaGenerHiddenDangerOfImmeRectiActivity.startActivity(context, QuaGenerHiddenDangerOfImmeRectiActivity.TYPE_MAJOR, id);
            }
        }
    }

    private void test(String content, Canvas canvas) {
        ToastUtils.showShort("小米推送：" + content);
        Intent intent = new Intent(WApplication.getApplication(), AboutActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        WApplication.getApplication().startActivity(intent);
    }

}
