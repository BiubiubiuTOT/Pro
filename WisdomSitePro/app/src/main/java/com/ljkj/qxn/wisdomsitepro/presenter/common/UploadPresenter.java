package com.ljkj.qxn.wisdomsitepro.presenter.common;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.common.UploadContract;
import com.ljkj.qxn.wisdomsitepro.data.FileEntity;
import com.ljkj.qxn.wisdomsitepro.model.CommonModel;

import java.io.File;
import java.util.List;

import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;

public class UploadPresenter extends UploadContract.Presenter {
    public UploadPresenter(UploadContract.View view, CommonModel model) {
        super(view, model);
    }

    @Override
    public void upload(String proId, List<File> fileList) {
        model.upload(proId, fileList, new JsonCallback<ResponseData<List<FileEntity>>>(new TypeToken<ResponseData<List<FileEntity>>>() {
        }) {
            @Override
            public void onStart() {
                super.onStart();
                if (isAttach) {
                    view.showProgress("文件上传中...");
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
            public void onSuccess(ResponseData<List<FileEntity>> data) {
                if (isAttach) {
                    if (data.getErrcode() == 200) {
                        view.showUploadInfo(data.getResult());
                    } else {
                        view.showError(data.getErrmsg());
                    }

                }
            }

        });
    }
}
