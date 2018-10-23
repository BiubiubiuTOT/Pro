package com.ljkj.qxn.wisdomsitepro.ui.safe;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.contract.safe.SafetySupervisionDeclarationContract;
import com.ljkj.qxn.wisdomsitepro.data.BaseEntity;
import com.ljkj.qxn.wisdomsitepro.data.TxtEntryEntity;
import com.ljkj.qxn.wisdomsitepro.data.entity.SafetySupervisionDeclarationInfo;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.model.SafeModel;
import com.ljkj.qxn.wisdomsitepro.presenter.safe.SafetySupervisionDeclarationPresenter;
import com.ljkj.qxn.wisdomsitepro.ui.safe.adapter.FilesAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseActivity;

/**
 * 类描述：安全监督申报
 * 创建人：mhl
 * 创建时间：2018/2/7 13:52
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SafetySupervisionDeclarationActivity extends BaseActivity implements SafetySupervisionDeclarationContract.View {

    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.tv_pro_name)
    TextView tvProName;

    @BindView(R.id.img_statu)
    ImageView imgStatu;

    @BindView(R.id.tv_build_unit)
    TextView tvBuildUnit;

    @BindView(R.id.tv_construction_control_unit)
    TextView tvConstructionControlUnit;

    @BindView(R.id.tv_construction_unit)
    TextView tvConstructionUnit;

    @BindView(R.id.tv_area)
    TextView tvArea;

    @BindView(R.id.tv_apply_date)
    TextView tvApplyDate;

    @BindView(R.id.rl_files)
    RecyclerView rlFiles;

    @BindView(R.id.ll_no_data)
    ViewGroup noDataLayout;

    @BindView(R.id.content)
    ViewGroup contentLayout;

    FilesAdapter filesAdapter;

    SafetySupervisionDeclarationPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safety_supervision_declaration);
    }

    @Override
    protected void initUI() {

        tvTitle.setText("安全监督申报");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rlFiles.setLayoutManager(linearLayoutManager);
        rlFiles.setAdapter(filesAdapter = new FilesAdapter(this, new ArrayList<BaseEntity>()));
        rlFiles.setNestedScrollingEnabled(false);

    }

    @Override
    protected void initData() {

        presenter = new SafetySupervisionDeclarationPresenter(this, SafeModel.newInstance());
        addPresenter(presenter);
        presenter.viewSupervisionDeclaration(UserManager.getInstance().getProjectId());
    }

    @OnClick(R.id.tv_back)
    public void onViewClicked() {
    }

    @Override
    public void showSupervisionDeclaration(SafetySupervisionDeclarationInfo data) {
        if (data == null || TextUtils.isEmpty(data.getName())) {
            noDataLayout.setVisibility(View.VISIBLE);
            imgStatu.setVisibility(View.GONE);
            contentLayout.setVisibility(View.GONE);
            return;
        }
        fillData(data);
        fillFiles(data);
    }

    private void fillData(SafetySupervisionDeclarationInfo data) {

        tvProName.setText(data.getName());
        tvBuildUnit.setText(data.getJsUnit());
        tvConstructionControlUnit.setText(data.getJlUnit());
        tvConstructionUnit.setText(data.getSgUnit());
        tvArea.setText(data.getJzmj());
        tvApplyDate.setText(data.getApplyTime());

        //已通过
        if (TextUtils.equals(data.getStatus(), "01")) {
            imgStatu.setVisibility(View.GONE);
        }

        //申报中
        else if (TextUtils.equals(data.getStatus(), "02")) {
            imgStatu.setImageResource(R.mipmap.ic_in_the_declaration);
            imgStatu.setVisibility(View.VISIBLE);
        }

        //被驳回
        else if (TextUtils.equals(data.getStatus(), "03")) {
            imgStatu.setVisibility(View.GONE);
        }
    }

    private void fillFiles(SafetySupervisionDeclarationInfo info) {

        List<BaseEntity> data = new ArrayList<BaseEntity>();
        filesAdapter.setItems(data);
        if (info.getAttachment() != null) {

            //贵州省建设工程安全监督注册登记表
            if (info.getAttachment().getFileList() != null) {

                data.add(new TxtEntryEntity("贵州省建设工程安全监督注册登记表"));
                data.addAll(info.getAttachment().getFileList());
            }

            //危险性较大的分部分项工程清单
            if (info.getAttachment().getFile_wxqdList() != null) {

                data.add(new TxtEntryEntity("危险性较大的分部分项工程清单"));
                data.addAll(info.getAttachment().getFile_wxqdList());
            }

            //贵州省建设工程施工安全监督交底告知书
            if (info.getAttachment().getJdgzs_fileList() != null) {

                data.add(new TxtEntryEntity("贵州省建设工程施工安全监督交底告知书"));
                data.addAll(info.getAttachment().getJdgzs_fileList());
            }

            //施工安全监督工作方案
            if (info.getAttachment().getWork_planList() != null) {

                data.add(new TxtEntryEntity("施工安全监督工作方案"));
                data.addAll(info.getAttachment().getWork_planList());
            }
        }
        filesAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.tv_back)
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            default:
                break;
        }
    }

}
