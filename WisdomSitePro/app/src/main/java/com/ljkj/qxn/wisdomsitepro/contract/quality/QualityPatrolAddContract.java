package com.ljkj.qxn.wisdomsitepro.contract.quality;

import com.ljkj.qxn.wisdomsitepro.data.entity.LabourInfo;
import com.ljkj.qxn.wisdomsitepro.model.QualityModel;

import java.util.List;

import cdsp.android.http.ResponseData;
import cdsp.android.presenter.BasePresenter;
import cdsp.android.ui.base.BaseView;

/**
 * 类描述：新增巡检
 * 创建人：mhl
 * 创建时间：2018/3/17 9:27
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public interface QualityPatrolAddContract {

    interface View extends BaseView {


        /**
         * 下发结果显示
         *
         * @param data
         */
        void showQualityPatrolAddResult(ResponseData<String> data);

        /**
         * 班组列表
         *
         * @param data
         */
        void showManagerList(List<LabourInfo> data);
    }

    abstract class Presenter extends BasePresenter<View, QualityModel> {

        public Presenter(View view, QualityModel model) {
            super(view, model);
        }


        /**
         * 下发责任整改通知书
         *
         * @param bzfzr
         * @param code
         * @param fwr
         * @param lossType
         * @param status
         * @param upTime
         * @param type
         * @param whyType
         * @param proId
         * @param zgbz
         * @param zgnr
         */
        public abstract void addQualityPatrol(String bzfzr, String code, String fwr, String lossType, String status, String upTime, String type, String whyType, String proId,
                                              String zgbz, String zgnr);

        /**
         * 班组负责人列表
         *
         * @param proId
         */
        public abstract void listManageList(String proId);
    }
}
