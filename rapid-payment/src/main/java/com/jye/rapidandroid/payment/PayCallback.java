package com.jye.rapidandroid.payment;

import androidx.annotation.NonNull;

/**
 * 描述：支付回调接口
 * <p>
 * 作者：jye-ixiaojye@163.com
 */
public interface PayCallback {

    /**
     * 支付成功
     */
    void onSuccess();

    /**
     * 支付失败
     *
     * @param errorCode 错误码
     * @param errorDesc 错误描述
     */
    void onError(int errorCode, @NonNull String errorDesc);

    /**
     * 支付取消
     */
    void onCancel();
}
