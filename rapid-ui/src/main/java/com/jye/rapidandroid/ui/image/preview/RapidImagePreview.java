package com.jye.rapidandroid.ui.image.preview;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.jye.rapidandroid.ui.image.preview.bean.ImagePreviewInfo;
import com.jye.rapidandroid.ui.image.preview.bean.VideoPreviewInfo;

import org.jetbrains.annotations.NotNull;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * 描述：[类描述]
 * 创建人：jye-ixiaojye@163.com
 */
public class RapidImagePreview {

    final WeakReference<Context> context;

    private RapidImagePreview(Context context) {
        this.context = new WeakReference<>(context);
    }

    public static RapidImagePreview from(Context context) {
        return new RapidImagePreview(context);
    }

    public static RapidImagePreview from(Activity activity) {
        return new RapidImagePreview(activity);
    }

    public static RapidImagePreview from(Fragment fragment) {
        return new RapidImagePreview(fragment.getActivity());
    }

    public ImagePreviewBuilder imageList(@NotNull List<ImagePreviewInfo> imageInfoList) {
        return new ImagePreviewBuilder(this, imageInfoList);
    }

    public VideoPreviewBuilder videoList(@NotNull List<VideoPreviewInfo> videoInfoList) {
        return new VideoPreviewBuilder(this, videoInfoList);
    }

    public interface ILoader {

        /**
         * 加载图片
         *
         * @param context      上下文对象
         * @param uri          图片资源URI
         * @param imageView    图片视图控件
         * @param progressView 进度视图控件
         */
        void loadImage(@NotNull Context context, @NotNull Uri uri, @NotNull ImageView imageView, @NotNull View progressView);

        /**
         * 加载GIF图片
         *
         * @param context      上下文对象
         * @param uri          图片资源URI
         * @param imageView    图片视图控件
         * @param progressView 进度视图控件
         */
        void loadGifImage(@NotNull Context context, @NotNull Uri uri, @NotNull ImageView imageView, @NotNull View progressView);
    }

}
