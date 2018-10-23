package com.ljkj.qxn.wisdomsitepro.contract.application;

import com.ljkj.qxn.wisdomsitepro.data.FileEntity;
import com.ljkj.qxn.wisdomsitepro.data.entity.ConstructAddLogInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.ConstructLogAddInfo;
import com.ljkj.qxn.wisdomsitepro.model.ApplicationModel;

import java.util.List;

import cdsp.android.presenter.BasePresenter;
import cdsp.android.ui.base.BaseView;

/**
 * 类描述：新增施工日志
 * 创建人：rjf
 * 创建时间：2018/3/14 14:54.
 * 修改人：
 * 修改时间
 * 修改备注
 */

public interface ConstructAddLogContract {

    interface View extends BaseView {


        /**
         * 施工日志新增页面信息
         *
         * @param info
         */
        void showAddInfo(ConstructLogAddInfo info);

        /**
         * 施工日志保存结果
         */
        void showAddResult(ConstructAddLogInfo info);
    }

    abstract class Presenter extends BasePresenter<View, ApplicationModel> {

        public Presenter(View view, ApplicationModel model) {
            super(view, model);
        }


        /**
         * 获取施工日志新增页面保存所需信息
         *
         * @param proId 当前项目ID
         */
        public abstract void getConstructLogAddInfo(String proId);

        /**
         * 新增施工日志
         *
         * @param constructionUnit 施工单位
         * @param date             日期
         * @param emergency        突发情况
         * @param maxMinTemp       最高/最低温度
         * @param proHead          项目负责人
         * @param proId            项目ID
         * @param proName          项目名称
         * @param proNo            项目编号
         * @param production       生产情况记录
         * @param qualitySafety    技术质量安全工作记录
         * @param recorder         记录人
         * @param weather          天气
         * @param wind             风力等级
         */
        public abstract void saveConstructLog(String constructionUnit, String date, String emergency, String maxMinTemp, String proHead,
                                              String proId, String proName, String proNo, String production, String qualitySafety, String recorder,
                                              String weather, String wind, List<FileEntity> file);
    }
}
