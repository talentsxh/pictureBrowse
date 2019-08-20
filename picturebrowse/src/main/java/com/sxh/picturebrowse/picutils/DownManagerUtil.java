package com.sxh.picturebrowse.picutils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadListener;
import com.liulishuo.filedownloader.FileDownloader;
import com.sxh.picturebrowse.viewdialog.DownLoadDialog;

import java.io.File;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ClamLaw
 * @time 2018/8/29  17:31
 * @desc 批量下载工具
 */
public class DownManagerUtil {
    private Context mContext;
    private DownLoadDialog mDownLoadDialog;
    private boolean mIsCancel=false;//false不显示取消
    public DownManagerUtil(Context context) {
        this.mContext = context;
    }

    public boolean isCancel() {
        return mIsCancel;
    }

    public void setCancel(boolean cancel) {
        mIsCancel = cancel;
    }

    /**
     * 下载文件
     *
     * @param urls 下载地址
     * @param type 2表示图片 ，3表示视频
     */
    public void downLoad(List<String> urls, String type) {
        if (urls == null || urls.size() == 0) {
            ToastAlert.showErrorMsg("没有图片或视频可下载");
            return;
        }
        mDownLoadDialog = new DownLoadDialog(mContext);
        mDownLoadDialog.setContentTx(type, urls.size());
        mDownLoadDialog.setCancelLayoutVisiable(mIsCancel);
        mDownLoadDialog.show();

        String path = AllUtils.DOWN_PATH + File.separator + System.currentTimeMillis();
        for (int i = 0; i < urls.size(); i++) {
            String pastStr = path + i;
            String suffixes="avi|mpeg|3gp|mp3|mp4|wav|jpeg|gif|jpg|png|apk|exe|pdf|rar|zip|docx|doc";
            Pattern pat= Pattern.compile("[\\w]+[\\.]("+suffixes+")");//正则判断
            Matcher mc=pat.matcher(urls.get(i));//条件匹配
            String substring="jpg";
            while(mc.find()){
                substring = mc.group();//截取文件名后缀名
                Log.e("substring:", substring);
            }
            if (substring.contains(".") && substring.substring(substring.lastIndexOf("."),substring.length()).equals(".gif")){
                FileDownloader.getImpl().create(urls.get(i)).setPath("2".equals(type) ? pastStr + ".gif" : pastStr + ".mp4")
                        //.setCallbackProgressTimes(urls.size()) // 由于是队列任务, 这里是我们假设了现在不需要每个任务都回调`FileDownloadListener#progress`, 我们只关系每个任务是否完成, 所以这里这样设置可以很有效的减少ipc.
                        .setListener(queueTarget)
                        .asInQueueTask()
                        .enqueue();
            }else{
                FileDownloader.getImpl().create(urls.get(i)).setPath("2".equals(type) ? pastStr + ".jpg" : pastStr + ".mp4")
                        //.setCallbackProgressTimes(urls.size()) // 由于是队列任务, 这里是我们假设了现在不需要每个任务都回调`FileDownloadListener#progress`, 我们只关系每个任务是否完成, 所以这里这样设置可以很有效的减少ipc.
                        .setListener(queueTarget)
                        .asInQueueTask()
                        .enqueue();
            }
        }
        // 串行执行该队列
        FileDownloader.getImpl().start(queueTarget, true);
    }



    final FileDownloadListener queueTarget = new FileDownloadListener() {
        @Override
        protected void pending(BaseDownloadTask task, int soFarBytes, int totalBytes) {
        }

        @Override
        protected void connected(BaseDownloadTask task, String etag, boolean isContinue, int soFarBytes, int totalBytes) {
        }

        @Override
        protected void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {

            mDownLoadDialog.setProgress(0, soFarBytes, totalBytes,task);
        }

        @Override
        protected void blockComplete(BaseDownloadTask task) {
        }

        @Override
        protected void retry(final BaseDownloadTask task, final Throwable ex, final int retryingTimes, final int soFarBytes) {
        }

        @Override
        protected void completed(BaseDownloadTask task) {
            mDownLoadDialog.setProgress(1, 0, 0, task);
            Log.i("--------------------", "--" + task.getPath());
            File file = new File(task.getPath());
            //通知相册
            mContext.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file)));
        }

        @Override
        protected void paused(BaseDownloadTask task, int soFarBytes, int totalBytes) {
        }

        @Override
        protected void error(BaseDownloadTask task, Throwable e) {
            mDownLoadDialog.setFail();
        }

        @Override
        protected void warn(BaseDownloadTask task) {
        }
    };
}
