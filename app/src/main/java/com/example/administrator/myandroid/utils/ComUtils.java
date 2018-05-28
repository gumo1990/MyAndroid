package com.example.administrator.myandroid.utils;

import android.content.Context;
import android.provider.Settings;
import android.widget.Toast;

/**
 * Created by whq on 2018/5/28.
 * 工具类，封装一些公共方法
 */

public class ComUtils {

    /**
     * dp转换成px
     */
    public static int dp2px(Context context, float dpValue){
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue*scale + 0.5f);
    }
    /**
     * px转换成dp
     */
    public static int px2dp(Context context, float pxValue){
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static void toast(Context context, String msg){
        String oldMsg = null;
        Toast toast = null;
        long firstTime = 0;
        long secondTime = 0;
        if(toast == null){
            toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
            toast.show();
            firstTime = System.currentTimeMillis();
        }else{
            secondTime = System.currentTimeMillis();
            if(msg.equals(oldMsg)){
                if(secondTime - firstTime > Toast.LENGTH_SHORT){
                    toast.show();
                }
            }else{
                oldMsg = msg;
                toast.setText(msg);
                toast.show();
            }
        }
        firstTime = secondTime;
    }
}
