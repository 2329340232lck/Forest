package com.demo.forest.zhkz.disaster_control.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.forest.zhkz.disaster_control.vo.AreaInfoVo;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;

public interface AreaDao {

    IPage<AreaInfoVo> queryAreaInfo(@Param("page") Page page, @Param("areaInfo") AreaInfoVo areaInfo) throws SQLException;
}
