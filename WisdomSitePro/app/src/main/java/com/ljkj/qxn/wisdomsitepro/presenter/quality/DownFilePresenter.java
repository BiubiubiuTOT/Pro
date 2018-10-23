package com.ljkj.qxn.wisdomsitepro.presenter.quality;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.quality.HandleDownFileContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.DownFileInfo;
import com.ljkj.qxn.wisdomsitepro.model.QualityModel;

import java.util.List;

import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;

/**
 * 类描述：政府端下发的附件
 * 创建人：lxx
 * 创建时间：2018/3/15 14:18
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class DownFilePresenter extends HandleDownFileContract.Presenter {

    public DownFilePresenter(HandleDownFileContract.View view, QualityModel model) {
        super(view, model);
    }

    @Override
    public void handleDownFile(String file, String type) {
        model.handleDownFile(file, type, new JsonCallback<ResponseData<List<DownFileInfo>>>(new TypeToken<ResponseData<List<DownFileInfo>>>() {
        }) {

            @Override
            protected void onError(int errcode, String errmsg) {
                if (isAttach) {
                    view.showError(errmsg);
                }
            }

            @Override
            public void onSuccess(ResponseData<List<DownFileInfo>> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showDownFile(data.getResult());
                    } else {
                        view.showError(data.getErrmsg());
                    }
                }
            }

        });
    }
}
