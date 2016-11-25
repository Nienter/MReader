package com.yuanchuangli.mreader.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.yuanchuangli.mreader.R;
import com.yuanchuangli.mreader.model.bean.user.User;
import com.yuanchuangli.mreader.presenter.UserLoginPresenter;
import com.yuanchuangli.mreader.ui.view.IUserLoginView;
import com.yuanchuangli.mreader.utils.ActivityCollector;
import com.yuanchuangli.mreader.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Blank
 * @description LoginActivity 登录的activity ，作为一个view层
 * @time 2016/11/24 18:28
 */
public class LoginActivity extends BaseActivity implements IUserLoginView, View.OnClickListener {
    private EditText mEtUsername, mEtPassword;
    private Button mBtnLogin;
    private ImageView img_logo;
    private ProgressBar mPbLoading;
    private String str_name, str_psw;

    private UserLoginPresenter mUserLoginPresenter = new UserLoginPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        findView();
        ActivityCollector.addActivity(this);//加入管理
        initLogoAnim();
    }


    /**
     * 初始化组件
     */
    public void findView() {
        mEtUsername = (EditText) findViewById(R.id.id_et_username);
        mEtPassword = (EditText) findViewById(R.id.id_et_psw);
        //mBtnLogin = (Button) findViewById(R.id.id_btn_login);
        img_logo = (ImageView) findViewById(R.id.id_img_logo);
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
        Toast.makeText(this, "登录失败,请检查账户或密码", Toast.LENGTH_LONG).show();
    }

    @Override
    public User getUser() {
        User user = new User(str_name, str_psw);
        return user;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_btn_login:
                getAllText();
                if (checkInput() && isOpenNetwork())
                    mUserLoginPresenter.login();
                break;
        }
    }

    /**
     * 判断输入的合法性
     *
     * @return
     */
    private boolean checkInput() {
        if (TextUtils.isEmpty(str_name) | TextUtils.isEmpty(str_psw)) {
            Toast.makeText(LoginActivity.this, "账户或密码为空", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    /**
     * 检测网络是否可用，暂时先用这个，以后根据需求需要使用广播
     *
     * @return 网络状态
     */
    public boolean isOpenNetwork() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (connectivityManager.getActiveNetworkInfo() != null && networkInfo.isAvailable()) {
            return true;
        }
        Toast.makeText(LoginActivity.this, "网络不可用", Toast.LENGTH_LONG).show();
        return false;
    }

    public void getAllText() {
        str_name = mEtUsername.getText().toString();
        str_psw = mEtPassword.getText().toString();
    }

    /**
     * 初始化logo动画
     */
    private void initLogoAnim() {
        AnimatorSet Animset = new AnimatorSet();
        List<Animator> anis = new ArrayList<>();
        ObjectAnimator AnimObj1 = ObjectAnimator.ofFloat(img_logo,
                View.SCALE_X, 0, 1);
        ObjectAnimator AnimObj2 = ObjectAnimator.ofFloat(img_logo,
                View.SCALE_Y, 0, 1);
        anis.add(AnimObj1);
        anis.add(AnimObj2);
        Animset.playTogether(anis);
        Animset.setDuration(1000);
        Animset.start();
    }
}
