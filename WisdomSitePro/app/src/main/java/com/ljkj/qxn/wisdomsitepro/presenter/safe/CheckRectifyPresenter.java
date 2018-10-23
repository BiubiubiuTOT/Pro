package com.ljkj.qxn.wisdomsitepro.presenter.safe;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.safe.CheckRectifyContract;
import com.ljkj.qxn.wisdomsitepro.data.FileEntity;
import com.ljkj.qxn.wisdomsitepro.data.entity.NullEntity;
import com.ljkj.qxn.wisdomsitepro.model.SafeModel;

import java.util.List;

import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;

/**
 * 类描述：检查立即整改
 * 创建人：lxx
 * 创建时间：2018/9/7
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class CheckRectifyPresenter extends CheckRectifyContract.Presenter {

    public CheckRectifyPresenter(CheckRectifyContract.View view, SafeModel model) {
        super(view, model);
    }

    @Override
    public void safeCheckRectify(String id, String rectifyInfo, String isPlan, String isMeasure, String money,
                                 String reformer, String proId, String proCode, List<FileEntity> file) {
        model.safeCheckRectify(id, rectifyInfo, isPlan, isMeasure, money, reformer, proId, proCode, file, new JsonCallback<ResponseData<NullEntity>>(new TypeToken<ResponseData<NullEntity>>() {
        }) {
            @Override
            public void onStart() {
                super.onStart();
                if (isAttach) {
                    view.showProgress("正在提交中...");
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

            @Override
            public void onSuccess(ResponseData<NullEntity> data) {
                if (isAttach) {
                    if (data.getErrcode() == 200) {
                        view.showSafeRectifySuccess();
                    } else {
                        view.showError(data.getErrmsg());
                    }
                }
            }

        });
    }

    @Override
    public void qualityCheckRectify(String id, String rectifyInfo, String isPlan, String isMeasure, String money, String reformer, String proId, String proCode, List<FileEntity> file) {
        model.qualityCheckRectify(id, rectifyInfo, isPlan, isMeasure, money, reformer, proId, proCode, file, new JsonCallback<ResponseData<NullEntity>>(new TypeToken<ResponseData<NullEntity>>() {
        }) {
            @Override
            public void onStart() {
                super.onStart();
                if (isAttach) {
                    view.showProgress("正在提交中...");
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

            @Override
            public void onSuccess(ResponseData<NullEntity> data) {
                if (isAttach) {
                    if (data.getErrcode() == 200) {
                        view.showQualityRectifySuccess();
                    } else {
                        view.showError(data.getErrmsg());
                    }
                }
            }

        });
    }
}
