package com.demo.forest.zhkz.data_manage.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.forest.util.ResponseInfo;
import com.demo.forest.zhkz.data_manage.domain.PestsInfo;
import com.demo.forest.zhkz.data_manage.service.PestsService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pests")
@RequiresRoles(value = {"超级管理员", "资料管理员"}, logical = Logical.OR)
public class PestsController {

    @Autowired
    private PestsService pestsService;

    @RequestMapping(value = "/queryPestsInfo.ajax")
    public ResponseInfo queryPestsInfo(Page page, PestsInfo pestsInfo) throws Exception {
        return ResponseInfo.SUCCESS(pestsService.queryPestsInfo(page, pestsInfo));
    }

    @RequestMapping(value = "/insertPestsInfo.ajax")
    public ResponseInfo insertPestsInfo(PestsInfo pestsInfo) throws Exception {
        pestsService.insertPestsInfo(pestsInfo);
        return ResponseInfo.SUCCESS();
    }

    @RequestMapping(value = "/updatePestsInfo.ajax")
    public ResponseInfo updatePestsInfo(PestsInfo pestsInfo) throws Exception {
        pestsService.updatePestsInfo(pestsInfo);
        return ResponseInfo.SUCCESS();
    }

    @RequestMapping(value = "/deletePestsInfo.ajax")
    public ResponseInfo deletePestsInfo(PestsInfo pestsInfo) throws Exception {
        pestsService.deletePestsInfo(pestsInfo);
        return ResponseInfo.SUCCESS();
    }
}
