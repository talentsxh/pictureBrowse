package com.sxh.demo.SetUtils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

/**
 * ScreenUtils
 * Created by hanj on 14-9-25.
 */
public class ScreenUtils {
    private static int screenW;
    private static int screenH;

    public static int getScreenW(Context context){
        if (screenW == 0){
            initScreen(context);
        }
        return screenW;
    }

    public static int getScreenH(Context context){
        if (screenH == 0){
            initScreen(context);
        }
        return screenH;
    }

    private static void initScreen(Context context){
        DisplayMetrics metric = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(metric);
        screenW = metric.widthPixels;
        screenH = metric.heightPixels;
    }

    public static float getDensity(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.density;
    }

    /**
     * 将px值转换为dip或dp值，保证尺寸大小不变
     *
     * @param pxValue
     *            （DisplayMetrics类中属性density）
     * @return
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     *
     * @param dipValue
     *            （DisplayMetrics类中属性density）
     * @return
     */
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }




    public static String addComma(String str) {
        str = new StringBuilder(str).reverse().toString();     //先将字符串颠倒顺序
        if (str.equals("0")) {
            return str;
        }
        String str2 = "";
        for (int i = 0; i < str.length(); i++) {
            if (i * 3 + 3 > str.length()) {
                str2 += str.substring(i * 3, str.length());
                break;
            }
            str2 += str.substring(i * 3, i * 3 + 3) + ",";
        }
        if (str2.endsWith(",")) {
            str2 = str2.substring(0, str2.length() - 1);
        }
        //最后再将顺序反转过来
        String temp = new StringBuilder(str2).reverse().toString();
        //将最后的,去掉
        return temp.substring(0, temp.lastIndexOf(",")) + temp.substring(temp.lastIndexOf(",") + 1, temp.length());
    }
}
