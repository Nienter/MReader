package com.yuanchuangli.mreader.presenter;

import android.os.Handler;

import com.yuanchuangli.mreader.model.bean.user.BaseUser;
import com.yuanchuangli.mreader.model.biz.IUserBiz;
import com.yuanchuangli.mreader.model.biz.OnloginListener;
import com.yuanchuangli.mreader.model.biz.UserBiz;
import com.yuanchuangli.mreader.ui.view.IUserLoginView;

/**
 * @author Blank
 * @description 登录，用于model和view的交互
 * @time 2016/11/24 18:17
 */

public class UserLoginPresenter {
    private IUserBiz userBiz;
    private IUserLoginView userLoginView;
    private Handler mHandler = new Handler();

    public UserLoginPresenter(IUserLoginView userLoginView) {
        this.userLoginView = userLoginView;
        this.userBiz = new UserBiz();
    }

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
            public void loginFailed() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        userLoginView.showFaildError();
                        userLoginView.hideLoading();
                    }
                });
            }
        });
    }
}
