package com.ljkj.qxn.wisdomsitepro.ui.personal;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseActivity;

/**
 * 类描述：消息
 * 创建人：liufei
 * 创建时间：2018/2/2 10:54
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class MessgeActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.tabLayout)
    TabLayout tablayout;

    @BindView(R.id.viewpager)
    ViewPager viewpager;

    private String[] tablayoutTitle = {"待我处理", "历史消息"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messge);
    }

    @Override
    protected void initUI() {
        tvTitle.setText("我的消息");
        ViewCompat.setElevation(tablayout, 8);
        viewpager.setAdapter(fragmentStatePagerAdapter);
        tablayout.setupWithViewPager(viewpager);
        viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout));
    }

    @Override
    protected void initData() {

    }

    FragmentStatePagerAdapter fragmentStatePagerAdapter = new FragmentStatePagerAdapter(getSupportFragmentManager()) {
        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return MessageFragment.newInstance(MessageFragment.STATUS_NEW);
            } else {
                return MessageFragment.newInstance(MessageFragment.STATUS_HISTORY);
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
    };

    @OnClick(R.id.tv_back)
    public void onViewClicked() {
        finish();
    }
}
