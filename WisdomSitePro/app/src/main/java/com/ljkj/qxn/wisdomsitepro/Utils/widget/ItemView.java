package com.ljkj.qxn.wisdomsitepro.Utils.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * ItemView
 * 创建人：lxx
 * 创建时间：2018/3/26 14:09
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class ItemView extends FrameLayout {

    @BindView(R.id.tv_title)
    TextView titleText;

    @BindView(R.id.tv_content)
    TextView contentText;

    @BindView(R.id.line)
    View lineView;

    @BindView(R.id.container)
    LinearLayout container;

    private int leftCircleRadius = 10;
    private Paint paint;
    private int height;

    public ItemView(@NonNull Context context) {
        this(context, null);
    }

    public ItemView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ItemView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = inflate(context, R.layout.item_view_layout, this);
        ButterKnife.bind(this, view);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ItemView);
        String title = ta.getString(R.styleable.ItemView_item_title);
        int titleColor = ta.getColor(R.styleable.ItemView_item_title_color, getResources().getColor(R.color.color_grey_333333));
        int titleSize = ta.getDimensionPixelSize(R.styleable.ItemView_item_title_size, spToPx(15));
        String content = ta.getString(R.styleable.ItemView_item_content);
        int contentColor = ta.getColor(R.styleable.ItemView_item_content_color, getResources().getColor(R.color.color_grey_333333));
        int contentSize = ta.getDimensionPixelSize(R.styleable.ItemView_item_content_size, spToPx(14));
        boolean line = ta.getBoolean(R.styleable.ItemView_item_bottom_line, true);
        boolean arrow = ta.getBoolean(R.styleable.ItemView_item_right_arrow, true);
        Drawable drawable = ta.getDrawable(R.styleable.ItemView_item_icon);
        boolean hasBg = ta.getBoolean(R.styleable.ItemView_item_has_background, true);
        if (!hasBg) {
            container.setBackground(null);
        }

        int circleColor = ta.getColor(R.styleable.ItemView_item_left_circle_color, 0);
        if (circleColor != 0) {
            paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setColor(circleColor);
            leftCircleRadius = ta.getDimensionPixelSize(R.styleable.ItemView_item_left_circle_radius, dpToPx(4));

            ViewGroup.MarginLayoutParams layoutParams = (MarginLayoutParams) titleText.getLayoutParams();
            layoutParams.leftMargin = 2 * leftCircleRadius + 2 * dpToPx(10);
            titleText.setLayoutParams(layoutParams);

        }
        ta.recycle();

        titleText.setText(title);
        titleText.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
        titleText.setTextColor(titleColor);
        titleText.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleSize);
        contentText.setText(content);
        contentText.setTextColor(contentColor);
        contentText.setTextSize(TypedValue.COMPLEX_UNIT_PX, contentSize);
        lineView.setVisibility(line ? View.VISIBLE : View.GONE);
        if (!arrow) {
            contentText.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        }
    }

    private int spToPx(int sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, getContext().getResources().getDisplayMetrics());
    }

    private int dpToPx(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getContext().getResources().getDisplayMetrics());
    }


    public TextView getContentTextView() {
        return contentText;
    }

    public void setTitle(@NonNull String title) {
        titleText.setText(title);
    }

    public void setContent(@NonNull String content) {
        contentText.setText(content);
    }

    public String getTitle() {
        return titleText.getText().toString();
    }

    public String getContent() {
        return contentText.getText().toString();
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        height = h;
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (paint != null) {
            canvas.drawCircle(dpToPx(10) + leftCircleRadius, height / 2, leftCircleRadius, paint);
        }

    }

}
