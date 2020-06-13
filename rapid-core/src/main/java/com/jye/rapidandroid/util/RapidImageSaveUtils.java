package com.jye.rapidandroid.util;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.OutputStream;

/**
 * 描述：图片保存工具类
 * <p>
 * 创建人：jye-ixiaojye@163.com
 */
public class RapidImageSaveUtils {

    private RapidImageSaveUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 保存图片到Pictures公共图片目录
     *
     * @param context  上下文对象
     * @param bitmap   需要保存的图片bitmap
     * @param fileName 保存图片文件名
     * @return 保存成功返回true，反之false
     */
    public static boolean saveImageToPictures(Context context, Bitmap bitmap, String fileName) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.Images.Media.DISPLAY_NAME, fileName);
        contentValues.put(MediaStore.Images.Media.DESCRIPTION, fileName);
        contentValues.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");

        Uri uri = context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);

        OutputStream outputStream;
        try {
            outputStream = context.getContentResolver().openOutputStream(uri);
            bitmap.compress(CompressFormat.JPEG, 100, outputStream);
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        //更新图库
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        intent.setData(Uri.fromFile(RapidUriUtils.getFileFromUri(context, uri)));
        context.sendBroadcast(intent);

        return true;
    }

    /**
     * 保存图片到Pictures公共图片目录(默认文件名为yyyyMMddHHmmssSSS.jpg)
     *
     * @param context 上下文对象
     * @param bitmap  需要保存的图片bitmap
     * @return 保存成功返回true，反之false
     */
    public static boolean saveImageToPictures(Context context, Bitmap bitmap) {
        return saveImageToPictures(context, bitmap, RapidDateTimeUtils.timestampToString(System.currentTimeMillis(), "yyyyMMddHHmmssSSS") + ".jpg");
    }

}