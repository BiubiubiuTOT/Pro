package com.ljkj.qxn.wisdomsitepro.contract.quality;

import com.ljkj.qxn.wisdomsitepro.data.entity.ConcreteCompressiveInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.ConcreteEntranceAcceptanceInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.PageInfo;
import com.ljkj.qxn.wisdomsitepro.model.ConcreteModel;

import cdsp.android.presenter.BasePresenter;
import cdsp.android.ui.base.BaseView;

/**
 * 混凝土质量信息
 * Created by lxx on 2018/3/10.
 */

public interface ConcreteQualityInfoContract {

    interface View extends BaseView {
        void showApproachList(PageInfo<ConcreteEntranceAcceptanceInfo> pageInfo);

        void showCompressiveList(PageInfo<ConcreteCompressiveInfo> pageInfo);

        void showPermeabilityList(PageInfo<ConcreteCompressiveInfo> pageInfo);
    }

    abstract class Presenter extends BasePresenter<View, ConcreteModel> {
        public Presenter(View view, ConcreteModel model) {
            super(view, model);
        }

        public abstract void getApproachList(String keyWord, int page, String proId, int rows);

        public abstract void getCompressiveList(String keyWord, int page, String proId, int rows);

        public abstract void getPermeabilityList(String keyWord, int page, String proId, int rows);
    }

}
