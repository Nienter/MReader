package com.yuanchuangli.mreader.api;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * 此类主要是使用GET方式请求的接口地址和方法
 * <p>
 * Created by Blank on 2016/11/21 11:25
 */

public class ServerInterface_GET {
    /**
     * REQUEST_PATH_TIME 获取服务器时间的接口
     */
    public final static String REQUEST_PATH_TIME = ServerInterface.LOGIN_BASE_ADDRESS + "getServerTime";
    /**
     * REQUEST_PATH_CHOOSEN 获取精选文档的接口
     */
    public final static String REQUEST_PATH_DOC_CHOOSEN = ServerInterface.DOC_BASE_ADDRESS + "getDocIsNew";
    /**
     * REQUET_PATH_DOC_SEARCH 搜索文档的接口
     */
    public final static String REQUET_PATH_DOC_SEARCH = ServerInterface.DOC_BASE_ADDRESS + "searchDoc";
    /**
     * REQUEST_PATH_USER_RECORD_RECHARGE 用户查询充值记录的接口
     */
    public final static String REQUEST_PATH_USER_RECORD_RECHARGE = ServerInterface.DOC_BASE_ADDRESS + "rechargeRecord";
    /**
     * REQUEST_PATH_USER_RECORD_DOWNLOAD 用户查询下载记录的接口
     */
    public final static String REQUEST_PATH_USER_RECORD_DOWNLOAD = ServerInterface.DOC_BASE_ADDRESS + "downloadRecord";
    /**
     * REQUREST_PATH_DOC_PROVIEW 文档预览的接口
     */
    public final static String REQUREST_PATH_DOC_PROVIEW = ServerInterface.DOC_BASE_ADDRESS + "docPreview";

    /**
     * 私有化构造方法
     */
    private ServerInterface_GET() {
        throw new Error("不允许初始化");
    }

    public static String getTimeFromServer() {
        try {
            URL url = new URL(REQUEST_PATH_TIME);
            return HttpUtil.sendGet(url, null);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return "";
        }

    }

    public static String getDocListFromServer() {
        try {
            URL url = new URL(REQUEST_PATH_DOC_CHOOSEN);
            return HttpUtil.sendGet(url, null);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return "";
        }
    }
}
