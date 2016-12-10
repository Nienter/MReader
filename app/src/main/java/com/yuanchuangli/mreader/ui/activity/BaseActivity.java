package com.yuanchuangli.mreader.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.yuanchuangli.mreader.utils.ActivityCollector;
import com.yuanchuangli.mreader.utils.LogUtils;

/**
 * @author Blank
 * @description BaseActivity 活动的基类
 * @time 2016/11/24 18:55
 */

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.d("BaseActivity", getClass().getSimpleName());
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
}
