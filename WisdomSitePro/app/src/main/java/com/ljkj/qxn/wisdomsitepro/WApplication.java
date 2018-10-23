package com.ljkj.qxn.wisdomsitepro;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.multidex.MultiDex;

import com.ljkj.qxn.wisdomsitepro.Utils.OkGoHelper;
import com.ljkj.qxn.wisdomsitepro.data.Consts;
import com.ljkj.qxn.wisdomsitepro.manager.AuthorityManager;
import com.ljkj.qxn.wisdomsitepro.manager.BuglyManager;
import com.ljkj.qxn.wisdomsitepro.manager.IMManager;
import com.ljkj.qxn.wisdomsitepro.manager.MiPushManager;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.ui.personal.LoginActivity;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;

import cdsp.android.base.AppManager;
import cdsp.android.base.BaseApplication;
import cdsp.android.glide.GlideHelper;
import cdsp.android.logging.Logger;
import io.rong.imkit.RongIM;

/**
 * 类描述：
 * 创建人：mhl
 * 创建时间：2018/2/1 16:35
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class WApplication extends BaseApplication {

    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @NonNull
            @Override
            public RefreshHeader createRefreshHeader(@NonNull Context context, @NonNull RefreshLayout layout) {
                return new MaterialHeader(context).setColorSchemeColors(Color.parseColor("#248bfe"));
            }
        });

        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @NonNull
            @Override
            public RefreshFooter createRefreshFooter(@NonNull Context context, @NonNull RefreshLayout layout) {
                return new ClassicsFooter(context).setSpinnerStyle(SpinnerStyle.Translate);
            }
        });
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.d("Application", "---------------onCreate()---------------");
        OkGoHelper.getInstance();
        GlideHelper.init(R.mipmap.iv_error, R.mipmap.iv_error);
        if (!BuildConfig.DEBUG) {
            BuglyManager.getInstance().initBugly(getAppContext());
        }
        MiPushManager.getInstance().registerPush(this);
//        initRongIM();

//        VideoInit.initPlayBase(this);
    }

    private void initRongIM() {
        if (getApplicationInfo().packageName.equals(getCurProcessName(getApplicationContext()))) {
            RongIM.init(this);
            IMManager.getInstance().connect("l/4oKUR1H6FoLJL8pQgvpyMMNoPGp+ybiP/3GGQWVqEUlaVfrsZXd3JZ8fhr215ykiFQ8m8jHRUV5JZ1O8YX7Sls6OhwRR+V", null); //FIXME:测试代码
        }
    }

    @Override
    public boolean isDevMode() {
        return BuildConfig.DEBUG;
    }

    @Override
    public String getAppId() {
        return Consts.APPParams.APP_ID;
    }

    @Override
    public void tokenExpire() {
        super.tokenExpire();
        MiPushManager.getInstance().doForLogout(this);
        UserManager.getInstance().clearLoginInfo();
        AuthorityManager.getInstance().clear();
        AppManager.getAppManager().finishAllActivity();
        Intent intent = new Intent();
        intent.setClass(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager.getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }

}
