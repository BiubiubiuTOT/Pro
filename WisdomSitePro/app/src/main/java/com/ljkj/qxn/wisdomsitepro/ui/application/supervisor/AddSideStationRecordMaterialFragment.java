package com.ljkj.qxn.wisdomsitepro.ui.application.supervisor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.foamtrace.photopicker.PhotoPickerActivity;
import com.foamtrace.photopicker.PhotoPreviewActivity;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.Utils.PhotoPickerHelper;
import com.ljkj.qxn.wisdomsitepro.data.FileEntity;
import com.ljkj.qxn.wisdomsitepro.data.PermissionConstant;
import com.ljkj.qxn.wisdomsitepro.data.entity.SiteStationRecorderManageDetailInfo;
import com.ljkj.qxn.wisdomsitepro.ui.common.SelectImageCallback;
import com.ljkj.qxn.wisdomsitepro.ui.common.adapter.SelectImageAdapter;
import com.ljkj.qxn.wisdomsitepro.ui.common.adapter.ShowImageAdapter;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import cdsp.android.ui.base.BaseFragment;

/**
 * 类描述：新增旁站记录-料
 * 创建人：lxx
 * 创建时间：2018/8/30
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class AddSideStationRecordMaterialFragment extends BaseFragment implements DataInterface {
    private static final int PREVIEW_CODE = 0x5;
    private static final int SELECT_CODE = 0x6;

    @BindView(R.id.et_contents)
    EditText contentEdit;

    @BindView(R.id.rl_attachment)
    RecyclerView attachmentRV;

    private SelectImageAdapter imageAdapter;
    private ShowImageAdapter showImageAdapter;
    private ArrayList<String> imagePaths = new ArrayList<>();
    private String content;
    private static boolean canEdit;
    private static final String TYPE_FILE = "396";

    public static AddSideStationRecordMaterialFragment newInstance(boolean b) {
        canEdit = b;
        AddSideStationRecordMaterialFragment fragment = new AddSideStationRecordMaterialFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_side_station_record_material, container, false);
    }


    @Override
    protected void initUI() {
        attachmentRV.setNestedScrollingEnabled(false);
        attachmentRV.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        imageAdapter = new SelectImageAdapter(getActivity());
        showImageAdapter = new ShowImageAdapter(getContext());

        if (canEdit) {
            attachmentRV.setAdapter(imageAdapter);
        } else
            attachmentRV.setAdapter(showImageAdapter);
    }

    @Override
    protected void initData() {
        imageAdapter.setSelectImageCallback(new SelectImageCallback() {
            @Override
            public void viewImage(int position) {
                PhotoPickerHelper.startPreview(AddSideStationRecordMaterialFragment.this, PREVIEW_CODE, imagePaths, position, true);
            }

            @Override
            public void addImage() {
                AndPermission.with(getContext())
                        .runtime()
                        .permission(PermissionConstant.PERMISSIONS_OF_IMAGE)
                        .onGranted(new Action<List<String>>() {
                            @Override
                            public void onAction(List<String> permissions) {
                                PhotoPickerHelper.startPicker(AddSideStationRecordMaterialFragment.this, SELECT_CODE, imagePaths, 9, true, true);
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
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case SELECT_CODE:
                    imagePaths = data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT);
                    imageAdapter.loadData(imagePaths);
                    break;

                case PREVIEW_CODE:
                    imagePaths = data.getStringArrayListExtra(PhotoPreviewActivity.EXTRA_RESULT);
                    imageAdapter.loadData(imagePaths);
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void putData(SiteStationRecorderManageDetailInfo info, Map<String, List<String>> map) {
        content = contentEdit.getText().toString();

        info.setMaterialAcceptanceInfo(content);

        if (!imagePaths.isEmpty())
            map.put(TYPE_FILE, imagePaths);
    }

    @Override
    public boolean checkData() {
        content = contentEdit.getText().toString();
        if (TextUtils.isEmpty(content)) {
            showError("请填写主要材料检验情况");
            return false;
        }
        return true;
    }

    @Override
    public void updateUIForDetail(SiteStationRecorderManageDetailInfo info) {
        String materialAcceptanceInfo = info.getMaterialAcceptanceInfo();
        if (TextUtils.isEmpty(materialAcceptanceInfo))
            contentEdit.setText("无");
        else
            contentEdit.setText(materialAcceptanceInfo);

        contentEdit.setEnabled(false);
    }

    @Override
    public void showFiles(List<FileEntity> fileEntities) {
        List<FileEntity> list = new ArrayList<>();
        for (FileEntity fileEntity : fileEntities) {
            if (fileEntity.getType().equals(TYPE_FILE)) {
                list.add(fileEntity);
            }
        }
        showImageAdapter.loadData(list);
    }

}
