package com.ljkj.qxn.wisdomsitepro.ui.safe;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.contract.common.QueryFileContract;
import com.ljkj.qxn.wisdomsitepro.data.FileEntity;
import com.ljkj.qxn.wisdomsitepro.model.CommonModel;
import com.ljkj.qxn.wisdomsitepro.presenter.common.QueryFilePresenter;
import com.ljkj.qxn.wisdomsitepro.ui.common.adapter.ShowImageAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseActivity;
import cdsp.android.ui.widget.DividerGridItemDecoration;

/**
 * 类描述：安全隐患台账详情
 * 创建人：lxx
 * 创建时间：2018/7/11
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SafeHiddenAccountDetailActivity extends BaseActivity implements QueryFileContract.View {
    private static final String KEY_DETAIL = "key_detail";
    private static final String KEY_ID = "key_id";

    @BindView(R.id.tv_title)
    TextView titleText;

    @BindView(R.id.tv_detail)
    TextView detailText;

    @BindView(R.id.rl_images)
    RecyclerView imagesRV;

    @BindView(R.id.tv_image_title)
    TextView imageTitleText;

    private ShowImageAdapter adapter;

    private QueryFilePresenter queryFilePresenter;

    public static void startActivity(Context context, String detail, String images) {
        Intent intent = new Intent(context, SafeHiddenAccountDetailActivity.class);
        intent.putExtra(KEY_DETAIL, detail);
        intent.putExtra(KEY_ID, images);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safe_hidden_account_detail);
    }

    @Override
    protected void initUI() {
        titleText.setText("详情");
        imagesRV.setLayoutManager(new GridLayoutManager(this, 3));
        imagesRV.addItemDecoration(new DividerGridItemDecoration(this, ContextCompat.getDrawable(this, R.drawable.divider_of_select_image)));
        imagesRV.setAdapter(adapter = new ShowImageAdapter(this));
        imagesRV.setNestedScrollingEnabled(false);
    }

    @Override
    protected void initData() {
        queryFilePresenter = new QueryFilePresenter(this, CommonModel.newInstance());
        addPresenter(queryFilePresenter);
        String detail = getIntent().getStringExtra(KEY_DETAIL);
        String id = getIntent().getStringExtra(KEY_ID);

        detailText.setText(detail);
        if (!TextUtils.isEmpty(id)) {
            queryFilePresenter.queryFile(id);
        } else {
            imagesRV.setVisibility(View.GONE);
            imageTitleText.setVisibility(View.GONE);
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
    public void showFiles(List<FileEntity> fileEntities) {
        adapter.loadData(fileEntities);
    }
}
