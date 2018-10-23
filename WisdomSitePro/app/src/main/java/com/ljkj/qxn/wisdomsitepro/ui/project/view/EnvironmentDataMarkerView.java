package com.ljkj.qxn.wisdomsitepro.ui.project.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.TextView;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.Utils;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.data.entity.EnvironmentStatisticsInfo;

/**
 * 类描述：最近24小时环境数据统计 图表
 * 创建人：lxx
 * 创建时间：2018/8/21zF
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class EnvironmentDataMarkerView extends MarkerView {

    private TextView timeText;
    private TextView pmText;

    public EnvironmentDataMarkerView(Context context) {
        super(context, R.layout.view_environment_data_marker);
        timeText = (TextView) findViewById(R.id.tv_time);
        pmText = (TextView) findViewById(R.id.tv_pm);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        float time = (int) e.getX();
        float pm = e.getY();
        EnvironmentStatisticsInfo.PmInfo pmInfo = (EnvironmentStatisticsInfo.PmInfo) e.getData();
        timeText.setText("时刻：" + pmInfo.timeHour + "点");
        pmText.setText("PM2.5：" + Utils.formatNumber(pm, 0, false, '.') + "ug/m³");

        super.refreshContent(e, highlight);
    }

}
