package com.ljkj.qxn.wisdomsitepro.ui.common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.contract.common.InspectionDetailContract;
import com.ljkj.qxn.wisdomsitepro.contract.common.ListFileContract;
import com.ljkj.qxn.wisdomsitepro.contract.common.QueryFileContract;
import com.ljkj.qxn.wisdomsitepro.data.FileEntity;
import com.ljkj.qxn.wisdomsitepro.data.entity.EnclosureInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.InspectionInfo;
import com.ljkj.qxn.wisdomsitepro.model.CommonModel;
import com.ljkj.qxn.wisdomsitepro.model.QualityModel;
import com.ljkj.qxn.wisdomsitepro.presenter.common.InspectionDetailPresenter;
import com.ljkj.qxn.wisdomsitepro.presenter.common.ListFilePresenter;
import com.ljkj.qxn.wisdomsitepro.presenter.common.QueryFilePresenter;
import com.ljkj.qxn.wisdomsitepro.ui.common.adapter.ShowImageAdapter;
import com.ljkj.qxn.wisdomsitepro.ui.quality.adapter.RectificationProcessAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseActivity;
import cdsp.android.ui.widget.DividerGridItemDecoration;

/**
 * 类描述：巡检详情-ui基类
 * 创建人：mhl
 * 创建时间：2018/2/6 14:59
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class BaseInspectionDetailsActivity extends BaseActivity implements InspectionDetailContract.View {

    protected RectificationProcessAdapter adapter;

    protected ShowImageAdapter sAdapter1;

    protected ShowImageAdapter sAdapter2;

    protected ShowImageAdapter sAdapter3;

    @BindView(R.id.v_d3)
    protected View vD3;

    @BindView(R.id.tv_title)
    protected TextView tvTitle;

    @BindView(R.id.tv_code)
    TextView tvCode;

    @BindView(R.id.tv_date)
    TextView tvDate;

    @BindView(R.id.tv_group)
    TextView tvGroup;

    @BindView(R.id.tv_reason)
    TextView tvReason;

    @BindView(R.id.tv_lossType)
    TextView lossType;

    @BindView(R.id.tv_next)
    protected TextView tvNext;

    @BindView(R.id.tv_suggestion_mark)
    protected TextView tvSuggestionMark;

    @BindView(R.id.tv_suggestion_content)
    protected TextView tvSuggestionContent;

    @BindView(R.id.rl_imgs1)
    protected RecyclerView rlImgs1;

    @BindView(R.id.tv_problem)
    protected TextView tvProblem;

    @BindView(R.id.rl_imgs2)
    protected RecyclerView rlImgs2;

    @BindView(R.id.tv_detail)
    protected TextView tvDetail;

    @BindView(R.id.tv_detail_content)
    protected TextView tvDetailContent;

    @BindView(R.id.rl_imgs3)
    protected RecyclerView rlImgs3;

    @BindView(R.id.rl_persons)
    protected RecyclerView rlPersons;

    @BindView(R.id.ll_imm_approval)
    protected LinearLayout llImmApproval;

    @BindView(R.id.nsv_detail)
    protected NestedScrollView nsvDetail;

    protected InspectionDetailPresenter detailPresenter;

    protected QueryFilePresenter filePresenter;

    protected QueryFilePresenter zgPresenter;

    protected InspectionInfo inspectionInfo;

    protected String id;

    protected String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_base_inspection_details);
        ButterKnife.bind(this);
    }

    public static void startActivity(Context context, String id, String title, Class<? extends Activity> clazz) {

        Intent intent = new Intent();
        intent.putExtra("id", id);
        intent.putExtra("title", title);
        intent.setClass(context, clazz);
        context.startActivity(intent);
    }

    @Override
    protected void initUI() {

        id = getIntent().getStringExtra("id");
        title = getIntent().getStringExtra("title");
        tvTitle.setText(title);

        rlImgs1.setLayoutManager(new GridLayoutManager(this, 3));
        rlImgs1.addItemDecoration(new DividerGridItemDecoration(this, ContextCompat.getDrawable(this, R.drawable.divider_of_select_image)));
        rlImgs1.setAdapter(sAdapter1 = new ShowImageAdapter(this));
        rlImgs1.setNestedScrollingEnabled(false);

        rlImgs2.setLayoutManager(new GridLayoutManager(this, 3));
        rlImgs2.addItemDecoration(new DividerGridItemDecoration(this, ContextCompat.getDrawable(this, R.drawable.divider_of_select_image)));
        rlImgs2.setAdapter(sAdapter2 = new ShowImageAdapter(this));
        rlImgs2.setNestedScrollingEnabled(false);

        rlImgs3.setLayoutManager(new GridLayoutManager(this, 3));
        rlImgs3.addItemDecoration(new DividerGridItemDecoration(this, ContextCompat.getDrawable(this, R.drawable.divider_of_select_image)));
        rlImgs3.setAdapter(sAdapter3 = new ShowImageAdapter(this));
        rlImgs3.setNestedScrollingEnabled(false);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rlPersons.setLayoutManager(linearLayoutManager);
        rlPersons.setAdapter(new RectificationProcessAdapter(this));
        rlPersons.setNestedScrollingEnabled(false);

    }

    @Override
    protected void initData() {

        detailPresenter = new InspectionDetailPresenter(this, QualityModel.newInstance());
        addPresenter(detailPresenter);
        detailPresenter.viewInspectionDetail(id);

        filePresenter = new QueryFilePresenter(new QueryFileContract.View() {
            @Override
            public void showFiles(List<FileEntity> data) {
                if (data != null && !data.isEmpty()) {
                    sAdapter2.loadData(data);
                }
            }

            @Override
            public void showProgress(String message) {

            }

            @Override
            public void hideProgress() {

            }

            @Override
            public void showError(String message) {

            }
        }, CommonModel.newInstance());

        zgPresenter = new QueryFilePresenter(new QueryFileContract.View() {
            @Override
            public void showFiles(List<FileEntity> data) {
                if (data != null && !data.isEmpty()) {
                    sAdapter3.loadData(data);
                }
            }

            @Override
            public void showProgress(String message) {

            }

            @Override
            public void hideProgress() {

            }

            @Override
            public void showError(String message) {

            }
        }, CommonModel.newInstance());
        addPresenter(filePresenter);
        addPresenter(zgPresenter);
    }

    @Override
    public void showInspectionDetail(InspectionInfo data) {

        if (data != null) {
            inspectionInfo = data;
            fillData(inspectionInfo);
        }
    }


    /**
     * 数据填充
     *
     * @param data
     */
    protected void fillData(InspectionInfo data) {

        //编号
        tvCode.setText(data.getCode());

        //检查时间
        tvDate.setText(data.getUpTime());

        //整改班组
        tvGroup.setText(data.getZgbz());

        //事故发生原因分类
        tvReason.setText(data.getWhyType());

        //损失分类
        lossType.setText(data.getLossType());

        //整改内容
        tvProblem.setText(data.getZgnr());


        //存在整改详情
        if (!TextUtils.isEmpty(data.getZgDetail())) {
            tvDetail.setVisibility(View.VISIBLE);
            tvDetailContent.setVisibility(View.VISIBLE);
            tvDetailContent.setText(data.getZgDetail());
        } else {
            tvDetail.setVisibility(View.GONE);
            tvDetailContent.setVisibility(View.GONE);
        }

        //新增巡检时图片
        filePresenter.queryFile(id);

        //整改上传的图片
        zgPresenter.queryFile(id);
    }

    @OnClick(R.id.tv_back)
    public void onViewClicked(View view) {

        if (view.getId() == R.id.tv_back) {
            finish();
        }
    }
}
