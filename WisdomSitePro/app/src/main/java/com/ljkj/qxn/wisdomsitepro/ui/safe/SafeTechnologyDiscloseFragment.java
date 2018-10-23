package com.ljkj.qxn.wisdomsitepro.ui.safe;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.contract.safe.SafeEduListContract;
import com.ljkj.qxn.wisdomsitepro.data.Consts;
import com.ljkj.qxn.wisdomsitepro.data.entity.PageInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.SafeEduInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.SafeTechnologyInfo;
import com.ljkj.qxn.wisdomsitepro.data.event.SafeEducationRefreshEvent;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.model.SafeModel;
import com.ljkj.qxn.wisdomsitepro.presenter.safe.SafeEduPresenter;
import com.ljkj.qxn.wisdomsitepro.ui.safe.adapter.SafeTechnologyAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import cdsp.android.ui.widget.DividerItemDecoration;

/**
 * 类描述：安全技术交底
 * 创建人：lxx
 * 创建时间：2018/7/18
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SafeTechnologyDiscloseFragment extends BaseSafeDiscloseFragment implements SafeEduListContract.View {

    private SafeTechnologyAdapter adapter;
    private SafeEduPresenter presenter;

    @Override
    protected void initUI() {
        super.initUI();
        recyclerView.setAdapter(adapter = new SafeTechnologyAdapter(null));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL, ContextCompat.getDrawable(getContext(), R.drawable.divider_of_tv_list)));
        refreshLayout.setEnableLoadMore(false);
    }

    @Override
    protected void initData() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        presenter = new SafeEduPresenter(this, SafeModel.newInstance());
        addPresenter(presenter);
        refreshLayout.autoRefresh();
        super.initData();

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                SafeTechnologyInfo info = (SafeTechnologyInfo) adapter.getItem(position);
                if (info != null) {
                    SafeTechnologyDetailActivity.startActivity(getContext(), info.id);
                }
            }
        });
    }

    @Override
    protected void requestData(int loadType) {
        if (loadType == Consts.XR_LOAD_TYPE.REFRESH) {
            presenter.getSafeEdeTechnologyList(UserManager.getInstance().getProjectId(), isReport, trainDate, Consts.APPParams.ROWS, 1);
        } else {
            presenter.getSafeEdeTechnologyList(UserManager.getInstance().getProjectId(), isReport, trainDate, Consts.APPParams.ROWS, page);
        }
    }

    @Override
    public void showSafeEduList(PageInfo<SafeEduInfo> pageInfo) {
    }

    @Override
    public void showSafeTechnologyList(PageInfo<SafeTechnologyInfo> pageInfo) {
        if (loadType == Consts.XR_LOAD_TYPE.REFRESH) {
            adapter.setNewData(pageInfo.getList());
            page = 2;
        } else {
            page++;
            adapter.addData(adapter.getItemCount(), pageInfo.getList());
        }
        refreshLayout.setEnableLoadMore(pageInfo.getTotal() > adapter.getItemCount());
    }

    /** @see SafeEducationAddActivity */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRefreshEvent(SafeEducationRefreshEvent event) {
        if (event.type == SafeEducationRefreshEvent.EDUCATION) {
            refreshLayout.autoRefresh();
        }
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

}
