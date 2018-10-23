package com.ljkj.qxn.wisdomsitepro.ui.application.supervisor;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.Utils.widget.InputItemView;
import com.ljkj.qxn.wisdomsitepro.data.FileEntity;
import com.ljkj.qxn.wisdomsitepro.data.entity.SiteStationRecorderManageDetailInfo;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import cdsp.android.ui.base.BaseFragment;

/**
 * 类描述：新增旁站记录-法/环
 * 创建人：lxx
 * 创建时间：2018/8/30
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class AddSideStationRecordMethodEnvironmentFragment extends BaseFragment implements DataInterface {

    @BindView(R.id.item_safety_technical_measures)
    InputItemView safetyTechnicalMeasuresItem;

    @BindView(R.id.et_sampling_data)
    EditText samplingDataText;

    @BindView(R.id.et_construction_process)
    EditText constructionProcessText;

    @BindView(R.id.et_problem)
    EditText problemText;
    private String safetyTechnicalMeasures;
    private String samplingData;
    private String process;
    private String problem;

    public static AddSideStationRecordMethodEnvironmentFragment newInstance() {
        AddSideStationRecordMethodEnvironmentFragment fragment = new AddSideStationRecordMethodEnvironmentFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_side_station_record_method_environment, container, false);
    }


    @Override
    protected void initUI() {

    }

    @Override
    protected void initData() {

    }


    @Override
    public void putData(SiteStationRecorderManageDetailInfo info, Map<String, List<String>> map) {
        safetyTechnicalMeasures = safetyTechnicalMeasuresItem.getContent();
        samplingData = samplingDataText.getText().toString();
        problem = problemText.getText().toString();
        process = constructionProcessText.getText().toString();

        info.setSecurityMeasuresInfo(safetyTechnicalMeasures);
        info.setSupervisionSamplingData(samplingData);
        info.setConstructionDesc(process);
        info.setProjRisk(problem);

    }

    @Override
    public boolean checkData() {
        safetyTechnicalMeasures = safetyTechnicalMeasuresItem.getContent();
        samplingData = samplingDataText.getText().toString();
        problem = problemText.getText().toString();
        process = constructionProcessText.getText().toString();

        if (TextUtils.isEmpty(safetyTechnicalMeasures)) {
            showError("请填写安全技术措施情况");
            return false;
        }

        if (TextUtils.isEmpty(samplingData)) {
            showError("请填写监理抽检数据情况");
            return false;
        }


        if (TextUtils.isEmpty(process)) {
            showError("请填写施工过程简述");
            return false;
        }

        if (TextUtils.isEmpty(problem)) {
            showError("请填写工程质量安全问题或隐患");
            return false;
        }
        return true;
    }

    @Override
    public void updateUIForDetail(SiteStationRecorderManageDetailInfo info) {
        safetyTechnicalMeasuresItem.setEnable(false);
        samplingDataText.setEnabled(false);
        problemText.setEnabled(false);
        constructionProcessText.setEnabled(false);

        String projRisk = info.getProjRisk();
        if (TextUtils.isEmpty(projRisk))
            problemText.setText("无");
        else
            problemText.setText(projRisk);


        String supervisionSamplingData = info.getSupervisionSamplingData();
        if (TextUtils.isEmpty(supervisionSamplingData))
            samplingDataText.setText("无");
        else
            samplingDataText.setText(supervisionSamplingData);

        String process = info.getConstructionDesc();
        if (TextUtils.isEmpty(process))
            constructionProcessText.setText("无");
        else
            constructionProcessText.setText(process);

        String securityMeasuresInfo = info.getSecurityMeasuresInfo();
        if (TextUtils.isEmpty(securityMeasuresInfo))
            safetyTechnicalMeasuresItem.setContent("无");
        else
            safetyTechnicalMeasuresItem.setContent(securityMeasuresInfo);
    }

    @Override
    public void showFiles(List<FileEntity> fileEntities) {

    }

}
