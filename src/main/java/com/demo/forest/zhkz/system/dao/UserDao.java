package com.demo.forest.zhkz.system.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.forest.zhkz.system.domain.UserInfo;
import com.demo.forest.zhkz.system.vo.MenuVo;
import com.demo.forest.zhkz.system.vo.UserInfoVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public interface UserDao {

    IPage<UserInfoVo> queryUserInfo(@Param("page") Page page, @Param("userInfo") UserInfo userInfo) throws SQLException;

    Set<String> getUserRoles(String userName);

    @Select("SELECT userId,userName,roleId,userPassword,userRealName FROM zhkz_user WHERE userName = #{userName}")
    UserInfo getUserInfo(String userName);

    List<MenuVo> queryMenuInfoByUser(UserInfo userInfo) throws SQLException;

    @Select("select * from zhkz_user where userName = #{userName}")
    List<UserInfo> checkIsRepeated(UserInfo userInfo) throws SQLException;
}
