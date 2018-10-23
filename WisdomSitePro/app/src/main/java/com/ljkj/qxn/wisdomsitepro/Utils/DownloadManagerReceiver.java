package com.ljkj.qxn.wisdomsitepro.Utils;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.text.TextUtils;

import com.foamtrace.photopicker.FileProviderHelper;

import java.io.File;

/**
 * 类描述：下载广播接收器
 * 创建人：lxx
 * 创建时间：2018/4/11 11:31
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class DownloadManagerReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Cursor cursor = null;
        if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(intent.getAction()) && AppUpdateHelper.downloadId != -1) {
            long id = AppUpdateHelper.downloadId;
            DownloadManager.Query query = new DownloadManager.Query();
            query.setFilterById(id);
            DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
            cursor = downloadManager.query(query);
            if (cursor.moveToFirst()) {
                int status = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS));
                if (status == DownloadManager.STATUS_FAILED) {
                    downloadManager.remove(id);
                } else if (status == DownloadManager.STATUS_SUCCESSFUL) {
                    if (!TextUtils.isEmpty(AppUpdateHelper.downloadPath)) {
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        FileProviderHelper.setIntentDataAndType(context, i, "application/vnd.android.package-archive", new File(AppUpdateHelper.downloadPath), true);
                        context.startActivity(i);
                    }
                }
            }
        }
        if (cursor != null) {
            cursor.close();
        }
    }


}
