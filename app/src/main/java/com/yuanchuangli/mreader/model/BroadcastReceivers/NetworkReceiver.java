package com.yuanchuangli.mreader.model.BroadcastReceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.yuanchuangli.mreader.ui.activity.HomeActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Blank
 * @description NetworkReceiver 监听网络变化的广播，发现一个问题只有当网络拜变化时才会发出广播，而且是两次，并不能在一开始时判断网络状况，为此添加了NetUtils。
 * @time 2016/12/12 18:03
 */

public class NetworkReceiver extends BroadcastReceiver {
    private static List<INetworkChange> lists = new ArrayList<>();

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        //如果网络可用
        if (networkInfo != null && networkInfo.isAvailable()) {
            updateAppEnvironment_hasNetWork(context);
        } else {
            updateAppEnvironment_noNetWork();
        }
    }


    /**
     * 网络不通畅
     */
    private void updateAppEnvironment_noNetWork() {
        if (HomeActivity.homeActivity != null) {
            HomeActivity.netIsWork = false;
            HomeActivity.updateNetWorkText();
        }
        // 如果网络断开。从集合中迭代。一个个通知
        for (INetworkChange change : lists) {
            try {
                change.networkError();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 网络通畅
     *
     * @param context
     */
    private void updateAppEnvironment_hasNetWork(Context context) {
        if (HomeActivity.homeActivity != null) {
            HomeActivity.netIsWork = true;
            HomeActivity.updateNetWorkText();
        }
        // 如果网络断开。从集合中迭代。一个个通知
        for (INetworkChange change : lists) {
            try {
                change.networkConn();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 添加需要通知网络变化的Activity(需要继承了NetWorkChange接口)
     *
     * @param netWorkChange 集成了NetWorkChange接口的对象
     */

    public static void addNetWorkChange(INetworkChange netWorkChange) {
        try {
            lists.add(netWorkChange);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 移除需要通知网络变化的Activity
     *
     * @param netWorkChange 集成了NetWorkChange接口的对象
     */
    public static void removeNetWorkChange(INetworkChange netWorkChange) {
        try {
            lists.remove(netWorkChange);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
