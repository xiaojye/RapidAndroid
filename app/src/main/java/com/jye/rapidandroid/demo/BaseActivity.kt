package com.jye.rapidandroid.demo

import android.app.ProgressDialog
import android.graphics.Color
import android.view.Gravity
import com.jye.rapidandroid.ui.base.RapidToolbarActivity
import com.jye.rapidandroid.util.RapidToastUtils

/**
 * 描述：[类描述]
 * 创建人：jye-ixiaojye@163.com
 */
abstract class BaseActivity : RapidToolbarActivity() {

    private var mLoadingDialog: ProgressDialog? = null

    override fun getToolbarLightLayoutId(): Int {
        return R.layout.common_toolbar_light_layout
    }

    override fun getToolbarDarkLayoutId(): Int {
        return R.layout.common_toolbar_dark_layout
    }

    override fun getToolbarViewId(): Int {
        return R.id.toolbar
    }

    override fun getToolbarTitleViewId(): Int? {
        return R.id.toolbar_title_tv
    }

    override fun getToolbarDividerViewId(): Int? {
        return R.id.toolbar_divider
    }

    override fun getToolbarDefaultBgColor(): Int? {
        return Color.WHITE
    }

    override fun showToast(message: CharSequence, duration: Int) {
        RapidToastUtils.customToastGravity(getContext(), message, duration, Gravity.CENTER, 0, 0)
    }

    override fun showLoadingDialog(message: CharSequence, cancelable: Boolean) {
        mLoadingDialog = ProgressDialog(getContext())
        mLoadingDialog?.setMessage(message)
        mLoadingDialog?.setCancelable(cancelable)
        mLoadingDialog?.show()
    }

    override fun cancelLoadingDialog() {
        mLoadingDialog?.cancel()
        mLoadingDialog = null
    }
}