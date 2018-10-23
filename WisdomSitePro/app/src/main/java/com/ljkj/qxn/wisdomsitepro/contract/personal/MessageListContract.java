package com.ljkj.qxn.wisdomsitepro.contract.personal;

import com.ljkj.qxn.wisdomsitepro.data.entity.MessageInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.PageInfo;
import com.ljkj.qxn.wisdomsitepro.model.UserModel;

import cdsp.android.presenter.BasePresenter;
import cdsp.android.ui.base.BaseView;

/**
 * 消息列表
 * 创建人：lxx
 * 创建时间：2018/3/19 14:52
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public interface MessageListContract {

    interface View extends BaseView {
        void showMessageList(PageInfo<MessageInfo> datas);
    }


    abstract class Presenter extends BasePresenter<View, UserModel> {

        public Presenter(View view, UserModel model) {
            super(view, model);
        }

        public abstract void getMessageList(int page, String proId, int rows, int status);
    }


}
