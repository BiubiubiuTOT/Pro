package com.ljkj.qxn.wisdomsitepro.ui.safe;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseActivity;

/**
 * 类描述：安全隐患台账
 * 创建人：lxx
 * 创建时间：2018/7/11
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SafeHiddenAccountActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    protected TextView titleText;

    @BindView(R.id.view_pager)
    ViewPager viewPager;

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safe_hidden_account);
    }

    @Override
    protected void initUI() {
        titleText.setText("安全隐患台账");
    }

    @Override
    protected void initData() {
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
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

    /** 1:安全 2：质量 */
    protected int getSecurityOrQuality() {
        return 1;
    }

    class MyFragmentPagerAdapter extends FragmentPagerAdapter {
        MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return SafeHiddenAccountFragment.newInstance(SafeHiddenAccountFragment.ACCOUNT_PRESENT_LEVEL, getSecurityOrQuality());
            } else {
                return SafeHiddenAccountFragment.newInstance(SafeHiddenAccountFragment.ACCOUNT_PROJECT, getSecurityOrQuality());
            }
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (position == 0) {
                return "本级隐患台账";
            } else {
                return "项目隐患台账";
            }
        }
    }
}
