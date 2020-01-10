package com.demo.forest.config.mybatis.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.forest.config.mybatis.mapper.MybatisMapper;
import com.demo.forest.config.mybatis.service.MybatisService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class MybatisServiceImpl implements MybatisService {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public <T> List<T> selectList(Object condition) throws SQLException {
        MybatisMapper mapper = sqlSession.getMapper(MybatisMapper.class);
        List<T> objects = mapper.selectList(condition);
        return objects;
    }

    @Override
    public <T> T selectOne(Object condition) throws SQLException {
        List<T> objects = selectList(condition);
        Object o = objects.get(0);
        return (T) o;
    }

    @Override
    public <T> IPage<T> selectPage(Page page, Object paramObject) throws SQLException {
        MybatisMapper mapper = sqlSession.getMapper(MybatisMapper.class);
        return mapper.selectPage(page, paramObject);
    }

    @Override
    public long selectCount(Object paramObject) throws SQLException {
        MybatisMapper mapper = sqlSession.getMapper(MybatisMapper.class);
        return mapper.selectCount(paramObject);
    }

    @Override
    public long insert(Object condition) throws SQLException {
        MybatisMapper mapper = sqlSession.getMapper(MybatisMapper.class);
        long insert = mapper.insert(condition);
        return insert;
    }

    @Override
    public long update(Object updateParam, Object condition) throws SQLException {
        MybatisMapper mapper = sqlSession.getMapper(MybatisMapper.class);
        long update = mapper.update(updateParam, condition);
        return update;
    }

    @Override
    public long delete(Object condition) throws SQLException {
        MybatisMapper mapper = sqlSession.getMapper(MybatisMapper.class);
        long delete = mapper.delete(condition);
        return delete;
    }

}
