package com.android.aiziran.baicaoyuan.utils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.content.ContextCompat;

/**
 * Created by Administrator on 2018/1/30.
 */
public class PermissionUtils {
    //检查定位权限
    public static boolean checkeLocationPer(Activity context){
        if(ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(context,Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED) {
            String[] perLocation = new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION};
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                context.requestPermissions(perLocation, Constance.PERMISSION_LOCATION);
            }
            return false;
        }
        return true;
    }

    //检查文件操作权限
    public static boolean checkFilePer(Activity context){
        if(ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED) {
            String[] perLocation = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                context.requestPermissions(perLocation, Constance.PERMISSION_WRITE_FILE);
            }
            return false;
        }
        return true;
    }

    /**
     * 向系统日历中添加日程事件权限检查
     * @param mContext
     * @return
     */
    public static boolean checkRemindPer(Activity mContext){
        if(ContextCompat.checkSelfPermission(mContext,Manifest.permission.READ_CALENDAR)!= PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(mContext,Manifest.permission.WRITE_CALENDAR)!= PackageManager.PERMISSION_GRANTED) {
            String[] perRemind = new String[]{Manifest.permission.WRITE_CALENDAR,Manifest.permission.READ_CALENDAR};
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                mContext.requestPermissions(perRemind, Constance.PERMISSION_REMIND);
            }
            return false;
        }
        return true;
    }

    /**
     * 拨打电话的权限
     */
    public static boolean checkPhonePer(Activity mContext){
        if(ContextCompat.checkSelfPermission(mContext,Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED) {
            String[] perRemind = new String[]{Manifest.permission.CALL_PHONE};
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                mContext.requestPermissions(perRemind, Constance.PERMISSION_PHONE);
            }
            return false;
        }
        return true;
    }

    /**
     * 录音的权限
     */
    public static boolean checkRecording(Activity mContext){
        if(ContextCompat.checkSelfPermission(mContext,Manifest.permission.RECORD_AUDIO)!= PackageManager.PERMISSION_GRANTED) {
            String[] perRemind = new String[]{Manifest.permission.RECORD_AUDIO};
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                mContext.requestPermissions(perRemind, Constance.PERMISSION_RECORDING);
            }
            return false;
        }
        return true;
    }

    /**
     * 拍照的权限
     */
    public static boolean checkCamera(Activity mContext){
        if(ContextCompat.checkSelfPermission(mContext,Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(mContext,Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(mContext,Manifest.permission.RECORD_AUDIO)!= PackageManager.PERMISSION_GRANTED) {
            String[] perRemind = new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.RECORD_AUDIO};
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                mContext.requestPermissions(perRemind, Constance.PERMISSION_CAMERA);
            }
            return false;
        }
        return true;
    }


    /**
     * 未知来源安装权限
     */
    public static boolean checkRequstInstallPackages(Activity mContext){
        if(ContextCompat.checkSelfPermission(mContext,Manifest.permission.REQUEST_INSTALL_PACKAGES)!= PackageManager.PERMISSION_GRANTED) {
            String[] perRemind = new String[]{Manifest.permission.REQUEST_INSTALL_PACKAGES};
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                mContext.requestPermissions(perRemind, Constance.PERMISSION_REQUEST_INSTALL_PACKAGES);
            }
            return false;
        }
        return true;
    }

}
