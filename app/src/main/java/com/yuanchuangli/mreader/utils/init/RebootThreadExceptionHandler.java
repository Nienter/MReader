/**
 * Copyright 2014 Zhenguo Jin (jinzhenguo1990@gmail.com)
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.yuanchuangli.mreader.utils.init;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.text.TextUtils;

import com.yuanchuangli.mreader.ui.activity.LoginActivity;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * 展示未用，发现在BaseApplication会无法生成崩溃日志
 * 重启线程异常处理器，当发生未知异常时会提示异常信息并在一秒钟后重新启动应用
 * <br>使用此功能的第一步需要你在AndroidMainfest.xml中注册me.xiaopan.android.content.StartApplicationBrocastReceiver广播（注意不要任何的filter）
 * <br>第二步就是在你的Application的onCreate()方法中加上new RebootThreadExceptionHandler(getBaseContext());即可
 *
 * @author zhenguo
 */
public class RebootThreadExceptionHandler implements UncaughtExceptionHandler {

    private Context context;
    private String hintText;

    public RebootThreadExceptionHandler(Context context, String hintText) {
        this.context = context;
        this.hintText = hintText;
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    public RebootThreadExceptionHandler(Context context) {
        this(context, null);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        ex.printStackTrace();// 输出异常信息到控制台

        if (TextUtils.isEmpty(hintText)) {
            /* 启动新线程提示程序异常 */
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Looper.prepare();
//                    Toast.makeText(context, "程序出现异常,3秒后即将关闭该页面并将重启程序，请原谅", Toast.LENGTH_SHORT)
//                            .show();

                    Looper.loop();
                }
            }).start();

			/* 主线程等待1秒钟，让提示信息显示出来 */
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

		/* 设置定时器，在1秒钟后发出启动程序的广播 */
        AlarmManager mAlarmManager = (AlarmManager) context
                .getSystemService(Context.ALARM_SERVICE);
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.SECOND, 1);
        //注释的这段采用的是广播重新启动应用，未知原因无法启动，现直接采用下面一种方法
//        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
//                PendingIntent.getBroadcast(context, 0, new Intent(context,
//                        StartAppReceiver.class).addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES), 0));

        mAlarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                PendingIntent.getActivity(context, 0, new Intent(context, LoginActivity.class), 0));
        android.os.Process.killProcess(android.os.Process.myPid()); // 结束程序
    }

}
