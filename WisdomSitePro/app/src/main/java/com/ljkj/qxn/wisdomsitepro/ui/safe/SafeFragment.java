package com.ljkj.qxn.wisdomsitepro.ui.safe;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.Utils.H5Helper;
import com.ljkj.qxn.wisdomsitepro.Utils.widget.ItemView;
import com.ljkj.qxn.wisdomsitepro.data.AuthorityId;
import com.ljkj.qxn.wisdomsitepro.manager.AuthorityManager;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.ui.quality.SafeQualityLogActivity;
import com.ljkj.qxn.wisdomsitepro.ui.safe.check.CheckListActivity;
import com.ljkj.qxn.wisdomsitepro.ui.safe.check.SupervisorCheckListActivity;
import com.ljkj.qxn.wisdomsitepro.ui.safe.protection.SafeProtectionActivity;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseFragment;

public class SafeFragment extends BaseFragment {
    public static final String TAG = SafeFragment.class.getName();

    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.item_safe_check)
    ItemView safeCheckImage;

    @BindView(R.id.item_safe_inspection)
    ImageView safeInspectionImage;

    @BindView(R.id.item_safe_hidden_account)
    ItemView safeHiddenAccountItem;

    @BindView(R.id.item_safe_log)
    ImageView safeLogItem;

    @BindView(R.id.item_safe_system)
    ItemView safeSystemItem;

    @BindView(R.id.item_safe_guard)
    ItemView safeGuardItem;

    @BindView(R.id.item_safe_education)
    ItemView safeEducationItem;

    @BindView(R.id.item_statistics)
    ItemView statisticsItem;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_safe, container, false);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void initUI() {
        tvTitle.setText("安全管理");
    }

    @Override
    protected void handleAuthority() {
        AuthorityManager manager = AuthorityManager.getInstance();
        manager.handleViewByAuthority(safeCheckImage, AuthorityId.SafeManage.SAFE_CHECK);
        manager.handleViewByAuthority(safeInspectionImage, AuthorityId.SafeManage.SAFE_INSPECTION);
        manager.handleViewByAuthority(safeHiddenAccountItem, AuthorityId.SafeManage.SAFE_DANGER);
        manager.handleViewByAuthority(safeLogItem, AuthorityId.SafeManage.SAFE_LOG);
        manager.handleViewByAuthority(safeSystemItem, AuthorityId.SafeManage.SAFE_SYSTEM);
        manager.handleViewByAuthority(safeGuardItem, AuthorityId.SafeManage.SAFE_PROTECT);
        manager.handleViewByAuthority(safeEducationItem, AuthorityId.SafeManage.SAFE_EDU);
        manager.handleViewByAuthority(statisticsItem, AuthorityId.SafeManage.SAFE_STATISTICS);
    }

    @Override
    protected void initData() {
    }

    @OnClick({R.id.item_safe_check, R.id.item_safe_inspection, R.id.item_safe_system, R.id.item_safe_guard,
            R.id.item_safe_education, R.id.item_statistics, R.id.item_safe_log, R.id.item_safe_hidden_account})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.item_safe_check: //安全检查
                if (UserManager.getInstance().isSupervisor()) {
                    SupervisorCheckListActivity.startActivity(mContext, SupervisorCheckListActivity.TYPE_SUPERVISOR_SAFE_CHECK);
                } else {
                    CheckListActivity.startActivity(mContext, CheckListActivity.TYPE_SAFE_CHECK);
                }
                break;
            case R.id.item_safe_inspection: //安全巡检
                startActivity(new Intent(mContext, SafeInspectionListV2Activity.class));
                break;
            case R.id.item_safe_system: //安全体系
                startActivity(new Intent(mContext, SafeSystemActivity.class));
                break;
            case R.id.item_safe_guard: //安全防护
                startActivity(new Intent(mContext, SafeProtectionActivity.class));
                break;
            case R.id.item_safe_education: //安全教育
                startActivity(new Intent(mContext, SafeEducationActivity.class));
                break;
            case R.id.item_statistics: //安全统计
                H5Helper.toQualitySafeStatistics(mContext, "安全统计", UserManager.getInstance().getProjectId(), 1);
                break;
            case R.id.item_safe_log: //安全日志
                SafeQualityLogActivity.startActivity(getContext(), SafeQualityLogActivity.SAFE_LOG);
                break;
            case R.id.item_safe_hidden_account: //安全隐患台账
                startActivity(new Intent(getContext(), SafeHiddenAccountActivity.class));
                break;
            default:
                break;
        }
    }


}
