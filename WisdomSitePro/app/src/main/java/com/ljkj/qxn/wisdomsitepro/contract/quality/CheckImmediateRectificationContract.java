package com.ljkj.qxn.wisdomsitepro.contract.quality;

import com.ljkj.qxn.wisdomsitepro.data.entity.QualityCheckResultDetail;
import com.ljkj.qxn.wisdomsitepro.model.QualityModel;

import java.io.File;
import java.util.List;

import cdsp.android.presenter.BasePresenter;
import cdsp.android.ui.base.BaseView;

/**
 * 立即整改
 * 创建人：lxx
 * 创建时间：2018/3/15 16:50
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public interface CheckImmediateRectificationContract {

    interface View extends BaseView {
        void rectifySuccess(String id);
    }

    abstract class Presenter extends BasePresenter<View, QualityModel> {

        public Presenter(View view, QualityModel model) {
            super(view, model);
        }

        public abstract void dealWithSave(QualityCheckResultDetail.Base base, String proId);

        /**
         * 立即整改/重新整改文件上传
         *
         * @param base        base
         * @param proId       项目id
         * @param imageList   整改图片
         * @param reportList  整改报告书扫描件
         * @param planList    制定预案附件
         * @param measureList 制定措施附件
         * @param moneyList   整改资金附件
         */
        public abstract void dealWithAndFileSave(QualityCheckResultDetail.Base base, String proId, List<File> imageList,
                                                 List<File> reportList, List<File> planList, List<File> measureList, List<File> moneyList);
    }

}
