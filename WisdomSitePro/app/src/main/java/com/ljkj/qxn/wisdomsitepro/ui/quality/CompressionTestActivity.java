package com.ljkj.qxn.wisdomsitepro.ui.quality;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.ljkj.qxn.wisdomsitepro.contract.common.QueryFileContract;
import com.ljkj.qxn.wisdomsitepro.contract.quality.ConcreteCompressionContract;
import com.ljkj.qxn.wisdomsitepro.data.BaseEntity;
import com.ljkj.qxn.wisdomsitepro.data.FileEntity;
import com.ljkj.qxn.wisdomsitepro.data.TxtEntryEntity;
import com.ljkj.qxn.wisdomsitepro.data.TxtReEntryEntity;
import com.ljkj.qxn.wisdomsitepro.data.entity.ConcreteCompressiveInfo;
import com.ljkj.qxn.wisdomsitepro.model.CommonModel;
import com.ljkj.qxn.wisdomsitepro.model.ConcreteModel;
import com.ljkj.qxn.wisdomsitepro.presenter.ConcreteCompressionPresenter;
import com.ljkj.qxn.wisdomsitepro.presenter.common.QueryFilePresenter;
import com.ljkj.qxn.wisdomsitepro.ui.common.BaseQualityInforOfMixedSoilActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * 类描述：混泥土质量信息-抗压检验
 * 创建人：mhl
 * 创建时间：2018/2/6 17:27
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class CompressionTestActivity extends BaseQualityInforOfMixedSoilActivity implements ConcreteCompressionContract.View, QueryFileContract.View {

    private static final String KEY_ID = "key_id";
    private String id;
    private ConcreteCompressionPresenter compressionPresenter;
    private QueryFilePresenter listFilePresenter;

    public static void startActivity(@NonNull Context context, @NonNull String id) {
        Intent intent = new Intent(context, CompressionTestActivity.class);
        intent.putExtra(KEY_ID, id);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initUI() {
        super.initUI();
        tvTitle.setText("抗压强度检验详情");
    }

    @Override
    protected void initData() {
        id = getIntent().getStringExtra(KEY_ID);
        compressionPresenter = new ConcreteCompressionPresenter(this, ConcreteModel.newInstance());
        listFilePresenter = new QueryFilePresenter(this, CommonModel.newInstance());
        addPresenter(compressionPresenter);
        addPresenter(listFilePresenter);
        compressionPresenter.getCompressiveDetail(id);
    }

    @Override
    public void onViewClicked() {
        super.onViewClicked();
        finish();
    }

    @Override
    public void showCompressionDetail(ConcreteCompressiveInfo detail) {
        List<BaseEntity> datas = new ArrayList<>();
        datas.add(new TxtReEntryEntity("检验编号", detail.getCheckCode()));
        datas.add(new TxtReEntryEntity("委托单位", detail.getEntrustUnit()));
        datas.add(new TxtReEntryEntity("见证单位", detail.getWitnessUnit()));
        datas.add(new TxtReEntryEntity("见证人", detail.getWitness()));
        datas.add(new TxtReEntryEntity("使用部位", detail.getUsePart()));
        datas.add(new TxtReEntryEntity("样品名称", detail.getSampleName()));
        datas.add(new TxtReEntryEntity("设计强度等级", detail.getDesignStrengthGrade()));
        datas.add(new TxtReEntryEntity("养护方式", detail.getMaintenanceMode()));

        datas.add(new TxtReEntryEntity("龄期（d）", detail.getNearTerm()));
        datas.add(new TxtReEntryEntity("成型日期",detail.getFormingDate()));
        datas.add(new TxtReEntryEntity("检验日期", detail.getCheckDate()));
        datas.add(new TxtReEntryEntity("检验结果", detail.getCheckResult()));
        datas.add(new TxtReEntryEntity("检验单位", detail.getCheckUnit()));
        datas.add(new TxtReEntryEntity("外观质量", detail.getAppearanceQuality()));
        datas.add(new TxtEntryEntity("验收附件"));

        adapter.setItems(datas);
        adapter.notifyDataSetChanged();
        listFilePresenter.queryFile(detail.getId());
    }


    @Override
    public void showFiles(List<FileEntity> fileEntities) {
        if (adapter.getItems() != null) {
            adapter.getItems().addAll(fileEntities);
            adapter.notifyDataSetChanged();
        }
    }
}
