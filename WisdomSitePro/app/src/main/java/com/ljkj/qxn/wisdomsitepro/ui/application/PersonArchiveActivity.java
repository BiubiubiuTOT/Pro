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
import com.ljkj.qxn.wisdomsitepro.ui.application.adapter.PersonArchiveAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseActivity;

/**
 * 类描述：人员档案
 * 创建人：lxx
 * 创建时间：2018/7/2
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class PersonArchiveActivity extends BaseActivity implements PersonArchiveContact.View {

    @BindView(R.id.tv_title)
    TextView titleText;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    @BindView(R.id.ll_no_data)
    ViewGroup noDataLayout;

    private PersonArchiveAdapter adapter;
    private PersonArchivePresenter personArchivePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_archive);
    }

    @Override
    protected void initUI() {
        titleText.setText("人员档案");
        refreshLayout.setEnableLoadMore(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void initData() {
        personArchivePresenter = new PersonArchivePresenter(this, ApplicationModel.newInstance());
        addPresenter(personArchivePresenter);
        recyclerView.setAdapter(adapter = new PersonArchiveAdapter(null));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                personArchivePresenter.getTeamPersonList(UserManager.getInstance().getProjectId());
            }
        });

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                TeamPersonInfo info = (TeamPersonInfo) adapter.getItem(position);
                if (info == null) {
                    return;
                }
                LaborPersonFileListActivity.startActivity(PersonArchiveActivity.this, info.getTeams());
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
    public void showTeamPersonList(List<TeamPersonInfo> personArchiveAndAttendInfo) {
        adapter.setNewData(personArchiveAndAttendInfo);
    }

    @Override
    public void showTeamAttendanceList(List<AttendManagerTeamInfo> info) {

    }


    @Override
    public void hideProgress() {
        super.hideProgress();
        noDataLayout.setVisibility(adapter.getItemCount() > 0 ? View.GONE : View.VISIBLE);
        refreshLayout.finishRefresh();
    }

}
