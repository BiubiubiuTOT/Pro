package com.ljkj.qxn.wisdomsitepro.ui.safe.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.data.entity.SafeHiddenAccountInfo;
import com.ljkj.qxn.wisdomsitepro.ui.safe.SafeHiddenAccountFragment;

import java.util.List;

/**
 * 类描述：安全隐患台账Adapter
 * 创建人：lxx
 * 创建时间：2018/7/11
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SafeHiddenAccountAdapter extends BaseQuickAdapter<SafeHiddenAccountInfo, BaseViewHolder> {

    private int accountType;

    public SafeHiddenAccountAdapter(@Nullable List<SafeHiddenAccountInfo> data, int accountType) {
        super(R.layout.adapter_safe_hidden_account, data);
        this.accountType = accountType;
    }

    @Override
    protected void convert(BaseViewHolder helper, SafeHiddenAccountInfo item) {
        helper.setText(R.id.tv_project_name, item.getProjectName());
        helper.setText(R.id.tv_check_date, "检查日期：" + item.getCheckDate());

        String endDate;
        try {
            endDate = item.getEndDate().split(" ")[0];
        } catch (Exception e) {
            endDate = item.getEndDate() == null ? "" : item.getEndDate();
        }

        helper.setText(R.id.tv_end_date, "销号日期：" + endDate);

        if (accountType == SafeHiddenAccountFragment.ACCOUNT_PRESENT_LEVEL) { //本级台账
            helper.setText(R.id.tv_content1, "隐患等级：" + item.getHiddenLevel());
            helper.setText(R.id.tv_content2, "是否定制预案：" + item.getPlan());
            helper.setText(R.id.tv_content3, "是否定制措施：" + item.getMeasure());
            String m = item.getMoney() == null ? "" : item.getMoney() + "万";
            helper.setText(R.id.tv_content4, "整改资金投入情况：" + m);
            helper.setText(R.id.tv_content5, "整改期限：" + item.getUpTime());
            helper.setText(R.id.tv_content6, "整改责任人：" + item.getTeamLeader());
        } else { //项目台账
            helper.setText(R.id.tv_content1, "事故类型：" + item.getAccidentType());
            helper.setText(R.id.tv_content2, "事故原因：" + item.getAccidentReason());
            helper.setText(R.id.tv_content3, "整改期限：" + item.getUpTime());
            helper.setText(R.id.tv_content4, "整改负责人：" + item.getTeamLeader());
            helper.setGone(R.id.tv_content5, false);
            helper.setGone(R.id.tv_content6, false);
        }

    }

}
