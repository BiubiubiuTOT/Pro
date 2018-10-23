package com.ljkj.qxn.wisdomsitepro.ui.quality;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.Utils.DateUtil;
import com.ljkj.qxn.wisdomsitepro.contract.quality.QualitySuperviseSignContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.QualitySuperviseSignInfo;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.model.QualityModel;
import com.ljkj.qxn.wisdomsitepro.presenter.quality.QualitySuperviseSignPresenter;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseActivity;

/**
 * 类描述：质量监督注册登记
 * 创建人：liufei
 * 创建时间：2018/2/6 10:30
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class QualitySuperviseSignActivity extends BaseActivity implements QualitySuperviseSignContract.View {

    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.tv_project_name)
    TextView tvProjectName;

    @BindView(R.id.tv_project_address)
    TextView tvProjectAddress;

    @BindView(R.id.tv_project_type)
    TextView tvProjectType;

    @BindView(R.id.tv_project_count)
    TextView tvProjectCount;

    @BindView(R.id.tv_project_date)
    TextView tvProjectDate;

    @BindView(R.id.tv_company)
    TextView tvCompany;

    @BindView(R.id.tv_address)
    TextView tvAddress;

    @BindView(R.id.tv_area)
    TextView tvArea;

    @BindView(R.id.tv_height)
    TextView tvHeight;

    @BindView(R.id.tv_organ)
    TextView tvOrgan;

    @BindView(R.id.tv_base)
    TextView tvBase;

    @BindView(R.id.tv_form)
    TextView tvForm;

    QualitySuperviseSignPresenter qualitySuperviseSignPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quality_supervise_sign);
    }

    @Override
    protected void initUI() {
        tvTitle.setText("质量监督注册登记");
    }

    @Override
    protected void initData() {

        qualitySuperviseSignPresenter = new QualitySuperviseSignPresenter(this, QualityModel.newInstance());
        addPresenter(qualitySuperviseSignPresenter);
        qualitySuperviseSignPresenter.viewQualitySuperviseSignInfo(UserManager.getInstance().getProjectId());

    }

    @Override
    public void showQualitySuperviseSignInfo(QualitySuperviseSignInfo data) {
        if (data != null) {
            fillData(data);
            tvForm.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 数据填充
     *
     * @param data
     */
    private void fillData(QualitySuperviseSignInfo data) {
        tvProjectName.setText(data.getProName());
        tvProjectAddress.setText(data.getProAddress());
        tvProjectType.setText(data.getProUse());
        tvProjectCount.setText(String.format(this.getString(R.string.quality_floor_count), data.getDscs(), data.getDxcs()));
        tvCompany.setText(data.getJsdw());
        tvAddress.setText(data.getProAddress());
        tvArea.setText(data.getArea());
        tvHeight.setText(data.getJzgd());
        tvOrgan.setText(data.getJclx());
        tvBase.setText(data.getJclx());
        tvProjectDate.setText(DateUtil.format(data.getDeclareDate()));
        tvForm.setVisibility(View.VISIBLE);
    }

    @OnClick({R.id.tv_back, R.id.tv_form})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.tv_form:
                startActivity(new Intent(this, QualitySuperviRegiActivity.class));
                break;
            default:
                break;
        }
    }
}
