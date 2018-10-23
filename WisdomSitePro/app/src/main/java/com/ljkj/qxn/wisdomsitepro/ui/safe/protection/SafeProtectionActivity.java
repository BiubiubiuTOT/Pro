package com.ljkj.qxn.wisdomsitepro.ui.safe.protection;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.Utils.widget.WisdomDialog;
import com.ljkj.qxn.wisdomsitepro.Utils.widget.WisdomPopupWindow;
import com.ljkj.qxn.wisdomsitepro.contract.safe.BuildingContract;
import com.ljkj.qxn.wisdomsitepro.data.AuthorityId;
import com.ljkj.qxn.wisdomsitepro.data.entity.BuildingInfo;
import com.ljkj.qxn.wisdomsitepro.data.event.SafeProtectionRefreshEvent;
import com.ljkj.qxn.wisdomsitepro.manager.AuthorityManager;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.model.SafeProtectionModel;
import com.ljkj.qxn.wisdomsitepro.presenter.BuildingPresenter;
import com.ljkj.qxn.wisdomsitepro.ui.safe.adapter.SafeProtectionAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseActivity;
import cdsp.android.util.DisplayUtils;
import cdsp.android.util.ToastUtils;

/**
 * 类描述：安全防护
 * 创建人：lxx
 * 创建时间：2018/9/4
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SafeProtectionActivity extends BaseActivity implements BuildingContract.View {
    @BindView(R.id.tv_title)
    TextView titleText;

    @BindView(R.id.tv_right)
    TextView rightText;

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    @BindView(R.id.iv_no_data)
    ImageView noDataImage;

    @BindView(R.id.tv_add)
    TextView addText;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private SafeProtectionAdapter safeProtectionAdapter;
    private BuildingPresenter buildingPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safe_protection);
    }

    @Override
    protected void initUI() {
        titleText.setText("安全防护");
        rightText.setText("新增");
        rightText.setVisibility(View.GONE);
        refreshLayout.setEnableRefresh(false);
        refreshLayout.setEnableLoadMore(false);
    }

    @Override
    protected void handleAuthority() {
        AuthorityManager manager = AuthorityManager.getInstance();
        manager.handleViewByAuthority(rightText, AuthorityId.SafeManage.SAFE_PROTECT, true);
        manager.handleViewByAuthority(addText, AuthorityId.SafeManage.SAFE_PROTECT, true);
    }

    @Override
    protected void initData() {
        EventBus.getDefault().register(this);
        buildingPresenter = new BuildingPresenter(this, SafeProtectionModel.newInstance());
        addPresenter(buildingPresenter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(safeProtectionAdapter = new SafeProtectionAdapter(null));

        initListener();
        requestData();
    }

    private void requestData() {
        buildingPresenter.getBuildList(UserManager.getInstance().getProjectId());
    }

    private void initListener() {
        safeProtectionAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                BuildingInfo buildingInfo = safeProtectionAdapter.getItem(position);
                FloorsActivity.startActivity(SafeProtectionActivity.this, buildingInfo);
            }
        });

        safeProtectionAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                BuildingInfo buildingInfo = safeProtectionAdapter.getItem(position);
                if (buildingInfo != null) {
                    showOptionsWindow(view, buildingInfo);
                }
                return false;
            }
        });
    }

    @OnClick({R.id.tv_back, R.id.tv_right, R.id.tv_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.tv_right:
                updateBuildingDialog(null, true);
                break;
            case R.id.tv_add:
                showSelectBuildNums();
                break;
            default:
                break;
        }
    }

    private void showOptionsWindow(View view, final BuildingInfo buildingInfo) {
        View contentView = LayoutInflater.from(this).inflate(R.layout.view_safe_protection_options, null);
        final WisdomPopupWindow popupWindow = new WisdomPopupWindow.Builder(this)
                .setView(contentView)
                .setOutsideTouchable(true)
                .setAnimationStyle(R.style.pop_right_top)
                .setWidthAndHeight(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                .setViewOnclickListener(null)
                .build();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            popupWindow.setElevation(DisplayUtils.dip2px(this, 10));
            popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        }
        contentView.findViewById(R.id.tv_option1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //删除楼栋
                buildingPresenter.deleteBuild(buildingInfo.id);
                popupWindow.dismiss();
            }
        });
        contentView.findViewById(R.id.tv_option2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //编辑名称
                popupWindow.dismiss();
                updateBuildingDialog(buildingInfo, false);
            }
        });
        popupWindow.showAsDropDown(view, view.getWidth() / 6, -view.getHeight() / 2);
    }

    private void updateBuildingDialog(final BuildingInfo buildingInfo, final boolean isAdd) {
        final WisdomDialog dialog = new WisdomDialog(this);
        int width = DisplayUtils.getScreenWidth() - DisplayUtils.dip2px(this, 32);

        View view = LayoutInflater.from(this).inflate(R.layout.dialog_add_building, null);
        TextView title = (TextView) view.findViewById(R.id.tv_title);
        if (isAdd) {
            title.setText("新增楼栋");
        } else {
            title.setText("编辑楼栋名称");
        }
        final EditText contentText = (EditText) view.findViewById(R.id.et_content);
        view.findViewById(R.id.tv_sure).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newName = contentText.getText().toString().trim();
                if (TextUtils.isEmpty(newName)) {
                    ToastUtils.showShort("请输入名称");
                    return;
                }
                for (BuildingInfo info : safeProtectionAdapter.getData()) {
                    if (newName.equals(info.buildName)) {
                        ToastUtils.showShort("该名称已被使用，请输入新名称");
                        return;
                    }
                }

                if (isAdd) { //新增楼栋
                    buildingPresenter.addBuild(newName, UserManager.getInstance().getProjectId(), UserManager.getInstance().getProjectCode(), com.ljkj.qxn.wisdomsitepro.manager.UserManager.getInstance().getProjectName());
                } else { //修改楼栋名称
                    buildingPresenter.updateBuild(buildingInfo.id, newName, UserManager.getInstance().getProjectId());
                }
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

    private void showSelectBuildNums() {
        final WisdomDialog dialog = new WisdomDialog(this);
        int width = DisplayUtils.getScreenWidth() - DisplayUtils.dip2px(this, 32);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_select_build_num, null);
        final EditText contentText = (EditText) view.findViewById(R.id.et_content);
        view.findViewById(R.id.tv_sure).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = Integer.parseInt(contentText.getText().toString().trim());
                if (num == 0) {
                    ToastUtils.showShort("请输入楼栋数");
                    return;
                }
                if (num > 50) {
                    ToastUtils.showShort("可输入的最大楼栋数为50");
                    return;
                }
                buildingPresenter.firstAddBuild("", num, UserManager.getInstance().getProjectCode(), UserManager.getInstance().getProjectId(), UserManager.getInstance().getProjectName());
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
    public void showBuildings(List<BuildingInfo> buildingInfoList) {
        safeProtectionAdapter.setNewData(buildingInfoList);
        addText.setVisibility(safeProtectionAdapter.getItemCount() == 0 ? View.VISIBLE : View.GONE);
        rightText.setVisibility(safeProtectionAdapter.getItemCount() == 0 ? View.GONE : View.VISIBLE);
    }

    @Override
    public void showDeleteBuilding() {
        requestData();
        ToastUtils.showShort("删除成功!");
    }

    @Override
    public void showUpdateBuilding() {
        requestData();
        ToastUtils.showShort("修改成功!");
    }

    @Override
    public void showAddBuild() {
        requestData();
        ToastUtils.showShort("新增楼栋成功!");
    }

    @Override
    public void showFirstAddBuild() {
        requestData();
        ToastUtils.showShort("新增楼栋成功!");
    }

    /** @see FloorsActivity */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRefreshEvent(SafeProtectionRefreshEvent event) {
        requestData();
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
