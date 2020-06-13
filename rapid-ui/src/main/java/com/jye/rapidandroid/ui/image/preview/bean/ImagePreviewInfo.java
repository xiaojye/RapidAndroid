package com.jye.rapidandroid.ui.image.preview.bean;

import android.net.Uri;
import android.widget.ImageView;

/**
 * 描述：[功能描述]
 * </p>
 * 作者：jye-ixiaojye@163.com
 * 日期：2018/12/15
 */
public class ImagePreviewInfo extends PreviewInfo {

    public ImagePreviewInfo(String url) {
        super(Uri.parse(url));
    }

    public ImagePreviewInfo(Uri uri) {
        super(uri);
    }

    public ImagePreviewInfo(Uri uri, ImageView imageView) {
        super(uri, imageView);
    }

}
