package com.demo.forest.zhkz.data_manage.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.forest.util.ResponseInfo;
import com.demo.forest.zhkz.data_manage.domain.MouseInfo;
import com.demo.forest.zhkz.data_manage.service.MouseService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mouse")
@RequiresRoles(value = {"超级管理员", "资料管理员"}, logical = Logical.OR)
public class MouseController {

    @Autowired
    private MouseService mouseService;

    @RequestMapping(value = "/queryMouseInfo.ajax")
    public ResponseInfo queryMouseInfo(Page page, MouseInfo mouseInfo) throws Exception {
        return ResponseInfo.SUCCESS(mouseService.queryMouseInfo(page, mouseInfo));
    }

    @RequestMapping(value = "/insertMouseInfo.ajax")
    public ResponseInfo insertMouseInfo(MouseInfo mouseInfo) throws Exception {
        mouseService.insertMouseInfo(mouseInfo);
        return ResponseInfo.SUCCESS();
    }

    @RequestMapping(value = "/updateMouseInfo.ajax")
    public ResponseInfo updateMouseInfo(MouseInfo mouseInfo) throws Exception {
        mouseService.updateMouseInfo(mouseInfo);
        return ResponseInfo.SUCCESS();
    }

    @RequestMapping(value = "/deleteMouseInfo.ajax")
    public ResponseInfo deleteMouseInfo(MouseInfo mouseInfo) throws Exception {
        mouseService.deleteMouseInfo(mouseInfo);
        return ResponseInfo.SUCCESS();
    }
}
