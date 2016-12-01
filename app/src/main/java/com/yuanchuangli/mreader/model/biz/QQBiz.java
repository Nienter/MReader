package com.yuanchuangli.mreader.model.biz;

import android.app.Activity;

import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.yuanchuangli.mreader.utils.init.BaseApplication;

/**
 * Created by Blank on 2016/12/1 14:15
 */

public class QQBiz implements IQQBiz {
    //public static IUiListener listener;
//
//    public static void login(Activity activity) {
//        listener = new BaseUiListener() {
//            @Override
//            protected void doComplete(JSONObject values) {
//            }
//        };
//
//        if (!BaseApplication.getTecent().isSessionValid()) {
//            BaseApplication.getTecent().login(activity, "all", listener);
//        }
//    }


    @Override
    public void login(final Activity activity, final IUiListener iUiListener) {
        Tencent mTencent = BaseApplication.getTecent();
        mTencent.login(activity, "all", iUiListener);

        //LogUtils.i("UUU", "数字是" + mTencent.login(activity, "all", iUiListener));
    }
}
