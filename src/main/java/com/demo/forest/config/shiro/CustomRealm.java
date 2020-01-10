package com.demo.forest.config.shiro;

import com.demo.forest.config.mybatis.service.MybatisService;
import com.demo.forest.zhkz.system.domain.UserInfo;
import com.demo.forest.zhkz.system.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.pam.UnsupportedTokenException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class CustomRealm extends AuthorizingRealm {

    private UserService userService;

    private MybatisService mybatisService;

    private CustomRealm(UserService userService, MybatisService mybatisService) {
        this.userService = userService;
        this.mybatisService = mybatisService;
    }

    //权限验证
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //获取当前用户
        String primaryPrincipal = (String) principalCollection.getPrimaryPrincipal();
        //获取用户权限
        Set<String> userRoles = userService.getUserRoles(primaryPrincipal);
        if (userRoles.isEmpty()) {
            throw new AuthenticationException("未经授权的非法访问!");
        }
        authorizationInfo.setRoles(userRoles);
        return authorizationInfo;
    }

    //身份验证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String principal = (String) authenticationToken.getPrincipal();
        if (principal == null) {
            throw new UnsupportedTokenException("警告,未获取到[Principal],主体参数为空!");
        }
        UserInfo userInfo = userService.getUserInfo(principal);
        if (userInfo == null) {
            throw new UnknownAccountException("当前账号不存在!");
        }
        ByteSource bytes = ByteSource.Util.bytes(principal);
        return new SimpleAuthenticationInfo(principal, userInfo.getUserPassword(), bytes, getName());
    }
}
