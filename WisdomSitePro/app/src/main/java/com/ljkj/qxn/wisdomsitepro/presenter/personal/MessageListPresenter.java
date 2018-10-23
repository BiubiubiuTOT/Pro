package com.ljkj.qxn.wisdomsitepro.presenter.personal;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.personal.MessageListContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.MessageInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.PageInfo;
import com.ljkj.qxn.wisdomsitepro.model.UserModel;

import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;

/**
 * 消息列表
 * 创建人：lxx
 * 创建时间：2018/3/19 14:59
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class MessageListPresenter extends MessageListContract.Presenter {
    public MessageListPresenter(MessageListContract.View view, UserModel model) {
        super(view, model);
    }

    @Override
    public void getMessageList(int page, String proId, int rows, int status) {
        model.getMessageList(page, proId, rows, status, new JsonCallback<ResponseData<PageInfo<MessageInfo>>>(new TypeToken<ResponseData<PageInfo<MessageInfo>>>() {
        }) {
            @Override
            protected void onError(int errcode, String errmsg) {
                if (isAttach) {
                    view.showError(errmsg);
                }
            }

            @Override
            public void onSuccess(ResponseData<PageInfo<MessageInfo>> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showMessageList(data.getResult());
                    } else {
                        view.showError(data.getErrmsg());
                    }
                }
            }

            @Override
            public void onFinish() {
                super.onFinish();
                if (isAttach) {
                    view.hideProgress();
                }
            }

        });
    }
}
