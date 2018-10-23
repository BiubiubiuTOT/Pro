package com.ljkj.qxn.wisdomsitepro.contract.safe;

import com.ljkj.qxn.wisdomsitepro.data.FileEntity;
import com.ljkj.qxn.wisdomsitepro.model.SafeModel;

import java.util.List;

import cdsp.android.presenter.BasePresenter;
import cdsp.android.ui.base.BaseView;

/**
 * 类描述：检查立即整改
 * 创建人：lxx
 * 创建时间：2018/9/6
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public interface CheckRectifyContract {

    interface View extends BaseView {
        void showSafeRectifySuccess();

        void showQualityRectifySuccess();
    }

    abstract class Presenter extends BasePresenter<View, SafeModel> {
        public Presenter(View view, SafeModel model) {
            super(view, model);
        }

        public abstract void safeCheckRectify(String id, String rectifyInfo, String isPlan, String isMeasure, String money,
                                              String reformer, String proId, String proCode, List<FileEntity> file);

        public abstract void qualityCheckRectify(String id, String rectifyInfo, String isPlan, String isMeasure, String money,
                                                 String reformer, String proId, String proCode, List<FileEntity> file);
    }

}
