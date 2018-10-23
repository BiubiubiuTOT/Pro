package com.ljkj.qxn.wisdomsitepro.Utils;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.Utils.widget.LineEditText;

/**
 * 类描述：弹窗
 * 创建人：liufei
 * 创建时间：2017/10/10
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class DialogUtil {

    //部门弹框
    private static Dialog mOperateDepartmentDialog;
    //删除部门弹框
    private static Dialog mDeleteDepartmentDialog;

    public static void showOperateDepartmentDialog(final Context context, String dialogTitle, String departmentName, final OnDepartmentOperateListener listener) {
        mOperateDepartmentDialog = new Dialog(context, R.style.DialogCommonStyle);
        mOperateDepartmentDialog.setContentView(R.layout.dialog_operate_department);
        TextView title = (TextView) mOperateDepartmentDialog.findViewById(R.id.tv_dialog_title);
        title.setText(dialogTitle);

        mOperateDepartmentDialog.findViewById(R.id.iv_close_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOperateDepartmentDialog.dismiss();
            }
        });
        mOperateDepartmentDialog.findViewById(R.id.tv_quit_edit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOperateDepartmentDialog.dismiss();
            }
        });
        final LineEditText etDepartmentName = (LineEditText) mOperateDepartmentDialog.findViewById(R.id.et_department_name);
        if (!TextUtils.isEmpty(departmentName)){
            etDepartmentName.setText(departmentName);
        }
        mOperateDepartmentDialog.findViewById(R.id.tv_sure_edit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String departmentName = etDepartmentName.getText().toString();
                listener.onDepartmentOperate(departmentName);
                mOperateDepartmentDialog.dismiss();
            }
        });
        mOperateDepartmentDialog.show();
    }

    public static void showDeleteDepartmentDialog(final Context context, final OnDepartmentDeleteListener listener) {
        mDeleteDepartmentDialog = new Dialog(context, R.style.DialogCommonStyle);
        mDeleteDepartmentDialog.setContentView(R.layout.dialog_delete_department);
        mDeleteDepartmentDialog.findViewById(R.id.iv_close_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDeleteDepartmentDialog.dismiss();
            }
        });
        mDeleteDepartmentDialog.findViewById(R.id.tv_quit_edit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDeleteDepartmentDialog.dismiss();
            }
        });

        mDeleteDepartmentDialog.findViewById(R.id.tv_sure_edit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onDepartmentDelete();
                mDeleteDepartmentDialog.dismiss();
            }
        });
        mDeleteDepartmentDialog.show();
    }

    public interface OnDepartmentOperateListener {
        void onDepartmentOperate(String departmentName);
    }

    public interface OnDepartmentDeleteListener{
        void onDepartmentDelete();
    }
}
