package com.demo.forest.config.mybatis.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.sql.SQLException;
import java.util.List;

public interface MybatisService {

    <T> List<T> selectList(Object condition) throws SQLException;

    <T> T selectOne(Object condition) throws SQLException;

    <T> IPage<T> selectPage(Page page, Object paramObject) throws SQLException;

    long selectCount(Object paramObject) throws SQLException;

    long insert(Object condition) throws SQLException;

    long update(Object updateParam, Object condition) throws SQLException;

    long delete(Object condition) throws SQLException;
}
