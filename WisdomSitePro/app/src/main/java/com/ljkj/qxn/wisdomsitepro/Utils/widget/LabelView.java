package com.ljkj.qxn.wisdomsitepro.Utils.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.ColorInt;
import android.support.annotation.IntDef;
import android.util.AttributeSet;
import android.view.View;

import com.ljkj.qxn.wisdomsitepro.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 类描述：标签View
 * 创建人：lxx
 * 创建时间：2018/8/3
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class LabelView extends View {
    private static final int DEFAULT_DEGREES = 45;

    private static final int LEFT_TOP = 1; //左上角
    private static final int RIGHT_TOP = 2; //右上角
    private static final int LEFT_BOTTOM = 3; //左下角
    private static final int RIGHT_BOTTOM = 4; //右下角

    private String mTextContent;
    private int mTextColor;
    private float mTextSize;
    private boolean mTextBold;
    private boolean mFillTriangle;
    private int mBackgroundColor;
    private float mPadding;

    @LabelGravity
    private int mLabelGravity;

    private Paint mTextPaint;
    private Paint mBackgroundPaint;
    private Path mPath = new Path();

    public LabelView(Context context) {
        this(context, null);
    }

    public LabelView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.LabelView);
        mTextContent = ta.getString(R.styleable.LabelView_label_text);
        mTextColor = ta.getColor(R.styleable.LabelView_label_text_color, Color.WHITE);
        mTextSize = ta.getDimension(R.styleable.LabelView_label_text_size, sp2px(12));
        mTextBold = ta.getBoolean(R.styleable.LabelView_label_text_bold, false);
        mFillTriangle = ta.getBoolean(R.styleable.LabelView_label_fill_triangle, false);
        mBackgroundColor = ta.getColor(R.styleable.LabelView_label_background_color, Color.RED);
        mPadding = ta.getDimension(R.styleable.LabelView_label_padding, dp2px(3.5f));
        mLabelGravity = ta.getInt(R.styleable.LabelView_label_gravity, RIGHT_TOP);
        ta.recycle();

        initPaint();
    }

    private void initPaint() {
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mTextPaint.setColor(mTextColor);
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setFakeBoldText(mTextBold);

        mBackgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBackgroundPaint.setColor(mBackgroundColor);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int measuredWidth = measureWidth(widthMeasureSpec);
        setMeasuredDimension(measuredWidth, measuredWidth);
    }

    private int measureWidth(int widthMeasureSpec) {
        int result;
        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        int specSize = MeasureSpec.getSize(widthMeasureSpec);
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            int padding = getPaddingLeft() + getPaddingRight();

            float textWidth = mTextPaint.measureText(mTextContent + "");
            result = (int) ((padding + (int) textWidth) * Math.sqrt(2));
            //如果父视图的测量要求为AT_MOST,即限定了一个最大值,则再从系统建议值和自己计算值中取一个较小值
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            } else {
                result = Math.max(dp2px(48), result);
            }

        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int size = getHeight();
        float textHeight = mTextPaint.descent() - mTextPaint.ascent();
        mBackgroundPaint.setColor(mBackgroundColor);
        mTextPaint.setColor(mTextColor);
        if (mFillTriangle) {
            if (mLabelGravity == LEFT_TOP) {
                mPath.reset();
                mPath.moveTo(0, 0);
                mPath.lineTo(0, size);
                mPath.lineTo(size, 0);
                mPath.close();
                canvas.drawPath(mPath, mBackgroundPaint);

                drawTextWhenFill(size, -DEFAULT_DEGREES, canvas, true);
            } else if (mLabelGravity == RIGHT_TOP) {
                mPath.reset();
                mPath.moveTo(size, 0);
                mPath.lineTo(0, 0);
                mPath.lineTo(size, size);
                mPath.close();
                canvas.drawPath(mPath, mBackgroundPaint);

                drawTextWhenFill(size, DEFAULT_DEGREES, canvas, true);
            } else if (mLabelGravity == LEFT_BOTTOM) {
                mPath.reset();
                mPath.moveTo(0, size);
                mPath.lineTo(0, 0);
                mPath.lineTo(size, size);
                mPath.close();
                canvas.drawPath(mPath, mBackgroundPaint);

                drawTextWhenFill(size, DEFAULT_DEGREES, canvas, false);
            } else if (mLabelGravity == RIGHT_BOTTOM) {
                mPath.reset();
                mPath.moveTo(size, size);
                mPath.lineTo(0, size);
                mPath.lineTo(size, 0);
                mPath.close();
                canvas.drawPath(mPath, mBackgroundPaint);

                drawTextWhenFill(size, -DEFAULT_DEGREES, canvas, false);
            }
        } else {
            double delta = (textHeight + mPadding * 2) * Math.sqrt(2);
            if (mLabelGravity == LEFT_TOP) {
                mPath.reset();
                mPath.moveTo(0, (float) (size - delta));
                mPath.lineTo(0, size);
                mPath.lineTo(size, 0);
                mPath.lineTo((float) (size - delta), 0);
                mPath.close();
                canvas.drawPath(mPath, mBackgroundPaint);

                drawText(size, -DEFAULT_DEGREES, canvas, textHeight, true);
            } else if (mLabelGravity == RIGHT_TOP) {
                mPath.reset();
                mPath.moveTo(0, 0);
                mPath.lineTo((float) delta, 0);
                mPath.lineTo(size, (float) (size - delta));
                mPath.lineTo(size, size);
                mPath.close();
                canvas.drawPath(mPath, mBackgroundPaint);

                drawText(size, DEFAULT_DEGREES, canvas, textHeight, true);
            } else if (mLabelGravity == LEFT_BOTTOM) {
                mPath.reset();
                mPath.moveTo(0, 0);
                mPath.lineTo(0, (float) delta);
                mPath.lineTo((float) (size - delta), size);
                mPath.lineTo(size, size);
                mPath.close();
                canvas.drawPath(mPath, mBackgroundPaint);

                drawText(size, DEFAULT_DEGREES, canvas, textHeight, false);
            } else if (mLabelGravity == RIGHT_BOTTOM) {
                mPath.reset();
                mPath.moveTo(0, size);
                mPath.lineTo((float) delta, size);
                mPath.lineTo(size, (float) delta);
                mPath.lineTo(size, 0);
                mPath.close();
                canvas.drawPath(mPath, mBackgroundPaint);

                drawText(size, -DEFAULT_DEGREES, canvas, textHeight, false);
            }
        }
    }

    private void drawText(int size, float degrees, Canvas canvas, float textHeight, boolean isTop) {
        canvas.save();
        canvas.rotate(degrees, size / 2f, size / 2f);
        float delta = isTop ? -(textHeight + mPadding * 2) / 2 : (textHeight + mPadding * 2) / 2;
        float textBaseY = size / 2 - (mTextPaint.descent() + mTextPaint.ascent()) / 2 + delta;
        canvas.drawText(mTextContent, getPaddingLeft() + (size - getPaddingLeft() - getPaddingRight()) / 2, textBaseY, mTextPaint);
        canvas.restore();
    }

    private void drawTextWhenFill(int size, float degrees, Canvas canvas, boolean isTop) {
        canvas.save();
        canvas.rotate(degrees, size / 2f, size / 2f);
        float delta = isTop ? -size / 4 : size / 4;
        float textBaseY = size / 2 - (mTextPaint.descent() + mTextPaint.ascent()) / 2 + delta;
        canvas.drawText(mTextContent, getPaddingLeft() + (size - getPaddingLeft() - getPaddingRight()) / 2, textBaseY, mTextPaint);
        canvas.restore();
    }

    /**
     * 设置标签文字颜色
     *
     * @param textColor textColor
     */
    public void setTextColor(@ColorInt int textColor) {
        mTextColor = textColor;
        invalidate();
    }

    /**
     * 设置标签文字
     *
     * @param text 文字
     */
    public void setText(String text) {
        mTextContent = text;
        invalidate();
    }

    /**
     * 设置文字大小
     *
     * @param textSize 文字大小
     */
    public void setTextSize(float textSize) {
        mTextSize = sp2px(textSize);
        invalidate();
    }

    /**
     * 设置数据
     *
     * @param text      文字
     * @param textColor 文字颜色
     * @param bgColor   标签背景颜色
     */
    public void setData(String text, int textColor, int bgColor) {
        this.mTextContent = text;
        this.mTextColor = textColor;
        this.mBackgroundColor = bgColor;
        invalidate();
    }

    /**
     * 标签背景颜色
     *
     * @param backgroundColor 颜色
     */
    public void setBgColor(@ColorInt int backgroundColor) {
        mBackgroundColor = backgroundColor;
        invalidate();
    }

    /**
     * 设置标签位置
     *
     * @param gravity {@link LabelGravity}
     */
    public void setGravity(@LabelGravity int gravity) {
        mLabelGravity = gravity;
    }

    public String getText() {
        return mTextContent;
    }

    public int getTextColor() {
        return mTextColor;
    }

    public float getTextSize() {
        return mTextSize;
    }

    public boolean isFillTriangle() {
        return mFillTriangle;
    }

    public int getBgColor() {
        return mBackgroundColor;
    }

    private int dp2px(float dp) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    private int sp2px(float sp) {
        final float scale = getResources().getDisplayMetrics().scaledDensity;
        return (int) (sp * scale + 0.5f);
    }

    @IntDef({LEFT_BOTTOM, LEFT_TOP, RIGHT_BOTTOM, RIGHT_TOP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface LabelGravity {
    }

}
