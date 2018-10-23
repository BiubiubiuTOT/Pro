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

import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.foamtrace.photopicker.PhotoPickerActivity;
import com.foamtrace.photopicker.PhotoPreviewActivity;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.Utils.PhotoPickerHelper;
import com.ljkj.qxn.wisdomsitepro.Utils.PickerDialogHelper;
import com.ljkj.qxn.wisdomsitepro.Utils.widget.InputItemView;
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
import butterknife.OnClick;
import cdsp.android.ui.base.BaseFragment;

/**
 * 类描述：新增旁站记录-机
 * 创建人：lxx
 * 创建时间：2018/8/30
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class AddSideStationRecordMachineFragment extends BaseFragment implements DataInterface {
    private static final int PREVIEW_CODE = 0x3;
    private static final int SELECT_CODE = 0x4;

    @BindView(R.id.rl_attachment)
    RecyclerView attachmentRV;

    @BindView(R.id.item_machine)
    InputItemView machineItem;

    @BindView(R.id.item_machine_number)
    InputItemView machineNumberItem;

    private SelectImageAdapter imageAdapter;
    private ShowImageAdapter showImageAdapter;
    private ArrayList<String> imagePaths = new ArrayList<>();
    private String machine;
    private String machineNumber;
    private static boolean canEdit;
    private static final String TYPE_FILE = "395";

    public static AddSideStationRecordMachineFragment newInstance(boolean b) {
        canEdit = b;
        AddSideStationRecordMachineFragment fragment = new AddSideStationRecordMachineFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_side_station_record_machine, container, false);
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
                PhotoPickerHelper.startPreview(AddSideStationRecordMachineFragment.this, PREVIEW_CODE, imagePaths, position, true);
            }

            @Override
            public void addImage() {
                AndPermission.with(getContext())
                        .runtime()
                        .permission(PermissionConstant.PERMISSIONS_OF_IMAGE)
                        .onGranted(new Action<List<String>>() {
                            @Override
                            public void onAction(List<String> permissions) {
                                PhotoPickerHelper.startPicker(AddSideStationRecordMachineFragment.this, SELECT_CODE, imagePaths, 9, true, true);
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

    @OnClick({R.id.item_machine_number})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.item_machine_number:
                if (!canEdit) return;

                String content = machineNumberItem.getContent();
                if (content.contains("台")) {
                    content = content.substring(0, content.length() - 1);
                }
                int select = TextUtils.isEmpty(content) ? 0 : Integer.parseInt(content) - 1;
                PickerDialogHelper.showNumberPicker(getActivity(), 1, 40, select, true, new OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                        machineNumberItem.setContent(String.valueOf(1 + options1) + "个");
                    }
                });
                break;
            default:
                break;
        }
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
        machine = machineItem.getContent();
        machineNumber = TextUtils.isEmpty(machineNumberItem.getContent()) ? "" : machineNumberItem.getContent().substring(0, machineNumberItem.getContent().length() - 1);

        info.setMachineEquipment(machine);
        info.setMachineEquipmentNum(machineNumber);

        if (!imagePaths.isEmpty())
            map.put(TYPE_FILE, imagePaths);
    }

    @Override
    public boolean checkData() {
        machine = machineItem.getContent();
        machineNumber = TextUtils.isEmpty(machineNumberItem.getContent()) ? "" : machineNumberItem.getContent().substring(0, machineNumberItem.getContent().length() - 1);

        if (TextUtils.isEmpty(machine)) {
            showError("请填写主要机械设备");
            return false;
        }
        if (TextUtils.isEmpty(machineNumber)) {
            showError("请填写主要机械设备数量");
            return false;
        }
        return true;
    }

    @Override
    public void updateUIForDetail(SiteStationRecorderManageDetailInfo info) {
        canEdit = false;

        machineItem.setEnable(false);
        machineNumberItem.setEnable(false);
        machineNumberItem.showArrow(false);

        String machineEquipment = info.getMachineEquipment();
        if (TextUtils.isEmpty(machineEquipment))
            machineItem.setContent("无");
        else
            machineItem.setContent(machineEquipment);

        String machineEquipmentNum = info.getMachineEquipmentNum();
        if (!TextUtils.isEmpty(machineEquipmentNum))
            machineNumberItem.setContent(machineEquipmentNum + "台");
        else
            machineNumberItem.setContent("无");

//        showImageAdapter.loadData();
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
