package cdsp.android.util;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;

import java.io.File;

/**
 * 类描述：Image工具类
 * 创建人：mhl
 * 创建时间：2017/9/26 15:52
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class ImageUtils {


    public static final int CROP_REQUEST_CODE = 0X90;

    /**
     * 图片剪裁
     *
     * @param filePath
     */
    public static Intent createCropIntent(Context context, String filePath, String cropPath, String providerAuthor) {

        Uri imageUri;
        Uri outputUri;
        File file = FileUtils.makeFile(cropPath);
        if (file != null) {
            Intent intent = new Intent("com.android.camera.action.CROP");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                imageUri = FileProvider.getUriForFile(context, providerAuthor, new File(filePath));
                outputUri = Uri.fromFile(file);
            } else {
                imageUri = Uri.fromFile(new File(filePath));
                outputUri = Uri.fromFile(file);
            }
            return buildIntent(intent, imageUri, outputUri, 1, 1, 200, 200);
        }
        return null;
    }

    /**
     * 配置剪裁参数
     *
     * @param intent
     * @param resourceUri
     * @param dataUri
     * @param aspectX
     * @param aspectY
     * @param outputX
     * @param outputY
     * @return
     */
    private static Intent buildIntent(Intent intent, Uri resourceUri, Uri dataUri, int aspectX, int aspectY, int outputX, int outputY) {

        intent.setDataAndType(resourceUri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", aspectX);
        intent.putExtra("aspectY", aspectY);
        intent.putExtra("outputX", outputX);
        intent.putExtra("outputY", outputY);
        intent.putExtra("scale", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, dataUri);
        intent.putExtra("return-data", false);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.PNG.toString());
        intent.putExtra("noFaceDetection", true);
        return intent;
    }
}
