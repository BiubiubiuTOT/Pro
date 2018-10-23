package com.ljkj.qxn.wisdomsitepro.ui.safe;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.contract.safe.SafeHiddenAccountContract;
import com.ljkj.qxn.wisdomsitepro.data.Consts;
import com.ljkj.qxn.wisdomsitepro.data.entity.PageInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.SafeHiddenAccountInfo;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.model.SafeModel;
import com.ljkj.qxn.wisdomsitepro.presenter.safe.SafeHiddenAccountPresenter;
import com.ljkj.qxn.wisdomsitepro.ui.safe.adapter.SafeHiddenAccountAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;
import cdsp.android.ui.base.BaseFragment;

public class SafeHiddenAccountFragment extends BaseFragment implements SafeHiddenAccountContract.View {
    public static final int ACCOUNT_PRESENT_LEVEL = 1; //本级台账
    public static final int ACCOUNT_PROJECT = 2; //项目台账

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    @BindView(R.id.ll_no_data)
    ViewGroup noDataLayout;

    private int loadType;
    private int page = 1;
    private SafeHiddenAccountAdapter accountAdapter;
    private SafeHiddenAccountPresenter presenter;

    private int accountType;
    private int securityOrQuality; //1:安全 2：质量

    public static SafeHiddenAccountFragment newInstance(int accountType, int securityOrQuality) {
        SafeHiddenAccountFragment fragment = new SafeHiddenAccountFragment();
        Bundle args = new Bundle();
        args.putInt("accountType", accountType);
        args.putInt("securityOrQuality", securityOrQuality);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_recycler_view_list, container, false);
    }

    @Override
    protected void initUI() {
        accountType = getArguments().getInt("accountType");
        securityOrQuality = getArguments().getInt("securityOrQuality");
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        refreshLayout.setEnableLoadMore(false);
    }

    @Override
    protected void initData() {
        presenter = new SafeHiddenAccountPresenter(this, SafeModel.newInstance());
        addPresenter(presenter);
        recyclerView.setAdapter(accountAdapter = new SafeHiddenAccountAdapter(null, accountType));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                loadType = Consts.XR_LOAD_TYPE.REFRESH;
                if (securityOrQuality == 1) {
                    presenter.getSafeHiddenAccountList(UserManager.getInstance().getProjectId(), accountType, 1, 10);
                } else {
                    presenter.getQualityHiddenAccountList(UserManager.getInstance().getProjectId(), accountType, 1, 10);
                }
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                loadType = Consts.XR_LOAD_TYPE.LOAD_MORE;
                if (securityOrQuality == 1) {
                    presenter.getSafeHiddenAccountList(UserManager.getInstance().getProjectId(), accountType, page, 10);
                } else {
                    presenter.getQualityHiddenAccountList(UserManager.getInstance().getProjectId(), accountType, page, 10);
                }
            }
        });
        accountAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                SafeHiddenAccountInfo info = accountAdapter.getItem(position);
                if (info != null) {
                    if (securityOrQuality == 1) {
                        SafeHiddenAccountDetailActivity.startActivity(getContext(), info.getHiddenDetail(), info.getId());
                    } else {
                        SafeHiddenAccountDetailActivity.startActivity(getContext(), info.getHiddenDetail(), info.getId());
                    }

                }
            }
        });
        refreshLayout.autoRefresh();
    }

    @Override
    public void hideProgress() {
        super.hideProgress();
        noDataLayout.setVisibility(recyclerView.getAdapter().getItemCount() > 0 ? View.GONE : View.VISIBLE);
        if (loadType == Consts.XR_LOAD_TYPE.REFRESH) {
            refreshLayout.finishRefresh();
        } else {
            refreshLayout.finishLoadMore();
        }
    }

    @Override
    public void showSafeHiddenAccount(PageInfo<SafeHiddenAccountInfo> datas) {
        if (loadType == Consts.XR_LOAD_TYPE.REFRESH) {
            accountAdapter.setNewData(datas.getList());
            page = 2;
        } else {
            accountAdapter.addData(datas.getList());
            ++page;
        }
        refreshLayout.setEnableLoadMore(datas.getTotal() > accountAdapter.getItemCount());
    }

    @Override
    public void showQualityHiddenAccount(PageInfo<SafeHiddenAccountInfo> datas) {
        if (loadType == Consts.XR_LOAD_TYPE.REFRESH) {
            accountAdapter.setNewData(datas.getList());
            page = 2;
        } else {
            accountAdapter.addData(datas.getList());
            ++page;
        }
        refreshLayout.setEnableLoadMore(datas.getTotal() > accountAdapter.getItemCount());
    }

}
