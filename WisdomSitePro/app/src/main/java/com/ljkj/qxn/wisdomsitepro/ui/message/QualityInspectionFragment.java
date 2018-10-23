package com.ljkj.qxn.wisdomsitepro.ui.message;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.contract.quality.QualityPatrolContract;
import com.ljkj.qxn.wisdomsitepro.data.Consts;
import com.ljkj.qxn.wisdomsitepro.data.entity.PageInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.QualityPatrolDetailInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.QualityPatrolInfo;
import com.ljkj.qxn.wisdomsitepro.data.event.InspectionEvent;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.model.QualityModel;
import com.ljkj.qxn.wisdomsitepro.presenter.quality.QualityPatrolPresenter;
import com.ljkj.qxn.wisdomsitepro.ui.quality.QualityInspectionDetailV2Activity;
import com.ljkj.qxn.wisdomsitepro.ui.quality.QualityInspectionDetailsOfImmeRectiV2Activity;
import com.ljkj.qxn.wisdomsitepro.ui.quality.QualityInspectionDetailsOfQualifiedV2;
import com.ljkj.qxn.wisdomsitepro.ui.quality.QualityInspectionDetailsOfQualifiedV2Activity;
import com.ljkj.qxn.wisdomsitepro.ui.quality.QualityInspectionDetailsOfTobeAuditedV2Activity;
import com.ljkj.qxn.wisdomsitepro.ui.quality.adapter.QualityInspectionListV2Adapter;
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
 * 类描述：消息：质量巡检
 * 创建人：lxx
 * 创建时间：2018/9/25
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class QualityInspectionFragment extends BaseFragment implements QualityPatrolContract.View {
    @BindView(R.id.recyclerView)
    public RecyclerView recyclerView;
    @BindView(R.id.ll_no_data)
    public ViewGroup llNoData;
    @BindView(R.id.refreshLayout)
    public SmartRefreshLayout refreshLayout;

    public QualityInspectionListV2Adapter adapter;
    public int loadType;
    public int page = 1;
    public String isDealWith = "0";
    private QualityPatrolPresenter patrolPresenter;

    public static QualityInspectionFragment newInstance() {
        QualityInspectionFragment fragment = new QualityInspectionFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_recycler_view_list, container, false);
        return view;
    }

    @Override
    protected void initUI() {
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(adapter = new QualityInspectionListV2Adapter(mContext));
        refreshLayout.setEnableLoadMore(false);

        EventBus.getDefault().register(this);
        initListener();
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(InspectionEvent event) {
        int msgType = event.getMsgType();
        if (msgType == Consts.EVENT_TYPE.INSPECTION_TYPE || msgType == Consts.EVENT_TYPE.INSPECTION_PASS
                || msgType == Consts.EVENT_TYPE.INSPECTION_ADD) {
            refreshLayout.autoRefresh();
        }
    }

    private void initListener() {

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                loadType = Consts.XR_LOAD_TYPE.REFRESH;
                patrolPresenter.getQualityPatrolMessageList(UserManager.getInstance().getProjectId(), Consts.APPParams.ROWS, 1);
            }
        });

        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                loadType = Consts.XR_LOAD_TYPE.LOAD_MORE;
                patrolPresenter.getQualityPatrolMessageList(UserManager.getInstance().getProjectId(), Consts.APPParams.ROWS, page);
            }
        });


        adapter.setOnItemClickListener(new QualityInspectionListV2Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, int flag) {

                QualityPatrolInfo item = adapter.getItem(position);
                boolean canEdit;
                if (TextUtils.isEmpty(item.getTeamManagerId())) {
                    canEdit = false;
                } else
                    canEdit = item.getTeamManagerId().equals(UserManager.getInstance().getUserId());

                switch (flag) {
                    case -1://未整改、重新整改
                        QualityInspectionDetailV2Activity.startActivity(mContext, item.getId(), canEdit ? "巡检详情-立即整改" : "巡检详情",
                                canEdit ? QualityInspectionDetailsOfImmeRectiV2Activity.class : QualityInspectionDetailsOfQualifiedV2.class);
                        break;
                    case 0://未整改
                        QualityInspectionDetailV2Activity.startActivity(mContext, item.getId(), canEdit ? "巡检详情-立即整改" : "巡检详情",
                                canEdit ? QualityInspectionDetailsOfImmeRectiV2Activity.class : QualityInspectionDetailsOfQualifiedV2.class);
                        break;

                    case 1://立即审批：对于创建人，需要审批班组负责人的整改
                        QualityInspectionDetailV2Activity.startActivity(mContext, item.getId(), "巡检详情-待核查", QualityInspectionDetailsOfTobeAuditedV2Activity.class);
                        break;

                    case 2://整改不通过
                        QualityInspectionDetailV2Activity.startActivity(mContext, item.getId(), canEdit ? "巡检详情-重新整改" : "巡检详情",
                                canEdit ? QualityInspectionDetailsOfQualifiedV2Activity.class : QualityInspectionDetailsOfQualifiedV2.class);
                        break;

                    case 3: //合格：对于创建人和班组负责人都行，一次巡检结束
                        QualityInspectionDetailV2Activity.startActivity(mContext, item.getId(), "巡检详情-合格", QualityInspectionDetailsOfQualifiedV2.class);
                        break;

                    default:
                        break;
                }
            }
        });
    }

    @Override
    public void showQualityPatrolList(PageInfo<QualityPatrolInfo> pageInfo) {
        if (loadType == Consts.XR_LOAD_TYPE.REFRESH) {
            adapter.loadData(pageInfo.getList());
            recyclerView.scrollToPosition(0);
            page = 2;
        } else {
            page++;
            adapter.insertData(adapter.getItemCount(), pageInfo.getList());
        }
        refreshLayout.setEnableLoadMore(pageInfo.getTotal() > adapter.getItemCount());
    }

    @Override
    public void showQualityPatrolDetail(QualityPatrolDetailInfo info) {

    }

    @Override
    public void hideProgress() {
        super.hideProgress();
        llNoData.setVisibility(recyclerView.getAdapter().getItemCount() > 0 ? View.GONE : View.VISIBLE);
        if (loadType == Consts.XR_LOAD_TYPE.REFRESH) {
            refreshLayout.finishRefresh();
        } else {
            refreshLayout.finishLoadmore();
        }
    }

    @Override
    protected void initData() {
        patrolPresenter = new QualityPatrolPresenter(this, QualityModel.newInstance());
        addPresenter(patrolPresenter);

        refreshLayout.autoRefresh();
    }
}
