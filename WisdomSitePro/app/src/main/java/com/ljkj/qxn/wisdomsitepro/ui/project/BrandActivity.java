package com.ljkj.qxn.wisdomsitepro.ui.project;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.contract.common.QueryFileContract;
import com.ljkj.qxn.wisdomsitepro.contract.project.BrandInfoContract;
import com.ljkj.qxn.wisdomsitepro.data.BrandInfo;
import com.ljkj.qxn.wisdomsitepro.data.FileEntity;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.model.CommonModel;
import com.ljkj.qxn.wisdomsitepro.model.ProjectModel;
import com.ljkj.qxn.wisdomsitepro.presenter.common.QueryFilePresenter;
import com.ljkj.qxn.wisdomsitepro.presenter.project.BrandInfoPresenter;
import com.ljkj.qxn.wisdomsitepro.ui.common.adapter.ShowImageAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseActivity;
import cdsp.android.ui.widget.DividerGridItemDecoration;

public class BrandActivity extends BaseActivity implements BrandInfoContract.View, QueryFileContract.View {

    @BindView(R.id.tv_title)
    TextView titleText;

    @BindView(R.id.tv_content)
    TextView contentText;

    @BindView(R.id.rl_images)
    RecyclerView imagesRV;

    private BrandInfoPresenter brandInfoPresenter;
    private QueryFilePresenter queryFilePresenter;
    private ShowImageAdapter adapter;

    public static void startActivity(Context context, String brandType, String title) {
        Intent intent = new Intent(context, BrandActivity.class);
        intent.putExtra("brandType", brandType);
        intent.putExtra("title", title);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand);
    }

    @Override
    protected void initUI() {
        imagesRV.setLayoutManager(new GridLayoutManager(this, 3));
        imagesRV.addItemDecoration(new DividerGridItemDecoration(this, ContextCompat.getDrawable(this, R.drawable.divider_of_select_image)));
        imagesRV.setAdapter(adapter = new ShowImageAdapter(this));
        imagesRV.setNestedScrollingEnabled(false);
    }

    @Override
    protected void initData() {
        String title = getIntent().getStringExtra("title");
        String brandType = getIntent().getStringExtra("brandType");
        titleText.setText(title);
        brandInfoPresenter = new BrandInfoPresenter(this, ProjectModel.newInstance());
        addPresenter(brandInfoPresenter);
        queryFilePresenter = new QueryFilePresenter(this, CommonModel.newInstance());
        addPresenter(queryFilePresenter);
        brandInfoPresenter.getBrandInfo(UserManager.getInstance().getProjectId(), brandType);
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
    public void showBrandInfo(BrandInfo info) {
        if (info == null) {
            return;
        }
        contentText.setText(info.publicContent);
        queryFilePresenter.queryFile(info.id);
    }

    @Override
    public void showFiles(List<FileEntity> fileEntities) {
        adapter.loadData(fileEntities);
    }
}
