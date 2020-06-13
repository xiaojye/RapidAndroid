package com.jye.rapidandroid.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetrics;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.jye.rapidandroid.ui.R;
import com.jye.rapidandroid.ui.util.RapidScreenUtils;

/**
 * 描述：字母导航条
 * <p>
 * 作者：jye-ixiaojye@163.com
 */
public class RapidLetterIndexBar extends View {

    private String[] mLetters = {"#", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
            "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    private int mLettersCount = mLetters.length;

    private Paint mPaint;

    //提示框背景颜色，默认#88000000
    private int mOverlayBackgroundColor = Color.parseColor("#88000000");
    //提示框文本颜色，默认#FFFFFF
    private int mOverlayTextColor = Color.WHITE;
    //提示框文本大小（单位：sp），默认40sp
    private float mOverlayTextSize = RapidScreenUtils.sp2px(getContext(), 40f);
    //提示框宽度（单位：dp），默认70dp
    private float mOverlayWith = RapidScreenUtils.dp2px(getContext(), 70f);
    //提示框高度（单位：dp），默认70dp
    private float mOverlayHeight = RapidScreenUtils.dp2px(getContext(), 70f);

    //字母背景颜色，默认#00000000
    private int mLetterBackgroundColor = Color.parseColor("#00000000");
    //字母字体颜色，默认#157DF9
    private int mLetterTextColor = Color.parseColor("#157DF9");
    //字母字体大小（单位：sp），默认9sp
    private float mLetterTextSize = RapidScreenUtils.sp2px(getContext(), 10f);
    //字母之间的间隔（单位：dp），默认2dp
    private float mLetterTextSpace = RapidScreenUtils.dp2px(getContext(), 3f);

    //字母高度（=字体高度+字母间隔）
    private float mLetterHeight;
    //字母索引栏宽度（=字母高度+字母间隔）
    private float mLetterBarWidth;
    //字母索引栏高度（=字母高度*字母数量）
    private float mLetterBarHeight;

    // 导航栏在 x 轴的位移
    private float mLetterBarXOffset;

    //上一个选中的字母索引（避免touch时在同一字母区域类重复回调）
    private int mLastSelectIndex = -1;

    //字母选中监听器
    private OnLetterChangedListener mOnLetterChangedListener;

    public RapidLetterIndexBar(Context context) {
        super(context);
        init(null);
    }

    public RapidLetterIndexBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public RapidLetterIndexBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        setBackgroundColor(Color.TRANSPARENT);

        if (attrs != null) {
            TypedArray typedArray = getResources().obtainAttributes(attrs, R.styleable.RapidLetterIndexBar);

            if (typedArray.hasValue(R.styleable.RapidLetterIndexBar_rapid_lib_textColor)) {
                mLetterTextColor = typedArray.getColor(
                        R.styleable.RapidLetterIndexBar_rapid_lib_textColor, Color.parseColor("#157DF9"));
            }

            typedArray.recycle();
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        if (changed) {
            invalidate();
        }
        super.onLayout(changed, left, top, right, bottom);
    }

    /**
     * 获取字体高度
     *
     * @param fontSize 字体大小
     * @return 字体高度
     */
    private int getFontHeight(float fontSize) {
        Paint paint = new Paint();
        paint.setTextSize(fontSize);
        FontMetrics fm = paint.getFontMetrics();
        return (int) Math.ceil(fm.descent - fm.ascent);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mLetterHeight <= 0f) {
            //计算每个字母所占空间的高度
            mLetterHeight = getFontHeight(mLetterTextSize) + mLetterTextSpace;

            mLetterBarWidth = mLetterHeight + mLetterTextSpace;
            mLetterBarHeight = (int) (mLetterHeight * mLettersCount);

            // 计算 导航栏在 x 轴的位移
            mLetterBarXOffset = getWidth() - mLetterBarWidth;
        }

        drawLetters(canvas, mLetterBarWidth, mLetterHeight);
//        drawOverlayLetter(canvas, mLastSelectIndex < 0 ? null : mLetters[mLastSelectIndex]);
    }

    /**
     * 绘制字母
     *
     * @param canvas         画布
     * @param letterBarWidth 导航栏宽度
     * @param letterHeight   字母的高度
     */
    private void drawLetters(Canvas canvas, float letterBarWidth, float letterHeight) {
        canvas.save();
        canvas.translate(mLetterBarXOffset, 0);
        canvas.clipRect(0, getPaddingTop(), letterBarWidth, getHeight());
        canvas.drawColor(mLetterBackgroundColor);

        mPaint.reset();
        mPaint.setTextSize(mLetterTextSize);
        mPaint.setColor(mLetterTextColor);
        mPaint.setTextAlign(Align.CENTER);
        //设置是否需要抗锯齿
        mPaint.setAntiAlias(true);
        //设置是否加粗
        mPaint.setFakeBoldText(true);

        int height = getHeight();

        float xPos = letterBarWidth / 2;
        for (int i = 0; i < mLettersCount; i++) {
            float yPos = letterHeight * i + letterHeight + (height - mLetterBarHeight) / 2;

            // 设置字的位置为居中显示
            canvas.drawText(mLetters[i], xPos, yPos, mPaint);
        }

        canvas.restore();
    }

    /**
     * 绘制弹出层上的字母和弹出层
     *
     * @param canvas 画布
     * @param letter 要绘制的字母
     */
    private void drawOverlayLetter(Canvas canvas, String letter) {
        if (TextUtils.isEmpty(letter)) {
            return;
        }

        // 设置弹出层为 View 的中心
        canvas.save();
        canvas.translate(
                (getWidth() - getPaddingLeft() - getPaddingRight() - mOverlayWith) / 2,
                (getHeight() - getTop() - getPaddingBottom() - mOverlayHeight) / 2);
        canvas.clipRect(0, 0, mOverlayWith, mOverlayHeight);
        canvas.drawColor(mOverlayBackgroundColor);

        mPaint.reset();
        mPaint.setTextSize(mOverlayTextSize);
        mPaint.setColor(mOverlayTextColor);
        mPaint.setTextAlign(Align.CENTER);
        //设置是否需要抗锯齿
        mPaint.setAntiAlias(true);
        //设置是否加粗
        mPaint.setFakeBoldText(false);

        //计算文字y坐标位置
        FontMetrics metrics = mPaint.getFontMetrics();
        float overlayTextY = mOverlayHeight / 2 + (metrics.bottom - metrics.top) / 4;

        canvas.drawText(letter, mOverlayWith / 2, overlayTextY, mPaint);

        canvas.restore();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        final int action = event.getAction();

        final float y = event.getY();

        final int currentIndex = (int) ((y - (getHeight() - mLetterBarHeight) / 2) / mLetterBarHeight * mLettersCount);

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                //防止拦截底层布局事件
                if (mLetterBarXOffset > event.getX()) {
                    invalidate();
                    return false;
                }
            case MotionEvent.ACTION_MOVE:
                if (mLastSelectIndex != currentIndex && currentIndex >= 0 && currentIndex < mLettersCount) {
                    mLastSelectIndex = currentIndex;
                    final String letter = mLetters[currentIndex];
                    if (mOnLetterChangedListener != null) {
                        mOnLetterChangedListener.onLetterChanged(letter);
                    }
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                if (mOnLetterChangedListener != null && mLastSelectIndex >= 0) {
                    mOnLetterChangedListener.afterLetterChanged(mLetters[mLastSelectIndex]);
                }
                mLastSelectIndex = -1;
                invalidate();
//                dismissLetterOverlay();
                break;
        }
        return true;
    }

    /**
     * 消失字母弹出层
     */
    private void dismissLetterOverlay() {
        postDelayed(new Runnable() {

            @Override
            public void run() {
                mLastSelectIndex = -1;
                invalidate();
            }
        }, 500);
    }

    //----------------------------------------------------------------------------------------------

    /**
     * 字母选中监听器
     */
    public interface OnLetterChangedListener {
        /**
         * @param letter 被选中的字母
         */
        void onLetterChanged(String letter);

        /**
         * @param letter 被选中的字母
         */
        void afterLetterChanged(String letter);
    }

    /**
     * 设置字母选中监听器
     *
     * @param listener 字母选中监听器
     */
    public void setOnLetterChangeListener(OnLetterChangedListener listener) {
        this.mOnLetterChangedListener = listener;
    }

    /**
     * 自定义现实的字母
     *
     * @param letters 字母数组
     */
    public void setLetters(String[] letters) {
        if (letters != null && letters.length == 0) {
            mLetters = letters;
            mLettersCount = mLetters.length;
            invalidate();
        }
    }

}