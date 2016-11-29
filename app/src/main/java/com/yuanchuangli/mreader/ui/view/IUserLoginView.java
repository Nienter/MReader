package com.yuanchuangli.mreader.ui.view;

import com.yuanchuangli.mreader.model.bean.user.User;

/**
 * @author Balnk
 * @description 用户登录的接口
 * @time 2016/11/24 18:29
 */

public interface IUserLoginView {
    String getUsername();//获得用户名

    String getPassword();//获得密码

    void showLoading();//显示进度

    User getUser();//获取用户对象

    void hideLoading();//隐藏进度框

    boolean isCancleLodading();//取消对话框

    void toHomeActivity();//跳转到主界面

    void showFaildError(int code);//显示错误信息
}
