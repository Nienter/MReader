package com.yuanchuangli.mreader.model.biz;

import com.yuanchuangli.mreader.model.bean.user.BaseUser;

/**
 * @author Blank
 * @description OnloginListener 登录结果的回调接口
 * @time 2016/11/24 18:25
 */

public interface OnloginListener {
    void loginSuccess(BaseUser user);

    void loginFailed();
}
