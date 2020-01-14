package com.demo.forest.zhkz.data_manage.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.forest.util.ResponseInfo;
import com.demo.forest.zhkz.data_manage.domain.DiseaseInfo;
import com.demo.forest.zhkz.data_manage.service.DiseaseService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/disease")
@RequiresRoles(value = {"资料管理员"}, logical = Logical.OR)
public class DiseaseController {

    @Autowired
    private DiseaseService diseaseService;

    @RequestMapping(value = "/queryDiseaseInfo.ajax")
    public ResponseInfo queryDiseaseInfo(Page page, DiseaseInfo diseaseInfo) throws Exception {
        return ResponseInfo.SUCCESS(diseaseService.queryDiseaseInfo(page, diseaseInfo));
    }

    @RequestMapping(value = "/insertDiseaseInfo.ajax")
    public ResponseInfo insertDiseaseInfo(DiseaseInfo diseaseInfo) throws Exception {
        diseaseService.insertDiseaseInfo(diseaseInfo);
        return ResponseInfo.SUCCESS();
    }

    @RequestMapping(value = "/updateDiseaseInfo.ajax")
    public ResponseInfo updateDiseaseInfo(DiseaseInfo diseaseInfo) throws Exception {
        diseaseService.updateDiseaseInfo(diseaseInfo);
        return ResponseInfo.SUCCESS();
    }

    @RequestMapping(value = "/deleteDiseaseInfo.ajax")
    public ResponseInfo deleteDiseaseInfo(DiseaseInfo diseaseInfo) throws Exception {
        diseaseService.deleteDiseaseInfo(diseaseInfo);
        return ResponseInfo.SUCCESS();
    }
}
