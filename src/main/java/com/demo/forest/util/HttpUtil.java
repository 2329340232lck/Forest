package com.demo.forest.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * http工具类
 *
 * @author lck
 */
public class HttpUtil {

    /**
     * 判断是否是ajax请求
     */
    public static Boolean isAjax(HttpServletRequest request) {
        String header = request.getHeader("X-Requested-With");
        String accept = request.getHeader("accept");
        if (accept == null || header == null) {
            return false;
        } else if (header.equals("XMLHttpRequest") || accept.equals("application/json")) {
            return true;
        } else {
            return false;
        }
    }

    public static ResponseInfo setErrorCode(HttpServletResponse response, ResponseInfo responseInfo) {
        response.setStatus(responseInfo.getCode());
        return responseInfo;
    }
}
