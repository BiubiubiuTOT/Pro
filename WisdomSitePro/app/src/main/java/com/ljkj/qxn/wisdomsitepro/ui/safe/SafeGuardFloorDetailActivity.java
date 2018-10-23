package com.ljkj.qxn.wisdomsitepro.ui.safe;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.contract.safe.SafeGuardFloorDetailContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.SafeGuardFloorInfo;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.model.SafeModel;
import com.ljkj.qxn.wisdomsitepro.presenter.safe.SafeGuardFloorDetailPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cdsp.android.banner.ConvenientBanner;
import cdsp.android.banner.holder.CBViewHolderCreator;
import cdsp.android.ui.base.BaseActivity;

/**
 * 类描述：安全防护楼层详情
 * 创建人：rjf
 * 创建时间：2018/3/13 14:01.
 * 修改人：
 * 修改时间
 * 修改备注
 */

public class SafeGuardFloorDetailActivity extends BaseActivity implements SafeGuardFloorDetailContract.View {
    private static final String KEY_FLOOR_NAME = "key_floor_name";
    private static final String KEY_BUILDING_NAME = "key_building_name";
    private static final String KEY_FLOOR_ID = "key_floor_id";

    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.tv_floor_name)
    TextView tvFloorName;
    @BindView(R.id.tv_safe_guard_detail)
    TextView tvSafeGuardDetail;
    @BindView(R.id.bt_add_floor_image)
    Button btAddFloorImage;
    @BindView(R.id.convenientBanner)
    ConvenientBanner convenientBanner;
    @BindView(R.id.iv_banner_left)
    ImageView ivBannerLeft;
    @BindView(R.id.iv_banner_right)
    ImageView ivBannerRight;
    @BindView(R.id.tv_banner_page)
    TextView tvBannerPage;
    @BindView(R.id.rl_floor_detail)
    RelativeLayout rlFloorDetail;

    private String floorName;
    private String buildingName;
    private String floorId;

    SafeGuardFloorDetailPresenter presenter;


    public static void startActivity(Context context, String floorName, String buildingName, String floorId) {
        Intent intent = new Intent(context, SafeGuardFloorDetailActivity.class);
        intent.putExtra(KEY_FLOOR_NAME, floorName);
        intent.putExtra(KEY_BUILDING_NAME, buildingName);
        intent.putExtra(KEY_FLOOR_ID, floorId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safe_guard_floor_detail);
        ButterKnife.bind(this);
    }

    @Override
    protected void initUI() {
        floorName = getIntent().getStringExtra(KEY_FLOOR_NAME);
        buildingName = getIntent().getStringExtra(KEY_BUILDING_NAME);
        floorId = getIntent().getStringExtra(KEY_FLOOR_ID);

        if (!TextUtils.isEmpty(floorName)) {
            tvTitle.setText(floorName);
        }

        tvRight.setText("编辑");

        if (!TextUtils.isEmpty(buildingName)) {
            tvFloorName.setText(buildingName);
        }
    }

    @Override
    protected void initData() {
        presenter = new SafeGuardFloorDetailPresenter(this, SafeModel.newInstance());
        addPresenter(presenter);
        presenter.getSafeGuardFloorDetail(floorId, UserManager.getInstance().getProjectId());
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getSafeGuardFloorDetail(floorId, UserManager.getInstance().getProjectId());
    }

    @OnClick({R.id.tv_back, R.id.tv_right, R.id.bt_add_floor_image, R.id.iv_banner_left, R.id.iv_banner_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.tv_right:
                SafeGuardEditFloorActivity.startActivity(this, tvSafeGuardDetail.getText().toString(), floorId);
                break;
            case R.id.bt_add_floor_image:
                SafeGuardEditFloorActivity.startActivity(this, "", floorId);
                break;
            case R.id.iv_banner_left:
                convenientBanner.setcurrentitem(convenientBanner.getCurrentItem() - 1);
                break;
            case R.id.iv_banner_right:
                convenientBanner.setcurrentitem(convenientBanner.getCurrentItem() + 1);
                break;
        }
    }


    @Override
    public void showFloorDetail(List<SafeGuardFloorInfo> data) {
        if (null != data && !data.isEmpty()) {
            tvSafeGuardDetail.setText(data.get(0).getAqfhsm());

            setConvenientBanner(data);
        } else {
            showError("数据获取出错");
        }
    }

    //设置轮播图
    private void setConvenientBanner(final List<SafeGuardFloorInfo> data) {
        convenientBanner.setPages(new CBViewHolderCreator() {
            @Override
            public SafeGuardFloorBannerView createHolder() {
                return new SafeGuardFloorBannerView();
            }
        }, data)
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {
                        tvBannerPage.setText((position + 1) + "/" + data.size());
                        ivBannerLeft.setVisibility(position > 0 ? View.VISIBLE : View.GONE);
                        ivBannerRight.setVisibility(position + 1 < data.size() ? View.VISIBLE : View.GONE);
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });
        convenientBanner.setCanLoop(false);
        convenientBanner.setcurrentitem(0);
        tvBannerPage.setText("1" + "/" + data.size());
    }
}
