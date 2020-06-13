package com.jye.rapidandroid.ui.image.picker;

import android.app.Activity;
import android.content.Intent;

import com.jye.rapidandroid.ui.image.picker.ui.ImagePickerActivity;

/**
 * 描述：[类描述]
 * 创建人：jye-ixiaojye@163.com
 */
public class PickerBuilder {

    private final RapidImagePicker mImagePicker;

    PickerBuilder(RapidImagePicker imagePicker) {
        mImagePicker = imagePicker;
    }

    /**
     * 设置最大选择图片数量
     *
     * @param maxCount 最大选择图片数目，默认不限制
     * @return {@link PickerBuilder}
     */
    public PickerBuilder setMaxCount(int maxCount) {
        return this;
    }

    /**
     * 设置是否可以拍照获取
     *
     * @param cameraEnable 是否可以拍照获取，默认为true
     * @return {@link PickerBuilder}
     */
    public PickerBuilder setCameraEnable(boolean cameraEnable) {
        return this;
    }

    /**
     * 设置选择结果回调
     *
     * @param resultCallback 选择结果回调接口实现
     * @return {@link PickerBuilder}
     */
    public PickerBuilder setResultCallback(RapidImagePicker.IResultCallback resultCallback) {
        return this;
    }

    /**
     * 打开选择界面
     *
     * @param loader 加载引擎
     */
    public void start(RapidImagePicker.ILoader loader) {
        Activity activity = mImagePicker.mActivity.get();
        if (activity == null) {
            return;
        }

        Intent intent = new Intent(activity, ImagePickerActivity.class);
        activity.startActivity(intent);
    }
}
