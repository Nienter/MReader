package com.yuanchuangli.mreader.model.biz;

import com.yuanchuangli.mreader.model.bean.user.User;

/**
 * Created by Blank on 2016/11/21 16:26
 */

public interface OnloginListener {
    void loginSuccess(User user);

    void loginFailed();
}
