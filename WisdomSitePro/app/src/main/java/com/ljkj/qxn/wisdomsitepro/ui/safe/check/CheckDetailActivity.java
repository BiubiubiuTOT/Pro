package com.ljkj.qxn.wisdomsitepro.ui.safe.check;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.Utils.PhotoPickerHelper;
import com.ljkj.qxn.wisdomsitepro.contract.safe.CheckDetailContract;
import com.ljkj.qxn.wisdomsitepro.data.FileEntity;
import com.ljkj.qxn.wisdomsitepro.data.FileType;
import com.ljkj.qxn.wisdomsitepro.data.api.HostConfig;
import com.ljkj.qxn.wisdomsitepro.data.entity.CheckDetail;
import com.ljkj.qxn.wisdomsitepro.data.entity.CheckInfo;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.model.CommonModel;
import com.ljkj.qxn.wisdomsitepro.model.SafeModel;
import com.ljkj.qxn.wisdomsitepro.presenter.safe.CheckDetailPresenter;
import com.ljkj.qxn.wisdomsitepro.ui.common.RemotePDFActivity;
import com.ljkj.qxn.wisdomsitepro.ui.common.adapter.ShowImageAdapter;
import com.ljkj.qxn.wisdomsitepro.ui.safe.adapter.CheckAttachmentAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;
import cdsp.android.ui.base.BaseActivity;

/**
 * 类描述：检查详情
 * 创建人：lxx
 * 创建时间：2018/9/6
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class CheckDetailActivity extends BaseActivity implements CheckDetailContract.View {

    @BindView(R.id.tv_title)
    TextView titleText;

    @BindView(R.id.tv_project_name)
    TextView projectNameText;

    @BindView(R.id.tv_code)
    TextView codeText;

    @BindView(R.id.check_name)
    TextView nameText;

    @BindView(R.id.tv_check_date)
    TextView checkDateText;

    @BindView(R.id.tv_danger_level)
    TextView dangerLevelText;

    @BindView(R.id.tv_check_position)
    TextView checkPositionText;

    @BindView(R.id.tv_rectify_type)
    TextView rectifyTypeText;

    @BindView(R.id.tv_rectify_unit)
    TextView rectifyUnitText;

    @BindView(R.id.tv_rectify_detail)
    TextView rectifyDetailText;

    @BindView(R.id.rl_images)
    RecyclerView imagesRV;

    @BindView(R.id.rl_attachment)
    RecyclerView attachmentRV;

    @BindView(R.id.ll_container)
    LinearLayout containerLayout;

    @BindView(R.id.tv_submit)
    TextView submitText;

    @BindView(R.id.ll_supervisor)
    LinearLayout supervisorLayout;

    @BindView(R.id.tv_no_pass)
    TextView noPassText; //监理驳回

    @BindView(R.id.tv_pass)
    TextView passText; //监理同意

    private ShowImageAdapter showImageAdapter;
    private CheckAttachmentAdapter checkAttachmentAdapter;
    private int checkType;
    private CheckDetailPresenter presenter;
    private String id;
    private int status;
    private boolean isSupervisorVerify = false; //是否为监理审核流程
    private String reformId;

    /**
     * @param context            context
     * @param checkType          检查类型 CheckListActivity.java
     * @param id                 检查id
     * @param status             检查状态:CheckInfo.java
     * @param isSupervisorVerify 是否为监理审核
     */
    public static void startActivity(Context context, int checkType, String id, int status, boolean isSupervisorVerify) {
        Intent intent = new Intent(context, CheckDetailActivity.class);
        intent.putExtra("checkType", checkType);
        intent.putExtra("id", id);
        intent.putExtra("status", status);
        intent.putExtra("isSupervisorVerify", isSupervisorVerify);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_detail);
    }

    @Override
    protected void initUI() {
        checkType = getIntent().getIntExtra("checkType", CheckListActivity.TYPE_SAFE_CHECK);
        status = getIntent().getIntExtra("status", CheckInfo.WAIT_RECTIFY);
        isSupervisorVerify = getIntent().getBooleanExtra("isSupervisorVerify", false);
        titleText.setText("详情");

        if (status == CheckInfo.WAIT_RECTIFY) {
            submitText.setText("立即整改");
            submitText.setVisibility(View.VISIBLE);
        } else if (status == CheckInfo.WAIT_AUDITED) {
            submitText.setText("待审核");
        } else if (status == CheckInfo.RE_RECTIFY) {
            submitText.setText("重新整改");
            submitText.setVisibility(View.VISIBLE);
        } else if (status == CheckInfo.QUALIFIED) {
            submitText.setText("合格");
        } else {
            supervisorLayout.setVisibility(View.GONE);
            submitText.setVisibility(View.GONE);
        }

        imagesRV.setLayoutManager(new GridLayoutManager(this, 4));
        imagesRV.setAdapter(showImageAdapter = new ShowImageAdapter(this));
        imagesRV.setNestedScrollingEnabled(false);
        attachmentRV.setLayoutManager(new LinearLayoutManager(this));
        attachmentRV.setAdapter(checkAttachmentAdapter = new CheckAttachmentAdapter(null));
        attachmentRV.setNestedScrollingEnabled(false);
    }

    @Override
    protected void initData() {
        id = getIntent().getStringExtra("id");
        presenter = new CheckDetailPresenter(this, SafeModel.newInstance());
        addPresenter(presenter);
        checkAttachmentAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                FileEntity fileEntity = checkAttachmentAdapter.getItem(position);
                if (fileEntity == null) {
                    return;
                }
                String extend = fileEntity.fileExt;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && "pdf".equals(extend)) {
                    RemotePDFActivity.startActivity(CheckDetailActivity.this, HostConfig.getFileDownUrl(fileEntity.fileId));
                } else if ("jpg".equals(extend) || "jpeg".equals(extend) || "png".equals(extend)) {
                    PhotoPickerHelper.startPreview(CheckDetailActivity.this, HostConfig.getFileDownUrl(fileEntity.fileId));
                } else {
                    jumpToBrowser(HostConfig.getFileDownUrl(fileEntity.fileId));
                }
            }
        });

        if (checkType == SupervisorCheckListActivity.TYPE_SUPERVISOR_QUALITY_CHECK || checkType == SupervisorCheckListActivity.TYPE_SUPERVISOR_SAFE_CHECK) {
            presenter.getSupervisorCheckDetail(id);
        } else {
            if (checkType == CheckListActivity.TYPE_SAFE_CHECK) {
                presenter.getSafeCheckDetail(id);
            } else {
                presenter.getQualityCheckDetail(id);
            }
        }
    }

    private void jumpToBrowser(String path) {
        if (TextUtils.isEmpty(path)) {
            return;
        }
        Uri uri = Uri.parse(path);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    @OnClick({R.id.tv_back, R.id.tv_submit, R.id.tv_no_pass, R.id.tv_pass})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.tv_submit:
                switch (status) {
                    case CheckInfo.WAIT_RECTIFY: //立即整改
                        CheckImmediateRectifyActivity.startActivity(this, id, checkType, CheckImmediateRectifyActivity.RECTIFY);
                        break;
                    case CheckInfo.RE_RECTIFY: //重新整改
                        CheckImmediateRectifyActivity.startActivity(this, id, checkType, CheckImmediateRectifyActivity.RE_RECTIFY);
                        break;
                    case CheckInfo.WAIT_AUDITED: //待审核
                        break;
                    case CheckInfo.QUALIFIED: //合格
                        break;
                    default:
                        break;
                }
                break;
            case R.id.tv_no_pass: //监理驳回
                SupervisorVerifyActivity.startActivity(this, false, reformId, checkType);
                break;
            case R.id.tv_pass: //监理同意
                SupervisorVerifyActivity.startActivity(this, true, reformId, checkType);
                break;
            default:
                break;
        }
    }

    @Override
    public void showSafeCheckDetail(CheckDetail checkDetail) {
        showDetail(checkDetail);
    }

    @SuppressLint("SetTextI18n")
    private void showDetail(CheckDetail checkDetail) {
        CheckDetail.Check check = checkDetail.check;
        if (check == null) {
            return;
        }
        if (checkDetail.reforms != null && checkDetail.reforms.size() > 0) {
            this.reformId = checkDetail.reforms.get(checkDetail.reforms.size() - 1).id;
            supervisorLayout.setVisibility(isSupervisorVerify ? View.VISIBLE : View.GONE);
        }
        projectNameText.setText("项目名称：" + UserManager.getInstance().getProjectName());
        if (checkType == CheckListActivity.TYPE_SAFE_CHECK) {
            codeText.setText("安全检查编号：" + check.checkCode);
        } else {
            codeText.setText("质量检查编号：" + check.checkCode);
        }

        nameText.setText("检查人员：" + check.checkerName);
        checkDateText.setText("检查日期：" + check.checkDate);

        String grade = check.reformGrade;
        if ("1".equals(grade)) {
            grade = "一般隐患";
        } else if ("2".equals(grade)) {
            grade = "重大隐患";
        }
        dangerLevelText.setText("隐患等级：" + grade);

        checkPositionText.setText("检查部位：" + check.checkPosition);

        String reformType = check.reformType;
        if ("1".equals(reformType)) {
            reformType = "限期整改";
        } else if ("2".equals(reformType)) {
            reformType = "局部停工整改";
        } else if ("3".equals(reformType)) {
            reformType = "停工挂牌督办";
        }
        rectifyTypeText.setText("整改类型：" + reformType);

        rectifyUnitText.setText("整改单位：" + check.reformUnit);
        rectifyDetailText.setText(check.checkInfo);
        queryCheckFileId(checkDetail);

        if (status != CheckInfo.WAIT_RECTIFY) {
            addRectifyReply(checkDetail);
            queryReformFileId(checkDetail);
        }
    }

    @Override
    public void showQualityCheckDetail(CheckDetail checkDetail) {
        showDetail(checkDetail);
    }

    @Override
    public void showSupervisorCheckDetail(CheckDetail checkDetail) {
        showDetail(checkDetail);
    }

    //添加整改回复
    private void addRectifyReply(CheckDetail checkDetail) {
        if (checkDetail.reforms == null || checkDetail.reforms.size() == 0) {
            return;
        }
        containerLayout.removeAllViews();
        for (int i = 1, size = checkDetail.reforms.size(); i <= size; i++) {
            CheckDetail.Reform reform = checkDetail.reforms.get(i - 1);
            RectifyReplyView rectifyReplyView = new RectifyReplyView(this);
            rectifyReplyView.setData(checkDetail.check, reform, i);
            containerLayout.addView(rectifyReplyView);
            if (i < size) {
                rectifyReplyView.collapse();
                rectifyReplyView.setRecallTextVisibility(false);
                rectifyReplyView.setReasonText(reform);
            }
            rectifyReplyView.setOnRecallListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO 撤回逻辑
                }
            });
        }
    }

    private void queryCheckFileId(CheckDetail checkDetail) {
        String id;
        if (checkType == SupervisorCheckListActivity.TYPE_SUPERVISOR_SAFE_CHECK || checkType == SupervisorCheckListActivity.TYPE_SUPERVISOR_QUALITY_CHECK) {
            id = checkDetail.check.checkId;
        } else {
            id = checkDetail.check.id;
        }
        CommonModel.newInstance().queryFile(id, new JsonCallback<ResponseData<List<FileEntity>>>(new TypeToken<ResponseData<List<FileEntity>>>() {
        }) {
            @Override
            protected void onError(int errcode, String errmsg) {
            }

            @Override
            public void onSuccess(ResponseData<List<FileEntity>> data) {
                if (isDestroy()) {
                    return;
                }
                List<FileEntity> fileEntities = data.getResult();
                List<FileEntity> dangerList = new ArrayList<>();
                List<FileEntity> attachmentList = new ArrayList<>();
                for (FileEntity entity : fileEntities) {
                    if (TextUtils.equals(entity.type, FileType.SafeCheck.TYPE_SAFE_CHECK_DANGER)) {
                        FileEntity enclosureInfo = new FileEntity();
                        enclosureInfo.setFileId(entity.fileId);
                        dangerList.add(enclosureInfo);
                    } else if (TextUtils.equals(entity.type, FileType.SafeCheck.TYPE_SAFE_CHECK_ATTACHMENT)) {
                        attachmentList.add(entity);
                    }
                }
                showImageAdapter.loadData(dangerList);
                checkAttachmentAdapter.setNewData(attachmentList);
            }
        });
    }

    private void queryReformFileId(CheckDetail checkDetail) {
        if (checkDetail.reforms == null) {
            return;
        }
        for (int i = 0; i < checkDetail.reforms.size(); i++) {
            final RectifyReplyView rectifyReplyView = (RectifyReplyView) containerLayout.getChildAt(i);
            String id;
            if (checkType == SupervisorCheckListActivity.TYPE_SUPERVISOR_SAFE_CHECK || checkType == SupervisorCheckListActivity.TYPE_SUPERVISOR_QUALITY_CHECK) {
                id = checkDetail.reforms.get(i).reformId;
            } else {
                id = checkDetail.reforms.get(i).id;
            }
            CommonModel.newInstance().queryFile(id, new JsonCallback<ResponseData<List<FileEntity>>>(new TypeToken<ResponseData<List<FileEntity>>>() {
            }) {
                @Override
                protected void onError(int errcode, String errmsg) {
                }

                @Override
                public void onSuccess(ResponseData<List<FileEntity>> data) {
                    if (isDestroy()) {
                        return;
                    }
                    List<FileEntity> fileEntities = data.getResult();
                    rectifyReplyView.showFiles(fileEntities);
                }
            });
        }
    }

}
