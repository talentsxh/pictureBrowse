package com.sxh.picturebrowse.viewdialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.sxh.picturebrowse.R;
import com.sxh.picturebrowse.picutils.DownManagerUtil;

import java.util.ArrayList;
import java.util.List;

public class ImgSelectDialog extends Dialog {
    private final Context mContext;
    private List<String> mImages=new ArrayList<>();
    private List<String> mOneImages=new ArrayList<>();
    private LinearLayout mSaveLayout;
    private LinearLayout mSaveAllLayout;
    private LinearLayout mDismissLayout;


    public ImgSelectDialog(@NonNull Context context) {
        super(context, R.style.ActionSheetDialogStyle);
        setContentView(R.layout.img_info_item);
        this.mContext = context;
        //获取当前Activity所在的窗体
        Window dialogWindow = getWindow();
        //设置Dialog从窗体中部弹出
        dialogWindow.setGravity(Gravity.BOTTOM);
        /**
         * 设置宽度全屏，要设置在show的后面
         */
        WindowManager.LayoutParams layoutParams = dialogWindow.getAttributes();
        layoutParams.gravity = Gravity.BOTTOM;
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        dialogWindow.setAttributes(layoutParams);
        initView();
    }

    public void saveOne(){
        if (mOneImages!=null &&  mOneImages.size()>0) {
            DownManagerUtil downManagerUtil = new DownManagerUtil(mContext);
            downManagerUtil.downLoad(mOneImages, "2");
        }
    }

    public void saveAll(){
        if (mImages!=null &&  mImages.size()>0) {
            DownManagerUtil downManagerUtil = new DownManagerUtil(mContext);
            downManagerUtil.downLoad(mImages, "2");
        }
    }

    private void initView() {
        mSaveLayout=findViewById(R.id.save_layout);
        mSaveAllLayout=findViewById(R.id.save_all_layout);
        mDismissLayout=findViewById(R.id.dismiss_layout);
        mSaveLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveOne();
                dismiss();
            }
        });
        mSaveAllLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveAll();
                dismiss();
            }
        });
        mDismissLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

    }


    public void setImages(List<String> images) {
        //如果为空则隐藏下载图片
        if (images == null || images.size()==0) {
            mSaveLayout.setVisibility(View.GONE);
            mSaveAllLayout.setVisibility(View.GONE);
        }
        if (images!=null && images.size()<2){
            mSaveAllLayout.setVisibility(View.GONE);
        }
    }


    public  void setAllImg(List<String> images){
        mImages.clear();
        mImages.addAll(images);
        setImages(images);
    }

    public void setOneImg(String onePic){
        mOneImages.clear();
        if (onePic!=null &&onePic.length()>0) {
            mOneImages.add(onePic);
        }
    }
}
