package com.yuanchuangli.mreader.model.biz.Welcome;

import com.yuanchuangli.mreader.utils.LogUtils;
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
                LogUtils.i("SHARE", "到了WELCOMEBIZ");
                LogUtils.i("判断？", "6666" + SharedPreferenceUtil.getUser(BaseApplication.getContext()).contains(SharedPreferenceUtil.SHAR_PRE_TOKEN));
                if (SharedPreferenceUtil.getUser(BaseApplication.getContext()).contains(SharedPreferenceUtil.SHAR_PRE_TOKEN)) {
                    iWelcomeListener.initSuccess();
                } else {
                    iWelcomeListener.initFail();
                }
            }
        }.start();

    }
}
