package com.ljkj.qxn.wisdomsitepro.ui.contacts;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.Utils.PhotoPickerHelper;
import com.ljkj.qxn.wisdomsitepro.data.api.HostConfig;
import com.ljkj.qxn.wisdomsitepro.data.entity.contract.QueryMemberInfo;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.glide.GlideHelper;
import cdsp.android.ui.base.BaseActivity;

/**
 * 类描述：成员信息
 * 创建人：rjf
 * 创建时间：2018/6/26 15:10.
 * 修改人：
 * 修改时间
 * 修改备注
 */

public class MemberInfoActivity extends BaseActivity {

    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.tv_manager_tag)
    TextView tvManagerTag;
    @BindView(R.id.iv_avatar)
    ImageView ivAvatar;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_item_name)
    TextView tvItemName;
    @BindView(R.id.tv_item_department)
    TextView tvItemDepartment;
    @BindView(R.id.tv_item_position)
    TextView tvItemPosition;
    @BindView(R.id.tv_item_phone)
    TextView tvItemPhone;
    @BindView(R.id.iv_link_phone)
    ImageView ivLinkPhone;

    QueryMemberInfo queryMemberInfo;
    private String department;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_info);
    }

    @Override
    protected void initUI() {
        tvTitle.setText("个人信息");
    }

    @Override
    protected void initData() {
        queryMemberInfo = getIntent().getParcelableExtra("data");
        department = getIntent().getStringExtra("department");
        if (queryMemberInfo != null) {
//            boolean isPmRole = queryMemberInfo.getRole().equals("isPmRole");
//            if (isPmRole) tvManagerTag.setVisibility(View.VISIBLE);

            tvItemName.setText(queryMemberInfo.getName());
            tvName.setText(queryMemberInfo.getName());
            tvItemDepartment.setText(department);
            tvItemPosition.setText(queryMemberInfo.getRole());
            tvItemPhone.setText(queryMemberInfo.getPhone());

            String photoId = queryMemberInfo.getPhotoId();
            if (photoId != null) {
                url = HostConfig.getFileDownUrl(photoId);
                GlideHelper.loadCircleImage(this, ivAvatar,
                        url, R.mipmap.ic_head_default);
            }
        }
    }

    @OnClick({R.id.tv_back, R.id.iv_link_phone, R.id.iv_avatar})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.iv_link_phone:
                callPhone();
                break;
            case R.id.iv_avatar:
                PhotoPickerHelper.startPreview(this, url);
                break;
        }
    }

    private void callPhone() {
        AndPermission.with(this)
                .runtime()
                .permission(Manifest.permission.CALL_PHONE)
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> permissions) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:" + queryMemberInfo.getPhone()));
                        startActivity(intent);
                    }
                })
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> permissions) {
                        showError("用户已禁止访问电话权限");
                    }
                })
                .start();
    }
}
