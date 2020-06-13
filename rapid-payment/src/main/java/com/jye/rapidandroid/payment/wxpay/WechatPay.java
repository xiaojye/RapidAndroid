package com.jye.rapidandroid.payment.wxpay;

import android.content.Context;
import android.text.TextUtils;

import com.jye.rapidandroid.payment.ErrorCode;
import com.jye.rapidandroid.payment.PayCallback;
import com.jye.rapidandroid.payment.PayStrategy;
import com.tencent.mm.opensdk.constants.Build;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 描述：微信支付
 * <p>
 * 作者：jye-ixiaojye@163.com
 */
public class WechatPay implements PayStrategy {

    private PayCallback mPayCallback;

    private IWXAPI mWXApi;

    private static WechatPay sInstance;

    /**
     * 构造函数
     *
     * @param context Activity上下文对象
     */
    private WechatPay(Context context, String wxAppid) {
        mWXApi = WXAPIFactory.createWXAPI(context, null);
        mWXApi.registerApp(wxAppid);
    }


    /**
     * 初始化（要在支付前调用）
     *
     * @param context 上下文对象
     * @param wxAppid 微信平台应用AppId
     */
    public static WechatPay init(Context context, String wxAppid) {
        if (sInstance == null) {
            sInstance = new WechatPay(context, wxAppid);
        }
        return sInstance;
    }


    /**
     * 获取微信支付实例
     *
     * @return 微信支付实例
     */
    public static WechatPay getInstance() {
        return sInstance;
    }

    public IWXAPI getWXApi() {
        return mWXApi;
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

        // 1、检查是否安装了APP
        if (!checkInstalledWechat()) {
            if (payCallback != null) {
                payCallback.onError(ErrorCode.UNKNOWN_ERROR, "未安装微信或微信版本过低");
            }
            return;
        }

        // 2、参数校验
        if (payParam == null || payParam.equals("")) {
            payCallback.onError(ErrorCode.PARAMETER_NULL, "支付参数为空");
            return;
        }
        JSONObject paramJson;
        try {
            paramJson = new JSONObject(payParam);
            if (TextUtils.isEmpty(paramJson.optString("appid"))) {
                payCallback.onError(ErrorCode.PARAMETER_INVALID, "缺少'appid'参数");
                return;
            }
            if (TextUtils.isEmpty(paramJson.optString("partnerid"))) {
                payCallback.onError(ErrorCode.PARAMETER_INVALID, "缺少'partnerid'参数");
                return;
            }
            if (TextUtils.isEmpty(paramJson.optString("prepayid"))) {
                payCallback.onError(ErrorCode.PARAMETER_INVALID, "缺少'prepayid'参数");
                return;
            }
            if (TextUtils.isEmpty(paramJson.optString("package"))) {
                payCallback.onError(ErrorCode.PARAMETER_INVALID, "缺少'package'参数");
                return;
            }
            if (TextUtils.isEmpty(paramJson.optString("noncestr"))) {
                payCallback.onError(ErrorCode.PARAMETER_INVALID, "缺少'noncestr'参数");
                return;
            }
            if (TextUtils.isEmpty(paramJson.optString("timestamp"))) {
                payCallback.onError(ErrorCode.PARAMETER_INVALID, "缺少'timestamp'参数");
                return;
            }
            if (TextUtils.isEmpty(paramJson.optString("sign"))) {
                payCallback.onError(ErrorCode.PARAMETER_INVALID, "缺少'sign'参数");
                return;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            payCallback.onError(ErrorCode.PARAMETER_INVALID, "支付参数解析出错");
            return;
        }

        // 3、实现支付相关逻辑
        PayReq request = new PayReq();
        request.appId = paramJson.optString("appid");
        request.partnerId = paramJson.optString("partnerid");
        request.prepayId = paramJson.optString("prepayid");
        request.packageValue = paramJson.optString("package");
        request.nonceStr = paramJson.optString("noncestr");
        request.timeStamp = String.valueOf(paramJson.optLong("timestamp"));
        request.sign = paramJson.optString("sign");

        mWXApi.sendReq(request);
    }

    /**
     * 检查是否安装了微信APP
     *
     * @return true：已安装，false：未安装
     */
    private boolean checkInstalledWechat() {
        return mWXApi.isWXAppInstalled() && mWXApi.getWXAppSupportAPI() >= Build.PAY_SUPPORTED_SDK_INT;
    }

    /**
     * 支付回调响应
     *
     * @param errCode 错误码
     * @param errDesc 错误描述
     */
    public void onResp(int errCode, String errDesc) {
        if (mPayCallback == null) {
            return;
        }

        if (errCode == 0) {   //成功
            mPayCallback.onSuccess();
        } else if (errCode == -2) {   //取消
            mPayCallback.onCancel();
        } else {   //错误
            if (TextUtils.isEmpty(errDesc)) {
                errDesc = "支付失败";
            }
            mPayCallback.onError(errCode, errDesc);
        }

        mPayCallback = null;
    }

}
