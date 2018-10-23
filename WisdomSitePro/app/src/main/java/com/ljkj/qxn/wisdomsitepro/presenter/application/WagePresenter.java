package com.ljkj.qxn.wisdomsitepro.presenter.application;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.application.WageContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.PageInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.SfgzStatisticsInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.WageInfo;
import com.ljkj.qxn.wisdomsitepro.model.ApplicationModel;

import cdsp.android.http.HttpUtils;
import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;

/**
 * 类描述：工资列表查询
 * 创建人：mhl
 * 创建时间：2018/2/27 9:56
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class WagePresenter extends WageContract.Presenter {

    public WagePresenter(WageContract.View view, ApplicationModel model) {
        super(view, model);
    }

    @Override
    public void listPageWageInfo(String month, String searchKey, int page, String proId, int rows, String workType, String teams) {
        model.wageList(month, searchKey, page, proId, rows, workType, teams, new JsonCallback<ResponseData<PageInfo<WageInfo>>>(new TypeToken<ResponseData<PageInfo<WageInfo>>>() {
        }) {

            @Override
            public void onFinish() {
                super.onFinish();
                if (isAttach) {
                    view.hideProgress();
                }
            }

            @Override
            public void onSuccess(ResponseData<PageInfo<WageInfo>> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showPageWageInfo(data.getResult());
                    } else {
                        view.showError(data.getErrmsg());
                    }
                }
            }

            @Override
            protected void onError(int errcode, String errmsg) {

                if (isAttach) {
                    view.showError(errmsg);
                }
            }
        });
    }

    @Override
    public void proSfgzStatistics(String month, String proId) {

        model.proSfgzStatistics(month, proId, new JsonCallback<ResponseData<SfgzStatisticsInfo>>(new TypeToken<ResponseData<SfgzStatisticsInfo>>() {
        }) {

            @Override
            public void onSuccess(ResponseData<SfgzStatisticsInfo> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showSfgzStatisticsInfo(data.getResult());
                    } else {
                        view.showError(data.getErrmsg());
                    }
                }
            }

            @Override
            protected void onError(int errcode, String errmsg) {
                if (isAttach) {
                    view.showError(errmsg);
                }
            }
        });
    }
}
