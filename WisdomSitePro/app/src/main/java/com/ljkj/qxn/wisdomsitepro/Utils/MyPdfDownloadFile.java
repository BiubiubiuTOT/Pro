package com.ljkj.qxn.wisdomsitepro.Utils;

import com.ljkj.qxn.wisdomsitepro.WApplication;
import com.ljkj.qxn.wisdomsitepro.data.Consts;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;

import java.io.File;

import es.voghdev.pdfviewpager.library.remote.DownloadFile;
import es.voghdev.pdfviewpager.library.util.FileUtil;

/**
 * 类描述：PdfDownloadFile
 * 创建人：lxx
 * 创建时间：2018/6/15
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class MyPdfDownloadFile implements DownloadFile {
    private static final String TAG = "PdfDownloadFile";
    private Listener listener;

    public MyPdfDownloadFile(Listener listener) {
        this.listener = listener;
    }

    @Override
    public void download(final String url, String destinationPath) {
        File destFileDir = new File(Consts.Cache.PDF_DIR);
        if (!destFileDir.canWrite()) {
            destFileDir = WApplication.getApplication().getCacheDir();
        }
        String fileName = FileUtil.extractFileNameFromURL(url);

        OkGoHelper.getInstance().downloadFile(url, TAG, new FileCallback(destFileDir.getAbsolutePath(), fileName) {
            @Override
            public void onSuccess(Response<File> response) {
                if (listener != null) {
                    listener.onSuccess(url, response.body().getAbsolutePath());
                }
            }

            @Override
            public void onError(Response<File> response) {
                super.onError(response);
                if (listener != null) {
                    listener.onFailure((Exception) response.getException());
                }
            }

            @Override
            public void downloadProgress(Progress progress) {
                super.downloadProgress(progress);
                if (listener != null) {
                    listener.onProgressUpdate((int) progress.currentSize, (int) progress.totalSize);
                }
            }
        });
    }

    public void release() {
        OkGoHelper.getInstance().cancelTag(TAG);
        listener = null;
    }

}
