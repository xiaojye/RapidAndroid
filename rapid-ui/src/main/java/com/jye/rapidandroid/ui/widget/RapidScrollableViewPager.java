package com.jye.rapidandroid.ui.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.viewpager.widget.ViewPager;

public class RapidScrollableViewPager extends ViewPager {

    //是否可以左右滑动
    private boolean mScrollable = true;

    /**
     * 设置左右滑动是否可用
     *
     * @param scrollable true-可滑动，false-不可滑动
     */
    public void setScrollable(boolean scrollable) {
        this.mScrollable = scrollable;
    }

    public RapidScrollableViewPager(Context context) {
        super(context);
    }

    public RapidScrollableViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mScrollable && super.onInterceptTouchEvent(ev);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return !mScrollable || super.onTouchEvent(ev);
    }

    @Override
    public void setCurrentItem(int item) {
        if (!mScrollable) {
            super.setCurrentItem(item, false);//表示切换的时候，不需要切换时间。
        } else {
            super.setCurrentItem(item);
        }
    }

}