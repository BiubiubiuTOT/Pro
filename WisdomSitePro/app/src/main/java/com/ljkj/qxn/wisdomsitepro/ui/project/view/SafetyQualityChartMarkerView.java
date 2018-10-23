package com.ljkj.qxn.wisdomsitepro.ui.project.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.TextView;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.data.SafeQualityStatisticsInfo;

/**
 * 类描述：本月安全质量情况统计 MarkerView
 * 创建人：lxx
 * 创建时间：2018/8/24
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SafetyQualityChartMarkerView extends MarkerView {
    private TextView dateText;
    private TextView safeInspectText;
    private TextView qualityInspectText;
    private TextView safeCheckText;
    private TextView qualityCheckText;

    public SafetyQualityChartMarkerView(Context context) {
        super(context, R.layout.view_safety_quality_chart);
        dateText = (TextView) findViewById(R.id.tv_date);
        safeInspectText = (TextView) findViewById(R.id.tv_safe_inspect);
        qualityInspectText = (TextView) findViewById(R.id.tv_quality_inspect);
        safeCheckText = (TextView) findViewById(R.id.tv_safe_check);
        qualityCheckText = (TextView) findViewById(R.id.tv_quality_check);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        int date = (int) e.getX();
        SafeQualityStatisticsInfo info = (SafeQualityStatisticsInfo) e.getData();
        dateText.setText("日期：" + date + "日");
        safeInspectText.setText("安全巡检：暂无");
        qualityInspectText.setText("质量巡检：暂无");
        safeCheckText.setText("安全检查：暂无");
        qualityCheckText.setText("质量检查：暂无");
        for (SafeQualityStatisticsInfo.Data data : info.secPatrol) {
            if (date == data.day) {
                safeInspectText.setText("安全巡检：" + data.data + "次");
                break;
            }
        }
        for (SafeQualityStatisticsInfo.Data data : info.quaPatrol) {
            if (date == data.day) {
                qualityInspectText.setText("质量巡检：" + data.data + "次");
                break;
            }
        }
        for (SafeQualityStatisticsInfo.Data data : info.secCheck) {
            if (date == data.day) {
                safeCheckText.setText("安全检查：" + data.data + "次");
                break;
            }
        }
        for (SafeQualityStatisticsInfo.Data data : info.quaCheck) {
            if (date == data.day) {
                qualityCheckText.setText("质量检查：" + data.data + "次");
                break;
            }
        }
        super.refreshContent(e, highlight);
    }

}
