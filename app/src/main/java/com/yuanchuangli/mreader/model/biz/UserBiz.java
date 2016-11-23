package com.yuanchuangli.mreader.model.biz;

import com.yuanchuangli.mreader.model.bean.user.User;

/**
 * @author Blank
 * @description 处理具体的逻辑
 * @time 2016/11/23 11:26
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
