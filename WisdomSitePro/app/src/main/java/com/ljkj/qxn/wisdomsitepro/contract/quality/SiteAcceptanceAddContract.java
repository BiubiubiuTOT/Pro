package com.ljkj.qxn.wisdomsitepro.contract.quality;

import com.ljkj.qxn.wisdomsitepro.data.entity.ConcreteEntranceAcceptanceInfo;
import com.ljkj.qxn.wisdomsitepro.model.ConcreteModel;

import cdsp.android.presenter.BasePresenter;
import cdsp.android.ui.base.BaseView;

/**
 * 商品混凝土进场验收-新增
 * Created by lxx on 2018/3/13.
 */

public interface SiteAcceptanceAddContract {
    interface View extends BaseView {
        void addSiteAcceptanceSuccess(ConcreteEntranceAcceptanceInfo detail);
    }

    abstract class Presenter extends BasePresenter<View, ConcreteModel> {
        public Presenter(View view, ConcreteModel model) {
            super(view, model);
        }

        public abstract void addSiteAcceptance(ConcreteEntranceAcceptanceInfo detail);
    }

}
