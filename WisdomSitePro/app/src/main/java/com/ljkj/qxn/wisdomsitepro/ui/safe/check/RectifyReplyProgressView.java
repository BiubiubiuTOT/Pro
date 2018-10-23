package com.ljkj.qxn.wisdomsitepro.ui.safe.check;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.data.entity.CheckDetail;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 类描述：检查-整改回复进度View
 * 创建人：lxx
 * 创建时间：2018/9/7
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class RectifyReplyProgressView extends FrameLayout {

    @BindView(R.id.ll_supervisor)
    LinearLayout supervisorLayout; //监理

    @BindView(R.id.ll_supervise)
    LinearLayout superviseLayout; //监督机构

    @BindView(R.id.tv_supervisor_state)
    TextView supervisorStateText;

    @BindView(R.id.tv_supervisor_advice)
    TextView supervisorAdviceText;

    @BindView(R.id.tv_supervise_state)
    TextView superviseStateText;

    @BindView(R.id.tv_supervise_advice)
    TextView superviseAdviceText;

    @BindView(R.id.item_progress1)
    RectifyProgressItemView progressItemView1;

    @BindView(R.id.item_progress2)
    RectifyProgressItemView progressItemView2;

    @BindView(R.id.item_progress3)
    RectifyProgressItemView progressItemView3;

    @BindView(R.id.item_progress4)
    RectifyProgressItemView progressItemView4;

    public RectifyReplyProgressView(@NonNull Context context) {
        this(context, null);
    }

    public RectifyReplyProgressView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        View view = inflate(context, R.layout.view_rectify_reply_progress, this);
        ButterKnife.bind(this, view);

        initView();
    }

    private void initView() {

    }

    public void setData(CheckDetail.Check check, CheckDetail.Reform reform) {
        progressItemView1.setData(check.checkerName, check.checkDate, R.drawable.shape_rectify_progress_normal, "下发整改通知书", true, false);
        progressItemView2.setData(reform.reformer, reform.createTime, R.drawable.shape_rectify_progress_normal, "整改完成", false, false);

        supervisorAdviceText.setText(reform.supervisorHandleInfo);
        superviseAdviceText.setText(reform.govAuditInfo);
        int status = reform.reformStatus;
        switch (status) {
            case CheckDetail.Reform.REFORM_STATUS1: //等待监理审核
                supervisorLayout.setVisibility(View.GONE);
                superviseLayout.setVisibility(View.GONE);
                progressItemView3.setData("--", "--", R.drawable.shape_rectify_progress_wait, "等待监理审核", false, true);
                progressItemView4.setVisibility(View.GONE);
                break;
            case CheckDetail.Reform.REFORM_STATUS2: //监理审核不合格
                supervisorStateText.setText("审核不通过");
                superviseLayout.setVisibility(View.GONE);
                supervisorStateText.setTextColor(Color.parseColor("#F42E14"));
                supervisorStateText.setBackgroundResource(R.drawable.shape_check_verify_no_pass);
                progressItemView3.setData(reform.supervisorHandler, reform.supervisorHandleDate, R.drawable.shape_rectify_progress_no_pass, "监理审核不通过", false, true);
                progressItemView4.setVisibility(View.GONE);
                break;
            case CheckDetail.Reform.REFORM_STATUS3: //监理审核通过,等待政府部门审核
                supervisorStateText.setText("审核通过");
                supervisorStateText.setTextColor(Color.parseColor("#37C171"));
                supervisorStateText.setBackgroundResource(R.drawable.shape_check_verify_pass);
                progressItemView3.setData(reform.supervisorHandler, reform.supervisorHandleDate, R.drawable.shape_rectify_progress_pass, "监理审核通过", false, false);
                progressItemView4.setData("--", "--", R.drawable.shape_rectify_progress_wait, "等待监督机构审核", false, true);
                superviseLayout.setVisibility(View.GONE);
                break;
            case CheckDetail.Reform.REFORM_STATUS4: //政府部门 审核不合格
                supervisorStateText.setText("审核通过");
                supervisorStateText.setTextColor(Color.parseColor("#37C171"));
                supervisorStateText.setBackgroundResource(R.drawable.shape_check_verify_pass);
                superviseStateText.setText("审核不通过");
                superviseStateText.setTextColor(Color.parseColor("#F42E14"));
                superviseStateText.setBackgroundResource(R.drawable.shape_check_verify_no_pass);
                progressItemView3.setData(reform.supervisorHandler, reform.supervisorHandleDate, R.drawable.shape_rectify_progress_pass, "监理审核通过", false, false);
                progressItemView4.setData(reform.govAuditor, reform.govAuditDate, R.drawable.shape_rectify_progress_no_pass, "监督机构审核不通过", false, true);
                break;
            case CheckDetail.Reform.REFORM_STATUS5: //政府部门 审核合格
                supervisorStateText.setText("审核通过");
                supervisorStateText.setTextColor(Color.parseColor("#37C171"));
                supervisorStateText.setBackgroundResource(R.drawable.shape_check_verify_pass);
                superviseStateText.setText("审核通过");
                superviseStateText.setTextColor(Color.parseColor("#37C171"));
                superviseStateText.setBackgroundResource(R.drawable.shape_check_verify_pass);
                progressItemView3.setData(reform.supervisorHandler, reform.supervisorHandleDate, R.drawable.shape_rectify_progress_pass, "监理审核通过", false, false);
                progressItemView4.setData(reform.govAuditor, reform.govAuditDate, R.drawable.shape_rectify_progress_pass, "监督机构审核通过", false, true);
                break;
            default:
                break;
        }

    }


}
