package com.sxh.picturebrowse.picutils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sxh.picturebrowse.R;


/**
 * @author ClamLaw
 * @time 2017/04/05  15:22
 * @desc Toast工具类
 */
public class ToastAlert {

    private static Context mContext;
    private static Toast mToast;
    private static ImageView sImgType;
    private static TextView sContentTx;

    public static void init(Context context) {
        ToastAlert.mContext = context;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.toast_item, null);
        sImgType = (ImageView) view.findViewById(R.id.img);
        sContentTx = (TextView) view.findViewById(R.id.content_tx);
        mToast = new Toast(mContext);
        mToast.setGravity(Gravity.CENTER, 0, 0);
        mToast.setDuration(Toast.LENGTH_SHORT);
        mToast.setView(view);
    }

    /**
     * String类型的参数
     *
     * @param msg
     */
    public static void showMsg(String msg) {
        Toast toast = Toast.makeText(mContext, msg, Toast.LENGTH_SHORT);
        toast.setText(msg);
        toast.show();
    }

    /**
     * int类型的参数
     *
     * @param resid
     */
    public static void showMsg(int resid) {
        Toast toast = Toast.makeText(mContext, resid, Toast.LENGTH_SHORT);
        toast.setText(resid);
        toast.show();
    }

    /**
     * 错误的提示
     *
     * @param msg
     */
    public static void showErrorMsg(String msg) {
        sImgType.setVisibility(View.VISIBLE);
        sImgType.setImageResource(R.drawable.tishi_shibai_icon);
        sContentTx.setText(msg);
        mToast.show();
    }
    public static void showMsg2(String msg) {
        sImgType.setVisibility(View.GONE);
        sContentTx.setText(msg);
        mToast.show();
    }
    /**
     * 正确的提示
     *
     * @param msg
     */
    public static void showCorrectMsg(String msg) {
        sImgType.setVisibility(View.VISIBLE);
        sImgType.setImageResource(R.drawable.tishi_wancheng_icon);
        sContentTx.setText(msg);
        mToast.show();
    }

    /**
     * 正确的提示
     *
     * @param msg
     */
    public static void showSimpleMsg(String msg) {
        sImgType.setVisibility(View.GONE);
        sContentTx.setText(msg);
        mToast.show();
    }


    /**
     * 取消Toast
     */
    public static void cancel() {
        if (mToast != null) {
            mToast.cancel();
        }
    }

}
