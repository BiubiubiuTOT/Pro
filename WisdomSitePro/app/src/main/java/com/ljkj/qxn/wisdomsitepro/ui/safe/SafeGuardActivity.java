package com.ljkj.qxn.wisdomsitepro.ui.safe;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.contract.safe.SafeGuardContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.SafeGuardInfo;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.model.SafeModel;
import com.ljkj.qxn.wisdomsitepro.presenter.safe.SafeGuardPresenter;
import com.ljkj.qxn.wisdomsitepro.ui.safe.adapter.SafeGuardAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseActivity;

/**
 * 类描述：安全防护
 * 创建人：rjf
 * 创建时间：2018/3/13 09:50.
 * 修改人：
 * 修改时间
 * 修改备注
 */

public class SafeGuardActivity extends BaseActivity implements SafeGuardContract.View {

    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tv_project_name)
    TextView tvProjectName;

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    @BindView(R.id.ll_no_data)
    ViewGroup noDataLayout;

    private SafeGuardAdapter adapter;

    private String projectName;

    SafeGuardPresenter presenter;

    private String buildName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safe_guard);
        ButterKnife.bind(this);
    }

    @Override
    protected void initUI() {
        tvTitle.setText("安全防护");
        tvRight.setText("新增楼栋");

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter = new SafeGuardAdapter(this));
        refreshLayout.setEnableLoadMore(false);
    }

    @Override
    protected void initData() {
        presenter = new SafeGuardPresenter(this, SafeModel.newInstance());
        addPresenter(presenter);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                presenter.getSafeGuardInfo(UserManager.getInstance().getProjectId());
            }
        });
        refreshLayout.autoRefresh();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @OnClick({R.id.tv_back, R.id.tv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.tv_right:
                //跳转新增楼栋界面
                SafeGuardAddBuildActivity.startActivity(this,buildName);
                break;
            default:
                break;
        }
    }

    @Override
    public void hideProgress() {
        super.hideProgress();
        refreshLayout.finishRefresh();
        noDataLayout.setVisibility(adapter.getItemCount() > 0 ? View.GONE : View.VISIBLE);
    }

    @Override
    public void showProgress(String message) {
//        super.showProgress(message);
    }

    @Override
    public void showSafeGuardInfo(SafeGuardInfo data) {
        buildName = data.getName();
        adapter.loadData(data.getDongs());
        projectName = data.getName();
        adapter.setProjectName(projectName);
        tvProjectName.setText(projectName);
    }
}
