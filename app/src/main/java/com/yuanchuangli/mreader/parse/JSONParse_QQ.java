package com.yuanchuangli.mreader.parse;

import android.content.Context;

import com.tencent.connect.UserInfo;
import com.tencent.tauth.Tencent;
import com.yuanchuangli.mreader.utils.LogUtils;
import com.yuanchuangli.mreader.utils.init.BaseApplication;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author 解析qq返回的信息
 * @description JSONParse_QQ
 * @time 2016/12/2 17:04
 */

public class JSONParse_QQ {
    private static Tencent mTencent = BaseApplication.getTecent();
    private static Context mContext = BaseApplication.getContext();

    public static UserInfo getQQLoginInfo(Object json) {
        try {
            JSONObject jo = (JSONObject) json;
            int ret = jo.getInt("ret");
            LogUtils.i("JSON", json.toString());
            if (ret == 0) {
                String openID = jo.getString("openid");
                String accessToken = jo.getString("access_token");
                String expires = jo.getString("expires_in");
                LogUtils.i("QQ登录成功", "QQ登录成功");
                mTencent.setOpenId(openID);
                mTencent.setAccessToken(accessToken, expires);

            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return new UserInfo(mContext, mTencent.getQQToken());
    }
}
