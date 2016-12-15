package com.yuanchuangli.mreader.wxapi;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.tencent.mm.sdk.openapi.BaseReq;
import com.tencent.mm.sdk.openapi.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.yuanchuangli.mreader.R;
import com.yuanchuangli.mreader.utils.init.BaseApplication;

/**
 * @author 与微信通信的activity
 * @description WXEntryActivity
 * @time 2016/12/14 13:29
 */

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BaseApplication.getIWXAPI().handleIntent(getIntent(), this);
    }


    @Override
    public void onReq(BaseReq baseReq) {

    }


    @Override
    public void onResp(BaseResp resp) {
        int result = 0;
        switch (resp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                //String code = ((SendAuth.Resp) resp).token;
                result = R.string.errcode_success;
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                result = R.string.errcode_cancel;
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                result = R.string.errcode_deny;
                break;
            default:
                result = R.string.errcode_unknown;
                break;
        }
        finish();
        Toast.makeText(this, result, Toast.LENGTH_LONG).show();
    }

}
