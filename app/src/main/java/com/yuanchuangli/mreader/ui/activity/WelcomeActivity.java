package com.yuanchuangli.mreader.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.yuanchuangli.mreader.R;
import com.yuanchuangli.mreader.ui.view.IWelcome;

public class WelcomeActivity extends AppCompatActivity implements IWelcome {
    private static final int GOTO_MAIN_ACTIVITY = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        mHandler.sendEmptyMessageDelayed(GOTO_MAIN_ACTIVITY, 8000);//3秒跳转
//        if (SharedPreferenceUtil.getUser(this).getBoolean(SharedPreferenceUtil.SHAR_PRE_ISLOGIN, false)) {
//            startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
//
//        }
//
//        hasBackground();
    }


    @Override
    public void hasBackground() {
        startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
        finish();
    }

    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {

            switch (msg.what) {
                case GOTO_MAIN_ACTIVITY:
                    Intent intent = new Intent();
                    intent.setClass(WelcomeActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    break;

                default:
                    break;
            }
        }

        ;
    };
}
