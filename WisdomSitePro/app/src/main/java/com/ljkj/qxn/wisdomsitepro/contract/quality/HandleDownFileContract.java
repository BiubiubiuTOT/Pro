package com.ljkj.qxn.wisdomsitepro.contract.quality;

import com.ljkj.qxn.wisdomsitepro.data.entity.DownFileInfo;
import com.ljkj.qxn.wisdomsitepro.model.QualityModel;

import java.util.List;

import cdsp.android.presenter.BasePresenter;
import cdsp.android.ui.base.BaseView;

/**
 * 类描述：查看政府端下发的附件
 * 创建人：lxx
 * 创建时间：2018/3/15 13:48
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public interface HandleDownFileContract {


    interface View extends BaseView {
        void showDownFile(List<DownFileInfo> fileInfos);
    }

    abstract class Presenter extends BasePresenter<View, QualityModel> {

        public Presenter(View view, QualityModel model) {
            super(view, model);
        }

        public abstract void handleDownFile(String file, String type);
    }


}
