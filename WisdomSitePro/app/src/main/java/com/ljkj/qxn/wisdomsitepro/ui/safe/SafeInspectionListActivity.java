package com.ljkj.qxn.wisdomsitepro.ui.safe;

import android.view.View;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.data.Consts;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.ui.common.BaseInspectionDetailsActivity2;
import com.ljkj.qxn.wisdomsitepro.ui.common.BaseInspectionListActivity;
import com.ljkj.qxn.wisdomsitepro.ui.common.InspectionAddActivity;
import com.ljkj.qxn.wisdomsitepro.ui.common.InspectionDetailsActivity2;
import com.ljkj.qxn.wisdomsitepro.ui.common.InspectionDetailsOfImmeApprovalActivity2;
import com.ljkj.qxn.wisdomsitepro.ui.common.InspectionDetailsOfImmeRectiActivity2;
import com.ljkj.qxn.wisdomsitepro.ui.common.InspectionDetailsOfQualified2;
import com.ljkj.qxn.wisdomsitepro.ui.common.InspectionDetailsOfQualifiedActivity2;
import com.ljkj.qxn.wisdomsitepro.ui.common.InspectionDetailsOfTobeAuditedActivity2;
import com.ljkj.qxn.wisdomsitepro.ui.common.adapter.InspectionListAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.OnClick;

/**
 * 类描述：安全巡检
 * 创建人：liufei
 * 创建时间：2018/2/7 11:57
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SafeInspectionListActivity extends BaseInspectionListActivity {

    @Override
    protected void initUI() {
        tvTitle.setText("安全巡检");
        super.initUI();
    }

    @Override
    protected void initListener() {

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                loadType = Consts.XR_LOAD_TYPE.REFRESH;
                presenter.getPatrolList(UserManager.getInstance().getProjectId(), SAFE_TYPE, whyType, zgbz, jcrqTime, isDealWith, Consts.APPParams.ROWS, 1);
            }
        });

        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                loadType = Consts.XR_LOAD_TYPE.LOAD_MORE;
                presenter.getPatrolList(UserManager.getInstance().getProjectId(), SAFE_TYPE, whyType, zgbz, jcrqTime, isDealWith, Consts.APPParams.ROWS, page);
            }
        });


        adapter.setOnItemClickListener(new InspectionListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, int flag) {

                switch (flag) {
                    case 0://查看详情 :对于创建人，无操作，仅查看
                        BaseInspectionDetailsActivity2.startActivity(SafeInspectionListActivity.this, adapter.getItem(position).getId(), "巡检详情", InspectionDetailsActivity2.class);
                        break;

                    case 1://立即审批：对于创建人，需要审批班组负责人的整改
                        BaseInspectionDetailsActivity2.startActivity(SafeInspectionListActivity.this, adapter.getItem(position).getId(), "巡检详情-立即审批", InspectionDetailsOfImmeApprovalActivity2.class);
                        break;

                    case 2://立即整改：对于班组负责人，需要提交整改内容
                        BaseInspectionDetailsActivity2.startActivity(SafeInspectionListActivity.this, adapter.getItem(position).getId(), "巡检详情-立即整改", InspectionDetailsOfImmeRectiActivity2.class);

                        break;

                    case 3://重新整改：对于班组负责人，创建人不通过上次整改，需要再次提交整改内容
                        BaseInspectionDetailsActivity2.startActivity(SafeInspectionListActivity.this, adapter.getItem(position).getId(), "巡检详情-重新整改", InspectionDetailsOfQualifiedActivity2.class);
                        break;

                    case 4://合格：对于创建人和班组负责人都行，一次巡检结束
                        BaseInspectionDetailsActivity2.startActivity(SafeInspectionListActivity.this, adapter.getItem(position).getId(), "巡检详情-合格", InspectionDetailsOfQualified2.class);
                        break;

                    case 5://待审核：对于班组负责人，班组负责人已经提交了整改给创建人，等待创建人审核操作
                        BaseInspectionDetailsActivity2.startActivity(SafeInspectionListActivity.this, adapter.getItem(position).getId(), "巡检详情-待核查", InspectionDetailsOfTobeAuditedActivity2.class);

                        break;
                    default:
                        break;
                }
            }
        });

    }

    @OnClick({R.id.tv_right})
    @Override
    public void onViewClicked(View view) {
        super.onViewClicked(view);
        switch (view.getId()) {
            case R.id.tv_right:
                InspectionAddActivity.startActivity(this, SAFE_TYPE);
                break;
        }
    }

    @Override
    protected String getType() {
        return SAFE_TYPE;
    }
}
