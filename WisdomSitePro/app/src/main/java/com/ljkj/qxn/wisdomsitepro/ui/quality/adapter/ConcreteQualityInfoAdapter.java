package com.ljkj.qxn.wisdomsitepro.ui.quality.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.data.entity.BaseConcreteInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.ConcreteCompressiveInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.ConcreteEntranceAcceptanceInfo;

import java.text.SimpleDateFormat;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import cdsp.android.ui.BaseRecyclerAdapter;

/**
 * 类描述：混凝土质量信息
 * 创建人：liufei
 * 创建时间：2018/2/2 10:54
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class ConcreteQualityInfoAdapter extends BaseRecyclerAdapter<BaseConcreteInfo, ConcreteQualityInfoAdapter.MsgViewHolder> {

    private int type;
    private SimpleDateFormat dateFormat;

    public ConcreteQualityInfoAdapter(Context mContext, int type) {
        super(mContext);
        this.type = type;
        dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    }


    @Override
    protected boolean useItemAnimation() {
        return false;
    }

    @Override
    public MsgViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_concrete_quality_info, parent, false);
        return new MsgViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MsgViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        if (type == 0) { //商品混凝土进场验收
            ConcreteEntranceAcceptanceInfo entranceAcceptanceInfo = (ConcreteEntranceAcceptanceInfo) getItem(position);
            holder.titleText.setText("浇筑部位：" + entranceAcceptanceInfo.getPouringPart());
            holder.meetRequirementText.setVisibility(View.GONE);
            holder.dateLeftText.setText("浇筑时间：" + entranceAcceptanceInfo.getPouringStartDate());
            holder.dateRightText.setVisibility(View.VISIBLE);
            holder.dateRightText.setText("结束时间：" + entranceAcceptanceInfo.getPouringEndDate());
            holder.usePartText.setText("混凝土类别：" + entranceAcceptanceInfo.getConcreteType());
            holder.maintenanceModeText.setText("强度等级：" + entranceAcceptanceInfo.getStrengthGrade());
            holder.checkNumberText.setText("混合比编号：" + entranceAcceptanceInfo.getCoordinationCode());
            holder.imperviousLevelText.setVisibility(View.GONE);

        } else if (type == 1) { //混凝土抗压强度检验
            ConcreteCompressiveInfo concreteCompressiveInfo = (ConcreteCompressiveInfo) getItem(position);
            holder.titleText.setText("样品名称：" + concreteCompressiveInfo.getSampleName());
            holder.meetRequirementText.setVisibility(View.VISIBLE);
            holder.meetRequirementText.setText("检验结果：" + concreteCompressiveInfo.getCheckResult());
            holder.meetRequirementText.setCompoundDrawables(null, null, null, null);
            holder.dateLeftText.setText("检验时间：" + concreteCompressiveInfo.getCheckDate());
            holder.dateRightText.setVisibility(View.GONE);
            holder.usePartText.setText("使用部位：" + concreteCompressiveInfo.getUsePart());
            holder.maintenanceModeText.setText("养护方式：" + concreteCompressiveInfo.getMaintenanceMode());
            holder.checkNumberText.setText("检验编号：" + concreteCompressiveInfo.getCheckCode());
            holder.imperviousLevelText.setText("设计强度等级：" + concreteCompressiveInfo.getDesignStrengthGrade());

        } else { //混凝土抗渗检验
            ConcreteCompressiveInfo permeabilityInfo = (ConcreteCompressiveInfo) getItem(position);
            holder.titleText.setText("样品名称：" + permeabilityInfo.getSampleName());
            holder.meetRequirementText.setVisibility(View.GONE);
            holder.dateLeftText.setText("检验时间：" + permeabilityInfo.getCheckDate());
            holder.dateRightText.setVisibility(View.GONE);
            holder.usePartText.setText("使用部位：" + permeabilityInfo.getUsePart());
            holder.maintenanceModeText.setText("养护方式：" + permeabilityInfo.getMaintenanceMode());
            holder.checkNumberText.setText("检验编号：" + permeabilityInfo.getCheckCode());
            holder.imperviousLevelText.setText("设计抗渗等级：" + permeabilityInfo.getDesignImperviousGrade());
        }
    }

    private String getDateStr(String millisStr) {
        try {
            long millis = Long.parseLong(millisStr);
            return dateFormat.format(millis);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    class MsgViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_title)
        TextView titleText;

        @BindView(R.id.tv_meet_requirement)
        TextView meetRequirementText;

        @BindView(R.id.tv_date_left)
        TextView dateLeftText;

        @BindView(R.id.tv_date_right)
        TextView dateRightText;

        @BindView(R.id.tv_use_part)
        TextView usePartText;

        @BindView(R.id.tv_maintenance_mode)
        TextView maintenanceModeText;

        @BindView(R.id.tv_check_number)
        TextView checkNumberText;

        @BindView(R.id.tv_impervious_level)
        TextView imperviousLevelText;

        public MsgViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
