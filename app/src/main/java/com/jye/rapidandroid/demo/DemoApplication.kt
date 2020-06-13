package com.jye.rapidandroid.demo

import android.app.Application
import com.jye.rapidandroid.RapidAndroid
import com.jye.rapidandroid.payment.RapidPayment
import com.jye.rapidandroid.payment.PayConfig

/**
 * 描述：[类描述]
 * 创建人：jye-ixiaojye@163.com
 */
class DemoApplication : Application() {
    companion object {
        lateinit var instance: DemoApplication
    }

    override fun onCreate() {
        super.onCreate()
        RapidAndroid.init(this, true)
        RapidPayment.setGlobalConfig(PayConfig().setWxAppKey("123"))
    }

    fun exit() {
        RapidAndroid.getInstance().exitApp()
    }
}