package com.ljkj.qxn.wisdomsitepro.ui.message.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.Utils.DateUtil;
import com.ljkj.qxn.wisdomsitepro.data.entity.QualityCheckInfo;
import com.ljkj.qxn.wisdomsitepro.ui.message.SafeQualityListFragment;
import com.ljkj.qxn.wisdomsitepro.ui.quality.QuaCheckDetailsOfQualifiedActivity;
import com.ljkj.qxn.wisdomsitepro.ui.quality.QuaCheckDetailsOfRectifyActivity;
import com.ljkj.qxn.wisdomsitepro.ui.quality.QuaCheckDetailsOfToBeAuditedActivity;
import com.ljkj.qxn.wisdomsitepro.ui.quality.QuaGenerHiddenDangerOfImmeRectiActivity;
import com.ljkj.qxn.wisdomsitepro.ui.safe.SafeCheckDetailsOfQualifiedActivity;
import com.ljkj.qxn.wisdomsitepro.ui.safe.SafeCheckDetailsOfRectifyActivity;
import com.ljkj.qxn.wisdomsitepro.ui.safe.SafeCheckDetailsOfToBeAuditedActivity;
import com.ljkj.qxn.wisdomsitepro.ui.safe.SafeGenerHiddenDangerOfImmeRectiActivity;

import java.util.List;

/**
 * 类描述：消息：检查adapter
 * 创建人：lxx
 * 创建时间：2018/6/27
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SafeQualityListAdapter extends BaseQuickAdapter<QualityCheckInfo, BaseViewHolder> {
    private int type;

    public SafeQualityListAdapter(@Nullable List<QualityCheckInfo> data, int type) {
        super(R.layout.adapter_safe_quality_item, data);
        this.type = type;
    }

    @Override
    protected void convert(BaseViewHolder helper, final QualityCheckInfo item) {
        String zglx = item.getZglx(); //整改类型
        String clzt = item.getClzt(); //处理状态
        final String yhdj = item.getYhdj(); //隐患等级
        String sfsh = item.getSfsh(); // 是否审核

        String name = item.getJcry();
        helper.setText(R.id.tv_name, "检查人员：" + name);
        if (name != null && name.length() > 0) {
            String str = String.valueOf(name.charAt(name.length() - 1));
            helper.setText(R.id.iv_img, str);
        }

        helper.setText(R.id.tv_check_date, "检查日期：" + DateUtil.format(item.getJcrqTime()));
        helper.setText(R.id.tv_danger_level, "隐患等级："+yhdj);
        helper.setText(R.id.tv_handle_state, "处理状态：" + clzt);
        helper.setText(R.id.tv_rectify_type, "整改类型：" + zglx);
        helper.setText(R.id.tv_finish_date, "整改完成期限：" + DateUtil.format(item.getZgqx()));
        helper.setGone(R.id.tv_red_dot, false);

        final ImageView statusImage = helper.getView(R.id.iv_status);

        int flag = 0;
        if ("一般隐患".equals(yhdj) && sfsh.equals("立即整改")) { //1 立即整改
            statusImage.setVisibility(View.VISIBLE);
            statusImage.setImageResource(R.mipmap.ic_status_wait_rectify);
            flag = 1;

        } else if ("一般隐患".equals(yhdj) && sfsh.equals("待核查")) { //2 待核查
            statusImage.setVisibility(View.VISIBLE);
            statusImage.setImageResource(R.mipmap.ic_status_wait_audit);
            flag = 2;

        } else if ("一般隐患".equals(yhdj) && sfsh.startsWith("合格")) { //3 合格
            statusImage.setVisibility(View.VISIBLE);
            statusImage.setImageResource(R.mipmap.ic_status_qualified);
            flag = 3;

        } else if ("一般隐患".equals(yhdj) && sfsh.equals("不合格")) { //4  重新整改
            statusImage.setVisibility(View.VISIBLE);
            statusImage.setImageResource(R.mipmap.ic_status_unqualified);
            flag = 4;

        } else if ("重大隐患".equals(yhdj) && sfsh.equals("立即整改")) { //5  立即整改
            statusImage.setVisibility(View.VISIBLE);
            statusImage.setImageResource(R.mipmap.ic_status_wait_rectify);
            flag = 5;

        } else if ("重大隐患".equals(yhdj) && sfsh.equals("待核查")) { //6  核查中
            statusImage.setVisibility(View.VISIBLE);
            statusImage.setImageResource(R.mipmap.ic_status_wait_audit);
            flag = 6;

        } else if ("重大隐患".equals(yhdj) && sfsh.startsWith("合格")) { //7  核查
            statusImage.setVisibility(View.VISIBLE);
            statusImage.setImageResource(R.mipmap.ic_status_qualified);
            flag = 7;

        } else if ("重大隐患".equals(yhdj) && sfsh.equals("不合格")) { //8  重新整改
            statusImage.setVisibility(View.VISIBLE);
            statusImage.setImageResource(R.mipmap.ic_status_unqualified);
            flag = 8;
        } else {
            statusImage.setVisibility(View.GONE);
        }

        final int finalFlag = flag;
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type == SafeQualityListFragment.MESSAGE_QUALITY) {
                    jumpForQuality(statusImage.getContext(), finalFlag, item);
                } else {
                    jumpForSafe(statusImage.getContext(), finalFlag, item);
                }
            }
        });

    }

    private void jumpForSafe(Context context, int flag, QualityCheckInfo qualityCheckInfo) {
        switch (flag) {
            case 1: //立即整改-一般隐患
                SafeGenerHiddenDangerOfImmeRectiActivity.startActivity(context, QuaGenerHiddenDangerOfImmeRectiActivity.TYPE_NORMAL, qualityCheckInfo.getId());
                break;
            case 2: //待核查
                SafeCheckDetailsOfToBeAuditedActivity.startActivity(context, qualityCheckInfo.getId());
                break;
            case 3: //合格
                SafeCheckDetailsOfQualifiedActivity.startActivity(context, qualityCheckInfo.getId());
                break;
            case 4: //重新整改
                SafeCheckDetailsOfRectifyActivity.startActivity(context, qualityCheckInfo.getId());
                break;
            case 5: //立即整改-重大隐患
                SafeGenerHiddenDangerOfImmeRectiActivity.startActivity(context, QuaGenerHiddenDangerOfImmeRectiActivity.TYPE_MAJOR, qualityCheckInfo.getId());
                break;
            case 6: //待核查
                SafeCheckDetailsOfToBeAuditedActivity.startActivity(context, qualityCheckInfo.getId());
                break;
            case 7: //合格
                SafeCheckDetailsOfQualifiedActivity.startActivity(context, qualityCheckInfo.getId());
                break;
            case 8: //重新整改
                SafeCheckDetailsOfRectifyActivity.startActivity(context, qualityCheckInfo.getId());
                break;
            default:
                break;
        }
    }

    private void jumpForQuality(Context context, int flag, QualityCheckInfo qualityCheckInfo) {
        switch (flag) {
            case 1: //立即整改-一般隐患
                QuaGenerHiddenDangerOfImmeRectiActivity.startActivity(context, QuaGenerHiddenDangerOfImmeRectiActivity.TYPE_NORMAL, qualityCheckInfo.getId());
                break;
            case 2: //待核查
                QuaCheckDetailsOfToBeAuditedActivity.startActivity(context, qualityCheckInfo.getId());
                break;
            case 3: //合格 一般隐患
                QuaCheckDetailsOfQualifiedActivity.startActivity(context, qualityCheckInfo.getId());
                break;
            case 4: //重新整改
                QuaCheckDetailsOfRectifyActivity.startActivity(context, qualityCheckInfo.getId());
                break;
            case 5: //立即整改-重大隐患
                QuaGenerHiddenDangerOfImmeRectiActivity.startActivity(context, QuaGenerHiddenDangerOfImmeRectiActivity.TYPE_MAJOR, qualityCheckInfo.getId());
                break;
            case 6: //待核查
                QuaCheckDetailsOfToBeAuditedActivity.startActivity(context, qualityCheckInfo.getId());
                break;
            case 7: //合格 重大隐患
                QuaCheckDetailsOfQualifiedActivity.startActivity(context, qualityCheckInfo.getId());
                break;
            case 8: //重新整改
                QuaCheckDetailsOfRectifyActivity.startActivity(context, qualityCheckInfo.getId());
                break;
            default:
                break;
        }
    }


}
