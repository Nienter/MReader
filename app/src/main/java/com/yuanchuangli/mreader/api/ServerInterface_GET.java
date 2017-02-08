package com.yuanchuangli.mreader.api;

import com.yuanchuangli.mreader.model.bean.doc.DocBean;
import com.yuanchuangli.mreader.utils.SharedPreferenceUtil;
import com.yuanchuangli.mreader.utils.init.BaseApplication;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Blank
 * @description ServerInterface_GET 处理get请求的接口
 * @time 2017/1/4 15:43
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
    public final static String REQUEST_PATH_USER_RECORD_DOWNLOAD = ServerInterface.DOC_BASE_ADDRESS + "downloadRecord";//展示未用
    /**
     * REQUREST_PATH_DOC_PROVIEW 文档预览的接口
     */
    public final static String REQUREST_PATH_DOC_PROVIEW = ServerInterface.DOC_BASE_ADDRESS + "docPreview";
    /**
     * REQUEST_PATH_DOC_DOWNLOAD 文档下载路径的接口
     */
    public final static String REQUEST_PATH_DOC_DOWNLOAD = ServerInterface.DOC_BASE_ADDRESS + "docDownloadLink";

    /**
     * 私有化构造方法
     */
    private ServerInterface_GET() {
        throw new Error("不允许初始化");
    }

    /**
     * 从服务器获取时间
     *
     * @return 服务器的时间
     */
    public static String getTimeFromServer() {
        try {
            URL url = new URL(REQUEST_PATH_TIME);
            return HttpUtil.sendGet(url, null);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return "";
        }

    }

    /**
     * 从服务器获取文档列表
     *
     * @return 文档列表的json
     */
    public static String getDocListFromServer(int page) {
        try {
            URL url = new URL(REQUEST_PATH_DOC_CHOOSEN);
            Map<String, Object> map = new HashMap<>();
            map.put("page", page);
            map.put("token", SharedPreferenceUtil.getUser(BaseApplication.getContext()).getString("token", null));
            return HttpUtil.sendGet(url, map);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 从服务器获取单个文档的链接信息
     *
     * @param docBean
     * @return 文档的信息
     */
    public static String getDocInfromServer(DocBean docBean) {
        final String docId = docBean.getdocId();
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("docid", docId);
            map.put("token", SharedPreferenceUtil.getUser(BaseApplication.getContext()).getString("token", null));
            URL url = new URL(ServerInterface_GET.REQUREST_PATH_DOC_PROVIEW);
            return HttpUtil.sendGet(url, map);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 从服务器查询充值记录信息
     *
     * @return 充值记录信息
     */
    public static String getRechargeRecordsFromServer() {
        try {
            URL url = new URL(ServerInterface_GET.REQUEST_PATH_USER_RECORD_RECHARGE);
            Map<String, Object> map = new HashMap<>();
            map.put("token", SharedPreferenceUtil.getUser(BaseApplication.getContext()).getString("token", null));
            return HttpUtil.sendGet(url, map);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 从服务器获取文档的下载地址（非游客下载）
     *
     * @param docId
     * @return
     */
    public static String getDownloadLink(String docId) {
        try {
            URL url = new URL(ServerInterface_GET.REQUEST_PATH_DOC_DOWNLOAD);
            Map<String, Object> map = new HashMap<>();
            map.put("token", SharedPreferenceUtil.getUser(BaseApplication.getContext()).getString("token", null));
            map.put("docid", docId);
            return HttpUtil.sendGet(url, map);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return "";
    }
}
