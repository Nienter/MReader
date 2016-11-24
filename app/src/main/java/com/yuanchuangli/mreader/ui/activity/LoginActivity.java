package com.yuanchuangli.mreader.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.yuanchuangli.mreader.R;
import com.yuanchuangli.mreader.model.bean.user.User;
import com.yuanchuangli.mreader.presenter.UserLoginPresenter;
import com.yuanchuangli.mreader.ui.view.IUserLoginView;
import com.yuanchuangli.mreader.utils.LogUtils;

/**
 * @author Blank
 * @description LoginActivity 登录的activity ，作为一个view层
 * @time 2016/11/24 18:28
 */
public class LoginActivity extends BaseActivity implements IUserLoginView, View.OnClickListener {
    private EditText mEtUsername, mEtPassword;
    private Button mBtnLogin;
    private ProgressBar mPbLoading;

    private UserLoginPresenter mUserLoginPresenter = new UserLoginPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        initView();
        float xdpi = getResources().getDisplayMetrics().xdpi;
        float ydpi = getResources().getDisplayMetrics().ydpi;
        LogUtils.i("XDPI", "xdpi is" + xdpi);
        LogUtils.i("YDPI", "ydpi is" + ydpi);

    }

    /**
     * 初始化
     */
    public void initView() {
        mEtUsername = (EditText) findViewById(R.id.id_et_username);
        mEtPassword = (EditText) findViewById(R.id.id_et_psw);
        mBtnLogin = (Button) findViewById(R.id.id_btn_login);
        mBtnLogin.setOnClickListener(this);
        LogUtils.i("LOGIN", "666");
        mPbLoading = (ProgressBar) findViewById(R.id.id_pb_loading);
    }

    @Override
    public String getUsername() {
        return mEtUsername.getText().toString();
    }

    @Override
    public String getPassword() {
        return mEtPassword.getText().toString();
    }

    @Override
    public void showLoading() {
        mPbLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mPbLoading.setVisibility(View.GONE);
    }

    @Override
    public void toHomeActivity() {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }

    @Override
    public void showFaildError() {
        Toast.makeText(this, "登录失败", Toast.LENGTH_LONG).show();
    }

    @Override
    public User getUser() {
        User user = new User(getUsername(), getPassword());
        return user;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_btn_login:
                mUserLoginPresenter.login();
                break;
        }
    }
}
