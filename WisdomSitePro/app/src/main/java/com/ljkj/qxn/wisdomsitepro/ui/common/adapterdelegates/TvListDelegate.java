package com.ljkj.qxn.wisdomsitepro.ui.common.adapterdelegates;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hannesdorfmann.adapterdelegates3.AbsListItemAdapterDelegate;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.data.BaseEntity;
import com.ljkj.qxn.wisdomsitepro.data.TvListEntity;
import com.ljkj.qxn.wisdomsitepro.ui.quality.adapter.TvListAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cdsp.android.ui.widget.DividerItemDecoration;
import cdsp.android.util.DisplayUtils;

/**
 * 类描述：竖向文字列表
 * 创建人：mhl
 * 创建时间：2018/2/3 9:49
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class TvListDelegate extends AbsListItemAdapterDelegate<TvListEntity, BaseEntity, TvListDelegate.TvListViewHolder> {

    private Context mContext;

    public TvListDelegate(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    protected boolean isForViewType(@NonNull BaseEntity item, @NonNull List<BaseEntity> items, int position) {
        return item instanceof TvListEntity;
    }

    @NonNull
    @Override
    protected TvListViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_simple_recycleview_list, parent, false);
        return new TvListViewHolder(view);

    }

    @Override
    protected void onBindViewHolder(@NonNull TvListEntity item, @NonNull TvListViewHolder viewHolder, @NonNull List<Object> payloads) {

        TvListAdapter adapter = null;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        if (viewHolder.recyclerView.getTag() == null) {
            viewHolder.recyclerView.setTag("tag");
            viewHolder.recyclerView.addItemDecoration(new DividerItemDecoration(mContext, LinearLayoutManager.VERTICAL, ContextCompat.getDrawable(mContext, R.drawable.divider_of_tv_list)));
        }
        viewHolder.recyclerView.setLayoutManager(linearLayoutManager);
        viewHolder.recyclerView.setAdapter(adapter = new TvListAdapter(mContext));

        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) viewHolder.recyclerView.getLayoutParams();
        layoutParams.setMargins(0, DisplayUtils.dip2px(mContext, 15.0f), 0, DisplayUtils.dip2px(mContext, 15.0f));
        adapter.loadData(item.tvList);

        if (TextUtils.isEmpty(item.mark)) {
            viewHolder.tvMark.setVisibility(View.GONE);
        } else {
            viewHolder.tvMark.setVisibility(View.VISIBLE);
            viewHolder.tvMark.setText(item.mark);
        }
    }

    static class TvListViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.recyclerView)
        RecyclerView recyclerView;

        @BindView(R.id.tv_mark)
        TextView tvMark;

        public TvListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
