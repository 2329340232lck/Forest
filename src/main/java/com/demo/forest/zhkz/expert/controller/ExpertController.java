package com.demo.forest.zhkz.expert.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.forest.util.ResponseInfo;
import com.demo.forest.zhkz.expert.entity.ExpertInfo;
import com.demo.forest.zhkz.expert.service.ExpertService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.demo.forest.util.ResponseInfo.SUCCESS;

@RestController
@RequiresRoles(value = {"超级管理员","专家管理员"},logical = Logical.OR)
public class ExpertController {

    @Autowired
    private ExpertService expertService;

    @GetMapping(value = "/expert")
    public ResponseInfo queryExpertInfo(Page page, ExpertInfo expertInfo) throws Exception {
        return SUCCESS(expertService.queryExpertInfo(page, expertInfo));
    }

    @PostMapping(value = "/expert")
    public ResponseInfo insertExpertInfo(ExpertInfo expertInfo) throws Exception {
        expertService.insertExpertInfo(expertInfo);
        return SUCCESS();
    }

    @PutMapping(value = "/expert")
    public ResponseInfo updateExpertInfo(ExpertInfo expertInfo) throws Exception {
        expertService.updateExpertInfo(expertInfo);
        return SUCCESS();
    }

    @DeleteMapping(value = "/expert")
    public ResponseInfo deleteExpertInfo(ExpertInfo expertInfo) throws Exception {
        expertService.deleteExpertInfo(expertInfo);
        return SUCCESS();
    }
}
