package com.android.aiziran.baicaoyuan.utils;

import android.text.TextUtils;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.Utils;

/**
 * SharedPreferences使用工具类
 */
public class SpUtils {
    static SPUtils mSPUtils = SPUtils.getInstance(Utils.getApp().getPackageName());

    public static boolean isFirstStart() {
        return !mSPUtils.getBoolean("FirstStart", false);
    }

    public static void appStarted() {
        mSPUtils.put("FirstStart", true);
    }

    public static void saveUserInfo() {

    }

    public static void deleteUserInfo() {
        saveUserID(""); //userid
        saveUserName("");//用户姓名
        saveUserPhone("");//用户手机号
        saveUserPhoto("");//用户头像
        saveUserSex(0);//用户性别
        saveUserSig("");
        saveKeFu("");
    }

    public static void saveUserID(String id) {
        mSPUtils.put("uid", id);
    }

    public static String getUID() {
        return mSPUtils.getString("uid");
    }

    public static void saveUserName(String name) {
        mSPUtils.put("user_name", name);
    }

    public static String getUserName() {
        return mSPUtils.getString("user_name");
    }

    public static void saveUserPhone(String phone) {
        mSPUtils.put("user_phone", phone);
    }

    public static String getUserPhone() {
        return mSPUtils.getString("user_phone");
    }

    public static boolean isLogined() {
        return !TextUtils.isEmpty(getUID());
    }

    public static void saveUserType(int type){
        mSPUtils.put("user_type", type);
    }

    public static int getUserType(){
        return mSPUtils.getInt("user_type");
    }

    public static void saveUserPhoto(String photo){
        mSPUtils.put("user_photo", photo);
    }

    public static String getUserPhoto(){
        return mSPUtils.getString("user_photo");
    }

    public static void saveToday(long today){
        mSPUtils.put("today",today);
    }

    public static long getToday(){
        return mSPUtils.getLong("today");
    }

    public static void saveUserSex(int sex){
        mSPUtils.put("user_sex",sex);
    }

    public static int getUserSex(){
        return mSPUtils.getInt("user_sex");
    }

    public static void saveUserSig(String userSig){
        mSPUtils.put("userSig",userSig);
    }

    public static String getUserSig(){
        return mSPUtils.getString("userSig");
    }

    public static void saveKeFu(String kefu){
        mSPUtils.put("kefu",kefu);
    }

    public static String getKeFu(){
        return mSPUtils.getString("kefu");
    }

    public static void saveUpdataTimeRemark(String remark,String key){
        mSPUtils.put("updataTime"+key,remark);
    }

    public static String getUapdataTimeRemark(String key){
        return mSPUtils.getString("updataTime"+key);
    }
}
