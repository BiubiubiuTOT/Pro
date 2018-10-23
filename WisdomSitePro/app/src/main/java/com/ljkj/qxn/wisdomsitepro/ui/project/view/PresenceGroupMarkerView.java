package com.ljkj.qxn.wisdomsitepro.ui.project.view;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.data.entity.RealTimeTeamStatisticsInfo;

/**
 * 类描述：项目实时在场班组统计图表 MarkerView
 * 创建人：lxx
 * 创建时间：2018/8/24
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class PresenceGroupMarkerView extends MarkerView {
    private TextView nameText;
    private TextView teamPersonsText;
    private TextView presencePersonsText;
    private ViewGroup container;

    public PresenceGroupMarkerView(Context context) {
        super(context, R.layout.view_presence_group_marker);
        nameText = (TextView) findViewById(R.id.tv_name);
        teamPersonsText = (TextView) findViewById(R.id.tv_team_persons);
        presencePersonsText = (TextView) findViewById(R.id.tv_presence_persons);
        container = (ViewGroup) findViewById(R.id.container);
    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        RealTimeTeamStatisticsInfo info = (RealTimeTeamStatisticsInfo) e.getData();
        nameText.setText("班组：" + info.teams);

//        int presencePerson = (int) e.getY();
//        container.setVisibility(presencePerson == 0 ? View.GONE : View.VISIBLE);
        presencePersonsText.setText("现场人数：" + info.count + "人");
        teamPersonsText.setText("班组人数：" + info.totalCount + "人");
        super.refreshContent(e, highlight);
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight());
    }
}
