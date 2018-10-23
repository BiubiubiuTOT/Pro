package com.ljkj.qxn.wisdomsitepro.presenter.common;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.common.DeleteFilesContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.NullEntity;
import com.ljkj.qxn.wisdomsitepro.model.CommonModel;

import java.util.List;

import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;

/**
 * 类描述：批量删除文件
 * 创建人：lxx
 * 创建时间：2018/9/17
 * 修改人：
 * 修改时间：
 * 修改备注：
 * {@link DeleteFileRelationPresenter}
 */
@Deprecated
public class DeleteFilesPresenter extends DeleteFilesContract.Presenter {
    public DeleteFilesPresenter(DeleteFilesContract.View view, CommonModel model) {
        super(view, model);
    }

    @Override
    public void deleteFiles(List<String> fileIds) {
        model.deleteFiles(fileIds, new JsonCallback<ResponseData<NullEntity>>(new TypeToken<ResponseData<NullEntity>>() {
        }) {
            @Override
            protected void onError(int errcode, String errmsg) {
                if (isAttach) {
                    view.showError(errmsg);
                }
            }

            @Override
            public void onSuccess(ResponseData<NullEntity> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showDeleteFiles();
                    } else {
                        view.showError(data.getErrmsg());
                    }
                }
            }
        });
    }
}
