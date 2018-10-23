package com.ljkj.qxn.wisdomsitepro.Utils.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.ljkj.qxn.wisdomsitepro.R;

/**
 * 类描述：WisdomDialog
 * 创建人：lxx
 * 创建时间：2018/5/16 10:00
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class WisdomDialog extends Dialog {

    public WisdomDialog(Context context) {
        this(context, 0);
    }

    public WisdomDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        requestWindowFeature(Window.FEATURE_NO_TITLE); // 去除对话框的标题
        getWindow().setBackgroundDrawableResource(R.drawable.bg_dialog);//设置对话框边框背景,必须在代码中设置对话框背景，不然对话框背景是黑色的
        setDimAmount(0.2f);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onCreateDialog();
    }

    protected void onCreateDialog() {

    }

    public WisdomDialog config(@LayoutRes int layoutResID, boolean canceledOnTouchOutside) {
        config(layoutResID, 0.2f, Gravity.CENTER, AnimType.CENTER, WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT, canceledOnTouchOutside);
        return this;
    }

    public WisdomDialog config(@NonNull View view, boolean canceledOnTouchOutside) {
        config(view, 0.2f, Gravity.CENTER, AnimType.CENTER, WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT, canceledOnTouchOutside);
        return this;
    }

    public WisdomDialog config(@LayoutRes int layoutResID, int gravity, AnimType animInType, boolean canceledOnTouchOutside) {
        config(layoutResID, 0.2f, gravity, animInType, WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT, canceledOnTouchOutside);
        return this;
    }

    public WisdomDialog config(@NonNull View view, int gravity, AnimType animInType, boolean canceledOnTouchOutside) {
        config(view, 0.2f, gravity, animInType, WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT, canceledOnTouchOutside);
        return this;
    }

    public WisdomDialog config(@LayoutRes int layoutResID, int gravity, AnimType animInType,
                               int width, int height, boolean canceledOnTouchOutside) {
        config(layoutResID, 0.2f, gravity, animInType, width, height, canceledOnTouchOutside);
        return this;
    }

    public WisdomDialog config(@NonNull View view, int gravity, AnimType animInType,
                               int width, int height, boolean canceledOnTouchOutside) {
        config(view, 0.2f, gravity, animInType, width, height, canceledOnTouchOutside);
        return this;
    }

    public WisdomDialog config(@LayoutRes int layoutResID, float dimAmount, int gravity, AnimType animType,
                               int width, int height, boolean canceledOnTouchOutside) {
        View contentView = LayoutInflater.from(getContext()).inflate(layoutResID, null);
        config(contentView, dimAmount, gravity, animType, width, height, canceledOnTouchOutside);
        return this;
    }

    /**
     * @param view                   内容View
     * @param dimAmount              背景阴影
     * @param gravity                gravity
     * @param animInType             动画类型
     * @param width                  宽度
     * @param height                 高度
     * @param canceledOnTouchOutside 是否可取消
     * @return WisdomDialog
     */
    public WisdomDialog config(@NonNull View view, float dimAmount, int gravity, AnimType animInType,
                               int width, int height, boolean canceledOnTouchOutside) {
        setContentView(view);
        setDimAmount(dimAmount);
        getWindow().setGravity(gravity);
        setAnimType(animInType);
        getWindow().setLayout(width, height);
        setCanceledOnTouchOutside(canceledOnTouchOutside ? true : false);
        return this;
    }

    /**
     * @param view                   内容View
     * @param params                 内容View 的布局参数
     * @param dimAmount              背景阴影
     * @param gravity                gravity
     * @param animType               动画类型
     * @param width                  宽度
     * @param height                 高度
     * @param canceledOnTouchOutside 是否可取消
     * @return WisdomDialog
     */
    public WisdomDialog config(@NonNull View view, @Nullable ViewGroup.LayoutParams params, float dimAmount, int gravity, AnimType animType,
                               int width, int height, boolean canceledOnTouchOutside) {
        setContentView(view, params);
        setDimAmount(dimAmount);
        getWindow().setGravity(gravity);
        setAnimType(animType);
        getWindow().setLayout(width, height);
        setCanceledOnTouchOutside(canceledOnTouchOutside);
        return this;
    }

    public WisdomDialog setAnimType(AnimType animInType) {
        if (animInType == AnimType.CENTER) {
            setAnimations(R.style.dialog_zoom);
        } else if (animInType == AnimType.LEFT) {
            setAnimations(R.style.dialog_anim_left);
        } else if (animInType == AnimType.TOP) {
            setAnimations(R.style.dialog_anim_top);
        } else if (animInType == AnimType.RIGHT) {
            setAnimations(R.style.dialog_anim_right);
        } else if (animInType == AnimType.BOTTOM) {
            setAnimations(R.style.dialog_anim_bottom);
        }
        return this;
    }

    /**
     * 添加进出场动画
     *
     * @param animStyleResID 动画
     */
    private void setAnimations(int animStyleResID) {
        getWindow().setWindowAnimations(animStyleResID);
    }

    public void setOffset(int x, int y) {
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.x = x;
        layoutParams.y = y;
    }

    //设置背景阴影,必须setContentView之后调用才生效
    public WisdomDialog setDimAmount(float dimAmount) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.dimAmount = dimAmount;
        return this;
    }

    //动画类型
    public enum AnimType {
        CENTER(0),
        BOTTOM(1),
        LEFT(2),
        RIGHT(3),
        TOP(4),
        NONE(5);

        AnimType(int type) {
            this.type = type;
        }

        final int type;
    }
}
