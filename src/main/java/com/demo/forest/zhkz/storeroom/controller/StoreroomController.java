package com.demo.forest.zhkz.storeroom.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.forest.util.ResponseInfo;
import com.demo.forest.zhkz.storeroom.domain.StoreroomInfo;
import com.demo.forest.zhkz.storeroom.service.StoreroomService;
import com.demo.forest.zhkz.storeroom.vo.StoreroomInfoVo;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/storeroom")
@RequiresRoles(value = {"超级管理员","库房管理员"},logical = Logical.OR)
public class StoreroomController {

    @Autowired
    private StoreroomService storeroomService;

    @RequestMapping(value = "/queryStoreroomInfo.ajax")
    public ResponseInfo queryStoreroomInfo(Page page, StoreroomInfoVo storeroomInfoVo) throws Exception {
        return ResponseInfo.SUCCESS(storeroomService.queryStoreroomInfo(page, storeroomInfoVo));
    }

    @RequestMapping(value = "/getResources.ajax")
    public ResponseInfo getResources(String key) throws Exception {
        return ResponseInfo.SUCCESS(storeroomService.getResources(key));
    }

    @RequestMapping(value = "/insertStoreroomInfo.ajax")
    public ResponseInfo insertStoreroomInfo(@RequestBody StoreroomInfoVo storeroomInfoVo) throws Exception {
        storeroomService.insertStoreroomInfo(storeroomInfoVo);
        return ResponseInfo.SUCCESS();
    }

    @RequestMapping(value = "/updateStoreroomInfo.ajax")
    public ResponseInfo updateStoreroomInfo(@RequestBody StoreroomInfoVo storeroomInfo) throws Exception {
        storeroomService.updateStoreroomInfo(storeroomInfo);
        return ResponseInfo.SUCCESS();
    }

    @RequestMapping(value = "/deleteStoreroomInfo.ajax")
    public ResponseInfo deleteStoreroomInfo(StoreroomInfo storeroomInfo) throws Exception {
        storeroomService.deleteStoreroomInfo(storeroomInfo);
        return ResponseInfo.SUCCESS();
    }
}
