package com.android.aiziran.baicaoyuan.utils;

import android.app.Activity;
import android.content.Context;

/**
 * Created by 韩健强 on 2018-01-28.
 */

public class BufferDialogUtil {
    public static BufferDialog bufferDialog;
    public static void showBufferDialog(Context context){
        if(((Activity)context).isFinishing() || context == null){
            return;
        }
        try {
            if (bufferDialog == null) {
                bufferDialog = new BufferDialog(context);
            }
            bufferDialog.show();
        }catch (Exception e){e.printStackTrace();bufferDialog = null;}
    }
    public static void showBufferDialog(Context context,boolean canDissmiss){
        if(((Activity)context).isFinishing() || context == null){
            return;
        }
        try {
            if (bufferDialog == null) {
                bufferDialog = new BufferDialog(context);
            }
            bufferDialog.show();
            bufferDialog.canDissmis(canDissmiss);
        }catch (Exception e){e.printStackTrace();bufferDialog = null;}
    }
    public static void dismissBufferDialog(){
        if(bufferDialog != null){
            bufferDialog.dismiss();
            bufferDialog = null;
        }
    }
}
