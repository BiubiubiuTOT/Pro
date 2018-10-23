package com.ljkj.qxn.wisdomsitepro.ui.application.supervisor;

import android.text.TextUtils;
import android.view.View;

import com.ljkj.qxn.wisdomsitepro.contract.common.QueryFileContract;
import com.ljkj.qxn.wisdomsitepro.data.FileEntity;
import com.ljkj.qxn.wisdomsitepro.data.entity.SupervisorRecordManageInfo;
import com.ljkj.qxn.wisdomsitepro.model.CommonModel;
import com.ljkj.qxn.wisdomsitepro.presenter.common.QueryFilePresenter;
import com.ljkj.qxn.wisdomsitepro.ui.common.adapter.ShowImageAdapter;

import java.util.List;

/**
 * 类描述：监理记录详情
 * 创建人：lxx
 * 创建时间：2018/9/1
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SupervisorRecordDetailActivity extends AddSupervisorRecordActivity implements QueryFileContract.View {
    private ShowImageAdapter adapter;
    private QueryFilePresenter queryFilePresenter;

    @Override
    protected void initUI() {
        super.initUI();
        titleText.setText("监理记录详情");
        recordNameItem.setEnable(false);
        dateItem.showArrow(false);
        dateItem.setClickable(false);
        unitItem.setEnable(false);
        supervisorTypeItem.showArrow(false);
        supervisorTypeItem.setClickable(false);
        contentEdit.setEnabled(false);
        submitText.setVisibility(View.GONE);
    }

    @Override
    protected void initData() {
        super.initData();
        imageRV.setAdapter(adapter = new ShowImageAdapter(this));
        imageRV.setNestedScrollingEnabled(false);

        queryFilePresenter = new QueryFilePresenter(this, CommonModel.newInstance());
        addPresenter(queryFilePresenter);
        queryFilePresenter.queryFile(id);
    }

    @Override
    public void showSupervisorRecordDetail(SupervisorRecordManageInfo info) {
        super.showSupervisorRecordDetail(info);
        String name = info.getName();
        recordNameItem.setContent(TextUtils.isEmpty(name) ? "无" : name);

        String supervisionUnit = info.getSupervisionUnit();
        unitItem.setContent(TextUtils.isEmpty(supervisionUnit) ? "无" : supervisionUnit);

        String recordInfo = info.getRecordInfo();
        contentEdit.setText(TextUtils.isEmpty(recordInfo) ? "无" : recordInfo);

        int supervisionType = info.getSupervisionType();
        supervisorTypeItem.setContent(supervisionType == 0 ? "无" : types.get(supervisionType - 1));

        String recordTime = info.getRecordTime();
        dateItem.setContent(TextUtils.isEmpty(recordInfo) ? "无" : recordTime);
    }

    @Override
    public void showFiles(List<FileEntity> fileEntities) {
        adapter.loadData(fileEntities);
    }
}
