package com.jye.rapidandroid.ui.image.preview;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.View;

import androidx.viewpager.widget.ViewPager;

/**
 * 描述：[类描述]
 * 创建人：jye-ixiaojye@163.com
 */
public abstract class PreviewBuilder<T extends PreviewBuilder> {
    private static final String TAG = PreviewBuilder.class.getSimpleName();

    //触发双击的最短时间，小于这个时间的直接返回
    private static final int DOUBLE_CLICK_INTERVAL_TIME = 1500;

    //防止多次快速点击，记录上次打开的时间戳
    private long mLastClickTime = 0;

    final PreviewOptions mPreviewOptions;

    PreviewBuilder(RapidImagePreview preview) {
        mPreviewOptions = PreviewOptions.getCleanInstance();
        mPreviewOptions.context = preview.context.get();
    }

    /**
     * 设置当前索引
     *
     * @param currentIndex 当前索引位置
     * @return PreviewBuilder
     */
    public T currentIndex(int currentIndex) {
        if (currentIndex < mPreviewOptions.previewInfoList.size()) {
            mPreviewOptions.currentIndex = currentIndex;
        }
        return (T) this;
    }

    /**
     * 设置自定义遮盖层，定制自己想要的效果，当设置遮盖层后，原本的指示器会被隐藏
     *
     * @param customShadeView 自定义遮盖层
     * @return PreviewBuilder
     */
    public T customShadeView(View customShadeView) {
        mPreviewOptions.customShadeView = customShadeView;
        return (T) this;
    }

    /**
     * 自定义ProgressView
     *
     * @param customProgressView 自定义ProgressView
     * @return PreviewBuilder
     */
    public T customProgressView(View customProgressView) {
        mPreviewOptions.customProgressView = customProgressView;
        return (T) this;
    }

    /**
     * 设置页面切换监听器
     *
     * @param onPageChangeListener 页面切换监听器
     * @return PreviewBuilder
     */
    public T onPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        mPreviewOptions.onPageChangeListener = onPageChangeListener;
        return (T) this;
    }

    /**
     * 打开预览界面
     *
     * @param loader 加载引擎
     */
    public void start(RapidImagePreview.ILoader loader) {
        if (System.currentTimeMillis() - mLastClickTime <= DOUBLE_CLICK_INTERVAL_TIME) {
            Log.w(TAG, "忽略多次快速点击");
            return;
        }

        mPreviewOptions.loader = loader;

        if (loader == null) {
            throw new NullPointerException("loader is null, please call " + RapidImagePreview.class.toString() + "$initLoader before use.");
        }

        //因为context是使用弱引用保存，所以在此判断如果为空则表示也被回收，就不跳转到预览页了
        final Context context = mPreviewOptions.context;
        if (context == null) {
            Log.e(TAG, "preview start failed, because context has been recycle.");
            return;
        }

        if (!(context instanceof Activity)) {
            throw new IllegalArgumentException("context must be a Activity!");
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            if (((Activity) context).isFinishing() || ((Activity) context).isDestroyed()) {
                mPreviewOptions.reset();
                return;
            }
        } else {
            if (((Activity) context).isFinishing()) {
                mPreviewOptions.reset();
                return;
            }
        }

        if (mPreviewOptions.previewInfoList.isEmpty()) {
            mPreviewOptions.reset();
            return;
        }


        mLastClickTime = System.currentTimeMillis();

        doStart(context);
    }

    /**
     * 子类实现预览页面的跳转
     *
     * @param context 上下文对象
     */
    protected abstract void doStart(Context context);

}
