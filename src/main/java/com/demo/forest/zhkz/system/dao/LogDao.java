package com.demo.forest.zhkz.system.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.forest.zhkz.system.domain.LogInfo;
import org.apache.ibatis.annotations.Param;

public interface LogDao {

    IPage<LogInfo> queryLogInfo(@Param("page") Page page, @Param("logInfo") LogInfo logInfo) throws Exception;
}
