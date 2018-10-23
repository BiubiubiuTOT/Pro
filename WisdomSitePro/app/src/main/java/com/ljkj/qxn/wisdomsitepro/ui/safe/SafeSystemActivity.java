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
 * 类描述：安全体系
 * 创建人：liufei
 * 创建时间：2018/2/7 14:14
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SafeSystemActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    @BindView(R.id.viewpager)
    ViewPager viewpager;

    private String[] tablayoutTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safe_system);
    }

    @Override
    protected void initUI() {
        tvTitle.setText("安全体系");
        tablayoutTitle = getResources().getStringArray(R.array.arr_safe_system);
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        viewpager.setOffscreenPageLimit(2);
        viewpager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewpager);
        viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    @Override
    protected void initData() {

    }

    class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) { //安全管理人员体系
                return new SafeSystemPeopleFragment();
            } else if (position == 1) { //安全生产管理制度
                return SafeSystemPdfFragment.newInstance(SafeSystemPdfFragment.TYPE_MANAGER);
            } else { //安全生产规章制度
                return SafeSystemPdfFragment.newInstance(SafeSystemPdfFragment.TYPE_RULE);
            }
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

    @OnClick({R.id.tv_back, R.id.tv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.tv_right:
                break;
        }
    }
}
