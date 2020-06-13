package com.jye.rapidandroid.ui.base

import android.content.Context
import android.os.Bundle
import android.widget.Toast

/**
 * 描述：Activity/Fragment的通用功能抽象
 * 创建人：jye-ixiaojye@163.com
 */
interface RapidBaseView {

    /**
     * 获取上下文对象
     *
     * @return 上下文对象
     */
    fun getContext(): Context

    /**
     * 获取内容布局ID
     *
     * @return 布局ID
     */
    fun getContentLayoutId(): Int

    /**
     * 初始化视图
     */
    fun initView() {}

    /**
     * 初始化事件
     */
    fun initEvent() {}

    /**
     * 初始化数据
     */
    fun initData() {}

    /**
     * 显示短时间的Toast
     *
     * @param message 提示文字
     */
    fun showShortToast(message: CharSequence) {
        showToast(message, Toast.LENGTH_SHORT)
    }

    /**
     * 显示长时间的Toast
     *
     * @param message 提示文字
     */
    fun showLongToast(message: CharSequence) {
        showToast(message, Toast.LENGTH_LONG)
    }

    /**
     * 显示Toast
     *
     * @param message 提示文字
     * @param duration 显示时间（参考Toast.LENGTH_LONG等）
     */
    fun showToast(message: CharSequence, duration: Int)

    /**
     * 显示加载框
     */
    fun showLoadingDialog() {
        showLoadingDialog("加载中...")
    }

    /**
     * 显示加载框
     *
     * @param message 提示文字
     */
    fun showLoadingDialog(message: CharSequence) {
        showLoadingDialog(message, true)
    }

    /**
     * 显示加载框
     *
     * @param message    提示文字
     * @param cancelable 点击外部是否隐藏加载框
     */
    fun showLoadingDialog(message: CharSequence, cancelable: Boolean)

    /**
     * 取消加载框
     */
    fun cancelLoadingDialog()

    /**
     * 界面跳转
     *
     * @param cls 目标Activity
     */
    fun startActivity(cls: Class<*>) {
        startActivity(cls, Bundle())
    }

    /**
     * 跳转界面并关闭当前界面
     *
     * @param cls 目标Activity
     */
    fun startActivityAndKillSelf(cls: Class<*>) {
        startActivityAndKillSelf(cls, Bundle())
    }

    /**
     * startActivityForResult
     *
     * @param cls         目标Activity
     * @param requestCode 发送判断值
     */
    fun startActivityForResult(cls: Class<*>, requestCode: Int) {
        startActivityForResult(cls, requestCode, Bundle())
    }

    /**
     * 跳转界面，传参
     *
     * @param cls    目标Activity
     * @param bundle 数据
     */
    fun startActivity(cls: Class<*>, bundle: Bundle)

    /**
     * @param cls    目标Activity
     * @param bundle 数据
     */
    fun startActivityAndKillSelf(cls: Class<*>, bundle: Bundle)

    /**
     * startActivityForResult with bundle
     *
     * @param cls         目标Activity
     * @param requestCode 发送判断值
     * @param bundle      数据
     */
    fun startActivityForResult(cls: Class<*>, requestCode: Int, bundle: Bundle)
}
