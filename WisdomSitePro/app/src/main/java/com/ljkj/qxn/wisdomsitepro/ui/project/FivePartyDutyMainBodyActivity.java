package com.ljkj.qxn.wisdomsitepro.ui.project;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.contract.project.FivePartyContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.FivePartyInfo;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.model.ProjectModel;
import com.ljkj.qxn.wisdomsitepro.presenter.project.FivePartyPresenter;
import com.ljkj.qxn.wisdomsitepro.ui.project.fragment.BuildUnitFragment;
import com.ljkj.qxn.wisdomsitepro.ui.project.fragment.ConstructionUnitFragment;
import com.ljkj.qxn.wisdomsitepro.ui.project.fragment.DataInterface;
import com.ljkj.qxn.wisdomsitepro.ui.project.fragment.SubcontractingUnitFragment;
import com.ljkj.qxn.wisdomsitepro.ui.project.fragment.SupervisorUnitFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseActivity;

/**
 * 五方责任主体
 */
public class FivePartyDutyMainBodyActivity extends BaseActivity implements FivePartyContract.View {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    @BindView(R.id.viewpager)
    ViewPager viewpager;

    private FivePartyPresenter partyPresenter;

    private List<Fragment> fragments = new ArrayList<>();
    private String[] titles = {"建设单位", "勘察单位", "设计单位", "监理单位", "施工单位", "分包单位"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_five_party_duty_main_body);
    }

    @Override
    protected void initUI() {
        tvTitle.setText("五方责任主体");
    }

    @Override
    protected void initData() {
        fragments.add(BuildUnitFragment.newInstance(BuildUnitFragment.TYPE_BUILD));
        fragments.add(BuildUnitFragment.newInstance(BuildUnitFragment.TYPE_SURVEY));
        fragments.add(BuildUnitFragment.newInstance(BuildUnitFragment.TYPE_DESIGN));
        fragments.add(SupervisorUnitFragment.newInstance());
        fragments.add(ConstructionUnitFragment.newInstance());
        fragments.add(SubcontractingUnitFragment.newInstance());

        viewpager.setOffscreenPageLimit(5);
        viewpager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewpager);
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewpager));
        viewpager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }
        });

        partyPresenter = new FivePartyPresenter(this, ProjectModel.newInstance());
        addPresenter(partyPresenter);
        partyPresenter.getFivePartyInfo(UserManager.getInstance().getProjectId());
        partyPresenter.getSubInfo(UserManager.getInstance().getProjectId());
    }

    @Override
    public void showFivePartyInfo(FivePartyInfo info) {
        for (Fragment fragment : fragments) {
            if (fragment instanceof DataInterface) {
                ((DataInterface) fragment).updateUIForDetail(info);
            }
        }
    }

    @Override
    public void showSubInfo(FivePartyInfo info) {
        for (Fragment fragment : fragments) {
            if (fragment instanceof DataInterface) {
                ((DataInterface) fragment).showSubData(info);
            }
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
            return titles[position];
        }

        @Override
        public int getCount() {
            return titles.length;
        }

    }

    @OnClick(R.id.tv_back)
    public void onViewClicked() {
        finish();
    }

}
