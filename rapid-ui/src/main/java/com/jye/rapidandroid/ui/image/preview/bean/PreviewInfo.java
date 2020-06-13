package com.jye.rapidandroid.ui.image.preview.bean;

import android.net.Uri;
import android.widget.ImageView;

import java.io.Serializable;

/**
 * 描述：[功能描述]
 * </p>
 * 作者：jye-ixiaojye@163.com
 * 日期：2018/12/15
 */
public abstract class PreviewInfo implements Serializable {

    private Uri uri;

    //记录传入ImageView的坐标（用于缩放回原位）
    private float imageViewX;
    private float imageViewY;
    private int imageViewWidth;
    private int imageViewHeight;

    public PreviewInfo(Uri uri) {
        this.uri = uri;
    }

    public PreviewInfo(Uri uri, ImageView imageView) {
        this.uri = uri;

        if (imageView == null) {
            return;
        }

        this.imageViewX = imageView.getX();
        this.imageViewY = imageView.getY();
        this.imageViewWidth = imageView.getWidth();
        this.imageViewHeight = imageView.getHeight();
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public float getImageViewX() {
        return imageViewX;
    }

    public void setImageViewX(float imageViewX) {
        this.imageViewX = imageViewX;
    }

    public float getImageViewY() {
        return imageViewY;
    }

    public void setImageViewY(float imageViewY) {
        this.imageViewY = imageViewY;
    }

    public int getImageViewWidth() {
        return imageViewWidth;
    }

    public void setImageViewWidth(int imageViewWidth) {
        this.imageViewWidth = imageViewWidth;
    }

    public int getImageViewHeight() {
        return imageViewHeight;
    }

    public void setImageViewHeight(int imageViewHeight) {
        this.imageViewHeight = imageViewHeight;
    }

}
