package com.ljkj.qxn.wisdomsitepro.ui.safe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.entity.IExpandable;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.contract.safe.AuthorityContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.DeptTeamInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.SecurityEduTechPerson;
import com.ljkj.qxn.wisdomsitepro.data.entity.TeamLeader;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.model.AuthorityModel;
import com.ljkj.qxn.wisdomsitepro.presenter.common.AuthorityPresenter;
import com.ljkj.qxn.wisdomsitepro.ui.safe.adapter.SelectTeamAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseActivity;

/**
 * 类描述：安全教育-选择班组
 * 创建人：lxx
 * 创建时间：2018/9/21
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SelectTeamActivity extends BaseActivity implements AuthorityContract.View {

    @BindView(R.id.tv_title)
    TextView titleText;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private AuthorityPresenter authorityPresenter;
    private SelectTeamAdapter selectTeamAdapter;

    public static void startActivity(Activity activity, int requestCode) {
        Intent intent = new Intent(activity, SelectTeamActivity.class);
        activity.startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_team);
    }

    @Override
    protected void initUI() {
        titleText.setText("选择班组");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(selectTeamAdapter = new SelectTeamAdapter(null));
    }

    @Override
    protected void initData() {
        authorityPresenter = new AuthorityPresenter(this, AuthorityModel.newInstance());
        addPresenter(authorityPresenter);
        authorityPresenter.getDeptTeamList(UserManager.getInstance().getProjectId());
    }

    @OnClick({R.id.tv_back, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.tv_submit:
                ArrayList<MultiItemEntity> list = (ArrayList<MultiItemEntity>) selectTeamAdapter.getData();
                ArrayList<SecurityEduTechPerson> resultList = new ArrayList<>();
                for (MultiItemEntity entity : list) {
                    if (!(entity instanceof IExpandable)) {
                        continue;
                    }
                    SelectTeamAdapter.CompanyItem companyItem = (SelectTeamAdapter.CompanyItem) entity;
                    if (companyItem.getSubItems() != null) {
                        for (SelectTeamAdapter.TeamItem teamItem : companyItem.getSubItems()) {
                            if (teamItem.isSelect) {
                                SecurityEduTechPerson person = new SecurityEduTechPerson();
                                person.laborCom = companyItem.name;
                                person.teamName = teamItem.name;
                                person.teamLeaderId = teamItem.teamLeaderId;
                                person.teamPerson = teamItem.teamLeader;
                                person.teamId = teamItem.id;
                                person.laborId = companyItem.id;
                                resultList.add(person);
                            }
                        }
                    }

                }
                Intent intent = new Intent();
                intent.putParcelableArrayListExtra("data", resultList);
                setResult(RESULT_OK, intent);
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void showDeptTeamList(List<DeptTeamInfo> pageInfo) {
        ArrayList<MultiItemEntity> list = new ArrayList<>();
        for (DeptTeamInfo deptTeamInfo : pageInfo) {
            SelectTeamAdapter.CompanyItem companyItem = new SelectTeamAdapter.CompanyItem(deptTeamInfo.getName(), deptTeamInfo.getId());
            for (DeptTeamInfo.LmTeamListBean bean : deptTeamInfo.getLmTeamList()) {
                SelectTeamAdapter.TeamItem teamItem = new SelectTeamAdapter.TeamItem(bean.getName(), bean.getTeamLeader());
                teamItem.teamLeaderId = bean.getTeamLeaderId();
                teamItem.id = bean.getId();
                companyItem.addSubItem(teamItem);
            }
            list.add(companyItem);
        }
        selectTeamAdapter.setNewData(list);
    }

    @Override
    public void showTeamLeader(TeamLeader leader) {
    }

    private void test() {
        ArrayList<MultiItemEntity> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            SelectTeamAdapter.CompanyItem companyItem = new SelectTeamAdapter.CompanyItem("公司" + i, "");
            for (int j = 0; j < 6; j++) {
                SelectTeamAdapter.TeamItem teamItem = new SelectTeamAdapter.TeamItem("班组" + j, "sdf");
                companyItem.addSubItem(teamItem);
            }
            list.add(companyItem);
        }
        selectTeamAdapter.setNewData(list);
    }


}
