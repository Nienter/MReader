package com.yuanchuangli.mreader.model.biz;

import android.app.Activity;

import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.yuanchuangli.mreader.utils.init.BaseApplication;

/**
 * Created by Blank on 2016/12/1 14:15
 */

public class QQBiz implements IQQBiz {
    @Override
    public void login(final Activity activity, final IUiListener iUiListener) {
        Tencent mTencent = BaseApplication.getTecent();
        mTencent.login(activity, "all", iUiListener);
    }
}
