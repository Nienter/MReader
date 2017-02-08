package com.yuanchuangli.mreader.model.biz.User;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import com.yuanchuangli.mreader.ui.activity.HomeActivity;
import com.yuanchuangli.mreader.utils.ToastUtils;

import java.io.File;

/**
 * @author Blank
 * @description DownloadService
 * @time 2017/2/6 15:43
 */
public class DownloadService extends Service {
    private DownLoadTask downLoadTask;
    private String downloadUrl;


    private DownloadListener listener = new DownloadListener() {
        @Override
        public void onProgress(int progress) {
            getNotificationManager().notify(1, getNotification("Downloading...", progress));
        }

        @Override
        public void onSuccess() {
            downLoadTask = null;
            //下载成功时将前台服务通知关闭，并创建一个下载成功的通知
            stopForeground(true);
            getNotificationManager().notify(1, getNotification("Downloading Success", -1));
            ToastUtils.showToast(DownloadService.this, "Download Success");
            String filenName = downloadUrl.substring(downloadUrl.lastIndexOf("/"));
            String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
            File file = new File(directory + filenName);
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.addCategory("android.intent.category.DEFAULT");
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Uri uri = Uri.fromFile(file);
            intent.setDataAndType(uri, "application/msword");
            startActivity(intent);


        }

        @Override
        public void onFailed() {
            downLoadTask = null;
            stopForeground(true);
            getNotificationManager().notify(1, getNotification("Downloading Failed", -1));
            ToastUtils.showToast(DownloadService.this, "Download Failed");

        }

        @Override
        public void onPause() {
            downLoadTask = null;
            ToastUtils.showToast(DownloadService.this, "Pause");

        }

        @Override
        public void OnCancled() {
            downLoadTask = null;
            stopForeground(true);
            ToastUtils.showToast(DownloadService.this, "Cancle");


        }
    };


    private DownloadBinder mBinder = new DownloadBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public class DownloadBinder extends Binder {
        public void startDownload(String url) {
            if (downLoadTask == null) {
                downloadUrl = url;
                downLoadTask = new DownLoadTask(listener);
                downLoadTask.execute(downloadUrl);
                startForeground(1, getNotification("Downloading...", 0));
                ToastUtils.showToast(DownloadService.this, "Downloading...");

            }
        }

        public void pauseDownload() {
            if (downLoadTask != null) {
                downLoadTask.pauseDown();
            }
        }

        public void cancleDownload() {
            if (downLoadTask != null) {
                downLoadTask.cancelDownload();
            } else {
                if (downloadUrl != null)
                //取消下载时需将文件删除
                {
                    String filenName = downloadUrl.substring(downloadUrl.lastIndexOf("/"));
                    String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
                    File file = new File(directory + filenName);
                    if (file.exists()) {
                        file.delete();
                    }
                    getNotificationManager().cancel(1);
                    stopForeground(true);
                    ToastUtils.showToast(DownloadService.this, "Canceled..");
                }
            }
        }
    }

    private NotificationManager getNotificationManager() {
        return (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

    }

    private Notification getNotification(String title, int progress)

    {
        Intent intent = new Intent(this, HomeActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(android.R.mipmap.sym_def_app_icon);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), android.R.mipmap.sym_def_app_icon));
        builder.setContentTitle(title);
        if (progress > 0) {
            //当progress大于0时才需要显示进度
            builder.setContentText(progress + "%");
            builder.setProgress(100, progress, false);
        }
        return builder.build();
    }
}
