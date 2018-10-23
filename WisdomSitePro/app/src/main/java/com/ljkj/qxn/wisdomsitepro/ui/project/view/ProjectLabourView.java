package com.ljkj.qxn.wisdomsitepro.ui.project.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 类描述：劳务实名制
 * 创建人：lxx
 * 创建时间：2018/6/20
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class ProjectLabourView extends FrameLayout {

    @BindView(R.id.tv_workers)
    TextView workersText; //在册工人数

    @BindView(R.id.tv_entrance)
    TextView entranceText; //入场人数

    @BindView(R.id.tv_present)
    TextView presentText; //在场人数

    @BindView(R.id.tv_attendance)
    TextView attendanceText; //出勤率

    public ProjectLabourView(@NonNull Context context) {
        this(context, null);
    }

    public ProjectLabourView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProjectLabourView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = inflate(context, R.layout.view_project_labour, this);
        ButterKnife.bind(this, view);
    }

    /**
     * @param workers    在册工人数
     * @param entrance   入场人数
     * @param present    在场人数
     * @param attendance 出勤率
     */
    public void setData(String workers, String entrance, String present, String attendance) {
        workersText.setText(workers);
        entranceText.setText(entrance);
        presentText.setText(present);
        attendanceText.setText(attendance);
    }

    void test() {
        workersText.setText("500");
        entranceText.setText("400");
        presentText.setText("300");
        attendanceText.setText("80%");
    }

}
