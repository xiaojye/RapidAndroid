package com.jye.rapidandroid.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 描述：MD5工具类
 * <p>
 * 创建人：jye-ixiaojye@163.com
 */
public final class RapidMD5Utils {

    private RapidMD5Utils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * MD5加密-32位小写（改名为strToMD532L）
     *
     * @param str 要加密的字符串
     * @return 加密后的字符串
     */
    @Deprecated
    public static String strToMD5By32L(String str) {
        return strToMD532L(str);
    }

    /**
     * MD5加密-32位小写
     *
     * @param str 要加密的字符串
     * @return 加密后的字符串
     */
    public static String strToMD532L(String str) {
        String reStr = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(str.getBytes());
            StringBuilder stringBuilder = new StringBuilder();
            for (byte b : bytes) {
                int bt = b & 0xff;
                if (bt < 16) {
                    stringBuilder.append(0);
                }
                stringBuilder.append(Integer.toHexString(bt));
            }
            reStr = stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return reStr;
    }

    /**
     * MD5加密-32位大写（改名为strToMD532U）
     *
     * @param str 要加密的字符串
     * @return 加密后的字符串
     */
    @Deprecated
    public static String strToMD5By32U(String str) {
        return strToMD532U(str);
    }

    /**
     * MD5加密-32位大写
     *
     * @param str 要加密的字符串
     * @return 加密后的字符串
     */
    public static String strToMD532U(String str) {
        String reStr = strToMD532L(str);
        if (reStr != null) {
            reStr = reStr.toUpperCase();
        }
        return reStr;
    }


    /**
     * MD5加密-16位小写（改名为strToMD516L）
     *
     * @param str 要加密的字符串
     * @return 加密后的字符串
     */
    @Deprecated
    public static String strToMD5By16L(String str) {
        return strToMD516L(str);
    }

    /**
     * MD5加密-16位小写
     *
     * @param str 要加密的字符串
     * @return 加密后的字符串
     */
    public static String strToMD516L(String str) {
        String reStr = strToMD532L(str);
        if (reStr != null) {
            reStr = reStr.substring(8, 24);
        }
        return reStr;
    }

    /**
     * MD5加密-16位大写（改名为strToMD516U）
     *
     * @param str 要加密的字符串
     * @return 加密后的字符串
     */
    @Deprecated
    public static String strToMD5By16U(String str) {
        return strToMD516U(str);
    }


    /**
     * MD5加密-16位大写
     *
     * @param str 要加密的字符串
     * @return 加密后的字符串
     */
    public static String strToMD516U(String str) {
        String reStr = strToMD532L(str);
        if (reStr != null) {
            reStr = reStr.toUpperCase().substring(8, 24);
        }
        return reStr;
    }

}