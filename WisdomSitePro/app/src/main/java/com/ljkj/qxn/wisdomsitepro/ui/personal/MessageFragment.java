package com.ljkj.qxn.wisdomsitepro.ui.personal;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.contract.personal.MessageListContract;
import com.ljkj.qxn.wisdomsitepro.data.Consts;
import com.ljkj.qxn.wisdomsitepro.data.entity.MessageInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.PageInfo;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.model.UserModel;
import com.ljkj.qxn.wisdomsitepro.presenter.personal.MessageListPresenter;
import com.ljkj.qxn.wisdomsitepro.ui.personal.adapter.MessageAdapter;
import com.ljkj.qxn.wisdomsitepro.ui.quality.QuaCheckDetailsOfQualifiedActivity;
import com.ljkj.qxn.wisdomsitepro.ui.quality.QuaCheckDetailsOfRectifyActivity;
import com.ljkj.qxn.wisdomsitepro.ui.quality.QuaCheckDetailsOfToBeAuditedActivity;
import com.ljkj.qxn.wisdomsitepro.ui.quality.QuaGenerHiddenDangerOfImmeRectiActivity;
import com.ljkj.qxn.wisdomsitepro.ui.safe.SafeCheckDetailsOfQualifiedActivity;
import com.ljkj.qxn.wisdomsitepro.ui.safe.SafeCheckDetailsOfRectifyActivity;
import com.ljkj.qxn.wisdomsitepro.ui.safe.SafeCheckDetailsOfToBeAuditedActivity;
import com.ljkj.qxn.wisdomsitepro.ui.safe.SafeGenerHiddenDangerOfImmeRectiActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cdsp.android.ui.BaseRecyclerAdapter;
import cdsp.android.ui.base.BaseFragment;

/**
 * 类描述：消息
 * 创建人：liufei
 * 创建时间：2018/2/2 10:54
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
@Deprecated
public class MessageFragment extends BaseFragment implements MessageListContract.View {

    /**
     * 新消息（待处理）
     */
    public static final int STATUS_NEW = 1;
    /**
     * 已读消息（历史消息）
     */
    public static final int STATUS_HISTORY = 2;

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.ll_no_data)
    ViewGroup noDataLayout;

    private MessageAdapter adapter;
    private MessageListPresenter messageListPresenter;
    private int page = 1;
    private int loadType;
    private int status = STATUS_NEW;

    public MessageFragment() {
        // Required empty public constructor
    }

    /**
     * @param status 状态，1：新消息（待处理）；2：已读消息（历史消息）
     */
    public static MessageFragment newInstance(int status) {
        MessageFragment fragment = new MessageFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("status", status);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_recycler_view_list, container, false);
        if (getArguments() != null) {
            status = getArguments().getInt("status");
        }
        return view;
    }

    @Override
    protected void initUI() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter = new MessageAdapter(getActivity()));
    }

    @Override
    protected void initData() {
        messageListPresenter = new MessageListPresenter(this, UserModel.newInstance());
        addPresenter(messageListPresenter);
        refreshLayout.autoRefresh();
        initListener();
    }

    private void initListener() {
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                loadType = Consts.XR_LOAD_TYPE.REFRESH;
                messageListPresenter.getMessageList(1, UserManager.getInstance().getProjectId(), Consts.APPParams.ROWS, status);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                loadType = Consts.XR_LOAD_TYPE.LOAD_MORE;
                messageListPresenter.getMessageList(page, UserManager.getInstance().getProjectId(), Consts.APPParams.ROWS, status);
            }
        });

        adapter.setOnItemViewClickListener(new BaseRecyclerAdapter.OnItemViewClickListener() {
            @Override
            public void onViewClick(View view, int position) {
                MessageInfo messageInfo = adapter.getItem(position);
                int flag = adapter.getFlag(messageInfo);
                String type = messageInfo.getType();// 1:安全  2:质量
                switch (flag) {
                    case 1: //立即整改-一般隐患
                        if ("1".equals(type)) {
                            SafeGenerHiddenDangerOfImmeRectiActivity.startActivity(mContext, SafeGenerHiddenDangerOfImmeRectiActivity.TYPE_NORMAL, messageInfo.getId());
                        } else if ("2".equals(type)) {
                            QuaGenerHiddenDangerOfImmeRectiActivity.startActivity(mContext, QuaGenerHiddenDangerOfImmeRectiActivity.TYPE_NORMAL, messageInfo.getId());
                        }
                        break;
                    case 2: //待核查
                        if ("1".equals(type)) {
                            SafeCheckDetailsOfToBeAuditedActivity.startActivity(mContext, messageInfo.getId());
                        } else if ("2".equals(type)) {
                            QuaCheckDetailsOfToBeAuditedActivity.startActivity(mContext, messageInfo.getId());
                        }
                        break;
                    case 3: //合格
                        if ("1".equals(type)) {
                            SafeCheckDetailsOfQualifiedActivity.startActivity(mContext, messageInfo.getId());
                        } else if ("2".equals(type)) {
                            QuaCheckDetailsOfQualifiedActivity.startActivity(mContext, messageInfo.getId());
                        }
                        break;
                    case 4: //重新整改
                        if ("1".equals(type)) {
                            SafeCheckDetailsOfRectifyActivity.startActivity(mContext, messageInfo.getId());
                        } else if ("2".equals(type)) {
                            QuaCheckDetailsOfRectifyActivity.startActivity(mContext, messageInfo.getId());
                        }
                        break;
                    case 5: //立即整改-重大隐患
                        if ("1".equals(type)) {
                            SafeGenerHiddenDangerOfImmeRectiActivity.startActivity(mContext, SafeGenerHiddenDangerOfImmeRectiActivity.TYPE_MAJOR, messageInfo.getId());
                        } else if ("2".equals(type)) {
                            QuaGenerHiddenDangerOfImmeRectiActivity.startActivity(mContext, QuaGenerHiddenDangerOfImmeRectiActivity.TYPE_MAJOR, messageInfo.getId());
                        }
                        break;
                    case 6: //待核查
                        if ("1".equals(type)) {
                            SafeCheckDetailsOfToBeAuditedActivity.startActivity(mContext, messageInfo.getId());
                        } else if ("2".equals(type)) {
                            QuaCheckDetailsOfToBeAuditedActivity.startActivity(mContext, messageInfo.getId());
                        }
                        break;
                    case 7: //合格
                        if ("1".equals(type)) {
                            SafeCheckDetailsOfQualifiedActivity.startActivity(mContext, messageInfo.getId());
                        } else if ("2".equals(type)) {
                            QuaCheckDetailsOfQualifiedActivity.startActivity(mContext, messageInfo.getId());
                        }
                        break;
                    case 8: //重新整改
                        if ("1".equals(type)) {
                            SafeCheckDetailsOfRectifyActivity.startActivity(mContext, messageInfo.getId());
                        } else if ("2".equals(type)) {
                            QuaCheckDetailsOfRectifyActivity.startActivity(mContext, messageInfo.getId());
                        }
                        break;
                    default:
                        break;
                }

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
    }

    @Override
    public void showMessageList(PageInfo<MessageInfo> datas) {
        List<MessageInfo> infoList = datas.getList();
        if (loadType == Consts.XR_LOAD_TYPE.REFRESH) {
            adapter.loadData(infoList);
            page = 2;
        } else {
            adapter.insertData(adapter.getItemCount(), new ArrayList<>(infoList));
            ++page;
        }
        refreshLayout.setEnableLoadMore(datas.getTotal() > adapter.getItemCount());
        noDataLayout.setVisibility(adapter.getItemCount() == 0 ? View.VISIBLE : View.GONE);
    }
}
