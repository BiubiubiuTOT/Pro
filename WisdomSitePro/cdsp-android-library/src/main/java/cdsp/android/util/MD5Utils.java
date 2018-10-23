package cdsp.android.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import cdsp.android.constant.Consts;
import cdsp.android.logging.Logger;

/**
 * ================================================
 * 作    者：loujingying@aliyun.com
 * 版    本：1.0.0
 * 创建日期：2017/8/4
 * 描    述：MD5工具类
 * 修订历史：
 * ================================================
 */

public abstract class MD5Utils {

    private static final String TAG = "MD5Utils";

    /**
     * 获取MD5消息摘要
     *
     * @param data 源数据
     * @return MD5消息摘要
     */
    public static byte[] getMD5(byte[] data) {
        byte[] md5 = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md5 = md.digest(data);
        } catch (Exception e) {
        }
        return md5;
    }

    /**
     * MD5字符串
     *
     * @param str
     * @return
     */
    public static String getMD5Str(String str) {
        MessageDigest messageDigest = null;

        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            if (!StringUtils.isEmpty(str)) {
                messageDigest.update(str.getBytes(Consts.DEFAULT_ENCODING));
            }
        } catch (NoSuchAlgorithmException e) {
            Logger.e(TAG, e.getMessage());
        } catch (UnsupportedEncodingException e) {
            Logger.e(TAG, e.getMessage());
        }

        byte[] byteArray = messageDigest.digest();

        StringBuffer md5StrBuff = new StringBuffer();

        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
            else
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
        }
        return md5StrBuff.toString().toUpperCase();
    }

    /**
     * MD5字符串
     *
     * @param data
     * @return
     */
    public static String getMD5Str(byte[] data) {
        MessageDigest messageDigest = null;

        try {
            messageDigest = MessageDigest.getInstance("MD5");

            messageDigest.reset();

            messageDigest.update(data);
        } catch (NoSuchAlgorithmException e) {
            // LogUtil.e("NoSuchAlgorithmException = " + e.toString());
        }

        byte[] byteArray = messageDigest.digest();

        StringBuffer md5StrBuff = new StringBuffer();

        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
            else
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
        }
        return md5StrBuff.toString().toUpperCase();
    }


    /**
     * 获取文件数据的MD5摘要信息
     *
     * @param f
     * @return
     */
    public static String encodeMD5(File f) {
        if (!f.isFile()) {
            return null;
        }
        MessageDigest digest = null;
        byte buffer[] = new byte[1024];
        int len;
        try {
            digest = MessageDigest.getInstance("MD5");
            FileInputStream in = new FileInputStream(f);
            while ((len = in.read(buffer, 0, 1024)) != -1) {
                digest.update(buffer, 0, len);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        BigInteger bigInt = new BigInteger(1, digest.digest());
        return bigInt.toString(16);
    }


}
