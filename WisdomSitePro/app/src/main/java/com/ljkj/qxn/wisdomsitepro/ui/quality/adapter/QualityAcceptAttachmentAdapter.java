package com.ljkj.qxn.wisdomsitepro.ui.quality.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.Utils.JumpUtil;
import com.ljkj.qxn.wisdomsitepro.data.entity.EnclosureInfo;

import butterknife.BindView;
import butterknife.ButterKnife;
import cdsp.android.ui.BaseRecyclerAdapter;

/**
 * 类描述：质量验收附件
 * 创建人：rjf
 * 创建时间：2018/3/10 15:31.
 * 修改人：
 * 修改时间
 * 修改备注
 */

public class QualityAcceptAttachmentAdapter extends BaseRecyclerAdapter<EnclosureInfo, QualityAcceptAttachmentAdapter.AttachmentViewHolder> {
    public QualityAcceptAttachmentAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    protected boolean useItemAnimation() {
        return false;
    }

    @Override
    public AttachmentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_quality_accept_attachment, parent, false);
        return new AttachmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AttachmentViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        final EnclosureInfo enclosureInfo = getItem(position);
        holder.tvAttachmentName.setText(enclosureInfo.getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtil.toBrowserForEnclosure(mContext, enclosureInfo);
            }
        });
    }

    public class AttachmentViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_attachment_name)
        TextView tvAttachmentName;

        public AttachmentViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
