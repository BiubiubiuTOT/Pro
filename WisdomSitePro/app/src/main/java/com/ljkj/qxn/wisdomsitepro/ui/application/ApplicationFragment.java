package com.ljkj.qxn.wisdomsitepro.ui.application;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.Utils.H5Helper;
import com.ljkj.qxn.wisdomsitepro.data.ApplicationItemEntity;
import com.ljkj.qxn.wisdomsitepro.data.AuthorityId;
import com.ljkj.qxn.wisdomsitepro.data.ProItemEntity;
import com.ljkj.qxn.wisdomsitepro.manager.AuthorityManager;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.ui.application.adapter.ApplicationAdapter;
import com.ljkj.qxn.wisdomsitepro.ui.application.equipment.SpecialEquipmentActivity;
import com.ljkj.qxn.wisdomsitepro.ui.application.supervisor.SupervisorManageActivity;
import com.ljkj.qxn.wisdomsitepro.ui.common.WebViewActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cdsp.android.ui.base.BaseFragment;


/**
 * 类描述：应用模块
 * 创建人：mhl
 * 创建时间：2018/2/1 14:51
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class ApplicationFragment extends BaseFragment {
    public static final String TAG = ApplicationFragment.class.getName();
    public static List<ApplicationItemEntity> items = new ArrayList<>();

    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.tv_back)
    TextView backText;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private ApplicationAdapter applicationAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_application, container, false);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void initUI() {
        tvTitle.setText("应用中心");
        backText.setVisibility(View.GONE);
    }

    @Override
    protected void initData() {
        initItem();
        applicationAdapter = new ApplicationAdapter(R.layout.adapter_application_content, R.layout.adapter_application_header, ApplicationFragment.items);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));

        recyclerView.setAdapter(applicationAdapter);
        applicationAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ApplicationItemEntity entity = applicationAdapter.getItem(position);
                if (entity == null || entity.isHeader) {
                    return;
                }
                int type = entity.t.type;
                switch (type) {
                    case ApplicationItemEntity.TYPE_ENVIRONMENT:
                        H5Helper.toEnvironmentManager(mContext, "环境监测", UserManager.getInstance().getProjectId());
                        break;
                    case ApplicationItemEntity.TYPE_LAN_LING_QUAN:
                        Intent intent = new Intent();
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setClassName("com.ljkj.bluecollar", "com.ljkj.bluecollar.ui.SplashActivity");

                        if (getContext().getPackageManager().resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY) != null) {
                            startActivity(intent);
                        } else {
                            toAppShop(getActivity(), "com.ljkj.bluecollar");
                        }

                        break;
                    case ApplicationItemEntity.TYPE_QING_YI_ZHU:
                        Intent intent1 = new Intent();
                        intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent1.setClassName("com.ljkj.cyanrent", "com.ljkj.cyanrent.ui.SplashActivity");

                        if (getContext().getPackageManager().resolveActivity(intent1, PackageManager.MATCH_DEFAULT_ONLY) != null) {
                            startActivity(intent1);
                        } else {
                            toAppShop(getActivity(), "com.ljkj.cyanrent");
                        }

                        break;
                    case ApplicationItemEntity.BIM_TEMP:
                        //TODO
                        String url = "http://111.85.152.35:18071/CO-CTMS/m/laborController.do?bimApplication";
                        WebViewActivity.startActivity(getContext(), "BIM应用", url);
                        break;
                    default:
                        Class<? extends Activity> act = entity.t.clazz;
                        if (act != null) {
                            startActivity(new Intent(getContext(), act));
                        } else {
                            showError("正在开发中，敬请期待!");
                        }
                        break;
                }
            }
        });
    }

    private void toAppShop(Activity activity, String packageName) {
        try {
            Uri uri = Uri.parse("market://details?id=" + packageName);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            activity.startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(activity, "您没有安装应用市场", Toast.LENGTH_SHORT).show();
        }
    }

    private void initItem() {
        items.clear();
        items.add(createItem(true, "日常应用", null));
        if (!AuthorityManager.getInstance().findAuthorityById(AuthorityId.SAFE_MANAGE).isHide()) {
            items.add(createItem(false, null, new ProItemEntity("安全管理", R.mipmap.ic_application_safe_check, SafeManagerActivity.class, 0)));
        }
        if (!AuthorityManager.getInstance().findAuthorityById(AuthorityId.QUALITY_MANAGE).isHide()) {
            items.add(createItem(false, null, new ProItemEntity("质量管理", R.mipmap.ic_application_quality_check, QualityManagerActivity.class, 0)));
        }
        if (!AuthorityManager.getInstance().findAuthorityById(AuthorityId.DataManage.CONSTRUCT_LOG).isHide()) {
            items.add(createItem(false, null, new ProItemEntity("施工日志", R.mipmap.ic_application_log, ConstructLogListActivity.class, 0)));
        }
        if (!AuthorityManager.getInstance().findAuthorityById(AuthorityId.EquipmentManage.TOWER_CRANE).isHide()) {
            items.add(createItem(false, null, new ProItemEntity("特种设备", R.mipmap.ic_application_equipment, SpecialEquipmentActivity.class, 111)));
        }

        items.add(createItem(true, "高频应用", null));

        if (!AuthorityManager.getInstance().findAuthorityById(AuthorityId.LABOR_MANAGER).isHide()) {
            items.add(createItem(false, null, new ProItemEntity("劳务管理", R.mipmap.ic_application_labour, LabourManagerActivity.class, 0)));
        }
        if (!AuthorityManager.getInstance().findAuthorityById(AuthorityId.VideoManage.VIDEO_MONITOR).isHide()) {
            items.add(createItem(false, null, new ProItemEntity("视频监控", R.mipmap.ic_application_video_monitor, VideoMonitorActivity.class, 0)));
        }
        if (!AuthorityManager.getInstance().findAuthorityById(AuthorityId.EnvironmentManage.ENVIRONMENT_MONITOR).isHide()) {
            items.add(createItem(false, null, new ProItemEntity("环境监测", R.mipmap.ic_application_environment, null, ApplicationItemEntity.TYPE_ENVIRONMENT)));
        }
        if (!AuthorityManager.getInstance().findAuthorityById(AuthorityId.VideoManage.MOBILE_LAW).isHide()) {
            items.add(createItem(false, null, new ProItemEntity("移动执法", R.mipmap.ic_application_mobile_law, MobileLawActivity.class, 0)));
        }
        items.add(createItem(false, null, new ProItemEntity("进度管理", R.mipmap.ic_application_progress_manager, null, 0)));
        items.add(createItem(false, null, new ProItemEntity("合同管理", R.mipmap.ic_application_contract_manager, null, 0)));
        items.add(createItem(false, null, new ProItemEntity("材料管理", R.mipmap.ic_application_material_manager, null, 0)));
        if (UserManager.getInstance().isSupervisor()) {
            items.add(createItem(false, null, new ProItemEntity("监理管理", R.mipmap.ic_application_supervisor_manager, SupervisorManageActivity.class, 0)));
        }
        items.add(createItem(false, null, new ProItemEntity("BIM应用", R.mipmap.ic_application_bim, null, ApplicationItemEntity.BIM_TEMP)));

        items.add(createItem(true, "外部应用", null));
        items.add(createItem(false, null, new ProItemEntity("青易筑", R.mipmap.ic_application_qyz, null, ApplicationItemEntity.TYPE_QING_YI_ZHU)));
        items.add(createItem(false, null, new ProItemEntity("蓝领圈", R.mipmap.ic_application_llq, null, ApplicationItemEntity.TYPE_LAN_LING_QUAN)));
    }

    private static ApplicationItemEntity createItem(boolean isHeader, String header, ProItemEntity proItemEntity) {
        if (isHeader) {
            return new ApplicationItemEntity(true, header);
        } else {
            return new ApplicationItemEntity(proItemEntity);
        }
    }


}
