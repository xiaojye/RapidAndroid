package com.jye.rapidandroid.util;

import java.util.Collection;
import java.util.regex.Pattern;

/**
 * 描述：验证工具类
 * <p>
 * 创建人：jye-ixiaojye@163.com
 */
public final class RapidCheckUtils {

    private RapidCheckUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 验证手机号码（只验证手机号位数是否等于11位）
     *
     * @param mobile 手机号码
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkMobile(String mobile) {
        return checkMobile(mobile, false);
    }

    /**
     * 验证手机号码
     *
     * @param mobile 手机号码
     * @param strict 是否严格模式验证（默认只验证了手机号位数）
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkMobile(String mobile, boolean strict) {
        if (!strict) {
            //验证手机号位数是否等于11位
            return !checkEmpty(mobile) && Pattern.matches("^[1]\\d{10}$", mobile);
        } else {
            /*
             * 中国电信号段 133、149、153、173、177、180、181、189、199
             * 中国联通号段 130、131、132、145、155、156、166、175、176、185、186
             * 中国移动号段 134(0-8)、135、136、137、138、139、147、150、151、152、157、158、159、178、182、183、184、187、188、198
             * 其他号段
             * 14号段以前为上网卡专属号段，如中国联通的是145，中国移动的是147等等。
             * 虚拟运营商
             * 电信：1700、1701、1702
             * 移动：1703、1705、1706
             * 联通：1704、1707、1708、1709、171
             * 卫星通信：1349
             */
            return !checkEmpty(mobile) && Pattern.matches("^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$", mobile);
        }
    }

    /**
     * 验证Email
     *
     * @param email email地址
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkEmail(String email) {
        //支持'数字/字母/下划线/汉字'，二级域名（格式：zhangsan@xxx.com，zhangsan@xxx.com.cn，xxx代表邮件服务商）
        return !checkEmpty(email) && Pattern.matches("^[\\w\\u4e00-\\u9fa5]+@[\\w\\u4e00-\\u9fa5]+\\.[\\w\\u4e00-\\u9fa5]+(\\.[\\w\\u4e00-\\u9fa5]+)?$", email);
    }

    /**
     * 验证身份证号码（只验证了位数等于15/18位）
     *
     * @param idCard 居民身份证号码
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkIdCard(String idCard) {
        return checkIdCard(idCard, false);
    }


    /**
     * 验证身份证号码
     *
     * @param idCard 居民身份证号码
     * @param strict 是否严格模式验证（默认只验证了位数等于15/18位）
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkIdCard(String idCard, boolean strict) {
        if (!strict) {
            //验证身份证号位数是否为15/18位
            return !checkEmpty(idCard) && Pattern.matches("^[1-9]\\d{13,16}[a-zA-Z0-9]$", idCard);
        } else {
            //使用IDCardUtils进行身份证的验证
            return !checkEmpty(idCard) && RapidIDCardUtils.validateCard(idCard);
        }
    }

    /**
     * 验证数字
     *
     * @param number 数字，包括：整数、小数
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkNumber(String number) {
        return !checkEmpty(number) && Pattern.matches("^-?[0-9]+\\.?[0-9]*$", number);
    }

    /**
     * 验证整数（正整数和负整数）
     *
     * @param numberSigned 整数数字
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkNumberSigned(String numberSigned) {
        return !checkEmpty(numberSigned) && Pattern.matches("^-?[0-9]+$", numberSigned);
    }

    /**
     * 验证小数
     *
     * @param numberDecimal 小数数字
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkNumberDecimal(String numberDecimal) {
        return !checkEmpty(numberDecimal) && Pattern.matches("^-?[0-9]+\\.[0-9]*$", numberDecimal);
    }

    /**
     * 空对象检查
     *
     * @param obj 对象实例
     * @return 为空时返回true
     */
    public static boolean checkNull(Object obj) {
        return obj == null;
    }

    /**
     * 空值检查
     *
     * @param str 字符串
     * @return 为空时返回true
     */
    public static boolean checkEmpty(String str) {
        return checkNull(str) || str.isEmpty();
    }

    /**
     * 空值检查（Collection）
     *
     * @param collection 集合实例
     * @return 为空时返回true
     */
    public static boolean checkEmpty(Collection collection) {
        return checkNull(collection) || collection.isEmpty();
    }

    /**
     * 检查
     *
     * @param regex 正则表达式
     * @param input 输入的字符串
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean check(String regex, String input) {
        return !checkEmpty(input) && Pattern.matches(regex, input);
    }

}