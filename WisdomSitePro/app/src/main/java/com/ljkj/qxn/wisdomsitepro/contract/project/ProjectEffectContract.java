package com.ljkj.qxn.wisdomsitepro.contract.project;

import com.ljkj.qxn.wisdomsitepro.data.entity.ProjectEffectInfo;
import com.ljkj.qxn.wisdomsitepro.model.ProjectModel;

import cdsp.android.presenter.BasePresenter;
import cdsp.android.ui.base.BaseView;

/**
 * 类描述：施工现场图
 * 创建人：liufei
 * 创建时间：2018/3/10 11:33
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public interface ProjectEffectContract {

    interface View extends BaseView {

        /**
         * 显示获取的数据
         *
         * @param data
         */
        void showProjectEffect(ProjectEffectInfo data);
    }

    abstract class Presenter extends BasePresenter<View, ProjectModel> {

        public Presenter(View view, ProjectModel model) {
            super(view, model);
        }

        /**
         * 获取
         *
         * @param proId 项目id
         */
        public abstract void getProjectEffect(String proId);
    }


}
