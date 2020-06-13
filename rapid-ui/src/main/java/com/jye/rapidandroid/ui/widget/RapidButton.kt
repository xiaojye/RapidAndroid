package com.jye.rapidandroid.ui.widget

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.StateListDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.os.Build
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton
import com.jye.rapidandroid.ui.R
import com.jye.rapidandroid.ui.util.RapidResourceUtils

/**
 * 描述 可设置按钮圆角、触碰/禁用效果的自定义按钮
 * 创建人：jye-ixiaojye@163.com
 */
class RapidButton : AppCompatButton {

    //圆角角度
    private var radius = 0
    //默认颜色
    private var normalColor =  RapidResourceUtils.getColorId(context, "colorPrimary")
    //触碰颜色
    private var pressedColor = 0
    //禁用颜色
    private var disableColor = Color.parseColor("#CCCCCC")

    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        if (attrs != null) {
            val ta = context.obtainStyledAttributes(attrs, R.styleable.RapidButton)

            radius = ta.getDimensionPixelSize(R.styleable.RapidButton_rapid_cornerRadius, radius)
            normalColor = ta.getColor(R.styleable.RapidButton_rapid_btn_bgColor, normalColor)
            pressedColor = ta.getColor(R.styleable.RapidButton_rapid_btn_bgColor_pressed, 0)
            disableColor = ta.getColor(R.styleable.RapidButton_rapid_btn_bgColor_disable, disableColor)

            ta.recycle()
        }

        resetAttr()
    }

    private fun resetAttr() {
        // 设置带有不同状态的drawable背景
        val listDrawable = StateListDrawable()

        val normal: Drawable
        val pressed: Drawable
        val disable: Drawable

        normal = createAngleDrawable(normalColor, radius.toFloat())
        pressed = createAngleDrawable(
            if (pressedColor != 0) pressedColor else getPressColor(normalColor),
            radius.toFloat()
        )
        disable = createAngleDrawable(disableColor, radius.toFloat())

        //注意该处的顺序，只要有一个状态与之相配，背景就会被换掉
        //所以不要把大范围放在前面了，如果sd.addState(new[]{},normal)放在第一个的话，就没有什么效果了
        listDrawable.addState(intArrayOf(android.R.attr.state_pressed), pressed)
        listDrawable.addState(intArrayOf(android.R.attr.state_enabled), normal)
        listDrawable.addState(intArrayOf(-android.R.attr.state_enabled), disable)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            this.background = listDrawable
        } else {
            this.setBackgroundDrawable(listDrawable)
        }
    }

    /**
     * 根据传入颜色返回触碰效果的颜色
     */
    private fun getPressColor(color: Int): Int {
        val hsv = FloatArray(3)
        Color.colorToHSV(color, hsv)
        hsv[2] *= 0.8f
        return Color.HSVToColor(hsv)
    }

    /**
     * 返回圆角drawable
     */
    private fun createAngleDrawable(color: Int, radius: Float): ShapeDrawable {
        val outerRadii = floatArrayOf(
            radius, radius, radius, radius, radius, radius, radius, radius
        )//外矩形 左上、右上、右下、左下 圆角半径
        val roundRectShape = RoundRectShape(outerRadii, null, null) //无内矩形

        val drawable = ShapeDrawable(roundRectShape)
        drawable.paint.color = color

        return drawable
    }

    /**
     * 设置圆角半径
     */
    fun setCornerRadius(radius: Int) {
        this.radius = radius
        resetAttr()
    }

    /**
     * 设置按钮默认颜色
     */
    fun setNormalColor(normalColor: Int) {
        this.normalColor = normalColor
        resetAttr()
    }

    /**
     * 设置按钮触摸颜色
     */
    fun setPressedColor(pressedColor: Int) {
        this.pressedColor = pressedColor
        resetAttr()
    }

    /**
     * 设置按钮禁用颜色
     */
    fun setDisableColor(disableColor: Int) {
        this.disableColor = disableColor
        resetAttr()
    }

}
