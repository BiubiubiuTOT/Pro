package com.ljkj.qxn.wisdomsitepro.presenter.message;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.message.NoticeListContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.PageInfo;
import com.ljkj.qxn.wisdomsitepro.data.message.NoticeInfo;
import com.ljkj.qxn.wisdomsitepro.model.MessageModel;

import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;

/**
 * 类描述：公告列表
 * 创建人：lxx
 * 创建时间：2018/6/25
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class GetNoticeListPresenter extends NoticeListContract.Presenter {
    public GetNoticeListPresenter(NoticeListContract.View view, MessageModel model) {
        super(view, model);
    }

    @Override
    public void getNoticeList(String projectId, String userName, int page, int size) {
        model.getNoticeList(projectId, userName, page, size, new JsonCallback<ResponseData<PageInfo<NoticeInfo>>>(new TypeToken<ResponseData<PageInfo<NoticeInfo>>>() {
        }) {

            @Override
            public void onSuccess(ResponseData<PageInfo<NoticeInfo>> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showNoticeList(data.getResult());
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

            @Override
            protected void onError(int errcode, String errmsg) {
                if (isAttach) {
                    view.showError(errmsg);
                }
            }


        });
    }
}
