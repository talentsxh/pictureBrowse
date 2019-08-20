package com.sxh.picturebrowse.picmain;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import com.alexvasilkov.gestures.Settings;
import com.alexvasilkov.gestures.animation.ViewPosition;
import com.alexvasilkov.gestures.animation.ViewPositionAnimator;
import com.alexvasilkov.gestures.views.GestureImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.ImageViewState;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.sxh.picturebrowse.R;
import com.sxh.picturebrowse.picutils.AllUtils;
import com.sxh.picturebrowse.viewdialog.ImgSelectDialog;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView.ZOOM_FOCUS_CENTER_IMMEDIATE;

/**
 * by sxh
 */

public class ImageDetailFragment extends Fragment {
    private ArrayList<String> mViewPosition=new ArrayList<>();
    private List<String> mImages =new ArrayList<>();
    private GestureImageView mGestureImageView;
    private SubsamplingScaleImageView mScaleImageView;
    private ViewPager mViewPager;
    private boolean isNewCreate = false;
    private boolean isVisible = false;//是否第一次加载完成，是否可见。
    private boolean mIsFail=false;
    private int PERMISSIONS_WRITE = 1;
    private String mImageUrl="";
    private int mSelectPosition;
    private int mSelectType =1;//1为普通图，2为长图加载模式

    public void setViewPosition(ArrayList<String> viewPosition, int selectPosition) {
        mViewPosition = viewPosition;
        this.mSelectPosition =selectPosition;
    }


    public void setViewPager(ViewPager viewPager) {
        mViewPager = viewPager;
    }

    public static ImageDetailFragment newInstance(String imageUrl,List<String> images) {
        final ImageDetailFragment f = new ImageDetailFragment();
        final Bundle args = new Bundle();
        args.putString("url", imageUrl);
        args.putStringArrayList("images", (ArrayList<String>) images);
        f.setArguments(args);
        return f;
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isVisible = isVisibleToUser;
        if (isVisibleToUser) {
            initData();
        } else {
            isNewCreate = false;
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    public void controllerViewPager(int type){
        if (type==1) {
            if (mViewPager!=null && mGestureImageView!=null && mGestureImageView.getVisibility()==View.VISIBLE) {
                mGestureImageView.getController().disableViewPager(false);
                mGestureImageView.getController().enableScrollInViewPager(mViewPager);
            }
        }else{
            //一定要，否则超长图和长图切换会卡顿
            if (mViewPager!=null) {
                mViewPager.setOnTouchListener(null);
            }
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (getActivity() instanceof OnLoadListener) {
            onLoadListener = (OnLoadListener) getActivity();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onLoadListener = null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mImages =bundle.getStringArrayList("images");
            mImageUrl=bundle.getString("url", "");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.image_detail_fragment, container, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mScaleImageView=(SubsamplingScaleImageView)view.findViewById(R.id.scale_image);
        mScaleImageView.setMaxScale(AllUtils.getmMaxZoom());
        mGestureImageView = (GestureImageView)view.findViewById(R.id.gesture_image);
        mGestureImageView.getController().getSettings().setRotationEnabled(AllUtils.ismRotationEnabled());
        mGestureImageView.getController().getSettings().setRestrictRotation(true);
        mGestureImageView.getController().getSettings().setMaxZoom(AllUtils.getmMaxZoom());
        mGestureImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mGestureImageView.getPositionAnimator().addPositionUpdateListener(new ViewPositionAnimator.PositionUpdateListener() {
            @Override
            public void onPositionUpdate(float position, boolean isLeaving) {
                // Exit animation is finished,下面这个mGb和mGic的setVisibility会不断的调用，看能不能进行优化
                if (mGestureImageView.getVisibility()==View.VISIBLE) {
                    boolean isFinished = position == 0f && isLeaving;
                    mGestureImageView.setVisibility(isFinished ? View.INVISIBLE : View.VISIBLE);
                    if (isFinished) {
                        //下面两行代码是为了退出时的流畅效果，避免出现闪烁
                        mGestureImageView.setOnClickListener(null);
                        mGestureImageView.getController().getSettings().disableBounds();
                        mGestureImageView.getPositionAnimator().setState(0f, false, false);
                        if (getActivity() != null) {
                            getActivity().finish();
                            getActivity().overridePendingTransition(0, 0);
                        }
                    }
                }
            }
        });

        runAfterImageDraw(savedInstanceState, mSelectPosition);
    }

    private void enterFullImage(boolean animate, int positions) {
        // 播放从提供的位置输入动画
        if (mViewPosition!=null && mViewPosition.size()>positions) {
            ViewPosition position = ViewPosition.unpack(mViewPosition.get(positions));
            mGestureImageView.getPositionAnimator().enter(position, animate);
        }
    }



    private void runAfterImageDraw(final Bundle savedInstanceState, final int position) {
        mGestureImageView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                mGestureImageView.getViewTreeObserver().removeOnPreDrawListener(this);
                // 只有当activity不是从保存状态创建时，才应该播放动画
                enterFullImage(savedInstanceState == null, position);
                return true;
            }
        });
        mGestureImageView.invalidate();

    }


    public void onBackPressed() {
        if (mViewPager!=null) {
            mViewPager.setBackgroundColor(0x00000000);
        }
        if (mGestureImageView !=null && !mGestureImageView.getPositionAnimator().isLeaving()
                && mGestureImageView.getVisibility()==View.VISIBLE) {
            mGestureImageView.getPositionAnimator().exit(true);
        }else{
            if (getActivity()!=null) {
                getActivity().finish();
                getActivity().overridePendingTransition(0, 0);
            }
        }

    }

    //切换页面执行
    public void updateFrom(int positions){
        if (mGestureImageView !=null && mViewPosition.size()>positions){
            mGestureImageView.getPositionAnimator().update(ViewPosition.unpack(mViewPosition.get(positions)));
            controllerViewPager(mSelectType);
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isNewCreate = true;//布局新创建
        initData();
        mGestureImageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mImages !=null && mImages.size()>0 && AllUtils.ismCanDown()) {
                    saveAllPics(mImages);
                }
                return true;
            }
        });

        mScaleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity()!=null) {
                    getActivity().finish();
                    getActivity().overridePendingTransition(0, 0);
                }
            }
        });
        mScaleImageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mImages !=null && mImages.size()>0 && AllUtils.ismCanDown()) {
                    saveAllPics(mImages);
                }
                return true;
            }
        });
    }


    public  void saveAllPics(List<String> images){
        checkPermission();
        ImgSelectDialog imgSelectDialog = new ImgSelectDialog(getActivity());
        imgSelectDialog.setAllImg(images);
        imgSelectDialog.setOneImg(mImageUrl);
        imgSelectDialog.show();
    }

    public void checkPermission(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        PERMISSIONS_WRITE);
            }
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        if (requestCode == PERMISSIONS_WRITE) {
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                checkPermission();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    private void initData() {
        if (!isVisible || !isNewCreate) {
            if (mIsFail){
                if (onLoadListener != null) {
                    onLoadListener.onLoadFailed();
                }
            }else{
                if (onLoadListener != null) {
                    onLoadListener.onLoadSuccess();
                }
            }
            return;
        }
        if (onLoadListener != null) {
            onLoadListener.onLoadStart();
        }
        RequestOptions options=new RequestOptions();
        if (AllUtils.ismIsGif()) {
            options.skipMemoryCache(true);
        }else{
            options.skipMemoryCache(false);
        }

        Glide.with(getActivity())
                .load(mImageUrl)
                .apply(options)
                .into(new com.bumptech.glide.request.target.Target<Drawable>() {


                    @Override
                    public void onStart() {
                        if (AllUtils.ismIsGif()) {
                            if (onLoadListener != null) {
                                onLoadListener.onLoadStart();
                            }
                        }
                    }

                    @Override
                    public void onStop() {
                        if (onLoadListener != null) {
                            onLoadListener.onLoadSuccess();
                        }
                    }

                    @Override
                    public void onDestroy() {

                    }

                    @Override
                    public void onLoadStarted(@Nullable Drawable placeholder) {

                    }

                    @Override
                    public void onLoadFailed(@Nullable Drawable errorDrawable) {
                        ActivityCompat.startPostponedEnterTransition(getActivity());
                        //出错后直接设置一次，判断是否成功
                        Glide.with(getActivity()).load(mImageUrl).into(mGestureImageView);
                        mGestureImageView.setVisibility(View.VISIBLE);
                        controllerViewPager(mSelectType);
                        mGestureImageView.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if (mGestureImageView!=null && mGestureImageView.getDrawable()==null) {
                                    if (onLoadListener != null) {
                                        onLoadListener.onLoadFailed();
                                    }
                                    mIsFail=true;
                                }else{
                                    if (onLoadListener != null) {
                                        onLoadListener.onLoadSuccess();
                                    }
                                }
                            }
                        },2000);

                    }

                    @SuppressLint("ClickableViewAccessibility")
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        Log.e("pic",resource.getIntrinsicWidth()+"--"+resource.getIntrinsicHeight());
                        //当图片高度大于屏幕高度，并且图片高度小于最大设置高度，并且宽度小于高度
                        if (resource.getIntrinsicHeight()> AllUtils.getScreenHeight(getActivity())
                                && resource.getIntrinsicHeight()< AllUtils.getmPicSizeLong()
                                && resource.getIntrinsicWidth()< AllUtils.getScreenHeight((getActivity()))){
                            mSelectType =1;
                            mGestureImageView.getController().getSettings().setFillViewport(true);
                            mGestureImageView.getController().getSettings().setGravity(Gravity.TOP);
                            mGestureImageView.getController().getSettings().setFitMethod(Settings.Fit.OUTSIDE);
                            //用url重新加载是为了防止load Drawable 出错，用view直接设置图就不支持动图
                            if (AllUtils.ismIsGif()) {
                                RequestOptions options = new RequestOptions();
                                options.skipMemoryCache(true);
                                try {
                                    Glide.with(getActivity()).load(resource).apply(options).into(mGestureImageView);
                                } catch (Exception e) {
                                    Glide.with(getActivity()).load(mImageUrl).apply(options).into(mGestureImageView);
                                }
                            }else{
                                mGestureImageView.setImageDrawable(resource);
                            }
                            mGestureImageView.setVisibility(View.VISIBLE);
                            mScaleImageView.setVisibility(View.GONE);
                            if (onLoadListener != null) {
                                onLoadListener.onLoadSuccess();
                            }
                        }else if (resource.getIntrinsicHeight()> AllUtils.getmPicSizeLong()  || resource.getIntrinsicWidth()> AllUtils.getmPicSizeLong()){
                           //判断当高度大于最大设置参数，或者宽度大于最大设置参数，宽长图或者高长图
                            mSelectType =2;
                            mGestureImageView.setVisibility(View.GONE);
                            mScaleImageView.setVisibility(View.VISIBLE);
                            loadLong();
                        }else{
                            //其他例如正方形，长方形图不超最大参数的都用这种
                            mSelectType =1;
                            mGestureImageView.setVisibility(View.VISIBLE);
                            mScaleImageView.setVisibility(View.GONE);
                            //设置去掉缓存的原因是因为动图情况下会读取bitmap出错。
                            if (AllUtils.ismIsGif()) {
                                RequestOptions options = new RequestOptions();
                                options.skipMemoryCache(true);
                                try {
                                    Glide.with(getActivity()).load(resource).apply(options).into(mGestureImageView);
                                } catch (Exception e) {
                                    Glide.with(getActivity()).load(mImageUrl).apply(options).into(mGestureImageView);
                                }
                            }else{
                                mGestureImageView.setImageDrawable(resource);
                            }
                            if (onLoadListener != null) {
                                onLoadListener.onLoadSuccess();
                            }
                        }
                        controllerViewPager(mSelectType);

                    }


                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }

                    @Override
                    public void getSize(@NonNull SizeReadyCallback cb) {
                        cb.onSizeReady(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL);
                    }

                    @Override
                    public void removeCallback(@NonNull SizeReadyCallback cb) {

                    }

                    @Override
                    public void setRequest(@Nullable Request request) {

                    }

                    @Nullable
                    @Override
                    public Request getRequest() {
                        return null;
                    }


                });

    }

    @SuppressLint("CheckResult")
    public void loadLong(){
        Glide.with(getActivity())
                .load(mImageUrl)
                .downloadOnly(new com.bumptech.glide.request.target.Target<File>() {

                    @Override
                    public void onStart() {
                        if (onLoadListener != null) {
                            onLoadListener.onLoadStart();
                        }
                    }

                    @Override
                    public void onStop() {
                        if (onLoadListener != null) {
                            onLoadListener.onLoadSuccess();
                        }
                    }

                    @Override
                    public void onDestroy() {

                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }

                    @Override
                    public void onLoadStarted(@Nullable Drawable placeholder) {

                    }

                    @Override
                    public void onLoadFailed(Drawable errorDrawable) {
                        ActivityCompat.startPostponedEnterTransition(getActivity());
                        if (onLoadListener != null) {
                            onLoadListener.onLoadFailed();
                        }
                        mScaleImageView.setVisibility(View.VISIBLE);
                        controllerViewPager(mSelectType);
                        mIsFail=true;
                    }

                    @Override
                    public void onResourceReady(@NonNull File resource, @Nullable Transition<? super File> transition) {
                        // 将保存的图片地址给SubsamplingScaleImageView,这里注意设置ImageViewState设置初始显示比例
                        ImageSource imageSource = ImageSource.uri(Uri.fromFile(resource));
                        int sWidth = BitmapFactory.decodeFile(resource.getAbsolutePath()).getWidth();
                        int sHeight = BitmapFactory.decodeFile(resource.getAbsolutePath()).getHeight();
                        Log.e("picLong",sWidth+"-----"+sHeight);
                        int width = AllUtils.getScreenWidth(getActivity());
                        int height = AllUtils.getScreenHeight(getActivity());
                        float scaleW = width / (float) sWidth;
                        //float scaleH = height / (float) sHeight;
                        if (sHeight >= height
                                && sHeight / sWidth >= height / width) {
                            mScaleImageView.setMinimumScaleType(SubsamplingScaleImageView.SCALE_TYPE_CENTER_CROP);
                            mScaleImageView.setImage(imageSource, new ImageViewState(scaleW, new PointF(0, 0), 0));
                        } else {
                            mScaleImageView.setMinimumScaleType(SubsamplingScaleImageView.SCALE_TYPE_CUSTOM);
                            mScaleImageView.setImage(imageSource);
                            mScaleImageView.setDoubleTapZoomStyle(ZOOM_FOCUS_CENTER_IMMEDIATE);
                        }

                        if (onLoadListener != null) {
                            onLoadListener.onLoadSuccess();
                        }
                    }

                    @Override
                    public void getSize(@NonNull SizeReadyCallback cb) {
                        cb.onSizeReady(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL);
                    }

                    @Override
                    public void removeCallback(@NonNull SizeReadyCallback cb) {

                    }

                    @Override
                    public void setRequest(@Nullable Request request) {

                    }

                    @Nullable
                    @Override
                    public Request getRequest() {
                        return null;
                    }
                });

    }

    public interface OnLoadListener {
        void onLoadStart();

        void onLoadSuccess();

        void onLoadFailed();
    }

    private OnLoadListener onLoadListener;

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (onLoadListener != null) {
            onLoadListener.onLoadFailed();
        }
    }

}
