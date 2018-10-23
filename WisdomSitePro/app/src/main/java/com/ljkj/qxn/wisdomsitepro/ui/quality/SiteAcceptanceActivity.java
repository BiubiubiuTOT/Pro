package com.ljkj.qxn.wisdomsitepro.ui.quality;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.ljkj.qxn.wisdomsitepro.contract.common.ListFileContract;
import com.ljkj.qxn.wisdomsitepro.contract.common.QueryFileContract;
import com.ljkj.qxn.wisdomsitepro.contract.quality.ConcreteSiteAcceptanceContract;
import com.ljkj.qxn.wisdomsitepro.data.BaseEntity;
import com.ljkj.qxn.wisdomsitepro.data.FileEntity;
import com.ljkj.qxn.wisdomsitepro.data.TxtEntryEntity;
import com.ljkj.qxn.wisdomsitepro.data.TxtReEntryEntity;
import com.ljkj.qxn.wisdomsitepro.data.entity.ConcreteEntranceAcceptanceInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.EnclosureInfo;
import com.ljkj.qxn.wisdomsitepro.model.CommonModel;
import com.ljkj.qxn.wisdomsitepro.model.ConcreteModel;
import com.ljkj.qxn.wisdomsitepro.presenter.common.ListFilePresenter;
import com.ljkj.qxn.wisdomsitepro.presenter.common.QueryFilePresenter;
import com.ljkj.qxn.wisdomsitepro.presenter.quality.ConcreteSiteAcceptancePresenter;
import com.ljkj.qxn.wisdomsitepro.ui.common.BaseQualityInforOfMixedSoilActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 类描述：混泥土质量信息-进场验收
 * 创建人：mhl
 * 创建时间：2018/2/6 17:29
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SiteAcceptanceActivity extends BaseQualityInforOfMixedSoilActivity
        implements ConcreteSiteAcceptanceContract.View, QueryFileContract.View {

    private static final String KEY_ID = "key_id";
    private String id;
    private ConcreteSiteAcceptancePresenter siteAcceptancePresenter;
    private QueryFilePresenter listFilePresenter;

    public static void startActivity(@NonNull Context context, @NonNull String id) {
        Intent intent = new Intent(context, SiteAcceptanceActivity.class);
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
        tvTitle.setText("混凝混凝土进场验收详情");
    }

    @Override
    protected void initData() {
        siteAcceptancePresenter = new ConcreteSiteAcceptancePresenter(this, ConcreteModel.newInstance());
        listFilePresenter = new QueryFilePresenter(this, CommonModel.newInstance());
        addPresenter(siteAcceptancePresenter);
        addPresenter(listFilePresenter);
        id = getIntent().getStringExtra(KEY_ID);
        siteAcceptancePresenter.getApproachDetail(id);
    }

    @Override
    public void onViewClicked() {
        super.onViewClicked();
        finish();
    }

    @Override
    public void showSiteAcceptanceDetail(ConcreteEntranceAcceptanceInfo detail) {
        List<BaseEntity> datas = new ArrayList<>();
        datas.add(new TxtReEntryEntity("浇筑部位", detail.getPouringPart()));
        datas.add(new TxtReEntryEntity("混凝土供应商", detail.getConcreteSupplier()));
        datas.add(new TxtReEntryEntity("现场施工人员", detail.getSceneConstructionPersonnel()));
        datas.add(new TxtReEntryEntity("监理单位现场旁站人员", detail.getSupervisorUnitPersonnel()));
        datas.add(new TxtReEntryEntity("现场操作劳务人员", detail.getSceneOperationPersonnel()));
        datas.add(new TxtReEntryEntity("混凝土类别", detail.getConcreteType()));
        datas.add(new TxtReEntryEntity("强度等级", detail.getStrengthGrade()));
        datas.add(new TxtReEntryEntity("浇筑时间", detail.getPouringStartDate()));
        datas.add(new TxtReEntryEntity("结束时间",detail.getPouringEndDate()));
        datas.add(new TxtReEntryEntity("配合编号", detail.getCoordinationCode()));
        datas.add(new TxtReEntryEntity("天气情况", detail.getWeatherCondition()));
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
