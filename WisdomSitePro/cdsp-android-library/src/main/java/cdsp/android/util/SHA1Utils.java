package cdsp.android.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * ================================================
 * 作    者：loujingying@aliyun.com
 * 版    本：1.0.0
 * 创建日期：2017/8/4
 * 描    述：SHA1编码工具类
 * 修订历史：
 * ================================================
 */

public abstract class SHA1Utils {

    private SHA1Utils(){}

    /**
     *  SHA1 数字签名
     *
     * @param s
     * @return
     */
    public static String SHA1(String s) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();
            return HexUtils.encodeToString(messageDigest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }


}
