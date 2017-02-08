package com.yuanchuangli.mreader.model.biz.User;

/**
 * @author Blank
 * @description DownloadListener 监听下载过程中的各种状态
 * @time 2017/2/6 15:03
 */

public interface DownloadListener {
    void onProgress(int progress);//下载进度

    void onSuccess();//通知下载成功

    void onFailed();//通知下载失败

    void onPause();//下载暂停

    void OnCancled();//下载取消
}
