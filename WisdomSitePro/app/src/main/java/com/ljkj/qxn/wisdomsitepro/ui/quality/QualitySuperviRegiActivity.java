package com.ljkj.qxn.wisdomsitepro.ui.quality;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.contract.quality.QualitySuperviseRegisterContract;
import com.ljkj.qxn.wisdomsitepro.data.BaseEntity;
import com.ljkj.qxn.wisdomsitepro.data.TvListEntity;
import com.ljkj.qxn.wisdomsitepro.data.TvListItemEntity;
import com.ljkj.qxn.wisdomsitepro.data.TxtEntryEntity;
import com.ljkj.qxn.wisdomsitepro.data.entity.QualitySuperviseRegisterInfo;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.model.QualityModel;
import com.ljkj.qxn.wisdomsitepro.presenter.quality.QualitySuperviseRegisterPresenter;
import com.ljkj.qxn.wisdomsitepro.ui.quality.adapter.RegiAndSuperviOfQualityAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseActivity;

/**
 * 类描述：质量监督注册登记 申报表
 * 创建人：mhl
 * 创建时间：2018/2/6 9:41
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class QualitySuperviRegiActivity extends BaseActivity implements QualitySuperviseRegisterContract.View {

    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.rlv)
    RecyclerView rlv;

    RegiAndSuperviOfQualityAdapter adapter;
    private QualitySuperviseRegisterPresenter registerPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quality_supervi_regi);
    }

    @Override
    protected void initUI() {
        tvTitle.setText("质量监督注册登记");
        LinearLayoutManager linearManager = new LinearLayoutManager(this);
        linearManager.setOrientation(LinearLayoutManager.VERTICAL);
        rlv.setLayoutManager(linearManager);
        rlv.setAdapter(adapter = new RegiAndSuperviOfQualityAdapter(this, new ArrayList<BaseEntity>()));
    }

    @Override
    protected void initData() {

        registerPresenter = new QualitySuperviseRegisterPresenter(this, QualityModel.newInstance());
        addPresenter(registerPresenter);
        registerPresenter.qualitySupReg(UserManager.getInstance().getProjectId());

    }

    @OnClick(R.id.tv_back)
    public void onViewClicked() {
        finish();
    }


    @Override
    public void showQualitySuperviseRegisterInfo(QualitySuperviseRegisterInfo info) {
        QualitySuperviseRegisterInfo.Brief brief = info.brief;
        QualitySuperviseRegisterInfo.Units units = info.units;

        if (brief == null || units == null) {
            return;
        }

        List<BaseEntity> datas = new ArrayList<>();
        datas.add(new TxtEntryEntity("工程概况"));
        List<TvListItemEntity> tvList1 = new ArrayList<>();
        tvList1.add(new TvListItemEntity("建设单位：", brief.jsdw == null ? "" : brief.jsdw.name));
        tvList1.add(new TvListItemEntity("工程地址：", brief.address));
        tvList1.add(new TvListItemEntity("建设类型：", brief.buildingProperty));
        tvList1.add(new TvListItemEntity("建筑高度（m）：", brief.jzgd));
        tvList1.add(new TvListItemEntity("地下层数：", brief.dxcs));
        tvList1.add(new TvListItemEntity("地上总建筑面积（m2）：", brief.dsArea));
        tvList1.add(new TvListItemEntity("人防面积（m2）：", brief.rfArea));
        tvList1.add(new TvListItemEntity("基础类型：", brief.jclx));
        datas.add(new TvListEntity(tvList1));

        List<TvListItemEntity> tvList2 = new ArrayList<>();
        datas.add(new TxtEntryEntity("建设单位基本情况登记"));
        tvList2.add(new TvListItemEntity("名称：", units.jsdw == null ? "" : units.jsdw.name));
        tvList2.add(new TvListItemEntity("法人代表：", units.jsdw.frPerson == null ? "" : units.jsdw.frPerson.name));
        tvList2.add(new TvListItemEntity("身份证号码：", units.jsdw.frPerson == null ? "" : units.jsdw.frPerson.idCard));
        tvList2.add(new TvListItemEntity("联系电话：", units.jsdw.frPerson == null ? "" : units.jsdw.frPerson.phone));
        tvList2.add(new TvListItemEntity("企业组织机构代码：", units.jsdw == null ? "" : units.jsdw.zzjgdmz));
        tvList2.add(new TvListItemEntity("地址：", units.jsdw == null ? "" : units.jsdw.address));
        tvList2.add(new TvListItemEntity("资质证号：", units.jsdw == null ? "" : units.jsdw.zzzsh));
        tvList2.add(new TvListItemEntity("联系电话：", units.jsdw.xmfzrPerson == null ? "" : units.jsdw.xmfzrPerson.phone));
        tvList2.add(new TvListItemEntity("项目负责人：", units.jsdw.xmfzrPerson == null ? "" : units.jsdw.xmfzrPerson.name));
        tvList2.add(new TvListItemEntity("身份证号码：", units.jsdw.xmfzrPerson == null ? "" : units.jsdw.xmfzrPerson.idCard));
        datas.add(new TvListEntity(tvList2));

        List<TvListItemEntity> tvList3 = new ArrayList<>();
        datas.add(new TxtEntryEntity("勘察单位基本情况登记"));
        tvList3.add(new TvListItemEntity("名称：", units.kcdw == null ? "" : units.kcdw.name));
        tvList3.add(new TvListItemEntity("资质等级：", units.kcdw == null ? "" : units.kcdw.dwzzdj));
        tvList3.add(new TvListItemEntity("法人代表：", units.kcdw.frPerson == null ? "" : units.kcdw.frPerson.name));
        tvList3.add(new TvListItemEntity("身份证号码：", units.kcdw.frPerson == null ? "" : units.kcdw.frPerson.idCard));
        tvList3.add(new TvListItemEntity("联系电话：", units.kcdw.frPerson == null ? "" : units.kcdw.frPerson.phone));
        tvList3.add(new TvListItemEntity("企业组织机构代码：", units.kcdw == null ? "" : units.kcdw.zzjgdmz));
        datas.add(new TvListEntity(tvList3));

        List<TvListItemEntity> tvList4 = new ArrayList<>();
        datas.add(new TxtEntryEntity("设计单位基本情况登记"));
        tvList4.add(new TvListItemEntity("名称：", units.sjdw == null ? "" : units.sjdw.name));
        tvList4.add(new TvListItemEntity("资质等级：", units.sjdw == null ? "" : units.sjdw.dwzzdj));
        tvList4.add(new TvListItemEntity("法人代表：", units.sjdw.frPerson == null ? "" : units.sjdw.frPerson.name));
        tvList4.add(new TvListItemEntity("身份证号码：", units.sjdw.frPerson == null ? "" : units.sjdw.frPerson.idCard));
        tvList4.add(new TvListItemEntity("联系电话：", units.sjdw.frPerson == null ? "" : units.sjdw.frPerson.phone));
        tvList4.add(new TvListItemEntity("企业组织机构代码：", units.sjdw == null ? "" : units.sjdw.zzjgdmz));
        datas.add(new TvListEntity(tvList4));

        List<TvListItemEntity> tvList5 = new ArrayList<>();
        datas.add(new TxtEntryEntity("监理单位基本情况登记"));
        tvList5.add(new TvListItemEntity("名称：", units.jldw == null ? "" : units.jldw.name));
        tvList5.add(new TvListItemEntity("资质等级：", units.jldw == null ? "" : units.jldw.dwzzdj));
        tvList5.add(new TvListItemEntity("法人代表：", units.jldw.frPerson == null ? "" : units.jldw.frPerson.name));
        tvList5.add(new TvListItemEntity("身份证号码：", units.jldw.frPerson == null ? "" : units.jldw.frPerson.idCard));
        tvList5.add(new TvListItemEntity("联系电话：", units.jldw.frPerson == null ? "" : units.jldw.frPerson.phone));
        tvList5.add(new TvListItemEntity("企业组织机构代码：", units.jldw == null ? "" : units.jldw.zzjgdmz));
        datas.add(new TvListEntity(tvList5));


        List<TvListItemEntity> tvList6 = new ArrayList<>();
        datas.add(new TxtEntryEntity("施工单位"));
        tvList6.add(new TvListItemEntity("名称：", units.sgdw == null ? "" : units.sgdw.name));
        tvList6.add(new TvListItemEntity("资质等级：", units.sgdw == null ? "" : units.sgdw.dwzzdj));
        tvList6.add(new TvListItemEntity("法人代表：", units.sgdw.frPerson == null ? "" : units.sgdw.frPerson.name));
        tvList6.add(new TvListItemEntity("身份证号码：", units.sgdw.frPerson == null ? "" : units.sgdw.frPerson.idCard));
        tvList6.add(new TvListItemEntity("联系电话：", units.sgdw.frPerson == null ? "" : units.sgdw.frPerson.phone));
        tvList6.add(new TvListItemEntity("建造师等级：", units.sgdw.xmjlPerson == null ? "" : units.sgdw.xmjlPerson.zzdj));
        datas.add(new TvListEntity("总承包单位信息登记", tvList6));


        for (QualitySuperviseRegisterInfo.Units.Fbdw fbdw : units.fbdws) {
            List<TvListItemEntity> tvList7 = new ArrayList<>();
            tvList7.add(new TvListItemEntity("名称：", fbdw.name));
            tvList7.add(new TvListItemEntity("资质等级：", fbdw.dwzzdj));
            tvList7.add(new TvListItemEntity("法人代表：", fbdw.frPerson == null ? "" : fbdw.frPerson.name));
            tvList7.add(new TvListItemEntity("身份证号码：", fbdw.frPerson == null ? "" : fbdw.frPerson.idCard));
            tvList7.add(new TvListItemEntity("技术负责人：", fbdw.jsfzrPerson == null ? "" : fbdw.jsfzrPerson.name));
            tvList7.add(new TvListItemEntity("身份证号码：", fbdw.jsfzrPerson == null ? "" : fbdw.jsfzrPerson.idCard));
            tvList7.add(new TvListItemEntity("联系电话：", fbdw.jsfzrPerson == null ? "" : fbdw.jsfzrPerson.phone));
            datas.add(new TvListEntity("分包单位信息登记", tvList7));
        }

        adapter.setItems(datas);
        adapter.notifyDataSetChanged();

    }


}
