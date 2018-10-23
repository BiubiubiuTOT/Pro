package com.ljkj.qxn.wisdomsitepro.ui.contacts;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.text.TextUtils;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.Utils.PickerDialogHelper;
import com.ljkj.qxn.wisdomsitepro.Utils.widget.InputItemView;
import com.ljkj.qxn.wisdomsitepro.Utils.widget.ItemView;
import com.ljkj.qxn.wisdomsitepro.contract.contacts.DepartmentDetailContract;
import com.ljkj.qxn.wisdomsitepro.contract.contacts.DepartmentListContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.LaborCompanyBean;
import com.ljkj.qxn.wisdomsitepro.data.entity.RoleBean;
import com.ljkj.qxn.wisdomsitepro.data.entity.contract.DepartmentListInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.contract.ProjectDeptInfo;
import com.ljkj.qxn.wisdomsitepro.data.event.RefreshEvent;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.model.ContactsModel;
import com.ljkj.qxn.wisdomsitepro.presenter.contacts.DepartmentDetailPresenter;
import com.ljkj.qxn.wisdomsitepro.presenter.contacts.DepartmentListPresenter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseActivity;

public class DeptAppendActivity extends BaseActivity implements DepartmentListContract.View, DepartmentDetailContract.View {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.radioGroup)
    public RadioGroup radioGroup;
    private String orgType;
    @BindView(R.id.item_company)
    ItemView item_company;
    @BindView(R.id.item_name)
    InputItemView item_name;

    private DepartmentListPresenter mListPresenter;
    private DepartmentDetailPresenter departmentDetailPresenter;
    private List<String> picks;
    private Map<String, String> pickMap;
    private String labId;
    private String id;
    private String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dept_append);
    }

    @Override
    protected void initUI() {
        id = getIntent().getStringExtra("id");
        name = getIntent().getStringExtra("name");
        if (id != null) {
            tvTitle.setText("修改部门");
            item_name.setContent(name);
        } else {
            tvTitle.setText("添加部门");
        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

                if (checkedId == R.id.rb_dept) {
                    item_company.setVisibility(View.GONE);
                    orgType = "194";//部门
                    labId = ContactsFragment.companyId;
                } else if (checkedId == R.id.rb_banzu) {
                    orgType = "195";//班组
                    item_company.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    protected void initData() {
        mListPresenter = new DepartmentListPresenter(this, ContactsModel.newInstance());
        addPresenter(mListPresenter);
        mListPresenter.getLaborCompanyList(UserManager.getInstance().getProjectId());

        departmentDetailPresenter = new DepartmentDetailPresenter(this, ContactsModel.newInstance());
        addPresenter(departmentDetailPresenter);
    }

    @OnClick({R.id.tv_back, R.id.btn_submit, R.id.item_company})
    public void clickView(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.btn_submit:
                submit();
                break;
            case R.id.item_company:
                showLaborWindow();
                break;
            default:
                break;
        }
    }

    private void submit() {
        name = item_name.getContent().trim();
        if (TextUtils.isEmpty(name)) {
            showError("部门/班组名称不能为空");
            return;
        }
        if (TextUtils.isEmpty(orgType)) {
            showError("请选择部门/班组属性");
            return;
        }
        if (item_company.getVisibility() == View.VISIBLE && TextUtils.isEmpty(labId)) {
            showError("请选择劳务公司");
            return;
        }

        if (id != null) {//修改
            departmentDetailPresenter.updateDepartment(id, UserManager.getInstance().getProjectId(), name, orgType, labId);
        } else {//新增
            mListPresenter.addDepartment(UserManager.getInstance().getProjectId(), name, orgType, labId);
        }

    }

    private void showLaborWindow() {
        if (picks == null) {
            showError("劳务公司获取失败");
            return;
        }

        PickerDialogHelper.showPickerOption(this, picks, 0, true, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                String content = picks.get(options1);
                item_company.setContent(content);
                labId = pickMap.get(content);
            }
        });
    }

    @Override
    public void showProjectInfo(ProjectDeptInfo memberNumber) {

    }

    @Override
    public void showProjectDepartmentList(List<DepartmentListInfo> listInfo) {

    }

    @Override
    public void showBanZuDepartmentList(List<DepartmentListInfo> listInfo) {

    }

    @Override
    public void showUserDepartmentList(List<DepartmentListInfo> listInfo) {

    }

    @Override
    public void showLaborCompanyList(List<LaborCompanyBean> list) {
        picks = new ArrayList<>();
        pickMap = new HashMap<>();

        for (LaborCompanyBean laborCompanyBean : list) {
            String name = laborCompanyBean.getName();
            picks.add(name);

            pickMap.put(name, laborCompanyBean.getId());
        }
    }

    @Override
    public void showRoleList(RoleBean list) {

    }

    @Override
    public void dealAddDepartmentResult() {
        showError("添加部门/班组成功");

        EventBus.getDefault().post(new RefreshEvent(""));
        finish();
    }

    @Override
    public void dealUpdateDepartmentResult() {
        showError("修改部门/班组成功");

        EventBus.getDefault().post(new RefreshEvent(""));
        Intent intent = new Intent();
        intent.putExtra("name", name);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void dealDeleteDepartmentResult() {

    }

    @Override
    public void showDepartmentDetail(DepartmentListInfo data) {

    }
}
