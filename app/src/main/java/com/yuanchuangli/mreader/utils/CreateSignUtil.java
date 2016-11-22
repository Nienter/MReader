package com.yuanchuangli.mreader.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

/**
 * 签名算法
 * 以参数（包括token）进行字母排序然后加上秘钥，时间进行MD5加密。
 * Created by Blank on 2016/11/22 11:28
 */
public class CreateSignUtil {
    public final static String SECRETKEY = "suidysady9osu;sdjh";

    private CreateSignUtil() {
        throw (new Error("Do not need instantiate!"));
    }

    public static String createSign(Map<String, Object> params, boolean encode) throws UnsupportedEncodingException {
        if (params == null) {
            return "";
        } else {
            Set<String> keysSet = params.keySet();
            Object[] keys = keysSet.toArray();
            Arrays.sort(keys);
            StringBuffer temp = new StringBuffer();
            boolean first = true;
            for (Object key : keys) {
                if (first) {
                    first = false;
                } else {
                    temp.append("&");
                }
                temp.append(key).append("=");
                Object value = params.get(key);
                String valueString = "";
                if (null != value) {
                    valueString = String.valueOf(value);
                }
                if (encode) {
                    temp.append(URLEncoder.encode(valueString, "UTF-8"));
                } else {
                    temp.append(valueString);
                }
            }
            String temp2 = temp.toString();
            String temp1 = temp.append("&" + SECRETKEY).append("&" + 1479784191 / 1800).toString();
            return temp2 + "&sign=" + MD5Util.md5(temp1.toString()).toUpperCase();
        }

    }


}
