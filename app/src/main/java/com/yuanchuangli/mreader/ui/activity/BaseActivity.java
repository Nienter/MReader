package com.yuanchuangli.mreader.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.yuanchuangli.mreader.model.BroadcastReceivers.INetworkChange;
import com.yuanchuangli.mreader.model.BroadcastReceivers.NetworkReceiver;
import com.yuanchuangli.mreader.utils.ActivityCollector;
import com.yuanchuangli.mreader.utils.LogUtils;

/**
 * @author Blank
 * @description BaseActivity 活动的基类
 * @time 2016/11/24 18:55
 */

public class BaseActivity extends AppCompatActivity implements INetworkChange {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.i("BaseActivity", getClass().getSimpleName());
        ActivityCollector.addActivity(this);
        NetworkReceiver.addNetWorkChange(this);
    }

    @Override
    protected void onDestroy() {
        ActivityCollector.removeActivity(this);
        NetworkReceiver.removeNetWorkChange(this);
        super.onDestroy();

    }

    /**
     * 这个是当网络顺畅时的操作暂时不写
     */
    @Override
    public void networkConn() {

    }

    /**
     * 网络故障时的操作暂时不写
     */
    @Override
    public void networkError() {

    }


}
