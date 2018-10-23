package com.ljkj.qxn.wisdomsitepro.contract.quality;

import com.ljkj.qxn.wisdomsitepro.data.entity.PageInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.SafeQualityLogDetail;
import com.ljkj.qxn.wisdomsitepro.data.entity.SafeQualityLogInfo;
import com.ljkj.qxn.wisdomsitepro.model.QualityModel;

import java.io.File;
import java.util.List;

import cdsp.android.presenter.BasePresenter;
import cdsp.android.ui.base.BaseView;

/**
 * 类描述：安全/质量日志
 * 创建人：lxx
 * 创建时间：2018/7/9
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public interface SafeQualityLogContract {

    interface View extends BaseView {
        void showLogList(PageInfo<SafeQualityLogInfo> datas);

        void showLogDetail(SafeQualityLogDetail detail);

        void showAddLogSuccess();
    }

    abstract class Presenter extends BasePresenter<SafeQualityLogContract.View, QualityModel> {
        public Presenter(SafeQualityLogContract.View view, QualityModel model) {
            super(view, model);
        }

        public abstract void getLogList(String projectId, int type, int page, int rows);

        public abstract void getLogDetail(String id);

        public abstract void addLog(String proId, int type, String date, String weather, String constructionSite,
                                    String constructionDynamic, String status, String handleSituation, List<File> imageList);
    }

}
