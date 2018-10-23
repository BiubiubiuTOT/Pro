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
 * 类描述：新增旁站记录-人
 * 创建人：lxx
 * 创建时间：2018/8/30
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class AddSideStationRecordPersonFragment extends BaseFragment implements DataInterface {
    private static final int PREVIEW_CODE = 0x1;
    private static final int SELECT_CODE = 0x2;

    @BindView(R.id.rl_attachment)
    RecyclerView attachmentRV;

    @BindView(R.id.item_security_inspector)
    InputItemView securityInspectorItem;

    @BindView(R.id.item_quality_inspector)
    InputItemView qualityInspectorItem;

    @BindView(R.id.item_special_operator_numbers)
    InputItemView specialOperatorNumbersItem;

    @BindView(R.id.item_special_operator_certificate_numbers)
    InputItemView specialOperatorCertificateNumbersItem;

    private SelectImageAdapter imageAdapter;
    private ShowImageAdapter showImageAdapter;
    private ArrayList<String> imagePaths = new ArrayList<>();
    private String securityInspector;
    private String qualityInspector;
    private String specialOperatorNumbers;
    private String specialOperatorCertificateNumbers;
    private static boolean canEdit;
    private final static String TYPE_FILE = "394";

    public static AddSideStationRecordPersonFragment newInstance(boolean b) {
        canEdit = b;
        AddSideStationRecordPersonFragment fragment = new AddSideStationRecordPersonFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_side_station_record_person, container, false);
    }


    @Override
    protected void initUI() {
        attachmentRV.setNestedScrollingEnabled(false);
        attachmentRV.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        showImageAdapter = new ShowImageAdapter(mContext);
        imageAdapter = new SelectImageAdapter(mContext);

        specialOperatorNumbersItem.setTag(0);
        specialOperatorCertificateNumbersItem.setTag(0);


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
                PhotoPickerHelper.startPreview(AddSideStationRecordPersonFragment.this, PREVIEW_CODE, imagePaths, position, true);
            }

            @Override
            public void addImage() {
                AndPermission.with(getContext())
                        .runtime()
                        .permission(PermissionConstant.PERMISSIONS_OF_IMAGE)
                        .onGranted(new Action<List<String>>() {
                            @Override
                            public void onAction(List<String> permissions) {
                                PhotoPickerHelper.startPicker(AddSideStationRecordPersonFragment.this, SELECT_CODE, imagePaths, 9, true, true);
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

    @OnClick({R.id.item_special_operator_numbers, R.id.item_special_operator_certificate_numbers})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.item_special_operator_numbers: //特种作业人员人数
                showNumberPicker(specialOperatorNumbersItem);
                break;
            case R.id.item_special_operator_certificate_numbers: //特种作业人员持证人数
                showNumberPicker(specialOperatorCertificateNumbersItem);
                break;
            default:
                break;
        }
    }

    private void showNumberPicker(final InputItemView inputItemView) {
        if (!canEdit) return;

        int select = (int) inputItemView.getTag();
        PickerDialogHelper.showNumberPicker(getActivity(), 1, 40, select, true, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                inputItemView.setContent(String.valueOf(1 + options1) + "人");
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
        securityInspector = securityInspectorItem.getContent();
        qualityInspector = qualityInspectorItem.getContent();
        specialOperatorNumbers = TextUtils.isEmpty(specialOperatorNumbersItem.getContent()) ? "" : specialOperatorNumbersItem.getContent().substring(0, specialOperatorNumbersItem.getContent().length() - 1);
        specialOperatorCertificateNumbers = TextUtils.isEmpty(specialOperatorCertificateNumbersItem.getContent()) ? "" : specialOperatorCertificateNumbersItem.getContent().substring(0, specialOperatorNumbersItem.getContent().length() - 1);

        info.setQualityInspector(qualityInspector);
        info.setSecurityInspector(securityInspector);
        info.setSpecialCertifiedNum(specialOperatorCertificateNumbers);
        info.setSpecialOperators(specialOperatorNumbers);

        if (!imagePaths.isEmpty())
            map.put(TYPE_FILE, imagePaths);
    }

    @Override
    public boolean checkData() {
        securityInspector = securityInspectorItem.getContent();
        qualityInspector = qualityInspectorItem.getContent();
        specialOperatorNumbers = TextUtils.isEmpty(specialOperatorNumbersItem.getContent()) ? "" : specialOperatorNumbersItem.getContent().substring(0, specialOperatorNumbersItem.getContent().length() - 1);
        specialOperatorCertificateNumbers = TextUtils.isEmpty(specialOperatorCertificateNumbersItem.getContent()) ? "" : specialOperatorCertificateNumbersItem.getContent().substring(0, specialOperatorNumbersItem.getContent().length() - 1);

        if (TextUtils.isEmpty(securityInspector)) {
            showError("请填写安检员");
            return false;
        }

        if (TextUtils.isEmpty(qualityInspector)) {
            showError("请填写质检员");
            return false;
        }

        if (TextUtils.isEmpty(specialOperatorNumbers)) {
            showError("请填写特种作业人员人数");
            return false;
        }

        if (TextUtils.isEmpty(specialOperatorCertificateNumbers)) {
            showError("请填写特种作业人员持证人数");
            return false;
        }

        return true;
    }

    @Override
    public void updateUIForDetail(SiteStationRecorderManageDetailInfo info) {
        specialOperatorCertificateNumbersItem.showArrow(false);
        specialOperatorCertificateNumbersItem.setEnable(false);
        specialOperatorNumbersItem.showArrow(false);
        specialOperatorNumbersItem.setEnable(false);
        securityInspectorItem.setEnable(false);
        qualityInspectorItem.setEnable(false);

        String securityInspector = info.getSecurityInspector();
        if (TextUtils.isEmpty(securityInspector)) {
            securityInspectorItem.setContent("无");
        } else
            securityInspectorItem.setContent(securityInspector);


        String qualityInspector = info.getQualityInspector();
        if (TextUtils.isEmpty(qualityInspector)) {
            qualityInspectorItem.setContent("无");
        } else
            qualityInspectorItem.setContent(qualityInspector);

        String specialCertifiedNum = info.getSpecialCertifiedNum();

        if (!TextUtils.isEmpty(specialCertifiedNum))
            specialOperatorCertificateNumbersItem.setContent(specialCertifiedNum + "人");
        else
            specialOperatorCertificateNumbersItem.setContent("无");


        String specialOperators = info.getSpecialOperators();
        if (!TextUtils.isEmpty(specialOperators))
            specialOperatorNumbersItem.setContent(specialOperators + "人");
        else
            specialOperatorNumbersItem.setContent("无");

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
