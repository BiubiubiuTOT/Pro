package com.ljkj.qxn.wisdomsitepro.ui.application;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.Utils.DateUtil;
import com.ljkj.qxn.wisdomsitepro.Utils.PickerDialogHelper;
import com.ljkj.qxn.wisdomsitepro.Utils.widget.ItemView;
import com.ljkj.qxn.wisdomsitepro.contract.application.LabourContract;
import com.ljkj.qxn.wisdomsitepro.contract.application.LabourDetailContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.ContractInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.CreditInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.ExperienceInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.InjuryInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.LabourDetail;
import com.ljkj.qxn.wisdomsitepro.data.entity.LabourInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.PageInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.SkillInfo;
import com.ljkj.qxn.wisdomsitepro.model.ApplicationModel;
import com.ljkj.qxn.wisdomsitepro.presenter.application.LabourDetailPresenter;
import com.ljkj.qxn.wisdomsitepro.presenter.application.LabourPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseActivity;

public class LaborPersonDetailActivity extends BaseActivity implements LabourContract.View, LabourDetailContract.View {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ll_id_card)
    LinearLayout idCardLayout;
    @BindView(R.id.tv_id_card)
    TextView tvIdCard;
    @BindView(R.id.tv_work_card)
    TextView tvWorkCard;
    @BindView(R.id.ll_work_card)
    LinearLayout workCardLayout;
    @BindView(R.id.tv_credit_history)
    TextView tvCreditHistory;
    @BindView(R.id.ll_credit_history)
    LinearLayout creditHistoryLayout;
    @BindView(R.id.tv_skill_history)
    TextView tvSkillHistory;
    @BindView(R.id.ll_skill_history)
    LinearLayout skillHistoryLayout;
    @BindView(R.id.tv_labour_contract)
    TextView tvLabourContract;
    @BindView(R.id.ll_labour_contract)
    LinearLayout labourContractLayout;
    @BindView(R.id.tv_work_injury_history)
    TextView tvWorkInjuryHistory;
    @BindView(R.id.ll_injury_history)
    LinearLayout injuryHistoryLayout;
    @BindView(R.id.tv_work_experience)
    TextView tvWorkExperience;
    @BindView(R.id.ll_work_experience)
    LinearLayout workExperienceLayout;

    @BindView(R.id.item_name)
    ItemView nameItem;
    @BindView(R.id.item_sex)
    ItemView sexItem;
    @BindView(R.id.item_nation)
    ItemView nationItem;
    @BindView(R.id.item_birthday)
    ItemView birthdayItem;
    @BindView(R.id.item_org)
    ItemView orgItem;
    @BindView(R.id.item_end_date)
    ItemView endDateItem;
    @BindView(R.id.item_id_card)
    ItemView idCardItem;
    @BindView(R.id.item_address)
    ItemView addressItem;

    @BindView(R.id.item_phone)
    ItemView phoneItem;
    @BindView(R.id.item_team)
    ItemView teamItem;
    @BindView(R.id.item_in_date)
    ItemView inDataItem;
    @BindView(R.id.item_out_date)
    ItemView outDateItem;
    @BindView(R.id.item_no)
    ItemView noItem;
    @BindView(R.id.item_social_person)
    ItemView socialPersonItem;
    @BindView(R.id.item_emergency_person)
    ItemView emergencyPersonItem;
    @BindView(R.id.item_emergency_phone)
    ItemView emergencyPhoneItem;
    @BindView(R.id.item_sub_unit)
    ItemView subUnitItem;
    @BindView(R.id.item_sub_unit_person)
    ItemView subUnitPersonItem;
    @BindView(R.id.item_sub_unit_phone)
    ItemView subUnitPhone;
    @BindView(R.id.item_work_type)
    ItemView workTypeItem;
    @BindView(R.id.item_is_upload)
    ItemView isUploadItem;
    @BindView(R.id.item_is_poor)
    ItemView isPoorItem;

    @BindView(R.id.tv_credit_history_num)
    TextView tvCreditHistoryNum; //信用记录条数
    @BindView(R.id.item_credit_name)
    ItemView creditNameItem;
    @BindView(R.id.item_credit_type)
    ItemView creditTypeItem;
    @BindView(R.id.item_credit_describe)
    ItemView creditDescripeItem;
    @BindView(R.id.item_credit_project)
    ItemView creditProjectItem;
    @BindView(R.id.item_credit_sub_unit)
    ItemView creditSubUnitItem;
    @BindView(R.id.item_credit_prize_unit)
    ItemView creditPriceUnitItem;
    @BindView(R.id.item_credit_date)
    ItemView creditDate;
    @BindView(R.id.item_credit_remark)
    ItemView creditRemarkItem;

    @BindView(R.id.tv_skill_history_num)
    TextView tvSkillHistoryNum; //技能记录条数
    @BindView(R.id.item_skill_name)
    ItemView skillNameItem;
    @BindView(R.id.item_skill_org)
    ItemView skillOrgItem;
    @BindView(R.id.item_skill_date)
    ItemView skillDateItem;
    @BindView(R.id.item_skill_remark)
    ItemView skillRemarkItem;

    @BindView(R.id.tv_labour_contract_num)
    TextView tvLaborContractNum; //劳务合同条数
    @BindView(R.id.item_contract_name)
    ItemView contractNameItem;
    @BindView(R.id.item_contract_id_card)
    ItemView contractIdCardItem;
    @BindView(R.id.item_contract_construction_unit)
    ItemView contractConstructionUnitItem;
    @BindView(R.id.item_contract_sub_unit)
    ItemView contractSubUnitItem;
    @BindView(R.id.item_contract_date)
    ItemView contractDateItem;
    @BindView(R.id.item_contract_start_date)
    ItemView contractStartDateItem;
    @BindView(R.id.item_contract_end_date)
    ItemView contractEndDateItem;
    @BindView(R.id.item_pay)
    ItemView contractPayItem;

    @BindView(R.id.tv_injury_history_num)
    TextView tvInjuryHistoryNum; //工伤记录条数
    @BindView(R.id.item_injury_date)
    ItemView injuryDateItem;
    @BindView(R.id.item_injury_address)
    ItemView injuryAddressItem;
    @BindView(R.id.item_injury_type)
    ItemView injuryTypeItem;
    @BindView(R.id.item_injury_reason)
    ItemView injuryReasonItem;
    @BindView(R.id.item_injury_die_situation)
    ItemView injuryDieSituationItem;
    @BindView(R.id.item_injury_work_level)
    ItemView injuryWorkLevelItem;
    @BindView(R.id.item_injury_age)
    ItemView injuryAgeItem;
    @BindView(R.id.item_injury_education)
    ItemView injuryEducationItem;

    @BindView(R.id.tv_work_experience_num)
    TextView tvWorkExperienceNum; //工伤记录条数
    @BindView(R.id.item_experience_project)
    ItemView experienceProjectItem;
    @BindView(R.id.item_experience_sub_unit)
    ItemView experienceSubUnitItem;
    @BindView(R.id.item_experience_work_time)
    ItemView experienceWorkTimeItem;
    @BindView(R.id.item_experience_team)
    ItemView experienceTeamItem;

    LabourPresenter labourPresenter;
    LabourDetailPresenter labourDetailPresenter;
    private List<ContractInfo> contractList;
    private List<CreditInfo> creditList;
    private List<InjuryInfo> injuryList;
    private List<ExperienceInfo> experienceList;
    private List<SkillInfo> skillList;

    private List<String> contractIndex = new ArrayList<>();
    private List<String> creditIndex = new ArrayList<>();
    private List<String> injuryIndex = new ArrayList<>();
    private List<String> experienceIndex = new ArrayList<>();
    private List<String> skillIndex = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_labor_person_detail);
    }

    @Override
    protected void initUI() {
        tvTitle.setText("劳务人员档案");

        tvCreditHistoryNum.setTag(0);
        tvInjuryHistoryNum.setTag(0);
        tvLaborContractNum.setTag(0);
        tvWorkExperienceNum.setTag(0);
        tvSkillHistoryNum.setTag(0);
    }

    @Override
    protected void initData() {
        labourPresenter = new LabourPresenter(this, ApplicationModel.newInstance());
        labourDetailPresenter = new LabourDetailPresenter(this, ApplicationModel.newInstance());
        addPresenter(labourPresenter);
        addPresenter(labourDetailPresenter);

        String id = getIntent().getStringExtra("id");
        if (id != null) {
            labourPresenter.getLabourDetail(id);

            labourDetailPresenter.getContractList(id);
            labourDetailPresenter.getCreditList(id);
            labourDetailPresenter.getExperienceList(id);
            labourDetailPresenter.getInjuryList(id);
            labourDetailPresenter.getSkillList(id);
        }
    }

    @Override
    public void showPageLabourInfo(PageInfo<LabourInfo> data) {

    }

    @Override
    public void showLabourDetail(LabourDetail data) {
        nameItem.setContent(data.getName());
        sexItem.setContent(data.getSex() == 0 ? "男" : "女");
        nationItem.setContent(data.getNation());
        birthdayItem.setContent(data.getBarthday());
        addressItem.setContent(data.getAddress());
        idCardItem.setContent(data.getCertificatesNum());
        orgItem.setContent(data.getSignOrganization());
        endDateItem.setContent(DateUtil.format(data.getStartDate()) + "至" + DateUtil.format(data.getEndDate()));

        phoneItem.setContent(data.getPhoneNum());
        teamItem.setContent(data.getTeamsType());
        inDataItem.setContent(data.getAccessDate());
        noItem.setContent(data.getWorkCode());
        isPoorItem.setContent(data.getIsPoverty() == 0 ? "否" : "是");
        isUploadItem.setContent(data.getIsReported() == 0 ? "未上报" : "已上报");
        workTypeItem.setContent(data.getSettleWageMethod() == 0 ? "记工" : "计件");

        subUnitItem.setContent(data.getLaborCompany());//分包单位

        outDateItem.setContent("");//离场日期
        socialPersonItem.setContent("");//社保人
        emergencyPersonItem.setContent("");//紧急联系人
        emergencyPhoneItem.setContent("");//紧急联系人电话
        subUnitPersonItem.setContent("");//分包单位负责人
        subUnitPhone.setContent("");//分包单位电话
    }


    @OnClick({R.id.tv_back, R.id.tv_id_card, R.id.tv_work_card,
            R.id.tv_credit_history, R.id.tv_skill_history,
            R.id.tv_labour_contract, R.id.tv_work_injury_history,
            R.id.tv_work_experience, R.id.iv_credit_history_arrow_down, R.id.iv_skill_history_arrow_down,
            R.id.iv_labour_contract_arrow_down, R.id.iv_injury_history_arrow_down, R.id.iv_work_experience_arrow_down
    })
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.tv_id_card:
                setLayout(idCardLayout, tvIdCard);
                break;
            case R.id.tv_work_card:
                setLayout(workCardLayout, tvWorkCard);
                break;
            case R.id.tv_credit_history:
                setLayout(creditHistoryLayout, tvCreditHistory);
                break;
            case R.id.tv_skill_history:
                setLayout(skillHistoryLayout, tvSkillHistory);
                break;
            case R.id.tv_labour_contract:
                setLayout(labourContractLayout, tvLabourContract);
                break;
            case R.id.tv_work_injury_history:
                setLayout(injuryHistoryLayout, tvWorkInjuryHistory);
                break;
            case R.id.tv_work_experience:
                setLayout(workExperienceLayout, tvWorkExperience);
                break;
            case R.id.iv_work_experience_arrow_down:
                showExperienceWindow();
                break;
            case R.id.iv_injury_history_arrow_down:
                showInjuryWindow();
                break;
            case R.id.iv_labour_contract_arrow_down:
                showContractWindow();
                break;
            case R.id.iv_skill_history_arrow_down:
                showSkillWindow();
                break;
            case R.id.iv_credit_history_arrow_down:
                showCreditWindow();
                break;
            default:
                break;
        }
    }

    private void showCreditWindow() {
        if (creditIndex.isEmpty()) {
            return;
        }
        PickerDialogHelper.showPickerOption(this, creditIndex, (Integer) tvCreditHistoryNum.getTag(), false, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                tvCreditHistoryNum.setTag(options1);
                tvCreditHistoryNum.setText(creditIndex.get(options1));

                showCreditData(options1);
            }
        });
    }

    private void showCreditData(int options1) {
        CreditInfo info = creditList.get(options1);
        creditNameItem.setContent(info.getName());
        creditProjectItem.setContent(info.getProjName());
        creditTypeItem.setContent(info.getBehaviorType());
        creditDescripeItem.setContent(info.getBehaviorDescription());
        creditSubUnitItem.setContent(info.getLaborCompany());
        creditDate.setContent(DateUtil.format(info.getRewordPunishDate()));

//        creditRemarkItem.setContent(info.get);

        creditPriceUnitItem.setContent("");//奖惩单位
    }

    private void showSkillWindow() {
        if (skillIndex.isEmpty()) {
            return;
        }

        PickerDialogHelper.showPickerOption(this, skillIndex, (Integer) tvSkillHistoryNum.getTag(), false, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                tvSkillHistoryNum.setTag(options1);
                tvSkillHistoryNum.setText(skillIndex.get(options1));

                showSkillData(options1);
            }
        });
    }

    private void showSkillData(int options1) {
        SkillInfo info = skillList.get(options1);
        skillNameItem.setContent(info.getSkillName());
        skillOrgItem.setContent(info.getCertificateUnit());
        skillDateItem.setContent(info.getEvidenceDate());

        skillRemarkItem.setContent("");//技能备注
    }

    private void showContractWindow() {
        if (contractIndex.isEmpty()) {
            return;
        }
        PickerDialogHelper.showPickerOption(this, contractIndex, (Integer) tvLaborContractNum.getTag(), false, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                tvLaborContractNum.setTag(options1);
                tvLaborContractNum.setText(contractIndex.get(options1));

                showContractData(options1);
            }
        });
    }

    private void showContractData(int options1) {
        ContractInfo info = contractList.get(options1);
        contractNameItem.setContent(info.getName());
        contractPayItem.setContent(info.getContractSalary());
        contractEndDateItem.setContent(info.getContractEndDate());
        contractDateItem.setContent(info.getSignDate());
        contractStartDateItem.setContent(info.getSignDate());//合同生效日期
        contractSubUnitItem.setContent(info.getLaborCompany());//分包单位

        contractIdCardItem.setContent("");//身份证号
        contractConstructionUnitItem.setContent("");//施工单位
    }

    private void showInjuryWindow() {
        if (injuryIndex.isEmpty()) {
            return;
        }

        PickerDialogHelper.showPickerOption(this, injuryIndex, (Integer) tvInjuryHistoryNum.getTag(), false, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                tvInjuryHistoryNum.setTag(options1);
                tvInjuryHistoryNum.setText(injuryIndex.get(options1));

                showInjuryData(options1);
            }
        });
    }

    private void showInjuryData(int options1) {
        InjuryInfo info = injuryList.get(options1);

        injuryAddressItem.setContent(info.getAccidentAddress());
        injuryDateItem.setContent(info.getAccidentTime());
        injuryDieSituationItem.setContent(info.getCasualtySituation());
        injuryReasonItem.setContent(info.getAccidentReason());
        injuryTypeItem.setContent(info.getAccidentType());

        injuryAgeItem.setContent("");//工种年龄
        injuryEducationItem.setContent("");//教育程度
        injuryWorkLevelItem.setContent("");//工种级别
    }

    private void showExperienceWindow() {
        if (experienceIndex.isEmpty()) {
            return;
        }

        PickerDialogHelper.showPickerOption(this, experienceIndex, (Integer) tvWorkExperienceNum.getTag(), false, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                tvWorkExperienceNum.setTag(options1);
                tvWorkExperienceNum.setText(experienceIndex.get(options1));

                showExperienceData(options1);
            }
        });
    }

    private void showExperienceData(int options1) {
        ExperienceInfo experienceInfo = experienceList.get(options1);

        experienceProjectItem.setContent(experienceInfo.getProjName());
        experienceSubUnitItem.setContent(experienceInfo.getLaborCompany());
        experienceWorkTimeItem.setContent(experienceInfo.getCumulativeWorkHours());
        experienceTeamItem.setContent(experienceInfo.getLaborExperienceDescription());
    }

    private void setLayout(LinearLayout layout, TextView textView) {
        layout.setVisibility(layout.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
        Drawable right = layout.getVisibility() == View.VISIBLE ? ContextCompat.getDrawable(this, R.mipmap.ic_collapse) : ContextCompat.getDrawable(this, R.mipmap.ic_expand);
        textView.setCompoundDrawablesWithIntrinsicBounds(null, null, right, null);
    }

    @Override
    public void showContractList(List<ContractInfo> data) {
        contractList = data;
        int index = 1;
        if (data != null && !data.isEmpty()) {
            for (ContractInfo info : data) {
                contractIndex.add(String.valueOf(index));
                index++;
            }
            showContractData(0);
            tvLaborContractNum.setVisibility(View.VISIBLE);
        } else tvLaborContractNum.setVisibility(View.GONE);
    }

    @Override
    public void showCreditList(List<CreditInfo> data) {
        creditList = data;
        int index = 1;
        if (data != null && !data.isEmpty()) {
            for (CreditInfo info : data) {
                creditIndex.add(String.valueOf(index));
                index++;
            }
            showCreditData(0);
            tvCreditHistoryNum.setVisibility(View.VISIBLE);
        } else tvCreditHistoryNum.setVisibility(View.GONE);
    }

    @Override
    public void showInjuryList(List<InjuryInfo> data) {
        injuryList = data;

        int index = 1;
        if (data != null && !data.isEmpty()) {
            for (InjuryInfo info : data) {
                injuryIndex.add(String.valueOf(index));
                index++;
            }

            showInjuryData(0);
            tvInjuryHistoryNum.setVisibility(View.VISIBLE);
        } else tvInjuryHistoryNum.setVisibility(View.GONE);
    }

    @Override
    public void showExperienceList(List<ExperienceInfo> data) {
        experienceList = data;

        int index = 1;
        if (data != null && !data.isEmpty()) {
            for (ExperienceInfo info : data) {
                experienceIndex.add(String.valueOf(index));
                index++;
            }

            showExperienceData(0);
            tvWorkExperienceNum.setVisibility(View.VISIBLE);
        } else tvWorkExperienceNum.setVisibility(View.GONE);
    }

    @Override
    public void showSkillList(List<SkillInfo> data) {
        skillList = data;

        int index = 1;
        if (data != null && !data.isEmpty()) {
            for (SkillInfo info : data) {
                skillIndex.add(String.valueOf(index));
                index++;
            }

            showSkillData(0);

            tvSkillHistoryNum.setVisibility(View.VISIBLE);
        } else tvSkillHistoryNum.setVisibility(View.GONE);
    }
}
