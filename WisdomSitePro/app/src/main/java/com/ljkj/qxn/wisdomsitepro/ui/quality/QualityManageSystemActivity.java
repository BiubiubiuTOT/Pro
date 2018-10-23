package com.ljkj.qxn.wisdomsitepro.ui.quality;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.contract.common.QueryFileContract;
import com.ljkj.qxn.wisdomsitepro.contract.quality.QualityManageSystemContract;
import com.ljkj.qxn.wisdomsitepro.data.BaseEntity;
import com.ljkj.qxn.wisdomsitepro.data.BlockEntryEntity;
import com.ljkj.qxn.wisdomsitepro.data.FileEntity;
import com.ljkj.qxn.wisdomsitepro.data.TxtEntryEntity;
import com.ljkj.qxn.wisdomsitepro.data.api.HostConfig;
import com.ljkj.qxn.wisdomsitepro.data.entity.EnclosureInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.QualityManageSystemInfo;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.model.CommonModel;
import com.ljkj.qxn.wisdomsitepro.model.QualityModel;
import com.ljkj.qxn.wisdomsitepro.presenter.common.QueryFilePresenter;
import com.ljkj.qxn.wisdomsitepro.presenter.quality.QualityManageSystemPresenter;
import com.ljkj.qxn.wisdomsitepro.ui.quality.adapter.QualityManageSystemAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseActivity;


/**
 * 类描述：质量管理体系
 * 创建人：mhl
 * 创建时间：2018/2/5 14:23
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class QualityManageSystemActivity extends BaseActivity implements QualityManageSystemContract.View, QueryFileContract.View {

    QualityManageSystemAdapter adapter;

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.recyclerView)
    RecyclerView rlv;

    @BindView(R.id.ll_no_data)
    public ViewGroup llNoData;

    private QualityManageSystemPresenter qPresenter;
    private QueryFilePresenter queryFilePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quality_manage_system);
    }

    @Override
    protected void initUI() {
        tvTitle.setText("质量体系");
        refreshLayout.setEnableRefresh(false);
        refreshLayout.setEnableLoadMore(false);
        LinearLayoutManager linearManager = new LinearLayoutManager(this);
        linearManager.setOrientation(LinearLayoutManager.VERTICAL);
        rlv.setLayoutManager(linearManager);
        rlv.setAdapter(adapter = new QualityManageSystemAdapter(this, new ArrayList<BaseEntity>()));
    }

    @Override
    protected void initData() {
        qPresenter = new QualityManageSystemPresenter(this, QualityModel.newInstance());
        addPresenter(qPresenter);
        queryFilePresenter = new QueryFilePresenter(this, CommonModel.newInstance());
        addPresenter(queryFilePresenter);

        qPresenter.viewQualityManageSystemInfo(UserManager.getInstance().getProjectId());
    }

    @OnClick(R.id.tv_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void showQualityManageSystemInfo(QualityManageSystemInfo data) {
        if (data != null) {
            List<BaseEntity> datas = new ArrayList<>();
            datas.add(new TxtEntryEntity("质量方针与目标"));
            datas.add(new BlockEntryEntity(data.getTarget()));
            adapter.setItems(datas);
            adapter.notifyDataSetChanged();
            queryFilePresenter.queryFile(data.getId());
        } else {
            llNoData.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showFiles(List<FileEntity> fileEntities) {
        List<EnclosureInfo> qualityList = new ArrayList<>();
        List<EnclosureInfo> programList = new ArrayList<>();
        List<EnclosureInfo> recordList = new ArrayList<>();
        for (FileEntity fileEntity : fileEntities) {
            if ("290".equals(fileEntity.type)) { //程序性文件
                EnclosureInfo enclosureInfo = new EnclosureInfo(fileEntity.fileName);
                enclosureInfo.setValue(HostConfig.getFileDownUrl(fileEntity.fileId));
                qualityList.add(enclosureInfo);
            } else if ("291".equals(fileEntity.type)) { //质量手册
                EnclosureInfo enclosureInfo1 = new EnclosureInfo(fileEntity.fileName);
                enclosureInfo1.setValue(HostConfig.getFileDownUrl(fileEntity.fileId));
                programList.add(enclosureInfo1);
            } else if ("292".equals(fileEntity.type)) { //质量记录文件
                EnclosureInfo enclosureInfo2 = new EnclosureInfo(fileEntity.fileName);
                enclosureInfo2.setValue(HostConfig.getFileDownUrl(fileEntity.fileId));
                recordList.add(enclosureInfo2);
            }
        }
        adapter.getItems().add(new TxtEntryEntity("质量手册"));
        adapter.getItems().addAll(qualityList);
        adapter.getItems().add(new TxtEntryEntity("程序性文件"));
        adapter.getItems().addAll(programList);
        adapter.getItems().add(new TxtEntryEntity("质量记录文件"));
        adapter.getItems().addAll(recordList);
        adapter.notifyDataSetChanged();
    }

}
