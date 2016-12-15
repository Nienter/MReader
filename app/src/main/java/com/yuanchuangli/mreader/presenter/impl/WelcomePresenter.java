package com.yuanchuangli.mreader.presenter.impl;

import android.os.Handler;

import com.yuanchuangli.mreader.model.biz.Welcome.IWelcomeBiz;
import com.yuanchuangli.mreader.model.biz.Welcome.IWelcomeListener;
import com.yuanchuangli.mreader.model.biz.Welcome.WelcomeBiz;
import com.yuanchuangli.mreader.ui.view.IWelcomeView;

/**
 * Created by Blank on 2016/12/9 10:25
 */

public class WelcomePresenter {
    private IWelcomeBiz WelcomeBiz;
    private IWelcomeView iWelcomeView;
    private Handler mHandler = new Handler();

    public WelcomePresenter(IWelcomeView iWelcomeView) {
        this.WelcomeBiz = new WelcomeBiz();
        this.iWelcomeView = iWelcomeView;
    }

    /**
     * 初始化欢迎界面
     * 延时3秒，后面可以更改为初始化整个app时的耗时
     */
    public void init() {
        WelcomeBiz.init(new IWelcomeListener() {
            @Override
            public void initToHome() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        iWelcomeView.toHomeActivity();
                    }
                }, 3000);
            }

            @Override
            public void initToLogin() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        iWelcomeView.toLoginActivity();
                    }
                }, 3000);

            }
        });


    }

}
