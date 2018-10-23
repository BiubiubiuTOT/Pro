package com.ljkj.qxn.wisdomsitepro.ui.quality;

import android.text.TextUtils;
import android.view.View;

import com.ljkj.qxn.wisdomsitepro.contract.common.InspectRectifyContract;
import com.ljkj.qxn.wisdomsitepro.model.QualityModel;
import com.ljkj.qxn.wisdomsitepro.presenter.common.InspectRectifyPresenter;
import com.ljkj.qxn.wisdomsitepro.ui.safe.SafeInspectionDetailV2Activity;

/**
 * 类描述：安全巡检详情-立即审核
 * 创建人：mhl
 * 创建时间：2018/2/6 16:01
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class QualityInspectionDetailsOfImmeApprovalV2Activity extends QualityInspectionDetailV2Activity implements InspectRectifyContract.View {

    private InspectRectifyPresenter presenter;

    @Override
    protected void initUI() {
        super.initUI();
        nextText.setText("提交");
        examineLayout.setVisibility(View.VISIBLE);
        rectifyLayout.setVisibility(View.GONE);
    }

    @Override
    protected void initData() {
        super.initData();
        presenter = new InspectRectifyPresenter(this, QualityModel.newInstance());
        addPresenter(presenter);
    }

    @Override
    protected void doNext() {
        super.doNext();
        String status = whetherQualifiedItem.getContent().equals("合格") ? "5" : "3";
        String reason = whetherQualifiedItem.getContent().equals("合格") ? "" : qualifiedEdit.getText().toString();
        String id = qualityPatrolDetailInfo.getReform().isEmpty() ? "" :
                qualityPatrolDetailInfo.getReform().get(qualityPatrolDetailInfo.getReform().size() - 1).getId();
        presenter.checkRectifyDetail(id, status, reason, qualityPatrolDetailInfo.getCheck().getId());
    }

    @Override
    protected boolean checkData() {
        if (qualityPatrolDetailInfo == null) {
            showError("巡检详情获取失败");
            return false;
        }
        if (whetherQualifiedItem.getContent().equals("不合格") && TextUtils.isEmpty(qualifiedEdit.getText().toString())) {
            showError("请输入不合格原因");
            return false;
        }
        return true;
    }

    @Override
    public void dealResult(String data) {
        showError("提交成功");
        finish();
    }

}
