package com.ljkj.qxn.wisdomsitepro.contract.project;

import com.ljkj.qxn.wisdomsitepro.data.entity.ProjectProgressInfo;
import com.ljkj.qxn.wisdomsitepro.model.ProjectModel;

import java.util.List;

import cdsp.android.presenter.BasePresenter;
import cdsp.android.ui.base.BaseView;

/**
 * 类描述：工程形象进度
 * 创建人：liufei
 * 创建时间：2018/3/10 11:33
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public interface ProjectProgressContract {

    interface View extends BaseView {

        /**
         * 显示获取的数据
         *
         * @param data
         */
        void showProjectProgressList(List<ProjectProgressInfo> data);

        void showRecentProjectProgressInfo(ProjectProgressInfo data);
    }

    abstract class Presenter extends BasePresenter<View, ProjectModel> {

        public Presenter(View view, ProjectModel model) {
            super(view, model);
        }

        /**
         * 获取
         */
        public abstract void getProjectProgress(String proId, String time);

        public abstract void getRecentProjectProgress(String proId);
    }


}
