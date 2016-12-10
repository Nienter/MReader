package com.yuanchuangli.mreader.model.biz.QQ;

import android.app.Activity;

import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.yuanchuangli.mreader.utils.init.BaseApplication;

/**
 * @author Blank
 * @description QQBiz qq登录的具体逻辑业务
 * @time 2016/12/9 10:38
 */

public class QQBiz implements IQQBiz {
    @Override
    public void login(final Activity activity, final IUiListener iUiListener) {
        Tencent mTencent = BaseApplication.getTecent();
        mTencent.login(activity, "all", iUiListener);
        if (mTencent != null) {
            mTencent = null;
        }
    }
}
