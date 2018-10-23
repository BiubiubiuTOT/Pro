package com.ljkj.qxn.wisdomsitepro.contract.safe;

import com.ljkj.qxn.wisdomsitepro.data.entity.SafeGuardBuildInfo;
import com.ljkj.qxn.wisdomsitepro.model.SafeModel;

import cdsp.android.presenter.BasePresenter;
import cdsp.android.ui.base.BaseView;

/**
 * 类描述：安全防护楼栋详情
 * 创建人：rjf
 * 创建时间：2018/3/13 16:07.
 * 修改人：
 * 修改时间
 * 修改备注
 */

public interface SafeGuardBuildContract {
    interface View extends BaseView {

        /**
         *
         * 显示安全防护楼栋信息
         *
         * @param data 网络请求返回实体类
         */
        void showSafeGuardBuild(SafeGuardBuildInfo data);

        /**
         * 删除楼栋楼层
         */
        void deleteSafeGuardBuild();
    }

    abstract class Presenter extends BasePresenter<View, SafeModel> {

        public Presenter(View view, SafeModel model) {
            super(view, model);
        }


        /**
         *
         * 对应楼栋信息
         *
         * @param lou 楼栋名
         * @param proId 项目名
         */
        public abstract void getSafeGuardBuild(String lou, String proId);

        /**
         *
         * 删除楼栋楼层
         *
         * @param cengId 楼层ID
         */
        public abstract void deleteSafeGuardBuild(String cengId);
    }
}
