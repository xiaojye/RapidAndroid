package com.jye.rapidandroid.ui.ext

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jye.rapidandroid.ui.util.RapidKeyboardUtils

/**
 * =========================================================
 * 描述：RecyclerView相关扩展函数
 * 作者：李杰-ixiaojye@163.com
 * =========================================================
 */

/**
 * RecyclerView滚动定位到item，并使其置顶
 */
fun RecyclerView.moveToPosition(position: Int) {
    if (layoutManager is LinearLayoutManager) {
        this.stopScroll()
        (layoutManager as LinearLayoutManager).scrollToPositionWithOffset(position, 0)
        (layoutManager as LinearLayoutManager).stackFromEnd = false
    }

}

/**
 * 静止,没有滚动                             SCROLL_STATE_IDLE = 0;
 * 正在被外部拖拽,一般为用户正在用手指滚动       SCROLL_STATE_DRAGGING = 1;
 * 自动滚动开始                               SCROLL_STATE_SETTLING = 2;
 */
fun RecyclerView.setScrollCloseKeyboard() {
    this.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                RapidKeyboardUtils.hideSoftInput(recyclerView)
            }
        }
    })
}
