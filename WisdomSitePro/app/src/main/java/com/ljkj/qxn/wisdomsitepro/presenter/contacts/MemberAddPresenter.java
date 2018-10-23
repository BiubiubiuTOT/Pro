
package com.ljkj.qxn.wisdomsitepro.presenter.contacts;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.contacts.MemberAddContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.PageInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.contract.DutyListInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.contract.QueryMemberInfo;
import com.ljkj.qxn.wisdomsitepro.model.ContactsModel;

import java.util.HashMap;

import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;

/**
 * 类描述：添加成员
 * 创建人：rjf
 * 创建时间：2018/7/3 11:44.
 * 修改人：
 * 修改时间
 * 修改备注
 */

public class MemberAddPresenter extends MemberAddContract.Presenter {

    public MemberAddPresenter(MemberAddContract.View view, ContactsModel model) {
        super(view, model);
    }

    @Override
    public void addMember(HashMap info) {
        model.addMember(info, new JsonCallback<ResponseData>(new TypeToken<ResponseData>() {
        }) {
            @Override
            public void onSuccess(ResponseData data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.dealAddMemberResult();
                    } else {
                        view.showError(data.getErrmsg());
                    }
                }
            }

            @Override
            protected void onError(int errcode, String errmsg) {
                if (isAttach) {
                    view.showError(errmsg);
                }
            }

            @Override
            public void onStart() {
                super.onStart();
                if (isAttach) {
                    view.showProgress("正在添加中...");
                }
            }

            @Override
            public void onFinish() {
                super.onFinish();
                if (isAttach) {
                    view.hideProgress();
                }
            }
        });
    }

    @Override
    public void getDutyList(String proId) {
        model.getDutyList(proId, new JsonCallback<ResponseData<PageInfo<DutyListInfo>>>(new TypeToken<ResponseData<PageInfo<DutyListInfo>>>() {
        }) {
            @Override
            public void onSuccess(ResponseData<PageInfo<DutyListInfo>> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showDutyList(data.getResult());
                    } else {
                        view.showError(data.getErrmsg());
                    }
                }
            }

            @Override
            protected void onError(int errcode, String errmsg) {
                if (isAttach) {
                    view.showError(errmsg);
                }
            }
        });
    }

    @Override
    public void deleteMember(String id) {
        model.deleteMember(id, new JsonCallback<ResponseData<String>>(new TypeToken<ResponseData<String>>() {
        }) {
            @Override
            public void onSuccess(ResponseData<String> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.dealDeleteResult();
                    } else {
                        view.showError(data.getErrmsg());
                    }
                }
            }

            @Override
            protected void onError(int errcode, String errmsg) {
                if (isAttach)
                    view.showError(errmsg);
            }
        });
    }

    @Override
    public void searachMember(String id, String proId) {
        model.searchMember(id, proId, new JsonCallback<ResponseData<PageInfo<QueryMemberInfo>>>(new TypeToken<ResponseData<PageInfo<QueryMemberInfo>>>() {
        }) {
            @Override
            public void onSuccess(ResponseData<PageInfo<QueryMemberInfo>> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showSearchMembers(data.getResult());
                    } else {
                        String errmsg = data.getErrmsg();
                        view.showError(errmsg);
                    }
                }
            }

            @Override
            protected void onError(int errcode, String errmsg) {
                view.showError(errmsg);
            }
        });
    }
}

