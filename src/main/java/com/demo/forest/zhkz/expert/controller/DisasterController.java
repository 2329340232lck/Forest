package com.demo.forest.zhkz.expert.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.forest.util.ResponseInfo;
import com.demo.forest.zhkz.disaster_control.entity.EventInfo;
import com.demo.forest.zhkz.expert.service.DisasterService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiresRoles(value = {"超级管理员", "专家管理员"}, logical = Logical.OR)
public class DisasterController {

    @Autowired
    private DisasterService disasterService;

    @GetMapping(value = "/disaster")
    public ResponseInfo queryDisasterInfo(Page page, EventInfo eventInfo) throws Exception {
        return ResponseInfo.SUCCESS(disasterService.queryDisasterInfo(page, eventInfo));
    }
}
