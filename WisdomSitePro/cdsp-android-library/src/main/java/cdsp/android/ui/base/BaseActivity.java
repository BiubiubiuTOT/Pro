package cdsp.android.ui.base;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.util.LinkedList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import cdsp.android.BuildConfig;
import cdsp.android.R;
import cdsp.android.base.AppManager;
import cdsp.android.presenter.BasePresenter;
import cdsp.android.ui.widget.MLoadingDialog;
import cdsp.android.util.ToastUtils;
import systembartint.SystemBarTintManager;

/**
 * 类描述：Activity 基类
 * 创建人：mhl
 * 创建时间：2016/10/10 12:03
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    protected String TAG;

    private Unbinder unbinder;
    private long mLastClickTime;
    private boolean isDestroy;

    /**
     * 业务逻辑类
     */
    protected List<BasePresenter> presenterList = new LinkedList<BasePresenter>();

    /** 是否处理权限 */
    public static boolean isHandleAuthority = true;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        if (BuildConfig.DEBUG) {
            Log.e(getClass().getSimpleName(), "current page is=>" + getClass().getSimpleName());
        }
        unbinder = ButterKnife.bind(this);
        if (needStatusBar()) {
            setTranslucentStatus(R.color.color_white);
        }
        initTAG(this);
        initUI();
        if (isHandleAuthority) {
            handleAuthority();
        }

        initData();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getAppManager().addActivity(this);
        for (BasePresenter basePresenter : presenterList) {
            basePresenter.onCreate();
        }
        isDestroy = false;
    }


    protected void setTranslucentStatus(int resColor) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(resColor);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    protected void setTranslucentStatusForProject(int resColor) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(resColor);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
    }

    @TargetApi(19)
    protected void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }


    @Override
    protected void onStop() {
        super.onStop();
        for (BasePresenter basePresenter : presenterList) {
            basePresenter.stop();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        for (BasePresenter basePresenter : presenterList) {
            basePresenter.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        for (BasePresenter basePresenter : presenterList) {
            basePresenter.resume();
        }
    }

    public boolean isDestroy() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return isDestroyed();
        }
        return isDestroy;
    }

    @Override
    protected void onDestroy() {
        hideProgress();
        super.onDestroy();
        isDestroy = true;
        AppManager.getAppManager().destroyActivity(this);
        for (BasePresenter basePresenter : presenterList) {
            basePresenter.destroy();
        }
        presenterList.clear();
        if (null != unbinder) {
            unbinder.unbind();
        }
    }

    /**
     * 日志TAG初始化
     *
     * @param context
     */
    protected void initTAG(Context context) {
        TAG = context.getClass().getSimpleName();
    }


    /**
     * 业务逻辑类加入生命周期管理
     *
     * @param BasePresenter
     */
    public void addPresenter(BasePresenter BasePresenter) {
        presenterList.add(BasePresenter);
    }

    /**
     * 业务逻辑类脱离生命周期管理
     *
     * @param BasePresenter
     */
    public void removePresenter(BasePresenter BasePresenter) {
        presenterList.remove(BasePresenter);
    }

    @Override
    public void showProgress(String message) {
        MLoadingDialog.show(this, message);
    }

    @Override
    public void hideProgress() {
        MLoadingDialog.dismiss();
    }

    @Override
    public void showError(String message) {
        ToastUtils.showShort(message);
    }

    public boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        if (mLastClickTime > time) {
            mLastClickTime = time;
            return false;
        }
        if (time - mLastClickTime < 500) {
            return true;
        }
        mLastClickTime = time;
        return false;
    }

    /**
     * 界面初始化
     */
    protected abstract void initUI();

    /**
     * 数据初始化
     */
    protected abstract void initData();

    protected boolean needStatusBar() {
        return true;
    }

    /** 处理权限相关逻辑 */
    protected void handleAuthority() {
    }
}
