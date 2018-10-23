package com.ljkj.qxn.wisdomsitepro.ui.application.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.data.entity.AttendManagerTeamInfo;

import java.util.List;

/**
 * 类描述：考勤管理Adapter
 * 创建人：lxx
 * 创建时间：2018/7/18
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class AttendManagerAdapter extends BaseQuickAdapter<AttendManagerTeamInfo, BaseViewHolder> {

    public AttendManagerAdapter(@Nullable List<AttendManagerTeamInfo> data) {
        super(R.layout.adapter_attend_manager, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AttendManagerTeamInfo item) {
        helper.setText(R.id.tv_team, item.getTeams());
        helper.setText(R.id.tv_unit, "分包单位：" + item.getUnit());
        helper.setText(R.id.tv_team_leader, "班组长：" + item.getTeamManager());
        helper.setText(R.id.tv_team_persons, "班组成员数：" + item.getCount() + "人");
        helper.setText(R.id.tv_present_persons, item.getCurrentCount() + "人");
        helper.setText(R.id.tv_average_work_time, item.getJobTime() + "时");
    }

}
