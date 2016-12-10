package com.yuanchuangli.mreader.presenter;

import android.os.Handler;

import com.yuanchuangli.mreader.model.biz.Welcome.IWelcomeBiz;
import com.yuanchuangli.mreader.model.biz.Welcome.IWelcomeListener;
import com.yuanchuangli.mreader.model.biz.Welcome.WelcomeBiz;
import com.yuanchuangli.mreader.ui.view.IWelcomeView;
import com.yuanchuangli.mreader.utils.LogUtils;

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
        LogUtils.i("WelcomePresenter", "初始化完成");
    }

    public void init() {
        LogUtils.i("WelcomePresenter", "初始化完成2");
        WelcomeBiz.init(new IWelcomeListener() {
            @Override
            public void initSuccess() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        LogUtils.i("WelcomePresenter", "初始化完成3");
                        iWelcomeView.toHomeACtivity();
                    }
                }, 3000);
            }

            @Override
            public void initFail() {
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
