package com.ljkj.qxn.wisdomsitepro.contract.common;

import com.ljkj.qxn.wisdomsitepro.model.CommonModel;

import java.util.List;

import cdsp.android.model.BaseModel;
import cdsp.android.presenter.BasePresenter;
import cdsp.android.ui.base.BaseView;

/**
 * 类描述：图片压缩
 * 创建人：mhl
 * 创建时间：2018/3/14 15:49
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public interface ImageTagCompressorContract {


    interface View extends BaseView {


        /**
         * @param data
         */
        void showCompressFilePaths(String tag, List<String> data);

    }

    abstract class Presenter extends BasePresenter<View, BaseModel> {

        public Presenter(View view, CommonModel model) {
            super(view, model);
        }

        /**
         * @param sourceFilePaths
         */
        public abstract void compressorImages(String tag, List<String> sourceFilePaths);
    }
}
