package com.jye.rapidandroid.ui.base

import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.jye.rapidandroid.ui.util.RapidColorUtils
import com.jye.rapidandroid.ui.util.RapidResourceUtils
import com.jye.rapidandroid.ui.util.RapidStatusBarUtils

/**
 * 描述：封装Toolbar功能的Activity基类
 * 创建人：jye-ixiaojye@163.com
 */
abstract class RapidToolbarActivity : RapidBaseActivity() {

    //标题栏对象
    private var mToolbar: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //安装标题栏
        buildToolbar()?.let { setupToolbar(it) }
    }

    /**
     * 获取Toolbar浅色主题布局id
     */
    abstract fun getToolbarLightLayoutId(): Int

    /**
     * 获取Toolbar深色主题布局id
     */
    abstract fun getToolbarDarkLayoutId(): Int

    /**
     * 获取Toolbar
     */
    abstract fun getToolbarViewId(): Int

    /**
     * 获取Toolbar标题（为空时使用系统的标题控件）
     */
    abstract fun getToolbarTitleViewId(): Int?

    /**
     * 获取Toolbar分割线（如果没有分割线则传空）
     */
    abstract fun getToolbarDividerViewId(): Int?

    /**
     * 获取Toolbar默认背景颜色
     */
    @ColorInt
    open fun getToolbarDefaultBgColor(): Int? {
        return null
    }

    /**
     * 构建标题栏配置
     *
     * @return ToolbarBuilder，为null时表示不构建Toolbar
     */
    open fun buildToolbar(): ToolbarBuilder? {
        return ToolbarBuilder()
    }

    /**
     * 安装Toolbar（可以通过重写此方法实现个性化Toolbar）
     *
     * @param toolbarBuilder
     */
    open fun setupToolbar(toolbarBuilder: ToolbarBuilder) {
        //获取Toolbar背景颜色，未设置时首先使用主题色colorPrimary，如果没有主题色则使用白色
        var barColor = toolbarBuilder.getBackgroundColor()
        if (barColor == -1) {
            barColor = getToolbarDefaultBgColor() ?: try {
                ContextCompat.getColor(
                    getContext(),
                    RapidResourceUtils.getColorId(getContext(), "colorPrimary")
                )
            } catch (e: Exception) {
                e.printStackTrace()
                Color.WHITE
            }
        }

        //设置状态栏颜色与Toolbar背景颜色一致
        RapidStatusBarUtils.setStatusBarColor(this, barColor)

        //获取Toolbar布局
        val toolbarLayout = if (RapidColorUtils.isLightColor(barColor)) {
            View.inflate(this, getToolbarLightLayoutId(), null)
        } else {
            View.inflate(this, getToolbarDarkLayoutId(), null)
        }

        mToolbar = toolbarLayout.findViewById(getToolbarViewId())
        //设置Toolbar背景颜色
        mToolbar?.setBackgroundColor(barColor)
        //将Toolbar绑定到Activity
        setSupportActionBar(mToolbar)
        //设置返回按钮是否可用
        supportActionBar?.setDisplayHomeAsUpEnabled(toolbarBuilder.isBackButtonEnable())
        //设置页面标题
        if (getToolbarTitleViewId() != null) {
            supportActionBar?.setDisplayShowTitleEnabled(false)
            val title =
                if (!TextUtils.isEmpty(toolbarBuilder.getTitle())) toolbarBuilder.getTitle() else ""
            mToolbar?.findViewById<TextView>(getToolbarTitleViewId()!!)?.text = title
        } else {
            supportActionBar?.setDisplayShowTitleEnabled(true)
            this.title = toolbarBuilder.getTitle()
        }

        //判断是否显示分割线
        if (getToolbarDividerViewId() != null) {
            if (toolbarBuilder.isDividerLineEnable()) {
                toolbarLayout.findViewById<View>(getToolbarDividerViewId()!!)?.visibility =
                    View.VISIBLE
            } else {
                toolbarLayout.findViewById<View>(getToolbarDividerViewId()!!)?.visibility =
                    View.GONE
            }
        }

        //将Toolbar显示到页面上
        addContentView(
            toolbarLayout,
            ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        )

        if (!toolbarBuilder.isFloatToolbar()) {
            toolbarLayout.post {
                val toolbarLayoutHeight = toolbarLayout.height
                //将内容区域移至toolbar布局下方
                (mContentView?.layoutParams as? FrameLayout.LayoutParams)?.topMargin =
                    toolbarLayoutHeight
            }
        }
    }

    /**
     * 设置Toolbar标题
     *
     * @param title 标题文本
     */
    fun setToolbarTitle(title: String) {
        if (getToolbarTitleViewId() != null) {
            mToolbar?.findViewById<TextView>(getToolbarTitleViewId()!!)?.text = title
        } else {
            this.title = title
        }
    }

    /**
     * Toolbar返回键点击事件
     *
     * @return
     */
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}