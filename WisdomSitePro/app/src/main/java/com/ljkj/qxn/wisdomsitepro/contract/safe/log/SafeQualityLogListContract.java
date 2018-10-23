package com.ljkj.qxn.wisdomsitepro.contract.safe.log;

import com.ljkj.qxn.wisdomsitepro.data.entity.PageInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.SafeQualityLogInfo;
import com.ljkj.qxn.wisdomsitepro.model.SafeQualityLogModel;

import cdsp.android.presenter.BasePresenter;
import cdsp.android.ui.base.BaseView;

/**
 * 类描述：安全/质量日志 列表
 * 创建人：lxx
 * 创建时间：2018/9/18
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public interface SafeQualityLogListContract {

    interface View extends BaseView {
        void showSafeLogList(PageInfo<SafeQualityLogInfo> datas);

        void showQualityLogList(PageInfo<SafeQualityLogInfo> datas);
    }

    abstract class Presenter extends BasePresenter<View, SafeQualityLogModel> {
        public Presenter(View view, SafeQualityLogModel model) {
            super(view, model);
        }

        public abstract void getSafeLogList(String proId, int page, int size,String recorder);

        public abstract void getQualityLogList(String proId, int page, int size,String recorder);
    }

}
