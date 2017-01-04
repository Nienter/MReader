package com.yuanchuangli.mreader.api;

/**
 * @author Blank
 * @description ServerInterface  应用的总接口，所有的子接口都以此为基础进行扩展
 * @time 2017/1/4 15:42 线上数据库
 */
public class ServerInterface {
    /**
     * 请求的总接口，其他接口以此为基础扩展
     */
    //private final static String ADDRESS = "http://192.168.0.113/mobile/";
    private final static String ADDRESS = "http://testm.book118.com/mobile/";
    /**
     * 登录请求的总接口
     */
    public final static String LOGIN_BASE_ADDRESS = ADDRESS + "login/";
    /**
     * 文档请求的总接口
     */
    public final static String DOC_BASE_ADDRESS = ADDRESS + "api/";
}
