package com.demo.forest.zhkz.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.forest.config.exception.custom.ExceptionEnum;
import com.demo.forest.config.exception.custom.HTMLException;
import com.demo.forest.config.mybatis.service.MybatisService;
import com.demo.forest.util.SessionManager;
import com.demo.forest.zhkz.system.dao.UserDao;
import com.demo.forest.zhkz.system.domain.UserInfo;
import com.demo.forest.zhkz.system.service.UserService;
import com.demo.forest.zhkz.system.vo.MenuVo;
import com.demo.forest.zhkz.system.vo.UserInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    private MybatisService mybatisService;

    public UserServiceImpl(UserDao userDao, MybatisService mybatisService) {
        this.userDao = userDao;
        this.mybatisService = mybatisService;
    }

    @Override
    public UserInfo getUserInfo(String userName) {
        return userDao.getUserInfo(userName);
    }

    @Override
    public Set<String> getUserRoles(String userName) {
        return userDao.getUserRoles(userName);
    }

    @Override
    public void loginExit(UserInfo userInfo, HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
            SessionManager.removeSession(session.getId());
        } else {
            throw new HTMLException(ExceptionEnum.USER_LOGIN_OUT_ERROR);
        }
    }

    @Override
    public IPage<UserInfoVo> queryUserInfo(Page page, UserInfo userInfo) throws Exception {
        return userDao.queryUserInfo(page, userInfo);
    }

    @Override
    public void insertUser(UserInfo userInfo) throws Exception {
        UserInfo info = userDao.getUserInfo(userInfo.getUserName());
        if (info != null) {
            throw new HTMLException(ExceptionEnum.DUPLICATE_USER_NAME);
        }
        mybatisService.insert(userInfo);
    }

    @Override
    public void updateUser(UserInfo userInfo) throws Exception {
        UserInfo condition = new UserInfo();
        condition.setUserId(userInfo.getUserId());
        mybatisService.update(userInfo, condition);
    }

    @Override
    public void deleteUser(UserInfo userInfo) throws Exception {
        mybatisService.delete(userInfo);
    }

    @Override
    public List<MenuVo> queryMenuInfo(UserInfo userInfo) throws Exception {
        return userDao.queryMenuInfoByUser(userInfo);
    }
}
