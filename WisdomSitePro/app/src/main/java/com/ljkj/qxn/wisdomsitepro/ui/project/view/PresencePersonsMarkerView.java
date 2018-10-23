package com.ljkj.qxn.wisdomsitepro.ui.project.view;

import android.content.Context;
import android.widget.TextView;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.Utils;
import com.ljkj.qxn.wisdomsitepro.R;

/**
 * 类描述：项目实时在场人数变化趋势 MarkerView
 * 创建人：lxx
 * 创建时间：2018/8/24
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class PresencePersonsMarkerView extends MarkerView {
    private TextView timeText;
    private TextView personsText;

    public PresencePersonsMarkerView(Context context) {
        super(context, R.layout.view_presence_person_marker);
        timeText = (TextView) findViewById(R.id.tv_time);
        personsText = (TextView) findViewById(R.id.tv_persons);
    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        float time = (int) e.getX();
        int persons = (int) e.getY();
        timeText.setText("时刻：" + Utils.formatNumber(time, 0, true) + "点");
        personsText.setText("人数：" + persons + "人");
        super.refreshContent(e, highlight);
    }

}
