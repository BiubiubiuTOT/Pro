package com.ljkj.qxn.wisdomsitepro.contract.project;

import com.ljkj.qxn.wisdomsitepro.data.entity.WeatherInfo;
import com.ljkj.qxn.wisdomsitepro.model.ProjectModel;

import cdsp.android.presenter.BasePresenter;
import cdsp.android.ui.base.BaseView;

/**
 * 类描述：天气
 * 创建人：lxx
 * 创建时间：2018/6/27
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public interface ProjectWeatherContract {

    interface View extends BaseView {
        void showWeather(WeatherInfo weatherInfo);
    }

    abstract class Presenter extends BasePresenter<ProjectWeatherContract.View, ProjectModel> {
        public Presenter(ProjectWeatherContract.View view, ProjectModel model) {
            super(view, model);
        }

        public abstract void getWeather(String projectId);
    }


}
