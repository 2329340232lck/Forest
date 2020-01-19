package com.demo.forest.config.exception;

import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import com.demo.forest.config.exception.custom.HTMLException;
import com.demo.forest.util.HttpUtil;
import com.demo.forest.util.ResponseInfo;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

import static com.demo.forest.util.HttpUtil.setErrorCode;
import static com.demo.forest.util.ResponseInfo.ERROR;

/**
 * 全局异常统一处理器
 */
@ControllerAdvice
public class UnifiedExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ModelAndView exceptionHandler(Exception e, HttpServletRequest request, HttpServletResponse response) {
        ResponseInfo responseInfo;
        //错误类型判断.....
        if (e instanceof HTMLException) {
            HTMLException he = (HTMLException) e;
            responseInfo = setErrorCode(response, ERROR(he.getErrorCode(), he.getErrorMessage()));
        } else if (e instanceof NullPointerException) {
            responseInfo = setErrorCode(response, ERROR(e.getMessage()));
        } else if (e instanceof SQLException) {
            responseInfo = setErrorCode(response, ERROR(e.getMessage()));
        } else if (e instanceof AuthorizationException) {
            responseInfo = setErrorCode(response, ERROR(605, e.getMessage()));
        } else {
            responseInfo = setErrorCode(response, ERROR(e.getMessage()));
            e.printStackTrace();
        }
        ModelAndView modelAndView = new ModelAndView();
        //判断是否为AJAX请求
        if (HttpUtil.isAjax(request)) {
            //ajax请求返回JSON数据
            modelAndView.setView(new FastJsonJsonView());
        } else {
            modelAndView.setViewName("/public/error/error");
        }
        return modelAndView.addObject("responseInfo", responseInfo);
    }

}
