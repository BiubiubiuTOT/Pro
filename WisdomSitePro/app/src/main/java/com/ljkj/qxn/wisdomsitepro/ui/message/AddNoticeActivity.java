package com.ljkj.qxn.wisdomsitepro.ui.message;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.foamtrace.photopicker.PhotoPickerActivity;
import com.foamtrace.photopicker.PhotoPreviewActivity;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.Utils.PhotoPickerHelper;
import com.ljkj.qxn.wisdomsitepro.Utils.widget.InputItemView;
import com.ljkj.qxn.wisdomsitepro.contract.common.ImageCompressorContract;
import com.ljkj.qxn.wisdomsitepro.contract.common.UploadContract;
import com.ljkj.qxn.wisdomsitepro.contract.message.AddNoticeContract;
import com.ljkj.qxn.wisdomsitepro.data.FileEntity;
import com.ljkj.qxn.wisdomsitepro.data.PermissionConstant;
import com.ljkj.qxn.wisdomsitepro.data.entity.SecurityEduTechPerson;
import com.ljkj.qxn.wisdomsitepro.data.event.NoticeRefreshEvent;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.model.CommonModel;
import com.ljkj.qxn.wisdomsitepro.model.MessageModel;
import com.ljkj.qxn.wisdomsitepro.presenter.common.ImageCompressorPresenter;
import com.ljkj.qxn.wisdomsitepro.presenter.common.UploadPresenter;
import com.ljkj.qxn.wisdomsitepro.presenter.message.AddNoticePresenter;
import com.ljkj.qxn.wisdomsitepro.presenter.message.GetNoticeContactPresenter;
import com.ljkj.qxn.wisdomsitepro.ui.common.SelectImageCallback;
import com.ljkj.qxn.wisdomsitepro.ui.common.adapter.SelectImageAdapter;
import com.ljkj.qxn.wisdomsitepro.ui.safe.SelectTeamActivity;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseActivity;
import cdsp.android.util.ToastUtils;

/**
 * 类描述：新增公告
 * 创建人：lxx
 * 创建时间：2018/6/19
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class AddNoticeActivity extends BaseActivity implements AddNoticeContract.View, ImageCompressorContract.View, UploadContract.View {
    private static final int REQUEST_CODE_CONTACT = 100;
    private static final int PREVIEW_CODE = 200;
    private static final int SELECT_CODE = 300;

    @BindView(R.id.tv_title)
    TextView titleText;

    @BindView(R.id.tv_right)
    TextView rightText;

    @BindView(R.id.tv_project_name)
    TextView projectNameText;

    @BindView(R.id.item_title)
    InputItemView titleItem;

    @BindView(R.id.et_contents)
    EditText contentEdit;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.item_range)
    InputItemView rangeItem;

    private List<SecurityEduTechPerson> publishList = new ArrayList<>();

    private SelectImageAdapter imageAdapter;
    private ArrayList<String> imagePaths = new ArrayList<>();
    private List<FileEntity> fileEntities = new ArrayList<>();

    private ImageCompressorPresenter compressorPresenter;
    private UploadPresenter uploadPresenter;
    private AddNoticePresenter addNoticePresenter;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, AddNoticeActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notice);
    }

    @Override
    protected void initUI() {
        projectNameText.setText("当前项目：" + UserManager.getInstance().getProjectName());
        titleText.setText("发布公告");
        rightText.setText("发布");
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void initData() {
        addNoticePresenter = new AddNoticePresenter(this, MessageModel.newInstance());
        compressorPresenter = new ImageCompressorPresenter(this, CommonModel.newInstance());
        uploadPresenter = new UploadPresenter(this, CommonModel.newInstance());
        addPresenter(addNoticePresenter);
        addPresenter(compressorPresenter);
        addPresenter(uploadPresenter);

        recyclerView.setAdapter(imageAdapter = new SelectImageAdapter(this));

        imageAdapter.setSelectImageCallback(new SelectImageCallback() {
            @Override
            public void viewImage(int position) {
                PhotoPickerHelper.startPreview(AddNoticeActivity.this, PREVIEW_CODE, imagePaths, position, true);
            }

            @Override
            public void addImage() {
                AndPermission.with(AddNoticeActivity.this)
                        .runtime()
                        .permission(PermissionConstant.PERMISSIONS_OF_IMAGE)
                        .onGranted(new Action<List<String>>() {
                            @Override
                            public void onAction(List<String> permissions) {
                                PhotoPickerHelper.startPicker(AddNoticeActivity.this, SELECT_CODE, imagePaths, 9, true, true);
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

    @OnClick({R.id.tv_back, R.id.tv_right, R.id.item_range})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.tv_right:
                if (checkData()) {
                    if (imagePaths.size() > 0) {
                        fileEntities.clear();
                        compressorPresenter.compressorImages(imagePaths);
                    } else {
                        showCompressFilePaths(null);
                    }
                }
                break;
            case R.id.item_range:
//                NoticeContactActivity.startActivity(this, REQUEST_CODE_CONTACT);
                SelectTeamActivity.startActivity(this, REQUEST_CODE_CONTACT);
                break;
            default:
                break;
        }
    }

    private boolean checkData() {
        if (TextUtils.isEmpty(titleItem.getContent())) {
            showError("请输入公告标题");
            return false;
        }
        if (TextUtils.isEmpty(contentEdit.getText().toString().trim())) {
            showError("请输入正文内容");
            return false;
        }
        if (publishList == null || publishList.size() == 0) {
            showError("请选择公告通知范围");
            return false;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == SELECT_CODE) {
            imagePaths = data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT);
            imageAdapter.loadData(imagePaths);
        } else if (resultCode == RESULT_OK && requestCode == PREVIEW_CODE) {
            imagePaths = data.getStringArrayListExtra(PhotoPreviewActivity.EXTRA_RESULT);
            imageAdapter.loadData(imagePaths);
        } else if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_CONTACT) {
            publishList = data.getParcelableArrayListExtra("data");
            rangeItem.setContent(publishList.size() + "人");
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    protected void onDestroy() {
        GetNoticeContactPresenter.setCacheList(null);
        super.onDestroy();
    }

    @Override
    public void showCompressFilePaths(List<String> data) {
        if (data == null) {
            showUploadInfo(null);
        } else {
            List<File> files = new ArrayList<>();
            for (String path : data) {
                files.add(new File(path));
            }
            uploadPresenter.upload(UserManager.getInstance().getProjectId(), files);
        }
    }

    @Override
    public void showUploadInfo(List<FileEntity> entities) {
        if (entities != null) {
            for (FileEntity entity : entities) {
                entity.projId = UserManager.getInstance().getProjectId();
                entity.projCode = UserManager.getInstance().getProjectCode();
                fileEntities.add(entity);
            }
        }
        doSubmit();
    }

    @Override
    public void showAddNoticeSuccess(String result) {
        EventBus.getDefault().post(new NoticeRefreshEvent());
        ToastUtils.showShort("发布成功");
        finish();
    }

    private void doSubmit() {
        String title = titleItem.getContent();
        String content = contentEdit.getText().toString().trim();
        StringBuilder acceptId = new StringBuilder();
        StringBuilder acceptName = new StringBuilder();
        for (int i = 0; i < publishList.size(); i++) {
            SecurityEduTechPerson person = publishList.get(i);
            if (TextUtils.isEmpty(person.teamLeaderId) || TextUtils.isEmpty(person.teamName)) {
                continue;
            }
            acceptId.append(publishList.get(i).teamLeaderId);
            acceptName.append(publishList.get(i).teamName);
            if (i != publishList.size() - 1) {
                acceptId.append(",");
                acceptName.append(",");
            }
        }
        addNoticePresenter.addNotice(UserManager.getInstance().getProjectId(), title, content, UserManager.getInstance().getUserId(),
                UserManager.getInstance().getUserName(), acceptId.toString(), acceptName.toString(), fileEntities);
    }

}
