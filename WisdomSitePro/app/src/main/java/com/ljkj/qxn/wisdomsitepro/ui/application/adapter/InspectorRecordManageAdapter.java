package com.ljkj.qxn.wisdomsitepro.ui.application.adapter;

import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.data.entity.InspectorRecorderManageInfo;
import com.ljkj.qxn.wisdomsitepro.ui.application.supervisor.SwipeListener;
import com.mcxtzhang.swipemenulib.SwipeMenuLayout;

import java.util.List;

/**
 * 类描述：巡视记录管理Adapter
 * 创建人：lxx
 * 创建时间：2018/8/29
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class InspectorRecordManageAdapter extends BaseQuickAdapter<InspectorRecorderManageInfo, BaseViewHolder> {
    private SwipeListener mOnSwipeListener;

    public InspectorRecordManageAdapter(@Nullable List<InspectorRecorderManageInfo> data) {
        super(R.layout.adapter_inspector_record_manage, data);
    }

    public void setOnSwipeListener(SwipeListener mOnDelListener) {
        this.mOnSwipeListener = mOnDelListener;
    }

    @Override
    protected void convert(final BaseViewHolder helper, InspectorRecorderManageInfo item) {
        helper.setText(R.id.tv_title, item.getName());
        helper.setText(R.id.tv_name, item.getCreateUserName());
        helper.setText(R.id.tv_unit, "监理单位：" + item.getSupervisionUnit());
        helper.setText(R.id.tv_date, "巡视时间：" + item.getPatrolTime());

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

    private boolean isCanDelete(InspectorRecorderManageInfo info) {
        return info.getFlag() == 1;

    }


}
