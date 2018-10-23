package com.ljkj.qxn.wisdomsitepro.ui.application.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.data.entity.TeamPersonInfo;

import java.util.List;

/**
 * 类描述：人员档案adapter
 * 创建人：lxx
 * 创建时间：2018/7/2
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class PersonArchiveAdapter extends BaseQuickAdapter<TeamPersonInfo, BaseViewHolder> {

    public PersonArchiveAdapter(@Nullable List<TeamPersonInfo> data) {
        super(R.layout.adapter_person_archive, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TeamPersonInfo item) {
        helper.setText(R.id.tv_team, item.getTeams());
        String unit = item.getUnit() == null ? "" : item.getUnit();
        helper.setText(R.id.tv_unit, "分包单位：" + unit);
        helper.setText(R.id.tv_team_leader, "班组长：" + item.getTeamManger());
        helper.setText(R.id.tv_phone, "电话：" + item.getPhone());
        helper.setText(R.id.tv_members_count, String.valueOf(item.getCount()) + "人");
    }

}
