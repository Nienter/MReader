package com.yuanchuangli.mreader.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import com.yuanchuangli.mreader.R;
import com.yuanchuangli.mreader.presenter.WelcomePresenter;
import com.yuanchuangli.mreader.ui.view.IWelcomeView;

/**
 * @author Blank
 * @description WelcomeActivity 欢迎界面
 * @time 2016/12/12 17:45
 */
public class WelcomeActivity extends BaseActivity implements IWelcomeView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        WelcomePresenter welcomePresenter = new WelcomePresenter(this);
        welcomePresenter.init();
    }

    /**
     * 跳转到主界面
     */
    @Override
    public void toHomeActivity() {
        Intent intent = new Intent();
        intent.setClass(WelcomeActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * 当发现没有用户资料的时候强制重新登录
     */
    @Override
    public void toLoginActivity() {
        Intent intent = new Intent();
        intent.setClass(WelcomeActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
