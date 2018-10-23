package com.ljkj.qxn.wisdomsitepro.ui.quality;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.Utils.widget.ItemView;
import com.ljkj.qxn.wisdomsitepro.contract.common.QueryFileContract;
import com.ljkj.qxn.wisdomsitepro.contract.safe.log.SafeQualityLogDetailContract;
import com.ljkj.qxn.wisdomsitepro.data.FileEntity;
import com.ljkj.qxn.wisdomsitepro.data.entity.SafeQualityLogDetail;
import com.ljkj.qxn.wisdomsitepro.model.CommonModel;
import com.ljkj.qxn.wisdomsitepro.model.SafeQualityLogModel;
import com.ljkj.qxn.wisdomsitepro.presenter.common.QueryFilePresenter;
import com.ljkj.qxn.wisdomsitepro.presenter.safe.log.SafeQualityLogDetailPresenter;
import com.ljkj.qxn.wisdomsitepro.ui.common.adapter.ShowImageAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseActivity;
import cdsp.android.ui.widget.DividerGridItemDecoration;
import cdsp.android.util.StringUtils;

/**
 * 类描述：安全/质量日志详情
 * 创建人：lxx
 * 创建时间：2018/7/6
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SafeQualityLogDetailActivity extends BaseActivity implements SafeQualityLogDetailContract.View, QueryFileContract.View {
    private static final String KEY_LOG_TYPE = "key_log_type";
    private static final String KEY_LOG_ID = "key_log_id";

    @BindView(R.id.tv_title)
    TextView titleText;

    @BindView(R.id.item_person)
    ItemView personItem;

    @BindView(R.id.item_date)
    ItemView dateItem;

    @BindView(R.id.item_weather)
    ItemView weatherItem;

    @BindView(R.id.item_construction_site)
    ItemView constructionSiteItem;

    @BindView(R.id.item_construction_dynamic)
    ItemView constructionDynamicItem;

    @BindView(R.id.tv_status_title)
    TextView statusTitleText;

    @BindView(R.id.tv_status_content)
    TextView statusContentText;

    @BindView(R.id.tv_handle_situation_title)
    TextView handleSituationTitleText;

    @BindView(R.id.tv_handle_situation_content)
    TextView handleSituationContentText;

    @BindView(R.id.tv_image_title)
    TextView imageTitleText;

    @BindView(R.id.rl_images)
    RecyclerView imagesRV;

    private int type;
    private String logId;

    private ShowImageAdapter adapter;
    private SafeQualityLogDetailPresenter presenter;
    private QueryFilePresenter queryFilePresenter;
    private SparseArray<String> weatherMap = new SparseArray<>();

    public static void startActivity(Context context, int type, String logId) {
        Intent intent = new Intent(context, SafeQualityLogDetailActivity.class);
        intent.putExtra(KEY_LOG_TYPE, type);
        intent.putExtra(KEY_LOG_ID, logId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safe_quality_log_detail);
    }

    @Override
    protected void initUI() {
        type = getIntent().getIntExtra(KEY_LOG_TYPE, SafeQualityLogActivity.SAFE_LOG);
        logId = getIntent().getStringExtra(KEY_LOG_ID);
        titleText.setText(type == SafeQualityLogActivity.SAFE_LOG ? "安全日志详情" : "质量日志详情");
        statusTitleText.setText(type == SafeQualityLogActivity.SAFE_LOG ? "安全状况" : "质量状况");
        handleSituationTitleText.setText(type == SafeQualityLogActivity.SAFE_LOG ? "安全问题处理情况" : "质量问题处理情况");

        imagesRV.setLayoutManager(new GridLayoutManager(this, 3));
        imagesRV.addItemDecoration(new DividerGridItemDecoration(this, ContextCompat.getDrawable(this, R.drawable.divider_of_select_image)));
        imagesRV.setAdapter(adapter = new ShowImageAdapter(this));
        imagesRV.setNestedScrollingEnabled(false);
    }

    @Override
    protected void initData() {
        weatherMap.put(1, "晴天");
        weatherMap.put(2, "阴天");
        weatherMap.put(3, "小雨");
        weatherMap.put(4, "大雨");
        presenter = new SafeQualityLogDetailPresenter(this, SafeQualityLogModel.newInstance());
        queryFilePresenter = new QueryFilePresenter(this, CommonModel.newInstance());
        addPresenter(queryFilePresenter);
        addPresenter(presenter);
        if (type == SafeQualityLogActivity.SAFE_LOG) {
            presenter.getSafeLogDetail(logId);
        } else {
            presenter.getQualityLogDetail(logId);
        }
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
    public void showSafeLogDetail(SafeQualityLogDetail detail) {
        showDetail(detail);
    }

    @Override
    public void showQualityDetail(SafeQualityLogDetail detail) {
        showDetail(detail);
    }

    private void showDetail(SafeQualityLogDetail detail) {
        personItem.setContent(detail.recorder);
        dateItem.setContent(detail.date);
        if (StringUtils.isNumeric(detail.weather)) {
            weatherItem.setContent(weatherMap.get(Integer.parseInt(detail.weather)));
        } else {
            weatherItem.setContent(detail.weather);
        }
        constructionSiteItem.setContent(detail.constructionSite);
        constructionDynamicItem.setContent(detail.constructionProcess);
        statusContentText.setText(detail.situation);
        handleSituationContentText.setText(detail.problem);

        queryFilePresenter.queryFile(detail.id);
    }

    @Override
    public void showFiles(List<FileEntity> fileEntities) {
        adapter.loadData(fileEntities);
    }
}
