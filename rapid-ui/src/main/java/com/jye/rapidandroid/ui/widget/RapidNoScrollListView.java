package com.jye.rapidandroid.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * 描述：无滑动功能的ListView（解决ScrollView嵌套ListView冲突问题）
 * <p>
 * 创建人：jye-ixiaojye@163.com
 */
public class RapidNoScrollListView extends ListView {

    public RapidNoScrollListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RapidNoScrollListView(Context context) {
        super(context);
    }

    public RapidNoScrollListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * 设置gridView不可滑动
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}