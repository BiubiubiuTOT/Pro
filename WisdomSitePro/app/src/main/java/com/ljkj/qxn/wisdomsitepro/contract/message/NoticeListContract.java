package com.ljkj.qxn.wisdomsitepro.contract.message;

import com.ljkj.qxn.wisdomsitepro.data.entity.PageInfo;
import com.ljkj.qxn.wisdomsitepro.data.message.NoticeInfo;
import com.ljkj.qxn.wisdomsitepro.model.MessageModel;

import cdsp.android.presenter.BasePresenter;
import cdsp.android.ui.base.BaseView;

/**
 * 类描述：公告列表
 * 创建人：lxx
 * 创建时间：2018/6/25
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public interface NoticeListContract {
    interface View extends BaseView {
        void showNoticeList(PageInfo<NoticeInfo> datas);
    }

    abstract class Presenter extends BasePresenter<NoticeListContract.View, MessageModel> {
        public Presenter(NoticeListContract.View view, MessageModel model) {
            super(view, model);
        }

        public abstract void getNoticeList(String projectId, String userName, int page, int size);
    }

}
