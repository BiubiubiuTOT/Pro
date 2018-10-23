package com.ljkj.qxn.wisdomsitepro.Utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;

import com.ljkj.qxn.wisdomsitepro.data.Consts;
import com.ljkj.qxn.wisdomsitepro.data.PermissionConstant;
import com.ljkj.qxn.wisdomsitepro.data.VersionInfo;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;

import java.io.File;
import java.util.List;

import cdsp.android.util.ToastUtils;

/**
 * 类描述：app更新帮助类
 * 创建人：lxx
 * 创建时间：2018/4/10 13:31
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class AppUpdateHelper {

    public static long downloadId = -1;
    public static String downloadPath;

    public static void showUpdateDialog(final Activity activity, final VersionInfo versionInfo) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(activity)
                .setTitle("发现新版本V" + versionInfo.getVersionName())
                .setMessage(versionInfo.getUpdateDetail())
                .setCancelable(false)
                .setPositiveButton("更新", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
//                            downloadApk(context, versionInfo.getInstallPackageUrl());
                            startDownload(activity, versionInfo.getInstallPackageUrl());
                        } catch (Exception e) {
                            e.printStackTrace();
                            ToastUtils.showShort("更新失败");
                        }
                    }
                });
        if (!versionInfo.isForceUpdate()) {
            builder.setNegativeButton("忽略", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
        }
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private static void startDownload(final Activity activity, final String url) {
        AndPermission.with(activity)
                .runtime()
                .permission(PermissionConstant.PERMISSIONS_OF_FILE)
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> permissions) {
                        downloadApk(activity, url);
                    }
                })
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> permissions) {
                        ToastUtils.showShort("用户已禁止读写权限");
                        downloadFromBrowser(activity, url);
                    }
                })
                .start();

    }

    private static void downloadApk(Context context, String url) {
        try {
            DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
            Uri uri = Uri.parse(url);
            DownloadManager.Request request = new DownloadManager.Request(uri);
            request.setVisibleInDownloadsUi(true);
            request.setMimeType("application/vnd.android.package-archive");
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            request.setTitle("");
            downloadPath = Consts.Cache.SDCardRoot + File.separator + Consts.Cache.FILE_PATH + File.separator + "WisdomSitePro.apk";
            File file = new File(downloadPath);
            if (file.exists()) {
                file.delete();
            }
            Uri fileUri = Uri.parse("file://" + downloadPath);
            request.setDestinationUri(fileUri);
            downloadId = downloadManager.enqueue(request);
        } catch (Exception e) {
            e.printStackTrace();
            downloadFromBrowser(context, url);
        }
    }

    private static void downloadFromBrowser(Context context, String url) {
        try {
            Uri uri = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            ToastUtils.showShort("更新失败");
        }

    }

}
