package com.ljkj.qxn.wisdomsitepro.ui.safe;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.ui.safe.adapter.SafeTroubleAccountAdapter;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseActivity;

/**
 * 类描述：安全隐患台账
 * 创建人：liufei
 * 创建时间：2018/2/7 13:46
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SafeTroubleAccountActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private SafeTroubleAccountAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safe_trouble_account);
    }

    @Override
    protected void initUI() {
        tvTitle.setText("安全隐患台账");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter = new SafeTroubleAccountAdapter(this));
    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.tv_back)
    public void onViewClicked() {
        finish();
    }
}
