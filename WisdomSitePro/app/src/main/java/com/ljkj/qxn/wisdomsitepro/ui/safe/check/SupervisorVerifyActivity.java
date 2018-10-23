package com.ljkj.qxn.wisdomsitepro.ui.safe.check;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.contract.safe.SupervisorVerifyContract;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.model.SafeModel;
import com.ljkj.qxn.wisdomsitepro.presenter.safe.SupervisorVerifyPresenter;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseActivity;
import cdsp.android.util.ToastUtils;

/**
 * 类描述：监理审核
 * 创建人：lxx
 * 创建时间：2018/9/13
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SupervisorVerifyActivity extends BaseActivity implements SupervisorVerifyContract.View {

    @BindView(R.id.tv_title)
    TextView titleText;

    @BindView(R.id.tv_right)
    TextView rightText;

    @BindView(R.id.et_advice)
    EditText adviceEdit;

    private boolean isPass;
    private String reformId;
    private int checkType;

    private SupervisorVerifyPresenter presenter;

    /**
     * @param context   context
     * @param isPass    审核是否通过
     * @param reformId  整改回复id
     * @param checkType 检查类型1：安全，2：质量
     */
    public static void startActivity(Context context, boolean isPass, String reformId, int checkType) {
        Intent intent = new Intent(context, SupervisorVerifyActivity.class);
        intent.putExtra("isPass", isPass);
        intent.putExtra("reformId", reformId);
        intent.putExtra("checkType", checkType);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supervisor_verify);
    }

    @Override
    protected void initUI() {
        isPass = getIntent().getBooleanExtra("isPass", false);
        reformId = getIntent().getStringExtra("reformId");
        checkType = getIntent().getIntExtra("checkType", CheckListActivity.TYPE_SAFE_CHECK);
        titleText.setText(isPass ? "同意意见" : "驳回意见");
        rightText.setText("提交");
    }

    @Override
    protected void initData() {
        presenter = new SupervisorVerifyPresenter(this, SafeModel.newInstance());
        addPresenter(presenter);
    }

    @OnClick({R.id.tv_back, R.id.tv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.tv_right:
                String content = adviceEdit.getText().toString().trim();
                if (TextUtils.isEmpty(content)) {
                    ToastUtils.showShort("请输入" + titleText.getText());
                } else {
                    presenter.supervisorVerify(checkType, isPass ? 1 : 0, content, UserManager.getInstance().getUserName(), reformId);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void showVerifySuccess() {
        ToastUtils.showShort("提交成功！");
        Intent intent = new Intent(this, SupervisorCheckListActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }
}
