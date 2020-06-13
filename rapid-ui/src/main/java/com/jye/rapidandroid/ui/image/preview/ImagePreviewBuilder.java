package com.jye.rapidandroid.ui.image.preview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.jye.rapidandroid.ui.R;
import com.jye.rapidandroid.ui.image.preview.bean.ImagePreviewInfo;
import com.jye.rapidandroid.ui.image.preview.ui.ImagePreviewActivity;

import java.util.List;

public class ImagePreviewBuilder extends PreviewBuilder<ImagePreviewBuilder> {

    ImagePreviewBuilder(RapidImagePreview preview, List<ImagePreviewInfo> imagePreviewInfoList) {
        super(preview);
        mPreviewOptions.previewInfoList = imagePreviewInfoList;
    }

    @Override
    protected void doStart(Context context) {
        Intent intent = new Intent(context, ImagePreviewActivity.class);
        context.startActivity(intent);
        ((Activity) context).overridePendingTransition(R.anim.rapid_image_preview_enter_anim, 0);
    }
}