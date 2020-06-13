package com.jye.rapidandroid.util;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;

import androidx.core.content.FileProvider;

import java.io.File;

/**
 * 描述：Uri相关工具类
 * <p>
 * 创建人：jye-ixiaojye@163.com
 */
public final class RapidUriUtils {

    /**
     * 获取Uri真实绝对地址
     */
    public static String getUriRealPath(Context context, Uri uri) {
        Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.Media.DATA}, null, null, null);
        int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String filePath = cursor.getString(columnIndex);
        cursor.close();
        return filePath;
    }

    /**
     * 根据Uri获取文件
     */
    public static File getFileFromUri(Context context, Uri uri) {
        return new File(getUriRealPath(context, uri));
    }

    /**
     * 根据文件获取Uri
     */
    public static Uri getUriFromFile(Context context, File file) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return FileProvider.getUriForFile(context.getApplicationContext(), RapidAppInfoUtils.getPackageName(context) + ".fileprovider", file);
        } else {
            return Uri.fromFile(file);
        }
    }

}
