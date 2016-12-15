package com.yuanchuangli.mreader.model.biz.User;

import com.yuanchuangli.mreader.api.ServerInterface_POST;
import com.yuanchuangli.mreader.model.bean.user.User;
import com.yuanchuangli.mreader.parse.JSONParse_PHP;
import com.yuanchuangli.mreader.utils.SharedPreferenceUtil;
import com.yuanchuangli.mreader.utils.init.BaseApplication;

/**
 * @author Blank
 * @description 处理具体的逻辑
 * @time 2016/11/23 11:26
 */

public class UserBiz implements IUserBiz {
    @Override
    public void login(final User user, final OnloginListener loginListener) {
        new Thread() {
            @Override
            public void run() {
                String json = ServerInterface_POST.checkPass(user);
                int code = JSONParse_PHP.getStatus(json);
                String token = JSONParse_PHP.getToken(json);
                User user = JSONParse_PHP.getUserBaseInfo(json);

                if (loginListener.isCancleLogin()) return;
                switch (code) {
                    case JSONParse_PHP.STATUS_SUCCESS:
                        SharedPreferenceUtil.saveUser(BaseApplication.getContext(), null, null, token);
                        loginListener.loginSuccess(user);
                        break;
                    default:
                        loginListener.loginFailed(code);
                        break;
                }
            }
        }.start();

    }


}
