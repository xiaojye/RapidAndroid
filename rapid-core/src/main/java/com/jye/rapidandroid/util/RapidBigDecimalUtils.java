package com.jye.rapidandroid.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * 描述：精准计算工具类
 * <p>
 * 创建人：jye-ixiaojye@163.com
 */
public class RapidBigDecimalUtils {

    /**
     * 格式化金额字符串
     *
     * @param amount 金额浮点数
     * @param digit  保留小数位数
     * @return 格式化后的金额字符串（保留两位小数）
     */
    public static String format(long amount, int digit) {
        return format(String.valueOf(amount), digit);
    }

    /**
     * 格式化金额字符串
     *
     * @param amount 金额浮点数
     * @param digit  保留小数位数
     * @return 格式化后的金额字符串（保留两位小数）
     */
    public static String format(double amount, int digit) {
        return format(String.valueOf(amount), digit);
    }

    /**
     * 格式化金额字符串
     *
     * @param amount 金额字符串
     * @param digit  保留小数位数
     * @return 格式化后的金额字符串（保留两位小数）
     */
    public static String format(String amount, int digit) {
        return format(amount, digit, RoundingMode.FLOOR);
    }

    /**
     * 格式化金额字符串
     *
     * @param amount       金额字符串
     * @param digit        保留小数位数
     * @param roundingMode 舍入模式（默认ROUND_FLOOR，截断）
     * @return 格式化后的金额字符串（保留两位小数）
     */
    public static String format(String amount, int digit, RoundingMode roundingMode) {
        StringBuilder pattern = new StringBuilder("0.");
        for (int i = 0; i < digit; i++) {
            pattern.append("0");
        }
        try {
            DecimalFormat df = new DecimalFormat(pattern.toString());
            df.setRoundingMode(roundingMode);
            return df.format(Double.parseDouble(amount));
        } catch (Exception e) {
            e.printStackTrace();
            return pattern.toString();
        }
    }

    /**
     * 加法
     */
    public static BigDecimal add(long arg1, long arg2) {
        return add(String.valueOf(arg1), String.valueOf(arg2));
    }

    /**
     * 加法
     */
    public static BigDecimal add(double arg1, double arg2) {
        return add(String.valueOf(arg1), String.valueOf(arg2));
    }

    /**
     * 加法
     */
    public static BigDecimal add(String arg1, String arg2) {
        if (!RapidCheckUtils.checkNumber(arg1)) {
            arg1 = "0.0";
        }
        if (!RapidCheckUtils.checkNumber(arg2)) {
            arg2 = "0.0";
        }
        return add(new BigDecimal(arg1), new BigDecimal(arg2));
    }

    /**
     * 加法
     */
    public static BigDecimal add(BigDecimal arg1, BigDecimal arg2) {
        return arg1.add(arg2);
    }

    /**
     * 减法
     */
    public static BigDecimal sub(long arg1, long arg2) {
        return sub(String.valueOf(arg1), String.valueOf(arg2));
    }

    /**
     * 减法
     */
    public static BigDecimal sub(double arg1, double arg2) {
        return sub(String.valueOf(arg1), String.valueOf(arg2));
    }

    /**
     * 减法
     */
    public static BigDecimal sub(String arg1, String arg2) {
        if (!RapidCheckUtils.checkNumber(arg1)) {
            arg1 = "0.0";
        }
        if (!RapidCheckUtils.checkNumber(arg2)) {
            arg2 = "0.0";
        }
        return sub(new BigDecimal(arg1), new BigDecimal(arg2));
    }

    /**
     * 减法
     */
    public static BigDecimal sub(BigDecimal arg1, BigDecimal arg2) {
        return arg1.subtract(arg2);
    }

    /**
     * 乘法
     */
    public static BigDecimal mul(long arg1, long arg2) {
        return mul(String.valueOf(arg1), String.valueOf(arg2));
    }

    /**
     * 乘法
     */
    public static BigDecimal mul(double arg1, double arg2) {
        return mul(String.valueOf(arg1), String.valueOf(arg2));
    }

    /**
     * 乘法
     */
    public static BigDecimal mul(String arg1, String arg2) {
        if (!RapidCheckUtils.checkNumber(arg1)) {
            arg1 = "0.0";
        }
        if (!RapidCheckUtils.checkNumber(arg2)) {
            arg2 = "0.0";
        }
        return mul(new BigDecimal(arg1), new BigDecimal(arg2));
    }

    /**
     * 乘法
     */
    public static BigDecimal mul(BigDecimal arg1, BigDecimal arg2) {
        return arg1.multiply(arg2);
    }

    /**
     * 除法
     */
    public static BigDecimal divide(long arg1, long arg2, int scale) {
        return divide(String.valueOf(arg1), String.valueOf(arg2), scale);
    }

    /**
     * 除法
     */
    public static BigDecimal divide(double arg1, double arg2, int scale) {
        return divide(String.valueOf(arg1), String.valueOf(arg2), scale);
    }

    /**
     * 除法
     */
    public static BigDecimal divide(String arg1, String arg2, int scale) {
        if (!RapidCheckUtils.checkNumber(arg1)) {
            arg1 = "0.0";
        }
        if (!RapidCheckUtils.checkNumber(arg2)) {
            arg2 = "0.0";
        }
        return divide(new BigDecimal(arg1), new BigDecimal(arg2), scale);
    }

    /**
     * 除法
     */
    public static BigDecimal divide(BigDecimal arg1, BigDecimal arg2, int scale) {
        return arg1.divide(arg2, scale, RoundingMode.HALF_UP);
    }

    /**
     * 除法
     */
    public static BigDecimal divide(BigDecimal arg1, BigDecimal arg2, int scale, int roundingMode) {
        return arg1.divide(arg2, scale, roundingMode);
    }

    /**
     * 去除末尾多余的0【如：1.100=1.1，1.00=1，1.=1】
     *
     * @param val 需要精简的数字
     * @return 精简后的数字字符串
     */
    public static String stripTrailingZeros(long val) {
        return stripTrailingZeros(String.valueOf(val));
    }

    /**
     * 去除末尾多余的0【如：1.100=1.1，1.00=1，1.=1】
     *
     * @param val 需要精简的数字
     * @return 精简后的数字字符串
     */
    public static String stripTrailingZeros(double val) {
        return stripTrailingZeros(String.valueOf(val));
    }

    /**
     * 去除末尾多余的0【如：1.100=1.1，1.00=1，1.=1】
     *
     * @param val 需要精简的数字
     * @return 精简后的数字字符串
     */
    public static String stripTrailingZeros(String val) {
        //使用正则替换方式去除
        if (val.indexOf(".") > 0) {
            val = val.replaceAll("0+?$", "");// 去掉多余的0
            val = val.replaceAll("[.]$", "");// 如最后一位是.则去掉
        }
        return val;
    }

    /**
     * 去除末尾多余的0【如：1.100=1.1，1.00=1，1.=1】
     *
     * @param val 需要精简的数字
     * @return 精简后的数字字符串
     */
    public static String stripTrailingZeros(BigDecimal val) {
        return val.stripTrailingZeros().toPlainString();
    }
}