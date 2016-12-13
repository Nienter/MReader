package com.yuanchuangli.mreader.utils;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.provider.Settings;

/**
 * Created by Blank on 2016/12/12 10:34
 */

public class NetUtils {
    //判断网络状况
    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }

        }
        return false;
    }

    /**
     * 判断WiFi状态
     *
     * @param context
     * @return
     */
    public static boolean isWifiConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mWifiNetWOrkInfo = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (mWifiNetWOrkInfo != null) {
                return mWifiNetWOrkInfo.isAvailable();
            }
        }
        return false;
    }

    //判断移动网络状态
    public static boolean isMobileConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mMobileNetWorkInfo = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (mMobileNetWorkInfo != null) {
                return mMobileNetWorkInfo.isAvailable();
            }
        }
        return false;
    }

    //获取连接类型
    public static int getConnectedType(Context context) {
        {
            if (context != null) {
                ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
                if (mNetworkInfo != null) {
                    return mNetworkInfo.getType();
                }
            }
            return -1;
        }

    }

    /**
     * 设置网络
     *
     * @param paramContext 传回的Context
     */
    public static void startToSetting(Context paramContext) {
        if (paramContext == null) {
            return;
        }
        if (Build.VERSION.SDK_INT > 10) {
            paramContext.startActivity(new Intent(Settings.ACTION_SETTINGS));
            return;
        }
        paramContext.startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
    }
}
