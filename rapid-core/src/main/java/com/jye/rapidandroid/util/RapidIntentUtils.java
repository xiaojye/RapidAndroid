package com.jye.rapidandroid.util;

import android.content.Intent;
import android.net.Uri;

/**
 * 描述：Intent相关工具类
 * <p>
 * 创建人：jye-ixiaojye@163.com
 */
public final class RapidIntentUtils {

    private RapidIntentUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }


    /**
     * 调用浏览器打开网页的Intent
     * <p>
     * param url - 网址：例如，http://www.baidu.com
     * return
     */
    public static Intent getBrowserIntent(String url) {
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        return intent;
    }

    /**
     * 跳转到拨号面板的Intent
     * <p>
     *
     * @param phoneNumber 电话号码
     * @return 对应的Intent对象
     */
    public static Intent getDialIntent(String phoneNumber) {
        Uri uri = Uri.parse("tel:" + phoneNumber);
        Intent intent = new Intent(Intent.ACTION_DIAL, uri);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    /**
     * 直接拨打电话的Intent
     * <p>
     *
     * @param phoneNumber 电话号码
     * @return 对应的Intent对象
     */
    public static Intent getCallIntent(String phoneNumber) {
        Uri uri = Uri.parse("tel:" + phoneNumber);
        Intent intent = new Intent(Intent.ACTION_CALL, uri);
        return intent;
    }

    /**
     * 打开短信程序，发送短信的Intent
     * <p>
     *
     * @param phoneNumber 电话号码
     * @param smsBody     短信内容文本
     * @return 对应的Intent对象
     */
    public static Intent getSMSIntent(String phoneNumber, String smsBody) {
        Uri uri = Uri.parse("smsto:" + phoneNumber);
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        intent.putExtra("sms_body", smsBody);
        return intent;
    }

    /**
     * 分享-文本的Intent
     *
     * @param title   标题
     * @param content 内容
     * @return 对应的Intent对象
     */
    public static Intent getShareTextIntent(String title, String content) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, content);
        intent = Intent.createChooser(intent, title);
        return Intent.createChooser(intent,"选择分享应用");
    }

}
