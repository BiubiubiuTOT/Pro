package com.ljkj.qxn.wisdomsitepro.ui.quality;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.contract.common.ListFileContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.EnclosureInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.QualityCheckResultDetail;
import com.ljkj.qxn.wisdomsitepro.model.CommonModel;
import com.ljkj.qxn.wisdomsitepro.presenter.common.ListFilePresenter;
import com.ljkj.qxn.wisdomsitepro.ui.common.BaseCheckDetailsActivity;

import java.util.List;


/**
 * 类描述：检查详情-合格
 * 创建人：mhl
 * 创建时间：2018/2/6 14:22
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class QuaCheckDetailsOfQualifiedActivity extends BaseCheckDetailsActivity implements ListFileContract.View {
    private ListFilePresenter filePresenter;

    private int attachmentType;

    public static void startActivity(Context context, String id) {
        Intent intent = new Intent(context, QuaCheckDetailsOfQualifiedActivity.class);
        intent.putExtra("id", id);
        context.startActivity(intent);
    }

    @Override
    protected void initUI() {
        super.initUI();
        tvTitle.setText("合格详情");
        tvNext.setText("合格");
        tvNext.setBackgroundColor(Color.parseColor("#82d248"));
    }

    @Override
    protected void initData() {
        super.initData();
        filePresenter = new ListFilePresenter(this, CommonModel.newInstance());
        addPresenter(filePresenter);

        String id = getIntent().getStringExtra("id");
        requestDetail(id);
    }

    @Override
    public void showCheckResultDetail(QualityCheckResultDetail detail) {
        super.showCheckResultDetail(detail);
        if (detail.proInspect.getYhdj().equals("重大隐患")) {
            statusImage1.setVisibility(View.VISIBLE);
            statusImage2.setVisibility(View.VISIBLE);
            statusImage1.setImageResource(R.mipmap.ic_rectify_pass);
            statusImage2.setImageResource(R.mipmap.ic_remove_supervision_of_listing);
        } else {
            statusImage1.setImageResource(R.mipmap.ic_rectify_pass);
            statusImage2.setVisibility(View.VISIBLE);
            statusImage2.setVisibility(View.INVISIBLE);
        }

        filePresenter.listFiles("file", detail.base.getId());
        attachmentType = TYPE_REPORT;
    }


    @Override
    public void showFiles(List<EnclosureInfo> data) {
        if (attachmentType == TYPE_REPORT) {
            reportAdapter.loadData(data);
            filePresenter.listFiles("zgzjfile", detail.base.getId());
            attachmentType = TYPE_MONEY;
        } else if (attachmentType == TYPE_MONEY) {
            moneyAdapter.loadData(data);
            if ("是".equals(detail.base.getSfzdya())) {
                filePresenter.listFiles("zdyafile", detail.base.getId());
                attachmentType = TYPE_PLAN;
            } else {
                if ("是".equals(detail.base.getSfzdcs())) {
                    filePresenter.listFiles("zdcsfile", detail.base.getId());
                    attachmentType = TYPE_MEASURE;
                }
            }

        } else if (attachmentType == TYPE_PLAN) {
            planAttachmentRV.setVisibility(data.size() > 0 ? View.VISIBLE : View.GONE);
            planAdapter.loadData(data);
            if ("是".equals(detail.base.getSfzdcs())) {
                filePresenter.listFiles("zdcsfile", detail.base.getId());
                attachmentType = TYPE_MEASURE;
            }
        } else if (attachmentType == TYPE_MEASURE) {
            measureAttachmentRV.setVisibility(data.size() > 0 ? View.VISIBLE : View.GONE);
            measureAdapter.loadData(data);
        }
    }
}
