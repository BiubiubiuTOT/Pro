package com.ljkj.qxn.wisdomsitepro.contract.message;

import com.ljkj.qxn.wisdomsitepro.data.FileEntity;
import com.ljkj.qxn.wisdomsitepro.model.MessageModel;

import java.util.List;

import cdsp.android.presenter.BasePresenter;
import cdsp.android.ui.base.BaseView;

/**
 * 类描述：发布公告
 * 创建人：lxx
 * 创建时间：2018/6/23
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public interface AddNoticeContract {
    interface View extends BaseView {
        void showAddNoticeSuccess(String result);
    }

    abstract class Presenter extends BasePresenter<View, MessageModel> {
        public Presenter(View view, MessageModel model) {
            super(view, model);
        }

        public abstract void addNotice(String projectId, String noticeTitle, String noticeContent, String createUserId, String createUserName,
                              String acceptUsersId, String acceptUsersName, List<FileEntity> file);
    }

}
