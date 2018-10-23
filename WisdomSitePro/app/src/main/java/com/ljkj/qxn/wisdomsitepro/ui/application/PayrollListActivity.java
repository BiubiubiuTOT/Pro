package com.ljkj.qxn.wisdomsitepro.ui.application;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.contract.application.WageContract;
import com.ljkj.qxn.wisdomsitepro.data.Consts;
import com.ljkj.qxn.wisdomsitepro.data.entity.PageInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.SfgzStatisticsInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.WageInfo;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.model.ApplicationModel;
import com.ljkj.qxn.wisdomsitepro.presenter.application.WagePresenter;
import com.ljkj.qxn.wisdomsitepro.ui.application.adapter.PayRollListAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseActivity;
import cdsp.android.util.DateUtils;

/**
 * 类描述：工资发放
 * 创建人：mhl
 * 创建时间：2018/2/2 11:26
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class PayrollListActivity extends BaseActivity implements WageContract.View {

    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.edit_search)
    EditText etSearch;

    @BindView(R.id.rb_select)
    RadioButton selectButton;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    @BindView(R.id.ll_no_data)
    ViewGroup noDataLayout;

    PayRollListAdapter adapter;
    WagePresenter wagePresenter;

    private int page = 1;
    private int loadType;
    private String keywords = "";
    private Calendar currentCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payroll_list);
    }

    @Override
    protected void initUI() {
        tvTitle.setText("工资发放");
        etSearch.setHint("关键字搜索");
        selectButton.setVisibility(View.GONE);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter = new PayRollListAdapter(this));
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                loadType = Consts.XR_LOAD_TYPE.REFRESH;
                currentCalendar = Calendar.getInstance();
                currentCalendar.add(Calendar.MONTH, -1);
                wagePresenter.listPageWageInfo(
                        DateUtils.date2str(currentCalendar.getTime(), "yyyy-MM"), keywords, 1, UserManager.getInstance().getProjectId(), Consts.APPParams.ROWS, "", "");
            }
        });

        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                loadType = Consts.XR_LOAD_TYPE.LOAD_MORE;
                wagePresenter.listPageWageInfo(DateUtils.date2str(currentCalendar.getTime(), "yyyy-MM"), keywords, page, UserManager.getInstance().getProjectId(), Consts.APPParams.ROWS, "", "");
            }
        });

        etSearch.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    keywords = etSearch.getText().toString().trim();
                    if (TextUtils.isEmpty(keywords)) {
                        showError("请输入搜索关键字");
                        return false;
                    } else {
                        refreshLayout.autoRefresh();
                    }
                }
                return false;
            }
        });
    }

    @Override
    protected void initData() {
        wagePresenter = new WagePresenter(this, ApplicationModel.newInstance());
        addPresenter(wagePresenter);
        refreshLayout.autoRefresh();
    }

    @OnClick({R.id.tv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            default:
                break;
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

    @Override
    public void showPageWageInfo(PageInfo<WageInfo> data) {
        if (loadType == Consts.XR_LOAD_TYPE.REFRESH) {
            adapter.loadData(data.getList());
            page = 2;
            if (data.getList() != null && data.getList().size() > 0) {
                refreshLayout.setEnableLoadMore(true);
            }
        } else {
            adapter.insertData(adapter.getItemCount(), data.getList());
            ++page;
        }
        if (data.getTotal() <= adapter.getItemCount()) {
            refreshLayout.setEnableLoadMore(false);
        }
    }

    @Override
    public void showSfgzStatisticsInfo(SfgzStatisticsInfo data) {

    }
}
