package cdsp.android.util;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.widget.Toast;

import cdsp.android.base.BaseApplication;

/**
 * 类描述：ToastUtils
 * 创建人：lxx
 * 创建时间：2018/5/31 16:45
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class ToastUtils {
    private static Toast mToast;

    private ToastUtils() {
        throw new UnsupportedOperationException("不能被实例化");
    }

    /**
     * 短时间显示Toast
     *
     * @param message 消息内容
     */
    public static void showShort(@NonNull CharSequence message) {
        show(message, Toast.LENGTH_SHORT);
    }

    /**
     * 短时间显示Toast
     *
     * @param resId 消息内容资源id
     */
    public static void showShort(@StringRes int resId) {
        show(resId, Toast.LENGTH_SHORT);
    }

    /**
     * 长时间显示Toast
     *
     * @param message 消息内容
     */
    public static void showLong(@NonNull CharSequence message) {
        show(message, Toast.LENGTH_LONG);
    }

    /**
     * 长时间显示Toast
     *
     * @param resId 消息内容资源id
     */
    public static void showLong(@StringRes int resId) {
        show(resId, Toast.LENGTH_LONG);
    }

    @SuppressLint("ShowToast")
    private static void show(@NonNull CharSequence message, int duration) {
        if (mToast == null) {
            mToast = Toast.makeText(BaseApplication.getApplication(), message, duration);
        }
        mToast.setText(message);
        mToast.show();
    }

    @SuppressLint("ShowToast")
    private static void show(@StringRes int resId, int duration) {
        if (mToast == null) {
            mToast = Toast.makeText(BaseApplication.getApplication(), resId, duration);
        }
        mToast.setText(resId);
        mToast.show();
    }

}
