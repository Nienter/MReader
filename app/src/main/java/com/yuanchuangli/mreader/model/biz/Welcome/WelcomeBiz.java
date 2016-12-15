package com.yuanchuangli.mreader.model.biz.Welcome;

import com.yuanchuangli.mreader.utils.SharedPreferenceUtil;
import com.yuanchuangli.mreader.utils.init.BaseApplication;

/**
 * Created by Blank on 2016/12/9 10:35
 */

public class WelcomeBiz implements IWelcomeBiz {
    @Override
    public void init(final IWelcomeListener iWelcomeListener) {
        new Thread() {
            @Override
            public void run() {
                if (SharedPreferenceUtil.getUser(BaseApplication.getContext()).contains(SharedPreferenceUtil.SHAR_PRE_TOKEN)) {
                    iWelcomeListener.initToHome();
                } else {
                    iWelcomeListener.initToLogin();
                }
            }
        }.start();

    }
}
