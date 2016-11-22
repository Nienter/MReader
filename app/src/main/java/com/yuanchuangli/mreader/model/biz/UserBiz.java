package com.yuanchuangli.mreader.model.biz;

import com.yuanchuangli.mreader.model.bean.user.User;

/**
 * Created by Blank on 2016/11/21 16:33
 */

public class UserBiz implements IUserBiz {
    @Override
    public void login(final String username, final String password, final OnloginListener loginListener) {
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //登录成功
                if ("npk".equals(username) && "123".equals(password)) {
                    User user = new User(username, password);
                    loginListener.loginSuccess(user);
                } else {
                    loginListener.loginFailed();
                }
            }
        }.start();

    }
}
