package com.demo.forest.zhkz.storeroom.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.forest.util.ResponseInfo;
import com.demo.forest.zhkz.storeroom.domain.StoreroomInfo;
import com.demo.forest.zhkz.storeroom.service.StoreroomService;
import com.demo.forest.zhkz.storeroom.vo.StoreroomInfoVo;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiresRoles(value = {"超级管理员", "库房管理员"}, logical = Logical.OR)
public class StoreroomController {

    @Autowired
    private StoreroomService storeroomService;

    @GetMapping(value = "/storeroom")
    public ResponseInfo queryStoreroomInfo(Page page, StoreroomInfoVo storeroomInfoVo) throws Exception {
        return ResponseInfo.SUCCESS(storeroomService.queryStoreroomInfo(page, storeroomInfoVo));
    }

    @GetMapping(value = "/storeroom/getResources")
    public ResponseInfo getResources(String key) throws Exception {
        return ResponseInfo.SUCCESS(storeroomService.getResources(key));
    }

    @PostMapping(value = "/storeroom")
    public ResponseInfo insertStoreroomInfo(@RequestBody StoreroomInfoVo storeroomInfoVo) throws Exception {
        storeroomService.insertStoreroomInfo(storeroomInfoVo);
        return ResponseInfo.SUCCESS();
    }

    @PutMapping(value = "/storeroom")
    public ResponseInfo updateStoreroomInfo(@RequestBody StoreroomInfoVo storeroomInfo) throws Exception {
        storeroomService.updateStoreroomInfo(storeroomInfo);
        return ResponseInfo.SUCCESS();
    }

    @DeleteMapping(value = "/storeroom")
    public ResponseInfo deleteStoreroomInfo(StoreroomInfo storeroomInfo) throws Exception {
        storeroomService.deleteStoreroomInfo(storeroomInfo);
        return ResponseInfo.SUCCESS();
    }
}
