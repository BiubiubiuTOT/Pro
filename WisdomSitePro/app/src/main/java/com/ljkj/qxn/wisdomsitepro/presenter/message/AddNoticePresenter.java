package com.ljkj.qxn.wisdomsitepro.presenter.message;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.contract.message.AddNoticeContract;
import com.ljkj.qxn.wisdomsitepro.data.FileEntity;
import com.ljkj.qxn.wisdomsitepro.data.entity.NullEntity;
import com.ljkj.qxn.wisdomsitepro.model.MessageModel;

import java.util.List;

import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;

/**
 * 类描述：发布公告
 * 创建人：lxx
 * 创建时间：2018/6/23
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class AddNoticePresenter extends AddNoticeContract.Presenter {
    public AddNoticePresenter(AddNoticeContract.View view, MessageModel model) {
        super(view, model);
    }

    @Override
    public void addNotice(String projectId, String noticeTitle, String noticeContent, String createUserId, String createUserName, String acceptUsersId, String acceptUsersName, List<FileEntity> file) {
        model.addNotice(projectId, noticeTitle, noticeContent, createUserId, createUserName, acceptUsersId, acceptUsersName, file, new JsonCallback<ResponseData<NullEntity>>(new TypeToken<ResponseData<NullEntity>>() {
        }) {
            @Override
            public void onStart() {
                super.onStart();
                if (isAttach) {
                    view.showProgress("提交中...");
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
                view.showError(errmsg);
            }

            @Override
            public void onSuccess(ResponseData<NullEntity> data) {
                if (isAttach) {
                    if (data.isSuccess()) {
                        view.showAddNoticeSuccess("");
                    } else {
                        view.showError(data.getErrmsg());
                    }
                }
            }
        });
    }


}
