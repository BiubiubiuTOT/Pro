package com.ljkj.qxn.wisdomsitepro.presenter.message;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.message.NoticeContactContract;
import com.ljkj.qxn.wisdomsitepro.data.message.NoticeContactInfo;
import com.ljkj.qxn.wisdomsitepro.model.MessageModel;

import java.util.List;

import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;

/**
 * 类描述：公告联系人
 * 创建人：lxx
 * 创建时间：2018/6/26
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class GetNoticeContactPresenter extends NoticeContactContract.Presenter {
    private static List<List<NoticeContactInfo>> cacheList = null;

    public GetNoticeContactPresenter(NoticeContactContract.View view, MessageModel model) {
        super(view, model);
    }

    @Override
    public void getNoticeContact(String projectId) {
        model.getNoticeContact(projectId, new JsonCallback<ResponseData<List<List<NoticeContactInfo>>>>(new TypeToken<ResponseData<List<List<NoticeContactInfo>>>>() {
        }) {

            @Override
            public void onStart() {
                super.onStart();
                if (isAttach) {
                    view.showProgress("正在加载中");
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
            public void onSuccess(ResponseData<List<List<NoticeContactInfo>>> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showNoticeContact(data.getResult());
                    } else {
                        view.showError(data.getErrmsg());
                    }
                }
            }
        });
    }

    public static List<List<NoticeContactInfo>> getCacheList() {
        return cacheList;
    }

    public static void setCacheList(List<List<NoticeContactInfo>> cacheList) {
        GetNoticeContactPresenter.cacheList = cacheList;
    }
}
