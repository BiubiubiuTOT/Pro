package com.ljkj.qxn.wisdomsitepro.ui.safe.protection;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.Utils.widget.WisdomDialog;
import com.ljkj.qxn.wisdomsitepro.contract.common.QueryFileContract;
import com.ljkj.qxn.wisdomsitepro.contract.safe.FloorDefendContract;
import com.ljkj.qxn.wisdomsitepro.data.AuthorityId;
import com.ljkj.qxn.wisdomsitepro.data.FileEntity;
import com.ljkj.qxn.wisdomsitepro.data.api.HostConfig;
import com.ljkj.qxn.wisdomsitepro.data.entity.FloorDetail;
import com.ljkj.qxn.wisdomsitepro.data.entity.FloorInfo;
import com.ljkj.qxn.wisdomsitepro.data.event.SafeDefendEvent;
import com.ljkj.qxn.wisdomsitepro.manager.AuthorityManager;
import com.ljkj.qxn.wisdomsitepro.model.CommonModel;
import com.ljkj.qxn.wisdomsitepro.model.SafeProtectionModel;
import com.ljkj.qxn.wisdomsitepro.presenter.common.QueryFilePresenter;
import com.ljkj.qxn.wisdomsitepro.presenter.safe.FloorDefendPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.banner.ConvenientBanner;
import cdsp.android.banner.holder.CBViewHolderCreator;
import cdsp.android.banner.holder.Holder;
import cdsp.android.glide.GlideHelper;
import cdsp.android.ui.base.BaseActivity;

/**
 * 类描述：楼层防护详情
 * 创建人：lxx
 * 创建时间：2018/9/5
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class FloorDetailActivity extends BaseActivity implements FloorDefendContract.View, QueryFileContract.View {

    @BindView(R.id.tv_title)
    TextView titleText;

    @BindView(R.id.tv_right)
    TextView rightText;

    @BindView(R.id.tv_add)
    TextView addText;

    @BindView(R.id.ll_no_data)
    LinearLayout noDataLayout;

    @BindView(R.id.tv_defend)
    TextView defendText;

    @BindView(R.id.banner)
    ConvenientBanner banner;

    @BindView(R.id.ll_content)
    LinearLayout contentLayout;

    private FloorInfo floorInfo;
    private FloorDefendPresenter floorDefendPresenter;
    private QueryFilePresenter queryFilePresenter;
    private FloorDetail floorDetail;

    public static void startActivity(Context context, FloorInfo floorInfo) {
        Intent intent = new Intent(context, FloorDetailActivity.class);
        intent.putExtra("floorInfo", floorInfo);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floor_detail);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initUI() {
        floorInfo = getIntent().getParcelableExtra("floorInfo");
        titleText.setText("楼层详情-" + floorInfo.floorName);
        rightText.setText("操作");
        rightText.setVisibility(View.GONE);
        banner.setCanLoop(false);
    }

    @Override
    protected void handleAuthority() {
        AuthorityManager manager = AuthorityManager.getInstance();
        manager.handleViewByAuthority(addText, AuthorityId.SafeManage.SAFE_PROTECT,true);
    }

    @Override
    protected void initData() {
        floorDefendPresenter = new FloorDefendPresenter(this, SafeProtectionModel.newInstance());
        queryFilePresenter = new QueryFilePresenter(this, CommonModel.newInstance());
        addPresenter(queryFilePresenter);
        addPresenter(floorDefendPresenter);

        floorDefendPresenter.getFloorDetail(floorInfo.floorId);
    }

    @OnClick({R.id.tv_back, R.id.tv_right, R.id.tv_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.tv_right:
                showDialog();
                break;
            case R.id.tv_add:
                AddSafeProtectionActivity.startActivity(this, floorInfo);
                break;
            default:
                break;
        }
    }

    private void showDialog() {
        final WisdomDialog dialog = new WisdomDialog(this);
        View contentView = LayoutInflater.from(this).inflate(R.layout.dialog_floor_detail_edit, null);
        contentView.findViewById(R.id.iv_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        contentView.findViewById(R.id.tv_protection).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //编辑防护说明
                dialog.dismiss();
                EditSafeProtectionActivity.startActivity(FloorDetailActivity.this, floorDetail);
            }
        });
        contentView.findViewById(R.id.tv_floor).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //编辑楼层信息
                dialog.dismiss();
                finish();
            }
        });

        dialog.config(contentView, Gravity.BOTTOM, WisdomDialog.AnimType.BOTTOM, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, true)
                .setDimAmount(0.4f).show();
    }

    @Override
    public void showFloorDetail(FloorDetail floorDetail) {
        this.floorDetail = floorDetail;
        if (TextUtils.isEmpty(floorDetail.buildDefendDetail)) { //该楼层无防护信息
            noDataLayout.setVisibility(View.VISIBLE);
            contentLayout.setVisibility(View.INVISIBLE);
            rightText.setVisibility(View.GONE);
        } else { //有防护信息
            noDataLayout.setVisibility(View.GONE);
            rightText.setVisibility(View.VISIBLE);
            contentLayout.setVisibility(View.VISIBLE);
            defendText.setText(floorDetail.buildDefendDetail);

            queryFilePresenter.queryFile(floorDetail.id);
        }
    }

    /** @see AddSafeProtectionActivity */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(SafeDefendEvent event) {
        floorDefendPresenter.getFloorDetail(floorInfo.floorId);
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    public void showFiles(List<FileEntity> fileEntities) {
        List<String> paths = new ArrayList<>();
        for (FileEntity entity : fileEntities) {
            paths.add(HostConfig.getFileDownUrl(entity.fileId));
        }
        banner.setPages(new CBViewHolderCreator() {
            @Override
            public ImageHolderView createHolder() {
                return new ImageHolderView();
            }
        }, paths).setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setPointViewVisible(true)
                .setPageIndicator(new int[]{R.mipmap.ic_pro_items_normal, R.mipmap.ic_pro_items_check});
    }

    private class ImageHolderView implements Holder<String> {
        private ImageView imageView;

        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, int position, String url) {
            GlideHelper.loadImage(context, imageView, url);
        }
    }

    @Override
    public void showAddFloorDetail() { //ignore
    }
}
