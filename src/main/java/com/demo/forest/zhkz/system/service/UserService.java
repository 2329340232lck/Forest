package com.demo.forest.zhkz.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.forest.zhkz.system.domain.UserInfo;
import com.demo.forest.zhkz.system.vo.MenuVo;
import com.demo.forest.zhkz.system.vo.UserInfoVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;

public interface UserService {

    UserInfo getUserInfo(String userName);

    Set<String> getUserRoles(String userName);

    void loginExit(UserInfo userInfo, HttpServletRequest request, HttpServletResponse response) throws Exception;

    IPage<UserInfoVo> queryUserInfo(Page page, UserInfo userInfo) throws Exception;

    void insertUser(UserInfo userInfo) throws Exception;

    void updateUser(UserInfo userInfo) throws Exception;

    void deleteUser(UserInfo userInfo) throws Exception;

    List<MenuVo> queryMenuInfo(UserInfo userInfo) throws Exception;
}
