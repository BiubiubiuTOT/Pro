package com.ljkj.qxn.wisdomsitepro.ui.project.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.data.entity.ProjectStatisticsInfo;

import java.util.List;

/**
 * 类描述：现场在场人数
 * 创建人：lxx
 * 创建时间：2018/6/20
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class ProjectTeamPersonView extends LinearLayout {
    public ProjectTeamPersonView(@NonNull Context context) {
        this(context, null);
    }

    public ProjectTeamPersonView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProjectTeamPersonView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(VERTICAL);
    }

    public void setData(List<ProjectStatisticsInfo.Team> list) {
        removeAllViews();
        while (list.size() > 0) {
            if (list.size() > 1) {
                String left = list.get(0).name + "：" + list.get(0).show;
                String right = list.get(1).name + "：" + list.get(1).show;
                addView(createPersonItem(left, right));
                list.remove(0);
                list.remove(0);
            } else {
                String left = list.get(0).name + "：" + list.get(0).show;
                addView(createPersonItem(left, ""));
                list.remove(0);
            }
        }

    }

    private View createPersonItem(String leftContent, String rightContent) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_project_team_person_item, this, false);
        TextView leftText = (TextView) view.findViewById(R.id.tv_left);
        TextView rightText = (TextView) view.findViewById(R.id.tv_right);
        leftText.setText(leftContent);
        rightText.setText(rightContent);
        return view;
    }

}
