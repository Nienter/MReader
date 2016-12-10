package com.yuanchuangli.mreader.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.tencent.tauth.Tencent;
import com.yuanchuangli.mreader.R;
import com.yuanchuangli.mreader.model.bean.user.User;
import com.yuanchuangli.mreader.parse.JSONParse_PHP;
import com.yuanchuangli.mreader.presenter.UserLoginPresenter;
import com.yuanchuangli.mreader.ui.view.IUserLoginView;
import com.yuanchuangli.mreader.utils.ActivityCollector;
import com.yuanchuangli.mreader.utils.LogUtils;
import com.yuanchuangli.mreader.utils.SharedPreferenceUtil;
import com.yuanchuangli.mreader.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Blank
 * @description LoginActivity 登录的activity ，作为一个view层
 * @time 2016/11/24 18:28
 */
public class LoginActivity extends BaseActivity implements IUserLoginView, View.OnClickListener {
    private EditText mEtUsername, mEtPassword;
    private Resources res;
    private ImageView img_logo;
    private ProgressDialog mPdLoading;
    private String str_name, str_psw;
    private boolean isStop = false;
    private static final String TAG = "LoginActivity";
    private UserLoginPresenter mUserLoginPresenter = new UserLoginPresenter(this);
    /**
     * 保存用户最近登录的文件
     */
    private final static String SHAR_PRE_FILENAME_LOGINCONFIG = "user";
    /**
     * 保存用户的账户的key
     */
    private final static String SHAR_PRE_USERNAME = "username";
    /**
     * 保存用户密码的key
     */
    private final static String SHAR_PRE_PASSWORD = "password";
    /**
     * 保存用户的token
     */
    private final static String SHAR_PRE_TOKEN = "token";

    // private IUiListener iUiListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        res = getResources();
        findView();
        initLogoAnim();
    }


    /**
     * 初始化组件
     */
    public void findView() {
        mEtUsername = (EditText) findViewById(R.id.id_et_username);
        mEtPassword = (EditText) findViewById(R.id.id_et_psw);
        Button mBtnLogin = (Button) findViewById(R.id.id_btn_login);
        img_logo = (ImageView) findViewById(R.id.id_img_logo);
        ImageView ic_login_qq = (ImageView) findViewById(R.id.id_ic_qq);
        assert ic_login_qq != null;
        ic_login_qq.setOnClickListener(this);
        assert mBtnLogin != null;
        mBtnLogin.setOnClickListener(this);

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
        mPdLoading = ProgressDialog.show(LoginActivity.this, null, res.getString(R.string.java_login_message_pd), false, true);
        mPdLoading.setCanceledOnTouchOutside(false);
        mPdLoading.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                isStop = true;
            }
        });
    }

    @Override
    public void hideLoading() {
        mPdLoading.dismiss();
    }

    @Override
    public boolean isCancleLodading() {
        return isStop;
    }

    @Override
    public void toHomeActivity() {
        saveUser();
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        ToastUtils.showToast(this, "登录成功");
        finish();
    }

    /**
     * 保存用户的信息
     */
    private void saveUser() {
        getAllText();
        SharedPreferenceUtil.saveUser(this, str_name, str_psw, null);
    }

    @Override
    public void showFaildError(int code) {
        LogUtils.i("CODE", "code is " + code);
        switch (code) {
            case JSONParse_PHP.SERVER_CONNECTION_ERROR:
                ToastUtils.showToast(this, res.getString(R.string.java_login_ServerError));
                break;
            case JSONParse_PHP.STATUS_FAILE:
                ToastUtils.showToast(this, res.getString(R.string.java_login_notmatch));
                break;
            case JSONParse_PHP.STATUS_PARSE_FAIL_INNER:
                ToastUtils.showToast(this, res.getString(R.string.java_login_paser_in));
                break;
            case 500:
                ToastUtils.showToast(this, "服务器内部错误");
                break;
        }

    }

    @Override
    public User getUser() {
        User user = new User(str_name, str_psw);
        clearFocus();
        return user;
    }

    /**
     * 去除焦点
     */
    private void clearFocus() {
        mEtPassword.clearFocus();
        mEtUsername.clearFocus();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_btn_login:
                getAllText();
                if (checkInput() && isOpenNetwork())
                    mUserLoginPresenter.login();
                break;
            case R.id.id_ic_qq:
                mUserLoginPresenter.login_qq(this);
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
            Toast.makeText(LoginActivity.this, res.getString(R.string.java_login_space_input), Toast.LENGTH_LONG).show();
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
        Toast.makeText(LoginActivity.this, res.getString(R.string.java_login_networkerror), Toast.LENGTH_LONG).show();
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ActivityCollector.finishAll();
    }


    /**
     * 接收qq的返回的数据
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Tencent.onActivityResultData(requestCode, resultCode, data, null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPdLoading != null && mPdLoading.isShowing()) {
            mPdLoading.dismiss();
        }

    }

}
