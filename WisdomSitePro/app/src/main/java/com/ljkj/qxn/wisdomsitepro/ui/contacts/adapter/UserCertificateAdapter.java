package com.ljkj.qxn.wisdomsitepro.ui.contacts.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.Utils.PickerDialogHelper;
import com.ljkj.qxn.wisdomsitepro.Utils.widget.InputItemView;
import com.ljkj.qxn.wisdomsitepro.data.entity.UserCertificateBean;
import com.ljkj.qxn.wisdomsitepro.data.entity.contract.QueryMemberInfo;

import java.util.Arrays;
import java.util.List;

import cdsp.android.glide.GlideHelper;

/**
 * Created by niujingtong on 2018/7/5.
 * 模块：
 */
public class UserCertificateAdapter extends BaseQuickAdapter<UserCertificateBean, BaseViewHolder> {
    private String[] certificates = {"一级建造师", "二级建造师", "造价工程师", "监理工程师",
            "建筑工程师", "结构工程师", "消防工程师", "给排水工程师", "岩土工程师", "电器工程师", "设备工程师", "环评工程师", "土木工程师", "规划工程师"};
    private String[] certificateIndex = {"178", "179", "180", "181",
            "182", "183", "184", "185", "186", "187", "188", "189", "190", "191"};
    private Context context;

    public UserCertificateAdapter(int layoutResId, @Nullable List<UserCertificateBean> data, Context context) {
        super(layoutResId, data);
        this.context = context;
    }


    @Override
    protected void convert(BaseViewHolder helper, final UserCertificateBean item) {
        final InputItemView name = helper.getView(R.id.input_certificate_name);
        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectUserCertificate(name, item);
            }
        });

        InputItemView number = helper.getView(R.id.input_certificate_number);
        number.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                item.setName(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    private void selectUserCertificate(final InputItemView view, final UserCertificateBean bean) {
        PickerDialogHelper.showPickerOption(context, Arrays.asList(certificates), 0, false, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                view.setContent(certificates[options1]);
                bean.setId(Arrays.asList(certificateIndex).get(options1));
            }
        });
    }
}
