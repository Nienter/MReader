package com.yuanchuangli.mreader.api;

import com.yuanchuangli.mreader.utils.CreateSignUtil;
import com.yuanchuangli.mreader.utils.LogUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * 发送http请求
 * Created by Blank on 2016/11/21 16:44
 */

public class HttpUtil {
    /**
     * 不允许初始化
     */
    public HttpUtil() {
        throw new Error("不允许初始化");
    }

    /**
     * get方式提交
     *
     * @param url    url地址
     * @param params 参数，集合形式
     * @return 返回的json信息
     */
    public static String sendGet(URL url, Map<String, Object> params) {
        String result;
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = null;
        HttpURLConnection conn = null;
        String urlName;
        try {
            if (params == null) {
                urlName = url.toString();
            } else {
                urlName = url + "?" + CreateSignUtil.createSign(params, false);
            }
            LogUtils.i("urlName", urlName);
            URL realUrl = new URL(urlName);
            conn = (HttpURLConnection) realUrl.openConnection();
            //conn.setConnectTimeout(5000);
            conn.setRequestMethod("GET");
            int code = conn.getResponseCode();
            LogUtils.i("url", "code=" + code);
            if (code == 200) {
                InputStream in = conn.getInputStream();
                reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
                while ((result = reader.readLine()) != null) {
                    sb.append(result);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null)
                    reader.close();
                if (conn != null)
                    conn.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    /**
     * post方式发送请求
     *
     * @param url    请求的url地址
     * @param params 请求的参数
     * @return json串
     */
    public static String sendPost(URL url, String params) {
        String result;
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = null;
        OutputStreamWriter osw = null;
        HttpURLConnection conn = null;
        LogUtils.i("url", url.toString());
        try {
            conn = (HttpURLConnection) url.openConnection();
            //设置连接属性
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);// 忽略缓存
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            osw = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            osw.write(params);//params没有加密，转型
            osw.flush();//
            osw.close();
            int code = conn.getResponseCode();
            if (code == 200) {
                reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                while ((result = reader.readLine()) != null) {
                    sb.append(result);
                }
            } else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        } finally {
            try {
                if (reader != null)
                    reader.close();
                if (osw != null)
                    osw.close();
                if (conn != null)
                    conn.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
