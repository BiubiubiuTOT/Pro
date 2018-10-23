package com.ljkj.qxn.wisdomsitepro.contract.application;


import com.ljkj.qxn.wisdomsitepro.data.entity.VideoSourceInfo;
import com.ljkj.qxn.wisdomsitepro.model.ApplicationModel;

import java.util.ArrayList;
import java.util.List;

import cdsp.android.presenter.BasePresenter;
import cdsp.android.ui.base.BaseView;

/**
 * 类描述：视频源信息
 * 创建人：lxx
 * 创建时间：2018/4/20
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public interface VideoSourceContract {

    interface View extends BaseView {
        void showVideoList(List<VideoSourceInfo> sources);

        void showVideoMonitor(List<ArrayList<VideoSourceInfo>> sources);
    }

    abstract class Presenter extends BasePresenter<View, ApplicationModel> {
        public Presenter(View view, ApplicationModel model) {
            super(view, model);
        }

        public abstract void getVideoSource(String proId);

        public abstract void getVideoMonitor(String proId);
    }

}
