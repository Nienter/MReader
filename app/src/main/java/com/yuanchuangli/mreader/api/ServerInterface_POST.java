package com.yuanchuangli.mreader.api;

import com.yuanchuangli.mreader.model.bean.user.BaseUser;
import com.yuanchuangli.mreader.utils.LogUtils;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * POST 请求
 * Created by Blank on 2016/11/21 12:10
 */


public class ServerInterface_POST {
    /**
     * 请求登录的接口
     */
    public final static String REQUEST_PATH_lOGIN = ServerInterface.LOGIN_BASE_ADDRESS + "checkLogin";
    //请求接口附带的参数
    private static String params;

    /**
     * 检查用户
     *
     * @param user 用户对象
     * @return json信息
     */
    public static String checkPass(BaseUser user) {
        params = "";
        String username = user.getUsername();
        params += "username=" + username;
        String password = user.getPassword();
        params += "&password=" + password;
        LogUtils.i("PARAMS", params);
        try {
            URL url = new URL(REQUEST_PATH_lOGIN);

            return HttpUtil.sendPost(url, params);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return "";
        }
    }
}
