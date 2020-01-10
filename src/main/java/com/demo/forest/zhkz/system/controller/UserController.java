package com.demo.forest.zhkz.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.forest.config.exception.custom.ExceptionEnum;
import com.demo.forest.util.HttpUtil;
import com.demo.forest.util.ResponseInfo;
import com.demo.forest.zhkz.system.domain.UserInfo;
import com.demo.forest.zhkz.system.service.LogService;
import com.demo.forest.zhkz.system.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private UserService userService;

    private LogService logService;

    public UserController(UserService userService, LogService logService) {
        this.userService = userService;
        this.logService = logService;
    }

    @RequestMapping(value = "/login.ajax", method = RequestMethod.POST)
    public ResponseInfo userLogin(UserInfo userInfo, HttpServletResponse response) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userInfo.getUserName(), userInfo.getUserPassword());
        token.setRememberMe(true);
        try {
            subject.login(token);
            logService.insertLogInfo("用户" + userInfo.getUserName() + "登录系统!");
        } catch (IncorrectCredentialsException | UnknownAccountException e) {
            return HttpUtil.setErrorCode(response, ResponseInfo.ERROR(ExceptionEnum.USER_LOGIN_FAIL));
        } catch (DisabledAccountException e) {
            return HttpUtil.setErrorCode(response, ResponseInfo.ERROR(ExceptionEnum.DISABLED_ACCOUNT));
        } catch (ExcessiveAttemptsException e) {
            return HttpUtil.setErrorCode(response, ResponseInfo.ERROR(ExceptionEnum.TOO_MANY_ATTEMPTS));
        }
        UserInfo info = userService.getUserInfo(userInfo.getUserName());
        info.setUserPassword(null);
        return ResponseInfo.SUCCESS(info);
    }

    @RequestMapping(value = "/logout.ajax", method = RequestMethod.POST)
    public ResponseInfo loginOut() throws Exception {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return ResponseInfo.SUCCESS();
    }

    @RequestMapping(value = "/queryMenuInfo.ajax", method = RequestMethod.GET)
    public ResponseInfo queryMenuInfo(UserInfo userInfo) throws Exception {
        return ResponseInfo.SUCCESS(userService.queryMenuInfo(userInfo));
    }

    @RequiresRoles("超级管理员")
    @RequestMapping(value = "/queryUserInfo.ajax", method = RequestMethod.GET)
    public ResponseInfo queryUserInfo(Page page, UserInfo userInfo) throws Exception {
        return ResponseInfo.SUCCESS(userService.queryUserInfo(page, userInfo));
    }

    @RequiresRoles("超级管理员")
    @RequestMapping(value = "/insertUserInfo.ajax", method = RequestMethod.POST)
    public ResponseInfo insertUser(UserInfo userInfo) throws Exception {
        userService.insertUser(userInfo);
        return ResponseInfo.SUCCESS();
    }

    @RequiresRoles("超级管理员")
    @RequestMapping(value = "/updateUserInfo.ajax", method = RequestMethod.PUT)
    public ResponseInfo updateUser(UserInfo userInfo) throws Exception {
        userService.updateUser(userInfo);
        return ResponseInfo.SUCCESS();
    }

    @RequiresRoles("超级管理员")
    @RequestMapping(value = "/deleteUserInfo.ajax", method = RequestMethod.DELETE)
    public ResponseInfo deleteUser(UserInfo userInfo) throws Exception {
        userService.deleteUser(userInfo);
        return ResponseInfo.SUCCESS();
    }
}
