package com.yuanchuangli.mreader.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.yuanchuangli.mreader.R;
import com.yuanchuangli.mreader.api.ServerInterface_GET;
import com.yuanchuangli.mreader.model.bean.Recharge;
import com.yuanchuangli.mreader.parse.JSONParse_PHP;
import com.yuanchuangli.mreader.ui.adapter.RechargeRecordsAdapter;
import com.yuanchuangli.mreader.utils.LogUtils;

import java.util.ArrayList;

/**
 * @author Blank
 * @description RechargeRecordsActivity 充值的activity
 * @time 2017/1/4 16:19
 */
public class RechargeRecordsActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private RechargeRecordsAdapter rechargeRecordsAdapter;
    private ArrayList<Recharge> mRechargeList = new ArrayList<>();
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge_records);
        mToolbar = (Toolbar) findViewById(R.id.id_rechargerecords_toolbar);
        mToolbar.setTitle("充值记录");
        setSupportActionBar(mToolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayShowHomeEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RechargeRecordsActivity.this.onBackPressed();
            }
        });
        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(true);
        }
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_recharge);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        rechargeRecordsAdapter = new RechargeRecordsAdapter(mRechargeList);
        mRecyclerView.setAdapter(rechargeRecordsAdapter);
        intData();
    }

    /**
     * 不符合mvp的规范
     */
    private void intData() {
        new Thread() {
            @Override
            public void run() {
                String json = ServerInterface_GET.getRechargeRecordsFromServer();
                int code = JSONParse_PHP.getStatus(json);
                ArrayList<Recharge> rechargeList = JSONParse_PHP.getRechargeRecords(json);
                switch (code) {
                    case JSONParse_PHP.STATUS_SUCCESS:
                        initView(rechargeList);
                        LogUtils.i("走到了");
                        break;
                }
            }
        }.start();
    }

    /**
     * 不符合mvp的规范
     */
    private void initView(final ArrayList<Recharge> rechargeList) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mRechargeList.addAll(rechargeList);
                rechargeRecordsAdapter.notifyDataSetChanged();
            }
        });
    }
}
