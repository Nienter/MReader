package com.yuanchuangli.mreader.model.BroadcastReceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Blank on 2016/11/25 16:24
 * 暂时不用
 */

public class NetworkReceiver extends BroadcastReceiver {
    private List<INetworkChange> lists = new ArrayList<>();

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
     * 通知这个app的网络环境，战士说不做
     */
    private void updateAppEnvironment_noNetWork() {
    }

    private void updateAppEnvironment_hasNetWork(Context context) {
    }
}
