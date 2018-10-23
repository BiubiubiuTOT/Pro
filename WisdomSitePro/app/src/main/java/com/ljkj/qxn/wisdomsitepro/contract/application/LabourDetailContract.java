package com.ljkj.qxn.wisdomsitepro.contract.application;

import com.ljkj.qxn.wisdomsitepro.data.entity.ContractInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.CreditInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.ExperienceInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.InjuryInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.SkillInfo;
import com.ljkj.qxn.wisdomsitepro.model.ApplicationModel;

import java.util.List;

import cdsp.android.presenter.BasePresenter;
import cdsp.android.ui.base.BaseView;

/**
 * 类描述：
 * 创建人：mhl
 * 创建时间：2018/2/27 9:38
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public interface LabourDetailContract {


    interface View extends BaseView {

        void showContractList(List<ContractInfo> data);

        void showCreditList(List<CreditInfo> data);

        void showInjuryList(List<InjuryInfo> data);

        void showExperienceList(List<ExperienceInfo> data);

        void showSkillList(List<SkillInfo> data);
    }

    abstract class Presenter extends BasePresenter<View, ApplicationModel> {

        public Presenter(View view, ApplicationModel model) {
            super(view, model);
        }

        /**
         * 查询劳务合同列表
         *
         * @param id
         */
        public abstract void getContractList(String id);

        public abstract void getCreditList(String id);

        public abstract void getExperienceList(String id);

        public abstract void getInjuryList(String id);

        public abstract void getSkillList(String id);

    }
}
