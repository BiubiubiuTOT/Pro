package com.ljkj.qxn.wisdomsitepro.contract.application;

import com.ljkj.qxn.wisdomsitepro.data.entity.TowerCraneEquipmentDetail;
import com.ljkj.qxn.wisdomsitepro.data.entity.TowerCraneEquipmentInfo;
import com.ljkj.qxn.wisdomsitepro.model.EquipmentModel;

import java.util.List;

import cdsp.android.presenter.BasePresenter;
import cdsp.android.ui.base.BaseView;

/**
 * 类描述：吊塔设备列表
 * 创建人：lxx
 * 创建时间：2018/8/1
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public interface TowerCraneEquipmentContract {

    interface View extends BaseView {
        void showTowerCraneEquipmentList(List<TowerCraneEquipmentInfo> datas);

        void showTowerCraneDetail(TowerCraneEquipmentDetail detail);

        void showTowerCraneRecord();
    }

    abstract class Presenter extends BasePresenter<TowerCraneEquipmentContract.View, EquipmentModel> {
        public Presenter(View view, EquipmentModel model) {
            super(view, model);
        }

        public abstract void getTowerCraneEquipmentList(String proId);

        public abstract void getTowerCraneEquipmentDetail(String deviceCode);

        public abstract void getTowerCraneEquipmentRecord(String deviceCode, int type);
    }

}
