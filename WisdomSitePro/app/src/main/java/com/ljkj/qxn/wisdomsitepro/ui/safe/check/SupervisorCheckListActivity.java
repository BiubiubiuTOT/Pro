package com.ljkj.qxn.wisdomsitepro.ui.safe.check;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ljkj.qxn.wisdomsitepro.contract.safe.SupervisorCheckListContract;
import com.ljkj.qxn.wisdomsitepro.data.Consts;
import com.ljkj.qxn.wisdomsitepro.data.entity.CheckInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.PageInfo;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.model.SafeModel;
import com.ljkj.qxn.wisdomsitepro.presenter.safe.SupervisorCheckListPresenter;
import com.ljkj.qxn.wisdomsitepro.ui.safe.adapter.CheckListAdapter2;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

/**
 * 类描述：监理检查列表
 * 创建人：lxx
 * 创建时间：2018/9/13
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SupervisorCheckListActivity extends CheckListActivity implements SupervisorCheckListContract.View {
    /** 监理质量检查 */
    public static final int TYPE_SUPERVISOR_QUALITY_CHECK = 3;
    /** 监理安全检查 */
    public static final int TYPE_SUPERVISOR_SAFE_CHECK = 4;
    private SupervisorCheckListPresenter checkListPresenter;

    private CheckListAdapter2 checkListAdapter;

    public static void startActivity(Context context, int checkType) {
        Intent intent = new Intent(context, SupervisorCheckListActivity.class);
        intent.putExtra("checkType", checkType);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initUI() {
        super.initUI();
        titleText.setText(checkType == TYPE_SUPERVISOR_SAFE_CHECK ? "安全检查" : "质量检查");
    }

    @Override
    protected void initData() {
        checkListPresenter = new SupervisorCheckListPresenter(this, SafeModel.newInstance());
        addPresenter(checkListPresenter);
        recyclerView.setAdapter(checkListAdapter = new CheckListAdapter2(null, true));
        initListener();
        refreshLayout.autoRefresh();
    }

    private void initListener() {
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                loadType = Consts.XR_LOAD_TYPE.REFRESH;
                checkListPresenter.getSupervisorCheckList(UserManager.getInstance().getProjectId(), checkType == TYPE_SUPERVISOR_SAFE_CHECK ? 1 : 2, 1, 10, dangerLevel, rectifyType, checkDate);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                loadType = Consts.XR_LOAD_TYPE.LOAD_MORE;
                checkListPresenter.getSupervisorCheckList(UserManager.getInstance().getProjectId(), checkType, page, 10, dangerLevel, rectifyType, checkDate);
            }
        });
        checkListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                CheckInfo checkInfo = checkListAdapter.getItem(position);
                if (checkInfo == null) {
                    return;
                }
                int status = checkInfo.handleType;
                if (status == 0) { //待监理自己处理
                    CheckDetailActivity.startActivity(SupervisorCheckListActivity.this, checkType, checkInfo.id, CheckInfo.WAIT_AUDITED, true);
                } else if (status == 1) { //待监督机构核查
                    CheckDetailActivity.startActivity(SupervisorCheckListActivity.this, checkType, checkInfo.id, CheckInfo.WAIT_AUDITED, false);
                } else if (status == 2) { //合格
                    CheckDetailActivity.startActivity(SupervisorCheckListActivity.this, checkType, checkInfo.id, CheckInfo.QUALIFIED, false);
                } else if (status == 3) { //待处理（待项目方重新整改）
                    CheckDetailActivity.startActivity(SupervisorCheckListActivity.this, checkType, checkInfo.id, CheckInfo.WAIT_AUDITED, false);
                }

            }
        });
    }

    @Override
    public void showSupervisorCheckList(PageInfo<CheckInfo> datas) {
        if (loadType == Consts.XR_LOAD_TYPE.REFRESH) {
            checkListAdapter.setNewData(datas.getList());
            page = 2;
        } else {
            checkListAdapter.addData(datas.getList());
            ++page;
        }
        refreshLayout.setEnableLoadMore(datas.getTotal() > checkListAdapter.getItemCount());
    }

}
