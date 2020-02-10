package com.demo.forest.zhkz.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.forest.config.exception.custom.ExceptionEnum;
import com.demo.forest.util.ResponseInfo;
import com.demo.forest.zhkz.system.domain.UserInfo;
import com.demo.forest.zhkz.system.service.LogService;
import com.demo.forest.zhkz.system.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import static com.demo.forest.util.HttpUtil.setErrorCode;
import static com.demo.forest.util.ResponseInfo.ERROR;
import static com.demo.forest.util.ResponseInfo.SUCCESS;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private LogService logService;

    @PostMapping(value = "/login")
    public ResponseInfo userLogin(UserInfo userInfo, HttpServletResponse response) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userInfo.getUserName(), userInfo.getUserPassword());
        token.setRememberMe(true);
        try {
            subject.login(token);
            logService.insertLogInfo("用户" + userInfo.getUserName() + "登录系统!");
        } catch (IncorrectCredentialsException | UnknownAccountException e) {
            return setErrorCode(response, ERROR(ExceptionEnum.USER_LOGIN_FAIL));
        } catch (DisabledAccountException e) {
            return setErrorCode(response, ERROR(ExceptionEnum.DISABLED_ACCOUNT));
        } catch (ExcessiveAttemptsException e) {
            return setErrorCode(response, ERROR(ExceptionEnum.TOO_MANY_ATTEMPTS));
        }
        UserInfo info = userService.getUserInfo(userInfo.getUserName());
        info.setUserPassword(null);
        return SUCCESS(info);
    }

    @GetMapping(value = "/logout")
    public ResponseInfo loginOut() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return SUCCESS();
    }

    @GetMapping(value = "/queryMenuInfo")
    public ResponseInfo queryMenuInfo(UserInfo userInfo) throws Exception {
        return SUCCESS(userService.queryMenuInfo(userInfo));
    }

    @RequiresRoles("超级管理员")
    @GetMapping(value = "/userInfo")
    public ResponseInfo queryUserInfo(Page page, UserInfo userInfo) throws Exception {
        return SUCCESS(userService.queryUserInfo(page, userInfo));
    }

    @RequiresRoles("超级管理员")
    @PostMapping(value = "/userInfo")
    public ResponseInfo insertUser(UserInfo userInfo) throws Exception {
        userService.insertUser(userInfo);
        return SUCCESS();
    }

    @RequiresRoles("超级管理员")
    @PutMapping(value = "/userInfo")
    public ResponseInfo updateUser(UserInfo userInfo) throws Exception {
        userService.updateUser(userInfo);
        return SUCCESS();
    }

    @RequiresRoles("超级管理员")
    @DeleteMapping(value = "/userInfo")
    public ResponseInfo deleteUser(UserInfo userInfo) throws Exception {
        userService.deleteUser(userInfo);
        return SUCCESS();
    }
}
