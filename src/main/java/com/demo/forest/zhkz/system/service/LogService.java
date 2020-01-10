package com.demo.forest.zhkz.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.forest.zhkz.system.domain.LogInfo;

public interface LogService {

    IPage<LogInfo> queryLogInfo(Page page, LogInfo logInfo) throws Exception;

    void insertLogInfo(LogInfo logInfo) throws Exception;
}
