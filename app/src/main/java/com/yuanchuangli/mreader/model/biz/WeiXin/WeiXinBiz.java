package com.yuanchuangli.mreader.model.biz.WeiXin;

import android.app.Activity;

import com.tencent.mm.sdk.openapi.SendAuth;
import com.yuanchuangli.mreader.utils.init.BaseApplication;

/**
 * Created by Blank on 2016/12/14 13:43
 */

public class WeiXinBiz implements IWeiXinBiz {
    @Override
    public void login(Activity activity) {
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "wechat_sdk_demo_tes";
        BaseApplication.getIWXAPI().sendReq(req);
    }
}
