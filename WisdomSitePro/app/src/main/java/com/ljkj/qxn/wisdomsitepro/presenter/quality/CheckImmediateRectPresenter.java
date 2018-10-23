package com.ljkj.qxn.wisdomsitepro.presenter.quality;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.quality.CheckImmediateRectificationContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.QualityCheckResultDetail;
import com.ljkj.qxn.wisdomsitepro.model.QualityModel;

import java.io.File;
import java.util.List;

import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;

/**
 * 质量检查 -立即整改
 * 创建人：lxx
 * 创建时间：2018/3/15 17:25
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class CheckImmediateRectPresenter extends CheckImmediateRectificationContract.Presenter {

    public CheckImmediateRectPresenter(CheckImmediateRectificationContract.View view, QualityModel model) {
        super(view, model);
    }

    @Override
    public void dealWithSave(QualityCheckResultDetail.Base base, String proId) {
        model.dealWithSave(base, proId, new JsonCallback<ResponseData<QualityCheckResultDetail.Base>>(new TypeToken<ResponseData<QualityCheckResultDetail.Base>>() {
        }) {
            @Override
            public void onStart() {
                super.onStart();
                if (isAttach) {
                    view.showProgress("数据提交中...");
                }
            }

            @Override
            protected void onError(int errcode, String errmsg) {
                if (isAttach) {
                    view.showError(errmsg);
                }
            }

            @Override
            public void onSuccess(ResponseData<QualityCheckResultDetail.Base> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.rectifySuccess(data.getResult().getId());
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
        });
    }

    @Override
    public void dealWithAndFileSave(QualityCheckResultDetail.Base base, String proId, List<File> imageList, List<File> reportList, List<File> planList, List<File> measureList, List<File> moneyList) {
        model.dealWithAndFileSave(base, proId, imageList, reportList, planList, measureList, moneyList, new JsonCallback<ResponseData<QualityCheckResultDetail.Base>>(new TypeToken<ResponseData<QualityCheckResultDetail.Base>>() {
        }) {
            @Override
            public void onStart() {
                super.onStart();
                if (isAttach) {
                    view.showProgress("数据提交中...");
                }
            }

            @Override
            protected void onError(int errcode, String errmsg) {
                if (isAttach) {
                    view.showError(errmsg);
                }
            }

            @Override
            public void onSuccess(ResponseData<QualityCheckResultDetail.Base> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.rectifySuccess(data.getResult().getId());
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
        });
    }


}
