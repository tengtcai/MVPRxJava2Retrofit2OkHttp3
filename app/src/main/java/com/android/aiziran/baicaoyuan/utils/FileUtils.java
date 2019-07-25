package com.android.aiziran.baicaoyuan.utils;

import android.content.Context;
import android.os.Environment;

public class FileUtils {
    /**
     * 获取缓存地址
     * @param context
     * @return
     */
    public static String getDiskCacheDir(Context context) {
        String cachePath = null;
        //Environment.getExtemalStorageState() 获取SDcard的状态
        //Environment.MEDIA_MOUNTED 手机装有SDCard,并且可以进行读写
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return cachePath;
    }
}
