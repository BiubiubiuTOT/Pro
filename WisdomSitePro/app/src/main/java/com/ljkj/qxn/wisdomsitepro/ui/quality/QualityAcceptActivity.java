package com.ljkj.qxn.wisdomsitepro.ui.quality;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.contract.quality.QualityAcceptContract;
import com.ljkj.qxn.wisdomsitepro.data.Consts;
import com.ljkj.qxn.wisdomsitepro.data.entity.PageInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.QualityAcceptInfo;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.model.QualityModel;
import com.ljkj.qxn.wisdomsitepro.presenter.quality.QualityAcceptPresenter;
import com.ljkj.qxn.wisdomsitepro.ui.quality.adapter.QualityAcceptAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseActivity;

/**
 * 类描述：质量验收
 * 创建人：liufei
 * 创建时间：2018/2/3 11:51
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class QualityAcceptActivity extends BaseActivity implements QualityAcceptContract.View {

    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    @BindView(R.id.ll_no_data)
    ViewGroup noDataLayout;

    private QualityAcceptAdapter adapter;

    private QualityAcceptPresenter presenter;

    private int loadType;
    private int page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_title_white_recycleview);
        ButterKnife.bind(this);
    }

    @Override
    protected void initUI() {
        tvTitle.setText("质量验收");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter = new QualityAcceptAdapter(this));

        initListener();
    }

    @Override
    protected void initData() {
        presenter = new QualityAcceptPresenter(this, QualityModel.newInstance());
        addPresenter(presenter);
        refreshLayout.autoRefresh();
    }

    private void initListener() {
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                loadType = Consts.XR_LOAD_TYPE.REFRESH;
                presenter.getQualityAcceptList(1, UserManager.getInstance().getProjectId(), Consts.APPParams.ROWS);
            }
        });

        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                loadType = Consts.XR_LOAD_TYPE.LOAD_MORE;
                presenter.getQualityAcceptList(page + 1, UserManager.getInstance().getProjectId(), Consts.APPParams.ROWS);
            }
        });
    }

    @Override
    public void hideProgress() {
        super.hideProgress();
        if (loadType == Consts.XR_LOAD_TYPE.REFRESH) {
            refreshLayout.finishRefresh();
        } else {
            refreshLayout.finishLoadmore();
        }
        noDataLayout.setVisibility(adapter.getItemCount() > 0 ? View.GONE : View.VISIBLE);
    }

    @OnClick(R.id.tv_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void showQualityAcceptInfo(PageInfo<QualityAcceptInfo> pageInfo) {

        if (loadType == Consts.XR_LOAD_TYPE.REFRESH) {
            adapter.loadData(pageInfo.getList());
            page = 2;
            refreshLayout.setEnableLoadMore(true);
        } else {
            adapter.insertData(adapter.getItemCount(), pageInfo.getList());
            ++page;
        }
        if (pageInfo.getTotal() <= adapter.getItemCount()) {
            refreshLayout.setEnableLoadMore(false);
        }
    }
}
