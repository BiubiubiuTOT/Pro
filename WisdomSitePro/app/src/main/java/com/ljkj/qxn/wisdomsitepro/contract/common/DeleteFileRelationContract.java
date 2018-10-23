package com.ljkj.qxn.wisdomsitepro.contract.common;

import com.ljkj.qxn.wisdomsitepro.model.CommonModel;

import java.util.List;

import cdsp.android.presenter.BasePresenter;
import cdsp.android.ui.base.BaseView;

public interface DeleteFileRelationContract {

    interface View extends BaseView {
        void showDeleteFilesRelation();
    }


    abstract class Presenter extends BasePresenter<View, CommonModel> {

        public Presenter(View view, CommonModel model) {
            super(view, model);
        }

        public abstract void deleteFilesRelation(List<String> deleteFileIds);
    }
}
