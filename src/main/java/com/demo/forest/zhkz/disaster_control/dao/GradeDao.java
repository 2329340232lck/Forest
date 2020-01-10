package com.demo.forest.zhkz.disaster_control.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.forest.zhkz.disaster_control.vo.GradeInfoVo;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;

public interface GradeDao {

    IPage<GradeInfoVo> queryGraderInfo(@Param("page") Page page, @Param("gradeInfo") GradeInfoVo gradeInfo) throws SQLException;
}
