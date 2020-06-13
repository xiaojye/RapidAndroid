package com.jye.rapidandroid.payment;

/**
 * 描述：支付策略接口
 * <p>
 * 作者：jye-ixiaojye@163.com
 */
public interface PayStrategy {

    /**
     * 发起支付
     *
     * @param payParam    支付参数（Json字符串）
     * @param payCallback 支付回调接口
     */
    void pay(String payParam, PayCallback payCallback);
}
