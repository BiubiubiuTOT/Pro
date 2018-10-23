package com.ljkj.qxn.wisdomsitepro.ui.project.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.Utils.widget.ItemView;
import com.ljkj.qxn.wisdomsitepro.data.entity.FivePartyInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cdsp.android.ui.BaseRecyclerAdapter;

/**
 * 类描述：施工现场
 * 创建人：liufei
 * 创建时间：2017/11/25 16:37
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class SubUnitAdapter extends BaseRecyclerAdapter<FivePartyInfo.UnitPersonQualificationsVOListBean, SubUnitAdapter.ViewHolder> {
    private FivePartyInfo.UnitPersonQualificationsVOListBean.UnitBean unit;
    private FivePartyInfo.UnitPersonQualificationsVOListBean.UnitQualificationsBean unitQualifications;

    public SubUnitAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    protected boolean useItemAnimation() {
        return false;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_subcontracting_unit, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);
        FivePartyInfo.UnitPersonQualificationsVOListBean bean = getItem(position);
        holder.tv_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int visibility = holder.ll_content.getVisibility();
                holder.ll_content.setVisibility(visibility == View.VISIBLE ? View.GONE : View.VISIBLE);
                Drawable right = visibility == View.VISIBLE ? ContextCompat.getDrawable(mContext, R.mipmap.ic_collapse) : ContextCompat.getDrawable(mContext, R.mipmap.ic_expand);
                holder.tv_title.setCompoundDrawablesWithIntrinsicBounds(null, null, right, null);

            }
        });

        FivePartyInfo.UnitPersonQualificationsVOListBean.UnitPeopleListBean unitBean = null;
        FivePartyInfo.UnitPersonQualificationsVOListBean.UnitPeopleListBean projSkillManager = null;
        FivePartyInfo.UnitPersonQualificationsVOListBean.UnitPeopleListBean projManager = null;
        FivePartyInfo.UnitPersonQualificationsVOListBean.UnitPeopleListBean skillManager = null;
        List<FivePartyInfo.UnitPersonQualificationsVOListBean.UnitPeopleListBean> managers = new ArrayList<>();
        List<FivePartyInfo.UnitPersonQualificationsVOListBean.UnitPeopleListBean> unitPeopleList = bean.getUnitPeopleList();
        unitQualifications = bean.getUnitQualifications();
        unit = bean.getUnit();

        for (FivePartyInfo.UnitPersonQualificationsVOListBean.UnitPeopleListBean unitPeopleListBean : unitPeopleList) {
            if (unitPeopleListBean.getPersonType().equals("116")) {//项目负责人
                unitBean = unitPeopleListBean;
                continue;
            }
            if (unitPeopleListBean.getPersonType().equals("118")) {//项目技术负责人
                projSkillManager = unitPeopleListBean;
                continue;
            }
            if (unitPeopleListBean.getPersonType().equals("122")) {//质检员
                managers.add(unitPeopleListBean);
            }
            if (unitPeopleListBean.getPersonType().equals("123")) {//技术负责人
                skillManager = unitPeopleListBean;
            }
            if (unitPeopleListBean.getPersonType().equals("115")) {//技术负责人
                projManager = unitPeopleListBean;
            }

        }
        if (unit != null) {
            holder.tv_title.setText(unit.getUnitName());
            holder.addressItem.setContent(unit.getUnitAddress());
            holder.legalPersonNameItem.setContent(unit.getLegalRepre());
            holder.legalPersonPhoneItem.setContent(unit.getUnitPhone());
            holder.legalPersonIdcardItem.setContent(unit.getLegalRepreIdcard());
            holder.orgCodeItem.setContent(unit.getCompOnlyCode());
        }

        if (unitQualifications != null) {
            holder.gradeItem.setContent(unitQualifications.getGrade());
            holder.certificateNoItem.setContent(unitQualifications.getCertificateNo());
        }

        if (unitBean != null) {
            holder.projManagerItem.setContent(unitBean.getPerson());
            holder.projManagerPhoneItem.setContent(unitBean.getPhone());
            holder.projManagerIdcardItem.setContent(unitBean.getIdcard());
        }

        if (skillManager != null) {
            holder.skillManagerItem.setContent(skillManager.getPerson());
            holder.skillManagerPhoneItem.setContent(skillManager.getPhone());
            holder.skillManagerIdcardItem.setContent(skillManager.getIdcard());
        }

        if (projSkillManager != null) {
            holder.projSkillManagerItem.setContent(projSkillManager.getPerson());
            holder.projSkillNameItem.setContent(projSkillManager.getProfessional());
            holder.projSkillIdcardItem.setContent(projSkillManager.getIdcard());
        }
        if (projManager != null) {
            holder.projManagerItem.setContent(projManager.getPerson());
            holder.projManagerPhoneItem.setContent(projManager.getPhone());
            holder.projManagerIdcardItem.setContent(projManager.getIdcard());
            holder.buildGradeItem.setContent(projManager.getCertificateName());
            holder.codeItem.setContent(projManager.getCertificateNum());
        }

        holder.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        holder.recyclerView.setAdapter(new UnitAdapter(managers, 2));
    }


    class ViewHolder extends RecyclerView.ViewHolder {
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
        ItemView projManagerIdcardItem;
        @BindView(R.id.item_org_code)
        ItemView orgCodeItem;

        @BindView(R.id.item_skill_manager)
        ItemView skillManagerItem;
        @BindView(R.id.item_skill_manager_phone)
        ItemView skillManagerPhoneItem;
        @BindView(R.id.item_skill_manager_idcard)
        ItemView skillManagerIdcardItem;

        @BindView(R.id.item_build_grade)
        ItemView buildGradeItem;
        @BindView(R.id.item_code)
        ItemView codeItem;

        @BindView(R.id.item_proj_skill_manager)
        ItemView projSkillManagerItem;
        @BindView(R.id.item_proj_skill_name)
        ItemView projSkillNameItem;
        @BindView(R.id.item_proj_skill_idcard)
        ItemView projSkillIdcardItem;

        @BindView(R.id.recycler_view)
        RecyclerView recyclerView;

        @BindView(R.id.tv_title)
        TextView tv_title;
        @BindView(R.id.ll_content)
        LinearLayout ll_content;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
