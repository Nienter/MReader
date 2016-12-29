package com.yuanchuangli.mreader.api;

/**
 * @author Blank
 * @description ServerInterface 总接口
 * @time 2016/12/26 12:16
 */
public class ServerInterface {
    /**
     * 请求的总接口，其他接口以此为基础扩展
     */
    //private final static String ADDRESS = "http://192.168.0.113/mobile/";
    private final static String ADDRESS = "http://testm.book118.com/mobile/";//12.26改
    /**
     * 登录请求的总接口
     */
    public final static String LOGIN_BASE_ADDRESS = ADDRESS + "login/";
    /**
     * 文档请求的总接口
     */
    public final static String DOC_BASE_ADDRESS = ADDRESS + "api/";
}
