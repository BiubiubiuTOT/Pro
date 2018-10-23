package com.ljkj.qxn.wisdomsitepro;

import android.content.Intent;
import android.os.Bundle;

import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.ui.personal.LoginActivity;

import cdsp.android.ui.base.BaseActivity;

/**
 * 启动页
 * 创建人：lxx
 * 创建时间：2018/3/20 17:43
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SplashActivity extends BaseActivity {
    private static final int DELAY = 2 * 1000;
    public static final String KEY_PUSH_CONTENT = "key_push_content";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!isTaskRoot()) { //https://www.jianshu.com/p/ddc4cec77c7f
            final Intent intent = getIntent();
            final String intentAction = intent.getAction();
            if (intent.hasCategory(Intent.CATEGORY_LAUNCHER) && intentAction != null && intentAction.equals(Intent.ACTION_MAIN)) {
                finish();
                return;
            }
        }
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void initUI() {
    }

    @Override
    protected void initData() {
        WApplication.getMainHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (UserManager.getInstance().isFirst()) {
                    UserManager.getInstance().saveFirst(false);
                    startActivity(new Intent(SplashActivity.this, WelcomeActivity.class));
                } else {
                    if (UserManager.getInstance().needLogin()) {
                        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    } else {
                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra(KEY_PUSH_CONTENT, getIntent().getStringExtra(KEY_PUSH_CONTENT));
                        getIntent().removeExtra(KEY_PUSH_CONTENT);
                        startActivity(intent);
                    }
                }
                finish();
            }
        }, DELAY);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        WApplication.getMainHandler().removeCallbacksAndMessages(null);
    }

    @Override
    protected boolean needStatusBar() {
        return false;
    }
}
