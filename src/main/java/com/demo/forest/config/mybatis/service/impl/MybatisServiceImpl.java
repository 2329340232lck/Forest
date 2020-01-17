package com.demo.forest.config.mybatis.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.forest.config.mybatis.mapper.MybatisMapper;
import com.demo.forest.config.mybatis.service.MybatisService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class MybatisServiceImpl implements MybatisService {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public <T> List<T> selectList(Object condition) throws SQLException {
        MybatisMapper mapper = sqlSession.getMapper(MybatisMapper.class);
        return resultListResolver(mapper.selectList(condition), condition);
    }

    @Override
    public <T> T selectOne(Object condition) throws SQLException {
        List<T> list = selectList(condition);
        Object object = list.get(0);
        return (T) object;
    }

    @Override
    public <T> IPage<T> selectPage(Page page, Object paramObject) throws SQLException {
        MybatisMapper mapper = sqlSession.getMapper(MybatisMapper.class);
        IPage<T> tiPage = mapper.selectPage(page, paramObject);
        return tiPage.setRecords(resultListResolver((List<Map<String, ?>>) tiPage.getRecords(), paramObject));
    }

    @Override
    public long selectCount(Object paramObject) throws SQLException {
        MybatisMapper mapper = sqlSession.getMapper(MybatisMapper.class);
        return mapper.selectCount(paramObject);
    }

    @Override
    public long insert(Object condition) throws SQLException {
        MybatisMapper mapper = sqlSession.getMapper(MybatisMapper.class);
        return mapper.insert(condition);
    }

    @Override
    public long update(Object updateParam, Object condition) throws SQLException {
        MybatisMapper mapper = sqlSession.getMapper(MybatisMapper.class);
        return mapper.update(updateParam, condition);
    }

    @Override
    public long delete(Object condition) throws SQLException {
        MybatisMapper mapper = sqlSession.getMapper(MybatisMapper.class);
        return mapper.delete(condition);
    }

    private <T> List<T> resultListResolver(List<Map<String, ?>> mapList, Object object) {
        ArrayList<T> arrayList = new ArrayList<>();
        for (Map<String, ?> map : mapList) {
            try {
                Class<?> cls = object.getClass();
                //实例化对象
                Object instance = cls.newInstance();
                //获取对象所有字段
                Field[] declaredFields = cls.getDeclaredFields();
                for (Field field : declaredFields) {
                    String fieldName = field.getName();
                    if (fieldName.equals("serialVersionUID")) {
                        continue;
                    }
                    for (String key : map.keySet()) {
                        if (fieldName.equals(key)) {
                            Object parameter = map.get(key);
                            String methodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                            cls.getMethod(methodName, field.getType()).invoke(instance, parameter);
                        }
                    }
                }
                arrayList.add((T) instance);
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }
}
