package com.ljkj.qxn.wisdomsitepro.ui.safe.adapter;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.ljkj.qxn.wisdomsitepro.R;

import java.util.List;

/**
 * 类描述：新增安全教育-选择班组Adapter
 * 创建人：lxx
 * 创建时间：2018/9/21
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SelectTeamAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {
    private static final int TYPE_COMPANY = 1;
    private static final int TYPE_TEAM = 2;

    public SelectTeamAdapter(List<MultiItemEntity> data) {
        super(data);
        addItemType(TYPE_COMPANY, R.layout.adaper_select_team_company);
        addItemType(TYPE_TEAM, R.layout.adapter_select_team);
    }

    @Override
    protected void convert(final BaseViewHolder holder, MultiItemEntity item) {
        switch (holder.getItemViewType()) {
            case TYPE_COMPANY:
                final CompanyItem companyItem = (CompanyItem) item;
                TextView companyText = holder.getView(R.id.tv_company_name);
                holder.setText(R.id.tv_company_name, companyItem.name);
                companyText.setCompoundDrawablesWithIntrinsicBounds(0, 0, companyItem.isExpanded() ? R.mipmap.ic_arrow_up : R.mipmap.ic_arrow_down, 0);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = holder.getAdapterPosition();
                        if (companyItem.isExpanded()) {
                            collapse(pos);
                        } else {
                            expand(pos);
                        }
                    }
                });

                break;
            case TYPE_TEAM:
                final TeamItem teamItem = (TeamItem) item;
                final TextView teamText = holder.getView(R.id.tv_team_name);
                teamText.setText(teamItem.name);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (teamItem.isSelect) {
                            teamItem.isSelect = false;
                            teamText.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.ic_circle_unselected, 0, 0, 0);
                        } else {
                            teamItem.isSelect = true;
                            teamText.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.ic_circle_select, 0, 0, 0);
                        }
                    }
                });
                break;
            default:
                break;
        }
    }

    public static class CompanyItem extends AbstractExpandableItem<TeamItem> implements MultiItemEntity {
        public String name;
        public String id;

        public CompanyItem(String name, String id) {
            this.name = name;
            this.id = id;
        }

        @Override
        public int getLevel() {
            return 0;
        }

        @Override
        public int getItemType() {
            return TYPE_COMPANY;
        }
    }

    public static class TeamItem implements MultiItemEntity {
        /** 班组名 */
        public String name;
        /** 班组负责人 */
        public String teamLeader;

        /** 班组长id */
        public String teamLeaderId;

        public String id; //班组id

        public boolean isSelect;

        public TeamItem(String name, String teamLeader) {
            this.name = name;
            this.teamLeader = teamLeader;
        }

        @Override
        public int getItemType() {
            return TYPE_TEAM;
        }
    }


}
