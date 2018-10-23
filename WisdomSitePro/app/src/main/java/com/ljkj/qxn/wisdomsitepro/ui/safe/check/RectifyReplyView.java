package com.ljkj.qxn.wisdomsitepro.ui.safe.check;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.data.FileEntity;
import com.ljkj.qxn.wisdomsitepro.data.FileType;
import com.ljkj.qxn.wisdomsitepro.data.entity.CheckDetail;
import com.ljkj.qxn.wisdomsitepro.ui.common.adapter.ShowImageAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 类描述：检查-整改回复展示View
 * 创建人：lxx
 * 创建时间：2018/9/7
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class RectifyReplyView extends FrameLayout {

    @BindView(R.id.tv_reply_title)
    TextView replyTitleText;

    @BindView(R.id.tv_recall)
    TextView recallText;

    @BindView(R.id.tv_fold)
    TextView foldText;

    @BindView(R.id.ll_content)
    LinearLayout contentLayout;

    @BindView(R.id.tv_rectify_info)
    TextView rectifyInfoText;

    @BindView(R.id.rl_rectify)
    RecyclerView rectifyRV;

    @BindView(R.id.rl_report)
    RecyclerView reportRV;

    @BindView(R.id.tv_plan)
    TextView planText;

    @BindView(R.id.tv_plan_title)
    TextView planTitleText;

    @BindView(R.id.rl_plan)
    RecyclerView planRV;

    @BindView(R.id.tv_measure)
    TextView measureText;

    @BindView(R.id.tv_measure_title)
    TextView measureTitleText;

    @BindView(R.id.rl_measure)
    RecyclerView measureRV;

    @BindView(R.id.tv_money)
    TextView moneyText;

    @BindView(R.id.tv_money_title)
    TextView moneyTitleText;

    @BindView(R.id.rl_money)
    RecyclerView moneyRV;

    @BindView(R.id.tv_reason)
    TextView reasonText;

    @BindView(R.id.reply_progress_view)
    RectifyReplyProgressView replyProgressView;

    private ShowImageAdapter rectifyImageAdapter;
    private ShowImageAdapter reportImageAdapter;
    private ShowImageAdapter planImageAdapter;
    private ShowImageAdapter measureImageAdapter;
    private ShowImageAdapter moneyImageAdapter;

    private boolean isExpand = true;
    private SparseArray<String> timesArray = new SparseArray<>();

    public RectifyReplyView(@NonNull Context context) {
        this(context, null);
    }

    public RectifyReplyView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        View view = inflate(context, R.layout.view_rectify_reply, this);
        ButterKnife.bind(this, view);

        initView();
        initListener();
        initData();
    }

    private void initView() {
        rectifyRV.setLayoutManager(new GridLayoutManager(getContext(), 4));
        rectifyRV.setAdapter(rectifyImageAdapter = new ShowImageAdapter(getContext()));
        rectifyRV.setNestedScrollingEnabled(false);

        reportRV.setLayoutManager(new GridLayoutManager(getContext(), 4));
        reportRV.setAdapter(reportImageAdapter = new ShowImageAdapter(getContext()));
        reportRV.setNestedScrollingEnabled(false);

        planRV.setLayoutManager(new GridLayoutManager(getContext(), 4));
        planRV.setAdapter(planImageAdapter = new ShowImageAdapter(getContext()));
        planRV.setNestedScrollingEnabled(false);

        measureRV.setLayoutManager(new GridLayoutManager(getContext(), 4));
        measureRV.setAdapter(measureImageAdapter = new ShowImageAdapter(getContext()));
        measureRV.setNestedScrollingEnabled(false);

        moneyRV.setLayoutManager(new GridLayoutManager(getContext(), 4));
        moneyRV.setAdapter(moneyImageAdapter = new ShowImageAdapter(getContext()));
        moneyRV.setNestedScrollingEnabled(false);
    }

    private void initData() {
        timesArray.put(1, "整改回复（第一次）");
        timesArray.put(2, "整改回复（第二次）");
        timesArray.put(3, "整改回复（第三次）");
        timesArray.put(4, "整改回复（第四次）");
        timesArray.put(5, "整改回复（第五次）");
        timesArray.put(6, "整改回复（第六次）");
        timesArray.put(7, "整改回复（第七次）");
        timesArray.put(8, "整改回复（第八次）");
        timesArray.put(9, "整改回复（第九次）");
        timesArray.put(10, "整改回复（第十次）");
        timesArray.put(11, "整改回复（第十一次）");
        timesArray.put(12, "整改回复（第十二次）");
    }

    private void initListener() {
        foldText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                foldText.setCompoundDrawablesWithIntrinsicBounds(0, 0, isExpand ? R.mipmap.ic_check_arrow_down : R.mipmap.ic_check_arrow_up, 0);
                foldText.setText(isExpand ? "展开" : "折叠");
                contentLayout.setVisibility(isExpand ? View.GONE : View.VISIBLE);
                isExpand = !isExpand;
            }
        });
    }

    public void showFiles(List<FileEntity> fileEntities) {
        if (fileEntities == null || fileEntities.size() == 0) {
            return;
        }
        List<FileEntity> rectifyList = new ArrayList<>();
        List<FileEntity> reportList = new ArrayList<>();
        List<FileEntity> planList = new ArrayList<>();
        List<FileEntity> measureList = new ArrayList<>();
        List<FileEntity> moneyList = new ArrayList<>();
        for (FileEntity entity : fileEntities) {
            if (TextUtils.equals(entity.type, FileType.SafeCheck.TYPE_SAFE_CHECK_RECTIFY)) {
                rectifyList.add(getInfo(entity.fileId));
            } else if (TextUtils.equals(entity.type, FileType.SafeCheck.TYPE_SAFE_CHECK_REPORT)) {
                reportList.add(getInfo(entity.fileId));
            } else if (TextUtils.equals(entity.type, FileType.SafeCheck.TYPE_SAFE_CHECK_PLAN)) {
                planList.add(getInfo(entity.fileId));
            } else if (TextUtils.equals(entity.type, FileType.SafeCheck.TYPE_SAFE_CHECK_MEASURE)) {
                measureList.add(getInfo(entity.fileId));
            } else if (TextUtils.equals(entity.type, FileType.SafeCheck.TYPE_SAFE_CHECK_MONEY)) {
                moneyList.add(getInfo(entity.fileId));
            }
        }
        rectifyImageAdapter.loadData(rectifyList);
        reportImageAdapter.loadData(reportList);
        planImageAdapter.loadData(planList);
        measureImageAdapter.loadData(measureList);
        moneyImageAdapter.loadData(moneyList);
    }

    private FileEntity getInfo(String fileId) {
        FileEntity enclosureInfo = new FileEntity();
        enclosureInfo.setFileId(fileId);
        return enclosureInfo;
    }

    public void collapse() {
        isExpand = false;
        foldText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.ic_check_arrow_down, 0);
        foldText.setText("展开");
        contentLayout.setVisibility(View.GONE);
    }

    public void setReasonText(CheckDetail.Reform reform) {
        int status = reform.reformStatus;
        if (status == CheckDetail.Reform.REFORM_STATUS2) {
            reasonText.setText("监理审核不通过");
        } else if (status == CheckDetail.Reform.REFORM_STATUS4) {
            reasonText.setText("监督机构审核不通过");
        } else {
            reasonText.setText("");
        }
    }

    public void setData(CheckDetail.Check check, CheckDetail.Reform reform, int times) {
        replyTitleText.setText(timesArray.get(times) == null ? "整改回复" + times : timesArray.get(times));
        rectifyInfoText.setText(reform.reformInfo);
        if (reform.isEncatPlan.equals("1")) {
            planTitleText.setVisibility(View.VISIBLE);
            planRV.setVisibility(View.VISIBLE);
            planText.setText("是否定制预案：是");
        } else {
            planTitleText.setVisibility(View.GONE);
            planRV.setVisibility(View.GONE);
            planText.setText("是否定制预案：否");
        }
        if (reform.isEncatFunc.equals("1")) {
            measureTitleText.setVisibility(View.VISIBLE);
            measureRV.setVisibility(View.VISIBLE);
            measureText.setText("是否定制措施：是");
        } else {
            measureTitleText.setVisibility(View.GONE);
            measureRV.setVisibility(View.GONE);
            measureText.setText("是否定制措施：否");
        }
        moneyText.setText(TextUtils.isEmpty(reform.reformMoney) ? "整改资金金额：无" : "整改资金金额：" + reform.reformMoney + "万");
        replyProgressView.setData(check, reform);
    }

    public void setOnRecallListener(OnClickListener listener) {
        recallText.setOnClickListener(listener);
    }

    public void setRecallTextVisibility(boolean visibility) {
        recallText.setVisibility(visibility ? View.VISIBLE : View.GONE);
    }

}
