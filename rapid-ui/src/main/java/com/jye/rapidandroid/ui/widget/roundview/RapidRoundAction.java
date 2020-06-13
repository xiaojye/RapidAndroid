package com.jye.rapidandroid.ui.widget.roundview;

/**
 * 圆角控件对外公开的属性设置接口
 */
public interface RapidRoundAction {

    /**
     * 设置边框颜色
     *
     * @param borderColor 边框线颜色
     */
    void setBorderColor(int borderColor);

    /**
     * 设置边框宽度
     *
     * @param borderWidth 边框线宽度（单位：像素）
     */
    void setBorderWidth(int borderWidth);

    /**
     * 统一设置四个角的圆角半径
     *
     * @param radius 四个角的圆角半径（单位：像素）
     */
    void setCornerRadius(int radius);

    /**
     * 设置左上角圆角半径
     *
     * @param topLeftRadius 左上角圆角半径（单位：像素）
     */
    void setCornerRadiusTopLeft(int topLeftRadius);

    /**
     * 设置右上角圆角半径
     *
     * @param topRightRadius 右上角圆角半径（单位：像素）
     */
    void setCornerRadiusTopRight(int topRightRadius);

    /**
     * 设置左下角圆角半径
     *
     * @param bottomLeftRadius 左下角圆角半径（单位：像素）
     */
    void setCornerRadiusBottomLeft(int bottomLeftRadius);

    /**
     * 设置右下角圆角半径
     *
     * @param bottomRightRadius 右下角圆角半径（单位：像素）
     */
    void setCornerRadiusBottomRight(int bottomRightRadius);

    /**
     * 是否以圆形形态显示控件
     *
     * @param isCircle 当设置为true时，设置的圆角半径将不起作用
     */
    void setIsCircle(boolean isCircle);

}