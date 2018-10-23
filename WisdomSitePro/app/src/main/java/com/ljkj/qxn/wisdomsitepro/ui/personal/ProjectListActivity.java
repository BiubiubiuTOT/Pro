package com.ljkj.qxn.wisdomsitepro.ui.personal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ljkj.qxn.wisdomsitepro.MainActivity;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.data.entity.ProjectInfo;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.ui.personal.adapter.ProjectListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseActivity;
import cdsp.android.util.ToastUtils;

public class ProjectListActivity extends BaseActivity {
    private static final String KEY_ENTER_FLAG = "enterFlag";
    public static final int LOGIN_ACTIVITY_ENTER_FLAG = 1;
    public static final int PERSONAL_FRAGMENT_ENTER_FLAG = 2;

    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private int enterFlag;
    private ProjectListAdapter projectAdapter;

    public static void startActivity(Context context, int enterFlag) {
        Intent intent = new Intent(context, ProjectListActivity.class);
        intent.putExtra(KEY_ENTER_FLAG, enterFlag);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_list);
    }

    @Override
    protected void initUI() {
        tvTitle.setText("选择项目");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(projectAdapter = new ProjectListAdapter(null));
        this.enterFlag = getIntent().getIntExtra(KEY_ENTER_FLAG, LOGIN_ACTIVITY_ENTER_FLAG);
    }

    @Override
    protected void initData() {
        List<ProjectInfo> projectInfos = new ArrayList<>();
        ProjectInfo projectInfo = new ProjectInfo();
        projectInfo.setName(UserManager.getInstance().getProjectName());
        projectInfos.add(projectInfo);
        projectAdapter.setNewData(projectInfos);
        projectAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (LOGIN_ACTIVITY_ENTER_FLAG == enterFlag) {
                    startActivity(new Intent(ProjectListActivity.this, MainActivity.class));
                }
                finish();
            }
        });
    }

    @OnClick(R.id.tv_back)
    public void onViewClicked() {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        if (LOGIN_ACTIVITY_ENTER_FLAG == enterFlag) {
            ToastUtils.showShort("请选择项目");
        } else {
            super.onBackPressed();
        }
    }
}
