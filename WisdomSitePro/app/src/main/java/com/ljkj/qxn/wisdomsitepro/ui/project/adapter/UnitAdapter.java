package com.ljkj.qxn.wisdomsitepro.ui.project.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.data.entity.FivePartyInfo;

import java.util.List;


public class UnitAdapter extends BaseQuickAdapter<FivePartyInfo.UnitPersonQualificationsVOListBean.UnitPeopleListBean, BaseViewHolder> {
    private int type;

    public UnitAdapter(@Nullable List<FivePartyInfo.UnitPersonQualificationsVOListBean.UnitPeopleListBean> data, int type) {
        super(R.layout.adapter_unit, data);
        this.type = type;
    }

    @Override
    protected void convert(BaseViewHolder helper, FivePartyInfo.UnitPersonQualificationsVOListBean.UnitPeopleListBean item) {
        String name = item.getPerson();
        helper.setText(R.id.tv_name, type == 1 ? "姓名：" + name + "   电话：" + item.getPhone() : "姓名：" + name + "   专业：" + item.getProfessional());
        helper.setText(R.id.tv_id_card, "身份证号：" + item.getIdcard());
        helper.setText(R.id.tv_code, type == 1 ? "资格证书号：" : "岗位证书号：" + item.getCertificateNum());

        if (name != null && name.length() > 0) {
            String str = String.valueOf(name.charAt(name.length() - 1));
            helper.setText(R.id.img_avatar, str);
        }
    }


}
