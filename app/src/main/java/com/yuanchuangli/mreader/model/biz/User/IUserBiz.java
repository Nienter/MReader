package com.yuanchuangli.mreader.model.biz.User;

import com.yuanchuangli.mreader.model.bean.user.User;

/**
 * @author Blank
 * @description IUserBiz 登录操作的接口
 * @time 2016/11/24 18:24
 */

public interface IUserBiz {
    void login(User user, OnloginListener onloginListener);

    //void getBaseInfo();
}
