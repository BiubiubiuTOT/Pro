package com.ljkj.qxn.wisdomsitepro.presenter.application;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.application.LabourDetailContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.ContractInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.CreditInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.ExperienceInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.InjuryInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.SkillInfo;
import com.ljkj.qxn.wisdomsitepro.model.ApplicationModel;

import java.util.List;

import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;

/**
 * 类描述：劳务人员列表
 * 创建人：mhl
 * 创建时间：2018/2/27 9:37
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class LabourDetailPresenter extends LabourDetailContract.Presenter {

    public LabourDetailPresenter(LabourDetailContract.View view, ApplicationModel model) {
        super(view, model);
    }

    @Override
    public void getContractList(String id) {
        model.getContractList(id, new JsonCallback<ResponseData<List<ContractInfo>>>(new TypeToken<ResponseData<List<ContractInfo>>>() {
        }) {

            @Override
            public void onFinish() {
                super.onFinish();
                if (isAttach) {
                    view.hideProgress();
                }
            }

            @Override
            public void onSuccess(ResponseData<List<ContractInfo>> data) {

                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showContractList(data.getResult());
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
    public void getCreditList(String id) {
        model.getCreditList(id, new JsonCallback<ResponseData<List<CreditInfo>>>(new TypeToken<ResponseData<List<CreditInfo>>>() {
        }) {

            @Override
            public void onFinish() {
                super.onFinish();
                if (isAttach) {
                    view.hideProgress();
                }
            }

            @Override
            public void onSuccess(ResponseData<List<CreditInfo>> data) {

                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showCreditList(data.getResult());
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
    public void getExperienceList(String id) {
        model.getExperienceList(id, new JsonCallback<ResponseData<List<ExperienceInfo>>>(new TypeToken<ResponseData<List<ExperienceInfo>>>() {
        }) {

            @Override
            public void onFinish() {
                super.onFinish();
                if (isAttach) {
                    view.hideProgress();
                }
            }

            @Override
            public void onSuccess(ResponseData<List<ExperienceInfo>> data) {

                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showExperienceList(data.getResult());
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
    public void getInjuryList(String id) {
        model.getInjuryList(id, new JsonCallback<ResponseData<List<InjuryInfo>>>(new TypeToken<ResponseData<List<InjuryInfo>>>() {
        }) {

            @Override
            public void onFinish() {
                super.onFinish();
                if (isAttach) {
                    view.hideProgress();
                }
            }

            @Override
            public void onSuccess(ResponseData<List<InjuryInfo>> data) {

                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showInjuryList(data.getResult());
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
    public void getSkillList(String id) {
        model.getSkillList(id, new JsonCallback<ResponseData<List<SkillInfo>>>(new TypeToken<ResponseData<List<SkillInfo>>>() {
        }) {

            @Override
            public void onFinish() {
                super.onFinish();
                if (isAttach) {
                    view.hideProgress();
                }
            }

            @Override
            public void onSuccess(ResponseData<List<SkillInfo>> data) {

                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showSkillList(data.getResult());
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
