package com.ljkj.qxn.wisdomsitepro.contract.application;

import com.ljkj.qxn.wisdomsitepro.data.entity.ConstructLogDetailInfo;
import com.ljkj.qxn.wisdomsitepro.model.ApplicationModel;

import cdsp.android.presenter.BasePresenter;
import cdsp.android.ui.base.BaseView;

/**
 * 类描述：施工日志详情
 * 创建人：rjf
 * 创建时间：2018/3/15 09:00.
 * 修改人：
 * 修改时间
 * 修改备注
 */

public interface ConstructLogDetailContract {

    interface View extends BaseView {

        /**
         *
         * 显示施工日志详情
         *
         * @param info 施工日志详情实体
         */
        void showLogDetail(ConstructLogDetailInfo info);
    }

    abstract class Presenter extends BasePresenter<View, ApplicationModel> {

        public Presenter(View view, ApplicationModel model) {
            super(view, model);
        }

        /**
         *
         * 获取施工日志详情
         *
         * @param id 施工日志对应ID
         */
        public abstract void getLogDetail(String id);
    }
}
