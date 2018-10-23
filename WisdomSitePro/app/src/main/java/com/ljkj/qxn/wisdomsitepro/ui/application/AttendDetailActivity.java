package com.ljkj.qxn.wisdomsitepro.ui.application;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.haibin.calendarview.CalendarView;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.Utils.DateUtil;
import com.ljkj.qxn.wisdomsitepro.contract.application.AttendanceContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.AttendanceHistoryInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.AttendanceInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.PageInfo;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.model.ApplicationModel;
import com.ljkj.qxn.wisdomsitepro.presenter.application.AttendanceHistoryPresenter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.glide.GlideHelper;
import cdsp.android.ui.base.BaseActivity;
import cdsp.android.util.GsonUtils;

/**
 * 考勤详情页
 * 创建人：lxx
 * 创建时间：2018/5/2 09:43
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
@SuppressLint("SetTextI18n")
public class AttendDetailActivity extends BaseActivity implements AttendanceContract.View {
    private static final String EXTRA_KEY_INFO = "extra_key_info";

    @BindView(R.id.tv_title)
    TextView titleText;

    @BindView(R.id.calendarView)
    CalendarView calendarView;

    @BindView(R.id.iv_date_left)
    ImageView dateLeftImage;

    @BindView(R.id.iv_date_right)
    ImageView dateRightImage;

    @BindView(R.id.tv_name)
    TextView nameText;

    @BindView(R.id.tv_avatar)
    TextView avatarText;

    @BindView(R.id.tv_job)
    TextView jobText;

    @BindView(R.id.tv_date)
    TextView dateText;

    @BindView(R.id.tv_date_to_work)
    TextView dateToWorkText; //上班打卡时间

    @BindView(R.id.tv_address_to_work)
    TextView addressToWorkText; //上班打卡地点

    @BindView(R.id.iv_go_work_head)
    ImageView goWorkHeadImage; //上班打卡照片

    @BindView(R.id.iv_off_work_head)
    ImageView offWorkHeadImage; //下班打卡照片

    @BindView(R.id.tv_date_off_work)
    TextView dateOffWorkText; //下班打卡时间

    @BindView(R.id.tv_address_off_work)
    TextView addressOffWorkText; //下班打卡地点

    @BindView(R.id.rl_matters)
    RelativeLayout mattersLayout;

    @BindView(R.id.tv_no_data)
    TextView noDataText;


    private AttendanceHistoryPresenter presenter;
    private String laborPersonId;

    public static void startActivity(Context context, AttendanceInfo attendanceInfo) {
        Intent intent = new Intent(context, AttendDetailActivity.class);
        intent.putExtra(EXTRA_KEY_INFO, attendanceInfo);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attend_detail);
    }

    @Override
    protected void initUI() {
        titleText.setText("考勤历史");
        AttendanceInfo attendanceInfo = getIntent().getParcelableExtra(EXTRA_KEY_INFO);
        laborPersonId = attendanceInfo.getId();
        String name = attendanceInfo.getName();
        nameText.setText(name);
        if (name != null && name.length() > 0) {
            String str = String.valueOf(name.charAt(name.length() - 1));
            avatarText.setText(str);
        }
//        jobText.setText("班组：" + attendanceInfo.getWorkType());
        dateText.setText(calendarView.getCurYear() + "-" + calendarView.getCurMonth());
    }

    @Override
    protected void initData() {
        presenter = new AttendanceHistoryPresenter(this, ApplicationModel.newInstance());
        addPresenter(presenter);
        Calendar calendar = Calendar.getInstance();
        calendarView.setRange(calendar.get(Calendar.YEAR) - 1, calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1);

        initListener();
        requestData(calendarView.getCurYear(), calendarView.getCurMonth());
    }

    private void initListener() {
        calendarView.setOnMonthChangeListener(new CalendarView.OnMonthChangeListener() {
            @Override
            public void onMonthChange(int year, int month) {
                dateText.setText(year + "-" + month);
                requestData(year, month);
            }
        });

        calendarView.setOnDateSelectedListener(new CalendarView.OnDateSelectedListener() {
            @Override
            public void onDateSelected(com.haibin.calendarview.Calendar calendar, boolean isClick) {
                if (calendar.hasScheme()) {
                    mattersLayout.setVisibility(View.VISIBLE);
                    noDataText.setVisibility(View.GONE);
                    AttendanceHistoryInfo info = GsonUtils.fromJson(calendar.getScheme(), AttendanceHistoryInfo.class);
                    if (!TextUtils.isEmpty(info.getSbTime())) {
                        dateToWorkText.setText(DateUtil.formatToHHmm(info.getSbTime()));
                        addressToWorkText.setText(info.getAddress());
                    } else {
                        dateToWorkText.setText("无");
                        addressToWorkText.setText("无");
                    }

                    if (!TextUtils.isEmpty(info.getXbTime())) {
                        dateOffWorkText.setText(DateUtil.formatToHHmm(info.getXbTime()));
                        addressOffWorkText.setText(info.getAddress());
                    } else {
                        dateOffWorkText.setText("无");
                        addressOffWorkText.setText("无");
                    }
                    //TODO
                    GlideHelper.loadCircleImage(AttendDetailActivity.this, goWorkHeadImage, info.getGoWorkImage(), R.mipmap.ic_head_default);
                    GlideHelper.loadCircleImage(AttendDetailActivity.this, offWorkHeadImage, info.getOffWorkImage(), R.mipmap.ic_head_default);

                } else {
                    mattersLayout.setVisibility(View.GONE);
                    noDataText.setVisibility(View.VISIBLE);
                }

            }
        });

    }

    private void requestData(int year, int month) {
        String m = month > 9 ? String.valueOf(month) : "0" + month;
        presenter.listPageAttendanceHitoryInfo(laborPersonId, year + "-" + m, 1, UserManager.getInstance().getProjectId(), 31);
    }

    @OnClick({R.id.tv_back, R.id.iv_date_left, R.id.iv_date_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.iv_date_left:
                calendarView.scrollToPre(true);
                break;
            case R.id.iv_date_right:
                calendarView.scrollToNext(true);
                break;
            default:
                break;
        }
    }

    private void test() {
        List<com.haibin.calendarview.Calendar> list = new ArrayList<>();
        list.add(getSchemeCalendar(2018, 5, 1, Color.BLACK, "黑"));
        list.add(getSchemeCalendar(2018, 5, 3, Color.RED, "红"));
        list.add(getSchemeCalendar(2018, 5, 5, Color.YELLOW, "黄"));

        calendarView.setSchemeDate(list);
    }

    private com.haibin.calendarview.Calendar getSchemeCalendar(int year, int month, int day, int color, String text) {
        com.haibin.calendarview.Calendar calendar = new com.haibin.calendarview.Calendar();
        calendar.setYear(year);
        calendar.setMonth(month);
        calendar.setDay(day);
        calendar.setSchemeColor(color);//如果单独标记颜色、则会使用这个颜色
        calendar.setScheme(text);
        return calendar;
    }

    @Override
    public void showPageAttendanceInfo(PageInfo<AttendanceInfo> data) {
    }

    @Override
    public void showAttendanceHitoryInfo(PageInfo<AttendanceHistoryInfo> data) {
        List<AttendanceHistoryInfo> list = data.getList();
        if (!list.isEmpty()) {
            setSchemeDate(list);
        }
    }

    private void setSchemeDate(List<AttendanceHistoryInfo> list) {
        List<com.haibin.calendarview.Calendar> schemes = new ArrayList<>();
        for (AttendanceHistoryInfo info : list) {
            if (!TextUtils.isEmpty(info.getDate())) {
                Calendar calendar = Calendar.getInstance();
                long time = Long.parseLong(info.getDate());
                calendar.setTimeInMillis(time);
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH) + 1;
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int color;
                if (TextUtils.isEmpty(info.getSbTime()) || TextUtils.isEmpty(info.getXbTime())) {
                    color = ContextCompat.getColor(this, R.color.color_orange);
                } else {
                    color = ContextCompat.getColor(this, R.color.color_main);
                }

                com.haibin.calendarview.Calendar calendar1 = getSchemeCalendar(year, month, day, color, GsonUtils.toJson(info));
                schemes.add(calendar1);
            }
        }
        if (schemes.size() > 0) {
            calendarView.setSchemeDate(schemes);
        }
    }


}
