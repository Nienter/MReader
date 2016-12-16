package com.yuanchuangli.mreader.model.biz.User;

import com.yuanchuangli.mreader.model.bean.user.User;

/**
 * @author Blank
 * @description OnloginListener 登录结果的回调接口
 * @time 2016/11/24 18:25
 */

public interface IOnloginListener {
    void loginSuccess(User user);

    boolean isCancleLogin();

    void loginFailed(int code);
}
