package com.ljkj.qxn.wisdomsitepro;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.ljkj.qxn.wisdomsitepro.Utils.OCRHelper;
import com.ljkj.qxn.wisdomsitepro.manager.MiPushManager;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.ui.application.ApplicationFragment;
import com.ljkj.qxn.wisdomsitepro.ui.contacts.ContactsFragment;
import com.ljkj.qxn.wisdomsitepro.ui.message.MessageFragment;
import com.ljkj.qxn.wisdomsitepro.ui.personal.PersonalFragment;
import com.ljkj.qxn.wisdomsitepro.ui.personal.ProjectListActivity;
import com.ljkj.qxn.wisdomsitepro.ui.project.ProjectFragmentV3;

import butterknife.BindView;
import butterknife.OnCheckedChanged;
import cdsp.android.ui.base.BaseActivity;

/**
 * 类描述：
 * 创建人：liufei
 * 创建时间：2018/1/31 15:26
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class MainActivity extends BaseActivity implements MessageFragment.onMsgStatusChangedListener {
    private static boolean isMainActivityOn = false;

    @BindView(R.id.rb_message)
    RadioButton messageRB;

    @BindView(R.id.rb_contacts)
    RadioButton contactsRB;

    @BindView(R.id.rb_project)
    RadioButton rbProject;

    @BindView(R.id.rb_application)
    RadioButton rbApplication;

    @BindView(R.id.rb_mine)
    RadioButton rbMine;

    @BindView(R.id.tv_message_dot)
    TextView messageDotText;

    private CompoundButton selectView;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isMainActivityOn = true;
        setContentView(R.layout.activity_main);
        OCRHelper.initAccessToken();
    }

    @Override
    protected void initUI() {
        fragmentManager = getSupportFragmentManager();
        contactsRB.setTag(ContactsFragment.TAG);
        messageRB.setTag(MessageFragment.TAG);
        //TODO
//        rbProject.setTag(ProjectFragment2.TAG);
        rbProject.setTag(ProjectFragmentV3.TAG);
        rbApplication.setTag(ApplicationFragment.TAG);
        rbMine.setTag(PersonalFragment.TAG);
        rbProject.setChecked(true);
    }

    @Override
    protected void initData() {
        String pushContent = getIntent().getStringExtra(SplashActivity.KEY_PUSH_CONTENT);
        if (!TextUtils.isEmpty(pushContent)) {
            MiPushManager.getInstance().handle(this, pushContent, false);
        }
        if (TextUtils.isEmpty(UserManager.getInstance().getProjectId())) {
            ProjectListActivity.startActivity(this, ProjectListActivity.PERSONAL_FRAGMENT_ENTER_FLAG);
        }
    }

    private void showFragment(CompoundButton curView) {
        if (selectView != null) {
            selectView.setChecked(false);
        }
        selectView = curView;
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        String tag = (String) curView.getTag();
        if (fragmentManager.findFragmentByTag(tag) == null) {
            Fragment fragment = Fragment.instantiate(this, tag);
            fragmentTransaction.add(R.id.fl_content, fragment, tag);
            fragmentTransaction.commitAllowingStateLoss();
        } else {
            boolean isHide = fragmentManager.findFragmentByTag(tag).isHidden();
            if (isHide) {
                fragmentTransaction.show(fragmentManager.findFragmentByTag(tag));
                fragmentTransaction.commitAllowingStateLoss();
                fragmentManager.findFragmentByTag(tag).onResume(); //切换Fragment，实时刷新数据
            }
        }
    }

    private void hideFragment(CompoundButton lastView) {
        if (lastView != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            String tag = (String) lastView.getTag();
            if (fragmentManager.findFragmentByTag(tag) != null) {
                boolean isHide = fragmentManager.findFragmentByTag(tag).isHidden();
                if (!isHide) {
                    fragmentTransaction.hide(fragmentManager.findFragmentByTag(tag));
                    fragmentTransaction.commitAllowingStateLoss();
                }
            }
        }
    }

    long touchTime = 0;

    @Override
    public void onBackPressed() {
        long currentTime = System.currentTimeMillis();
        if ((currentTime - touchTime) >= 2000) {
            Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
            touchTime = currentTime;
        } else {
            finish();
            System.exit(0);
        }
    }

    @OnCheckedChanged({R.id.rb_contacts, R.id.rb_message, R.id.rb_project, R.id.rb_application, R.id.rb_mine})
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {

            //for v2.0
            if (buttonView.getId() == R.id.rb_project) {
                setTranslucentStatusForProject(R.color.project_fragment_status_bar_color);
            } else {
                setTranslucentStatus(R.color.color_white);
            }
            //for v2.0

            showFragment(buttonView);
        } else {
            hideFragment(buttonView);
        }
    }


    public static boolean isMainActivityOn() {
        return isMainActivityOn;
    }

    @Override
    public void finish() {
        super.finish();
        isMainActivityOn = false;
    }

    @Override
    protected void onDestroy() {
        OCRHelper.releaseOCR();
        super.onDestroy();
    }

    @Override
    public void onMsgStatusChanged(boolean hasUnreadMsg) {
        if (fragmentManager.findFragmentByTag(MessageFragment.TAG) == null) {
            return;
        }
        MessageFragment fragment = (MessageFragment) fragmentManager.findFragmentByTag(MessageFragment.TAG);
        fragment.updateRedDot(hasUnreadMsg);
        messageDotText.setVisibility(hasUnreadMsg ? View.VISIBLE : View.GONE);
    }
}
