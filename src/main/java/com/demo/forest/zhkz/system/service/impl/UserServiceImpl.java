package com.demo.forest.zhkz.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.forest.config.exception.custom.ExceptionEnum;
import com.demo.forest.config.exception.custom.HTMLException;
import com.demo.forest.config.mybatis.service.MybatisService;
import com.demo.forest.zhkz.system.dao.UserDao;
import com.demo.forest.zhkz.system.domain.UserInfo;
import com.demo.forest.zhkz.system.service.UserService;
import com.demo.forest.zhkz.system.vo.MenuVo;
import com.demo.forest.zhkz.system.vo.UserInfoVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Resource
    private MybatisService mybatisService;

    @Override
    public UserInfo getUserInfo(String userName) {
        return userDao.getUserInfo(userName);
    }

    @Override
    public Set<String> getUserRoles(String userName) {
        return userDao.getUserRoles(userName);
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
