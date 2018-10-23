package com.ljkj.qxn.wisdomsitepro.contract.safe;

import com.ljkj.qxn.wisdomsitepro.data.entity.SafeGuardEditFloorInfo;
import com.ljkj.qxn.wisdomsitepro.model.SafeModel;

import cdsp.android.presenter.BasePresenter;
import cdsp.android.ui.base.BaseView;

/**
 * 类描述：安全防护新增楼栋
 * 创建人：rjf
 * 创建时间：2018/3/13 16:48.
 * 修改人：
 * 修改时间
 * 修改备注
 */

public interface SafeGuardAddContract {

    interface View extends BaseView {

        /**
         * 普通上传显示结果
         */
        void showAddResult();

        /**
         *
         * 编辑楼层显示上传结果
         *
         * @param info
         */
        void showAddResult(SafeGuardEditFloorInfo info);
    }

    abstract class Presenter extends BasePresenter<View, SafeModel> {

        public Presenter(View view, SafeModel model) {
            super(view, model);
        }

        /**
         * 添加新增楼栋
         *
         * @param name  楼盘名称
         * @param lous  楼栋数量
         * @param proId 项目名称
         */
        public abstract void addBuilding(String name, int lous, String proId);

        /**
         *
         * 添加新楼层
         *
         * @param louOrder 楼栋排序号
         * @param type0    楼层数量
         * @param type1    架空层数量
         * @param type3    地下室层数
         * @param proId    项目id
         */
        public abstract void addFloor(String louOrder, int type0, int type1,int type3,String proId);

        public abstract void editFloor(String aqfhsm, String cengId, String proId);
    }

}
