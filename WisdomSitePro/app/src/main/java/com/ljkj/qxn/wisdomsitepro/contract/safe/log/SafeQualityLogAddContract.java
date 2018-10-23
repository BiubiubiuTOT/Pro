package com.ljkj.qxn.wisdomsitepro.contract.safe.log;

import com.ljkj.qxn.wisdomsitepro.data.FileEntity;
import com.ljkj.qxn.wisdomsitepro.model.SafeQualityLogModel;

import java.util.List;

import cdsp.android.presenter.BasePresenter;
import cdsp.android.ui.base.BaseView;

/**
 * 类描述：新增安全/质量日志
 * 创建人：lxx
 * 创建时间：2018/9/18
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public interface SafeQualityLogAddContract {
    interface View extends BaseView {
        void showAddSafe();

        void showAddQuality();
    }

    abstract class Presenter extends BasePresenter<View, SafeQualityLogModel> {

        public Presenter(View view, SafeQualityLogModel model) {
            super(view, model);
        }

        public abstract void addSafeLog(String conProcess, String conSite, String recordDate, String createUserId, String createUsername, String projCode,
                                        String projId, String securityProblem, String securityStatus, String weather, List<FileEntity> file);

        public abstract void addQualityLog(String constructionDynamic, String constructionPosition, String recordDate, String projId,
                                           String qualityProblemResult, String qualitySituation, String weather, List<FileEntity> file, String createUserName);
    }

}
