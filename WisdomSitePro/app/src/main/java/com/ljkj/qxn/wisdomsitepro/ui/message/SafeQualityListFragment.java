package com.ljkj.qxn.wisdomsitepro.ui.message;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.contract.safe.CheckListContract;
import com.ljkj.qxn.wisdomsitepro.data.Consts;
import com.ljkj.qxn.wisdomsitepro.data.entity.CheckInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.PageInfo;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.model.SafeModel;
import com.ljkj.qxn.wisdomsitepro.presenter.safe.CheckListPresenter;
import com.ljkj.qxn.wisdomsitepro.ui.safe.adapter.CheckListAdapter2;
import com.ljkj.qxn.wisdomsitepro.ui.safe.check.CheckDetailActivity;
import com.ljkj.qxn.wisdomsitepro.ui.safe.check.CheckListActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;
import cdsp.android.ui.base.BaseFragment;

/**
 * 类描述：消息：安全/质量
 * 创建人：lxx
 * 创建时间：2018/6/19
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SafeQualityListFragment extends BaseFragment implements CheckListContract.View {
    public static final int MESSAGE_SAFE = 1;
    public static final int MESSAGE_QUALITY = 2;

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.ll_no_data)
    ViewGroup llNoData;

    private int type;
    private CheckListAdapter2 checkListAdapter;
    private CheckListPresenter checkListPresenter;
    protected String dangerLevel = "", rectifyType = "", checkDate = "";
    public int loadType;
    public int page = 1;

    public static SafeQualityListFragment newInstance(int type) {
        SafeQualityListFragment fragment = new SafeQualityListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.type = getArguments().getInt("type", MESSAGE_SAFE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_recycler_view_list, container, false);
    }

    @Override
    protected void initUI() {
        refreshLayout.setEnableLoadMore(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(checkListAdapter = new CheckListAdapter2(null, false));
    }

    @Override
    protected void initData() {
        initListener();
        checkListPresenter = new CheckListPresenter(this, SafeModel.newInstance());
        addPresenter(checkListPresenter);
        refreshLayout.autoRefresh();
    }

    private void initListener() {
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                loadType = Consts.XR_LOAD_TYPE.REFRESH;
                if (type == MESSAGE_SAFE) {
                    checkListPresenter.getSafeCheckList(UserManager.getInstance().getProjectId(), 1, 10, dangerLevel, rectifyType, checkDate, true);
                } else {
                    checkListPresenter.getQualityCheckList(UserManager.getInstance().getProjectId(), 1, 10, dangerLevel, rectifyType, checkDate, true);
                }

            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                loadType = Consts.XR_LOAD_TYPE.LOAD_MORE;
                if (type == MESSAGE_SAFE) {
                    checkListPresenter.getSafeCheckList(UserManager.getInstance().getProjectId(), page, 10, dangerLevel, rectifyType, checkDate, true);
                } else {
                    checkListPresenter.getQualityCheckList(UserManager.getInstance().getProjectId(), page, 10, dangerLevel, rectifyType, checkDate, true);
                }
            }
        });

        checkListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                CheckInfo checkInfo = checkListAdapter.getItem(position);
                if (checkInfo == null) {
                    return;
                }
                int status = checkInfo.reformStatus;
                CheckDetailActivity.startActivity(getContext(), type == MESSAGE_SAFE ? CheckListActivity.TYPE_SAFE_CHECK
                        : CheckListActivity.TYPE_QUALITY_CHECK, checkInfo.id, status, false);
            }
        });
    }

    @Override
    public void showSafeCheckList(PageInfo<CheckInfo> datas) {
        if (loadType == Consts.XR_LOAD_TYPE.REFRESH) {
            checkListAdapter.setNewData(datas.getList());
            page = 2;
        } else {
            checkListAdapter.addData(datas.getList());
            ++page;
        }
        refreshLayout.setEnableLoadMore(datas.getTotal() > checkListAdapter.getItemCount());
    }

    @Override
    public void showQualityCheckList(PageInfo<CheckInfo> datas) {
        if (loadType == Consts.XR_LOAD_TYPE.REFRESH) {
            checkListAdapter.setNewData(datas.getList());
            page = 2;
        } else {
            checkListAdapter.addData(datas.getList());
            ++page;
        }
        refreshLayout.setEnableLoadMore(datas.getTotal() > checkListAdapter.getItemCount());
    }

    @Override
    public void hideProgress() {
        super.hideProgress();
        llNoData.setVisibility(recyclerView.getAdapter().getItemCount() > 0 ? View.GONE : View.VISIBLE);
        if (loadType == Consts.XR_LOAD_TYPE.REFRESH) {
            refreshLayout.finishRefresh();
        } else {
            refreshLayout.finishLoadMore();
        }
    }

}
