package com.demo.forest.zhkz.data_manage.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.forest.util.ResponseInfo;
import com.demo.forest.zhkz.data_manage.domain.DiseaseInfo;
import com.demo.forest.zhkz.data_manage.service.DiseaseService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.demo.forest.util.ResponseInfo.SUCCESS;

@RestController
@RequiresRoles(value = {"资料管理员","超级管理员"}, logical = Logical.OR)
public class DiseaseController {

    @Autowired
    private DiseaseService diseaseService;

    @GetMapping(value = "/disease")
    public ResponseInfo queryDiseaseInfo(Page page, DiseaseInfo diseaseInfo) throws Exception {
        return SUCCESS(diseaseService.queryDiseaseInfo(page, diseaseInfo));
    }

    @PostMapping(value = "/disease")
    public ResponseInfo insertDiseaseInfo(DiseaseInfo diseaseInfo) throws Exception {
        diseaseService.insertDiseaseInfo(diseaseInfo);
        return SUCCESS();
    }

    @PutMapping(value = "/disease")
    public ResponseInfo updateDiseaseInfo(DiseaseInfo diseaseInfo) throws Exception {
        diseaseService.updateDiseaseInfo(diseaseInfo);
        return SUCCESS();
    }

    @DeleteMapping(value = "/disease")
    public ResponseInfo deleteDiseaseInfo(DiseaseInfo diseaseInfo) throws Exception {
        diseaseService.deleteDiseaseInfo(diseaseInfo);
        return SUCCESS();
    }
}
