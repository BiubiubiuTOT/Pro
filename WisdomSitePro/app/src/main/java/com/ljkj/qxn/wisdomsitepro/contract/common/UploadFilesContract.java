package com.ljkj.qxn.wisdomsitepro.contract.common;

import com.ljkj.qxn.wisdomsitepro.data.entity.EnclosureInfo;
import com.ljkj.qxn.wisdomsitepro.model.CommonModel;

import java.util.List;

import cdsp.android.presenter.BasePresenter;
import cdsp.android.ui.base.BaseView;

/**
 * 类描述：文件上传
 * 创建人：mhl
 * 创建时间：2018/3/13 15:39
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public interface UploadFilesContract {

    interface View extends BaseView {

        /**
         * @param data
         */
        void showEnclosureInfos(List<EnclosureInfo> data);

    }

    abstract class Presenter extends BasePresenter<View, CommonModel> {

        public Presenter(View view, CommonModel model) {
            super(view, model);
        }

        /**
         * @param cgFormField
         * @param cgFormId
         * @param cgFormName
         * @param fileKey
         * @param filePaths
         */
        public abstract void uploadFiles(String cgFormField, String cgFormId, String cgFormName, String fileKey, List<String> filePaths);
    }
}
