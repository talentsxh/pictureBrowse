package com.sxh.picturebrowse.viewdialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloader;
import com.sxh.picturebrowse.R;
import com.sxh.picturebrowse.picutils.ToastAlert;

/**
 * by sxh 下载进度框
 */
public class DownLoadDialog extends Dialog {
    private LinearLayout mCancelLayout;
    private BaseDownloadTask mTask;
    private TextView mContentTx;
    private Button mCancelBtn;
    private Context mContext;
    private int mStartSize = 0;
    private String mType;
    private int mSize;


    public DownLoadDialog(@NonNull Context context) {
        super(context, R.style.ActionDialogStyle);
        setContentView(R.layout.down_load_item);
        this.mContext = context;
        setCancelable(false);
        //获取当前Activity所在的窗体
        Window dialogWindow = getWindow();
        //设置Dialog从窗体中部弹出
        dialogWindow.setGravity(Gravity.CENTER);
        /**
         * 设置宽度全屏，要设置在show的后面
         */
        WindowManager.LayoutParams layoutParams = dialogWindow.getAttributes();
        layoutParams.gravity = Gravity.CENTER;
        layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        dialogWindow.setAttributes(layoutParams);
        initView();
    }


    private void initView() {
        mContentTx = (TextView) findViewById(R.id.content_tv);
        mCancelBtn=findViewById(R.id.save_cancel_btn);
        mCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mTask!=null) {
                    mTask.pause();
                }
                FileDownloader.getImpl().unBindService();
                ToastAlert.showCorrectMsg("取消成功");
                dismiss();
            }
        });
        mCancelLayout=findViewById(R.id.save_cancel_layout);
    }

    public void setCancelLayoutVisiable(boolean visiable){
        if (mCancelLayout!=null){
            if (visiable) {
                mCancelLayout.setVisibility(View.VISIBLE);
            }else {
                mCancelLayout.setVisibility(View.GONE);
            }
        }
    }

    /**
     * @param type 下载类型  2图片 其他视频
     * @param size 个数
     */
    public void setContentTx(String type, int size) {
        this.mType = type;
        this.mSize = size;
        if ("2".equals(type)) {
            mContentTx.setText("图片保存中(0/" + size + ")");
        } else {
            mContentTx.setText("视频保存中(0%)");
        }
    }

    public void setProgress(int complete, int soFarBytes, int totalBytes, BaseDownloadTask task) {
        mTask=task;
        mStartSize = mStartSize + complete;
        if ("2".equals(mType)) {
            mContentTx.setText("图片保存中(" + mStartSize + "/" + mSize + ")");
        } else {
            int pro = (int) (((float) soFarBytes / totalBytes) * 100);
            mContentTx.setText("视频保存中(" + pro + "%)");
        }

        if (mStartSize == mSize) {
            if (mType.equals("2")) {
                ToastAlert.showCorrectMsg("图片保存成功");
            }else{
                ToastAlert.showCorrectMsg("视频保存成功");
            }
            dismiss();
        }
    }

    public void setFail(){
        ToastAlert.showCorrectMsg("图片保存失败");
        dismiss();
    }

}
