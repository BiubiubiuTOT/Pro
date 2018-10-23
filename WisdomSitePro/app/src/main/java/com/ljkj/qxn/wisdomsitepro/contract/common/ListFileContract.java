package com.ljkj.qxn.wisdomsitepro.contract.common;

import com.ljkj.qxn.wisdomsitepro.data.entity.EnclosureInfo;
import com.ljkj.qxn.wisdomsitepro.model.CommonModel;

import java.util.List;

import cdsp.android.presenter.BasePresenter;
import cdsp.android.ui.base.BaseView;

/**
 * 类描述：附件列表查询
 * 创建人：mhl
 * 创建时间：2018/2/28 9:54
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public interface ListFileContract {


    interface View extends BaseView {


        /**
         * 文件列表
         *
         * @param data
         */
        void showFiles(List<EnclosureInfo> data);

    }

    abstract class Presenter extends BasePresenter<View, CommonModel> {

        public Presenter(View view, CommonModel model) {
            super(view, model);
        }


        /**
         * 文件列表查询
         *
         * @param fieldName
         * @param id
         */
        public abstract void listFiles(String fieldName, String id);

    }

}
