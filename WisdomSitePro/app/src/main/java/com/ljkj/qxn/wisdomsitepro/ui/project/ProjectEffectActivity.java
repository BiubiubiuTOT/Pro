package com.ljkj.qxn.wisdomsitepro.ui.project;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.contract.common.QueryFileContract;
import com.ljkj.qxn.wisdomsitepro.contract.project.ProjectEffectContract;
import com.ljkj.qxn.wisdomsitepro.data.FileEntity;
import com.ljkj.qxn.wisdomsitepro.data.entity.ProjectEffectInfo;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.model.CommonModel;
import com.ljkj.qxn.wisdomsitepro.model.ProjectModel;
import com.ljkj.qxn.wisdomsitepro.presenter.common.QueryFilePresenter;
import com.ljkj.qxn.wisdomsitepro.presenter.project.ProjectEffectPresenter;
import com.ljkj.qxn.wisdomsitepro.ui.project.adapter.ConstructionSiteAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseActivity;
import cdsp.android.ui.widget.DividerGridItemDecoration;

/**
 * 类描述：工程效果图
 * 创建人：liufei
 * 创建时间：2018/2/1 15:20
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class ProjectEffectActivity extends BaseActivity implements QueryFileContract.View, ProjectEffectContract.View {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.ll_no_data)
    ViewGroup llNoData;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private ConstructionSiteAdapter adapter;
    private QueryFilePresenter listFilePresenter;
    private ProjectEffectPresenter projectEffectPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_construction_site);
        ButterKnife.bind(this);
    }

    @Override
    protected void initUI() {
        tvTitle.setText("工程效果图");
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        recyclerView.addItemDecoration(new DividerGridItemDecoration(this, ContextCompat.getDrawable(this, R.drawable.divider_of_construction_site)));
        recyclerView.setAdapter(adapter = new ConstructionSiteAdapter(this));
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setEnableRefresh(false);
    }

    @Override
    protected void initData() {
        listFilePresenter = new QueryFilePresenter(this, CommonModel.newInstance());
        projectEffectPresenter = new ProjectEffectPresenter(this, ProjectModel.newInstance());
        addPresenter(projectEffectPresenter);
        addPresenter(listFilePresenter);

        projectEffectPresenter.getProjectEffect(UserManager.getInstance().getProjectId());
    }

    @OnClick(R.id.tv_back)
    public void onViewClicked() {
        finish();
    }


    @Override
    public void showFiles(List<FileEntity> data) {
        llNoData.setVisibility(data.size() > 0 ? View.GONE : View.VISIBLE);
        adapter.loadData(data);
    }

    @Override
    public void showProjectEffect(ProjectEffectInfo data) {
        if (data != null)
            listFilePresenter.queryFile(data.getId());
    }
}
