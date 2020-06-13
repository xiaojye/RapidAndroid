package com.jye.rapidandroid.ui.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment

/**
 * 描述：Fragment基类
 * 创建人：jye-ixiaojye@163.com
 */
abstract class RapidBaseFragment : Fragment(), RapidBaseView, HandleBackInterface {

    //内容视图
    protected var mContentView: View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (mContentView == null) {
            //获取内容视图
            mContentView = inflater.inflate(getContentLayoutId(), null)
        } else {
            if (mContentView!!.parent != null) {
                val parent = mContentView!!.parent as ViewGroup
                parent.removeView(mContentView)
            }
        }
        return mContentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //处理参数
        arguments?.let { handleArgs(it) }
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
     * @param args getArguments()
     */
    protected open fun handleArgs(args: Bundle) {}

    /**
     * 返回按钮回调
     */
    override fun onBackPressed(): Boolean {
        return HandleBackUtil.handleBackPress(this)
    }

    /**
     * 根据id获取视图
     *
     * @param id 视图id
     */
    fun <T : View> findViewById(@IdRes id: Int): T {
        return mContentView!!.findViewById(id)
    }

    //==============================================================================================

    /**
     * 获取上下文对象
     *
     * @return 上下文对象
     */
    override fun getContext(): Context {
        return activity!!
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
        activity?.finish()
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
