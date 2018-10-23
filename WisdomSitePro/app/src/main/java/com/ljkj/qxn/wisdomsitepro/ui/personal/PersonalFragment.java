package com.ljkj.qxn.wisdomsitepro.ui.personal;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.foamtrace.photopicker.PhotoPickerActivity;
import com.ljkj.qxn.wisdomsitepro.BuildConfig;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.Utils.AppUpdateHelper;
import com.ljkj.qxn.wisdomsitepro.Utils.PhotoPickerHelper;
import com.ljkj.qxn.wisdomsitepro.Utils.widget.ItemView;
import com.ljkj.qxn.wisdomsitepro.WApplication;
import com.ljkj.qxn.wisdomsitepro.contract.common.AppUpdateContract;
import com.ljkj.qxn.wisdomsitepro.contract.common.QueryFileContract;
import com.ljkj.qxn.wisdomsitepro.data.FileEntity;
import com.ljkj.qxn.wisdomsitepro.data.PermissionConstant;
import com.ljkj.qxn.wisdomsitepro.data.VersionInfo;
import com.ljkj.qxn.wisdomsitepro.data.api.HostConfig;
import com.ljkj.qxn.wisdomsitepro.manager.BuglyManager;
import com.ljkj.qxn.wisdomsitepro.manager.MiPushManager;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.model.CommonModel;
import com.ljkj.qxn.wisdomsitepro.presenter.common.AppUpdatePresenter;
import com.ljkj.qxn.wisdomsitepro.presenter.common.QueryFilePresenter;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.glide.GlideHelper;
import cdsp.android.ui.base.BaseFragment;
import cdsp.android.ui.widget.MLoadingDialog;
import cdsp.android.util.ToastUtils;

/**
 * 类描述：个人中心
 * 创建人：liufei
 * 创建时间：2018/1/31 15:28
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class PersonalFragment extends BaseFragment implements AppUpdateContract.View, QueryFileContract.View {
    public static final String TAG = PersonalFragment.class.getName();

    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.tv_back)
    TextView backText;

    @BindView(R.id.tv_name)
    TextView tvName;

    @BindView(R.id.tv_job)
    TextView tvJob;

    @BindView(R.id.item_project)
    ItemView projectItem;

    @BindView(R.id.item_update)
    ItemView updateItem;

    @BindView(R.id.iv_avatar)
    ImageView avatarImage;

    private AppUpdatePresenter updatePresenter;
    private QueryFilePresenter queryFilePresenter;
    private boolean hasGetAvatar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal, container, false);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!hasGetAvatar) {
            queryFilePresenter.queryFile(UserManager.getInstance().getUserId());
        }
    }

    @Override
    protected void initUI() {
        tvTitle.setText("我的");
        backText.setVisibility(View.GONE);
        updateItem.setContent("当前版本：" + BuildConfig.VERSION_NAME);
    }

    @Override
    protected void initData() {
        updatePresenter = new AppUpdatePresenter(this, CommonModel.newInstance());
        queryFilePresenter = new QueryFilePresenter(this, CommonModel.newInstance());
        addPresenter(queryFilePresenter);
        addPresenter(updatePresenter);

        tvName.setText(UserManager.getInstance().getUserPhone());
        tvJob.setText(UserManager.getInstance().getUserName());
    }

    @OnClick({R.id.item_project, R.id.item_password, R.id.item_update, R.id.item_about, R.id.ll_login_out, R.id.iv_avatar})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.item_project:
                ProjectListActivity.startActivity(getContext(), ProjectListActivity.PERSONAL_FRAGMENT_ENTER_FLAG);
                break;
            case R.id.item_password:
                startActivity(new Intent(getContext(), ResetPasswordActivity.class));
                break;
            case R.id.item_update:
//                MLoadingDialog.show(getActivity(), "检查更新中...");
//                updatePresenter.checkUpdate();

                checkUpdate();
                break;
            case R.id.item_about:
                AboutActivity.startActivity(mContext);
//                H5Helper.toAboutUs(mContext, "关于我们");
                break;
            case R.id.ll_login_out:
                new AlertDialog.Builder(getContext())
                        .setMessage("退出登录?")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ToastUtils.showShort("退出登录成功");
                                logout();
                            }
                        })
                        .setNegativeButton("取消", null)
                        .show();
                break;
            case R.id.iv_avatar:
//                addImage();
                break;
            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 0) { //图片选择
                ArrayList<String> filePaths = data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT);
            }
        }
    }

    private void logout() {
        MiPushManager.getInstance().doForLogout(WApplication.getApplication());
        UserManager.getInstance().clearLoginInfo();
        startActivity(new Intent(getContext(), LoginActivity.class));
        getActivity().finish();
    }

    private void addImage() {
        AndPermission.with(this)
                .runtime()
                .permission(PermissionConstant.PERMISSIONS_OF_IMAGE)
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> permissions) {
                        PhotoPickerHelper.startPicker(PersonalFragment.this, 0, null, 1, false, true);
                    }
                })
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> permissions) {
                        showError("用户已禁止访问图片权限");
                    }
                })
                .start();
    }

    @Override
    public void showProgress(String message) {
        MLoadingDialog.show(getActivity(), "图片上传中...");
    }

    @Override
    public void hideProgress() {
        MLoadingDialog.dismiss();
    }

    @Override
    public void showVersionInfo(VersionInfo versionInfo) {
        if (BuildConfig.VERSION_CODE < versionInfo.getLastVersionCode()) {
//            versionInfo.setInstallPackageUrl("http://10.2.100.252:18071/CO-CTMS/upload/app/wisdomSitePro_release_2018-04-09_1.0.0.apk");
            AppUpdateHelper.showUpdateDialog(getActivity(), versionInfo);
        } else {
            ToastUtils.showLong("已是最新版本!");
        }
    }


    private void checkUpdate() {
        if (BuglyManager.getInstance().isInit()) {
            BuglyManager.getInstance().checkUpgrade();
        }
    }


    @Override
    public void showFiles(List<FileEntity> fileEntities) {
        hasGetAvatar = true;
        if (fileEntities.size() > 0) {
            GlideHelper.loadCircleImage(getContext(), avatarImage, HostConfig.getFileDownUrl(fileEntities.get(0).fileId), R.mipmap.ic_head_default);
        }
    }

}
