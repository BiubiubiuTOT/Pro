package com.ljkj.qxn.wisdomsitepro.ui.project;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.utils.Utils;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.Utils.SimpleOnChartGestureListener;
import com.ljkj.qxn.wisdomsitepro.data.entity.EnvironmentStatisticsInfo;
import com.ljkj.qxn.wisdomsitepro.ui.project.view.EnvironmentDataMarkerView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseFragment;
import cdsp.android.util.DateUtils;

/**
 * 类描述：最近24小时环境数据统计 图表
 * 创建人：lxx
 * 创建时间：2018/8/21zF
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class EnvironmentDataChartFragment extends BaseFragment {

    @BindView(R.id.tv_title)
    TextView titleText;

    @BindView(R.id.line_chart)
    LineChart lineChart;

    @BindView(R.id.tv_pm5)
    TextView pm5Text;

    @BindView(R.id.tv_pm10)
    TextView pm10Text;

    private EnvironmentStatisticsInfo environmentStatisticsInfo;

    public static EnvironmentDataChartFragment newInstance() {
        EnvironmentDataChartFragment fragment = new EnvironmentDataChartFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_environment_data_chart, container, false);
        return view;
    }

    @Override
    protected void initUI() {
        Paint noDataPaint = lineChart.getPaint(Chart.PAINT_INFO);
        noDataPaint.setTextSize(Utils.convertDpToPixel(16f));
        lineChart.setNoDataTextColor(Color.parseColor("#74789C"));
        lineChart.setNoDataText("暂无数据");

        lineChart.setPinchZoom(false);
        lineChart.setScaleEnabled(false);
        lineChart.setDoubleTapToZoomEnabled(false);

        Legend legend = lineChart.getLegend();
        legend.setEnabled(false);

        lineChart.getDescription().setEnabled(false);

        final YAxis leftAxis = lineChart.getAxisLeft();
        leftAxis.setDrawGridLines(false);
        leftAxis.setTextColor(Color.parseColor("#74789C"));
        leftAxis.setAxisMinimum(0);
        leftAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                int v = (int) value;
                return v == leftAxis.getAxisMaximum() ? "ug/m³" : v + "";
            }
        });

        EnvironmentDataMarkerView mv = new EnvironmentDataMarkerView(getActivity());
        mv.setChartView(lineChart);
        lineChart.setMarker(mv);

        YAxis rightAxis = lineChart.getAxisRight();
        rightAxis.setEnabled(false);

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setTextColor(Color.parseColor("#74789C"));
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(true);
        xAxis.setAxisMaximum(24);
        xAxis.setAxisMinimum(0);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(8);
    }

    @Override
    protected void initData() {
        setListener();
    }

    private void setListener() {
        lineChart.setOnChartGestureListener(new SimpleOnChartGestureListener() {
            @Override
            public void onChartSingleTapped(MotionEvent me) {
                super.onChartSingleTapped(me);
            }
        });
    }

    @SuppressLint("SetTextI18n")
    public void showData(final EnvironmentStatisticsInfo info, final boolean isPM10) {
        this.environmentStatisticsInfo = info;
        titleText.setText("最近24小时环境数据统计（" + DateUtils.formatDate(new Date(), DateUtils.PATTERN_DATE) + "）");
        List<Entry> entries = new ArrayList<>();
        int maxValue = 7;
        for (int i = 0; i < info.latest24Data.size(); i++) {
            EnvironmentStatisticsInfo.PmInfo pmInfo = info.latest24Data.get(i);
            try {
                String[] strs = pmInfo.timeHour.split(" ");
                titleText.setText("最近24小时环境数据统计（20" + strs[0] + "）");

                entries.add(new Entry(i, isPM10 ? pmInfo.pm10 : pmInfo.pm25,pmInfo));
                if (isPM10) {
                    maxValue = maxValue > pmInfo.pm10 ? maxValue : (int) pmInfo.pm10;
                } else {
                    maxValue = maxValue > pmInfo.pm25 ? maxValue : (int) pmInfo.pm25;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setLabelCount(info.latest24Data.size(), false);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                int v = (int) value;
                if (v >= axis.getAxisMaximum()) {
                    return "时刻";
                }
                return getXValue(info.latest24Data.get(v).timeHour);
            }
        });

        YAxis leftAxis = lineChart.getAxisLeft();
        leftAxis.setAxisMaximum(maxValue);

        LineDataSet lineDataSet = new LineDataSet(entries, "");
        lineDataSet.setColor(Color.parseColor("#FFE826"));
        lineDataSet.setLineWidth(2);
        lineDataSet.setValueTextSize(10);
        lineDataSet.setValueTextColor(Color.parseColor("#74789C"));
        lineDataSet.setDrawValues(false);
        lineDataSet.setDrawCircles(true);
        lineDataSet.setMode(LineDataSet.Mode.LINEAR);
        lineDataSet.setHighlightEnabled(true);
        lineDataSet.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
        lineDataSet.setDrawFilled(true);
        lineDataSet.setFillDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.fade_enviroment_data_chart));

        lineDataSet.setHighLightColor(Color.parseColor("#8194A7"));
        lineDataSet.setDrawHorizontalHighlightIndicator(true);
        lineDataSet.setDrawVerticalHighlightIndicator(true);

        LineData lineData = new LineData();
        lineData.addDataSet(lineDataSet);

        lineChart.setData(lineData);
        lineChart.invalidate();
        lineChart.animateXY(3000, 3000);
    }

    private String getXValue(String timeHour) {
        String hour = "";
        try {
            hour = timeHour.split(" ")[1];
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hour;
    }

    @OnClick({R.id.tv_pm5, R.id.tv_pm10})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_pm5:
                pm10Text.setBackgroundResource(R.drawable.shape_pm_normal);
                pm5Text.setBackgroundResource(R.drawable.shape_pm_selected);
                if (environmentStatisticsInfo != null) {
                    showData(environmentStatisticsInfo, false);
                }
                break;
            case R.id.tv_pm10:
                pm5Text.setBackgroundResource(R.drawable.shape_pm_normal);
                pm10Text.setBackgroundResource(R.drawable.shape_pm_selected);
                if (environmentStatisticsInfo != null) {
                    showData(environmentStatisticsInfo, true);
                }
                break;
            default:
                break;
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    void test() {
        List<Entry> entries = new ArrayList<>();
        entries.add(new Entry(0, 40));
        entries.add(new Entry(1, 10));
        entries.add(new Entry(2, 11));
        entries.add(new Entry(3, 37));
        entries.add(new Entry(4, 30));
        entries.add(new Entry(6, 23));
        entries.add(new Entry(7, 25));
        entries.add(new Entry(10, 22));
        entries.add(new Entry(12, 45));
        entries.add(new Entry(14, 19));
        entries.add(new Entry(17, 45));
        entries.add(new Entry(19, 17));
        entries.add(new Entry(20, 15));
        entries.add(new Entry(21, 40));
        entries.add(new Entry(22, 41));

        LineDataSet lineDataSet = new LineDataSet(entries, "");
        lineDataSet.setColor(Color.parseColor("#FFE826"));
        lineDataSet.setLineWidth(2);
        lineDataSet.setValueTextSize(10);
        lineDataSet.setValueTextColor(Color.parseColor("#74789C"));
        lineDataSet.setDrawValues(false);
        lineDataSet.setDrawCircles(true);
        lineDataSet.setMode(LineDataSet.Mode.LINEAR);
        lineDataSet.setHighlightEnabled(true);
        lineDataSet.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
        lineDataSet.setDrawFilled(true);
        lineDataSet.setFillDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.fade_enviroment_data_chart));

        lineDataSet.setHighLightColor(Color.parseColor("#8194A7"));
        lineDataSet.setDrawHorizontalHighlightIndicator(true);
        lineDataSet.setDrawVerticalHighlightIndicator(true);


        LineData lineData = new LineData();
        lineData.addDataSet(lineDataSet);

        lineChart.setData(lineData);
        lineChart.invalidate();
        lineChart.animateXY(3000, 3000);
    }
}
