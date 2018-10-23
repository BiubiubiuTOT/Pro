package com.ljkj.qxn.wisdomsitepro.presenter.common;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.common.UploadFilesContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.EnclosureInfo;
import com.ljkj.qxn.wisdomsitepro.model.CommonModel;

import java.util.List;

import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;

/**
 * 类描述：文件上传
 * 创建人：mhl
 * 创建时间：2018/3/13 15:41
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class UploadFilesPresenter extends UploadFilesContract.Presenter {

    public UploadFilesPresenter(UploadFilesContract.View view, CommonModel model) {
        super(view, model);
    }

    @Override
    public void uploadFiles(String cgFormField, String cgFormId, String cgFormName, String fileKey, List<String> filePaths) {

        model.uploadFiles(cgFormField, cgFormId, cgFormName, fileKey, filePaths, new JsonCallback<ResponseData<List<EnclosureInfo>>>(new TypeToken<ResponseData<List<EnclosureInfo>>>() {
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
            public void onSuccess(ResponseData<List<EnclosureInfo>> data) {

                if (isAttach) {
                    if (data.isSuccess()) {
                        if (data.getResult() != null && !data.getResult().isEmpty()) {
                            view.showEnclosureInfos(data.getResult());
                        } else {
                            view.showError("附件上传，后台返回数据错误...");
                        }
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
