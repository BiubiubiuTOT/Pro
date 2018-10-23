package com.ljkj.qxn.wisdomsitepro.ui.application.supervisor;

import android.text.TextUtils;
import android.view.View;

import com.ljkj.qxn.wisdomsitepro.contract.common.QueryFileContract;
import com.ljkj.qxn.wisdomsitepro.data.FileEntity;
import com.ljkj.qxn.wisdomsitepro.data.entity.InspectorRecorderManageInfo;
import com.ljkj.qxn.wisdomsitepro.model.CommonModel;
import com.ljkj.qxn.wisdomsitepro.presenter.common.QueryFilePresenter;
import com.ljkj.qxn.wisdomsitepro.ui.common.adapter.ShowImageAdapter;

import java.util.List;

/**
 * 类描述：巡视记录详情
 * 创建人：lxx
 * 创建时间：2018/8/30
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class InspectorRecordDetailActivity extends AddInspectorRecordActivity implements QueryFileContract.View {

    private ShowImageAdapter adapter;
    private QueryFilePresenter queryFilePresenter;

    @Override
    protected void initUI() {
        super.initUI();
        titleText.setText("巡视记录详情");
        recordNameItem.setEnable(false);
        inspectorTimeItem.showArrow(false);
        inspectorTimeItem.setClickable(false);
        unitItem.setEnable(false);
        inspectorRangeEdit.setEnabled(false);
        constructProjectEdit.setEnabled(false);
        recordDataEdit.setEnabled(false);
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
    public void showInspectorRecordDetail(InspectorRecorderManageInfo info) {
        super.showInspectorRecordDetail(info);
        String name = info.getName();
        if (TextUtils.isEmpty(name))
            recordNameItem.setContent("无");
        else
            recordNameItem.setContent(name);

        String patrolScope = info.getPatrolScope();
        if (TextUtils.isEmpty(patrolScope))
            inspectorRangeEdit.setText("无");
        else
            inspectorRangeEdit.setText(patrolScope);

        String projWorkerTech = info.getProjWorkerTech();
        if (TextUtils.isEmpty(projWorkerTech))
            constructProjectEdit.setText("无");
        else
            constructProjectEdit.setText(projWorkerTech);

        String recordData = info.getRecordData();
        recordDataEdit.setText(TextUtils.isEmpty(recordData) ? "无" : recordData);

        String patrolTime = info.getPatrolTime();
        inspectorTimeItem.setContent(TextUtils.isEmpty(patrolTime) ? "无" : patrolTime);

        String supervisionUnit = info.getSupervisionUnit();
        unitItem.setContent(TextUtils.isEmpty(supervisionUnit) ? "无" : supervisionUnit);
    }

    @Override
    public void showFiles(List<FileEntity> fileEntities) {
        adapter.loadData(fileEntities);
    }
}
