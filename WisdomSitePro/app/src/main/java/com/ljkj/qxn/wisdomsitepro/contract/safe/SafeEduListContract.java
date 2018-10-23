package com.ljkj.qxn.wisdomsitepro.contract.safe;

import com.ljkj.qxn.wisdomsitepro.data.entity.PageInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.SafeEduInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.SafeTechnologyInfo;
import com.ljkj.qxn.wisdomsitepro.model.SafeModel;

import cdsp.android.presenter.BasePresenter;
import cdsp.android.ui.base.BaseView;

/**
 * 类描述：安全教育
 * 创建人：liufei
 * 创建时间：2018/3/12 13:41
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public interface SafeEduListContract {
    interface View extends BaseView {

        void showSafeEduList(PageInfo<SafeEduInfo> pageInfo);

        void showSafeTechnologyList(PageInfo<SafeTechnologyInfo> pageInfo);
    }

    abstract class Presenter extends BasePresenter<View, SafeModel> {
        public Presenter(View view, SafeModel model) {
            super(view, model);
        }

        public abstract void getSafeEduList(String proId, String isSb, String date, int rows, int page);

        public abstract void getSafeEdeTechnologyList(String proId, String isReport, String date, int pageSize, int pageNum);
    }

}
