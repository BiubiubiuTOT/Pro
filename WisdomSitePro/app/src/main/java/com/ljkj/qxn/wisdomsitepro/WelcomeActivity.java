package com.ljkj.qxn.wisdomsitepro;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ljkj.qxn.wisdomsitepro.ui.personal.LoginActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cdsp.android.ui.base.BaseActivity;

/**
 * 欢迎页
 * 创建人：lxx
 * 创建时间：2018/3/21 09:43
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class WelcomeActivity extends BaseActivity {

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    @Override
    protected void initUI() {
    }

    @Override
    protected boolean needStatusBar() {
        return false;
    }

    @Override
    protected void initData() {
        List<Integer> list = new ArrayList<>();
        list.add(R.mipmap.iv_wellcome1);
        list.add(R.mipmap.iv_wellcome2);
        list.add(R.mipmap.iv_wellcome3);
        list.add(R.mipmap.iv_wellcome4);
        viewPager.setAdapter(new WelcomePagerAdapter(list));
    }

    private class WelcomePagerAdapter extends PagerAdapter {
        private List<Integer> list;

        WelcomePagerAdapter(List<Integer> list) {
            super();
            this.list = list;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(WelcomeActivity.this);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setBackgroundResource(list.get(position));

            if (position == list.size() - 1) {
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
                        finish();
                    }
                });
            } else {
                imageView.setOnClickListener(null);
            }
            container.addView(imageView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            return imageView;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

    }

}
