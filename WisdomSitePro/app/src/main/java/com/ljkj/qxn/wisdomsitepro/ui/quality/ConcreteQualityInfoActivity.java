package com.ljkj.qxn.wisdomsitepro.ui.quality;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.data.AuthorityId;
import com.ljkj.qxn.wisdomsitepro.manager.AuthorityManager;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseActivity;

/**
 * 类描述：混凝土质量信息
 * 创建人：liufei
 * 创建时间：2018/2/3 10:35
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class ConcreteQualityInfoActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.tv_right)
    TextView rightText;

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    @BindView(R.id.viewpager)
    ViewPager viewpager;

    private String[] tablayoutTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concrete_quality_info);
    }

    @Override
    protected void initUI() {
        tvTitle.setText("混凝土质量信息");
        rightText.setText("新增");

        tablayoutTitle = getResources().getStringArray(R.array.arr_concrete_quality_info);
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        viewpager.setOffscreenPageLimit(2);
        viewpager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewpager);
        viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    @Override
    protected void handleAuthority() {
        AuthorityManager.getInstance().handleViewByAuthority(rightText, AuthorityId.QualityManage.QUALITY_CONCRETE, true);
    }

    @Override
    protected void initData() {
        rightText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int type = viewpager.getCurrentItem();
                if (type == 0) { //商品混凝土进场验收-新增
                    startActivity(new Intent(ConcreteQualityInfoActivity.this, SiteAcceptanceAddActivity.class));
                } else if (type == 1) { //混凝土抗压强度检验-新增
                    CompressionAndImpermeabilityAddActivity.startActivity(ConcreteQualityInfoActivity.this, CompressionAndImpermeabilityAddActivity.TYPE_COMPRESSION);
                } else { //混凝土抗渗检验-新增
                    CompressionAndImpermeabilityAddActivity.startActivity(ConcreteQualityInfoActivity.this, CompressionAndImpermeabilityAddActivity.TYPE_IMPERMEABILITY);
                }
            }
        });
    }

    class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return ConcreteQualityInfoFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return tablayoutTitle.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tablayoutTitle[position];
        }
    }

    @OnClick(R.id.tv_back)
    public void onViewClicked() {
        finish();
    }
}
