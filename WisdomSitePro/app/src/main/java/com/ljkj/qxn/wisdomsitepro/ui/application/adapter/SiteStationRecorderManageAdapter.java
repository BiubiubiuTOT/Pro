package com.ljkj.qxn.wisdomsitepro.ui.application.adapter;

import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.data.entity.SiteStationRecorderManageInfo;
import com.ljkj.qxn.wisdomsitepro.ui.application.supervisor.SwipeListener;
import com.mcxtzhang.swipemenulib.SwipeMenuLayout;

import java.util.List;

/**
 * 类描述：旁站记录管理Adapter
 * 创建人：lxx
 * 创建时间：2018/8/29
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SiteStationRecorderManageAdapter extends BaseQuickAdapter<SiteStationRecorderManageInfo, BaseViewHolder> {
    private SwipeListener mOnSwipeListener;

    public SwipeListener getOnDelListener() {
        return mOnSwipeListener;
    }

    public void setOnSwipeListener(SwipeListener mOnDelListener) {
        this.mOnSwipeListener = mOnDelListener;
    }

    public SiteStationRecorderManageAdapter(@Nullable List<SiteStationRecorderManageInfo> data) {
        super(R.layout.adapter_site_station_recorder_manage, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, SiteStationRecorderManageInfo item) {
        helper.setText(R.id.tv_title, item.getName());
        helper.setText(R.id.tv_name,  item.getCreateUserName());
        helper.setText(R.id.tv_start_date, "旁站开始时间：" + item.getBeginTime());
        helper.setText(R.id.tv_finish_date, "旁站结束时间：" + item.getEndTime());

        ((SwipeMenuLayout) helper.itemView).setSwipeEnable(isCanDelete(item));
        helper.setOnClickListener(R.id.tv_delete, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnSwipeListener.onDelete(helper.getAdapterPosition());
            }
        });

        helper.setOnClickListener(R.id.ll_main, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnSwipeListener.onClick(helper.getAdapterPosition());
            }
        });
    }

    private boolean isCanDelete(SiteStationRecorderManageInfo info) {
        return info.getFlag() == 1;

    }
}
