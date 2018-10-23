package com.ljkj.qxn.wisdomsitepro.ui.project;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
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
import com.ljkj.qxn.wisdomsitepro.data.SafeQualityStatisticsInfo;
import com.ljkj.qxn.wisdomsitepro.ui.project.view.SafetyQualityChartMarkerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cdsp.android.ui.base.BaseFragment;

/**
 * 类描述：本月安全质量情况统计 图表
 * 创建人：lxx
 * 创建时间：2018/8/23
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SafetyQualityChartFragment extends BaseFragment {

    @BindView(R.id.line_chart)
    LineChart lineChart;

    public static SafetyQualityChartFragment newInstance() {
        SafetyQualityChartFragment fragment = new SafetyQualityChartFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_safety_quality_chart, container, false);
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
        legend.setEnabled(true);
        legend.setTextColor(Color.parseColor("#74789C"));
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setForm(Legend.LegendForm.CIRCLE);


        lineChart.getDescription().setEnabled(false);
        Description description = lineChart.getDescription();
        description.setText("描述");
        description.setTextColor(Color.RED);
        description.setTextSize(10);
        lineChart.setDescription(description);

        final YAxis leftAxis = lineChart.getAxisLeft();
        leftAxis.setDrawGridLines(false);
        leftAxis.setAxisMaximum(70);
        leftAxis.setTextColor(Color.parseColor("#74789C"));
        leftAxis.setAxisMinimum(0);
        leftAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                int v = (int) value;
                return v == leftAxis.getAxisMaximum() ? "次数" : v + "";
            }
        });
        leftAxis.setXOffset(8);

        SafetyQualityChartMarkerView mv = new SafetyQualityChartMarkerView(getActivity());
        mv.setChartView(lineChart);
        lineChart.setMarker(mv);

        YAxis rightAxis = lineChart.getAxisRight();
        rightAxis.setEnabled(false);

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setTextColor(Color.parseColor("#74789C"));
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(true);
        xAxis.setAxisMaximum(31);
        xAxis.setAxisMinimum(1);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setSpaceMax(2);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                int v = (int) value;
                return v >= axis.getAxisMaximum() ? "时刻" : v + "";
            }
        });
        xAxis.setLabelCount(16, true);
    }

    @Override
    protected void initData() {
        setListener();
    }

    private LineDataSet getLineData(List<Entry> entries, String label, int lineColor) {
        LineDataSet lineDataSet = new LineDataSet(entries, label);
        lineDataSet.setColor(lineColor);
        lineDataSet.setLineWidth(2);
        lineDataSet.setValueTextSize(10);
        lineDataSet.setValueTextColor(Color.parseColor("#74789C"));
        lineDataSet.setDrawValues(false);
        lineDataSet.setDrawCircles(true);
        lineDataSet.setMode(LineDataSet.Mode.LINEAR);
        lineDataSet.setHighlightEnabled(true);
        lineDataSet.setHighLightColor(Color.parseColor("#8194A7"));
        return lineDataSet;
    }

    private void setListener() {
        lineChart.setOnChartGestureListener(new SimpleOnChartGestureListener() {
            @Override
            public void onChartSingleTapped(MotionEvent me) {
                super.onChartSingleTapped(me);
            }
        });
    }

    public void showData(SafeQualityStatisticsInfo info) {
        YAxis leftAxis = lineChart.getAxisLeft();
        int maxValue = 7;
        int maxDay = 5;
        List<SafeQualityStatisticsInfo.Data> secPatrol = info.secPatrol;
        List<SafeQualityStatisticsInfo.Data> quaPatrol = info.quaPatrol;
        List<SafeQualityStatisticsInfo.Data> secCheck = info.secCheck;
        List<SafeQualityStatisticsInfo.Data> quaCheck = info.quaCheck;

        LineData lineData = new LineData();
        if (secPatrol.size() > 0) {
            List<Entry> entries1 = new ArrayList<>();
            LineDataSet safeInspectionSet = getLineData(entries1, "安全巡检", Color.parseColor("#1A98CF"));
            for (SafeQualityStatisticsInfo.Data data : secPatrol) {
                entries1.add(new Entry(data.day, data.data, info));
                maxValue = maxValue > data.data ? maxValue : data.data;
                maxDay = maxDay > data.day ? maxDay : data.day;
            }
            safeInspectionSet.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
            lineData.addDataSet(safeInspectionSet);
        }

        if (quaPatrol.size() > 0) {
            List<Entry> entries2 = new ArrayList<>();
            LineDataSet qualityInspectionSet = getLineData(entries2, "质量巡检", Color.parseColor("#F56106"));
            for (SafeQualityStatisticsInfo.Data data : quaPatrol) {
                entries2.add(new Entry(data.day, data.data, info));
                maxValue = maxValue > data.data ? maxValue : data.data;
                maxDay = maxDay > data.day ? maxDay : data.day;
            }
            qualityInspectionSet.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
            lineData.addDataSet(qualityInspectionSet);
        }

        if (secCheck.size() > 0) {
            List<Entry> entries3 = new ArrayList<>();
            LineDataSet safeCheckSet = getLineData(entries3, "安全检查", Color.parseColor("#65CC97"));
            for (SafeQualityStatisticsInfo.Data data : secCheck) {
                entries3.add(new Entry(data.day, data.data, info));
                maxValue = maxValue > data.data ? maxValue : data.data;
                maxDay = maxDay > data.day ? maxDay : data.day;
            }
            safeCheckSet.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
            lineData.addDataSet(safeCheckSet);
        }

        if (quaCheck.size() > 0) {
            List<Entry> entries4 = new ArrayList<>();
            LineDataSet qualityCheckSet = getLineData(entries4, "质量检查", Color.parseColor("#FECC99"));
            for (SafeQualityStatisticsInfo.Data data : quaCheck) {
                entries4.add(new Entry(data.day, data.data, info));
                maxValue = maxValue > data.data ? maxValue : data.data;
                maxDay = maxDay > data.day ? maxDay : data.day;
            }
            qualityCheckSet.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
            lineData.addDataSet(qualityCheckSet);
        }

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setAxisMaximum(maxDay);
        leftAxis.setAxisMaximum(maxValue);

        lineChart.setData(lineData);
        lineChart.invalidate();
        lineChart.animateY(3000);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    void test() {
        List<Entry> entries1 = new ArrayList<>();
        entries1.add(new Entry(3, 23));
        entries1.add(new Entry(10, 10));
        entries1.add(new Entry(14, 20));
        entries1.add(new Entry(16, 30));
        entries1.add(new Entry(20, 50));
        LineDataSet safeInspectionSet = getLineData(entries1, "安全巡检", Color.parseColor("#1A98CF"));

        List<Entry> entries2 = new ArrayList<>();
        entries2.add(new Entry(5, 3));
        entries2.add(new Entry(10, 30));
        entries2.add(new Entry(17, 23));
        entries2.add(new Entry(22, 35));
        entries2.add(new Entry(27, 40));
        LineDataSet qualityInspectionSet = getLineData(entries2, "质量巡检", Color.parseColor("#F56106"));

        List<Entry> entries3 = new ArrayList<>();
        entries3.add(new Entry(2, 6));
        entries3.add(new Entry(12, 14));
        entries3.add(new Entry(15, 53));
        entries3.add(new Entry(19, 45));
        entries3.add(new Entry(29, 20));
        LineDataSet safeCheckSet = getLineData(entries3, "安全检查", Color.parseColor("#65CC97"));

        List<Entry> entries4 = new ArrayList<>();
        entries4.add(new Entry(2, 6));
        entries4.add(new Entry(12, 14));
        entries4.add(new Entry(15, 53));
        entries4.add(new Entry(19, 45));
        entries4.add(new Entry(29, 20));
        LineDataSet qualityCheckSet = getLineData(entries4, "质量检查", Color.parseColor("#FECC99"));

        LineData lineData = new LineData();
        lineData.addDataSet(safeInspectionSet);
        lineData.addDataSet(qualityInspectionSet);
        lineData.addDataSet(safeCheckSet);
        lineData.addDataSet(qualityCheckSet);

        lineChart.setData(lineData);
        lineChart.invalidate();
        lineChart.animateY(3000);
    }
}
