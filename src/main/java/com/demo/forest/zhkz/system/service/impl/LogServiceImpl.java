package com.demo.forest.zhkz.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.forest.config.mybatis.service.MybatisService;
import com.demo.forest.zhkz.system.dao.LogDao;
import com.demo.forest.zhkz.system.domain.LogInfo;
import com.demo.forest.zhkz.system.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LogServiceImpl implements LogService {

    @Autowired
    private MybatisService mybatisService;

    @Autowired
    private LogDao logDao;

    @Override
    public IPage<LogInfo> queryLogInfo(Page page, LogInfo logInfo) throws Exception {
        return logDao.queryLogInfo(page, logInfo);
    }

    @Override
    public void insertLogInfo(LogInfo logInfo) throws Exception {
        mybatisService.insert(logInfo);
    }
}
