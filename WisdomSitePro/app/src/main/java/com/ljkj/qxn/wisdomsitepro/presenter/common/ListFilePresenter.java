package com.ljkj.qxn.wisdomsitepro.presenter.common;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.common.ListFileContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.EnclosureInfo;
import com.ljkj.qxn.wisdomsitepro.model.CommonModel;

import java.util.List;

import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;

/**
 * 类描述：
 * 创建人：mhl
 * 创建时间：2018/2/28 9:54
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class ListFilePresenter extends ListFileContract.Presenter {

    public ListFilePresenter(ListFileContract.View view, CommonModel model) {
        super(view, model);
    }

    @Override
    public void listFiles(String fieldName, String id) {

        model.listFiles(fieldName, id, new JsonCallback<ResponseData<List<EnclosureInfo>>>(new TypeToken<ResponseData<List<EnclosureInfo>>>() {
        }) {

            @Override
            public void onSuccess(ResponseData<List<EnclosureInfo>> data) {

                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showFiles(data.getResult());
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
