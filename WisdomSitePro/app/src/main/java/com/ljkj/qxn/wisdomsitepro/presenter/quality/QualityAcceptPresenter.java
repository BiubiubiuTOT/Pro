package com.ljkj.qxn.wisdomsitepro.presenter.quality;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.quality.QualityAcceptContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.PageInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.QualityAcceptInfo;
import com.ljkj.qxn.wisdomsitepro.model.QualityModel;

import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;

/**
 * 类描述: 质量验收
 * 创建人：rjf
 * 创建时间：2018/3/10 12:01.
 * 修改人：
 * 修改时间
 * 修改备注
 */

public class QualityAcceptPresenter extends QualityAcceptContract.Presenter {

    public QualityAcceptPresenter(QualityAcceptContract.View view, QualityModel model) {
        super(view, model);
    }

    @Override
    public void getQualityAcceptList(int page, String proId, int rows) {
        model.getQualityAcceptList(page, proId, rows, new JsonCallback<ResponseData<PageInfo<QualityAcceptInfo>>>(new TypeToken<ResponseData<PageInfo<QualityAcceptInfo>>>(){}) {
            @Override
            public void onSuccess(ResponseData<PageInfo<QualityAcceptInfo>> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showQualityAcceptInfo(data.getResult());
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
