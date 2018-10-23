package com.ljkj.qxn.wisdomsitepro.ui.safe.check;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import cdsp.android.util.DisplayUtils;

public class RectifyProgressItemView extends FrameLayout {

    @BindView(R.id.tv_avatar)
    TextView avatarText;

    @BindView(R.id.tv_name)
    TextView nameText;

    @BindView(R.id.tv_date)
    TextView dateText;

    @BindView(R.id.tv_label)
    TextView labelText;

    @BindView(R.id.iv_left)
    ImageView leftImage;

    public RectifyProgressItemView(@NonNull Context context) {
        this(context, null);
    }

    public RectifyProgressItemView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        View view = inflate(context, R.layout.view_rectify_progress_item, this);
        ButterKnife.bind(this, view);
    }

    public void setData(String name, String date, @DrawableRes int labelRes, String label, boolean isFirst, boolean isLast) {
        nameText.setText(name);
        if (name != null && name.length() > 0) {
            String str = String.valueOf(name.charAt(name.length() - 1));
            avatarText.setText(str);
        }
        dateText.setText(date);
        labelText.setBackgroundResource(labelRes);
        labelText.setText(label);

        MarginLayoutParams layoutParams = (MarginLayoutParams) leftImage.getLayoutParams();
        if (isFirst) {
            layoutParams.topMargin = DisplayUtils.dip2px(getContext(), 10);
        }
        if (isLast) {
            layoutParams.bottomMargin = DisplayUtils.dip2px(getContext(), 10);
        }
        leftImage.setLayoutParams(layoutParams);
    }

}
