package com.ljkj.qxn.wisdomsitepro.ui.safe.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.Utils.widget.LabelView;
import com.ljkj.qxn.wisdomsitepro.data.entity.CheckInfo;

import java.util.List;

/**
 * 类描述：检查列表 Adapter
 * 创建人：lxx
 * 创建时间：2018/9/5
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class CheckListAdapter2 extends BaseQuickAdapter<CheckInfo, BaseViewHolder> {

    private boolean isSupervisor = false;

    public CheckListAdapter2(@Nullable List<CheckInfo> data, boolean isSupervisor) {
        super(R.layout.adapter_check_list, data);
        this.isSupervisor = isSupervisor;
    }

    @Override
    protected void convert(BaseViewHolder helper, CheckInfo item) {
        if (isSupervisor) {
            handleForSupervisor(helper, item);
        } else {
            handle(helper, item);
        }
    }

    private void handle(BaseViewHolder helper, CheckInfo item) {
        helper.setText(R.id.tv_title, item.checkerName + item.checkPosition + "的问题指派");
        helper.setText(R.id.tv_name, item.checkUnit + "-" + item.checkerDuty);
        helper.setText(R.id.tv_rectify_type, item.reformType);
        helper.setText(R.id.tv_danger_level, item.reformGrade);
        helper.setText(R.id.tv_submit_time, "检查日期：" + item.checkDate);
        helper.setText(R.id.tv_rectify_time, "整改期限：" + item.reformDate);

        int status = item.reformStatus;
        LabelView labelView = helper.getView(R.id.label_view);
        if (status == CheckInfo.RE_RECTIFY || status == CheckInfo.WAIT_RECTIFY) { //待整改
            labelView.setData("待整改", Color.WHITE, Color.parseColor("#4491FA"));
            helper.setGone(R.id.tv_rectify, true);
            helper.setText(R.id.tv_rectify, status == CheckInfo.RE_RECTIFY ? "重新整改" : "立即整改");
        } else {
            helper.setGone(R.id.tv_rectify, false);
            if (status == CheckInfo.QUALIFIED) { //合格
                labelView.setData("合格", Color.WHITE, Color.parseColor("#37C171"));
            } else if (status == CheckInfo.WAIT_AUDITED) { //待核查
                labelView.setData("待核查", Color.WHITE, Color.parseColor("#FBB319"));
            }
        }
    }

    private void handleForSupervisor(BaseViewHolder helper, CheckInfo item) {
        helper.setText(R.id.tv_title, item.checkerName + item.checkPosition + "的问题指派");
        helper.setText(R.id.tv_name, item.checkUnit + "-" + item.checkerDuty);
        helper.setText(R.id.tv_rectify_type, item.reformType);
        helper.setText(R.id.tv_danger_level, item.reformGrade);
        helper.setText(R.id.tv_submit_time, "检查日期：" + item.checkDate);
        helper.setText(R.id.tv_rectify_time, "整改期限：" + item.reformDate);

        int status = item.handleType;
        LabelView labelView = helper.getView(R.id.label_view);
        helper.setGone(R.id.tv_rectify, false);
        if (status == 0) { //待监理自己处理
            labelView.setData("待处理", Color.WHITE, Color.parseColor("#FBB319"));
            helper.setGone(R.id.tv_rectify, true);
            helper.setText(R.id.tv_rectify, "立即处理");
        } else if (status == 1) { //待监督机构核查
            labelView.setData("待核查", Color.WHITE, Color.parseColor("#4491FA"));
        } else if (status == 2) { //合格
            labelView.setData("待处理", Color.WHITE, Color.parseColor("#37C171"));
        } else if (status == 3) { //待处理（待项目方重新整改）
            labelView.setData("待处理", Color.WHITE, Color.parseColor("#FBB319"));
        }
    }


}
