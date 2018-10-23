package com.ljkj.qxn.wisdomsitepro.ui.safe;

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
 * 类描述：安全教育
 * 创建人：liufei
 * 创建时间：2018/2/7 13:31
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SafeEducationActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView titleText;

    @BindView(R.id.tv_right)
    TextView rightText;

    @BindView(R.id.view_pager)
    ViewPager viewPager;

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safe_education);
    }

    @Override
    protected void initUI() {
        titleText.setText("安全教育");
        rightText.setText("新增");
    }

    @Override
    protected void handleAuthority() {
        AuthorityManager.getInstance().handleViewByAuthority(rightText, AuthorityId.SafeManage.SAFE_EDU, true);
    }

    @Override
    protected void initData() {
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    @OnClick({R.id.tv_back, R.id.tv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.tv_right:
                if (viewPager.getCurrentItem() == 0) {
                    startActivity(new Intent(SafeEducationActivity.this, SafeEducationAddActivity.class));
                } else {
                    startActivity(new Intent(SafeEducationActivity.this, SafeTechnologyDiscloseAddActivity.class));
                }
                break;
            default:
                break;
        }
    }

    class MyFragmentPagerAdapter extends FragmentPagerAdapter {
        MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return Fragment.instantiate(SafeEducationActivity.this, SafeEducationDiscloseFragment.class.getName());
            } else {
                return Fragment.instantiate(SafeEducationActivity.this, SafeTechnologyDiscloseFragment.class.getName());
            }
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (position == 0) {
                return "安全教育交底";
            } else {
                return "安全技术交底";
            }
        }
    }

}
