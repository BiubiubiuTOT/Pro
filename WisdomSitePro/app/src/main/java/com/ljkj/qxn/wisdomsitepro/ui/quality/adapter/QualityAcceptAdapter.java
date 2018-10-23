package com.ljkj.qxn.wisdomsitepro.ui.quality.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.data.entity.QualityAcceptInfo;

import butterknife.BindView;
import butterknife.ButterKnife;
import cdsp.android.ui.BaseRecyclerAdapter;
import cdsp.android.util.DateUtils;

/**
 * 类描述：质量验收
 * 创建人：liufei
 * 创建时间：2018/2/2 10:54
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class QualityAcceptAdapter extends BaseRecyclerAdapter<QualityAcceptInfo, QualityAcceptAdapter.ViewHolder> {

    public QualityAcceptAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    protected boolean useItemAnimation() {
        return false;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_quality_accept, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);

        QualityAcceptInfo acceptInfo = getItem(position);

        holder.tvStructureName.setText(acceptInfo.getFbgcmc());

        String acceptDate = DateUtils.timeStampToDate(acceptInfo.getYsrq(), DateUtils.PATTERN_DATE);
        String date = String.format(mContext.getString(R.string.quality_accept_date), acceptDate);
        holder.tvAcceptDate.setText(date);

        String underStructureType = String.format(mContext.getString(R.string.quality_accept_understructure_type), acceptInfo.getJclx());
        holder.tvUnderstructureType.setText(underStructureType);

        String basementFloorNum = String.format(mContext.getString(R.string.quality_accept_num_of_basement_floor), acceptInfo.getDxscs() + "层");
        holder.tvNumOfBasementFloor.setText(basementFloorNum);

        String structureType = String.format(mContext.getString(R.string.quality_accept_construct_type), acceptInfo.getJglx());
        holder.tvConstructType.setText(structureType);

        String structureArea = String.format(mContext.getString(R.string.quality_accept_structure_area), acceptInfo.getJzmj() + "㎡");
        holder.tvStructureArea.setText(structureArea);

        String constructurePeriod = String.format(mContext.getString(R.string.quality_accept_construct_period), acceptInfo.getSgzq());
        holder.tvConstructPeriod.setText(constructurePeriod);

        String acceptRegion = String.format(mContext.getString(R.string.quality_accept_region), acceptInfo.getYsbw());
        holder.tvAcceptRegion.setText(acceptRegion);


        QualityAcceptAttachmentAdapter adapter = new QualityAcceptAttachmentAdapter(mContext);
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        holder.recyclerView.setAdapter(adapter);
        adapter.loadData(acceptInfo.getFiles());

    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_structure_name)
        TextView tvStructureName;
        @BindView(R.id.tv_accept_date)
        TextView tvAcceptDate;
        @BindView(R.id.tv_understructure_type)
        TextView tvUnderstructureType;
        @BindView(R.id.tv_num_of_basement_floor)
        TextView tvNumOfBasementFloor;
        @BindView(R.id.tv_construct_type)
        TextView tvConstructType;
        @BindView(R.id.tv_structure_area)
        TextView tvStructureArea;
        @BindView(R.id.tv_construct_period)
        TextView tvConstructPeriod;
        @BindView(R.id.tv_accept_region)
        TextView tvAcceptRegion;
        @BindView(R.id.recyclerView)
        RecyclerView recyclerView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
