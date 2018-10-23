package com.ljkj.qxn.wisdomsitepro.presenter.message;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.message.NoticeDetailContract;
import com.ljkj.qxn.wisdomsitepro.data.message.NoticeInfo;
import com.ljkj.qxn.wisdomsitepro.model.MessageModel;

import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;

/**
 * 类描述：公告详情
 * 创建人：lxx
 * 创建时间：2018/6/25
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class GetNoticeDetailPresenter extends NoticeDetailContract.Presenter {
    public GetNoticeDetailPresenter(NoticeDetailContract.View view, MessageModel model) {
        super(view, model);
    }

    @Override
    public void getNoticeDetail(String noticeId, String createUserId, String createUserName) {
        model.getNoticeDetail(noticeId, createUserId, createUserName, new JsonCallback<ResponseData<NoticeInfo>>(new TypeToken<ResponseData<NoticeInfo>>() {
        }) {

            @Override
            public void onStart() {
                super.onStart();
                if (isAttach) {
                    view.showProgress("正在加载中...");
                }
            }

            @Override
            public void onFinish() {
                super.onFinish();
                if (isAttach) {
                    view.hideProgress();
                }
            }

            @Override
            protected void onError(int errcode, String errmsg) {
                if (isAttach) {
                    view.showError(errmsg);
                }
            }

            @Override
            public void onSuccess(ResponseData<NoticeInfo> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showNoticeDetail(data.getResult());
                    } else {
                        view.showError(data.getErrmsg());
                    }
                }
            }
        });
    }
}
