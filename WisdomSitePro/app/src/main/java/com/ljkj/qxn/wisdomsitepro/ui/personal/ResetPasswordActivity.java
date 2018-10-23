package com.ljkj.qxn.wisdomsitepro.ui.personal;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.contract.personal.UserContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.AuthorityInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.LoginInfo;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.model.UserModel;
import com.ljkj.qxn.wisdomsitepro.presenter.personal.UserPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.base.AppManager;
import cdsp.android.ui.base.BaseActivity;
import cdsp.android.util.SpUtils;

/**
 * 类描述：修改密码
 * 创建人：liufei
 * 创建时间：2018/2/2 10:17
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class ResetPasswordActivity extends BaseActivity implements UserContract.View {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.edit_password_old)
    EditText editPasswordOld;
    @BindView(R.id.edit_password_new)
    EditText editPasswordNew;
    @BindView(R.id.edit_password_new_comfrim)
    EditText editPasswordNewComfrim;

    private UserPresenter userPresenter;
    private String oldPwd;
    private String newPwd;
    private String newPwdComfrim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
    }

    @Override
    protected void initUI() {
        tvTitle.setText("修改密码");
        tvRight.setText("提交");
    }

    @Override
    protected void initData() {
        userPresenter = new UserPresenter(this, UserModel.newInstance());
        addPresenter(userPresenter);
    }

    @Override
    public void showLoginInfo(LoginInfo data) {
    }

    @Override
    public void showUpdatePwdInfo() {
        showError("修改成功");
        SpUtils.putUserToken("");
        AppManager.getAppManager().finishAllActivity();
        startActivity(new Intent(this, LoginActivity.class));
    }

    @Override
    public void showAuthority(List<AuthorityInfo> list) {

    }

    private void postData() {
        oldPwd = editPasswordOld.getText().toString().trim();
        if (TextUtils.isEmpty(oldPwd)) {
            showError("请输入原密码");
            return;
        }
        newPwd = editPasswordNew.getText().toString().trim();
        if (TextUtils.isEmpty(newPwd)) {
            showError("请输入新密码");
            return;
        }
        newPwdComfrim = editPasswordNewComfrim.getText().toString().trim();
        if (TextUtils.isEmpty(newPwdComfrim)) {
            showError("请再次输入新密码");
            return;
        }
        if (!TextUtils.equals(newPwd, newPwdComfrim)) {
            showError("两次输入的新密码不一致");
            return;
        }
        userPresenter.updatePwd(UserManager.getInstance().getUserId(), UserManager.getInstance().getProjectId(), newPwdComfrim, oldPwd);
    }

    @OnClick({R.id.tv_back, R.id.tv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.tv_right:
                postData();
                break;
            default:
                break;
        }
    }

}
