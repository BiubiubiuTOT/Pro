package com.ljkj.qxn.wisdomsitepro.ui.application;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.contract.application.ConstructListLogContract;
import com.ljkj.qxn.wisdomsitepro.data.AuthorityId;
import com.ljkj.qxn.wisdomsitepro.data.Consts;
import com.ljkj.qxn.wisdomsitepro.data.entity.ConstructLogInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.PageInfo;
import com.ljkj.qxn.wisdomsitepro.data.event.RefreshEvent;
import com.ljkj.qxn.wisdomsitepro.manager.AuthorityManager;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.model.ApplicationModel;
import com.ljkj.qxn.wisdomsitepro.presenter.application.ConstructListLogPresenter;
import com.ljkj.qxn.wisdomsitepro.ui.application.adapter.ConstructLogListAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseActivity;
import cdsp.android.ui.base.BaseFragment;

/**
 * 类描述：施工日志
 * 创建人：mhl
 * 创建时间：2018/2/2 10:18
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class ConstructLogListActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.tv_right)
    TextView tvRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_construct_log_list);
    }

    @Override
    protected void initUI() {
        tvTitle.setText("施工日志");
        tvRight.setText("新增");
        tvRight.setVisibility(View.VISIBLE);
    }

    @Override
    protected void handleAuthority() {
        AuthorityManager.getInstance().handleViewByAuthority(tvRight, AuthorityId.DataManage.CONSTRUCT_LOG, true);
    }

    @Override
    protected void initData() {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, ConstructLogListFragment.newInstance())
                .commit();
    }

    @OnClick({R.id.tv_back, R.id.tv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.tv_right:
                startActivity(new Intent(this, ConstructLogAddActivity.class));
                break;
            default:
                break;
        }
    }

    public static class ConstructLogListFragment extends BaseFragment implements ConstructListLogContract.View {

        @BindView(R.id.recyclerView)
        RecyclerView recyclerView;

        @BindView(R.id.refreshLayout)
        SmartRefreshLayout refreshLayout;

        @BindView(R.id.ll_no_data)
        ViewGroup noDataLayout;

        private int loadType;

        private ConstructListLogPresenter presenter;
        private ConstructLogListAdapter adapter;
        private int page;

        public static ConstructLogListFragment newInstance() {
            return new ConstructLogListFragment();
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(R.layout.layout_recycler_view_list, container, false);
        }

        @Override
        protected void initUI() {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(adapter = new ConstructLogListAdapter(getContext()));
            refreshLayout.setEnableLoadMore(false);
            refreshLayout.setOnRefreshListener(new OnRefreshListener() {
                @Override
                public void onRefresh(RefreshLayout refreshlayout) {
                    loadType = Consts.XR_LOAD_TYPE.REFRESH;
                    presenter.constructLogList(UserManager.getInstance().getProjectId(), 1, 10, "", "");
                }
            });

            refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
                @Override
                public void onLoadMore(RefreshLayout refreshlayout) {
                    loadType = Consts.XR_LOAD_TYPE.LOAD_MORE;
                    presenter.constructLogList(UserManager.getInstance().getProjectId(), page, 10, "", "");
                }
            });
        }

        @Override
        protected void initData() {
            presenter = new ConstructListLogPresenter(this, ApplicationModel.newInstance());
            addPresenter(presenter);
            refreshLayout.autoRefresh();
            if (!EventBus.getDefault().isRegistered(this)) {
                EventBus.getDefault().register(this);
            }
        }

        @Override
        public void showPageLogInfo(PageInfo<ConstructLogInfo> data) {
            if (loadType == Consts.XR_LOAD_TYPE.REFRESH) {
                adapter.loadData(data.getList());
                page = 2;
                refreshLayout.setEnableLoadMore(true);
            } else {
                adapter.insertData(adapter.getItemCount(), data.getList());
                ++page;
            }

            if (data.getTotal() <= adapter.getItemCount()) {
                refreshLayout.setEnableLoadMore(false);
            }
        }


        @Override
        public void hideProgress() {
            super.hideProgress();
            if (loadType == Consts.XR_LOAD_TYPE.REFRESH) {
                refreshLayout.finishRefresh();
            } else {
                refreshLayout.finishLoadMore();
            }
            noDataLayout.setVisibility(adapter.getItemCount() > 0 ? View.GONE : View.VISIBLE);
        }

        /** @see ConstructLogAddActivity */
        @Subscribe(threadMode = ThreadMode.MAIN)
        public void onRefreshEvent(RefreshEvent event) {
            refreshLayout.autoRefresh();
        }

        @Override
        public void onDestroy() {
            EventBus.getDefault().unregister(this);
            super.onDestroy();
        }


    }
}
