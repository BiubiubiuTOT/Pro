package com.ljkj.qxn.wisdomsitepro.ui.safe;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.contract.safe.SafeSystemPeopleContract;
import com.ljkj.qxn.wisdomsitepro.data.BaseEntity;
import com.ljkj.qxn.wisdomsitepro.data.FileEntity;
import com.ljkj.qxn.wisdomsitepro.data.ManagerGroupEntity;
import com.ljkj.qxn.wisdomsitepro.data.ManagerPeopleRecycleEntity;
import com.ljkj.qxn.wisdomsitepro.data.entity.ManagerPeopleInfo;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.model.CommonModel;
import com.ljkj.qxn.wisdomsitepro.model.SafeModel;
import com.ljkj.qxn.wisdomsitepro.presenter.safe.SafeSystemPeoplePresenter;
import com.ljkj.qxn.wisdomsitepro.ui.project.adapter.ManagerPeopleAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;
import cdsp.android.ui.base.BaseFragment;

/**
 * 类描述：安全管理人员体系
 * 创建人：liufei
 * 创建时间：2018/3/10 16:48
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SafeSystemPeopleFragment extends BaseFragment implements SafeSystemPeopleContract.View {

    public static final String TAG = SafeSystemPeopleFragment.class.getName();

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    @BindView(R.id.ll_no_data)
    ViewGroup llNoData;

    private ManagerPeopleAdapter adapter;
    private ManagerPeopleInfo data;

    private SafeSystemPeoplePresenter safeSystemPeoplePresenter;
    private List<ManagerPeopleInfo.ManagerPersonBean> managerList;
    private List<ManagerPeopleInfo.ManagerPersonBean> deputyList;
    private List<ManagerPeopleInfo.ManageGroupBean> teamList;
    private List<ManagerPeopleInfo.ManageGroupBean> deptList;
    private boolean isDestroy;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_recycler_view_list, container, false);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void initUI() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                safeSystemPeoplePresenter.getSafeSystemPeople(UserManager.getInstance().getProjectId());

            }
        });
    }

    @Override
    protected void initData() {
        safeSystemPeoplePresenter = new SafeSystemPeoplePresenter(this, SafeModel.newInstance());
        addPresenter(safeSystemPeoplePresenter);
        refreshLayout.autoRefresh();
    }

    @Override
    public void showSafeSystemPeople(ManagerPeopleInfo data) {
        if (data != null) {
            this.data = data;
            List<BaseEntity> baseEntities = new ArrayList<>();
            managerList = new ArrayList<>();
            deputyList = new ArrayList<>();
            deptList = data.getManageDeptList();
            teamList = data.getManagerTeamList();

            List<ManagerPeopleInfo.ManagerPersonBean> managerDuptyList = data.getManagerDuptyList();

            for (ManagerPeopleInfo.ManagerPersonBean managerPersonBean : managerDuptyList) {
                if (managerPersonBean.getType() == 1) {
                    managerList.add(managerPersonBean);
                } else deputyList.add(managerPersonBean);
            }

            baseEntities.add(new ManagerPeopleRecycleEntity("项目经理", managerList));
            baseEntities.add(new ManagerPeopleRecycleEntity("项目副职", deputyList));
            baseEntities.add(new ManagerGroupEntity("部门", deptList));
            baseEntities.add(new ManagerGroupEntity("班组", teamList));
            recyclerView.setAdapter(adapter = new ManagerPeopleAdapter(getContext(), baseEntities));
        }

        queryManagerFileId();

        queryDeputyFileId();

        queryDeptFileId(deptList);
        queryDeptFileId(teamList);
    }

    private void queryDeptFileId(List<ManagerPeopleInfo.ManageGroupBean> list) {
        if (list == null) {
            return;
        }

        for (final ManagerPeopleInfo.ManageGroupBean managerPersonBean : list) {
            List<ManagerPeopleInfo.ManagerDeptPersonBean> managerList = managerPersonBean.getManagerList();
            if (managerList == null) return;

            for (final ManagerPeopleInfo.ManagerDeptPersonBean managerDeptPersonBean : managerList) {
                CommonModel.newInstance().queryFile(managerDeptPersonBean.getId(), new JsonCallback<ResponseData<List<FileEntity>>>(new TypeToken<ResponseData<List<FileEntity>>>() {
                }) {
                    @Override
                    protected void onError(int errcode, String errmsg) {
                    }

                    @Override
                    public void onSuccess(ResponseData<List<FileEntity>> data) {
                        if (isDestroy) {
                            return;
                        }
                        List<FileEntity> fileEntities = data.getResult();
                        if (fileEntities != null && !fileEntities.isEmpty()) {
                            managerDeptPersonBean.setPhotoId(fileEntities.get(0).fileId);

                            adapter.notifyDataSetChanged();
                        }
                    }
                });
            }

        }
    }

    private void queryDeputyFileId() {
        if (deputyList == null) {
            return;
        }

        for (final ManagerPeopleInfo.ManagerPersonBean managerPersonBean : deputyList) {
            CommonModel.newInstance().queryFile(managerPersonBean.getId(), new JsonCallback<ResponseData<List<FileEntity>>>(new TypeToken<ResponseData<List<FileEntity>>>() {
            }) {
                @Override
                protected void onError(int errcode, String errmsg) {
                }

                @Override
                public void onSuccess(ResponseData<List<FileEntity>> data) {
                    if (isDestroy) {
                        return;
                    }

                    List<FileEntity> fileEntities = data.getResult();
                    if (fileEntities != null && !fileEntities.isEmpty()) {
                        managerPersonBean.setPhotoId(fileEntities.get(0).fileId);

                        adapter.notifyDataSetChanged();
                    }
                }
            });
        }
    }

    private void queryManagerFileId() {
        if (managerList == null) {
            return;
        }

        for (final ManagerPeopleInfo.ManagerPersonBean managerPersonBean : managerList) {
            CommonModel.newInstance().queryFile(managerPersonBean.getId(), new JsonCallback<ResponseData<List<FileEntity>>>(new TypeToken<ResponseData<List<FileEntity>>>() {
            }) {
                @Override
                protected void onError(int errcode, String errmsg) {
                }

                @Override
                public void onSuccess(ResponseData<List<FileEntity>> data) {
                    if (isDestroy) {
                        return;
                    }
                    List<FileEntity> fileEntities = data.getResult();
                    for (FileEntity fileEntity : fileEntities) {
                        if (fileEntity.getType().equals("proj_manager_img")) {
                            managerPersonBean.setPhotoId(fileEntity.fileId);
                            break;
                        }
                    }

                    adapter.notifyDataSetChanged();
                }
            });
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isDestroy = true;
    }

    @Override
    public void hideProgress() {
        super.hideProgress();
        llNoData.setVisibility(data != null ? View.GONE : View.VISIBLE);
//        llNoData.setVisibility(data.getXmjl() != null || data.getTwoPersons().size() > 0 || data.getThreePersons().size() > 0 ? View.GONE : View.VISIBLE);
        refreshLayout.finishRefresh();
    }

}
