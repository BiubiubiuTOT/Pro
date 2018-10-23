package cdsp.android.util;

import java.util.Random;

/**
 * ================================================
 * 作    者：loujingying@aliyun.com
 * 版    本：1.0.0
 * 创建日期：2017/8/4
 * 描    述：随机数工具类
 * 修订历史：
 * ================================================
 */

public abstract class RandomUtils {

    /**
     * 返回随机数
     *
     * @return
     */
    public static int randomInt() {
        Random random = new Random();
        return random.nextInt();
    }

    /**
     * 返回随机数
     *
     * @param n
     * @return
     */
    public static int randomInt(int n) {
        Random random = new Random();
        return random.nextInt(n);
    }

    /**
     * 返回随机数
     *
     * @param min
     * @param max
     * @return
     */
    public static int randomInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max) % (max - min + 1) + min;
    }

    /**
     * 返回系统时间相关随机数
     *
     * @param n
     * @return
     */
    public static int randomInt2(int n) {
        return (int) (System.currentTimeMillis() % n);
    }

    /**
     * 返回随机浮点数
     *
     * @return
     */
    public static float randomFloat() {
        Random random = new Random();
        return random.nextFloat();
    }

    /**
     * 返回随机double数
     *
     * @return
     */
    public static double randomDouble() {
        Random random = new Random();
        return random.nextDouble();
    }

    /**
     * 返回随机long数
     *
     * @return
     */
    public static long randomLong() {
        Random random = new Random();
        return random.nextLong();
    }


    /**
     * Get a random string
     * 得到一个随机字符串
     *
     * @param source
     * @param length
     * @return
     */
    public static String randomString(String source, int length) {
        return StringUtils.isEmpty(source) ? null : randomString(source.toCharArray(), length);
    }

    /**
     * 得到一个随机字符串
     *
     * @param sourceChar
     * @param length
     * @return
     */
    public static String randomString(char[] sourceChar, int length) {
        if (sourceChar == null || sourceChar.length == 0 || length < 0) {
            return null;
        }
        StringBuilder builder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            builder.append(sourceChar[randomInt(sourceChar.length)]);
        }
        return builder.toString();
    }

    /**
     * 重新排序数组
     *
     * @param intArray
     * @return
     */
    public static int[] shuffle(int[] intArray) {
        if (intArray == null) {
            return null;
        }
        return shuffle(intArray, intArray.length);
    }

    /**
     * 重新排序数组
     *
     * @param intArray
     * @param shuffleCount
     * @return
     */
    public static int[] shuffle(int[] intArray, int shuffleCount) {
        int length;
        if (intArray == null || shuffleCount < 0 || (length = intArray.length) < shuffleCount) {
            return null;
        }

        int[] out = new int[shuffleCount];
        for (int i = 1; i <= shuffleCount; i++) {
            int random = randomInt(length - i);
            out[i - 1] = intArray[random];
            int temp = intArray[length - i];
            intArray[length - i] = intArray[random];
            intArray[random] = temp;
        }
        return out;
    }

    /**
     * 生成随机文字(中文汉字)
     *
     * @return
     */
    public static char generateRandomChar() {
        String str = "";
        int hightPos;
        int lowPos;
        Random random = new Random();
        hightPos = (176 + Math.abs(random.nextInt(39)));
        lowPos = (161 + Math.abs(random.nextInt(93)));

        byte[] b = new byte[2];
        b[0] = Integer.valueOf(hightPos).byteValue();
        b[1] = Integer.valueOf(lowPos).byteValue();
        try {
            str = new String(b, "GBK");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str.charAt(0);
    }

}
