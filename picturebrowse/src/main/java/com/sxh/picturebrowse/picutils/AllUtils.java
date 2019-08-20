package com.sxh.picturebrowse.picutils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.ImageView;

import com.alexvasilkov.gestures.animation.ViewPosition;
import com.sxh.picturebrowse.picmain.ImagePagerActivity;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AllUtils {
    private static int screenW;
    private static int screenH;
    //设置超长图的最大尺寸
    private static int mPicSizeLong=6000;
    //设置是否支持gif，true支持
    private static boolean mIsGif=true;
    //设置是否可以弹窗下载
    private static boolean mCanDown=true;
    //设置最大放大倍数
    private static float mMaxZoom=6.0f;
    //设置普通图下是否可以旋转
    private static boolean mRotationEnabled=true;
    //文件保存路径
    public static final String DOWN_PATH = Environment.getExternalStorageDirectory().getPath() + File.separator + "imagebrowse";


    public static int getmPicSizeLong() {
        return mPicSizeLong;
    }

    public static void setmPicSizeLong(int mPicSizeLong) {
        AllUtils.mPicSizeLong = mPicSizeLong;
    }

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

    public static boolean ismCanDown() {
        return mCanDown;
    }

    public static void setmCanDown(boolean mCanDown) {
        AllUtils.mCanDown = mCanDown;
    }

    public static boolean ismIsGif() {
        return mIsGif;
    }

    public static void setmIsGif(boolean mIsGif) {
        AllUtils.mIsGif = mIsGif;
    }

    public static float getmMaxZoom() {
        return mMaxZoom;
    }

    public static void setmMaxZoom(float mMaxZoom) {
        AllUtils.mMaxZoom = mMaxZoom;
    }

    public static boolean ismRotationEnabled() {
        return mRotationEnabled;
    }

    public static void setmRotationEnabled(boolean mRotationEnabled) {
        AllUtils.mRotationEnabled = mRotationEnabled;
    }

    private static void initScreen(Context context){
        DisplayMetrics metric = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(metric);
        screenW = metric.widthPixels;
        screenH = metric.heightPixels;
    }


    private AllUtils()
    {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 获得屏幕高度
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context)
    {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    /**
     * 获得屏幕宽度
     *
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context)
    {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }



    public static void startImagePage(Activity context, List<String> urls, List<ImageView> imageViews, int pos) {
        ArrayList<String> viewPositions = new ArrayList<>();
        for (ImageView imageView : imageViews) {
            if (imageView == null)
                break;
            ViewPosition viewPosition = ViewPosition.from(imageView);
            viewPositions.add(viewPosition.pack());
        }
        Intent intent = new Intent(context, ImagePagerActivity.class);
        // 图片url,从数据库中或网络中获取
        intent.putExtra("image_urls", (Serializable) urls);
        intent.putExtra("image_index", pos);
        intent.putStringArrayListExtra("positions",viewPositions);
        context.startActivity(intent);
        context.overridePendingTransition(0, 0);
    }


    public static void startImagePage(Activity context, String urls,ImageView imageViews) {
        ArrayList<String> viewPositions = new ArrayList<>();
        if (imageViews == null)
            return;
        ViewPosition viewPosition = ViewPosition.from(imageViews);
        viewPositions.add(viewPosition.pack());
        Intent intent = new Intent(context, ImagePagerActivity.class);
        // 图片url,从数据库中或网络中获取
        if (!TextUtils.isEmpty(urls)){
            ArrayList<String> urlList=new ArrayList<>();
            urlList.clear();
            urlList.add(urls);
            intent.putExtra("image_urls", urlList);
        }else{
            return;
        }
        intent.putExtra("image_index", 0);
        intent.putStringArrayListExtra("positions",viewPositions);
        context.startActivity(intent);
        context.overridePendingTransition(0, 0);
    }

    private static BackListeners mBackListeners;

    public static BackListeners getBackListeners() {
        return mBackListeners;
    }

    public static void setBackListeners(BackListeners backListeners) {
        mBackListeners = backListeners;
    }

    public interface BackListeners{
        void backMethod(int arg0);
    }



}
