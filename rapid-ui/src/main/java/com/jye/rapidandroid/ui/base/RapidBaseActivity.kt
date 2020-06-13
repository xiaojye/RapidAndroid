package com.jye.rapidandroid.ui.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

/**
 * 描述：Activity基类
 * 创建人：jye-ixiaojye@163.com
 */
abstract class RapidBaseActivity : AppCompatActivity(), RapidBaseView {

    //内容视图
    protected var mContentView: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //设置内容视图
        mContentView = View.inflate(this, getContentLayoutId(), null)
        setContentView(mContentView)

        //处理参数
        intent?.let { handleArgs(it) }
        //初始化视图
        initView()
        //初始化事件
        initEvent()
        //初始化数据
        initData()
    }

    /**
     * 处理参数
     *
     * @param intent Intent
     */
    protected open fun handleArgs(intent: Intent) {}

    /**
     * 返回按钮回调
     */
    override fun onBackPressed() {
        if (!HandleBackUtil.handleBackPress(this)) {
            super.onBackPressed()
        }
    }

    //==============================================================================================

    /**
     * 获取上下文对象
     *
     * @return 上下文对象
     */
    override fun getContext(): Context {
        return this
    }

    /**
     * 跳转界面，传参
     *
     * @param cls    目标Activity
     * @param bundle 数据
     */
    override fun startActivity(cls: Class<*>, bundle: Bundle) {
        val intent = Intent(getContext(), cls)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    /**
     * @param cls    目标Activity
     * @param bundle 数据
     */
    override fun startActivityAndKillSelf(cls: Class<*>, bundle: Bundle) {
        startActivity(cls, bundle)
        finish()
    }

    /**
     * startActivityForResult with bundle
     *
     * @param cls         目标Activity
     * @param requestCode 发送判断值
     * @param bundle      数据
     */
    override fun startActivityForResult(cls: Class<*>, requestCode: Int, bundle: Bundle) {
        val intent = Intent(getContext(), cls)
        intent.putExtras(bundle)
        startActivityForResult(intent, requestCode)
    }

}
