package com.ljkj.qxn.wisdomsitepro.ui.quality;

import com.ljkj.qxn.wisdomsitepro.data.Consts;
import com.ljkj.qxn.wisdomsitepro.data.entity.QualityCheckInfo;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.ui.common.BaseCheckListActivity;
import com.ljkj.qxn.wisdomsitepro.ui.common.adapter.CheckListAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

/**
 * 类描述：质量检查
 * 创建人：liufei
 * 创建时间：2018/2/3 15:26
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class QualityCheckActivity extends BaseCheckListActivity {

    @Override
    protected void initUI() {
        tvTitle.setText("质量检查");
        super.initUI();
    }

    @Override
    protected void initListener() {
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                loadType = Consts.XR_LOAD_TYPE.REFRESH;
                presenter.getQualityCheckList(1, UserManager.getInstance().getProjectId(), Consts.APPParams.ROWS, QUALITY_TYPE, yhdj, zglx, jcrqTime);
            }
        });

        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                loadType = Consts.XR_LOAD_TYPE.LOAD_MORE;
                presenter.getQualityCheckList(page, UserManager.getInstance().getProjectId(), Consts.APPParams.ROWS, QUALITY_TYPE, yhdj, zglx, jcrqTime);
            }
        });

        adapter.setOnItemClickListener(new CheckListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, int flag) {
                QualityCheckInfo qualityCheckInfo = adapter.getItem(position);
                switch (flag) {
                    case 1: //立即整改-一般隐患
                        QuaGenerHiddenDangerOfImmeRectiActivity.startActivity(QualityCheckActivity.this, QuaGenerHiddenDangerOfImmeRectiActivity.TYPE_NORMAL, qualityCheckInfo.getId());
                        break;
                    case 2: //待核查
                        QuaCheckDetailsOfToBeAuditedActivity.startActivity(QualityCheckActivity.this, qualityCheckInfo.getId());
                        break;
                    case 3: //合格 一般隐患
                        QuaCheckDetailsOfQualifiedActivity.startActivity(QualityCheckActivity.this, qualityCheckInfo.getId());
                        break;
                    case 4: //重新整改
                        QuaCheckDetailsOfRectifyActivity.startActivity(QualityCheckActivity.this, qualityCheckInfo.getId());
                        break;
                    case 5: //立即整改-重大隐患
                        QuaGenerHiddenDangerOfImmeRectiActivity.startActivity(QualityCheckActivity.this, QuaGenerHiddenDangerOfImmeRectiActivity.TYPE_MAJOR, qualityCheckInfo.getId());
                        break;
                    case 6: //待核查
                        QuaCheckDetailsOfToBeAuditedActivity.startActivity(QualityCheckActivity.this, qualityCheckInfo.getId());
                        break;
                    case 7: //合格 重大隐患
                        QuaCheckDetailsOfQualifiedActivity.startActivity(QualityCheckActivity.this, qualityCheckInfo.getId());
                        break;
                    case 8: //重新整改
                        QuaCheckDetailsOfRectifyActivity.startActivity(QualityCheckActivity.this, qualityCheckInfo.getId());
                        break;
                    default:
                        break;
                }
            }
        });


    }

    @Override
    protected String getType() {
        return BaseCheckListActivity.QUALITY_TYPE;
    }
}
