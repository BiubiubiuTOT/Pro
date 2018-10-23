package com.ljkj.qxn.wisdomsitepro.ui.project.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.Utils.widget.ItemView;
import com.ljkj.qxn.wisdomsitepro.data.entity.FivePartyInfo;
import com.ljkj.qxn.wisdomsitepro.ui.project.adapter.UnitAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cdsp.android.ui.base.BaseFragment;

/**
 * 类描述：监理单位
 * 创建人：lxx
 * 创建时间：2018/6/19
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SupervisorUnitFragment extends BaseFragment implements DataInterface {
    @BindView(R.id.item_main_supervisor)
    ItemView mainSupervisorItem;
    @BindView(R.id.item_main_supervisor_phone)
    ItemView mainSupervisorPhoneItem;
    @BindView(R.id.item_main_supervisor_code)
    ItemView mainSupervisorCodeItem;
    @BindView(R.id.item_main_supervisor_idcard)
    ItemView mainSupervisorIdcardItem;

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

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private UnitAdapter unitAdapter;

    private FivePartyInfo.UnitPersonQualificationsVOListBean.UnitQualificationsBean unitQualifications;
    private FivePartyInfo.UnitPersonQualificationsVOListBean.UnitBean unit;

    public static SupervisorUnitFragment newInstance() {
        SupervisorUnitFragment fragment = new SupervisorUnitFragment();
        Bundle bundle = new Bundle();
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
        View view = inflater.inflate(R.layout.fragment_supervisor_unit, container, false);
        return view;
    }

    @Override
    protected void initUI() {
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(unitAdapter = new UnitAdapter(new
                ArrayList<FivePartyInfo.UnitPersonQualificationsVOListBean.UnitPeopleListBean>(), 1));
    }

    @Override
    protected void initData() {

    }

    @Override
    public void updateUIForDetail(FivePartyInfo info) {
        FivePartyInfo.UnitPersonQualificationsVOListBean.UnitPeopleListBean unitBean = null;
        FivePartyInfo.UnitPersonQualificationsVOListBean.UnitPeopleListBean mainManager = null;
        List<FivePartyInfo.UnitPersonQualificationsVOListBean.UnitPeopleListBean> managers = new ArrayList<>();
        if (info != null) {
            List<FivePartyInfo.UnitPersonQualificationsVOListBean> list = info.getUnitPersonQualificationsVOList();
            for (FivePartyInfo.UnitPersonQualificationsVOListBean bean : list) {
                unit = bean.getUnit();
                if (unit.getType() == 108) {
                    List<FivePartyInfo.UnitPersonQualificationsVOListBean.UnitPeopleListBean> unitPeopleList = bean.getUnitPeopleList();
                    unitQualifications = bean.getUnitQualifications();
                    for (FivePartyInfo.UnitPersonQualificationsVOListBean.UnitPeopleListBean unitPeopleListBean : unitPeopleList) {
                        if (unitPeopleListBean.getPersonType().equals("116")) {
                            unitBean = unitPeopleListBean;
                            continue;
                        }
                        if (unitPeopleListBean.getPersonType().equals("120")) {
                            mainManager = unitPeopleListBean;
                            continue;
                        }
                        if (unitPeopleListBean.getPersonType().equals("121")) {
                            managers.add(unitPeopleListBean);
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
            if (mainManager != null) {
                mainSupervisorItem.setContent(mainManager.getPerson());
                mainSupervisorCodeItem.setContent(mainManager.getCertificateNum());
                mainSupervisorPhoneItem.setContent(mainManager.getPhone());
                mainSupervisorIdcardItem.setContent(mainManager.getIdcard());
            }

            unitAdapter.setNewData(managers);
        }
    }

    @Override
    public void showSubData(FivePartyInfo info) {

    }
}