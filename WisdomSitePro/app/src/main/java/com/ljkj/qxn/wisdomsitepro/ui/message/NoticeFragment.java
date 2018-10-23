package com.ljkj.qxn.wisdomsitepro.ui.message;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.contract.message.NoticeListContract;
import com.ljkj.qxn.wisdomsitepro.data.Consts;
import com.ljkj.qxn.wisdomsitepro.data.entity.PageInfo;
import com.ljkj.qxn.wisdomsitepro.data.event.NoticeRefreshEvent;
import com.ljkj.qxn.wisdomsitepro.data.message.NoticeInfo;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.model.MessageModel;
import com.ljkj.qxn.wisdomsitepro.presenter.message.GetNoticeListPresenter;
import com.ljkj.qxn.wisdomsitepro.ui.message.adapter.NoticeAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import cdsp.android.ui.base.BaseFragment;

/**
 * 类描述：公告
 * 创建人：lxx
 * 创建时间：2018/6/19
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class NoticeFragment extends BaseFragment implements NoticeListContract.View {

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.ll_no_data)
    ViewGroup llNoData;

    private GetNoticeListPresenter getNoticeListPresenter;
    private NoticeAdapter noticeAdapter;

    private int page = 1;
    private int loadType;
    private MessageFragment.onMsgStatusChangedListener onMsgStatusChangedListener;

    public static NoticeFragment newInstance() {
        NoticeFragment fragment = new NoticeFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MessageFragment.onMsgStatusChangedListener) {
            onMsgStatusChangedListener = (MessageFragment.onMsgStatusChangedListener) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_recycler_view_list, container, false);
        return view;
    }

    @Override
    protected void initUI() {
        refreshLayout.setEnableLoadMore(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    protected void initData() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        getNoticeListPresenter = new GetNoticeListPresenter(this, MessageModel.newInstance());
        addPresenter(getNoticeListPresenter);
        recyclerView.setAdapter(noticeAdapter = new NoticeAdapter(null));
        initListener();
        refreshLayout.autoRefresh();
    }

    private void initListener() {
        noticeAdapter.bindToRecyclerView(recyclerView);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                loadType = Consts.XR_LOAD_TYPE.REFRESH;
                getNoticeListPresenter.getNoticeList(UserManager.getInstance().getProjectId(), UserManager.getInstance().getUserId(), 1, Consts.APPParams.ROWS);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                loadType = Consts.XR_LOAD_TYPE.LOAD_MORE;
                getNoticeListPresenter.getNoticeList(UserManager.getInstance().getProjectId(), UserManager.getInstance().getUserId(), page, Consts.APPParams.ROWS);
            }
        });

        noticeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                NoticeInfo noticeInfo = noticeAdapter.getItem(position);
                if (noticeInfo == null) {
                    return;
                }
                if (noticeInfo.getStatus() == NoticeInfo.NOTICE_UNREAD) {
                    noticeInfo.setStatus(NoticeInfo.NOTICE_READ);
                    noticeInfo.setRead(noticeInfo.getRead() + 1);
                    noticeInfo.setNoRead(noticeInfo.getNoRead() - 1 < 0 ? 0 : noticeInfo.getNoRead() - 1);
                    noticeAdapter.refreshNotifyItemChanged(position);
                    if (onMsgStatusChangedListener != null) {
                        onMsgStatusChangedListener.onMsgStatusChanged(hasUnreadMsg());
                    }
                }
                NoticeDetailActivity.startActivity(getContext(), noticeInfo.getId(), false);
            }
        });
    }

    @Override
    public void showNoticeList(PageInfo<NoticeInfo> datas) {
        if (loadType == Consts.XR_LOAD_TYPE.REFRESH) {
            noticeAdapter.setNewData(datas.getList());
            page = 2;
        } else {
            noticeAdapter.addData(datas.getList());
            ++page;
        }
        refreshLayout.setEnableLoadMore(datas.getTotal() > noticeAdapter.getItemCount());
        if (onMsgStatusChangedListener != null) {
            onMsgStatusChangedListener.onMsgStatusChanged(hasUnreadMsg());
        }
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

    private boolean hasUnreadMsg() {
        if (noticeAdapter == null) {
            return false;
        }
        for (NoticeInfo noticeInfo : noticeAdapter.getData()) {
            if (noticeInfo.getStatus() == NoticeInfo.NOTICE_UNREAD) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    /** @see AddNoticeActivity */
    /** @see NoticeDetailActivity */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRefreshEvent(NoticeRefreshEvent event) {
        refreshLayout.autoRefresh();
    }

}
