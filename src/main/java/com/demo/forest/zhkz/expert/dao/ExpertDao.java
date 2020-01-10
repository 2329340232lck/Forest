package com.demo.forest.zhkz.expert.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.forest.zhkz.expert.entity.ExpertInfo;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;

public interface ExpertDao {

    IPage<ExpertInfo> queryExpertInfo(@Param("page") Page page, @Param("expertInfo") ExpertInfo expertInfo) throws SQLException;
}
