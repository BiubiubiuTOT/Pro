package com.ljkj.qxn.wisdomsitepro.contract.quality;

import com.ljkj.qxn.wisdomsitepro.data.entity.ConcreteCompressiveInfo;
import com.ljkj.qxn.wisdomsitepro.model.ConcreteModel;

import cdsp.android.presenter.BasePresenter;
import cdsp.android.ui.base.BaseView;

/**
 * 混凝土抗压强度检验-详情
 * Created by lxx on 2018/3/11.
 */

public interface ConcreteCompressionContract {

    interface View extends BaseView {
        void showCompressionDetail(ConcreteCompressiveInfo detail);
    }

    abstract class Presenter extends BasePresenter<View, ConcreteModel> {
        public Presenter(View view, ConcreteModel model) {
            super(view, model);
        }

        public abstract void getCompressiveDetail(String id);
    }


}
