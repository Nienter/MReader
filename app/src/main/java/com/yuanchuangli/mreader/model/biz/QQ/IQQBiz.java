package com.yuanchuangli.mreader.model.biz.QQ;

import android.app.Activity;

import com.tencent.tauth.IUiListener;

/**
 * @author qq业务的接口
 * @description IQQBiz
 * @time 2016/12/12 18:39
 */

public interface IQQBiz {
    void login(Activity activity, IUiListener iUiListener);
}
