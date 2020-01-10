package com.demo.forest.config.jwt;//package cn.config.jwt;
//
//import cn.util.HttpUtil;
//import cn.util.ResponseInfo;
//import cn.util.SessionManager;
//import cn.zhkz.system.domain.UserInfo;
//import com.alibaba.fastjson.JSON;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Component
//@Slf4j
//public class TokenInterceptors implements HandlerInterceptor {
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        log.info("请求来源 = " + request.getRequestURL());
//        UserInfo user = (UserInfo) SessionManager.getSession(request.getSession().getId());
//        if (user == null) {
//            reLogin(request, response, "未登录或登录已失效,重新登录");
//        } else {
//            return true;
//        }
//        if (!HttpUtil.isAjax(request)) {
//            response.sendRedirect("/public/login.html");
//        }
//        return false;
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
////        System.out.println("后处理");
////        System.out.println("handler = " + handler);
////        System.out.println("modelAndView = " + modelAndView);
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
////        System.out.println("处理完成");
//    }
//
//    private void reLogin(HttpServletRequest request, HttpServletResponse response, String message) throws IOException {
//        ResponseInfo responseInfo = new ResponseInfo();
//        responseInfo.setCode(601);
//        responseInfo.setResultMessage(message);
//        response.setStatus(500);
//        response.setContentType("application/json");
//        response.setCharacterEncoding("UTF-8");
//        response.getWriter().write(JSON.toJSONString(responseInfo));
//    }
//}
