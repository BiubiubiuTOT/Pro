package com.ljkj.qxn.wisdomsitepro.ui.quality;

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
import com.ljkj.qxn.wisdomsitepro.ui.safe.check.CheckListActivity;
import com.ljkj.qxn.wisdomsitepro.ui.safe.check.SupervisorCheckListActivity;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseFragment;

/**
 * 类描述：质量管理
 * 创建人：liufei
 * 创建时间：2018/1/31 15:28
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class QualityFragment extends BaseFragment {
    public static final String TAG = QualityFragment.class.getName();

    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.item_quality_check)
    ItemView qualityCheckImage;

    @BindView(R.id.item_quality_patrol)
    ImageView qualityPatrolImage;

    @BindView(R.id.item_quality_hidden_account)
    ItemView qualityHiddenAccountItem;

    @BindView(R.id.item_quality_log)
    ImageView qualityLogItem;

    @BindView(R.id.item_quality_system)
    ItemView qualitySystemItem;

    @BindView(R.id.item_quality_concrete_info)
    ItemView qualityConcreteInfoItem;

    @BindView(R.id.item_quality_statistics)
    ItemView qualityStatisticsItem;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quality, container, false);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void initUI() {
        tvTitle.setText("质量管理");
    }

    @Override
    protected void handleAuthority() {
        AuthorityManager manager = AuthorityManager.getInstance();
        manager.handleViewByAuthority(qualityCheckImage, AuthorityId.QualityManage.QUALITY_CHECK);
        manager.handleViewByAuthority(qualityPatrolImage, AuthorityId.QualityManage.QUALITY_INSPECTION);
        manager.handleViewByAuthority(qualityHiddenAccountItem, AuthorityId.QualityManage.QUALITY_DANGER);
        manager.handleViewByAuthority(qualityLogItem, AuthorityId.QualityManage.QUALITY_LOG);
        manager.handleViewByAuthority(qualitySystemItem, AuthorityId.QualityManage.QUALITY_SYSTEM);
        manager.handleViewByAuthority(qualityConcreteInfoItem, AuthorityId.QualityManage.QUALITY_CONCRETE);
        manager.handleViewByAuthority(qualityStatisticsItem, AuthorityId.QualityManage.QUALITY_STATISTICS);
    }

    @Override
    protected void initData() {
    }

    @OnClick({R.id.item_quality_check, R.id.item_quality_patrol, R.id.item_quality_system, R.id.item_quality_hidden_account,
            R.id.item_quality_concrete_info, R.id.item_quality_accept, R.id.item_quality_statistics, R.id.item_quality_log})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.item_quality_check: //质量检查
                if (UserManager.getInstance().isSupervisor()) {
                    SupervisorCheckListActivity.startActivity(mContext, SupervisorCheckListActivity.TYPE_SUPERVISOR_QUALITY_CHECK);
                } else {
                    CheckListActivity.startActivity(mContext, CheckListActivity.TYPE_QUALITY_CHECK);
                }
                break;
            case R.id.item_quality_patrol: //质量巡检
                startActivity(new Intent(mContext, QualityInspectionListV2Activity.class));
                break;
            case R.id.item_quality_system: //质量体系
                startActivity(new Intent(mContext, QualityManageSystemActivity.class));
                break;
            case R.id.item_quality_concrete_info: //砼质量信息
                startActivity(new Intent(mContext, ConcreteQualityInfoActivity.class));
                break;
            case R.id.item_quality_accept: //质量验收
                startActivity(new Intent(mContext, QualityAcceptActivity.class));
                break;
            case R.id.item_quality_statistics: //质量统计
                H5Helper.toQualitySafeStatistics(mContext, "质量统计", UserManager.getInstance().getProjectId(), 2);
                break;
            case R.id.item_quality_log: //质量日志
                SafeQualityLogActivity.startActivity(getContext(), SafeQualityLogActivity.QUALITY_LOG);
                break;
            case R.id.item_quality_hidden_account: //质量隐患台账
                startActivity(new Intent(mContext, QualityHiddenAccountActivity.class));
                break;
            default:
                break;
        }
    }

}
