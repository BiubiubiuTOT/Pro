package com.ljkj.qxn.wisdomsitepro.presenter.common;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.common.QueryFileContract;
import com.ljkj.qxn.wisdomsitepro.data.FileEntity;
import com.ljkj.qxn.wisdomsitepro.model.CommonModel;

import java.util.List;

import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;

/**
 * 类描述：查询文件关联信息
 * 创建人：lxx
 * 创建时间：2018/9/11
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class QueryFilePresenter extends QueryFileContract.Presenter {

    public QueryFilePresenter(QueryFileContract.View view, CommonModel model) {
        super(view, model);
    }

    @Override
    public void queryFile(String id) {
        model.queryFile(id, new JsonCallback<ResponseData<List<FileEntity>>>(new TypeToken<ResponseData<List<FileEntity>>>() {
        }) {

            @Override
            protected void onError(int errcode, String errmsg) {
                if (isAttach) {
                    view.showError(errmsg);
                }
            }

            @Override
            public void onSuccess(ResponseData<List<FileEntity>> data) {
                if (isAttach) {
                    if (data.getErrcode() == 200) {
                        view.showFiles(data.getResult());
                    } else {
                        view.showError(data.getErrmsg());
                    }
                }
            }

            @Override
            public void onFinish() {
                super.onFinish();
                if (isAttach)
                    view.hideProgress();
            }
        });
    }

}
