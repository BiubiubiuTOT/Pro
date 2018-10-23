package com.ljkj.qxn.wisdomsitepro.ui.message;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.data.AuthorityId;
import com.ljkj.qxn.wisdomsitepro.manager.AuthorityManager;
import com.ljkj.qxn.wisdomsitepro.ui.application.ConstructLogAddActivity;
import com.ljkj.qxn.wisdomsitepro.ui.application.ConstructLogListActivity;
import com.ljkj.qxn.wisdomsitepro.ui.quality.QualityInspectionAddV2Activity;
import com.ljkj.qxn.wisdomsitepro.ui.safe.SafeInspectionAddV2Activity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseFragment;
import cdsp.android.util.DisplayUtils;

/**
 * 类描述：消息
 * 创建人：lxx
 * 创建时间：2018/6/19
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class MessageFragment extends BaseFragment {
    public static final String TAG = MessageFragment.class.getName();


    @BindView(R.id.tv_title)
    TextView titleText;

    @BindView(R.id.tv_back)
    TextView backText;

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    @BindView(R.id.viewpager)
    ViewPager viewpager;

    @BindView(R.id.fab)
    TextView fab;

    @BindView(R.id.ll_add)
    LinearLayout addLayout;

    @BindView(R.id.tv_add_safe_inspect)
    TextView addSafeInspectText;

    @BindView(R.id.tv_add_quality_inspect)
    TextView addQualityInspectText;

    @BindView(R.id.tv_add_notice)
    TextView addNoticeText;

    @BindView(R.id.tv_add_log)
    TextView addLogText;

    private List<Fragment> fragments = new ArrayList<>();
    private List<String> titles = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_message, container, false);
    }

    @Override
    protected void initUI() {
        titleText.setText("消息");
        backText.setVisibility(View.GONE);
        viewpager.setOffscreenPageLimit(5);
    }

    @Override
    protected void handleAuthority() {
        AuthorityManager manager = AuthorityManager.getInstance();
//        if (!manager.findAuthorityById(AuthorityId.SafeManage.SAFE_CHECK).isHide()) {
//            titles.add("安全检查");
//            fragments.add(SafeQualityListFragment.newInstance(SafeQualityListFragment.MESSAGE_SAFE));
//        }
        if (!manager.findAuthorityById(AuthorityId.SafeManage.SAFE_INSPECTION).isHide()) {
            titles.add("安全巡检");
            fragments.add(SafeInspectionFragment.newInstance());
        }
//        if (!manager.findAuthorityById(AuthorityId.QualityManage.QUALITY_CHECK).isHide()) {
//            titles.add("质量检查");
//            fragments.add(SafeQualityListFragment.newInstance(SafeQualityListFragment.MESSAGE_QUALITY));
//        }
        if (!manager.findAuthorityById(AuthorityId.QualityManage.QUALITY_INSPECTION).isHide()) {
            titles.add("质量巡检");
            fragments.add(QualityInspectionFragment.newInstance());
        }
        titles.add("公告");
        fragments.add(NoticeFragment.newInstance());
        if (!manager.findAuthorityById(AuthorityId.DataManage.CONSTRUCT_LOG).isHide()) {
            titles.add("日志");
            fragments.add(ConstructLogListActivity.ConstructLogListFragment.newInstance());
        }

        manager.handleViewByAuthority(addSafeInspectText, AuthorityId.SafeManage.SAFE_INSPECTION, true);
        manager.handleViewByAuthority(addQualityInspectText, AuthorityId.QualityManage.QUALITY_INSPECTION, true);
        manager.handleViewByAuthority(addLogText, AuthorityId.DataManage.CONSTRUCT_LOG, true);
    }

    @Override
    protected void initData() {
//        fragments.add(Fragment.instantiate(getActivity(), ConversationListFragment.class.getName()));

        viewpager.setAdapter(new MyPagerAdapter(getChildFragmentManager()));
        tabLayout.setupWithViewPager(viewpager);
//        setTabLayout();
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewpager));
        viewpager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                closeFab();
            }
        });

    }

    private void setTabLayout() {
        tabLayout.removeAllTabs();
        for (String title : titles) {
            addTab(title, title.equals(titles.get(0)));
        }

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                View tabView = tab.getCustomView();
                View indicatorView = tabView.findViewById(R.id.view_item_indicator);
                indicatorView.setVisibility(View.VISIBLE);
                fab.setVisibility(tab.getPosition() == 2 ? View.VISIBLE : View.GONE);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                View tabView = tab.getCustomView();
                View indicatorView = tabView.findViewById(R.id.view_item_indicator);
                indicatorView.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    private void addTab(String tab, boolean indicator) {
        View customView = getTabView(getContext(), tab, indicator);
        tabLayout.addTab(tabLayout.newTab().setCustomView(customView));
    }

    private View getTabView(Context context, String text, boolean indicator) {
        View view = LayoutInflater.from(context).inflate(R.layout.tab_item_message_layout, null);
        TextView tabText = (TextView) view.findViewById(R.id.tv_title);
        View indicatorView = view.findViewById(R.id.view_item_indicator);
        indicatorView.setVisibility(indicator ? View.VISIBLE : View.INVISIBLE);
        tabText.setText(text);
        return view;
    }

    public void updateRedDot(boolean showDot) {
//        if (tabLayout == null) {
//            return;
//        }
//        View tabView = tabLayout.getTabAt(2).getCustomView();
//        View redDotView = tabView.findViewById(R.id.view_red_dot);
//        redDotView.setVisibility(showDot ? View.VISIBLE : View.GONE);
    }

    public void closeFab() {
        if (addLayout.getVisibility() == View.VISIBLE) {
            fab.animate().rotation(0).setDuration(200).start();
            addLayout.setVisibility(View.GONE);
        }
    }

    @OnClick({R.id.fab, R.id.tv_add_safe_inspect, R.id.tv_add_quality_inspect, R.id.tv_add_notice, R.id.tv_add_log})
    public void onViewClicked(View view) {
        if (view.getId() != R.id.fab) {
            closeFab();
        }
        switch (view.getId()) {
            case R.id.fab:
                if (addLayout.getVisibility() == View.GONE) {
                    fab.animate().rotation(45).setDuration(200).start();
                    addLayout.setVisibility(View.VISIBLE);
                } else {
                    fab.animate().rotation(0).setDuration(200).start();
                    addLayout.setVisibility(View.GONE);
                }
                break;
            case R.id.tv_add_safe_inspect:
                SafeInspectionAddV2Activity.startActivity(getActivity());
                break;
            case R.id.tv_add_quality_inspect:
                QualityInspectionAddV2Activity.startActivity(getActivity());
                break;
            case R.id.tv_add_notice:
                AddNoticeActivity.startActivity(getActivity());
                break;
            case R.id.tv_add_log:
                startActivity(new Intent(getContext(), ConstructLogAddActivity.class));
                break;
            default:
                break;
        }
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }

        @Override
        public int getCount() {
            return titles.size();
        }

    }

    public interface onMsgStatusChangedListener {
        void onMsgStatusChanged(boolean hasUnreadMsg);

    }


    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void setTabWidth(final TabLayout tabLayout, int tabCount, final int offset) {
        final int tabWidth = DisplayUtils.getScreenWidth() / tabCount;
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                try {
                    //拿到tabLayout的mTabStrip属性
                    LinearLayout mTabStrip = (LinearLayout) tabLayout.getChildAt(0);
                    for (int i = 0; i < mTabStrip.getChildCount(); i++) {
                        View tabView = mTabStrip.getChildAt(i);
                        //拿到tabView的mTextView属性  tab的字数不固定一定用反射取mTextView
                        Field mTextViewField = tabView.getClass().getDeclaredField("mTextView");
                        mTextViewField.setAccessible(true);
                        TextView mTextView = (TextView) mTextViewField.get(tabView);
                        tabView.setPadding(0, 0, 0, 0);

                        int width = mTextView.getWidth(); //测量mTextView的宽度
                        if (width == 0) {
                            mTextView.measure(0, 0);
                            width = mTextView.getMeasuredWidth();
                        }

                        //设置tab左右间距 注意这里不能使用Padding 因为源码中线的宽度是根据 tabView的宽度来设置的
                        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tabView.getLayoutParams();
                        params.width = width;

                        int margin = (tabWidth - width) / 2 - offset;
                        params.leftMargin = margin;
                        params.rightMargin = margin;
                        tabView.setLayoutParams(params);
                        tabView.invalidate();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

}
