package com.ljkj.qxn.wisdomsitepro.contract.message;

import com.ljkj.qxn.wisdomsitepro.data.message.NoticeInfo;
import com.ljkj.qxn.wisdomsitepro.model.MessageModel;

import cdsp.android.presenter.BasePresenter;
import cdsp.android.ui.base.BaseView;

/**
 * 类描述：公告详情
 * 创建人：lxx
 * 创建时间：2018/6/25
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public interface NoticeDetailContract {
    interface View extends BaseView {
        void showNoticeDetail(NoticeInfo noticeInfo);
    }

    abstract class Presenter extends BasePresenter<NoticeDetailContract.View, MessageModel> {
        public Presenter(NoticeDetailContract.View view, MessageModel model) {
            super(view, model);
        }

        public abstract void getNoticeDetail(String noticeId,String createUserId, String createUserName);
    }

}
