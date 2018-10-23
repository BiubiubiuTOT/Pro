package com.ljkj.qxn.wisdomsitepro.contract.message;

import com.ljkj.qxn.wisdomsitepro.data.message.NoticeContactInfo;
import com.ljkj.qxn.wisdomsitepro.model.MessageModel;

import java.util.List;

import cdsp.android.presenter.BasePresenter;
import cdsp.android.ui.base.BaseView;

/**
 * 类描述：公告联系人
 * 创建人：lxx
 * 创建时间：2018/6/26
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public interface NoticeContactContract {

    interface View extends BaseView {
        void showNoticeContact(List<List<NoticeContactInfo>> datas);
    }

    abstract class Presenter extends BasePresenter<NoticeContactContract.View, MessageModel> {
        public Presenter(NoticeContactContract.View view, MessageModel model) {
            super(view, model);
        }

        public abstract void getNoticeContact(String projectId);
    }

}
