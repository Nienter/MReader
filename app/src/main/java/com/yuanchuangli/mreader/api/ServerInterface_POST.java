package com.yuanchuangli.mreader.api;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * POST 请求
 * Created by Blank on 2016/11/21 12:10
 */

public class ServerInterface_POST {
    public final static String REQUEST_PATH_lOGIN = ServerInterface.LOGIN_BASE_ADDRESS + "checkLogin";

    public static String getTokenFromServer() {
        try {
            URL url = new URL(REQUEST_PATH_lOGIN);
            return HttpUtil.sendPost(url, "username=pkdoc&password=wxj123");
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return "";
        }

    }

}
