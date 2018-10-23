package com.ljkj.qxn.wisdomsitepro.ui.quality;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.contract.quality.ConcreteQualityInfoContract;
import com.ljkj.qxn.wisdomsitepro.data.Consts;
import com.ljkj.qxn.wisdomsitepro.data.entity.BaseConcreteInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.ConcreteCompressiveInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.ConcreteEntranceAcceptanceInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.PageInfo;
import com.ljkj.qxn.wisdomsitepro.data.event.InspectionEvent;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.model.ConcreteModel;
import com.ljkj.qxn.wisdomsitepro.presenter.quality.ConcreteQualityInfoPresenter;
import com.ljkj.qxn.wisdomsitepro.ui.quality.adapter.ConcreteQualityInfoAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cdsp.android.ui.BaseRecyclerAdapter;
import cdsp.android.ui.base.BaseFragment;
import cdsp.android.ui.widget.ClearEditTextView;

/**
 * 类描述：混凝土质量信息
 * 创建人：liufei
 * 创建时间：2018/2/7 16:24
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class ConcreteQualityInfoFragment extends BaseFragment implements ConcreteQualityInfoContract.View {

    public static final String TAG = ConcreteQualityInfoFragment.class.getName();

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.ll_no_data)
    ViewGroup noDataLayout;

    @BindView(R.id.tv_search_title)
    TextView searchTitleText;

    @BindView(R.id.edit_search)
    ClearEditTextView searchEt;

    private int type;
    private ConcreteQualityInfoPresenter presenter;
    private ConcreteQualityInfoAdapter adapter;
    private int page = 1;
    private int loadType;
    private String keywords = "";

    public static ConcreteQualityInfoFragment newInstance(int type) {
        ConcreteQualityInfoFragment fragment = new ConcreteQualityInfoFragment();
        Bundle args = new Bundle();
        args.putInt("type", type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_concrete_quality_info, container, false);
        if (getArguments() != null) {
            type = getArguments().getInt("type");
        }
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void initUI() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter = new ConcreteQualityInfoAdapter(getContext(), type));
        if (type == 0) {
            searchTitleText.setText("浇筑部位");
        } else {
            searchTitleText.setText("检验编号");
        }
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initData() {
        presenter = new ConcreteQualityInfoPresenter(this, ConcreteModel.newInstance());
        addPresenter(presenter);
        refreshLayout.autoRefresh();
        initListener();
    }

    private void initListener() {
        adapter.setOnItemViewClickListener(new BaseRecyclerAdapter.OnItemViewClickListener() {
            @Override
            public void onViewClick(View view, int position) {
                if (type == 0) {
                    ConcreteEntranceAcceptanceInfo entranceAcceptanceInfo = (ConcreteEntranceAcceptanceInfo) adapter.getItem(position);
                    SiteAcceptanceActivity.startActivity(getContext(), entranceAcceptanceInfo.getId());
                } else if (type == 1) {
                    ConcreteCompressiveInfo concreteCompressiveInfo = (ConcreteCompressiveInfo) adapter.getItem(position);
                    CompressionTestActivity.startActivity(getContext(), concreteCompressiveInfo.getId());
                } else {
                    ConcreteCompressiveInfo permeabilityInfo = (ConcreteCompressiveInfo) adapter.getItem(position);
                    ImpermeabilityTestActivity.startActivity(getContext(), permeabilityInfo.getId());
                }
            }
        });
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                loadType = Consts.XR_LOAD_TYPE.REFRESH;
                if (type == 0) {
                    presenter.getApproachList(keywords, 1, UserManager.getInstance().getProjectId(), Consts.APPParams.ROWS);
                } else if (type == 1) {
                    presenter.getCompressiveList(keywords, 1, UserManager.getInstance().getProjectId(), Consts.APPParams.ROWS);
                } else {
                    presenter.getPermeabilityList(keywords, 1, UserManager.getInstance().getProjectId(), Consts.APPParams.ROWS);
                }
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                loadType = Consts.XR_LOAD_TYPE.LOAD_MORE;
                if (type == 0) {
                    presenter.getApproachList(keywords, page, UserManager.getInstance().getProjectId(), Consts.APPParams.ROWS);
                } else if (type == 1) {
                    presenter.getCompressiveList(keywords, page, UserManager.getInstance().getProjectId(), Consts.APPParams.ROWS);
                } else {
                    presenter.getPermeabilityList(keywords, page, UserManager.getInstance().getProjectId(), Consts.APPParams.ROWS);
                }
            }
        });

        searchEt.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    keywords = searchEt.getText().toString().trim();
                    refreshLayout.autoRefresh();
                }
                return false;
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

    @Override
    public void showApproachList(PageInfo<ConcreteEntranceAcceptanceInfo> pageInfo) {
        List<ConcreteEntranceAcceptanceInfo> dataList = pageInfo.getList();
        if (loadType == Consts.XR_LOAD_TYPE.REFRESH) {
            adapter.loadData(dataList);
            page = 2;
        } else {
            adapter.insertData(adapter.getItemCount(), new ArrayList<BaseConcreteInfo>(dataList));
            ++page;
        }
        refreshLayout.setEnableLoadMore(pageInfo.getTotal() > adapter.getItemCount());
    }

    @Override
    public void showCompressiveList(PageInfo<ConcreteCompressiveInfo> pageInfo) {
        List<ConcreteCompressiveInfo> dataList = pageInfo.getList();
        if (loadType == Consts.XR_LOAD_TYPE.REFRESH) {
            adapter.loadData(dataList);
            page = 2;
        } else {
            adapter.insertData(adapter.getItemCount(), new ArrayList<BaseConcreteInfo>(dataList));
            ++page;
        }
        refreshLayout.setEnableLoadMore(pageInfo.getTotal() > adapter.getItemCount());
    }

    @Override
    public void showPermeabilityList(PageInfo<ConcreteCompressiveInfo> pageInfo) {
        List<ConcreteCompressiveInfo> dataList = pageInfo.getList();
        if (loadType == Consts.XR_LOAD_TYPE.REFRESH) {
            adapter.loadData(dataList);
            page = 2;
        } else {
            adapter.insertData(adapter.getItemCount(), new ArrayList<BaseConcreteInfo>(dataList));
            ++page;
        }
        refreshLayout.setEnableLoadMore(pageInfo.getTotal() > adapter.getItemCount());
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(InspectionEvent event) {
        refreshLayout.autoRefresh();
    }

}
