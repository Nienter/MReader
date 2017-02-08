package com.yuanchuangli.mreader.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;

import com.yuanchuangli.mreader.R;
import com.yuanchuangli.mreader.ui.fragment.SettingFragment;

import static com.yuanchuangli.mreader.R.id.toolbar;

/**
 * @author Blank
 * @description SettingActivity 设置的activity
 * @time 2017/1/4 16:21
 */
public class SettingActivity extends BaseActivity {
    private Toolbar mToolbar;
    private FrameLayout fl_Preference;
    private SettingFragment mSettingFragment = new SettingFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        findView();
        mToolbar.setTitle("设置");
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        getFragmentManager().beginTransaction().replace(R.id.fl_preference, mSettingFragment).commit();
    }


    private void findView() {
        mToolbar = (Toolbar) findViewById(toolbar);
        fl_Preference = (FrameLayout) findViewById(R.id.fl_preference);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
