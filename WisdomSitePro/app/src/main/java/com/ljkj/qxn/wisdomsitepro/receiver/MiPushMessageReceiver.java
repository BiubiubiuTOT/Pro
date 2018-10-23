package com.ljkj.qxn.wisdomsitepro.receiver;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.ljkj.qxn.wisdomsitepro.BuildConfig;
import com.ljkj.qxn.wisdomsitepro.manager.MiPushManager;
import com.xiaomi.mipush.sdk.ErrorCode;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xiaomi.mipush.sdk.MiPushCommandMessage;
import com.xiaomi.mipush.sdk.MiPushMessage;
import com.xiaomi.mipush.sdk.PushMessageReceiver;

import java.util.List;

/**
 * 类描述：小米推送接收器
 * 创建人：lxx
 * 创建时间：2018/6/5 11:17
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class MiPushMessageReceiver extends PushMessageReceiver {
    private static final String TAG = "MiPush";
    private Handler uiHandler = new Handler(Looper.getMainLooper());

    @Override
    public void onReceivePassThroughMessage(Context context, final MiPushMessage miPushMessage) {
        //接收服务器发送的透传消息
        logD(TAG, "onReceivePassThroughMessage is called ==> " + miPushMessage.toString());
        uiHandler.post(new Runnable() {
            @Override
            public void run() {
                MiPushManager.getInstance().onReceivePassThroughMessage(miPushMessage);
            }
        });
        super.onReceivePassThroughMessage(context, miPushMessage);
    }

    @Override
    public void onNotificationMessageArrived(Context context, final MiPushMessage miPushMessage) {
        //接收服务器向客户端发送的通知消息，这个回调方法是在通知消息到达客户端时触发。另外应用在前台时不弹出通知的通知消息到达客户端也会触发这个回调函数
        logD(TAG, "onNotificationMessageArrived is called ==>" + miPushMessage.toString());
        uiHandler.post(new Runnable() {
            @Override
            public void run() {
                MiPushManager.getInstance().onNotificationMessageArrived(miPushMessage);
            }
        });
        super.onNotificationMessageArrived(context, miPushMessage);
    }

    @Override
    public void onNotificationMessageClicked(final Context context, final MiPushMessage miPushMessage) {
        //接收服务器发来的通知栏消息（用户点击通知栏时触发）
        logD(TAG, "onNotificationMessageClicked is called ==>" + miPushMessage.toString());
        uiHandler.post(new Runnable() {
            @Override
            public void run() {
                MiPushManager.getInstance().onNotificationMessageClicked(miPushMessage);
            }
        });
        super.onNotificationMessageClicked(context, miPushMessage);

    }

    @Override
    public void onCommandResult(Context context, MiPushCommandMessage miPushCommandMessage) {
        //接收客户端向服务器发送命令消息后返回的响应
        logD(TAG, "onCommandResult is called ==>" + miPushCommandMessage.toString());
        String command = miPushCommandMessage.getCommand();
        List<String> arguments = miPushCommandMessage.getCommandArguments();
        String cmdArg1 = ((arguments != null && arguments.size() > 0) ? arguments.get(0) : null);
        String cmdArg2 = ((arguments != null && arguments.size() > 1) ? arguments.get(1) : null);
        if (MiPushClient.COMMAND_SET_ALIAS.equals(command)) { //调用MiPushClient.setAlias()，返回MiPushClient.COMMAND_SET_ALIAS
            if (miPushCommandMessage.getResultCode() == ErrorCode.SUCCESS) {
                String alias = cmdArg1;
            }
        } else if (MiPushClient.COMMAND_UNSET_ALIAS.equals(command)) { //调用MiPushClient.unsetAlias()，返回MiPushClient.COMMAND_UNSET_ALIAS
            if (miPushCommandMessage.getResultCode() == ErrorCode.SUCCESS) {
                String alias = cmdArg1;
            }
        } else if (MiPushClient.COMMAND_SUBSCRIBE_TOPIC.equals(command)) { //调用MiPushClient.subscribe()，返回MiPushClient.COMMAND_SUBSCRIBE_TOPIC
            if (miPushCommandMessage.getResultCode() == ErrorCode.SUCCESS) {
                String topic = cmdArg1;
            }
        } else if (MiPushClient.COMMAND_UNSUBSCRIBE_TOPIC.equals(command)) { //调用MiPushClient.unsubscribe()，返回MiPushClient.COMMAND_UNSUBSCIRBE_TOPIC
            if (miPushCommandMessage.getResultCode() == ErrorCode.SUCCESS) {
                String topic = cmdArg1;
            }
        } else if (MiPushClient.COMMAND_SET_ACCEPT_TIME.equals(command)) { //调用MiPushClient.setAcceptTime()，返回MiPushClient.COMMAND_SET_ACCEPT_TIME
            if (miPushCommandMessage.getResultCode() == ErrorCode.SUCCESS) {
                String startTime = cmdArg1;
                String endTime = cmdArg2;
            }
        }
        super.onCommandResult(context, miPushCommandMessage);
    }

    @Override
    public void onReceiveRegisterResult(Context context, MiPushCommandMessage miPushCommandMessage) {
        //接受客户端向服务器发送注册命令消息后返回的响应
        logD(TAG, "onReceiveRegisterResult is called ==>" + miPushCommandMessage.toString());
        String command = miPushCommandMessage.getCommand();
        List<String> arguments = miPushCommandMessage.getCommandArguments();
        String cmdArg1 = ((arguments != null && arguments.size() > 0) ? arguments.get(0) : null);
        if (MiPushClient.COMMAND_REGISTER.equals(command)) {
            if (miPushCommandMessage.getResultCode() == ErrorCode.SUCCESS) {
                //注册成功
                final String regId = cmdArg1;
                uiHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        logD(TAG, "小米推送注册成功");
                        MiPushManager.getInstance().onReceiveRegisterSuccess(regId);
                    }
                });
            }
        }
        super.onReceiveRegisterResult(context, miPushCommandMessage);
    }

    private void logD(String tag, String message) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, message);
        }
    }

}
