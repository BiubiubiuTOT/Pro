package com.ljkj.qxn.wisdomsitepro.ui.common;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.Utils.PickerDialogHelper;
import com.ljkj.qxn.wisdomsitepro.Utils.SelectWindowHelper;
import com.ljkj.qxn.wisdomsitepro.contract.quality.QualityCheckContract;
import com.ljkj.qxn.wisdomsitepro.data.Consts;
import com.ljkj.qxn.wisdomsitepro.data.entity.PageInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.QualityCheckInfo;
import com.ljkj.qxn.wisdomsitepro.data.event.RefreshEvent;
import com.ljkj.qxn.wisdomsitepro.model.QualityModel;
import com.ljkj.qxn.wisdomsitepro.presenter.quality.QualityCheckPresenter;
import com.ljkj.qxn.wisdomsitepro.ui.common.adapter.CheckListAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseActivity;
import cdsp.android.util.DateUtils;

/**
 * 类描述：检查-列表 基础UI
 * 创建人：liufei
 * 创建时间：2018/3/12 11:14
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public abstract class BaseCheckListActivity extends BaseActivity implements QualityCheckContract.View {
    public static final String SAFE_TYPE = "1";
    public static final String QUALITY_TYPE = "2";

    @BindView(R.id.tv_title)
    public TextView tvTitle;
    @BindView(R.id.refreshLayout)
    public SmartRefreshLayout refreshLayout;
    @BindView(R.id.recyclerView)
    public RecyclerView recyclerView;
    @BindView(R.id.ll_no_data)
    ViewGroup llNoData;

    @BindView(R.id.rb_trouble_level)
    RadioButton troubleLevelBtn;

    @BindView(R.id.rb_rectify_type)
    RadioButton rectifyTypeBtn;

    @BindView(R.id.rb_check_data)
    RadioButton checkDateBtn;

    @BindView(R.id.line)
    View lineView;

    public CheckListAdapter adapter;
    public QualityCheckPresenter presenter;
    public int loadType;
    public int page = 1;

    public String yhdj = ""; //隐患等级
    public String zglx = ""; //整改类型
    public String jcrqTime = ""; //检查日期

    protected String checkType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_check);
    }

    @Override
    protected void initUI() {
        checkType = getType();
        refreshLayout.setEnableLoadMore(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter = new CheckListAdapter(this, checkType));
        initListener();
        checkDateBtn.setTag(Calendar.getInstance());
    }

    @Override
    protected void initData() {
        presenter = new QualityCheckPresenter(this, QualityModel.newInstance());
        addPresenter(presenter);
        refreshLayout.autoRefresh();
        EventBus.getDefault().register(this);
    }

    @Override
    public void showQualityCheckInfo(PageInfo<QualityCheckInfo> pageInfo) {
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
    public void hideProgress() {
        super.hideProgress();
        llNoData.setVisibility(recyclerView.getAdapter().getItemCount() > 0 ? View.GONE : View.VISIBLE);
        if (loadType == Consts.XR_LOAD_TYPE.REFRESH) {
            refreshLayout.finishRefresh();
        } else {
            refreshLayout.finishLoadmore();
        }
    }

    protected abstract void initListener();

    @OnClick({R.id.tv_back, R.id.rb_trouble_level, R.id.rb_rectify_type, R.id.rb_check_data})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.rb_trouble_level: //隐患等级
                showSelectWindow(1, troubleLevelBtn);
                break;
            case R.id.rb_rectify_type: //整改类型
                showSelectWindow(2, rectifyTypeBtn);
                break;
            case R.id.rb_check_data: //检查日期
                showDateDialog();
                break;
            default:
                break;
        }

    }

    private void showSelectWindow(final int type, RadioButton button) {
        List<String> items = new ArrayList<>();
        if (type == 1) {
            items.add("全部");
            items.add("一般隐患");
            items.add("重大隐患");
        } else if (type == 2) {
            items.add("全部");
            items.add("限期整改");
            items.add("局部停工整改");
            items.add("停工");
            items.add("挂牌督办");
        }

        SelectWindowHelper.showSingleListWindow(this, items, lineView, button, new SelectWindowHelper.OnItemSelectListener() {
            @Override
            public void onItemSelect(String item, int position) {
                if (type == 1) {
                    troubleLevelBtn.setText(item);
                    yhdj = position == 0 ? "" : item;
                } else if (type == 2) {
                    rectifyTypeBtn.setText(item);
                    zglx = position == 0 ? "" : item;
                }
                refreshLayout.autoRefresh();
            }
        });
    }

    private void showDateDialog() {
        if (isFastDoubleClick()) {
            return;
        }
        final Calendar calendar = (Calendar) checkDateBtn.getTag();
        PickerDialogHelper.showTimePicker(this, calendar, true, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                calendar.setTime(date);
                checkDateBtn.setTag(calendar);
                checkDateBtn.setText(DateUtils.date2str(date, DateUtils.PATTERN_DATE));
                jcrqTime = checkDateBtn.getText().toString();
                refreshLayout.autoRefresh();
            }
        });
    }

    /** @see com.ljkj.qxn.wisdomsitepro.ui.quality.QuaImmediateRectificationActivity */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRefreshEvent(RefreshEvent event) {
        refreshLayout.autoRefresh();
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    protected abstract String getType();

}
