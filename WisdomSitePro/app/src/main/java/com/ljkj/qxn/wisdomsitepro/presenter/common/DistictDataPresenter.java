package com.ljkj.qxn.wisdomsitepro.presenter.common;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.common.DistictDataContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.DistictInfo;
import com.ljkj.qxn.wisdomsitepro.model.CommonModel;

import java.util.List;

import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;

/**
 * 类描述：数据字典
 * 创建人：mhl
 * 创建时间：2018/2/28 10:06
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class DistictDataPresenter extends DistictDataContract.Presenter {


    public DistictDataPresenter(DistictDataContract.View view, CommonModel model) {
        super(view, model);
    }

    @Override
    public void listDisticts(String type) {
        model.listDictData(type, new JsonCallback<ResponseData<List<DistictInfo>>>(new TypeToken<ResponseData<List<DistictInfo>>>() {
        }) {

            @Override
            public void onSuccess(ResponseData<List<DistictInfo>> data) {

                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showDisticts(data.getResult());
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
}
