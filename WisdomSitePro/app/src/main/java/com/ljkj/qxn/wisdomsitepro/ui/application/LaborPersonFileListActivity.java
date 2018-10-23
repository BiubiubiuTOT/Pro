package com.ljkj.qxn.wisdomsitepro.ui.application;

import android.content.Context;
import android.content.Intent;
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
import com.ljkj.qxn.wisdomsitepro.contract.application.LabourContract;
import com.ljkj.qxn.wisdomsitepro.data.Consts;
import com.ljkj.qxn.wisdomsitepro.data.entity.LabourDetail;
import com.ljkj.qxn.wisdomsitepro.data.entity.LabourInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.PageInfo;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.model.ApplicationModel;
import com.ljkj.qxn.wisdomsitepro.presenter.application.LabourPresenter;
import com.ljkj.qxn.wisdomsitepro.ui.application.adapter.LaborPersonFileListAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseActivity;

/**
 * 类描述：劳务人员档案
 * 创建人：mhl
 * 创建时间：2018/2/2 9:20
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class LaborPersonFileListActivity extends BaseActivity implements LabourContract.View {
    private static final String KEY_TEAM = "key_team";

    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.edit_search)
    EditText etSearch;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    @BindView(R.id.ll_no_data)
    ViewGroup noDataLayout;

    @BindView(R.id.rb_select)
    RadioButton selectBtn;

    LaborPersonFileListAdapter adapter;
    LabourPresenter labourPresenter;

    int page = 1;

    private int loadType;

    private String keywords = "";
    private String teamId;

    public static void startActivity(Context context, String teamId) {
        Intent intent = new Intent(context, LaborPersonFileListActivity.class);
        intent.putExtra(KEY_TEAM, teamId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_labor_personnel_file);
    }

    @Override
    protected void initUI() {
        tvTitle.setText("档案详情");
        etSearch.setHint("姓名关键字搜索");
        selectBtn.setVisibility(View.GONE);
        teamId = getIntent().getStringExtra(KEY_TEAM);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter = new LaborPersonFileListAdapter(this));
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {

                loadType = Consts.XR_LOAD_TYPE.REFRESH;
                labourPresenter.listPageLabourInfo
                        (keywords, 1, UserManager.getInstance().getProjectId(), Consts.APPParams.ROWS, teamId);
            }
        });

        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                loadType = Consts.XR_LOAD_TYPE.LOAD_MORE;
                labourPresenter.listPageLabourInfo(keywords, page, UserManager.getInstance().getProjectId(), Consts.APPParams.ROWS, teamId);
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
                        refreshLayout.autoRefresh(200);
                    }
                }
                return false;
            }
        });
    }

    @Override
    protected void initData() {
        labourPresenter = new LabourPresenter(this, ApplicationModel.newInstance());
        addPresenter(labourPresenter);
        refreshLayout.autoRefresh(400);

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
    public void showPageLabourInfo(PageInfo<LabourInfo> data) {
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
        refreshLayout.setEnableLoadMore(data.getTotal() > adapter.getItemCount());
    }

    @Override
    public void showLabourDetail(LabourDetail data) {

    }


}
