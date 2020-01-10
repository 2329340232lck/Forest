package com.demo.forest.zhkz.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.forest.util.ResponseInfo;
import com.demo.forest.zhkz.system.domain.LogInfo;
import com.demo.forest.zhkz.system.service.LogService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/log")
@RequiresRoles("超级管理员")
public class LogController {

    @Autowired
    private LogService logService;

    @RequestMapping("/queryLogInfo.ajax")
    public ResponseInfo queryLogInfo(Page page, LogInfo logInfo) throws Exception {
        return ResponseInfo.SUCCESS(logService.queryLogInfo(page, logInfo));
    }
}
