package com.jye.rapidandroid.ui.image.preview.bean;

import android.net.Uri;

/**
 * 描述：[功能描述]
 * </p>
 * 作者：jye-ixiaojye@163.com
 * 日期：2018/12/15
 */
public class VideoPreviewInfo extends PreviewInfo {


    public VideoPreviewInfo(String url) {
        super(Uri.parse(url));
    }

}
