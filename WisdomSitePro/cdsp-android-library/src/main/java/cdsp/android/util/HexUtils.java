package cdsp.android.util;

import cdsp.android.constant.Consts;

/**
 * ================================================
 * 作    者：loujingying@aliyun.com
 * 版    本：1.0.0
 * 创建日期：2017/9/12
 * 描    述：十六进制编码工具类
 * 修订历史：
 * ================================================
 */

public class HexUtils {

    private static final char[] HEX = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

    /**
     * 字符串加密
     *
     * @param bytes
     * @return
     */
    public static String encodeToString(byte[] bytes) {
        if (bytes.length == 0) {
            return "";
        }
        int holder = 0;
        char[] chars = new char[bytes.length * 2];
        for (int i = 0; i < bytes.length; i++) {
            holder = (bytes[i] & 0xf0) >> 4;
            chars[i * 2] = HEX[holder];
            holder = bytes[i] & 0x0f;
            chars[(i * 2) + 1] = HEX[holder];
        }
        return new String(chars);
    }

    /**
     * 二进制加密
     *
     * @param bytes
     * @return
     */
    public static char[] encode(byte[] bytes) {
        final int nBytes = bytes.length;
        char[] result = new char[2*nBytes];

        int j = 0;
        for (int i=0; i < nBytes; i++) {
            // Char for top 4 bits
            result[j++] = HEX[(0xF0 & bytes[i]) >>> 4 ];
            // Bottom 4
            result[j++] = HEX[(0x0F & bytes[i])];
        }
        return result;
    }

    /**
     * 字符解密
     *
     * @param s
     * @return
     */
    public static byte[] decode(CharSequence s) {
        int nChars = s.length();

        if (nChars % 2 != 0) {
            throw new IllegalArgumentException("Hex-encoded string must have an even number of characters");
        }

        byte[] result = new byte[nChars / 2];

        for (int i = 0; i < nChars; i += 2) {
            int msb = Character.digit(s.charAt(i), 16);
            int lsb = Character.digit(s.charAt(i+1), 16);

            if (msb < 0 || lsb < 0) {
                throw new IllegalArgumentException("Non-hex character in input: " + s);
            }
            result[i / 2] = (byte) ((msb << 4) | lsb);
        }
        return result;
    }

    /**
     * 字符串解密
     *
     * @param hex
     * @return
     */
    public static String decodeToString(String hex) {
        // A null string returns an empty array
        if (hex == null || hex.length() == 0) {
            return Consts.EMPTY;
        } else if (hex.length() < 3) {
            byte[] ret = new byte[] { (byte) (Integer.parseInt(hex, 16) & 0xff) };
            return String.valueOf(ret);
        }
        // Adjust accordingly for odd-length strings
        int count = hex.length();
        int nibble = 0;
        if (count % 2 != 0) {
            count++;
            nibble = 1;
        }
        byte[] buf = new byte[count / 2];
        char c = 0;
        int holder = 0;
        int pos = 0;
        for (int i = 0; i < buf.length; i++) {
            for (int z = 0; z < 2 && pos < hex.length(); z++) {
                c = hex.charAt(pos++);
                if (c >= 'A' && c <= 'F') {
                    c -= 55;
                } else if (c >= '0' && c <= '9') {
                    c -= 48;
                } else if (c >= 'a' && c <= 'f') {
                    c -= 87;
                }
                if (nibble == 0) {
                    holder = c << 4;
                } else {
                    holder |= c;
                    buf[i] = (byte) holder;
                }
                nibble = 1 - nibble;
            }
        }
        return new String(buf);
    }


}
