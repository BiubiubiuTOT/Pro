package com.ljkj.qxn.wisdomsitepro.contract.common;

import com.ljkj.qxn.wisdomsitepro.model.CommonModel;

import java.util.List;

import cdsp.android.presenter.BasePresenter;
import cdsp.android.ui.base.BaseView;

/**
 * 类描述：批量删除文件
 * 创建人：lxx
 * 创建时间：2018/9/17
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public interface DeleteFilesContract {

    interface View extends BaseView {
        void showDeleteFiles();
    }

    abstract class Presenter extends BasePresenter<View, CommonModel> {

        public Presenter(View view, CommonModel model) {
            super(view, model);
        }

        public abstract void deleteFiles(List<String> fileIds);

    }

}
