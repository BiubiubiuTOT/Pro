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

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.utils.Utils;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.Utils.DateUtil;
import com.ljkj.qxn.wisdomsitepro.Utils.SimpleOnChartGestureListener;
import com.ljkj.qxn.wisdomsitepro.data.entity.RealTimeTeamStatisticsInfo;
import com.ljkj.qxn.wisdomsitepro.ui.project.view.PresenceGroupMarkerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cdsp.android.ui.base.BaseFragment;

/**
 * 类描述：项目实时在场班组统计图表
 * 创建人：lxx
 * 创建时间：2018/8/22
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class PresenceGroupChartFragment extends BaseFragment {

    @BindView(R.id.tv_title)
    TextView titleText;

    @BindView(R.id.bar_chart)
    BarChart barChart;

    public static PresenceGroupChartFragment newInstance() {
        PresenceGroupChartFragment fragment = new PresenceGroupChartFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_presence_group_chart, container, false);
        return view;
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void initUI() {
        titleText.setText("项目实时在场班组统计（" + DateUtil.format(System.currentTimeMillis() + "") + ")");
        Paint noDataPaint = barChart.getPaint(Chart.PAINT_INFO);
        noDataPaint.setTextSize(Utils.convertDpToPixel(16f));
        barChart.setNoDataTextColor(Color.parseColor("#74789C"));
        barChart.setNoDataText("暂无数据");
        barChart.setScaleXEnabled(true);
        barChart.setScaleYEnabled(false);


        barChart.getDescription().setEnabled(false);
        barChart.setPinchZoom(false);
        barChart.setDoubleTapToZoomEnabled(false);

        Legend legend = barChart.getLegend();
        legend.setEnabled(false);

        final YAxis leftAxis = barChart.getAxisLeft();

        leftAxis.setDrawGridLines(false);
        leftAxis.setTextColor(Color.parseColor("#74789C"));
        leftAxis.setAxisMinimum(0);
        leftAxis.setLabelCount(6, false);
        leftAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                int v = (int) value;
                return v == leftAxis.getAxisMaximum() ? "人数" : v + "";
            }
        });
        leftAxis.setXOffset(8);

        YAxis rightAxis = barChart.getAxisRight();
        rightAxis.setEnabled(false);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setGranularity(1f);

        xAxis.setTextColor(Color.parseColor("#74789C"));
        xAxis.setTextSize(8);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(true);
        xAxis.setLabelCount(2, false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        PresenceGroupMarkerView mv = new PresenceGroupMarkerView(getActivity());
        mv.setChartView(barChart);
        barChart.setMarker(mv);
    }

    @Override
    protected void initData() {
        setListener();
    }

    private void setListener() {
        barChart.setOnChartGestureListener(new SimpleOnChartGestureListener() {
            @Override
            public void onChartSingleTapped(MotionEvent me) {
                super.onChartSingleTapped(me);
            }
        });
    }

    public void showData(final List<RealTimeTeamStatisticsInfo> list) {
        XAxis xAxis = barChart.getXAxis();
        xAxis.setLabelCount(list.size(), false);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                int index = (int) value;
                return list.get(index).teams;
            }
        });

        int maxValue = 7;
        List<BarEntry> entries = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            RealTimeTeamStatisticsInfo info = list.get(i);
            entries.add(new BarEntry(i, info.count, info));
            if (maxValue < info.count) {
                maxValue = info.count;
            }
        }
        YAxis leftAxis = barChart.getAxisLeft();
        leftAxis.setAxisMaximum(maxValue);


        BarDataSet barDataSet = new BarDataSet(entries, "");
        barDataSet.setColor(Color.parseColor("#34C8FF"));
        barDataSet.setValueTextSize(10);
        barDataSet.setValueTextColor(Color.parseColor("#74789C"));
        barDataSet.setDrawValues(false);
        barDataSet.setHighlightEnabled(true);
        barDataSet.setHighLightColor(Color.parseColor("#8194A7"));

        int color1 = ContextCompat.getColor(getContext(), android.R.color.holo_orange_light);
        int color2 = ContextCompat.getColor(getContext(), android.R.color.holo_blue_light);
        int color7 = ContextCompat.getColor(getContext(), android.R.color.holo_purple);
        int color4 = ContextCompat.getColor(getContext(), android.R.color.holo_green_light);
        int color5 = ContextCompat.getColor(getContext(), android.R.color.holo_red_light);
        int color6 = ContextCompat.getColor(getContext(), android.R.color.holo_blue_dark);
        int color8 = ContextCompat.getColor(getContext(), android.R.color.holo_green_dark);
        int color9 = ContextCompat.getColor(getContext(), android.R.color.holo_red_dark);
        int color10 = ContextCompat.getColor(getContext(), android.R.color.holo_orange_dark);
        barDataSet.setColors(color1, color2, color4, color5, color6, color7, color8, color9, color10);

        BarData barData = new BarData();
        barData.addDataSet(barDataSet);

        barChart.setData(barData);

        if (list.size() > 5 && list.size() <= 7) {
            barChart.setScaleMinima(1.2f, 1);
        } else if (list.size() > 7) {
            barChart.setScaleMinima(1.5f, 1);
        }
        barChart.animateY(3000);
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////
    void test() {
        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                int index = (int) value;
                List<String> list = new ArrayList<>();
                list.add("木工班");
                list.add("土方班");
                list.add("钢筋班");
                list.add("水泥班");
                list.add("电焊班");
                list.add("高级班");
                list.add("班组");
                return list.get(index);
            }
        });

        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0, 10, "木工班"));
        entries.add(new BarEntry(1, 30, "土方班"));
        entries.add(new BarEntry(2, 40, "钢筋班"));
        entries.add(new BarEntry(3, 15, "水泥班"));
        entries.add(new BarEntry(4, 45, "电焊班"));
        entries.add(new BarEntry(5, 45, "高级班"));
        entries.add(new BarEntry(6, 0, "班组"));

        BarDataSet barDataSet = new BarDataSet(entries, "");
        barDataSet.setColor(Color.parseColor("#34C8FF"));
        barDataSet.setValueTextSize(10);
        barDataSet.setValueTextColor(Color.parseColor("#74789C"));
        barDataSet.setDrawValues(false);
        barDataSet.setHighlightEnabled(true);
        barDataSet.setHighLightColor(Color.parseColor("#8194A7"));

        int color1 = ContextCompat.getColor(getContext(), android.R.color.holo_orange_light);
        int color2 = ContextCompat.getColor(getContext(), android.R.color.holo_blue_light);
        int color7 = ContextCompat.getColor(getContext(), android.R.color.holo_purple);
        int color4 = ContextCompat.getColor(getContext(), android.R.color.holo_green_light);
        int color5 = ContextCompat.getColor(getContext(), android.R.color.holo_red_light);
        int color6 = ContextCompat.getColor(getContext(), android.R.color.holo_blue_dark);
        int color8 = ContextCompat.getColor(getContext(), android.R.color.holo_green_dark);
        int color9 = ContextCompat.getColor(getContext(), android.R.color.holo_red_dark);
        int color10 = ContextCompat.getColor(getContext(), android.R.color.holo_orange_dark);
        barDataSet.setColors(color1, color2, color4, color5, color6, color7, color8, color9, color10);

        BarData barData = new BarData();
        barData.addDataSet(barDataSet);

        barChart.setData(barData);
        barChart.animateY(3000);
    }

}
