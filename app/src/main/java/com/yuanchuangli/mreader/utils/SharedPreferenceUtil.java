package com.yuanchuangli.mreader.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

/**
 * @author Blank
 * @description SharedPreferenceUtil
 * @time 2016/12/8 17:59
 */

public class SharedPreferenceUtil {
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
    public final static String SHAR_PRE_TOKEN = "token";
    /**
     * 用户是否第一次登录
     */
    public static final String SHAR_PRE_ISLOGIN = "islogin";

    /**
     * 保存用户的appid
     */
    public static final String SHAR_PRE_USER_APPID = "appid";

    private SharedPreferenceUtil() {

    }

    public static SharedPreferences.Editor SharedPreferencesInit(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHAR_PRE_FILENAME_LOGINCONFIG, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.commit();
        return editor;
    }

    /**
     * 储存用户的信息
     *
     * @param context
     * @param user
     * @param psw
     * @param token
     */
    public static void saveUser(Context context, String user, String psw, String token) {
        SharedPreferences.Editor editor = SharedPreferencesInit(context);
        if (TextUtils.isEmpty(token)) {
            editor.putString(SHAR_PRE_USERNAME, user);
            editor.putString(SHAR_PRE_PASSWORD, psw);
        } else {
            editor.putString(SHAR_PRE_TOKEN, token);
        }
        editor.putBoolean(SHAR_PRE_ISLOGIN, true);
        editor.apply();
    }

    public static SharedPreferences getUser(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHAR_PRE_FILENAME_LOGINCONFIG, Context.MODE_PRIVATE);
        return sharedPreferences;
    }


}
