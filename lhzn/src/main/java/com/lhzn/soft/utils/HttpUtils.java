package com.lhzn.soft.utils;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * http工具类
 *
 * @author qishuai
 */
public class HttpUtils {

    /**
     * 判断URL是否可达
     *
     * @param url 待判断的URL
     * @return true: 可达 false: 不可达
     */
    public static boolean urlIsReach(String url) {
        if (url == null) {
            return false;
        }
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            if (HttpURLConnection.HTTP_OK == connection.getResponseCode()) {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }
}
