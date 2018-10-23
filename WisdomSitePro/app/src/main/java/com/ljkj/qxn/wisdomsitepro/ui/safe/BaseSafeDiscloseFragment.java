package com.ljkj.qxn.wisdomsitepro.ui.safe;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.Utils.PickerDialogHelper;
import com.ljkj.qxn.wisdomsitepro.Utils.SelectWindowHelper;
import com.ljkj.qxn.wisdomsitepro.data.Consts;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseFragment;
import cdsp.android.util.DateUtils;

/**
 * 类描述：安全教育
 * 创建人：lxx
 * 创建时间：2018/7/18
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public abstract class BaseSafeDiscloseFragment extends BaseFragment {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.ll_no_data)
    ViewGroup llNoData;

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    @BindView(R.id.rb_report)
    RadioButton reportBtn;

    @BindView(R.id.rb_date)
    RadioButton dateBtn;

    protected String isReport = ""; //是否上报
    protected String trainDate = ""; //培训日期

    protected int loadType;
    protected int page = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_safe_disclose, container, false);
    }

    @Override
    protected void initUI() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        dateBtn.setTag(Calendar.getInstance());
    }

    @Override
    protected void initData() {
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                loadType = Consts.XR_LOAD_TYPE.REFRESH;
                requestData(loadType);
            }
        });

        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                loadType = Consts.XR_LOAD_TYPE.LOAD_MORE;
                requestData(loadType);
            }
        });
    }

    protected abstract void requestData(int loadType);


    @OnClick({R.id.rb_report, R.id.rb_date})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rb_report: //是否上报
                showSelectWindow(reportBtn);
                break;
            case R.id.rb_date: //培训日期
                showDateDialog();
                break;
            default:
                break;
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

    private void showSelectWindow(RadioButton button) {
        List<String> items = new ArrayList<>();
        items.add("全部");
        items.add("是");
        items.add("否");

        SelectWindowHelper.showSingleListWindow(getContext(), items, button, button, new SelectWindowHelper.OnItemSelectListener() {
            @Override
            public void onItemSelect(String item, int position) {
                reportBtn.setText(item);
                if (position == 0) {
                    isReport = "";
                } else if (position == 1) {
                    isReport = "1";
                } else if (position == 2) {
                    isReport = "0";
                }
                refreshLayout.autoRefresh();
            }
        });
    }

    private void showDateDialog() {
        if (isFastDoubleClick()) {
            return;
        }
        final Calendar calendar = (Calendar) dateBtn.getTag();
        PickerDialogHelper.showTimePicker(getContext(), calendar, true, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                calendar.setTime(date);
                dateBtn.setTag(calendar);
                dateBtn.setText(DateUtils.date2str(date, DateUtils.PATTERN_DATE));
                trainDate = dateBtn.getText().toString();
                refreshLayout.autoRefresh();
            }
        });
    }

}
