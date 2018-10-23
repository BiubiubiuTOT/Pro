package com.ljkj.qxn.wisdomsitepro.contract.common;

import com.ljkj.qxn.wisdomsitepro.data.FileEntity;
import com.ljkj.qxn.wisdomsitepro.model.CommonModel;

import java.util.List;

import cdsp.android.presenter.BasePresenter;
import cdsp.android.ui.base.BaseView;

public interface QueryFileContract {
    interface View extends BaseView {
        void showFiles(List<FileEntity> fileEntities);
    }

    abstract class Presenter extends BasePresenter<QueryFileContract.View, CommonModel> {
        public Presenter(QueryFileContract.View view, CommonModel model) {
            super(view, model);
        }

        public abstract void queryFile(String id);
    }

}
