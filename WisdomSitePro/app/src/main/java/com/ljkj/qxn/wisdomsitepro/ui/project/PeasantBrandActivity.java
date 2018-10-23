package com.ljkj.qxn.wisdomsitepro.ui.project;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.contract.common.QueryFileContract;
import com.ljkj.qxn.wisdomsitepro.contract.project.FarmerBrandContract;
import com.ljkj.qxn.wisdomsitepro.data.FarmerBrandInfo;
import com.ljkj.qxn.wisdomsitepro.data.FileEntity;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.model.CommonModel;
import com.ljkj.qxn.wisdomsitepro.model.ProjectModel;
import com.ljkj.qxn.wisdomsitepro.presenter.FarmerBrandPresenter;
import com.ljkj.qxn.wisdomsitepro.presenter.common.QueryFilePresenter;
import com.ljkj.qxn.wisdomsitepro.ui.common.adapter.ShowImageAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseActivity;
import cdsp.android.ui.widget.DividerGridItemDecoration;

public class PeasantBrandActivity extends BaseActivity implements FarmerBrandContract.View, QueryFileContract.View {

    @BindView(R.id.tv_title)
    TextView titleText;

    @BindView(R.id.tv_unit_name)
    TextView unitNameText;

    @BindView(R.id.tv_header)
    TextView headerText;

    @BindView(R.id.tv_phone)
    TextView phoneText;

    @BindView(R.id.tv_construction_unit)
    TextView constructionUnitText;

    @BindView(R.id.tv_enterprise_header)
    TextView enterpriseHeaderText;

    @BindView(R.id.tv_enterprise_phone)
    TextView enterprisePhoneText;

    @BindView(R.id.tv_pm)
    TextView pmText;

    @BindView(R.id.tv_pm_phone)
    TextView pmPhoneText;

    @BindView(R.id.tv_labor_header)
    TextView laborHeaderText;

    @BindView(R.id.tv_labor_phone)
    TextView laborPhoneText;

    @BindView(R.id.tv_supervisor_unit)
    TextView supervisorUnitText;

    @BindView(R.id.tv_supervisor_name)
    TextView supervisorNameText;

    @BindView(R.id.tv_supervisor_phone)
    TextView supervisorPhoneText;

    @BindView(R.id.tv_department_name)
    TextView departmentNameText;

    @BindView(R.id.tv_department_address)
    TextView departmentAddressText;

    @BindView(R.id.tv_department_phone)
    TextView departmentPhoneText;

    @BindView(R.id.tv_institution_name)
    TextView institutionNameText;

    @BindView(R.id.tv_institution_address)
    TextView institutionAddressText;

    @BindView(R.id.tv_institution_phone)
    TextView institutionPhoneText;

    @BindView(R.id.tv_content)
    TextView contentText;

    @BindView(R.id.rl_images)
    RecyclerView imagesRV;

    private FarmerBrandPresenter farmerBrandPresenter;
    private QueryFilePresenter queryFilePresenter;
    private ShowImageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peasant_brand);
    }

    @Override
    protected void initUI() {
        titleText.setText("农民工维权告示牌");
        imagesRV.setLayoutManager(new GridLayoutManager(this, 3));
        imagesRV.addItemDecoration(new DividerGridItemDecoration(this, ContextCompat.getDrawable(this, R.drawable.divider_of_select_image)));
        imagesRV.setAdapter(adapter = new ShowImageAdapter(this));
        imagesRV.setNestedScrollingEnabled(false);
    }

    @Override
    protected void initData() {
        farmerBrandPresenter = new FarmerBrandPresenter(this, ProjectModel.newInstance());
        addPresenter(farmerBrandPresenter);
        queryFilePresenter = new QueryFilePresenter(this, CommonModel.newInstance());
        addPresenter(queryFilePresenter);
        farmerBrandPresenter.getFarmerInfo(UserManager.getInstance().getProjectId());
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

    @SuppressLint("SetTextI18n")
    @Override
    public void showFarmerInfo(FarmerBrandInfo farmerBrandInfo) {
        unitNameText.setText("单位名称：" + farmerBrandInfo.buildUnit);
        headerText.setText("项目负责人：" + farmerBrandInfo.projDutyPerson);
        phoneText.setText("联系电话：" + farmerBrandInfo.projDutyPersonPhone);
        constructionUnitText.setText("单位名称：" + farmerBrandInfo.unitName);
        enterpriseHeaderText.setText("企业负责人：" + farmerBrandInfo.leadingOfficial);
        enterprisePhoneText.setText("联系电话：" + farmerBrandInfo.leadingOfficialPhone);
        pmText.setText("项目经理：" + farmerBrandInfo.projManager);
        pmPhoneText.setText("联系电话：" + farmerBrandInfo.projManagerPhone);
        laborHeaderText.setText("劳务管理员：" + farmerBrandInfo.laborManager);
        laborPhoneText.setText("联系电话：" + farmerBrandInfo.laborManagerPhone);
        supervisorUnitText.setText("单位名称：" + farmerBrandInfo.supervisionUnits);
        supervisorNameText.setText("监理总监：" + farmerBrandInfo.projMajordomo);
        supervisorPhoneText.setText("联系电话：" + farmerBrandInfo.projMajordomoPhone);

        departmentNameText.setText("单位名称：" + farmerBrandInfo.competentDepartmentName);
        departmentAddressText.setText("地址：" + farmerBrandInfo.competentDepartmentAddress);
        departmentPhoneText.setText("投诉电话：" + farmerBrandInfo.competentDepartmentPhone);

        institutionNameText.setText("单位名称：" + farmerBrandInfo.labourMonitorName);
        institutionAddressText.setText("地址：" + farmerBrandInfo.labourMonitorAddress);
        institutionPhoneText.setText("投诉电话：" + farmerBrandInfo.labourMonitorPhone);

        contentText.setText(farmerBrandInfo.claimForRights);
        queryFilePresenter.queryFile(farmerBrandInfo.id);
    }

    @Override
    public void showFiles(List<FileEntity> fileEntities) {
        adapter.loadData(fileEntities);
    }

}
