package com.ljkj.qxn.wisdomsitepro.ui.project.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.data.entity.WeatherInfo;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import cdsp.android.util.DateUtils;

/**
 * 类描述：项目天气
 * 创建人：lxx
 * 创建时间：2018/6/20
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class ProjectWeatherView extends FrameLayout {

    @BindView(R.id.iv_project)
    ImageView projectImage; //项目照片

    @BindView(R.id.tv_project_name)
    TextView projectNameText; //项目名称

    @BindView(R.id.tv_barometer)
    TextView barometerText; //晴雨表

    @BindView(R.id.tv_work_time)
    TextView workTimeText; //工期已用

    @BindView(R.id.tv_date)
    TextView dateText; //日期

    @BindView(R.id.tv_weather)
    TextView weatherText; //天气

    @BindView(R.id.tv_work_days)
    TextView workDaysText; //施工天数

    @BindView(R.id.tv_days)
    TextView daysText; //天数

    @BindView(R.id.tv_week)
    TextView weekText; //星期

    @BindView(R.id.tv_wind)
    TextView windText; //风力

    @BindView(R.id.tv_temperature)
    TextView temperatureText; //温度

    @BindView(R.id.tv_greet)
    TextView greetText; //问候

    public ProjectWeatherView(@NonNull Context context) {
        this(context, null);
    }

    public ProjectWeatherView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProjectWeatherView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = inflate(context, R.layout.view_project_weather, this);
        ButterKnife.bind(this, view);

        updateHello();
        projectNameText.setText(UserManager.getInstance().getProjectName());
    }

    /**
     * 设置天气信息
     *
     * @param now now
     */
    public void setData(WeatherInfo.Now now) {
        updateHello();
        if (now == null) {
            return;
        }
        projectNameText.setText(UserManager.getInstance().getProjectName());
        weatherText.setText("天气：" + now.cond_txt);
        windText.setText("风力：" + now.wind_dir + " " + now.wind_sc + "级");
        temperatureText.setText("温度：" + now.tmp + "°C");
        dateText.setText(DateUtils.formatDate(new Date(), DateUtils.PATTERN_CHINESE_DATE));

        String[] weekDaysName = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        int intWeek = Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 1;
        weekText.setText(weekDaysName[intWeek]);
        workDaysText.setText("施工天数");
    }

    /**
     * @param workLimit 工期已用
     * @param workDays  施工天数
     */
    public void setProjectInfo(String workLimit, String workDays) {
        setWorkTime("工期已用: " + workLimit);
        daysText.setText(workDays + "天");
    }

    private void updateHello() {
        if (Calendar.getInstance().get(Calendar.AM_PM) == 0) {
            greetText.setText("嗨，上午好！" + UserManager.getInstance().getUserName());
        } else {
            greetText.setText("嗨，下午好！" + UserManager.getInstance().getUserName());
        }
    }

    private void setWorkTime(String content) {
        SpannableString spannableString = new SpannableString(content);
        spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(getContext(), R.color.color_orange)), 5, content.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        int size = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 18, getResources().getDisplayMetrics());
        spannableString.setSpan(new AbsoluteSizeSpan(size), 5, content.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        workTimeText.setText(spannableString);
    }

    public void setOnBarometerListener(OnClickListener listener) {
        barometerText.setOnClickListener(listener);
    }

}
