package com.ljkj.qxn.wisdomsitepro.presenter.quality;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.quality.QualityCheckContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.PageInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.QualityCheckInfo;
import com.ljkj.qxn.wisdomsitepro.model.QualityModel;

import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;

/**
 * 类描述：质量检查
 * 创建人：lxx
 * 创建时间：2018/3/9
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class QualityCheckPresenter extends QualityCheckContract.Presenter {


    public QualityCheckPresenter(QualityCheckContract.View view, QualityModel model) {
        super(view, model);
    }

    @Override
    public void getQualityCheckList(int page, String proId, int rows, String type, String yhdj, String zglx, String jcrqTime) {
        model.getQualityCheckList(page, proId, rows, type, yhdj, zglx, jcrqTime,
                new JsonCallback<ResponseData<PageInfo<QualityCheckInfo>>>(new TypeToken<ResponseData<PageInfo<QualityCheckInfo>>>() {
                }) {
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
                    public void onSuccess(ResponseData<PageInfo<QualityCheckInfo>> data) {
                        if (isAttach) {
                            if (data.isSuccess()) {
                                view.showQualityCheckInfo(data.getResult());
                            } else {
                                view.showError(data.getErrmsg());
                            }
                        }
                    }
                });

    }
}
