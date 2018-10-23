package com.ljkj.qxn.wisdomsitepro.ui.project.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.Utils.widget.ItemView;
import com.ljkj.qxn.wisdomsitepro.data.entity.FivePartyInfo;

import java.util.List;

import butterknife.BindView;
import cdsp.android.ui.base.BaseFragment;

/**
 * 类描述：建设单位/勘察单位/设计单位
 * 创建人：lxx
 * 创建时间：2018/6/19
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class BuildUnitFragment extends BaseFragment implements DataInterface {
    @BindView(R.id.item_unit_name)
    ItemView nameItem;
    @BindView(R.id.item_address)
    ItemView addressItem;
    @BindView(R.id.item_grade)
    ItemView gradeItem;
    @BindView(R.id.item_certificate_no)
    ItemView certificateNoItem;
    @BindView(R.id.item_legal_person)
    ItemView legalPersonNameItem;
    @BindView(R.id.item_legal_person_phone)
    ItemView legalPersonPhoneItem;
    @BindView(R.id.item_legal_person_idcard)
    ItemView legalPersonIdcardItem;
    @BindView(R.id.item_proj_manager)
    ItemView projManagerItem;
    @BindView(R.id.item_proj_manager_phone)
    ItemView projManagerPhoneItem;
    @BindView(R.id.item_proj_manager_idcard)
    ItemView projManagerIdcard;
    @BindView(R.id.item_org_code)
    ItemView orgCodeItem;


    public static final int TYPE_BUILD = 105;
    public static final int TYPE_SURVEY = 107;
    public static final int TYPE_DESIGN = 106;
    private int type;
    private FivePartyInfo.UnitPersonQualificationsVOListBean.UnitQualificationsBean unitQualifications;
    private FivePartyInfo.UnitPersonQualificationsVOListBean.UnitBean unit;

    public static BuildUnitFragment newInstance(int type) {
        BuildUnitFragment fragment = new BuildUnitFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_build_unit, container, false);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.type = getArguments().getInt("type", TYPE_BUILD);
    }

    @Override
    protected void initUI() {
    }

    @Override
    protected void initData() {

    }

    @Override
    public void updateUIForDetail(FivePartyInfo info) {
        FivePartyInfo.UnitPersonQualificationsVOListBean.UnitPeopleListBean unitBean = null;
        if (info != null) {
            List<FivePartyInfo.UnitPersonQualificationsVOListBean> list = info.getUnitPersonQualificationsVOList();
            for (FivePartyInfo.UnitPersonQualificationsVOListBean bean : list) {
                unit = bean.getUnit();
                if (unit.getType() == type) {
                    List<FivePartyInfo.UnitPersonQualificationsVOListBean.UnitPeopleListBean> unitPeopleList = bean.getUnitPeopleList();
                    unitQualifications = bean.getUnitQualifications();
                    for (FivePartyInfo.UnitPersonQualificationsVOListBean.UnitPeopleListBean unitPeopleListBean : unitPeopleList) {
                        if (unitPeopleListBean.getPersonType().equals("116")) {
                            unitBean = unitPeopleListBean;
                            break;
                        }
                    }
                    break;
                }
            }
            if (unit != null) {
                nameItem.setContent(unit.getUnitName());
                addressItem.setContent(unit.getUnitAddress());
                legalPersonNameItem.setContent(unit.getLegalRepre());
                legalPersonPhoneItem.setContent(unit.getUnitPhone());
                legalPersonIdcardItem.setContent(unit.getLegalRepreIdcard());
                orgCodeItem.setContent(unit.getCompOnlyCode());
            }

            if (unitQualifications != null) {
                gradeItem.setContent(unitQualifications.getGrade());
                certificateNoItem.setContent(unitQualifications.getCertificateNo());
            }

            if (unitBean != null) {
                projManagerItem.setContent(unitBean.getPerson());
                projManagerPhoneItem.setContent(unitBean.getPhone());
                projManagerIdcard.setContent(unitBean.getIdcard());
            }
        }
    }

    @Override
    public void showSubData(FivePartyInfo info) {

    }
}