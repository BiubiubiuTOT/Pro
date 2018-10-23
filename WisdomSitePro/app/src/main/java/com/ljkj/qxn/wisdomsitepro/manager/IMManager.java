package com.ljkj.qxn.wisdomsitepro.manager;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.ljkj.qxn.wisdomsitepro.Utils.SharedPreferencesUtil;
import com.ljkj.qxn.wisdomsitepro.WApplication;

import java.util.Map;

import cdsp.android.logging.Logger;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.UserInfo;

/**
 * 类描述：融云Manger
 * 创建人：lxx
 * 创建时间：2018/7/17
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class IMManager {
    private static final String TAG = "RongIM";
    private static final String KEY_TOKEN = "im_token";

    private boolean isConnect;

    private IMManager() {
    }

    public static IMManager getInstance() {
        return IMHolder.holder;
    }

    private static class IMHolder {
        private static IMManager holder = new IMManager();
    }

    public void saveToken(@NonNull String token) {
        SharedPreferencesUtil.putString(WApplication.getApplication(), KEY_TOKEN, token);
    }

    public String getToken() {
        return SharedPreferencesUtil.getString(WApplication.getApplication(), KEY_TOKEN, "");
    }

    public boolean isConnect() {
        return isConnect;
    }

    /**
     * 连接服务器，在整个应用程序全局，只需要调用一次。
     *
     * @param token           token
     * @param connectCallback connectCallback
     */
    public void connect(String token, final RongIMClient.ConnectCallback connectCallback) {
        RongIM.connect(token, new RongIMClient.ConnectCallback() {
            /**
             * Token 错误。
             * 可以从下面两点检查 1.  Token 是否过期，如果过期您需要向 App Server 重新请求一个新的 Token
             *                  2.  token 对应的 appKey 和工程里设置的 appKey 是否一致
             */
            @Override
            public void onTokenIncorrect() {
                Logger.e(TAG, "onTokenIncorrect()===>" + "Token 错误");
                if (connectCallback != null) {
                    connectCallback.onTokenIncorrect();
                }
            }

            /**
             * 连接融云成功
             * @param userId 当前 token 对应的用户 id
             */
            @Override
            public void onSuccess(String userId) {
                Logger.d(TAG, "onSuccess===>" + "连接融云成功 +  userId=" + userId);
                isConnect = true;
                if (connectCallback != null) {
                    connectCallback.onSuccess(userId);
                }
            }

            /**
             * 连接融云失败
             * @param errorCode 错误码，可到官网 查看错误码对应的注释
             */
            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                Logger.e(TAG, "onError===>" + "连接融云失败 +  errorCode=" + errorCode.getValue());
                isConnect = false;
                if (connectCallback != null) {
                    connectCallback.onError(errorCode);
                }
            }
        });
    }


    /**
     * <p>启动会话界面。</p>
     * <p>使用时，可以传入多种会话类型 {@link io.rong.imlib.model.Conversation.ConversationType} 对应不同的会话类型，开启不同的会话界面。
     * 如果传入的是 {@link io.rong.imlib.model.Conversation.ConversationType#CHATROOM}，sdk 会默认调用
     * {@link RongIMClient#joinChatRoom(String, int, RongIMClient.OperationCallback)} 加入聊天室。
     * 如果你的逻辑是，只允许加入已存在的聊天室，请使用接口 {@link RongIM#startChatRoomChat(Context, String, boolean)} 并且第三个参数为 true</p>
     *
     * @param context          应用上下文。
     * @param conversationType 会话类型。
     * @param targetId         根据不同的 conversationType，可能是用户 Id、群组 Id 或聊天室 Id。
     * @param title            聊天的标题，开发者可以在聊天界面通过 intent.getData().getQueryParameter("title") 获取该值, 再手动设置为标题。
     */
    public void startConversation(Context context, Conversation.ConversationType conversationType, String targetId, String title) {
        RongIM.getInstance().startConversation(context, conversationType, targetId, title);
    }

    /**
     * 启动会话列表界面。
     *
     * @param context               应用上下文。
     * @param supportedConversation 定义会话列表支持显示的会话类型，及对应的会话类型是否聚合显示。
     *                              例如：supportedConversation.put(Conversation.ConversationType.PRIVATE.getName(), false) 非聚合式显示 private 类型的会话。
     */
    public void startConversationList(Context context, Map<String, Boolean> supportedConversation) {
        RongIM.getInstance().startConversationList(context, supportedConversation);
    }

    /**
     * <p>断开与融云服务器的连接。当调用此接口断开连接后，仍然可以接收 Push 消息。</p>
     * <p>若想断开连接后不接受 Push 消息，可以调用{@link #logout()}</p>
     */
    public void disconnect() {
        isConnect = false;
        RongIM.getInstance().disconnect();
    }

    /**
     * <p>断开与融云服务器的连接，并且不再接收 Push 消息。</p>
     * <p>若想断开连接后仍然接受 Push 消息，可以调用 {@link #disconnect()}</p>
     */
    public void logout() {
        isConnect = false;
        RongIM.getInstance().logout();
    }

    /**
     * 刷新用户缓存数据。
     *
     * @param userInfo 需要更新的用户缓存数据。
     */
    public void refreshUserInfoCache(UserInfo userInfo) {
        RongIM.getInstance().refreshUserInfoCache(userInfo);
    }


    /**
     * 启动单聊界面。
     *
     * @param context      应用上下文。
     * @param targetUserId 要与之聊天的用户 Id。
     * @param title        聊天的标题，开发者需要在聊天界面通过 intent.getData().getQueryParameter("title")
     *                     获取该值, 再手动设置为聊天界面的标题。
     */
    public void startPrivateChat(Context context, String targetUserId, String title) {
        RongIM.getInstance().startPrivateChat(context, targetUserId, title);
    }

    /**
     * 启动群组聊天界面。
     *
     * @param context       应用上下文。
     * @param targetGroupId 要聊天的群组 Id。
     * @param title         聊天的标题，开发者可以在聊天界面通过 intent.getData().getQueryParameter("title") 获取该值, 再手动设置为标题。
     */
    public void startGroupChat(Context context, String targetGroupId, String title) {
        RongIM.getInstance().startGroupChat(context, targetGroupId, title);
    }


    /**
     * 启动系统会话聊天界面(系统会话消息由应用服务端发送，客户端只能接收消息，不能进行回复)
     *
     * @param context  应用上下文。
     * @param targetId 目标 Id；
     * @param title    聊天的标题，开发者可以在聊天界面通过 intent.getData().getQueryParameter("title") 获取该值, 再手动设置为标题。
     */
    public void startConversation(Context context, String targetId, String title) {
        RongIM.getInstance().startConversation(context, Conversation.ConversationType.SYSTEM, targetId, title);
    }


    public void init() {
        /**
         * 设置用户信息的提供者，供 RongIM 调用获取用户名称和头像信息。
         *
         * @param userInfoProvider 用户信息提供者。
         * @param isCacheUserInfo  设置是否由 IMKit 来缓存用户信息。<br>
         *                         如果 App 提供的 UserInfoProvider
         *                         每次都需要通过网络请求用户数据，而不是将用户数据缓存到本地内存，会影响用户信息的加载速度；<br>
         *                         此时最好将本参数设置为 true，由 IMKit 将用户信息缓存到本地内存中。
         * @see UserInfoProvider
         */
        RongIM.setUserInfoProvider(new RongIM.UserInfoProvider() {
            @Override
            public UserInfo getUserInfo(String userId) {
                return new UserInfo(userId, UserManager.getInstance().getUserName(), Uri.parse(""));
            }
        }, true);

        Conversation.ConversationType[] types = new Conversation.ConversationType[]{
                Conversation.ConversationType.PRIVATE,
                Conversation.ConversationType.GROUP,
                Conversation.ConversationType.DISCUSSION
        };
        RongIM.getInstance().setReadReceiptConversationTypeList(types); //设置支持消息回执的会话类型

    }


}
