package com.demo.forest.zhkz.expert.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.forest.util.ResponseInfo;
import com.demo.forest.zhkz.expert.entity.ExpertInfo;
import com.demo.forest.zhkz.expert.service.ExpertService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/expert")
@RequiresRoles(value = {"超级管理员","专家管理员"},logical = Logical.OR)
public class ExpertController {

    @Autowired
    private ExpertService expertService;

    @RequestMapping(value = "/queryExpertInfo.ajax")
    public ResponseInfo queryExpertInfo(Page page, ExpertInfo expertInfo) throws Exception {
        return ResponseInfo.SUCCESS(expertService.queryExpertInfo(page, expertInfo));
    }

    @RequestMapping(value = "/insertExpertInfo.ajax")
    public ResponseInfo insertExpertInfo(ExpertInfo expertInfo) throws Exception {
        expertService.insertExpertInfo(expertInfo);
        return ResponseInfo.SUCCESS();
    }

    @RequestMapping(value = "/updateExpertInfo.ajax")
    public ResponseInfo updateExpertInfo(ExpertInfo expertInfo) throws Exception {
        expertService.updateExpertInfo(expertInfo);
        return ResponseInfo.SUCCESS();
    }

    @RequestMapping(value = "/deleteExpertInfo.ajax")
    public ResponseInfo deleteExpertInfo(ExpertInfo expertInfo) throws Exception {
        expertService.deleteExpertInfo(expertInfo);
        return ResponseInfo.SUCCESS();
    }
}
