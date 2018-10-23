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
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.utils.Utils;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.Utils.DateUtil;
import com.ljkj.qxn.wisdomsitepro.Utils.SimpleOnChartGestureListener;
import com.ljkj.qxn.wisdomsitepro.data.entity.RealTimePersonStatisticsInfo;
import com.ljkj.qxn.wisdomsitepro.ui.project.view.PresencePersonsMarkerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cdsp.android.ui.base.BaseFragment;

/**
 * 类描述：项目实时在场人数变化趋势 图表
 * 创建人：lxx
 * 创建时间：2018/8/23
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class PresencePersonsChartFragment extends BaseFragment {

    @BindView(R.id.tv_title)
    TextView titleText;

    @BindView(R.id.line_chart)
    LineChart lineChart;

    public static PresencePersonsChartFragment newInstance() {
        PresencePersonsChartFragment fragment = new PresencePersonsChartFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_presence_persons_chart, container, false);
        return view;
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void initUI() {
        titleText.setText("项目实时在场人数变化趋势（" + DateUtil.format(System.currentTimeMillis() + "") + ")");

        Paint noDataPaint = lineChart.getPaint(Chart.PAINT_INFO);
        noDataPaint.setTextSize(Utils.convertDpToPixel(16f));
        lineChart.setNoDataTextColor(Color.parseColor("#74789C"));
        lineChart.setNoDataText("暂无数据");

        lineChart.getDescription().setEnabled(false);
        lineChart.setPinchZoom(false);
        lineChart.setScaleEnabled(false);
        lineChart.setDoubleTapToZoomEnabled(false);

        Legend legend = lineChart.getLegend();
        legend.setEnabled(false);

        Description description = lineChart.getDescription();
        description.setText("描述");
        description.setTextColor(Color.RED);
        description.setTextSize(10);

        lineChart.setDescription(description);

        final YAxis leftAxis = lineChart.getAxisLeft();
        leftAxis.setDrawGridLines(false);
        leftAxis.setTextColor(Color.parseColor("#74789C"));
        leftAxis.setAxisMinimum(0);
        leftAxis.setLabelCount(7, false);
        leftAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                int v = (int) value;
                return v == leftAxis.getAxisMaximum() ? "人数" : v + "";
            }
        });
        leftAxis.setXOffset(8);

        LimitLine ll = new LimitLine(25, "test");
        ll.setTextColor(Color.RED);
        ll.setLineColor(Color.YELLOW);
        ll.setTextSize(12f);
        ll.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
//        leftAxis.addLimitLine(ll);

        PresencePersonsMarkerView mv = new PresencePersonsMarkerView(getActivity());
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
        xAxis.setLabelCount(12, false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelRotationAngle(0);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                int t = (int) value;
                if (t == 24) {
                    return "时刻";
                }
                return t + "";
            }
        });
    }

    @Override
    protected void initData() {
        setListener();
    }

    public void showData(List<RealTimePersonStatisticsInfo> list) {
        List<Entry> entries = new ArrayList<>();

        int maxValue = 7;
        for (RealTimePersonStatisticsInfo info : list) {
            entries.add(new Entry(info.countTime, info.peoJobCount));
            maxValue = maxValue > info.peoJobCount ? maxValue : info.peoJobCount;
        }
        YAxis leftAxis = lineChart.getAxisLeft();
        leftAxis.setAxisMaximum(maxValue);

        LineDataSet lineDataSet = new LineDataSet(entries, "");
        lineDataSet.setColor(Color.parseColor("#30E3A9"));
        lineDataSet.setLineWidth(2);
        lineDataSet.setValueTextSize(10);
        lineDataSet.setValueTextColor(Color.parseColor("#74789C"));
        lineDataSet.setDrawValues(false);
        lineDataSet.setDrawCircles(true);
        lineDataSet.setMode(LineDataSet.Mode.LINEAR);
        lineDataSet.setHighlightEnabled(true);
        lineDataSet.setHighLightColor(Color.parseColor("#8194A7"));
        lineDataSet.setDrawHorizontalHighlightIndicator(true);
        lineDataSet.setDrawVerticalHighlightIndicator(true);
        lineDataSet.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
        lineDataSet.setDrawFilled(true);
        lineDataSet.setFillDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.fade_presence_persons_chart));

        LineData lineData = new LineData();
        lineData.addDataSet(lineDataSet);

        lineChart.setData(lineData);
        lineChart.invalidate();
        lineChart.animateXY(3000, 3000);
    }

    private void setListener() {
        lineChart.setOnChartGestureListener(new SimpleOnChartGestureListener() {
            @Override
            public void onChartSingleTapped(MotionEvent me) {
                super.onChartSingleTapped(me);

            }
        });
    }


    void test() {
        List<Entry> entries = new ArrayList<>();
        entries.add(new Entry(3, 23));
        entries.add(new Entry(10, 10));
        entries.add(new Entry(14, 20));
        entries.add(new Entry(16, 30));
        entries.add(new Entry(20, 50));

        LineDataSet lineDataSet = new LineDataSet(entries, "");
        lineDataSet.setColor(Color.parseColor("#30E3A9"));
        lineDataSet.setLineWidth(2);
        lineDataSet.setValueTextSize(10);
        lineDataSet.setValueTextColor(Color.parseColor("#74789C"));
        lineDataSet.setDrawValues(false);
        lineDataSet.setDrawCircles(true);
        lineDataSet.setMode(LineDataSet.Mode.LINEAR);
        lineDataSet.setHighlightEnabled(true);
        lineDataSet.setHighLightColor(Color.parseColor("#8194A7"));
        lineDataSet.setDrawHorizontalHighlightIndicator(true);
        lineDataSet.setDrawVerticalHighlightIndicator(true);
        lineDataSet.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
        lineDataSet.setDrawFilled(true);
        lineDataSet.setFillDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.fade_presence_persons_chart));

        LineData lineData = new LineData();
        lineData.addDataSet(lineDataSet);

        lineChart.setData(lineData);
        lineChart.invalidate();
        lineChart.animateXY(3000, 3000);
    }
}
