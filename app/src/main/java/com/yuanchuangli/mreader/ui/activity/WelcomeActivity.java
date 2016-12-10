package com.yuanchuangli.mreader.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import com.yuanchuangli.mreader.R;
import com.yuanchuangli.mreader.presenter.WelcomePresenter;
import com.yuanchuangli.mreader.ui.view.IWelcomeView;

public class WelcomeActivity extends BaseActivity implements IWelcomeView {
    private static final int GOTO_MAIN_ACTIVITY = 0;
    private WelcomePresenter welcomePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        welcomePresenter = new WelcomePresenter(this);
        welcomePresenter.init();
        //mHandler.sendEmptyMessageDelayed(GOTO_MAIN_ACTIVITY, 3000);//3秒跳转


        //hasBackground();
    }


//    private Handler mHandler = new Handler() {
//        public void handleMessage(android.os.Message msg) {
//
//            switch (msg.what) {
//                case GOTO_MAIN_ACTIVITY:
//                    Intent intent = new Intent();
//                    intent.setClass(WelcomeActivity.this, MainActivity.class);
//                    startActivity(intent);
//                    finish();
//                    break;
//
//                default:
//                    break;
//            }
//        }
//
//        ;
//    };

    @Override
    public void toHomeACtivity() {
        Intent intent = new Intent();
        intent.setClass(WelcomeActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void toLoginActivity() {
        Intent intent = new Intent();
        intent.setClass(WelcomeActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
