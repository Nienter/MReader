package com.yuanchuangli.mreader.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.yuanchuangli.mreader.R;
import com.yuanchuangli.mreader.api.ServerInterface_GET;
import com.yuanchuangli.mreader.parse.JSONParse_PHP;

/**
 *
 */
public class MainActivity extends AppCompatActivity {
    private static class myHandler extends Handler {
    }

    private TextView tv_time;
    private static myHandler handler;
    private final int TIME = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_time = (TextView) findViewById(R.id.id_tv_time);
        Thread TimeThread = new MyThread();
        TimeThread.start();
        tv_time.setText("jh");
        hanlerMSG();
    }

    public class MyThread extends Thread {
        @Override
        public void run() {
            Message msg = new Message();
            msg.what = TIME;
            //msg.arg1 = JSONParse_PHP.getServerTime(ServerInterface_GET.getTimeFromServer());
            //msg.obj = JSONParse_PHP.getToken(ServerInterface_POST.getTokenFromServer());
            msg.obj = JSONParse_PHP.getDocList(ServerInterface_GET.getDocListFromServer());
            handler.sendMessage(msg);
        }
    }

    private void hanlerMSG() {
        handler = new myHandler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case TIME:
                        tv_time.setText("token是" + msg.obj);
                        break;
                }
            }
        };
    }

}
