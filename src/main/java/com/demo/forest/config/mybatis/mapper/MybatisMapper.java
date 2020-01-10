package com.demo.forest.config.mybatis.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.forest.config.mybatis.provider.MybatisSQLProvider;
import org.apache.ibatis.annotations.*;

import java.sql.SQLException;
import java.util.List;

public interface MybatisMapper {

    @SelectProvider(type = MybatisSQLProvider.class, method = "selectList")
    @ResultMap({"resultMap"})
    <T> List<T> selectList(@Param("paramObject") Object paramObject) throws SQLException;

    @SelectProvider(type = MybatisSQLProvider.class, method = "selectPage")
    @ResultMap({"resultMap"})
    <T> IPage<T> selectPage(@Param("page") Page page, @Param("paramObject") Object paramObject) throws SQLException;

    @SelectProvider(type = MybatisSQLProvider.class, method = "selectCount")
    Long selectCount(@Param("conditionObject") Object conditionObject) throws SQLException;

    @InsertProvider(type = MybatisSQLProvider.class, method = "insert")
    Long insert(@Param("paramObject") Object paramObject) throws SQLException;

    @UpdateProvider(type = MybatisSQLProvider.class, method = "update")
    Long update(@Param("updateParam") Object updateParam, @Param("conditionObject") Object condition) throws SQLException;

    @DeleteProvider(type = MybatisSQLProvider.class, method = "delete")
    Long delete(@Param("conditionObject") Object condition) throws SQLException;


}
