package com.jye.rapidandroid.ui.widget.roundview;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

/**
 * 圆角ImageView
 */
public class RapidRoundImageView extends AppCompatImageView implements RapidRoundAction {

    private RapidRoundHelper mRoundHelper = new RapidRoundHelper();

    public RapidRoundImageView(Context context) {
        this(context, null);
    }

    public RapidRoundImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RapidRoundImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mRoundHelper.init(this, attrs);
    }

    @Override
    protected void onSizeChanged(int width, int height, int oldWidth, int oldHeight) {
        super.onSizeChanged(width, height, oldWidth, oldHeight);
        mRoundHelper.onSizeChanged(width, height);
    }

    @Override
    public void draw(Canvas canvas) {
        mRoundHelper.preDraw(canvas);
        super.draw(canvas);
        mRoundHelper.drawPath(canvas);
    }

    @Override
    public void setBorderColor(int borderColor) {
        mRoundHelper.setBorderColor(borderColor);
    }

    @Override
    public void setBorderWidth(int borderWidth) {
        mRoundHelper.setBorderWidth(borderWidth);
    }

    @Override
    public void setCornerRadius(int radius) {
        mRoundHelper.setCornerRadius(radius);
    }

    @Override
    public void setCornerRadiusTopLeft(int topLeftRadius) {
        mRoundHelper.setCornerRadiusTopLeft(topLeftRadius);
    }

    @Override
    public void setCornerRadiusTopRight(int topRightRadius) {
        mRoundHelper.setCornerRadiusTopRight(topRightRadius);
    }

    @Override
    public void setCornerRadiusBottomLeft(int bottomLeftRadius) {
        mRoundHelper.setCornerRadiusBottomLeft(bottomLeftRadius);
    }

    @Override
    public void setCornerRadiusBottomRight(int bottomRightRadius) {
        mRoundHelper.setCornerRadiusBottomRight(bottomRightRadius);
    }

    @Override
    public void setIsCircle(boolean isCircle) {
        mRoundHelper.setIsCircle(isCircle);
    }
}