package com.jye.rapidandroid.payment;

import android.content.Context;
import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.jye.rapidandroid.payment.alipay.AliPay;
import com.jye.rapidandroid.payment.wxpay.WechatPay;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 描述：多渠道支付统一操作对象
 * 可通过“new”创建支付请求，也可根据Builder自己灵活配置
 * <p>
 * 作者：jye-ixiaojye@163.com
 */
public final class RapidPayment {

    private PayStrategy mPayStrategy;

    private static PayConfig mGlobalConfig;

    /**
     * 设置全局配置
     *
     * @param config 全局默认配置
     */
    public static void setGlobalConfig(PayConfig config) {
        mGlobalConfig = config;
    }

    /**
     * 构造函数
     *
     * @param payChannel 支付渠道
     */
    public RapidPayment(@NonNull Context context, @NonNull PayChannel payChannel) {
        switch (payChannel) {
            case ALIPAY:
                mPayStrategy = new AliPay(context);
                break;
            case WXPAY:
                if (mGlobalConfig == null || TextUtils.isEmpty(mGlobalConfig.getWxAppKey())) {
                    throw new NullPointerException("请先调用 RapidPayment.setGlobalConfig(PayConfig.setWxAppKey()) 来设置微信支付AppKey！");
                }
                mPayStrategy = WechatPay.init(context, mGlobalConfig.getWxAppKey());
                break;
        }
    }

    /**
     * 发起支付
     *
     * @param payParam    支付参数
     * @param payCallback 支付回调接口
     */
    public void doPay(@NonNull String payParam, @NonNull PayCallback payCallback) {
        mPayStrategy.pay(payParam, payCallback);
    }

    /**
     * 支付构建器抽象类
     */
    private static abstract class PayBuilder {
        Context context;

        PayBuilder(@NonNull Context context) {
            this.context = context;
        }

        public abstract void doPay(@NonNull PayCallback callback);
    }

    /**
     * 支付宝支付构建器
     */
    public static class AliPayBuilder extends PayBuilder {
        String orderInfo;

        public AliPayBuilder(@NonNull Context context) {
            super(context);
        }

        public AliPayBuilder setOrderInfo(@NonNull String orderInfo) {
            this.orderInfo = orderInfo;
            return this;
        }

        @Override
        public void doPay(@NonNull PayCallback callback) {
            RapidPayment payment = new RapidPayment(context, PayChannel.ALIPAY);
            payment.doPay(orderInfo, callback);
        }
    }

    /**
     * 微信支付构建器
     */
    public static class WxPayBuilder extends PayBuilder {
        JSONObject payParamsJsonObj;

        public WxPayBuilder(@NonNull Context context) {
            super(context);
            payParamsJsonObj = new JSONObject();
        }

        public WxPayBuilder setPayParams(String payParams) {
            try {
                payParamsJsonObj = new JSONObject(payParams);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return this;
        }

        public WxPayBuilder setAppId(String appId) {
            try {
                payParamsJsonObj.put("appId", appId);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return this;
        }

        public WxPayBuilder setPartnerId(String partnerId) {
            try {
                payParamsJsonObj.put("partnerId", partnerId);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return this;
        }

        public WxPayBuilder setPrepayId(String prepayId) {
            try {
                payParamsJsonObj.put("prepayId", prepayId);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return this;
        }

        public WxPayBuilder setPackageValue(String packageValue) {
            try {
                payParamsJsonObj.put("packageValue", packageValue);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return this;
        }

        public WxPayBuilder setNonceStr(String nonceStr) {
            try {
                payParamsJsonObj.put("nonceStr", nonceStr);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return this;
        }

        public WxPayBuilder setTimeStamp(String timeStamp) {
            try {
                payParamsJsonObj.put("timeStamp", timeStamp);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return this;
        }

        public WxPayBuilder setSign(String sign) {
            try {
                payParamsJsonObj.put(sign, sign);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return this;
        }

        @Override
        public void doPay(@NonNull PayCallback callback) {
            RapidPayment payment = new RapidPayment(context, PayChannel.WXPAY);
            payment.doPay(payParamsJsonObj.toString(), callback);
        }
    }

}
