package com.jye.rapidandroid.util;

import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;

/**
 * 描述：文件相关工具类
 * <p>
 * 创建人：jye-ixiaojye@163.com
 */
public final class RapidFileUtils {
    private static final String TAG = RapidFileUtils.class.getSimpleName();

    private RapidFileUtils() {
    }

    /**
     * 文件大小单位
     */
    public enum SizeUnit {
        B,
        KB,
        MB,
        GB,
        TB,
        Auto,
    }

    /**
     * 格式化文件大小
     *
     * @param size 文件大小
     * @return 文件大小描述文本
     */
    public static String formatFileSize(long size) {
        return formatFileSize(size, SizeUnit.Auto);
    }

    /**
     * 格式化文件大小
     *
     * @param size 文件大小
     * @param unit 大小单位
     * @return 文件大小描述文本
     */
    public static String formatFileSize(long size, SizeUnit unit) {
        final double KB = 1024;
        final double MB = KB * 1024;
        final double GB = MB * 1024;
        final double TB = GB * 1024;

        if (unit == SizeUnit.Auto) {
            if (size < KB) {
                unit = SizeUnit.B;
            } else if (size < MB) {
                unit = SizeUnit.KB;
            } else if (size < GB) {
                unit = SizeUnit.MB;
            } else if (size < TB) {
                unit = SizeUnit.GB;
            } else {
                unit = SizeUnit.TB;
            }
        }

        switch (unit) {
            default:
            case B:
                return size + "B";
            case KB:
                return String.format(Locale.US, "%.2fKB", size / KB);
            case MB:
                return String.format(Locale.US, "%.2fMB", size / MB);
            case GB:
                return String.format(Locale.US, "%.2fGB", size / GB);
            case TB:
                return String.format(Locale.US, "%.2fTB", size / TB);
        }
    }

    /**
     * 格式化文件大小
     *
     * @param size     文件大小
     * @param unit     大小单位
     * @param unitText 自定义单位文本
     * @return 文件大小描述文本
     */
    public static String formatFileSize(long size, SizeUnit unit, String unitText) {
        final double KB = 1024;
        final double MB = KB * 1024;
        final double GB = MB * 1024;
        final double TB = GB * 1024;

        if (unit == SizeUnit.Auto) {
            if (size < KB) {
                unit = SizeUnit.B;
            } else if (size < MB) {
                unit = SizeUnit.KB;
            } else if (size < GB) {
                unit = SizeUnit.MB;
            } else if (size < TB) {
                unit = SizeUnit.GB;
            } else {
                unit = SizeUnit.TB;
            }
        }

        switch (unit) {
            default:
            case B:
                return size + unitText;
            case KB:
                return String.format(Locale.US, "%.2f" + unitText, size / KB);
            case MB:
                return String.format(Locale.US, "%.2f" + unitText, size / MB);
            case GB:
                return String.format(Locale.US, "%.2f" + unitText, size / GB);
            case TB:
                return String.format(Locale.US, "%.2f" + unitText, size / TB);
        }
    }

    /**
     * 计算文件/文件夹的大小
     *
     * @param file 文件或文件夹
     * @return 文件大小
     */
    public static long calculateFileSize(File file) {
        if (file == null || !file.exists()) {
            return 0;
        }
        long result = 0;
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (null != files) {
                for (File subFile : files) {
                    if (subFile.isDirectory()) {
                        result += calculateFileSize(subFile);
                    } else {
                        result += subFile.length();
                    }
                }
            }
        }
        result += file.length();
        return result;
    }

    /**
     * SD卡是否能用
     *
     * @return true 可用,false不可用
     */
    public static boolean isSDCardAvailable() {
        try {
            return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 获取文件名
     *
     * @param filepath 文件路径
     * @return 文件名
     */
    public static String getFileName(String filepath) {
        if ((filepath != null) && (filepath.length() > 0)) {
            int sep = filepath.lastIndexOf('/');
            if ((sep > -1) && (sep < filepath.length() - 1)) {
                return filepath.substring(sep + 1);
            }
        }
        return filepath;
    }

    /**
     * 获取不带扩展名的文件名
     *
     * @param filename 文件名/路径
     * @return 不带扩展名的文件名
     */
    public static String getFileNameNoExtension(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot > -1) && (dot < (filename.length()))) {
                return filename.substring(0, dot);
            }
        }
        return filename;
    }

    /***
     * 获取文件扩展名
     *
     * @param filename 文件名/路径
     * @return 返回文件扩展名
     */
    public static String getFileExtensionName(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot > -1) && (dot < (filename.length() - 1))) {
                return filename.substring(dot + 1);
            }
        }
        return filename;
    }

    /**
     * 复制文件
     *
     * @param source 源文件
     * @param target 目标文件
     * @return 成功返回true，失败返回false
     */
    public static boolean copyFile(File source, File target) {
        FileOutputStream outputStream = null;
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(source);
            outputStream = new FileOutputStream(target);
            byte[] bytes = new byte[1024];
            int read;
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
            outputStream.flush();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /***
     * 删除文件/文件夹
     *
     * @param file 如果传入的是文件夹，将删除文件夹本身
     */
    public static void deleteFile(File file) {
        deleteFile(file, true);
    }

    /**
     * 删除文件/文件夹
     *
     * @param file         指定删除的文件/文件夹
     * @param isDeleteSelf 是否删除文件夹本身
     */
    public static void deleteFile(File file, boolean isDeleteSelf) {
        if (file == null || !file.exists()) {
            return;
        }
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                File f = files[i];
                deleteFile(f);
            }
            if (isDeleteSelf) {
                file.delete();
            }
        } else if (file.exists()) {
            file.delete();
        }
    }

    /**
     * 读取文件内容
     */
    public static String readFileText(File file) {
        if (file == null || !file.exists()) {
            return "";
        }
        return readFileText(file.getPath());
    }

    /**
     * 读取文件内容
     */
    public static String readFileText(String path) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path), 8192);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append("\n").append(line);
            }
            bufferedReader.close();
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

}