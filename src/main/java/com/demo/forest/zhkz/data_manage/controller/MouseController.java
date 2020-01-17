package com.demo.forest.zhkz.data_manage.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.forest.util.ResponseInfo;
import com.demo.forest.zhkz.data_manage.domain.MouseInfo;
import com.demo.forest.zhkz.data_manage.service.MouseService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiresRoles(value = {"超级管理员", "资料管理员"}, logical = Logical.OR)
public class MouseController {

    @Autowired
    private MouseService mouseService;

    @GetMapping(value = "/mouse")
    public ResponseInfo queryMouseInfo(Page page, MouseInfo mouseInfo) throws Exception {
        return ResponseInfo.SUCCESS(mouseService.queryMouseInfo(page, mouseInfo));
    }

    @PostMapping(value = "/mouse")
    public ResponseInfo insertMouseInfo(MouseInfo mouseInfo) throws Exception {
        mouseService.insertMouseInfo(mouseInfo);
        return ResponseInfo.SUCCESS();
    }

    @PutMapping(value = "/mouse")
    public ResponseInfo updateMouseInfo(MouseInfo mouseInfo) throws Exception {
        mouseService.updateMouseInfo(mouseInfo);
        return ResponseInfo.SUCCESS();
    }

    @DeleteMapping(value = "/mouse")
    public ResponseInfo deleteMouseInfo(MouseInfo mouseInfo) throws Exception {
        mouseService.deleteMouseInfo(mouseInfo);
        return ResponseInfo.SUCCESS();
    }
}
