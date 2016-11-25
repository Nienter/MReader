package com.yuanchuangli.mreader.utils.init;

import android.app.Application;
import android.widget.Toast;

import com.yuanchuangli.mreader.utils.LogUtils;

/**
 * 捕获应用异常Application 在这里完成整个应用退出；在这里进行全局变量的传递；在这里完成低内存的释放；在这里捕获未抓住的异常；用于应用配置,
 * 预加载处理
 *
 * @author Blank
 */

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // 异常处理
        LogUtils.i("Application", "OK");
        Toast.makeText(this, "application", Toast.LENGTH_SHORT).show();
        BaseCrashHandler handler = BaseCrashHandler.getInstance();
        handler.init(getApplicationContext());

        // 程序异常关闭1s之后重新启动
        new RebootThreadExceptionHandler(getBaseContext());
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

}
