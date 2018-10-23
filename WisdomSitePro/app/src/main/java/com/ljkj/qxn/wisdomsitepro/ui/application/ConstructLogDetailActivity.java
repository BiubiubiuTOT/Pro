package com.ljkj.qxn.wisdomsitepro.ui.application;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.contract.application.ConstructLogDetailContract;
import com.ljkj.qxn.wisdomsitepro.contract.common.QueryFileContract;
import com.ljkj.qxn.wisdomsitepro.data.FileEntity;
import com.ljkj.qxn.wisdomsitepro.data.entity.ConstructLogDetailInfo;
import com.ljkj.qxn.wisdomsitepro.model.ApplicationModel;
import com.ljkj.qxn.wisdomsitepro.model.CommonModel;
import com.ljkj.qxn.wisdomsitepro.presenter.application.ConstructLogDetailPresenter;
import com.ljkj.qxn.wisdomsitepro.presenter.common.QueryFilePresenter;
import com.ljkj.qxn.wisdomsitepro.ui.common.adapter.ShowImageAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseActivity;
import cdsp.android.ui.widget.DividerGridItemDecoration;
import cdsp.android.util.DisplayUtils;

import static com.ljkj.qxn.wisdomsitepro.R.id.ll_emergency;

/**
 * 类描述：施工日志详情
 * 创建人：rjf
 * 创建时间：2018/3/14 16:39.
 * 修改人：
 * 修改时间
 * 修改备注
 */

public class ConstructLogDetailActivity extends BaseActivity implements ConstructLogDetailContract.View, QueryFileContract.View {
    private static final String KEY_LOG_ID = "logId";

    private String logId;

    @BindView(R.id.tv_back)
    TextView tvBack;

    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.tv_project_name)
    TextView tvProjectName;

    @BindView(R.id.tv_project_header)
    TextView tvProjectHeader;

    @BindView(R.id.tv_number)
    TextView tvNumber;

    @BindView(R.id.tv_date)
    TextView tvDate;

    @BindView(R.id.tv_weather)
    TextView tvWeather;

    @BindView(R.id.tv_wind_level)
    TextView tvWindLevel;

    @BindView(R.id.tv_show_emergency)
    TextView tvShowEmergency;

    @BindView(R.id.tv_emergency_detail_text)
    TextView tvEmergencyDetailText;

    @BindView(R.id.tv_emergency_detail)
    TextView tvEmergencyDetail;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(ll_emergency)
    LinearLayout llEmergency;

    @BindView(R.id.tv_production_text)
    TextView tvProductionText;

    @BindView(R.id.tv_production_detail)
    TextView tvProductionDetail;

    @BindView(R.id.tv_quality_text)
    TextView tvQualityText;

    @BindView(R.id.tv_quality_detail)
    TextView tvQualityDetail;

    ConstructLogDetailPresenter presenter;
    QueryFilePresenter queryFilePresenter;

    private ShowImageAdapter adapter;

    public static void startActivity(Context context, String logId) {
        Intent intent = new Intent(context, ConstructLogDetailActivity.class);
        intent.putExtra(KEY_LOG_ID, logId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_construct_log_detail);
    }

    @Override
    protected void initUI() {

        tvTitle.setText("施工日志详情");
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) recyclerView.getLayoutParams();
        int margin = DisplayUtils.dip2px(this, 10.0f);
        params.setMargins(margin, margin, margin, margin);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.addItemDecoration(new DividerGridItemDecoration(this, ContextCompat.getDrawable(this, R.drawable.divider_of_select_image)));
        recyclerView.setAdapter(adapter = new ShowImageAdapter(this));
        recyclerView.setNestedScrollingEnabled(false);
    }

    @Override
    protected void initData() {
        this.logId = getIntent().getStringExtra(KEY_LOG_ID);
        presenter = new ConstructLogDetailPresenter(this, ApplicationModel.newInstance());
        addPresenter(presenter);
        presenter.getLogDetail(logId);

        queryFilePresenter = new QueryFilePresenter(this, CommonModel.newInstance());
        addPresenter(queryFilePresenter);
    }


    @OnClick({R.id.tv_back, R.id.tv_emergency_detail_text, R.id.tv_production_text, R.id.tv_quality_text})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.tv_emergency_detail_text:
                if (tvEmergencyDetail.getVisibility() == View.GONE) {
                    tvEmergencyDetail.setVisibility(View.VISIBLE);
                    tvEmergencyDetailText.setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(this, R.mipmap.ic_group_up_grey), null);
                } else {
                    tvEmergencyDetail.setVisibility(View.GONE);
                    tvEmergencyDetailText.setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(this, R.mipmap.ic_group_down_grey), null);
                }
                break;
            case R.id.tv_production_text:
                if (tvProductionDetail.getVisibility() == View.GONE) {
                    tvProductionDetail.setVisibility(View.VISIBLE);
                    tvProductionText.setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(this, R.mipmap.ic_group_up_grey), null);
                } else {
                    tvProductionDetail.setVisibility(View.GONE);
                    tvProductionText.setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(this, R.mipmap.ic_group_down_grey), null);
                }
                break;
            case R.id.tv_quality_text:
                if (tvQualityDetail.getVisibility() == View.GONE) {
                    tvQualityDetail.setVisibility(View.VISIBLE);
                    tvQualityText.setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(this, R.mipmap.ic_group_up_grey), null);
                } else {
                    tvQualityDetail.setVisibility(View.GONE);
                    tvQualityText.setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(this, R.mipmap.ic_group_down_grey), null);
                }
                break;
        }
    }

    @Override
    public void showLogDetail(ConstructLogDetailInfo log) {
        queryFilePresenter.queryFile(log.getId());
        tvProjectName.setText(log.getProName());
        tvProjectHeader.setText(log.getProHead());
        tvNumber.setText(log.getProNo());
        tvDate.setText(log.getDate());

        if (!TextUtils.isEmpty(log.getEmergency())) {
            tvShowEmergency.setText("是");
            tvEmergencyDetail.setText(log.getEmergency());
            llEmergency.setVisibility(View.VISIBLE);
        } else {
            tvShowEmergency.setText("否");
            llEmergency.setVisibility(View.GONE);
        }

        tvWeather.setText(log.getWeather());
        tvWindLevel.setText(log.getWind());

        tvProductionDetail.setText(log.getProduction());
        tvQualityDetail.setText(log.getQualitySafety());
    }

    @Override
    public void showFiles(List<FileEntity> data) {
        adapter.loadData(data);
    }
}
