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
import org.thymeleaf.util.DateUtils;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Service
@Slf4j
public class LogServiceImpl implements LogService {

    @Autowired
    private MybatisService mybatisService;

    @Resource
    private LogDao logDao;

    @Override
    public IPage<LogInfo> queryLogInfo(Page page, LogInfo logInfo) throws Exception {
        return logDao.queryLogInfo(page, logInfo);
    }

    @Override
    public void insertLogInfo(String message) throws Exception {
        LogInfo logInfo = new LogInfo();
        logInfo.setLogCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        logInfo.setLogContent(message);
        mybatisService.insert(logInfo);
    }

    public static void main(String[] args) {
        Calendar now = DateUtils.createNow();
        System.out.println("now = " + now);
    }
}
