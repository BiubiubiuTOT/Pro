package com.ljkj.qxn.wisdomsitepro.ui.project.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;

import cdsp.android.util.ToastUtils;

/**
 * 类描述：特种设备
 * 创建人：lxx
 * 创建时间：2018/6/20
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class ProjectSpecialEquipmentView extends LinearLayout {
    public ProjectSpecialEquipmentView(Context context) {
        this(context, null);
    }

    public ProjectSpecialEquipmentView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProjectSpecialEquipmentView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(VERTICAL);

        test();
    }

    public void setData() {
    }

    private View createEquipmentItem(String name, String exception) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_project_special_equipment_item, this, false);
        TextView equipmentNameText = (TextView) view.findViewById(R.id.tv_equipment_name);
        TextView equipmentExceptionText = (TextView) view.findViewById(R.id.tv_equipment_exception);
        TextView equipmentDetailText = (TextView) view.findViewById(R.id.tv_equipment_detail);

        equipmentNameText.setText(name);
        equipmentExceptionText.setText(exception);
        equipmentDetailText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return view;
    }

    void test() {
        View testView = createEquipmentItem("塔吊在线：0", "塔吊异常：0");
        testView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showShort("请接入塔吊设备");
            }
        });
        addView(testView);

    }

}
