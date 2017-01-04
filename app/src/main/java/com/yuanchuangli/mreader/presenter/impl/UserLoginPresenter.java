package com.yuanchuangli.mreader.presenter.impl;

import android.app.Activity;
import android.os.Handler;

import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import com.yuanchuangli.mreader.model.bean.user.User;
import com.yuanchuangli.mreader.model.biz.QQ.IQQBiz;
import com.yuanchuangli.mreader.model.biz.QQ.QQBiz;
import com.yuanchuangli.mreader.model.biz.User.IUserBiz;
import com.yuanchuangli.mreader.model.biz.User.IOnloginListener;
import com.yuanchuangli.mreader.model.biz.User.UserBiz;
import com.yuanchuangli.mreader.model.biz.WeiXin.IWeiXinBiz;
import com.yuanchuangli.mreader.model.biz.WeiXin.WeiXinBiz;
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
    private IWeiXinBiz weiXinBiz;

    public UserLoginPresenter(IUserLoginView userLoginView) {
        this.userLoginView = userLoginView;
        this.userBiz = new UserBiz();
        this.QQBiz = new QQBiz();
        this.weiXinBiz = new WeiXinBiz();
    }

    /**
     * 用于使用账户密码的用户的登录
     */
    public void login() {
        userLoginView.showLoading();
        userBiz.login(userLoginView.getUser(), new IOnloginListener() {
            @Override
            public void loginSuccess(final User user) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        userLoginView.toHomeActivity(user);
                        userLoginView.hideLoading();
                        ViewDestory();
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
//                       UserInfo userInfo = JSONParse_QQ.getQQLoginInfo(value);
                            User user = new User();//为空
                            userLoginView.toHomeActivity(user);
                            ViewDestory();
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

    public void login_weixin(final Activity activity) {
        weiXinBiz.login(activity);
    }

    /**
     * 前期发生了内存泄漏，发现还有引用，简单处理下，后期名字功能都要优化
     *
     * @return 空的引用
     */
    private IUserLoginView ViewDestory() {
        if (userLoginView != null) {
            userLoginView = null;
        }
        return null;
    }


}
