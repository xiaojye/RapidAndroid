package com.jye.rapidandroid.ui.base

/**
 * 描述：标题栏配置构建类
 * 创建人：jye-ixiaojye@163.com
 */
class ToolbarBuilder {

    //标题
    private var title: String = ""
    //背景颜色（默认值为-1是因为0是透明色，可能会用到）
    private var backgroundColor = -1
    //返回按钮是否可用
    private var backButtonEnable = true
    //分割线是否可用
    private var dividerLineEnable = true
    //是否浮动toolbar
    private var floatToolbar = false

    fun getTitle(): String {
        return title
    }

    fun setTitle(title: String): ToolbarBuilder {
        this.title = title
        return this
    }

    fun getBackgroundColor(): Int {
        return backgroundColor
    }

    fun setBackgroundColor(backgroundColor: Int): ToolbarBuilder {
        this.backgroundColor = backgroundColor
        return this
    }

    fun isBackButtonEnable(): Boolean {
        return backButtonEnable
    }

    fun setBackButtonEnable(backButtonEnable: Boolean): ToolbarBuilder {
        this.backButtonEnable = backButtonEnable
        return this
    }

    fun isDividerLineEnable(): Boolean {
        return dividerLineEnable
    }

    fun setDividerLineEnable(dividerLineEnable: Boolean): ToolbarBuilder {
        this.dividerLineEnable = dividerLineEnable
        return this
    }

    fun isFloatToolbar(): Boolean {
        return floatToolbar
    }

    fun setFloatToolbar(floatToolbar: Boolean): ToolbarBuilder {
        this.floatToolbar = floatToolbar
        return this
    }

}
