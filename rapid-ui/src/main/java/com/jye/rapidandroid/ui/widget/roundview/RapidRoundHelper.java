package com.jye.rapidandroid.ui.widget.roundview;

import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.jye.rapidandroid.ui.R;

/**
 * 圆角辅助类
 */
public final class RapidRoundHelper implements RapidRoundAction {

    private View mView;

    private Paint mPaint;
    private RectF mLayerRectF;
    private RectF mStrokeRectF;

    private Path mPath;
    private Path mTempPath;

    private Xfermode mXfermode;

    private float[] mRadii;
    private float[] mStrokeRadii;

    private int mWidth;
    private int mHeight;

    private float mRadiusTopLeft;
    private float mRadiusTopRight;
    private float mRadiusBottomLeft;
    private float mRadiusBottomRight;

    private boolean mIsCircle;

    private int mStrokeColor = Color.WHITE;
    private float mStrokeWidth;

    public void init(View view, AttributeSet attrs) {
        if (view instanceof ViewGroup && view.getBackground() == null) {
            view.setBackgroundColor(Color.parseColor("#00000000"));
        }
        this.mView = view;

        mPaint = new Paint();
        mLayerRectF = new RectF();
        mStrokeRectF = new RectF();

        mPath = new Path();
        mTempPath = new Path();

        mXfermode = new PorterDuffXfermode(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT ? PorterDuff.Mode.DST_OUT : PorterDuff.Mode.DST_IN);

        mRadii = new float[8];
        mStrokeRadii = new float[8];

        TypedArray typedArray = view.getContext().obtainStyledAttributes(attrs, R.styleable.Round);

        int radius = typedArray.getDimensionPixelSize(R.styleable.Round_rapid_cornerRadius, 0);
        mRadiusTopLeft = typedArray.getDimensionPixelSize(R.styleable.Round_rapid_cornerRadiusTopLeft, radius);
        mRadiusTopRight = typedArray.getDimensionPixelSize(R.styleable.Round_rapid_cornerRadiusTopRight, radius);
        mRadiusBottomLeft = typedArray.getDimensionPixelSize(R.styleable.Round_rapid_cornerRadiusBottomLeft, radius);
        mRadiusBottomRight = typedArray.getDimensionPixelSize(R.styleable.Round_rapid_cornerRadiusBottomRight, radius);

        mIsCircle = typedArray.getBoolean(R.styleable.Round_rapid_round_isCircle, false);

        mStrokeColor = typedArray.getColor(R.styleable.Round_rapid_borderColor, mStrokeColor);
        mStrokeWidth = typedArray.getDimension(R.styleable.Round_rapid_borderWidth, 0);

        typedArray.recycle();

        resetRadii();
    }

    private void resetRadii() {
        mRadii[0] = mRadii[1] = mRadiusTopLeft - mStrokeWidth;
        mRadii[2] = mRadii[3] = mRadiusTopRight - mStrokeWidth;
        mRadii[4] = mRadii[5] = mRadiusBottomRight - mStrokeWidth;
        mRadii[6] = mRadii[7] = mRadiusBottomLeft - mStrokeWidth;

        mStrokeRadii[0] = mStrokeRadii[1] = mRadiusTopLeft;
        mStrokeRadii[2] = mStrokeRadii[3] = mRadiusTopRight;
        mStrokeRadii[4] = mStrokeRadii[5] = mRadiusBottomRight;
        mStrokeRadii[6] = mStrokeRadii[7] = mRadiusBottomLeft;
    }

    public void onSizeChanged(int width, int height) {
        this.mWidth = width;
        this.mHeight = height;

        if (mIsCircle) {
            float radius = Math.min(height, width) * 1f / 2 - mStrokeWidth;
            mRadiusTopLeft = radius;
            mRadiusTopRight = radius;
            mRadiusBottomRight = radius;
            mRadiusBottomLeft = radius;
            resetRadii();
        }
        if (mLayerRectF != null) {
            mLayerRectF.set(0, 0, width, height);
        }
        if (mStrokeRectF != null) {
            mStrokeRectF.set((mStrokeWidth / 2), (mStrokeWidth / 2), width - mStrokeWidth / 2, height - mStrokeWidth / 2);
        }
    }

    public void preDraw(Canvas canvas) {
        canvas.saveLayer(mLayerRectF, null, Canvas.ALL_SAVE_FLAG);
        if (mStrokeWidth > 0) {
            float sx = (mWidth - 2 * mStrokeWidth) / mWidth;
            float sy = (mHeight - 2 * mStrokeWidth) / mHeight;
            // 缩小画布，使图片内容不被borders覆盖
            canvas.scale(sx, sy, mWidth / 2.0f, mHeight / 2.0f);
        }
    }

    public void drawPath(Canvas canvas) {
        mPaint.reset();
        mPath.reset();

        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setXfermode(mXfermode);

        mPath.addRoundRect(mLayerRectF, mRadii, Path.Direction.CCW);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mTempPath.reset();
            mTempPath.addRect(mLayerRectF, Path.Direction.CCW);
            mTempPath.op(mPath, Path.Op.DIFFERENCE);
            canvas.drawPath(mTempPath, mPaint);
        } else {
            canvas.drawPath(mPath, mPaint);
        }
        mPaint.setXfermode(null);
        canvas.restore();

        // draw stroke
        if (mStrokeWidth > 0) {
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeWidth(mStrokeWidth);
            mPaint.setColor(mStrokeColor);

            mPath.reset();
            mPath.addRoundRect(mStrokeRectF, mStrokeRadii, Path.Direction.CCW);
            canvas.drawPath(mPath, mPaint);
        }
    }

    @Override
    public void setBorderColor(int borderColor) {
        this.mStrokeColor = borderColor;
        if (mView != null) {
            mView.invalidate();
        }
    }

    @Override
    public void setBorderWidth(int borderWidth) {
        this.mStrokeWidth = borderWidth;
        if (mView != null) {
            resetRadii();
            onSizeChanged(mWidth, mHeight);
            mView.invalidate();
        }
    }

    @Override
    public void setCornerRadius(int radius) {
        this.mRadiusTopLeft = radius;
        this.mRadiusTopRight = radius;
        this.mRadiusBottomLeft = radius;
        this.mRadiusBottomRight = radius;
        if (mView != null) {
            mView.invalidate();
        }
    }

    @Override
    public void setCornerRadiusTopLeft(int topLeftRadius) {
        this.mRadiusTopLeft = topLeftRadius;
        if (mView != null) {
            mView.invalidate();
        }
    }

    @Override
    public void setCornerRadiusTopRight(int topRightRadius) {
        this.mRadiusTopRight = topRightRadius;
        if (mView != null) {
            mView.invalidate();
        }
    }

    @Override
    public void setCornerRadiusBottomLeft(int bottomLeftRadius) {
        this.mRadiusBottomLeft = bottomLeftRadius;
        if (mView != null) {
            mView.invalidate();
        }
    }

    @Override
    public void setCornerRadiusBottomRight(int bottomRightRadius) {
        this.mRadiusBottomRight = bottomRightRadius;
        if (mView != null) {
            mView.invalidate();
        }
    }

    @Override
    public void setIsCircle(boolean isCircle) {
        this.mIsCircle = isCircle;
        if (mView != null) {
            mView.invalidate();
        }
    }
}