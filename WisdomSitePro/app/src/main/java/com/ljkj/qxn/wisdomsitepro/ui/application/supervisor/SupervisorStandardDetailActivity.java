package com.ljkj.qxn.wisdomsitepro.ui.application.supervisor;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.Utils.PhotoPickerHelper;
import com.ljkj.qxn.wisdomsitepro.contract.common.QueryFileContract;
import com.ljkj.qxn.wisdomsitepro.contract.supervisor.StandardRecordContract;
import com.ljkj.qxn.wisdomsitepro.data.FileEntity;
import com.ljkj.qxn.wisdomsitepro.data.api.HostConfig;
import com.ljkj.qxn.wisdomsitepro.data.entity.PageInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.SupervisorStandardInfo;
import com.ljkj.qxn.wisdomsitepro.model.CommonModel;
import com.ljkj.qxn.wisdomsitepro.model.SupervisorModel;
import com.ljkj.qxn.wisdomsitepro.presenter.common.QueryFilePresenter;
import com.ljkj.qxn.wisdomsitepro.presenter.supervisor.StandardRecordPresenter;
import com.ljkj.qxn.wisdomsitepro.ui.common.RemotePDFActivity;
import com.ljkj.qxn.wisdomsitepro.ui.safe.adapter.CheckAttachmentAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseActivity;

/**
 * 类描述：监理标准规范详情
 * 创建人：lxx
 * 创建时间：2018/8/30
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SupervisorStandardDetailActivity extends BaseActivity implements StandardRecordContract.View, QueryFileContract.View {
    private static final String KEY_TITLE = "data";

    @BindView(R.id.tv_title)
    TextView titleText;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.ll_no_data)
    ViewGroup llNoData;


    @BindView(R.id.refreshLayout)
    public SmartRefreshLayout refreshLayout;

    private SupervisorStandardInfo info;
    private StandardRecordPresenter standardRecordPresenter;
    private QueryFilePresenter queryFilePresenter;
    private CheckAttachmentAdapter attachmentAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supervisor_standard_detail);
    }

    @Override
    protected void initUI() {
        info = (SupervisorStandardInfo) getIntent().getSerializableExtra(KEY_TITLE);
        if (info != null) {
            titleText.setText(info.getName());
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(attachmentAdapter = new CheckAttachmentAdapter(new ArrayList<FileEntity>()));

            attachmentAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    FileEntity fileEntity = attachmentAdapter.getItem(position);
                    if (fileEntity == null) {
                        return;
                    }
                    String extend = fileEntity.fileExt;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && "pdf".equals(extend)) {
                        RemotePDFActivity.startActivity(SupervisorStandardDetailActivity.this, HostConfig.getFileDownUrl(fileEntity.fileId));
                    } else if ("jpg".equals(extend) || "jpeg".equals(extend) || "png".equals(extend)) {
                        PhotoPickerHelper.startPreview(SupervisorStandardDetailActivity.this, HostConfig.getFileDownUrl(fileEntity.fileId));
                    } else {
                        jumpToBrowser(HostConfig.getFileDownUrl(fileEntity.fileId));
                    }
                }
            });
        }

        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                queryFilePresenter.queryFile(info.getId());
            }
        });
    }

    private void jumpToBrowser(String path) {
        if (TextUtils.isEmpty(path)) {
            return;
        }
        Uri uri = Uri.parse(path);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    @Override
    protected void initData() {
        standardRecordPresenter = new StandardRecordPresenter(this, SupervisorModel.newInstance());
        queryFilePresenter = new QueryFilePresenter(this, CommonModel.newInstance());
        addPresenter(standardRecordPresenter);
        addPresenter(queryFilePresenter);

        if (info != null) {
            refreshLayout.autoRefresh();
        }
    }

    @OnClick({R.id.tv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void showStandardRecordList(PageInfo<SupervisorStandardInfo> datas) {

    }

    @Override
    public void dealDeleteRecordResult() {
        showError("删除成功");
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void showStandardRecordDetail(SupervisorStandardInfo info) {

    }

    @Override
    public void dealAddRecordResult() {

    }

    @Override
    public void showFiles(List<FileEntity> fileEntities) {
        attachmentAdapter.setNewData(fileEntities);
    }

    @Override
    public void hideProgress() {
        super.hideProgress();
        llNoData.setVisibility(recyclerView.getAdapter().getItemCount() > 0 ? View.GONE : View.VISIBLE);
        refreshLayout.finishRefresh();
    }
}
