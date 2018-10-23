package com.ljkj.qxn.wisdomsitepro.Utils;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;

import com.ljkj.qxn.wisdomsitepro.data.AsyncResult;

import cn.bingoogolapple.qrcode.zxing.QRCodeEncoder;

/**
 * 类描述：二维码生成器
 * 创建人：lxx
 * 创建时间：2018/6/28
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class GenerateQRCodeHelper {


    /**
     * 创建黑色前景色、白色背景色的二维码图片
     *
     * @param content                要生成的二维码图片内容
     * @param size                   图片宽高，单位为px
     * @param onQRCodeCreateListener onQRCodeCreateListener
     */
    public static void createQRCode(final String content, final int size, final OnQRCodeCreateListener onQRCodeCreateListener) {
        new WisdomAsyncTask<Bitmap>() {
            @Override
            protected void runOnBackground(AsyncResult<Bitmap> asyncResult) {
                Bitmap bitmap = QRCodeEncoder.syncEncodeQRCode(content, size);
                asyncResult.setData(bitmap);
            }

            @Override
            protected void runOnUIThread(AsyncResult<Bitmap> asyncResult) {
                onQRCodeCreateListener.callback(asyncResult.getData());
            }
        }.execute();
    }

    /**
     * 创建指定前景色、白色背景色的二维码图片。
     *
     * @param content                要生成的二维码图片内容
     * @param size                   图片宽高，单位为px
     * @param foregroundColor        二维码图片的前景色
     * @param onQRCodeCreateListener onQRCodeCreateListener
     */
    public static void createQRCode(final String content, final int size, final int foregroundColor, final OnQRCodeCreateListener onQRCodeCreateListener) {
        new WisdomAsyncTask<Bitmap>() {
            @Override
            protected void runOnBackground(AsyncResult<Bitmap> asyncResult) {
                Bitmap bitmap = QRCodeEncoder.syncEncodeQRCode(content, size, foregroundColor);
                asyncResult.setData(bitmap);
            }

            @Override
            protected void runOnUIThread(AsyncResult<Bitmap> asyncResult) {
                onQRCodeCreateListener.callback(asyncResult.getData());
            }
        }.execute();
    }

    /**
     * 创建指定前景色、指定背景色、带logo的二维码图片。
     *
     * @param content                要生成的二维码图片内容
     * @param size                   图片宽高，单位为px
     * @param foregroundColor        二维码图片的前景色
     * @param backgroundColor        二维码图片的背景色
     * @param logo                   二维码图片的logo
     * @param onQRCodeCreateListener onQRCodeCreateListener
     */
    public static void createQRCode(final String content, final int size, final int foregroundColor, final int backgroundColor, final Bitmap logo, final OnQRCodeCreateListener onQRCodeCreateListener) {
        new WisdomAsyncTask<Bitmap>() {
            @Override
            protected void runOnBackground(AsyncResult<Bitmap> asyncResult) {
                Bitmap bitmap = QRCodeEncoder.syncEncodeQRCode(content, size, foregroundColor, backgroundColor, logo);
                asyncResult.setData(bitmap);
            }

            @Override
            protected void runOnUIThread(AsyncResult<Bitmap> asyncResult) {
                onQRCodeCreateListener.callback(asyncResult.getData());
            }
        }.execute();
    }

    public interface OnQRCodeCreateListener {
        void callback(@Nullable Bitmap bitmap);
    }
}
