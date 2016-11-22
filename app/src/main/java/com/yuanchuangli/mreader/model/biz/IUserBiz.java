package com.yuanchuangli.mreader.model.biz;

/**
 * Created by Blank on 2016/11/21 16:25
 */

public interface IUserBiz {
    void login(String username, String password, OnloginListener onloginListener);
}
