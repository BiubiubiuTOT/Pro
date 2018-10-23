package com.ljkj.qxn.wisdomsitepro.ui.quality;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.contract.common.ListFileContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.EnclosureInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.QualityCheckResultDetail;
import com.ljkj.qxn.wisdomsitepro.model.CommonModel;
import com.ljkj.qxn.wisdomsitepro.presenter.common.ListFilePresenter;
import com.ljkj.qxn.wisdomsitepro.ui.common.BaseCheckDetailsActivity;

import java.util.List;

import butterknife.OnClick;

/**
 * 类描述：检查详情-重新整改
 * 创建人：mhl
 * 创建时间：2018/2/6 14:15
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class QuaCheckDetailsOfRectifyActivity extends BaseCheckDetailsActivity implements ListFileContract.View {

    private ListFilePresenter filePresenter;
    private int attachmentType;

    public static void startActivity(Context context, String id) {
        Intent intent = new Intent(context, QuaCheckDetailsOfRectifyActivity.class);
        intent.putExtra("id", id);
        context.startActivity(intent);
    }

    @Override
    protected void initUI() {
        super.initUI();
        tvTitle.setText("整改详情");
    }

    @Override
    protected void initData() {
        super.initData();
        filePresenter = new ListFilePresenter(this, CommonModel.newInstance());
        addPresenter(filePresenter);

        String id = getIntent().getStringExtra("id");
        requestDetail(id);
    }

    @OnClick({R.id.tv_next})
    @Override
    public void onViewClicked(View view) {
        super.onViewClicked(view);
        switch (view.getId()) {
            case R.id.tv_next:
                if (detail != null) {
                    QuaImmediateRectificationActivity.startActivity(this, detail.proInspect.getId(), detail.proInspect.getProjectName(), detail.getProAddress(), detail.proInspect.getCheckNo());
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void showCheckResultDetail(QualityCheckResultDetail detail) {
        super.showCheckResultDetail(detail);
        if (detail.proInspect.getYhdj().equals("重大隐患")) {
            statusImage1.setVisibility(View.VISIBLE);
            statusImage2.setVisibility(View.VISIBLE);
            statusImage1.setImageResource(R.mipmap.ic_rectify_unqualified);
            statusImage2.setImageResource(R.mipmap.ic_supervision_of_listing);
        } else {
            statusImage1.setImageResource(R.mipmap.ic_rectify_unqualified);
            statusImage2.setVisibility(View.VISIBLE);
            statusImage2.setVisibility(View.INVISIBLE);
        }

        filePresenter.listFiles("file", detail.proInspect.getId());
        attachmentType = TYPE_REPORT;
    }


    @Override
    public void showFiles(List<EnclosureInfo> data) {
        if (attachmentType == TYPE_REPORT) {
            reportAdapter.loadData(data);
            filePresenter.listFiles("zgzjfile", detail.proInspect.getId());
            attachmentType = TYPE_MONEY;
        } else if (attachmentType == TYPE_MONEY) {
            moneyAdapter.loadData(data);
            if ("是".equals(detail.base.getSfzdya())) {
                filePresenter.listFiles("zdyafile", detail.proInspect.getId());
                attachmentType = TYPE_PLAN;
            } else {
                if ("是".equals(detail.base.getSfzdcs())) {
                    filePresenter.listFiles("zdcsfile", detail.proInspect.getId());
                    attachmentType = TYPE_MEASURE;
                }
            }

        } else if (attachmentType == TYPE_PLAN) {
            planAttachmentRV.setVisibility(data.size() > 0 ? View.VISIBLE : View.GONE);
            planAdapter.loadData(data);
            if ("是".equals(detail.base.getSfzdcs())) {
                filePresenter.listFiles("zdcsfile", detail.proInspect.getId());
                attachmentType = TYPE_MEASURE;
            }
        } else if (attachmentType == TYPE_MEASURE) {
            measureAttachmentRV.setVisibility(data.size() > 0 ? View.VISIBLE : View.GONE);
            measureAdapter.loadData(data);
        }
    }


}
