package com.ljkj.qxn.wisdomsitepro.ui.quality;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.contract.safe.log.SafeQualityLogListContract;
import com.ljkj.qxn.wisdomsitepro.data.Consts;
import com.ljkj.qxn.wisdomsitepro.data.entity.PageInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.SafeQualityLogInfo;
import com.ljkj.qxn.wisdomsitepro.data.event.RefreshEvent;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.model.SafeQualityLogModel;
import com.ljkj.qxn.wisdomsitepro.presenter.safe.log.SafeQualityLogListPresenter;
import com.ljkj.qxn.wisdomsitepro.ui.quality.adapter.SafeQualityLogAdapter;
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
import cdsp.android.ui.widget.ClearEditTextView;
import cdsp.android.util.KeyboardUtil;

/**
 * 类描述：安全/质量日志
 * 创建人：lxx
 * 创建时间：2018/7/6
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SafeQualityLogActivity extends BaseActivity implements SafeQualityLogListContract.View {
    private static final String KEY_LOG_TYPE = "key_log_type";

    /** 安全日志 */
    public static final int SAFE_LOG = 1;
    /** 质量日志 */
    public static final int QUALITY_LOG = 2;

    @BindView(R.id.tv_title)
    TextView titleText;

    @BindView(R.id.tv_right)
    TextView rightText;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    @BindView(R.id.ll_no_data)
    ViewGroup noDataLayout;

    @BindView(R.id.edit_search)
    ClearEditTextView searchEdit;

    private int loadType;
    private int page = 1;
    private int logType;
    private SafeQualityLogAdapter logAdapter;

    private SafeQualityLogListPresenter safeQualityLogListPresenter;

    public static void startActivity(Context context, int type) {
        Intent intent = new Intent(context, SafeQualityLogActivity.class);
        intent.putExtra(KEY_LOG_TYPE, type);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qualit_log);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initUI() {
        this.logType = getIntent().getIntExtra(KEY_LOG_TYPE, SAFE_LOG);
        titleText.setText(logType == SAFE_LOG ? "安全日志" : "质量日志");
        rightText.setText("新增");
        refreshLayout.setEnableLoadMore(false);

    }

    @Override
    protected void initData() {
        safeQualityLogListPresenter = new SafeQualityLogListPresenter(this, SafeQualityLogModel.newInstance());
        addPresenter(safeQualityLogListPresenter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(logAdapter = new SafeQualityLogAdapter(null));
        logAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                SafeQualityLogInfo logInfo = logAdapter.getItem(position);
                if (logInfo != null) {
                    SafeQualityLogDetailActivity.startActivity(SafeQualityLogActivity.this, logType, logInfo.getId());
                }
            }
        });
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                loadType = Consts.XR_LOAD_TYPE.REFRESH;
                if (logType == SAFE_LOG) {
                    safeQualityLogListPresenter.getSafeLogList(UserManager.getInstance().getProjectId(), 1, 10, searchEdit.getText().toString());
                } else {
                    safeQualityLogListPresenter.getQualityLogList(UserManager.getInstance().getProjectId(), 1, 10, searchEdit.getText().toString());
                }
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                loadType = Consts.XR_LOAD_TYPE.LOAD_MORE;
                if (logType == SAFE_LOG) {
                    safeQualityLogListPresenter.getSafeLogList(UserManager.getInstance().getProjectId(), page, 10, searchEdit.getText().toString());
                } else {
                    safeQualityLogListPresenter.getQualityLogList(UserManager.getInstance().getProjectId(), page, 10, searchEdit.getText().toString());
                }
            }
        });
        refreshLayout.autoRefresh();


        searchEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    refreshLayout.autoRefresh();
                    KeyboardUtil.closeKeyboard(SafeQualityLogActivity.this);
                }
                return false;
            }
        });
    }

    @OnClick({R.id.tv_back, R.id.tv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.tv_right:
                AddSafeQualityLogActivity.startActivity(this, logType);
                break;
            default:
                break;
        }
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

    /** @see AddSafeQualityLogActivity */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRefreshEvent(RefreshEvent event) {
        refreshLayout.autoRefresh();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void showSafeLogList(PageInfo<SafeQualityLogInfo> datas) {
        if (loadType == Consts.XR_LOAD_TYPE.REFRESH) {
            logAdapter.setNewData(datas.getList());
            page = 2;
        } else {
            logAdapter.addData(datas.getList());
            ++page;
        }
        refreshLayout.setEnableLoadMore(datas.getTotal() > logAdapter.getItemCount());
    }

    @Override
    public void showQualityLogList(PageInfo<SafeQualityLogInfo> datas) {
        if (loadType == Consts.XR_LOAD_TYPE.REFRESH) {
            logAdapter.setNewData(datas.getList());
            page = 2;
        } else {
            logAdapter.addData(datas.getList());
            ++page;
        }
        refreshLayout.setEnableLoadMore(datas.getTotal() > logAdapter.getItemCount());
    }
}
