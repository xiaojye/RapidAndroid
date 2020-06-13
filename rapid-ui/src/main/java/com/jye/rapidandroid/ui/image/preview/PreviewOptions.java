package com.jye.rapidandroid.ui.image.preview;

import android.content.Context;
import android.view.View;

import androidx.viewpager.widget.ViewPager;

import com.jye.rapidandroid.ui.image.preview.bean.PreviewInfo;

import java.util.List;

/**
 * 描述：[类描述]
 * 创建人：jye-ixiaojye@163.com
 */
public class PreviewOptions<T extends PreviewInfo> {

    /**
     * 上下文对象
     */
    public Context context;

    /**
     * 预览图片加载器
     */
    public RapidImagePreview.ILoader loader;

    /**
     * 预览信息列表
     */
    public List<T> previewInfoList;

    /**
     * 当前索引
     */
    public int currentIndex;

    /**
     * 自定义遮盖层
     */
    public View customShadeView;

    /**
     * 自定义进度视图
     */
    public View customProgressView;

    /**
     * 页面切换监听器
     */
    public ViewPager.OnPageChangeListener onPageChangeListener;

    private PreviewOptions() {
    }

    private static final class InstanceHolder {
        private static final PreviewOptions INSTANCE = new PreviewOptions();
    }

    public static <T extends PreviewInfo> PreviewOptions<T> getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public static <T extends PreviewInfo> PreviewOptions<T> getCleanInstance() {
        PreviewOptions<T> previewOptions = getInstance();
        previewOptions.reset();
        return previewOptions;
    }

    public void reset() {
        context = null;
        loader = null;
        previewInfoList = null;
        currentIndex = 0;
        customShadeView = null;
        customProgressView = null;
        onPageChangeListener = null;
    }

}
