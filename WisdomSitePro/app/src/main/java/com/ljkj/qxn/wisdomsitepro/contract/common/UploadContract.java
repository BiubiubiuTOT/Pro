package com.ljkj.qxn.wisdomsitepro.contract.common;

import com.ljkj.qxn.wisdomsitepro.data.FileEntity;
import com.ljkj.qxn.wisdomsitepro.model.CommonModel;

import java.io.File;
import java.util.List;

import cdsp.android.presenter.BasePresenter;
import cdsp.android.ui.base.BaseView;

public interface UploadContract {

    interface View extends BaseView {
        void showUploadInfo(List<FileEntity> entities);
    }

    abstract class Presenter extends BasePresenter<View, CommonModel> {
        public Presenter(View view, CommonModel model) {
            super(view, model);
        }

        public abstract void upload(String proId, List<File> fileList);
    }

}
