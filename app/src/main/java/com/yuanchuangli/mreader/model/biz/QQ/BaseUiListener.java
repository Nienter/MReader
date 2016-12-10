package com.yuanchuangli.mreader.model.biz.QQ;

import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import com.yuanchuangli.mreader.utils.LogUtils;
import com.yuanchuangli.mreader.utils.ToastUtils;
import com.yuanchuangli.mreader.utils.init.BaseApplication;

import org.json.JSONObject;

/**
 * @author qq的监听器 暂时未用
 * @description BaseUiListener
 * @time 2016/12/9 10:31
 */

public class BaseUiListener implements IUiListener {
    private final static String TAG = "LoginActivity";

    @Override
    public void onComplete(Object response) {
        if (response == null) {
            ToastUtils.showToast(BaseApplication.getContext(), "返回为空登录失败1");
            return;
        }
        JSONObject jsonResponse = (JSONObject) response;
        if (jsonResponse != null && jsonResponse.length() == 0) {
            LogUtils.i(TAG, "登录成功" + response.toString());
            return;
        }

        LogUtils.i(TAG, "登录成功" + response.toString());
        doComplete((JSONObject) response);
    }

    protected void doComplete(JSONObject values) {

    }

    @Override
    public void onError(UiError e) {
        LogUtils.e(TAG, "onError: " + e.errorDetail);
    }

    @Override
    public void onCancel() {

    }
}
