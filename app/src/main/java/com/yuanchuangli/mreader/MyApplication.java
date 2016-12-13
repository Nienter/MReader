package com.yuanchuangli.mreader;

import android.app.Application;
import android.content.Context;

import com.yuanchuangli.mreader.utils.SharedPreferenceUtil;

/**
 * @author Blank
 * @description MyApplication 用于初始化整个应用，暂时没有用，由BaseAPplication代替
 * @time 2016/11/24 18:32
 */

public class MyApplication extends Application {
    //获得主线程的上下文
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        SharedPreferenceUtil.SharedPreferencesInit(this);
    }

    public static Context getContext() {
        return mContext;
    }
}
