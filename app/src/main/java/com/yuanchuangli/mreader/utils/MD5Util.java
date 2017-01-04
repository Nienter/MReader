package com.yuanchuangli.mreader.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * md5 加密工具类
 * Created by Blank on 2016/11/22 11:28
 */
class MD5Util {
    private static final String encryModel = "MD5";

    /**
     * 不允许别人构造这个对象
     */
    private MD5Util() {
        throw new Error("Do not need instantiate!");
    }

    /**
     * 32位小写md5加密
     *
     * @param str 需要加密的字符串
     * @return 加密后的字符串
     */

    static String md5(String str) {
        return encrypt(encryModel, str);
    }

    private static String encrypt(String algorithm, String str) {
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            md.update(str.getBytes());
            StringBuilder sb = new StringBuilder();
            byte[] bytes = md.digest();
            for (byte aByte : bytes) {
                int b = aByte & 0xff;
                if (b < 0x10) {
                    sb.append("0");
                }
                sb.append(Integer.toHexString(b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            return "";
        }
    }
}
