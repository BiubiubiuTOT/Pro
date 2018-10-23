package com.ljkj.qxn.wisdomsitepro.ui.safe.protection;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.Utils.widget.WisdomDialog;
import com.ljkj.qxn.wisdomsitepro.contract.safe.FloorContract;
import com.ljkj.qxn.wisdomsitepro.data.AuthorityId;
import com.ljkj.qxn.wisdomsitepro.data.entity.BuildingInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.FloorInfo;
import com.ljkj.qxn.wisdomsitepro.data.event.SafeProtectionRefreshEvent;
import com.ljkj.qxn.wisdomsitepro.manager.AuthorityManager;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.model.SafeProtectionModel;
import com.ljkj.qxn.wisdomsitepro.presenter.safe.FloorPresenter;
import com.ljkj.qxn.wisdomsitepro.ui.safe.adapter.FloorsSelectAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseActivity;
import cdsp.android.util.DisplayUtils;
import cdsp.android.util.ToastUtils;

/**
 * 类描述：安全防护楼层
 * 创建人：lxx
 * 创建时间：2018/9/5
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class FloorsActivity extends BaseActivity implements FloorContract.View {
    @BindView(R.id.tv_title)
    TextView titleText;

    @BindView(R.id.tv_right)
    TextView rightText;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.tv_total_floor)
    TextView totalFloorText;

    @BindView(R.id.iv_no_data)
    ImageView noDataImage;

    @BindView(R.id.tv_add)
    TextView addText;

    private FloorsSelectAdapter floorsSelectAdapter;
    private FloorPresenter floorPresenter;
    private BuildingInfo buildingInfo;

    public static void startActivity(Context context, BuildingInfo buildingInfo) {
        Intent intent = new Intent(context, FloorsActivity.class);
        intent.putExtra("buildingInfo", buildingInfo);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floors);
    }

    @Override
    protected void initUI() {
        buildingInfo = getIntent().getParcelableExtra("buildingInfo");
        totalFloorText.setText("共计" + buildingInfo.floors + "层");
        titleText.setText(buildingInfo.buildName);
        rightText.setText("新增");
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
    }

    @Override
    protected void handleAuthority() {
        AuthorityManager manager = AuthorityManager.getInstance();
        manager.handleViewByAuthority(rightText, AuthorityId.SafeManage.SAFE_PROTECT, true);
        manager.handleViewByAuthority(addText, AuthorityId.SafeManage.SAFE_PROTECT, true);
    }

    @Override
    protected void initData() {
        floorPresenter = new FloorPresenter(this, SafeProtectionModel.newInstance());
        addPresenter(floorPresenter);
        recyclerView.setAdapter(floorsSelectAdapter = new FloorsSelectAdapter(null));
        floorsSelectAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                FloorInfo floorInfo = floorsSelectAdapter.getItem(position);
                if (floorInfo != null) {
                    FloorDetailActivity.startActivity(FloorsActivity.this, floorInfo);
                }
            }
        });
        floorsSelectAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                final FloorInfo floorInfo = floorsSelectAdapter.getItem(position);
                if (floorInfo != null) {
                    new AlertDialog.Builder(FloorsActivity.this)
                            .setMessage("删除 " + floorInfo.floorName + " 的楼层信息吗？")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    floorPresenter.deleteFloor(floorInfo.floorId);
                                }
                            })
                            .setNegativeButton("取消", null)
                            .show();
                }
                return false;
            }
        });
        requestData();
    }

    private void requestData() {
        floorPresenter.getFloorList(buildingInfo.id, UserManager.getInstance().getProjectCode(), UserManager.getInstance().getProjectId());
    }

    @OnClick({R.id.tv_back, R.id.tv_right, R.id.tv_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.tv_right:
                new AlertDialog.Builder(FloorsActivity.this)
                        .setMessage("新增一层楼层？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                floorPresenter.addFloor(buildingInfo.id, UserManager.getInstance().getProjectCode(), UserManager.getInstance().getProjectId());
                            }
                        })
                        .setNegativeButton("取消", null)
                        .show();
                break;
            case R.id.tv_add:
                showAddFloorsDialog();
                break;
            default:
                break;
        }
    }

    private void showAddFloorsDialog() {
        final WisdomDialog dialog = new WisdomDialog(this);
        int width = DisplayUtils.getScreenWidth() - DisplayUtils.dip2px(this, 32);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_select_build_num, null);
        final EditText contentText = (EditText) view.findViewById(R.id.et_content);
        TextView dialogTitle = (TextView) view.findViewById(R.id.tv_dialog_tile);
        TextView contentTitle = (TextView) view.findViewById(R.id.tv_content_title);
        dialogTitle.setText("新增楼层");
        contentTitle.setText("楼层数：");
        contentText.setHint("请输入楼层数(1~50)");

        view.findViewById(R.id.tv_sure).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = Integer.parseInt(contentText.getText().toString().trim());
                if (num == 0) {
                    ToastUtils.showShort("请输入楼层数");
                    return;
                }
                if (num > 50) {
                    ToastUtils.showShort("可输入的最大楼层数为50");
                    return;
                }
                floorPresenter.firstAddFloors(buildingInfo.id, num, UserManager.getInstance().getProjectCode(), UserManager.getInstance().getProjectId());
                dialog.dismiss();
            }
        });
        view.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.config(view, Gravity.CENTER, WisdomDialog.AnimType.CENTER, width, WindowManager.LayoutParams.WRAP_CONTENT, true)
                .setDimAmount(0.4f).show();
    }

    @Override
    public void hideProgress() {
        super.hideProgress();
        noDataImage.setVisibility(recyclerView.getAdapter().getItemCount() > 0 ? View.GONE : View.VISIBLE);
    }

    @Override
    public void showFloorList(List<FloorInfo> floorInfoList) {
        floorsSelectAdapter.setNewData(floorInfoList);
        totalFloorText.setText("共计" + floorsSelectAdapter.getItemCount() + "层");
        addText.setVisibility(floorsSelectAdapter.getItemCount() == 0 ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showFirstAddFloors() {
        ToastUtils.showShort("新增楼层数成功");
        EventBus.getDefault().post(new SafeProtectionRefreshEvent());
        requestData();
    }

    @Override
    public void showDeleteFloor() {
        ToastUtils.showShort("删除楼层信息成功");
        requestData();
    }

    @Override
    public void showAddFloor() {
        ToastUtils.showShort("新增楼层成功");
        recyclerView.scrollToPosition(floorsSelectAdapter.getItemCount() - 1);
        EventBus.getDefault().post(new SafeProtectionRefreshEvent());
        requestData();
    }

}
