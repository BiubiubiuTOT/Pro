package com.ljkj.qxn.wisdomsitepro.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.StringDef;
import android.text.TextUtils;

import com.baidu.ocr.sdk.OCR;
import com.baidu.ocr.sdk.OnResultListener;
import com.baidu.ocr.sdk.exception.OCRError;
import com.baidu.ocr.sdk.model.AccessToken;
import com.baidu.ocr.sdk.model.IDCardParams;
import com.baidu.ocr.sdk.model.IDCardResult;
import com.baidu.ocr.ui.camera.CameraActivity;
import com.baidu.ocr.ui.camera.CameraNativeHelper;
import com.baidu.ocr.ui.camera.CameraView;
import com.ljkj.qxn.wisdomsitepro.WApplication;

import java.io.File;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import cdsp.android.logging.Logger;

/**
 * 类描述：文字识别(身份证)
 * 创建人：lxx
 * 创建时间：2018/6/29
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class OCRHelper {
    private static final String API_KEY = "YbkkI7VeaE5fCti4g9sWTSBI";
    private static final String SECRET_KEY = "6gxN5whUnYAwGmTRchrW3BHXj1IfDc8d";
    public static boolean hasGotToken = false;

    public static void initAccessToken() {
        OCR.getInstance(WApplication.getApplication()).initAccessTokenWithAkSk(new OnResultListener<AccessToken>() {
            @Override
            public void onResult(AccessToken result) {
                String token = result.getAccessToken();
                hasGotToken = true;
                Logger.d("ocr", "token=" + token);
            }

            @Override
            public void onError(OCRError error) {
                error.printStackTrace();
                hasGotToken = false;
            }
        }, WApplication.getApplication(), API_KEY, SECRET_KEY);
    }

    /**
     * 释放OCR内存资源
     */
    public static void releaseOCR() {
        OCR.getInstance(WApplication.getApplication()).release();
        hasGotToken = false;
    }

    /**
     * 进入身份识别页面
     *
     * @param activity    activity
     * @param face        face
     * @param requestCode 请求码
     */
    public static void toIdCardIdentify(Activity activity, @Face String face, int requestCode) {
        Intent intent = new Intent(activity, CameraActivity.class);
        intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH, getSaveFile(activity).getAbsolutePath());
        intent.putExtra(CameraActivity.KEY_NATIVE_ENABLE, true);
        // KEY_NATIVE_MANUAL设置了之后CameraActivity中不再自动初始化和释放模型
        // 请手动使用CameraNativeHelper初始化和释放模型
        // 推荐这样做，可以避免一些activity切换导致的不必要的异常
        intent.putExtra(CameraActivity.KEY_NATIVE_MANUAL, true);
        intent.putExtra(CameraActivity.KEY_CONTENT_TYPE, face);
        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * 初始化本地质量控制模型,释放代码调用{@link #release()}
     *
     * @param context context
     */
    public static void init(Context context) {
        CameraNativeHelper.init(context, OCR.getInstance(context).getLicense(), new CameraNativeHelper.CameraNativeInitCallback() {
            @Override
            public void onError(int errorCode, Throwable e) {
                String msg;
                switch (errorCode) {
                    case CameraView.NATIVE_SOLOAD_FAIL:
                        msg = "加载so失败，请确保apk中存在ui部分的so";
                        break;
                    case CameraView.NATIVE_AUTH_FAIL:
                        msg = "授权本地质量控制token获取失败";
                        break;
                    case CameraView.NATIVE_INIT_FAIL:
                        msg = "本地质量控制";
                        break;
                    default:
                        msg = String.valueOf(errorCode);
                }
                Logger.e("本地质量控制初始化错误，错误原因： " + msg);
            }
        });
    }

    /**
     * 在onDestroy()中调用--->释放本地质量控制模型
     */
    public static void release() {
        CameraNativeHelper.release();
    }

    public static void handleIdCardIdentify(Intent data, OnResultListener<IDCardResult> onResultListener) {
        if (data != null) {
            String contentType = data.getStringExtra(CameraActivity.KEY_CONTENT_TYPE);
            String filePath = getSaveFile(WApplication.getApplication()).getAbsolutePath();
            if (!TextUtils.isEmpty(contentType)) {
                if (CameraActivity.CONTENT_TYPE_ID_CARD_FRONT.equals(contentType)) {
                    recIDCard(IDCardParams.ID_CARD_SIDE_FRONT, filePath, onResultListener);
                } else if (CameraActivity.CONTENT_TYPE_ID_CARD_BACK.equals(contentType)) {
                    recIDCard(IDCardParams.ID_CARD_SIDE_BACK, filePath, onResultListener);
                }
            }
        }
    }

    private static void recIDCard(String idCardSide, String filePath, OnResultListener<IDCardResult> onResultListener) {
        IDCardParams param = new IDCardParams();
        param.setImageFile(new File(filePath));
        // 设置身份证正反面
        param.setIdCardSide(idCardSide);
        // 设置方向检测
        param.setDetectDirection(true);
        // 设置图像参数压缩质量0-100, 越大图像质量越好但是请求时间越长。 不设置则默认值为20
        param.setImageQuality(20);

        OCR.getInstance(WApplication.getApplication()).recognizeIDCard(param, onResultListener);
    }

    private static File getSaveFile(Context context) {
        return new File(context.getFilesDir(), "pic.jpg");
    }

    @StringDef({CameraActivity.CONTENT_TYPE_ID_CARD_FRONT, CameraActivity.CONTENT_TYPE_ID_CARD_BACK})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Face {
    }
}
