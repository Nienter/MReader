package com.yuanchuangli.mreader.parse;

import com.yuanchuangli.mreader.model.bean.doc.DocBean;
import com.yuanchuangli.mreader.utils.LogUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * @author Balnk
 * @description JSONParse_PHP 解析服务器传回的数据
 * @time 2016/11/24 18:26
 */

public class JSONParse_PHP {
    /**
     * 服务端返回的状态码，表示成功
     */
    public final static int STATUS_SUCCESS = 0;
    /**
     * 服务端返回的状态码，表示失败
     */
    public final static int STATUS_FAILE = 1;
    /**
     * 内部解析错误(json解析异常)
     */
    public final static int STATUS_PARSE_FAIL_INNER = 400;
    /**
     * 服务器异常
     */
    public final static int SERVER_CONNECTION_ERROR = 2;

    /**
     * 私有化构造函数，不允许别人初始化
     */
    private JSONParse_PHP() {
        throw (new Error("不允许初始化"));
    }

    /**
     * 获取服务端的状态码，返回相应的状态码
     *
     * @param JSON 服务端获取的字符串值
     * @return 状态码
     */
    public static int getStatus(String JSON) {
        try {

            JSONObject jsonObject = new JSONObject(JSON);
            return jsonObject.getInt("code");
        } catch (JSONException e) {
            e.printStackTrace();
            if (JSON == "") {
                return SERVER_CONNECTION_ERROR;
            }
            return STATUS_PARSE_FAIL_INNER;
        }
    }

    /**
     * 获取result字段的值
     *
     * @param JSON 服务器传回的字符串
     * @return result的值
     */
    public static String getInfo(String JSON) {
        try {
            JSONObject jsonObject = new JSONObject(JSON);
            return jsonObject.getString("result");
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 从服务器返回的额字段取出时间
     *
     * @param JSON 要解析的字符串
     * @return 时间  自1970年1月1日0时起的秒数
     */
    public static int getServerTime(String JSON) {
        try {
            LogUtils.i("JSON_TIME", JSON);
            String a = getInfo(JSON);
            JSONObject jsonObject = new JSONObject(a);
            LogUtils.i("time", "时间是" + jsonObject.getInt("time"));
            return jsonObject.getInt("time");
        } catch (JSONException e) {
            e.printStackTrace();
            return STATUS_PARSE_FAIL_INNER;
        }

    }

    /**
     * 解析token
     *
     * @param JSON
     * @return
     */
    public static String getToken(String JSON) {
        try {
            LogUtils.i("JSON_TIME", JSON);
            String info = getInfo(JSON);
            JSONObject jsonObject = new JSONObject(info);
            LogUtils.i("token", "token是" + jsonObject.getString("token"));
            return jsonObject.getString("token");
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }

    }

    /**
     * 解析文档列表的方法
     *
     * @param JSON 服务器传过来的字符串
     * @return列表
     */
    public static ArrayList<DocBean> getDocList(String JSON) {
        LogUtils.i("DOC_LIST", JSON);
        ArrayList<DocBean> docList = new ArrayList<>();
        //String info = getInfo(JSON);
        try {
            JSONObject jsonObject = new JSONObject(JSON);
            JSONArray jsonArray = jsonObject.getJSONArray("result");
            for (int i = 0; i < jsonArray.length(); i++) {
                DocBean doc = new DocBean();
                JSONObject object = (JSONObject) jsonArray.get(i);
                doc.setId(object.getString("id"));
                doc.setClick(object.getString("click"));
                doc.setLitpic(object.getString("litpic"));
                doc.setNeedCoin(object.getString("needmoney"));
                doc.setPageNumber(object.getString("pagenumber"));
                doc.setTitle(object.getString("title"));
                doc.setUpdateTime(object.getString("updatenumber"));
                docList.add(doc);
            }
            return docList;
        } catch (JSONException e) {
            e.printStackTrace();
            return docList;
        }

    }
}
