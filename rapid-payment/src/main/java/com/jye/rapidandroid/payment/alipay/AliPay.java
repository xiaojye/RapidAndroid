package com.jye.rapidandroid.payment.alipay;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;

import com.alipay.sdk.app.PayTask;
import com.jye.rapidandroid.payment.ErrorCode;
import com.jye.rapidandroid.payment.PayCallback;
import com.jye.rapidandroid.payment.PayStrategy;

import java.util.Map;

/**
 * 描述：支付宝支付
 * <p>
 * 作者：jye-ixiaojye@163.com
 */
public class AliPay implements PayStrategy {

    private Context mContext;
    private PayCallback mPayCallback;

    private static final int SDK_PAY_FLAG = 1;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     * 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        if (mPayCallback != null) {
                            mPayCallback.onSuccess();
                        }
                    } else {
                        //支付取消
                        if (TextUtils.equals(resultStatus, "6001")) {
                            if (mPayCallback != null) {
                                mPayCallback.onCancel();
                            }
                        }
                        //网络连接出错
                        else if (TextUtils.equals(resultStatus, "6002")) {
                            if (mPayCallback != null) {
                                mPayCallback.onError(Integer.parseInt(resultStatus), "网络连接出错");
                            }
                        }
                        //支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        else if (TextUtils.equals(resultStatus, "8000")) {
                            mPayCallback.onError(Integer.parseInt(resultStatus), "支付处理中");
                        }
                        //支付失败
                        else {
                            if (mPayCallback != null) {
                                mPayCallback.onError(ErrorCode.ERROR_PAY, "支付失败");
                            }
                        }
                    }
                    break;
                }
            }
        }
    };

    /**
     * 构造函数
     *
     * @param context Activity上下文对象
     */
    public AliPay(Context context) {
        this.mContext = context;
    }

    /**
     * 发起支付
     *
     * @param payParam    支付参数（Json字符串）
     * @param payCallback 支付回调接口
     */
    @Override
    public void pay(String payParam, PayCallback payCallback) {
        this.mPayCallback = payCallback;

        // 1、参数校验
        if (payParam == null || payParam.equals("")) {
            payCallback.onError(ErrorCode.PARAMETER_NULL, "支付参数为空");
            return;
        }

        // 2、实现支付相关逻辑
        final String orderInfo = payParam;   // 订单信息

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask((Activity) mContext);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Log.i("msp", result.toString());

                // 3、回调支付结果
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();

    }
}
