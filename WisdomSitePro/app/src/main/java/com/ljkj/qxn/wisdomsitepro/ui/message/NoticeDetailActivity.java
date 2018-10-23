package com.ljkj.qxn.wisdomsitepro.ui.message;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ljkj.qxn.wisdomsitepro.MainActivity;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.Utils.PhotoPickerHelper;
import com.ljkj.qxn.wisdomsitepro.contract.common.QueryFileContract;
import com.ljkj.qxn.wisdomsitepro.contract.message.NoticeDetailContract;
import com.ljkj.qxn.wisdomsitepro.data.FileEntity;
import com.ljkj.qxn.wisdomsitepro.data.api.HostConfig;
import com.ljkj.qxn.wisdomsitepro.data.event.NoticeRefreshEvent;
import com.ljkj.qxn.wisdomsitepro.data.message.NoticeInfo;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.model.CommonModel;
import com.ljkj.qxn.wisdomsitepro.model.MessageModel;
import com.ljkj.qxn.wisdomsitepro.presenter.common.QueryFilePresenter;
import com.ljkj.qxn.wisdomsitepro.presenter.message.GetNoticeDetailPresenter;
import com.ljkj.qxn.wisdomsitepro.ui.common.RemotePDFActivity;
import com.ljkj.qxn.wisdomsitepro.ui.safe.adapter.CheckAttachmentAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseActivity;

/**
 * 类描述：公告详情
 * 创建人：lxx
 * 创建时间：2018/6/22
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class NoticeDetailActivity extends BaseActivity implements NoticeDetailContract.View, QueryFileContract.View {
    private static final String KEY_NOTICE_ID = "key_notice_id";
    private static final String KEY_NOTICE_PUSH = "KEY_NOTICE_PUSH";

    @BindView(R.id.tv_title)
    TextView titleText;

    @BindView(R.id.tv_notice_title)
    TextView noticeTitleText;

    @BindView(R.id.tv_publisher)
    TextView publisherText;

    @BindView(R.id.tv_read)
    TextView readText;

    @BindView(R.id.tv_content)
    TextView contentText;

    @BindView(R.id.tv_attachment_title)
    TextView attachmentTitleText;

    @BindView(R.id.tv_receiver)
    TextView receiverText;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private CheckAttachmentAdapter attachmentAdapter;
    private GetNoticeDetailPresenter detailPresenter;
    private QueryFilePresenter queryFilePresenter;
    private String noticeId;
    private boolean fromPush;

    /**
     * @param context  context
     * @param noticeId 公告id
     * @param fromPush 是否从推送调整而来
     */
    public static void startActivity(@NonNull Context context, @NonNull String noticeId, boolean fromPush) {
        Intent intent = new Intent(context, NoticeDetailActivity.class);
        intent.putExtra(KEY_NOTICE_ID, noticeId);
        intent.putExtra(KEY_NOTICE_PUSH, fromPush);
        if (fromPush) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_detail);
    }

    @Override
    protected void initUI() {
        titleText.setText("公告详情");
        noticeId = getIntent().getStringExtra(KEY_NOTICE_ID);
        fromPush = getIntent().getBooleanExtra(KEY_NOTICE_PUSH, false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setNestedScrollingEnabled(false);
    }

    @Override
    protected void initData() {
        detailPresenter = new GetNoticeDetailPresenter(this, MessageModel.newInstance());
        queryFilePresenter = new QueryFilePresenter(this, CommonModel.newInstance());
        addPresenter(queryFilePresenter);
        addPresenter(detailPresenter);
        recyclerView.setAdapter(attachmentAdapter = new CheckAttachmentAdapter(null));
        detailPresenter.getNoticeDetail(noticeId, UserManager.getInstance().getUserId(), "张毓军");

        attachmentAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                FileEntity fileEntity = attachmentAdapter.getItem(position);
                if (fileEntity == null) {
                    return;
                }
                String extend = fileEntity.fileExt;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && "pdf".equals(extend)) {
                    RemotePDFActivity.startActivity(NoticeDetailActivity.this, HostConfig.getFileDownUrl(fileEntity.fileId));
                } else if ("jpg".equals(extend) || "jpeg".equals(extend) || "png".equals(extend)) {
                    PhotoPickerHelper.startPreview(NoticeDetailActivity.this, HostConfig.getFileDownUrl(fileEntity.fileId));
                } else {
                    jumpToBrowser(HostConfig.getFileDownUrl(fileEntity.fileId));
                }
            }
        });
    }

    @OnClick({R.id.tv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                onBackPressed();
                break;
            default:
                break;
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void showNoticeDetail(NoticeInfo noticeInfo) {
        noticeTitleText.setText(noticeInfo.getNoticeTitle());
        contentText.setText(noticeInfo.getNoticeContent());
        publisherText.setText(noticeInfo.getFromUser());
        String dateStr = noticeInfo.getCreateTime();
        publisherText.setText("发布人：" + noticeInfo.getFromUser() + "  " + dateStr);
        receiverText.setText("接收人：" + noticeInfo.getAcceptUsersName());
        readText.setText("已读：" + noticeInfo.getRead() + "   未读：" + noticeInfo.getNoRead());
        queryFilePresenter.queryFile(noticeInfo.getId());
    }

    @Override
    public void onBackPressed() {
        if (fromPush) {
            if (!MainActivity.isMainActivityOn()) {
                restartApp(this);
            } else {
                EventBus.getDefault().post(new NoticeRefreshEvent());
            }
        }
        finish();
    }

    private void restartApp(Context context) {
        final Intent intent = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
        if (intent != null) {
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            context.startActivity(intent);
        }
    }

    private void jumpToBrowser(String path) {
        Uri uri = Uri.parse(path);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    @Override
    public void showFiles(List<FileEntity> fileEntities) {
        attachmentAdapter.setNewData(fileEntities);
    }
}
