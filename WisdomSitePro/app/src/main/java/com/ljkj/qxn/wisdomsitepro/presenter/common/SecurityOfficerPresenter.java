package com.ljkj.qxn.wisdomsitepro.presenter.common;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.common.SecurityOfficerListContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.SecurityOfficerInfo;
import com.ljkj.qxn.wisdomsitepro.model.CommonModel;

import java.util.List;

import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;

/**
 * 类描述：获取项目专职安全员列表
 * 创建人：lxx
 * 创建时间：2018/9/25
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SecurityOfficerPresenter extends SecurityOfficerListContract.Presenter {
    public SecurityOfficerPresenter(SecurityOfficerListContract.View view, CommonModel model) {
        super(view, model);
    }

    @Override
    public void getSecurityOfficer(String projId) {
        model.getSecurityOfficer(projId, new JsonCallback<ResponseData<List<SecurityOfficerInfo>>>(new TypeToken<ResponseData<List<SecurityOfficerInfo>>>() {
        }) {

            @Override
            protected void onError(int errcode, String errmsg) {
                if (isAttach) {
                    view.showError(errmsg);
                }
            }

            @Override
            public void onSuccess(ResponseData<List<SecurityOfficerInfo>> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showSecurityOfficer(data.getResult());
                    } else {
                        view.showError(data.getErrmsg());
                    }
                }
            }
        });
    }


}
