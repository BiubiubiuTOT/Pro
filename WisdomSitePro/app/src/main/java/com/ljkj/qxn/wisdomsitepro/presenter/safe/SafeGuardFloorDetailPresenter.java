package com.ljkj.qxn.wisdomsitepro.presenter.safe;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.safe.SafeGuardFloorDetailContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.SafeGuardFloorInfo;
import com.ljkj.qxn.wisdomsitepro.model.SafeModel;

import java.util.List;

import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;

/**
 * 类描述：安全防护楼层详情
 * 创建人：rjf
 * 创建时间：2018/3/13 17:22.
 * 修改人：
 * 修改时间
 * 修改备注
 */

public class SafeGuardFloorDetailPresenter extends SafeGuardFloorDetailContract.Presenter {
    public SafeGuardFloorDetailPresenter(SafeGuardFloorDetailContract.View view, SafeModel model) {
        super(view, model);
    }

    @Override
    public void getSafeGuardFloorDetail(String cengId, String proId) {
        model.getSafeGuardFloorDetail(cengId, proId, new JsonCallback<ResponseData<List<SafeGuardFloorInfo>>>(new TypeToken<ResponseData<List<SafeGuardFloorInfo>>>(){}) {


            @Override
            public void onSuccess(ResponseData<List<SafeGuardFloorInfo>> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showFloorDetail(data.getResult());
                    } else {
                        view.showError(data.getErrmsg());
                    }
                }
            }

            @Override
            public void onFinish() {
                super.onFinish();
                if (isAttach) {
                    view.hideProgress();
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
}
