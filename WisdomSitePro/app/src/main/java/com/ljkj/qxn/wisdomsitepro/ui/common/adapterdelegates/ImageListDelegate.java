package com.ljkj.qxn.wisdomsitepro.ui.common.adapterdelegates;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.hannesdorfmann.adapterdelegates3.AbsListItemAdapterDelegate;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.data.BaseEntity;
import com.ljkj.qxn.wisdomsitepro.data.ImageListEntity;
import com.ljkj.qxn.wisdomsitepro.ui.common.adapter.ShowImageAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cdsp.android.ui.widget.DividerGridItemDecoration;
import cdsp.android.util.DisplayUtils;

/**
 * 类描述：图片列表
 * 创建人：mhl
 * 创建时间：2018/2/3 9:49
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class ImageListDelegate extends AbsListItemAdapterDelegate<ImageListEntity, BaseEntity, ImageListDelegate.LightTvBlueViewHolder> {

    private Context mContext;

    public ImageListDelegate(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    protected boolean isForViewType(@NonNull BaseEntity item, @NonNull List<BaseEntity> items, int position) {
        return item instanceof ImageListEntity;
    }

    @NonNull
    @Override
    protected LightTvBlueViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_simple_recycleview_list, parent, false);
        return new LightTvBlueViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ImageListEntity item, @NonNull LightTvBlueViewHolder viewHolder, @NonNull List<Object> payloads) {

        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) viewHolder.recyclerView.getLayoutParams();
        int margin = DisplayUtils.dip2px(mContext, 10.0f);
        params.setMargins(margin, margin, margin, margin);

        viewHolder.recyclerView.setLayoutManager(new GridLayoutManager(mContext, 3));
        if (viewHolder.recyclerView.getTag() == null) {
            viewHolder.recyclerView.setTag("tag");
            viewHolder.recyclerView.addItemDecoration(new DividerGridItemDecoration(mContext, ContextCompat.getDrawable(mContext, R.drawable.divider_of_select_image)));
        }

        ShowImageAdapter adapter = null;
        viewHolder.recyclerView.setAdapter(adapter = new ShowImageAdapter(mContext));
        adapter.loadData(item.listEnclosureInfos);
    }

    static class LightTvBlueViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.recyclerView)
        RecyclerView recyclerView;

        public LightTvBlueViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
