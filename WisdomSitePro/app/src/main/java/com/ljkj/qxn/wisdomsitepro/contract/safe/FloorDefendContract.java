package com.ljkj.qxn.wisdomsitepro.contract.safe;

import com.ljkj.qxn.wisdomsitepro.data.FileEntity;
import com.ljkj.qxn.wisdomsitepro.data.entity.FloorDetail;
import com.ljkj.qxn.wisdomsitepro.model.SafeProtectionModel;

import java.util.List;

import cdsp.android.presenter.BasePresenter;
import cdsp.android.ui.base.BaseView;

public interface FloorDefendContract {
    interface View extends BaseView {
        void showFloorDetail(FloorDetail floorDetail);

        void showAddFloorDetail();
    }

    abstract class Presenter extends BasePresenter<View, SafeProtectionModel> {
        public Presenter(View view, SafeProtectionModel model) {
            super(view, model);
        }

        public abstract void getFloorDetail(String floorId);

        public abstract void addFloorDetail(String floorId,String projCode, String projId, String content, List<FileEntity> file);
    }

}
