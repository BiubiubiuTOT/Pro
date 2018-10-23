package com.ljkj.qxn.wisdomsitepro.contract.safe;

import com.ljkj.qxn.wisdomsitepro.data.entity.BuildingInfo;
import com.ljkj.qxn.wisdomsitepro.model.SafeProtectionModel;

import java.util.List;

import cdsp.android.presenter.BasePresenter;
import cdsp.android.ui.base.BaseView;

public interface BuildingContract {
    interface View extends BaseView {
        void showBuildings(List<BuildingInfo> buildingInfoList);

        void showDeleteBuilding();

        void showUpdateBuilding();

        void showAddBuild();

        void showFirstAddBuild();
    }

    abstract class Presenter extends BasePresenter<View, SafeProtectionModel> {
        public Presenter(View view, SafeProtectionModel model) {
            super(view, model);
        }

        public abstract void getBuildList(String proId);

        public abstract void deleteBuild(String id);

        public abstract void updateBuild(String id, String buildName, String proId);

        public abstract void addBuild(String buildName, String proId, String proCode, String proName);

        public abstract void firstAddBuild(String buildName, int num, String proCode, String proId, String proName);
    }

}
