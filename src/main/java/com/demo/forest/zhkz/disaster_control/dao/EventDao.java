package com.demo.forest.zhkz.disaster_control.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.forest.zhkz.disaster_control.entity.EventInfo;
import com.demo.forest.zhkz.disaster_control.vo.EventInfoVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.sql.SQLException;

@Repository
public interface EventDao {

    IPage<EventInfoVo> queryEventInfo(@Param("page") Page page, @Param("eventInfo") EventInfo eventInfo) throws SQLException;
}
