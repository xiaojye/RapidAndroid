package com.jye.rapidandroid.ui.image.preview;

import android.content.Context;

import com.jye.rapidandroid.ui.image.preview.bean.VideoPreviewInfo;

import java.util.List;

public class VideoPreviewBuilder extends PreviewBuilder<VideoPreviewBuilder> {

    VideoPreviewBuilder(RapidImagePreview preview, List<VideoPreviewInfo> videoPreviewInfoList) {
        super(preview);
        mPreviewOptions.previewInfoList = videoPreviewInfoList;
    }

    @Override
    protected void doStart(Context context) {
//        Intent intent = new Intent(context, VideoPreviewActivity.class);
//        context.startActivity(intent);
//        ((Activity) context).overridePendingTransition(R.anim.preview_enter_anim, 0);
    }
}