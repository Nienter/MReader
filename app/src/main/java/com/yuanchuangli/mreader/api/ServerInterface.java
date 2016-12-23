package com.yuanchuangli.mreader.api;

/**
 * 总接口
 * Created by Blank on 2016/11/21 11:21
 */

public class ServerInterface {
    /**
     * 请求的总接口，其他接口以此为基础扩展
     */
    private final static String ADDRESS = "http://192.168.0.113/mobile/";
    /**
     * 登录请求的总接口
     */
    public final static String LOGIN_BASE_ADDRESS = ADDRESS + "login/";
    /**
     * 文档请求的总接口
     */
    public final static String DOC_BASE_ADDRESS = ADDRESS + "api/";
}
