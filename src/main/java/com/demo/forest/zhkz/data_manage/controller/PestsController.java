package com.demo.forest.zhkz.data_manage.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.forest.util.ResponseInfo;
import com.demo.forest.zhkz.data_manage.domain.PestsInfo;
import com.demo.forest.zhkz.data_manage.service.PestsService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.demo.forest.util.ResponseInfo.SUCCESS;

@RestController
@RequiresRoles(value = {"超级管理员", "资料管理员"}, logical = Logical.OR)
public class PestsController {

    @Autowired
    private PestsService pestsService;

    @GetMapping(value = "/pests")
    public ResponseInfo queryPestsInfo(Page page, PestsInfo pestsInfo) throws Exception {
        return SUCCESS(pestsService.queryPestsInfo(page, pestsInfo));
    }

    @PostMapping(value = "/pests")
    public ResponseInfo insertPestsInfo(PestsInfo pestsInfo) throws Exception {
        pestsService.insertPestsInfo(pestsInfo);
        return SUCCESS();
    }

    @PutMapping(value = "/pests")
    public ResponseInfo updatePestsInfo(PestsInfo pestsInfo) throws Exception {
        pestsService.updatePestsInfo(pestsInfo);
        return SUCCESS();
    }

    @DeleteMapping(value = "/pests")
    public ResponseInfo deletePestsInfo(PestsInfo pestsInfo) throws Exception {
        pestsService.deletePestsInfo(pestsInfo);
        return SUCCESS();
    }
}
