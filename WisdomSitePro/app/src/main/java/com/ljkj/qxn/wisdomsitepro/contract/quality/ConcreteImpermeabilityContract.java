package com.ljkj.qxn.wisdomsitepro.contract.quality;

import com.ljkj.qxn.wisdomsitepro.data.entity.ConcreteCompressiveInfo;
import com.ljkj.qxn.wisdomsitepro.model.ConcreteModel;

import cdsp.android.presenter.BasePresenter;
import cdsp.android.ui.base.BaseView;

/**
 * 混凝土抗渗检验-详情
 * Created by lxx on 2018/3/11.
 */

public interface ConcreteImpermeabilityContract {

    interface View extends BaseView {
        void showPermeabilityDetail(ConcreteCompressiveInfo detail);
    }

    abstract class Presenter extends BasePresenter<View, ConcreteModel> {
        public Presenter(View view, ConcreteModel model) {
            super(view, model);
        }

        public abstract void getPermeabilityDetail(String id);
    }

}
