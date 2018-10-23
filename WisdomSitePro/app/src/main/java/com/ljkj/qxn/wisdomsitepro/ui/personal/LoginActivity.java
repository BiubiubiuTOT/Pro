package com.ljkj.qxn.wisdomsitepro.ui.personal;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ljkj.qxn.wisdomsitepro.BuildConfig;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.WApplication;
import com.ljkj.qxn.wisdomsitepro.contract.personal.UserContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.AuthorityInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.LoginInfo;
import com.ljkj.qxn.wisdomsitepro.manager.AuthorityManager;
import com.ljkj.qxn.wisdomsitepro.manager.MiPushManager;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.model.UserModel;
import com.ljkj.qxn.wisdomsitepro.presenter.personal.UserPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseActivity;
import cdsp.android.util.MD5Utils;
import cdsp.android.util.ToastUtils;


/**
 * 类描述：登录界面
 * 创建人：mhl
 * 创建时间：2018/2/26 15:54
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class LoginActivity extends BaseActivity implements UserContract.View {

    @BindView(R.id.et_name)
    EditText etName;

    @BindView(R.id.et_password)
    EditText etPassword;

    @BindView(R.id.btn_debug)
    Button debugBtn;

    private UserPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void setTranslucentStatus(int resColor) {
    }

    @Override
    protected void initUI() {
        debugBtn.setVisibility(BuildConfig.DEBUG ? View.VISIBLE : View.GONE);
        if (!BuildConfig.DEBUG) {
            etName.setText("");
            etPassword.setText("");
        }

        test();
    }

    void test() {
        if (BuildConfig.DEBUG && "1a1a2f68".equals(Build.SERIAL)) {
            etName.setText("18850502561");
            etPassword.setText("123456");
        }
    }

    @Override
    protected void initData() {
        presenter = new UserPresenter(this, UserModel.newInstance());
        addPresenter(presenter);
    }

    @OnClick({R.id.img_login, R.id.btn_debug})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_login:
                login();
                break;
            case R.id.btn_debug:
                DebugActivity.startActivity(this);
                break;
            default:
                break;
        }
    }

    private void login() {
        String name = etName.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            showError("请输入用户名");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            showError("请输入密码");
            return;
        }
        presenter.login(name, MD5Utils.getMD5Str(password).toLowerCase());
    }

    @Override
    public void showLoginInfo(LoginInfo loginInfo) {
        presenter.getAuthority(loginInfo.user.roleId, loginInfo.user.userId);
        UserManager.getInstance().saveLoginInfo(loginInfo);
        MiPushManager.getInstance().setAliasAndUserAccount();
        if (MiPushManager.getInstance().isUnregister()) {
            MiPushManager.getInstance().registerPush(WApplication.getApplication());
        }
//        IMManager.getInstance().connect("l/4oKUR1H6FoLJL8pQgvpyMMNoPGp+ybiP/3GGQWVqEUlaVfrsZXd3JZ8fhr215ykiFQ8m8jHRUV5JZ1O8YX7Sls6OhwRR+V", null);
    }

    @Override
    public void showAuthority(List<AuthorityInfo> list) {
        ToastUtils.showShort("登录成功");
        AuthorityManager.getInstance().saveAuthority(list);
        ProjectListActivity.startActivity(this, ProjectListActivity.LOGIN_ACTIVITY_ENTER_FLAG);
        finish();
    }

    @Override
    public void showUpdatePwdInfo() {
    }
}
