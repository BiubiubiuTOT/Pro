package com.ljkj.qxn.wisdomsitepro.ui.application;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.contract.application.PersonArchiveContact;
import com.ljkj.qxn.wisdomsitepro.data.entity.AttendManagerTeamInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.TeamPersonInfo;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.model.ApplicationModel;
import com.ljkj.qxn.wisdomsitepro.presenter.application.PersonArchivePresenter;
import com.ljkj.qxn.wisdomsitepro.ui.application.adapter.AttendManagerAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseActivity;

/**
 * 类描述：考勤管理
 * 创建人：lxx
 * 创建时间：2018/7/18
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class AttendManagerActivity extends BaseActivity implements PersonArchiveContact.View {

    @BindView(R.id.tv_title)
    TextView titleText;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    @BindView(R.id.ll_no_data)
    ViewGroup noDataLayout;

    private AttendManagerAdapter attendManagerAdapter;
    private PersonArchivePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attend_manager);
    }

    @Override
    protected void initUI() {
        titleText.setText("考勤管理");
        refreshLayout.setEnableLoadMore(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void initData() {
        presenter = new PersonArchivePresenter(this, ApplicationModel.newInstance());
        addPresenter(presenter);
        recyclerView.setAdapter(attendManagerAdapter = new AttendManagerAdapter(null));

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                presenter.getTeamAttendanceList(UserManager.getInstance().getProjectId());
            }
        });

        attendManagerAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                AttendManagerTeamInfo info = attendManagerAdapter.getItem(position);
                if (info != null) {
                    AttendManageListActivity.startActivity(AttendManagerActivity.this, info.getTeams());
                }
            }
        });

        refreshLayout.autoRefresh();
    }

    @OnClick({R.id.tv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void hideProgress() {
        super.hideProgress();
        noDataLayout.setVisibility(attendManagerAdapter.getItemCount() > 0 ? View.GONE : View.VISIBLE);
        refreshLayout.finishRefresh();
    }

    @Override
    public void showTeamPersonList(List<TeamPersonInfo> personArchiveAndAttendInfo) {


    }

    @Override
    public void showTeamAttendanceList(List<AttendManagerTeamInfo> info) {
        attendManagerAdapter.setNewData(info);
    }


}
