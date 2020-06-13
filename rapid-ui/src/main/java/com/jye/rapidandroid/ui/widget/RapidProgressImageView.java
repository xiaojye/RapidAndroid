package com.jye.rapidandroid.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

/**
 * 描述：带进度的图片控件（一般用于显示上传进度等）
 * <p>
 * 创建人：jye-ixiaojye@163.com
 */
public class RapidProgressImageView extends AppCompatImageView {
    private Context context;
    private Paint paint;
    int progress = 0;
    private boolean flag;

    public RapidProgressImageView(Context context) {
        super(context);
    }

    public RapidProgressImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RapidProgressImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setAntiAlias(true); //消除锯齿
        paint.setStyle(Paint.Style.FILL); //设置paint为实心， Paint.Style.STROKE为空心
        paint.setColor(Color.parseColor("#70000000")); //设置为半透明
        canvas.drawRect(0, 0, getWidth(), getHeight() - getHeight() * progress / 100, paint); //这里getWidth() 获取的是image对象宽高度 xml值*2

        paint.setColor(Color.parseColor("#00000000"));// 全透明
        canvas.drawRect(0, getHeight() - getHeight() * progress / 100,
                getWidth(), getHeight(), paint);

        if (!flag) {
            paint.setTextSize(sp2px(context, 15f));
            paint.setColor(Color.parseColor("#FFFFFF"));
            paint.setStrokeWidth(2);
            Rect rect = new Rect();
            paint.getTextBounds("100%", 0, "100%".length(), rect);// 确定文字的宽度
            canvas.drawText(progress + "%", getWidth() / 2 - rect.width() / 2,
                    getHeight() / 2, paint);
        }
    }

    public void setProgress(int progress) {
        this.progress = progress;
        if (progress == 100) {
            flag = true;
        }
        postInvalidate();
    }

    private static int sp2px(Context context, float spValue) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

} 