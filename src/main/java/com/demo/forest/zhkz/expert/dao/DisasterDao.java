package com.demo.forest.zhkz.expert.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.forest.zhkz.disaster_control.entity.EventInfo;
import com.demo.forest.zhkz.disaster_control.vo.EventInfoVo;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;

public interface DisasterDao {

    IPage<EventInfoVo> queryDisasterInfo(@Param("page") Page page, @Param("eventInfo") EventInfo eventInfo) throws SQLException;
}
