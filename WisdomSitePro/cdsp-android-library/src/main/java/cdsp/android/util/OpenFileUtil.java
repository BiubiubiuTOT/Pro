package cdsp.android.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;

import java.io.File;
import java.util.Locale;

/**
 * 打开文件
 * Created by lxx on 2018/3/12.
 */
public class OpenFileUtil {

    /**
     * 打开文件
     *
     * @param context  context
     * @param filePath 文件路径
     * @return intent
     */
    public static @Nullable
    Intent openFile(@NonNull Context context, @NonNull String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            return null;
        }
        //取得扩展名
        String end = file.getName().substring(file.getName().lastIndexOf(".") + 1, file.getName().length()).toLowerCase(Locale.getDefault());
        //依扩展名的类型决定MimeType
        if (end.equals("m4a") || end.equals("mp3") || end.equals("mid") || end.equals("xmf") || end.equals("ogg") || end.equals("wav")) {
            return getAudioFileIntent(context, filePath);
        } else if (end.equals("3gp") || end.equals("mp4")) {
            return getVideoFileIntent(context, filePath);
        } else if (end.equals("jpg") || end.equals("gif") || end.equals("png") || end.equals("jpeg") || end.equals("bmp")) {
            return getImageFileIntent(context, filePath);
        } else if (end.equals("apk")) {
            return getApkFileIntent(context, filePath);
        } else if (end.equals("ppt")) {
            return getPptFileIntent(context, filePath);
        } else if (end.equals("xls")) {
            return getExcelFileIntent(context, filePath);
        } else if (end.equals("doc")) {
            return getWordFileIntent(context, filePath);
        } else if (end.equals("pdf")) {
            return getPdfFileIntent(context, filePath);
        } else if (end.equals("chm")) {
            return getChmFileIntent(context, filePath);
        } else if (end.equals("txt")) {
            return getTextFileIntent(context, filePath);
        } else {
            return getAllIntent(context, filePath);
        }
    }

    //获取一个用于任意文件的intent
    public static Intent getAllIntent(Context context, String filePath) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        Uri uri = getUri(context, intent, new File(filePath));
        intent.setDataAndType(uri, "*/*");
        return intent;
    }

    // 获取一个用于打开APK文件的intent
    public static Intent getApkFileIntent(Context context, String filePath) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        Uri uri = getUri(context, intent, new File(filePath));
        intent.setDataAndType(uri, "application/vnd.android.package-archive");
        return intent;
    }

    // Android获取一个用于打开VIDEO文件的intent
    public static Intent getVideoFileIntent(Context context, String filePath) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("oneshot", 0);
        intent.putExtra("configchange", 0);
        Uri uri = getUri(context, intent, new File(filePath));
        intent.setDataAndType(uri, "video/*");
        return intent;
    }

    // 获取一个用于打开AUDIO文件的intent
    public static Intent getAudioFileIntent(Context context, String filePath) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("oneshot", 0);
        intent.putExtra("configchange", 0);
        Uri uri = getUri(context, intent, new File(filePath));
        intent.setDataAndType(uri, "audio/*");
        return intent;
    }

    // 获取一个用于打开Html文件的intent
    public static Intent getHtmlFileIntent(Context context, String filePath) {
        Uri uri = Uri.parse(filePath).buildUpon().encodedAuthority("com.android.htmlfileprovider").scheme("content").encodedPath(filePath).build();
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setDataAndType(uri, "text/html");
        return intent;
    }

    // 获取一个用于打开图片文件的intent
    public static Intent getImageFileIntent(Context context, String filePath) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = getUri(context, intent, new File(filePath));
        intent.setDataAndType(uri, "image/*");
        return intent;
    }

    //获取一个用于打开PPT文件的intent
    public static Intent getPptFileIntent(Context context, String filePath) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = getUri(context, intent, new File(filePath));
        intent.setDataAndType(uri, "application/vnd.ms-powerpoint");
        return intent;
    }

    // 获取一个用于打开Excel文件的intent
    public static Intent getExcelFileIntent(Context context, String filePath) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = getUri(context, intent, new File(filePath));
        intent.setDataAndType(uri, "application/vnd.ms-excel");
        return intent;
    }

    // 获取一个用于打开Word文件的intent
    public static Intent getWordFileIntent(Context context, String filePath) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = getUri(context, intent, new File(filePath));
        intent.setDataAndType(uri, "application/msword");
        return intent;
    }

    //获取一个用于打开CHM文件的intent
    public static Intent getChmFileIntent(Context context, String filePath) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = getUri(context, intent, new File(filePath));
        intent.setDataAndType(uri, "application/x-chm");
        return intent;
    }

    //获取一个用于打开文本文件的intent
    public static Intent getTextFileIntent(Context context, String filePath) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = getUri(context, intent, new File(filePath));
        intent.setDataAndType(uri, "text/plain");
        return intent;
    }

    // 获取一个用于打开PDF文件的intent
    public static Intent getPdfFileIntent(Context context, String filePath) {

        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = getUri(context, intent, new File(filePath));
        intent.setDataAndType(uri, "application/pdf");
        return intent;
    }

    //获取对应文件的Uri
    private static Uri getUri(Context context, Intent intent, File file) {
        Uri uri;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //判断版本是否在7.0以上
            uri = FileProvider.getUriForFile(context, context.getPackageName() + ".fileprovider", file);
            //对目标应用临时授权该Uri所代表的文件
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        } else {
            uri = Uri.fromFile(file);
        }
        return uri;
    }


}
