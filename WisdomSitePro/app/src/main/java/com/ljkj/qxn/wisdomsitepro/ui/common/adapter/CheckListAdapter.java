package com.ljkj.qxn.wisdomsitepro.ui.common.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.data.entity.QualityCheckInfo;
import com.ljkj.qxn.wisdomsitepro.ui.common.BaseCheckListActivity;

import java.text.SimpleDateFormat;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import cdsp.android.ui.BaseRecyclerAdapter;

/**
 * 类描述：检查 通用
 * 创建人：liufei
 * 创建时间：2018/2/2 10:54
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class CheckListAdapter extends BaseRecyclerAdapter<QualityCheckInfo, CheckListAdapter.ViewHolder> {

    private SimpleDateFormat dateFormat;

    private OnItemClickListener onItemClickListener;

    private String type = BaseCheckListActivity.QUALITY_TYPE;

    public CheckListAdapter(Context mContext) {
        super(mContext);
        dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    }

    public CheckListAdapter(Context mContext, String type) {
        this(mContext);
        this.type = type;
    }

    @Override
    protected boolean useItemAnimation() {
        return false;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_common_check, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);
        final QualityCheckInfo qualityCheckInfo = getItem(position);

        String zglx = qualityCheckInfo.getZglx(); //整改类型
        String clzt = qualityCheckInfo.getClzt(); //处理状态
        final String yhdj = qualityCheckInfo.getYhdj(); //隐患等级
        String sfsh = qualityCheckInfo.getSfsh(); // 是否审核

        String name = qualityCheckInfo.getJcry();
        holder.tvName.setText("检查人员：" + name);
        if (name != null && name.length() > 0) {
            String str = String.valueOf(name.charAt(name.length() - 1));
            holder.ivImg.setText(str);
        }

        holder.tvCheckDate.setText("检查日期：" + getDateStr(qualityCheckInfo.getJcrqTime()));
        holder.dangerLevelText.setText("隐患等级：" + qualityCheckInfo.getYhdj());
        holder.handleStateText.setText("处理状态：" + clzt);
        holder.rectifyTypeText.setText("整改类型：" + zglx);

        if (type.equals(BaseCheckListActivity.QUALITY_TYPE)) {
            holder.codeText.setText("质量检查编号：" + qualityCheckInfo.getCheckNo());
        } else if (type.equals(BaseCheckListActivity.SAFE_TYPE)) {
            holder.codeText.setText("安全检查编号：" + qualityCheckInfo.getCheckNo());
        }

        int flag = 0;
        if ("一般隐患".equals(yhdj) && sfsh.equals("立即整改")) { //1 立即整改
            holder.ivStatus.setVisibility(View.VISIBLE);
            holder.ivStatus.setImageResource(R.mipmap.ic_status_wait_rectify);
            flag = 1;

        } else if ("一般隐患".equals(yhdj) && sfsh.equals("待核查")) { //2 待核查
            holder.ivStatus.setVisibility(View.VISIBLE);
            holder.ivStatus.setImageResource(R.mipmap.ic_status_wait_audit);
            flag = 2;

        } else if ("一般隐患".equals(yhdj) && sfsh.startsWith("合格")) { //3 合格
            holder.ivStatus.setVisibility(View.VISIBLE);
            holder.ivStatus.setImageResource(R.mipmap.ic_status_qualified);
            flag = 3;

        } else if ("一般隐患".equals(yhdj) && sfsh.equals("不合格")) { //4  重新整改
            holder.ivStatus.setVisibility(View.VISIBLE);
            holder.ivStatus.setImageResource(R.mipmap.ic_status_unqualified);
            flag = 4;

        } else if ("重大隐患".equals(yhdj) && sfsh.equals("立即整改")) { //5  立即整改
            holder.ivStatus.setVisibility(View.VISIBLE);
            holder.ivStatus.setImageResource(R.mipmap.ic_status_wait_rectify);
            flag = 5;

        } else if ("重大隐患".equals(yhdj) && sfsh.equals("待核查")) { //6  核查中
            holder.ivStatus.setVisibility(View.VISIBLE);
            holder.ivStatus.setImageResource(R.mipmap.ic_status_wait_audit);
            flag = 6;

        } else if ("重大隐患".equals(yhdj) && sfsh.startsWith("合格")) { //7  核查
            holder.ivStatus.setVisibility(View.VISIBLE);
            holder.ivStatus.setImageResource(R.mipmap.ic_status_qualified);
            flag = 7;

        } else if ("重大隐患".equals(yhdj) && sfsh.equals("不合格")) { //8  重新整改
            holder.ivStatus.setVisibility(View.VISIBLE);
            holder.ivStatus.setImageResource(R.mipmap.ic_status_unqualified);
            flag = 8;

        } else {
            holder.ivStatus.setVisibility(View.GONE);
        }

        final int finalFlag = flag;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(position, finalFlag);
                }
            }
        });

    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position, int flag);
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

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_img)
        TextView ivImg;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_check_date)
        TextView tvCheckDate;

        @BindView(R.id.iv_status)
        ImageView ivStatus;

        @BindView(R.id.tv_danger_level)
        TextView dangerLevelText;

        @BindView(R.id.tv_handle_state)
        TextView handleStateText;

        @BindView(R.id.tv_rectify_type)
        TextView rectifyTypeText;

        @BindView(R.id.tv_code)
        TextView codeText;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
