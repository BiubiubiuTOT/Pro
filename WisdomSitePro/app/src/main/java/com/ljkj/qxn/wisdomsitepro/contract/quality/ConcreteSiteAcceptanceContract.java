package com.ljkj.qxn.wisdomsitepro.contract.quality;

import com.ljkj.qxn.wisdomsitepro.data.entity.ConcreteEntranceAcceptanceInfo;
import com.ljkj.qxn.wisdomsitepro.model.ConcreteModel;

import cdsp.android.presenter.BasePresenter;
import cdsp.android.ui.base.BaseView;

/**
 * 商品混凝土进场验收-详情
 * Created by lxx on 2018/3/11.
 */

public interface ConcreteSiteAcceptanceContract {

    interface View extends BaseView {
        void showSiteAcceptanceDetail(ConcreteEntranceAcceptanceInfo detail);
    }

    abstract class Presenter extends BasePresenter<View, ConcreteModel> {

        public Presenter(View view, ConcreteModel model) {
            super(view, model);
        }

        public abstract void getApproachDetail(String id);

    }


}
