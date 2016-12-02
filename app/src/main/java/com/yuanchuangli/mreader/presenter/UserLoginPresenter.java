package com.yuanchuangli.mreader.presenter;

import android.app.Activity;
import android.os.Handler;

import com.tencent.connect.UserInfo;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import com.yuanchuangli.mreader.model.bean.user.BaseUser;
import com.yuanchuangli.mreader.model.biz.IQQBiz;
import com.yuanchuangli.mreader.model.biz.IUserBiz;
import com.yuanchuangli.mreader.model.biz.OnloginListener;
import com.yuanchuangli.mreader.model.biz.QQBiz;
import com.yuanchuangli.mreader.model.biz.UserBiz;
import com.yuanchuangli.mreader.parse.JSONParse_QQ;
import com.yuanchuangli.mreader.ui.view.IUserLoginView;
import com.yuanchuangli.mreader.utils.LogUtils;

/**
 * @author Blank
 * @description 登录，用于model和view的交互
 * @time 2016/11/24 18:17
 */

public class UserLoginPresenter {
    private IUserBiz userBiz;
    private IUserLoginView userLoginView;
    private Handler mHandler = new Handler();
    private IQQBiz QQBiz;
    public static IUiListener listener;
    private UserInfo userInfo;

    public UserLoginPresenter(IUserLoginView userLoginView) {
        this.userLoginView = userLoginView;
        this.userBiz = new UserBiz();
        this.QQBiz = new QQBiz();
    }

    /**
     * 用于使用账户密码的用户的登录
     */
    public void login() {
        userLoginView.showLoading();
        userBiz.login(userLoginView.getUser(), new OnloginListener() {
            @Override
            public void loginSuccess(BaseUser user) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        userLoginView.toHomeActivity();
                        userLoginView.hideLoading();
                    }
                });
            }

            @Override
            public boolean isCancleLogin() {
                return userLoginView.isCancleLodading();
            }

            @Override
            public void loginFailed(final int code) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        userLoginView.hideLoading();
                        userLoginView.showFaildError(code);
                    }
                });
            }
        });
    }

    /**
     * 用于qq的第三方登录
     *
     * @param activity 发起登录的activity
     */
    public void login_qq(Activity activity) {
        QQBiz.login(activity, new IUiListener() {
                    @Override
                    public void onComplete(Object value) {
                        System.out.println("有数据返回..");
                        if (value == null) {
                            return;
                        }
                        try {
                            //暂时不做封装
//                    JSONObject jo = (JSONObject) value;
//                    int ret = jo.getInt("ret");
//                    LogUtils.i("JSON", value.toString());
//                    if (ret == 0) {
//                        String openID = jo.getString("openid");
//                        String accessToken = jo.getString("access_token");
//                        String expires = jo.getString("expires_in");
//                        LogUtils.i("登录成功", "登录成功" + accessToken);
//                        userLoginView.toHomeActivity();}
                            UserInfo userInfo = JSONParse_QQ.getQQLoginInfo(value);
                            userLoginView.toHomeActivity();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(UiError uiError) {
                        LogUtils.e("QQERROR", uiError.errorMessage);
                    }

                    @Override
                    public void onCancel() {
                    }
                }

        );
    }
}
