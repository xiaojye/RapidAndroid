package com.jye.rapidandroid.ui.image.picker;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * 描述：[类描述]
 * 创建人：jye-ixiaojye@163.com
 */
public class RapidImagePicker {

    final WeakReference<Activity> mActivity;

    private RapidImagePicker(Activity activity) {
        mActivity = new WeakReference<>(activity);
    }

    public static RapidImagePicker from(Activity activity) {
        return new RapidImagePicker(activity);
    }

    /**
     * 选择选择器类型
     *
     * @param type {@link PickerType} 选择器类型
     * @return {@link PickerBuilder}
     */
    public PickerBuilder setType(PickerType type) {
        return new PickerBuilder(this);
    }

    public interface ILoader {

        /**
         * Load thumbnail of a static image resource.
         *
         * @param context     Context
         * @param resize      Desired size of the origin image
         * @param placeholder Placeholder drawable when image is not loaded yet
         * @param imageView   ImageView widget
         * @param uri         Uri of the loaded image
         */
        void loadThumbnail(Context context, int resize, Drawable placeholder, ImageView imageView, Uri uri);

        /**
         * Load thumbnail of a gif image resource. You don't have to load an animated gif when it's only
         * a thumbnail tile.
         *
         * @param context     Context
         * @param resize      Desired size of the origin image
         * @param placeholder Placeholder drawable when image is not loaded yet
         * @param imageView   ImageView widget
         * @param uri         Uri of the loaded image
         */
        void loadAnimatedGifThumbnail(Context context, int resize, Drawable placeholder, ImageView imageView, Uri uri);

        /**
         * Load a static image resource.
         *
         * @param context   Context
         * @param resizeX   Desired x-size of the origin image
         * @param resizeY   Desired y-size of the origin image
         * @param imageView ImageView widget
         * @param uri       Uri of the loaded image
         */
        void loadImage(Context context, int resizeX, int resizeY, ImageView imageView, Uri uri);

        /**
         * Load a gif image resource.
         *
         * @param context   Context
         * @param resizeX   Desired x-size of the origin image
         * @param resizeY   Desired y-size of the origin image
         * @param imageView ImageView widget
         * @param uri       Uri of the loaded image
         */
        void loadAnimatedGifImage(Context context, int resizeX, int resizeY, ImageView imageView, Uri uri);

        /**
         * Whether this implementation supports animated gif. Just knowledge of it, convenient for users.
         *
         * @return true support animated gif, false do not support animated gif.
         */
        boolean supportAnimatedGif();
    }

    public interface IResultCallback {
        void onCancel();

        void onError(@NonNull Exception e);

        void onResult(@NonNull List<Uri> uriList);
    }

}
