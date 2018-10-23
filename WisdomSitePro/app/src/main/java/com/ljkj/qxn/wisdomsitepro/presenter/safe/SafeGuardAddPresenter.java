package com.ljkj.qxn.wisdomsitepro.presenter.safe;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.safe.SafeGuardAddContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.SafeGuardEditFloorInfo;
import com.ljkj.qxn.wisdomsitepro.model.SafeModel;

import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;

/**
 * 类描述：安全防护新增楼栋
 * 创建人：rjf
 * 创建时间：2018/3/13 16:53.
 * 修改人：
 * 修改时间
 * 修改备注
 */

public class SafeGuardAddPresenter extends SafeGuardAddContract.Presenter {
    public SafeGuardAddPresenter(SafeGuardAddContract.View view, SafeModel model) {
        super(view, model);
    }

    @Override
    public void addBuilding(String name, int lous, String proId) {
        model.addSafeGuardBuild(name, lous, proId, new JsonCallback<ResponseData>(new TypeToken<ResponseData>() {
        }) {
            @Override
            public void onSuccess(ResponseData data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showAddResult();
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
            public void onFinish() {
                super.onFinish();
                if (isAttach) {
                    view.hideProgress();
                }
            }
        });
    }

    @Override
    public void addFloor(String louOrder, int type0, int type1, int type3, String proId) {
        model.addFloor(louOrder, type0, type1, type3, proId, new JsonCallback<ResponseData>(new TypeToken<ResponseData>() {
        }) {

            @Override
            public void onStart() {
                super.onStart();
                if (isAttach){
                    view.showProgress("提交上传中...");
                }
            }

            @Override
            public void onSuccess(ResponseData data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showAddResult();
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
            public void onFinish() {
                super.onFinish();
                if (isAttach) {
                    view.hideProgress();
                }
            }
        });
    }

    @Override
    public void editFloor(String aqfhsm, String cengId, String proId) {
        model.editSafeGuardFloor(aqfhsm, cengId, proId, new JsonCallback<ResponseData<SafeGuardEditFloorInfo>>(new TypeToken<ResponseData<SafeGuardEditFloorInfo>>() {
        }) {
            @Override
            public void onSuccess(ResponseData<SafeGuardEditFloorInfo> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showAddResult(data.getResult());
//                        view.showError("修改成功");
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
            public void onFinish() {
                super.onFinish();
                if (isAttach) {
                    view.hideProgress();
                }
            }
        });
    }
}
