package com.ljkj.qxn.wisdomsitepro.contract.safe;

import com.ljkj.qxn.wisdomsitepro.data.entity.FloorInfo;
import com.ljkj.qxn.wisdomsitepro.model.SafeProtectionModel;

import java.util.List;

import cdsp.android.presenter.BasePresenter;
import cdsp.android.ui.base.BaseView;

public interface FloorContract {

    interface View extends BaseView {
        void showFloorList(List<FloorInfo> floorInfoList);

        void showFirstAddFloors();

        void showDeleteFloor();

        void showAddFloor();
    }

    abstract class Presenter extends BasePresenter<View, SafeProtectionModel> {
        public Presenter(View view, SafeProtectionModel model) {
            super(view, model);
        }

        public abstract void getFloorList(String buildId, String projCode, String projId);

        public abstract void firstAddFloors(String buildId, int num, String projCode, String projId);

        public abstract void deleteFloor(String floorId);

        public abstract void addFloor(String buildId, String projCode, String projId);
    }


}
