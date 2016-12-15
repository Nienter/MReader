package com.yuanchuangli.mreader.utils.init;

import android.app.Application;
import android.content.Context;

import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.tauth.Tencent;
import com.yuanchuangli.mreader.utils.Constants;
import com.yuanchuangli.mreader.utils.LogUtils;
import com.yuanchuangli.mreader.utils.SharedPreferenceUtil;

/**
 * 捕获应用异常Application 在这里完成整个应用退出；在这里进行全局变量的传递；在这里完成低内存的释放；在这里捕获未抓住的异常；用于应用配置,
 * 预加载处理
 *
 * @author Blank
 */

public class BaseApplication extends Application {
    private static Context mContext;
    private static Tencent mTecent;
    private static IWXAPI mWeiXinApi;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        SharedPreferenceUtil.SharedPreferencesInit(mContext);
        LogUtils.i("SHARE", "到了Appcalition");
        // 异常处理
        //BaseCrashHandler handler = BaseCrashHandler.getInstance();
        // handler.init(this);

        // 程序异常关闭1s之后重新启动
        // new RebootThreadExceptionHandler(getBaseContext());

        init();
    }

    /**
     * 初始化
     */
    private void init() {
        //QQ
        mTecent = Tencent.createInstance(Constants.QQ_API_ID, mContext);
        //通过WXAPIWXAPIFactory 获取IWAPI的实例
        mWeiXinApi = WXAPIFactory.createWXAPI(mContext, Constants.WEIXIN_API_ID, true);
        //将app注册到微信
        mWeiXinApi.registerApp(Constants.WEIXIN_API_ID);
    }

    @Override
    public void onTerminate() {

        super.onTerminate();

    }

    // 在内存低时,发送广播可以释放一些内存
    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    public static Context getContext() {
        return mContext;
    }

    public static Tencent getTecent() {
        return mTecent;
    }

    public static IWXAPI getIWXAPI() {
        return mWeiXinApi;
    }
}
