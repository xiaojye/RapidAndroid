package com.jye.rapidandroid.util;

import java.util.Random;

/**
 * 描述：随机工具类
 * <p>
 * 创建人：jye-ixiaojye@163.com
 */
public final class RapidRandomUtils {

    //数字和字母
    private static final String NUMBERS_AND_LETTERS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    //纯数字
    private static final String NUMBERS = "0123456789";
    //纯字母
    private static final String LETTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    //字母大写
    private static final String CAPITAL_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    //字母小写
    private static final String LOWER_CASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";

    private RapidRandomUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 获取一个固定长度的随机字符串，它是数字、小写/大写字母的混合
     *
     * @param length 随机字符串长度
     * @return 随机字符串
     * @see RapidRandomUtils#getRandom(String source, int length)
     */
    public static String getRandomNumbersAndLetters(int length) {
        return getRandom(NUMBERS_AND_LETTERS, length);
    }

    /**
     * 得到一个固定长度的随机字符串，它是纯数字
     *
     * @param length 随机字符串长度
     * @return 随机字符串
     * @see RapidRandomUtils#getRandom(String source, int length)
     */
    public static String getRandomNumbers(int length) {
        return getRandom(NUMBERS, length);
    }

    /**
     * 得到一个固定长度的随机字符串，它是纯字母（包含大小写）
     *
     * @param length 随机字符串长度
     * @return 随机字符串
     * @see RapidRandomUtils#getRandom(String source, int length)
     */
    public static String getRandomLetters(int length) {
        return getRandom(LETTERS, length);
    }

    /**
     * 得到一个固定长度的随机字符串，它是纯字母（大写）
     *
     * @param length 随机字符串长度
     * @return 随机字符串
     * @see RapidRandomUtils#getRandom(String source, int length)
     */
    public static String getRandomLettersUpper(int length) {
        return getRandom(CAPITAL_LETTERS, length);
    }

    /**
     * 得到一个固定长度的随机字符串，它是纯字母（大写）
     *
     * @param length 随机字符串长度
     * @return 随机字符串
     * @see RapidRandomUtils#getRandom(String source, int length)
     */
    public static String getRandomLettersLower(int length) {
        return getRandom(LOWER_CASE_LETTERS, length);
    }

    /**
     * 获取固定长度的随机字符串，它是source中字符的混合
     *
     * @param source 指定随机内容
     * @param length 随机字符串长度
     * @return 当sourceChar为null/empty返回null
     */
    public static String getRandom(String source, int length) {
        return source == null ? null : getRandom(source.toCharArray(), length);
    }

    /**
     * 获取固定长度的随机字符串，它是sourceChar中字符的混合
     *
     * @param sourceChar 指定随机内容
     * @param length     随机字符串长度
     * @return 当sourceChar为null/empty返回null，当length小于0返回null
     */
    public static String getRandom(char[] sourceChar, int length) {
        if (sourceChar == null || sourceChar.length == 0 || length < 0) {
            return null;
        }

        StringBuilder str = new StringBuilder(length);
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            str.append(sourceChar[random.nextInt(sourceChar.length)]);
        }
        return str.toString();
    }

    /**
     * 获取一个0到max之间的数字随机数
     *
     * @param max 最大数
     * @return 当max大于0时返回0到max之间的数字随机数，反之返回0
     */
    public static int getRandom(int max) {
        return getRandom(0, max);
    }

    /**
     * 获取一个min到max之间的数字随机数
     *
     * @param min 最小数
     * @param max 最大数
     * @return 当max大于min时返回min到max之间的数字随机数，当max等于min时返回min，当max小于min时返回0
     */
    public static int getRandom(int min, int max) {
        if (min > max) {
            return 0;
        }
        if (min == max) {
            return min;
        }
        return min + new Random().nextInt(max - min);
    }
}
