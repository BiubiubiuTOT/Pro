package com.ljkj.qxn.wisdomsitepro.presenter.project;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.project.ProjectWeatherContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.WeatherInfo;
import com.ljkj.qxn.wisdomsitepro.model.ProjectModel;

import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;

/**
 * 类描述：获取当前天气
 * 创建人：lxx
 * 创建时间：2018/6/27
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class ProjectWeatherPresenter extends ProjectWeatherContract.Presenter {
    public ProjectWeatherPresenter(ProjectWeatherContract.View view, ProjectModel model) {
        super(view, model);
    }

    @Override
    public void getWeather(String projectId) {
        model.getWeather(projectId, new JsonCallback<ResponseData<WeatherInfo>>(new TypeToken<ResponseData<WeatherInfo>>() {
        }) {

            @Override
            public void onFinish() {
                super.onFinish();
                if (isAttach) {
                    view.hideProgress();
                }
            }

            @Override
            protected void onError(int errcode, String errmsg) {
                if (isAttach) {
                    view.showError(errmsg);
                }
            }

            @Override
            public void onSuccess(ResponseData<WeatherInfo> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showWeather(data.getResult());
                    } else {
                        view.showError(data.getErrmsg());
                    }
                }
            }
        });
    }

}
