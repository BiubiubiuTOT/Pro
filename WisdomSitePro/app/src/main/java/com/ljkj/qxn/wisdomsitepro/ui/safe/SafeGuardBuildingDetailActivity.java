package com.ljkj.qxn.wisdomsitepro.ui.safe;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.contract.safe.SafeGuardBuildContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.SafeGuardBuildInfo;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.model.SafeModel;
import com.ljkj.qxn.wisdomsitepro.presenter.safe.SafeGuardBuildPresenter;
import com.ljkj.qxn.wisdomsitepro.ui.safe.adapter.SafeGuardBuildingDetailAdapter;
import com.mcxtzhang.swipemenulib.SwipeMenuLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseActivity;

/**
 * 类描述：安全防护楼栋详情
 * 创建人：rjf
 * 创建时间：2018/3/13 12:19.
 * 修改人：
 * 修改时间
 * 修改备注
 */

public class SafeGuardBuildingDetailActivity extends BaseActivity implements SafeGuardBuildContract.View {
    private static final String KEY_BUILDING_NAME = "buildingName";
    private static final String KEY_FLOOR_NUM = "floorNum";
    private static final String KEY_PROJECT_NAME = "projectName";

    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.tv_building_name)
    TextView tvBuildingName;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private String buildingName;
    private int floorNum;
    private String projectName;

    private SafeGuardBuildingDetailAdapter adapter;

    SafeGuardBuildPresenter presenter;

    private String louOrder;

    private List<SafeGuardBuildInfo.CengsBean> floors;

    private boolean isContaintSplitFloor;

    private int underFloorConunt;
    private int floorConunt;
    private int splitFloorConunt;


    public static void startActivity(Context context, String buildingName, int floorNum, String projectName) {
        Intent intent = new Intent(context, SafeGuardBuildingDetailActivity.class);
        intent.putExtra(KEY_BUILDING_NAME, buildingName);
        intent.putExtra(KEY_FLOOR_NUM, floorNum);
        intent.putExtra(KEY_PROJECT_NAME, projectName);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safe_guard_building_detail);
        ButterKnife.bind(this);

    }

    @Override
    protected void initUI() {
        this.buildingName = getIntent().getStringExtra(KEY_BUILDING_NAME);
        this.floorNum = getIntent().getIntExtra(KEY_FLOOR_NUM, 0);
        this.projectName = getIntent().getStringExtra(KEY_PROJECT_NAME);

        if (!TextUtils.isEmpty(projectName)) {
            tvTitle.setText(projectName);
        }

        tvRight.setText("新增");

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter = new SafeGuardBuildingDetailAdapter(this));

        adapter.setOnItemDeleteClickListener(new SafeGuardBuildingDetailAdapter.OnItemDeleteClickListener() {
            @Override
            public void onItemDeleteClick(int position, SwipeMenuLayout itemView) {
                if (position == floors.size() - 1 || floors.get(position).getCeng().equals("架空层")) {
                    presenter.deleteSafeGuardBuild(floors.get(position).getId());
                } else {
                    showError("不能删除中间层或地下室");
                }
            }
        });
    }

    @Override
    protected void initData() {

        presenter = new SafeGuardBuildPresenter(this, SafeModel.newInstance());
        addPresenter(presenter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getSafeGuardBuild(buildingName, UserManager.getInstance().getProjectId());
    }

    @OnClick({R.id.tv_back, R.id.tv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.tv_right:
                SafeGuardAddFloorActivity.startActivity(this, louOrder, underFloorConunt, floorConunt, splitFloorConunt);
                break;
            default:
                break;
        }
    }

    @Override
    public void showSafeGuardBuild(SafeGuardBuildInfo data) {
        floors = data.getCengs();
        adapter.loadData(floors);
        louOrder = data.getLouOrder();

        underFloorConunt = data.getDxCount();
        floorConunt = data.getLcCount();
        splitFloorConunt = data.getJkCount();

        tvBuildingName.setText(data.getLou());
        adapter.setBuildingName(data.getLou());
    }

    @Override
    public void deleteSafeGuardBuild() {
        presenter.getSafeGuardBuild(buildingName, UserManager.getInstance().getProjectId());
    }
}
