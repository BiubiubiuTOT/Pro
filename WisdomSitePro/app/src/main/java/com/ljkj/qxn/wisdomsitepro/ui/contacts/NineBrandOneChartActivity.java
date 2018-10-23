package com.ljkj.qxn.wisdomsitepro.ui.contacts;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.Utils.H5Helper;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.ui.project.BrandActivity;
import com.ljkj.qxn.wisdomsitepro.ui.project.ConstructionSiteActivity;
import com.ljkj.qxn.wisdomsitepro.ui.project.FivePartyDutyMainBodyActivity;
import com.ljkj.qxn.wisdomsitepro.ui.project.ManagerPeopleActivity;
import com.ljkj.qxn.wisdomsitepro.ui.project.PeasantBrandActivity;
import com.ljkj.qxn.wisdomsitepro.ui.project.ProjectEffectActivity;
import com.ljkj.qxn.wisdomsitepro.ui.project.ProjectOverviewBrandActivity;
import com.ljkj.qxn.wisdomsitepro.ui.project.ProjectProgressActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseActivity;

/**
 * 类描述：九牌一图
 * 创建人：njt
 * 创建时间：2018/6/23 13:53
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class NineBrandOneChartActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nine_brand_one_chart);
        ButterKnife.bind(this);
    }

    @Override
    protected void initUI() {
        tvTitle.setText("九牌一图");
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.tv_back, R.id.item_project_overview, R.id.item_manager_phone, R.id.item_fire_protection,
            R.id.item_safe_product, R.id.item_civilized_construction, R.id.item_site_layout, R.id.item_migrant_workers,
            R.id.item_project_progress, R.id.item_five_part, R.id.item_project_design_sketch})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.item_project_overview: //工程概况牌
//                H5Helper.toProfile(this, "工程概况牌", UserManager.getInstance().getProjectId());
                startActivity(new Intent(this, ProjectOverviewBrandActivity.class));

                break;
            case R.id.item_manager_phone: //管理人员名单及监督电话牌
                startActivity(new Intent(this, ManagerPeopleActivity.class));
                break;
            case R.id.item_fire_protection: //消防保卫牌
                BrandActivity.startActivity(this, "177", "消防保卫牌");
                break;
            case R.id.item_safe_product: //安全生产牌
                BrandActivity.startActivity(this, "176", "安全生产牌");
                break;
            case R.id.item_civilized_construction: //文明施工牌
                BrandActivity.startActivity(this, "178", "文明施工牌");
                break;
            case R.id.item_site_layout: //施工现场平面布置图
                startActivity(new Intent(this, ConstructionSiteActivity.class));
                break;
            case R.id.item_migrant_workers: //农民工维权告示牌
                startActivity(new Intent(this, PeasantBrandActivity.class));
                break;
            case R.id.item_project_progress: //工程形象进度
                startActivity(new Intent(this, ProjectProgressActivity.class));
                break;
            case R.id.item_five_part: //五方责任主体
                startActivity(new Intent(this, FivePartyDutyMainBodyActivity.class));
                break;
            case R.id.item_project_design_sketch: //工程效果图
                startActivity(new Intent(this, ProjectEffectActivity.class));
                break;
            default:
                break;
        }

    }
}
